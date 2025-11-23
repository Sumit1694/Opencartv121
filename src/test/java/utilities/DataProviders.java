package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String [][] getData() throws IOException
    {
        String filePath = System.getProperty("user.dir") + "//testData//LoginTestData.xlsx";
        ExcelUtility xlutil = new ExcelUtility(filePath); //creating object for xlUtility

        String sheetName = "Sheet1";

        int totalRows = xlutil.getRowCount(sheetName);
        int totalCols = xlutil.getCellCount(sheetName, 1);

        String loginData[][] = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
            	loginData[i - 1][j] = xlutil.getCellData(sheetName, i, j);
            }
        }
        return loginData;
    }
}
