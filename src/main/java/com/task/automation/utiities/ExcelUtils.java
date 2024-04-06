package com.task.automation.utiities;

import com.task.automation.helper.CustomHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;


public class ExcelUtils {

    public static Object[][] getExcelData(String fileName){

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFCell cell;
        Object[][] data = null;

        InputStream is = ExcelUtils.class.getResourceAsStream("/testdata/" + fileName);
        String sheetName = "productList";

        try{
            workbook = new XSSFWorkbook(is);
            sheet = workbook.getSheet(sheetName);

            int total_column = sheet.getRow(0).getLastCellNum();
            int total_row = sheet.getPhysicalNumberOfRows();
             data = new Object[total_row-1][total_column];



            for(int i=0;i<total_row-1;i++){
                Row row = sheet.getRow(i+1);
                for(int j=0;j<total_column;j++){
                    data[i][j] = new DataFormatter().formatCellValue(row.getCell(j));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }


}
