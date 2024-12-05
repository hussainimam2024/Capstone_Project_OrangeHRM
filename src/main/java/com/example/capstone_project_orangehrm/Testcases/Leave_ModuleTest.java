package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.LeavePage;
import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Leave_ModuleTest extends BaseClass {
    private LoginPage loginPage;
    private LeavePage leavePage;

    @BeforeMethod
    public void setUpTest() {
        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
        loginPage = new LoginPage(driver);
        leavePage = new LeavePage(driver);
    }

    @Test(priority = 1)
    public void applyForLeave() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");

        // Navigate to the base URL
        driver.get(baseUrl);

        // Login logic (use LoginPage class methods if available)
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Navigate to the Leave module
        leavePage.clickLeaveTab();
        leavePage.clickLeaveButton();

        // Enter a reason for leave and apply
        leavePage.enterComment("Family emergency leave request.");
        leavePage.clickApplyButton();

        System.out.println("Leave application submitted successfully.");
    }
    @Test(priority = 2)
    public void MyLeave() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");

        // Navigate to the base URL
        driver.get(baseUrl);

        // Login logic (use LoginPage class methods if available)
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Navigate to the Leave module
        leavePage.clickLeaveTab();
        leavePage.MyLeave();
        String totalleave_info = leavePage.getTotalLeave();
        System.out.println(totalleave_info);
        System.out.println("My Leave record shown successfully.");
    }
    @Test(priority = 3)
    public void AssignLeave() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");

        // Navigate to the base URL
        driver.get(baseUrl);

        // Login logic (use LoginPage class methods if available)
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Actions to trigger the failure
        leavePage.clickLeaveTab();
        leavePage.clickLeaveAssignMenu();
        leavePage.fillCommentAndAssignLeave();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully after the test.");
        }
    }
}
