package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {

    private String filePath;
    private FileInputStream fi;
    private FileOutputStream fo;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;

    public ExcelUtility(String filePath) {
        this.filePath = filePath;
    }

    public int getRowCount(String sheetName) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum();
            workbook.close();
            fi.close();
            return rowCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCellCount(String sheetName, int rowNum) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            int cellCount = row.getLastCellNum();
            workbook.close();
            fi.close();
            return cellCount;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getCellData(String sheetName, int rowNum, int colNum) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            String data;

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);

            workbook.close();
            fi.close();
            return data;

        } catch (Exception e) {
            return "";
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);

            row = sheet.getRow(rowNum);
            if (row == null)
                row = sheet.createRow(rowNum);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            fo = new FileOutputStream(filePath);
            workbook.write(fo);

            workbook.close();
            fi.close();
            fo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillGreenColour(String sheetName, int rowNum, int colNum) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);

            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            fo = new FileOutputStream(filePath);
            workbook.write(fo);

            workbook.close();
            fi.close();
            fo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillRedColour(String sheetName, int rowNum, int colNum) {
        try {
            fi = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);

            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);

            fo = new FileOutputStream(filePath);
            workbook.write(fo);

            workbook.close();
            fi.close();
            fo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
