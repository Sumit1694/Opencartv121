package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties prop;

	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException
	{
		logger = LogManager.getLogger(this.getClass());

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//config.properties");
		prop = new Properties();
		prop.load(fis);

		String appUrl = prop.getProperty("appUrl");

		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();

			// OS Setup
		    if (os.equalsIgnoreCase("windows")) {
		        capabilities.setPlatform(Platform.WIN11);
		    } else if (os.equalsIgnoreCase("mac")) {
		        capabilities.setPlatform(Platform.MAC);
		    } else if (os.equalsIgnoreCase("linux")) {
		        capabilities.setPlatform(Platform.LINUX);
		    } else {
		        System.out.println("Invalid OS name");
		        return;
		    }

		    switch (br.toLowerCase())
		    {
		        case "chrome": capabilities.setBrowserName("chrome"); break;
		        case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
		        case "firefox": capabilities.setBrowserName("firefox"); break;
		        case "safari": capabilities.setBrowserName("safari"); break;
		        case "opera": capabilities.setBrowserName("opera"); break;
		        default: System.out.println("Unsupported browser: " + br); return;
		    }
			driver = new RemoteWebDriver(new URL("http://10.19.201.38:4444"),capabilities);
		}

		if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name:....."); return;
			}
		}

		driver.get(appUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown()
	{
		driver.quit();
	}

	public String captureScreen(String testName) {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String screenshotName = testName + "_" + timestamp + ".png";

		String path = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName;

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			File dest = new File(path);
			dest.getParentFile().mkdirs(); // Create folder if not exists
			FileUtils.copyFile(src, dest);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	@SuppressWarnings("deprecation")
	public String getRandomString()
	{
		return RandomStringUtils.randomAlphabetic(6);
	}

	@SuppressWarnings("deprecation")
	public String getRandomNumber()
	{
		return RandomStringUtils.randomNumeric(10);
	}

	@SuppressWarnings("deprecation")
	public String getRandomAlphaNumeric()
	{
		return RandomStringUtils.randomAlphabetic(6) + RandomStringUtils.randomNumeric(10);
	}

}
