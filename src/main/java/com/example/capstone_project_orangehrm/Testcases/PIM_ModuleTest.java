package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.POM.PIMPage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PIM_ModuleTest extends BaseClass {
    private LoginPage loginPage;
    private PIMPage pimPage;

    @BeforeMethod
    public void setUpTest() {
        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
        loginPage = new LoginPage(driver);
        pimPage = new PIMPage(driver);
    }

    @Test(priority = 1)
    public void testPIMOperation() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.searchButtonClick();
        pimPage.viewEmployeeDetails();

        // Verify the total records displayed
        String totalRecords = pimPage.getTotalRecords();
        Assert.assertNotNull(totalRecords, "Total records value is null.");
        System.out.println("Total records value is valid: " + totalRecords);
    }
    @Test(priority = 2)
    public void ViewEmployeeByid() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        String employeeId = getTestData("Pemployee_Id"); // Employee ID fetched from Excel

        // Navigate to base URL
        driver.get(baseUrl);

        // Perform login operation
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Assert login success
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");

        // Navigate to PIM module
        pimPage.clickPimTab();

        // Search employee by ID
        pimPage.searchEmployeeById(employeeId);

        // Verify employee details are displayed
        String totalRecords = pimPage.getTotalRecords();
        Assert.assertNotNull(totalRecords, "No records found for the provided employee ID.");
        System.out.println("Employee ID search successful. Total records displayed: " + totalRecords);
    }

}
