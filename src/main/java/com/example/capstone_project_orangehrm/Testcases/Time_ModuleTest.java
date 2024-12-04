package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.LeavePage;
import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.POM.TimePage;
import com.example.capstone_project_orangehrm.base.BaseClass;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Time_ModuleTest extends BaseClass {
    private LoginPage loginPage;
    private TimePage timePage;

    @BeforeMethod
    public void setUpTest() {
        setUp("C:\\Users\\imam.hussain\\IdeaProjects\\Capstone_Project_OrangeHRM\\ExcelDatafinal.xlsx");
        loginPage = new LoginPage(driver);
        timePage= new TimePage(driver);
    }

    @Test(priority = 1)
    public void testTimeModule() {
        String baseUrl = getTestData("baseUrl");
        String username = getTestData("Ausername");
        String password = getTestData("Apassword");

        // Navigate to the base URL
        driver.get(baseUrl);

        // Login logic (use LoginPage class methods if available)
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        // Click Time tab
        timePage.clickTimeTab();
        String timeTabHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Time Tab: " + timeTabHeading);

        // Click Timesheet menu
        timePage.clickTimesheetMenu();
        String timesheetMenuHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Timesheet Menu: " + timesheetMenuHeading);

        // Click My Timesheet option
        timePage.clickMyTimesheetOption();
        String myTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking My Timesheet Option: " + myTimesheetHeading);

        timePage.clickTimesheetMenu();
        // Click Empty Timesheet option
        timePage.EmployeeTimesheet() ;
        String emptyTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Empty Timesheet Option: " + emptyTimesheetHeading);

        // View the sheet
        timePage.viewSheet();
        String viewSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after viewing the sheet: " + viewSheetHeading);

        timePage.createSheet();
        String createSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after creating sheet: " + createSheetHeading);

        // Verify the status after creating the sheet
        String createSheetStatus = timePage.getStatus(); // Assuming getStatus() method fetches the status text.
        System.out.println("Status after creating sheet: " + createSheetStatus);

        // Submit the created sheet
        timePage.submitSheet();
        String submitSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after submitting the sheet: " + submitSheetHeading);

        // Verify the status after submitting the sheet
        String submitSheetStatus = timePage.getStatus();
        System.out.println("Status after submitting sheet: " + submitSheetStatus);

        // Scroll to make the Approve button visibl

        // Approve the timesheet
        timePage.approveTimesheet();
        String approveTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after approving timesheet: " + approveTimesheetHeading);

        // Verify the status after approving the timesheet
        String approveTimesheetStatus = timePage.getStatus();
        System.out.println("Status after approving timesheet: " + approveTimesheetStatus);

        // Click Attendance menu
        timePage.clickAttendanceMenu();
        String attendanceMenuHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking Attendance Menu: " + attendanceMenuHeading);

        // Click My Records option
        timePage.clickMyRecords();
        String myRecordsHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking My Records: " + myRecordsHeading);

        // Click Project Info menu
        timePage.clickProjectInfoMenu();
        String projectInfoMenuHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking Project Info Menu: " + projectInfoMenuHeading);

        // Click Customer option
        timePage.clickCustomerOption();
        String customerOptionRecords = timePage.verifyRecords();
        System.out.println("Records after clicking Customer Option: " + customerOptionRecords);
    }
}
