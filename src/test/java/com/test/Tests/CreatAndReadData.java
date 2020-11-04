package com.test.Tests;

import com.github.javafaker.Faker;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CreatAndReadData {

   XSSFWorkbook workbook;
   XSSFSheet workSheet;
   XSSFRow sheetRow;
   XSSFCell rowCell;

   String filePath = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "TestingData.xlsx";
   String sheetName = "Test_Data";

   @Test(enabled = false)
    public void createExcelSheet() throws IOException {

       workbook = new XSSFWorkbook();
       workSheet = workbook.createSheet(sheetName);

       Map<Integer, Object[]> map = new HashMap<>();


       int dataCount = 10;
       for (int count = 1; count < dataCount; count++) {

           String firstName = new Faker().name().firstName();
           String lastName = new Faker().name().lastName();
           String address = new Faker().address().streetAddress();
           String city = new Faker().address().cityName();
           String state = new Faker().address().state();
           String zipCode = new Faker().address().zipCode();
           String phoneNumber = new Faker().phoneNumber().cellPhone();
           String DOB = new Faker().date().birthday().toString();

           map.put(count, new Object[]{firstName, lastName, address, city, state, zipCode, phoneNumber, DOB});
       }

       map.put(0, new  Object[]{"First Name", "Last Name", "Address", "City", "State", "Zip Code", "Phone Number", "Date of Birth"});

       Set<Integer> mapSet = map.keySet();
       int row = 0;

       for(int id : mapSet){
           Object[] values = map.get(id);
           sheetRow = workSheet.createRow(row++);

           int cell = 0;
           for (Object value : values ){


               rowCell = sheetRow.createCell(cell++);
               rowCell.setCellValue(value.toString());
           }
       }

       FileOutputStream file = new FileOutputStream(filePath);
       workbook.write(file);
       file.close();
   }

   //@Test(dependsOnMethods = "createExcelSheet")
    public Object[][] readExcelSheet() throws IOException {

       FileInputStream file = new FileInputStream(filePath);

       workbook = new XSSFWorkbook(file);
       workSheet = workbook.getSheet(sheetName);

       int totalRow = workSheet.getPhysicalNumberOfRows();
       int totalCell = workSheet.getRow(0).getLastCellNum();

       System.out.println("This is the total available row:  " + totalRow + " and total available cell:  " + totalCell);

       Object[][] data = new Object[totalRow][totalCell];

       for (int row = 0; row < totalRow; row++){

           for (int cell = 0; cell < totalCell; cell++) {

               data[row][cell] = workSheet.getRow(row).getCell(cell).getStringCellValue().toString();


//               System.out.println(row + "  These are the data in the excel sheet:    " + data[row][cell] + " \n ");
           }
       }

       return data;

   }

   @DataProvider
    public Object[][] datapro() throws IOException {

       Object [][] data=new CreatAndReadData().readExcelSheet();
       return data;
   }

   @Test(dataProvider="datapro")
    public void Print(String firstname, String lastName, String address, String city, String state, String zipCode, String phoneNumber, String dob ){


       System.out.printf(firstname, lastName, address, city, state, zipCode, phoneNumber, dob);


   }
}
