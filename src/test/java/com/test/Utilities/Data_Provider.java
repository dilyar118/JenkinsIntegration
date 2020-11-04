package com.test.Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Data_Provider {


    @DataProvider
    public Object[][] data_Provider() throws IOException {
      Data_Provider readEx  = new Data_Provider();
      Object[][] data = readEx.readExcel();

      return data;
    }

    @Test(dataProvider ="data_Provider")
    public void testing(String firstName, String lastName, String job, String address, String phoneNumber){
        System.out.println(firstName + lastName + job + address +phoneNumber);



}
    public Object[][] readExcel() throws IOException {

        XSSFWorkbook workbook;
        XSSFSheet workSheet;
        XSSFRow sheetRow;
        XSSFCell sheetCell;

        String filePath = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "DataTesting.xlsx";

        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);

         workSheet = workbook.getSheetAt(0);

         int totalRow = workSheet.getPhysicalNumberOfRows();
         int totalCell = workSheet.getRow(0).getLastCellNum();

         Object[][] data = new Object[totalRow][totalCell];

         for(int row =0; row < totalRow; row++){

             for (int col = 0; col < totalCell; col++){

                 data[row][col] = workSheet.getRow(row).getCell(col).getStringCellValue();
                 System.out.println(data[row][col]);
             }
         }

         return data;
    }


}
