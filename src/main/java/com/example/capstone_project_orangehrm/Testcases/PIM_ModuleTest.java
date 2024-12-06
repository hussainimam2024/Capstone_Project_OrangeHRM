package com.example.capstone_project_orangehrm.Testcases;
import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.POM.PIMPage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
    public void addEmployee() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        String employeeId = getTestData("Pemployee_Id");
        String firstName = getTestData("Pemployee_First");
        String lastName = getTestData("Pemployee_Last");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.clickAddButton();
        pimPage.enterFirstName(firstName);
        pimPage.enterLastName(lastName);
        pimPage.enterEmployeeId(employeeId);
        pimPage.clickSaveButton();
    }
    @Test(priority = 2)
    public void addEmployee_withoutdetails() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        String employeeId = getTestData("Pemployee_Id");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.clickAddButton();
        pimPage.enterEmployeeId(employeeId);
        pimPage.clickSaveButton();
        System.out.println("Employee not added due to blank firstname and lastname");
    }
    @Test(priority = 3)
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
        String totalRecords = pimPage.getTotalRecords();
        Assert.assertNotNull(totalRecords, "Total records value is null.");
        System.out.println("Total records value is valid: " + totalRecords);
    }
    @Test(priority = 4)
    public void ViewEmployeeByid() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        String employeeId = getTestData("Pemployee_Id");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.searchEmployeeById(employeeId);
        String totalRecords = pimPage.getTotalRecords();
        Assert.assertNotNull(totalRecords, "No records found for the provided employee ID.");
        System.out.println("Employee ID search successful. Total records displayed: " + totalRecords);
    }
    @Test(priority = 5)
    public void ViewReports(){
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.ReportGenerate();
        String heading = pimPage.Verifyheading();
        System.out.println(heading);
    }
    @Test(priority = 6)
    public void ESS() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickInfoTab();
        String empName = pimPage.VerifyInfoEmp_name();
        System.out.println("Verified employee name: " + empName);
    }
    @Test(priority = 7)
    public void updatePersonalInfo() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        String employeeId = getTestData("Pemployee_Id");
        String firstName = getTestData("Pemployee_First");
        String lastName = getTestData("Pemployee_Last");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickInfoTab();
        pimPage.updateFirstName(firstName);
        pimPage.updateLastName(lastName);
        pimPage.updateEmployeeId("");
        pimPage.clickSaveButtonAfterEdit();
        pimPage.scrollToPersonalDetailsEdit();

    }
    @Test(priority = 8)
    public void Configuration() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed for valid credentials.");
        pimPage.clickPimTab();
        pimPage.clickConfigureMenu();
        pimPage.clickOptionalOption();
        Assert.assertEquals(pimPage.getVerifyTextOption(), "Optional Fields", "Optional Fields text verification failed.");
        pimPage.toggleButton1();
        pimPage.toggleButton2();
        pimPage.clickConfigureSave();
        pimPage.clickConfigureMenu();
        pimPage.clickCustomOption();
        Assert.assertEquals(pimPage.getVerifyTextCustom(), "Custom Fields", "Custom Fields text verification failed.");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed successfully after the test.");
        }
    }





}
