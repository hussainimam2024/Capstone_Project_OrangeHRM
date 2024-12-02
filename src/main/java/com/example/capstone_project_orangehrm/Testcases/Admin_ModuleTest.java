package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.AdminPage;
import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Admin_ModuleTest extends BaseClass {
    private LoginPage loginPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setUpTest() {
        // Initialize the base setup and instantiate the page objects
        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
    }

    @Test(priority = 1)
    public void testLoginAndAdminOperations() throws Exception {
        // Fetch test data from Excel file
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");

        // Navigate to the URL and perform login
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        // Verify login was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");

        // Now click on the Admin tab
        adminPage.clickAdminTab();
        adminPage.clickAddUser();

        // Fetch admin user data
        String adminUsername = getTestData("Add_username");
        String adminPassword = getTestData("Add_password");
        String confirmPassword = adminPassword;

        // Fill in the admin user details and save
        adminPage.fillInUserDetails(adminUsername, adminPassword, confirmPassword);
        adminPage.clickSave();

        // Perform edit user operation
        // Fetch new username for editing from test data
        adminPage.clickEditUser();
        String newUsername = getTestData("Edit_username");
        adminPage.editUserfields(newUsername);

        // Perform delete user operation
        adminPage.clickDeleteUser();
        adminPage.clickConfirmDelete();
    }
}