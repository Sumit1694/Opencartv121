package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Automation-Report-" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/" + reportName);

        // ========== REPORT UI DESIGN ==========
        spark.config().setDocumentTitle("Automation Test Execution Report");
        spark.config().setReportName("Hybrid Selenium Framework Report");
        spark.config().setTheme(Theme.DARK);
        spark.config().setEncoding("UTF-8");
        spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // ========== SYSTEM INFO ==========
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Framework", "Hybrid (POM + TestNG)");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Executed By", "Sumit Rane");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName())
                        .assignCategory(result.getMethod().getGroups())
                        .assignAuthor("Sumit Rane")
                        .assignDevice(System.getProperty("os.name"));

        // Add method description (if @Test(description="") used)
        if (result.getMethod().getDescription() != null) {
            extentTest.info("Description: " + result.getMethod().getDescription());
        }

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("STATUS: PASS");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("STATUS: FAIL");
        test.get().fail(result.getThrowable());

        try {
            BaseClass base = (BaseClass) result.getInstance();

            // Capture screenshot
            String screenshotPath = base.captureScreen(result.getMethod().getMethodName());

            // Attach screenshot
            test.get().addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");

        } catch (Exception e) {
            test.get().warning("Unable to attach screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("STATUS: SKIPPED");
        test.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
