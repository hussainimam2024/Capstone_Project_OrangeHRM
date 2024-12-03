package com.example.capstone_project_orangehrm.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.IOException;

public class BaseClass {
    protected WebDriver driver;
    private Sheet sheet;

    // Set up WebDriver and load Excel file
    public void setUp(String excelFilePath) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        try (FileInputStream file = new FileInputStream(excelFilePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found in the Excel file.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading Excel file: " + e.getMessage());
        }
    }

    // Fetch test data from Excel based on field name (handles String and numeric)
    public String getTestData(String fieldName) {
        if (sheet == null) {
            throw new RuntimeException("Sheet is not initialized. Please call setUp() first.");
        }

        for (Row row : sheet) {
            // Assuming data starts from row index 1 (skipping header row)
            if (row.getCell(0) != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(fieldName)) {
                Cell cell = row.getCell(1); // Assuming the value is in the second column
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            return cell.getStringCellValue();
                        case NUMERIC:
                            return String.valueOf((int) cell.getNumericCellValue()); // Convert numeric to String
                        default:
                            throw new RuntimeException("Unsupported cell type for field: " + fieldName);
                    }
                }
            }
        }
        throw new RuntimeException("Test data not found for field: " + fieldName);
    }

    // Close WebDriver after tests are done
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

//
//
//package com.example.capstone_project_orangehrm.base;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class BaseClass {
//    protected WebDriver driver;
//    private Sheet sheet;
//    public void setUp(String excelFilePath) {
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        driver.manage().window().maximize();
//
//        try (FileInputStream file = new FileInputStream(excelFilePath);
//             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
//            sheet = workbook.getSheetAt(0);
//            if (sheet == null) {
//                throw new RuntimeException("Sheet not found in the Excel file.");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Error loading Excel file: " + e.getMessage());
//        }
//    }
//
//    // Method to fetch test data from Excel based on field name
//    public String getTestData(String fieldName) {
//        if (sheet == null) {
//            throw new RuntimeException("Sheet is not initialized. Please call setUp() first.");
//        }
//
//        for (Row row : sheet) {
//            // Assuming the data starts from row index 1 (skipping header row)
//            if (row.getCell(0) != null && row.getCell(0).getStringCellValue().equalsIgnoreCase(fieldName)) {
//                return row.getCell(1).getStringCellValue();  // Return the corresponding value in the second column
//            }
//        }
//        throw new RuntimeException("Test data not found for field: " + fieldName);
//    }
//
//    // Close WebDriver after tests are done
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}