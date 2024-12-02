//package com.example.capstone_project_orangehrm.Testcases;
//
//
//import com.example.capstone_project_orangehrm.POM.AdminPage;
//import com.example.capstone_project_orangehrm.POM.LoginPage;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class PIM_ModuleTest {
//    private LoginPage loginPage;
//    private WebDriver driver;
//
//    @BeforeMethod
//    public void setUpTest() {
//        // Initialize the base setup and instantiate the page objects
//        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
//        loginPage = new LoginPage(driver);
//    }
//
//    @Test(priority = 1)
//    public void testLoginAndAdminOperations() throws Exception {
//        // Fetch test data from Excel file
//        String baseUrl = getTestData("baseUrl");
//        String username = getTestData("Ausername");
//        String password = getTestData("Apassword");
//
//        // Navigate to the URL and perform login
//        driver.get(baseUrl);
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(password);
//        loginPage.clickLogin();
//
//        // Verify login was successful
//        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
//
//    }
//}
