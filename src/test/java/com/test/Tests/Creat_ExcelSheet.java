package com.test.Tests;


import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Creat_ExcelSheet {

    XSSFWorkbook workbook;
    XSSFSheet workSheet;
    XSSFRow sheetRow;
    XSSFCell rowCell;

    String filePath = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "DataTesting.xlsx";
    String sheetName = "Test_data";

    @Test
    public void createExcelSheet() throws IOException {

       workbook = new XSSFWorkbook();

       XSSFFont font = workbook.createFont();
       font.setFontHeightInPoints((short)18);
       font.setBoldweight((short)14);
       font.setFontName("Athelas");
       font.setItalic(true);
       font.setStrikeout(false);


       CellStyle style = workbook.createCellStyle();
       style.setAlignment(CellStyle.ALIGN_LEFT);
       workSheet = workbook.createSheet(sheetName);


       for (int rows = 0; rows < 9; rows++) {


           workSheet.setColumnWidth(rows, 30 * 256);
           style.setFont(font);
       }

        Map<Integer,  Object[]> map = new HashMap<>();

        int dataCount = 100;
        for (int count = 1; count < dataCount; count++){

            String firstName = new Faker().name().firstName();
            String lastName = new Faker().name().lastName();
            String address = new Faker().address().streetAddress();
            String cityName = new Faker().address().cityName();
            String state = new Faker().address().state();
            String zipCode = new Faker().address().zipCode();
            String country = new Faker().address().country();
            String phone = new Faker().phoneNumber().cellPhone();
            String gender = new Faker().demographic().sex();
            String jobTitle = new Faker().job().title();
            String salary = new Faker().number().digits(6).toString();

            map.put(count, new Object[]{firstName, lastName, address, cityName, state, zipCode, country, phone, gender, jobTitle, salary});

        }

        map.put(0, new Object[]{"First Name", "Last Name", "Address", "City Name", "State", "Zip Code", "Country", "Phone", "Gender", "Job Title", "Salary"});

        Set<Integer> mapSet = map.keySet();
        int row = 0;
        for(int id : mapSet){
            Object[] values = map.get(id);
            sheetRow = workSheet.createRow(row++);

            int cell = 0;
            for (Object value : values){
                rowCell = sheetRow.createCell(cell++);
                rowCell.setCellValue(value.toString());
                rowCell.setCellStyle(style);

            }
        }
        FileOutputStream file = new FileOutputStream(new File(filePath));
        workbook.write(file);
        file.close();


    }

    @Test (dependsOnMethods = "createExcelSheet")
    public void readExcelSheet() throws IOException {

        FileInputStream file = new FileInputStream(filePath);

        workbook = new XSSFWorkbook(file);
        workSheet = workbook.getSheet(sheetName);
        int totalRow = workSheet.getPhysicalNumberOfRows();
        int totalCell = workSheet.getRow(0).getLastCellNum();

        System.out.println("The total available row is \t" +  totalRow + "\t total available cell is \t" + totalCell);

        Object[][] data = new Object[totalRow][totalCell];

        for (int row = 0; row < totalRow; row++){

            for (int cell = 0; cell < totalCell; cell++){

                data[row][cell] = workSheet.getRow(row).getCell(cell).getStringCellValue();

                System.out.println(data[row][cell]);
            }
        }

    }


}

