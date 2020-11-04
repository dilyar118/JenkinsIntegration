package com.test.Tests;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataCreatAndRead {

    XSSFWorkbook  workBook;
    XSSFSheet worksheet;
    XSSFRow   sheetRow;
    XSSFCell  rowCell;

    String filePath = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "Testing.xlsx";
    String sheetName = "Test_Data";


    @Test
    public void write_exl_sheet() throws IOException {

        workBook = new XSSFWorkbook();
        worksheet =   workBook.createSheet(sheetName);

        Map<String, Object[]> map = new HashMap<String, Object[]>();
        map.put("0", new Object[] { "First_name", "Last_name", "Phone"});
        map.put("1", new Object[] { "Alim", "Memet", "555-333-4444"});
        map.put("2", new Object[] { "Murat", "Ataman", " 333-222-6666"});
        map.put("3", new Object[] { "Kuddus", "Imam", "333-=222-9999"});

        Set<String> mapSet = map.keySet();
        int row = 0;
        for( String id : mapSet){

            sheetRow = worksheet.createRow(row++);
            Object[] values = map.get(id);

            int cell = 0;
            for( Object value : values){

              rowCell = sheetRow.createCell(cell++);
              rowCell.setCellValue(value.toString());
            }
        }

        FileOutputStream file = new FileOutputStream(new File(filePath));

        workBook.write(file);
        file.close();

    }


    @Test
    public void Read_excel_sheet() throws IOException {

        FileInputStream file = new FileInputStream(filePath);

        workBook = new XSSFWorkbook(file);

        worksheet = workBook.getSheet(sheetName);

        int totalRow = worksheet.getPhysicalNumberOfRows();
        int totalCol = worksheet.getRow(0).getLastCellNum();
        System.out.println("Total aviable row " + totalRow + " Total avaible Column " + totalCol);

        Object[][] data = new Object[totalRow][totalCol];

        for(int row = 0; row < totalRow; row++){

            for(int col = 0; col < totalCol; col++){

                data[row][col] =  worksheet.getRow(row).getCell(col).getStringCellValue().toString();

                System.out.println( worksheet.getRow(row).getCell(col).getStringCellValue().toString());



                /*
                map.put("0", new Object[] {"First_name", "Last_name", "Job", "Address", "Phone_number"});
                map.put("1", new Object[] {"Mehmet", "Boptike", "Hokaqi", "Fairfax", "555-444-3334"});
                map.put("2", new Object[] {"Muralim", "Po", "Botka", "Fairfax", "444-333-6666"});
                map.put("3", new Object[] {"Ladi", "Piqak", "Joda", "Fairfax", "568-888-9999"});
                map.put("4", new Object[] {"Ehsan", "MiningRas", "IT", "Centreville", "333-222-4444"});
                map.put("5", new Object[] {"Urayim", "Diwani", "Bikaqi", "Centreville", "777-444-333"});
                map.put("6", new Object[] {"Gheyret", "Mergen", "Chef", "Annandale", "999-777-0000"});
                 */
            }
        }


    }





}
