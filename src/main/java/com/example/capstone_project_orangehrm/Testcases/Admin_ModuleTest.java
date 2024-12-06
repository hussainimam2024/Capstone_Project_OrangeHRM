package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.AdminPage;
import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Admin_ModuleTest extends BaseClass {
    private LoginPage loginPage;
    private AdminPage adminPage;
    @BeforeMethod
    public void setUpTest() {
        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }
    @Test(priority = 1)
    public void testLoginAndAdminOperations() throws Exception {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        adminPage.clickAdminTab();
        adminPage.clickAddUser();
        String adminUsername = getTestData("Add_username");
        String adminPassword = getTestData("Add_password");
        String confirmPassword = adminPassword;
        adminPage.fillInUserDetails(adminUsername, adminPassword, confirmPassword);
        adminPage.clickSave();
        adminPage.clickEditUser();
        String newUsername = getTestData("Edit_username");
        adminPage.editUserfields(newUsername);
        adminPage.clickDeleteUser();
        adminPage.clickConfirmDelete();
        adminPage.Jobmenubutton();
        adminPage.JobTitleOptions();
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully after the test.");
        }
    }
}