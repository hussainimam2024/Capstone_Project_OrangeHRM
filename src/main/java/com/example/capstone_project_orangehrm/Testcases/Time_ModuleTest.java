package com.example.capstone_project_orangehrm.Testcases;

import com.example.capstone_project_orangehrm.POM.LoginPage;
import com.example.capstone_project_orangehrm.POM.TimePage;
import com.example.capstone_project_orangehrm.base.BaseClass;

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
        driver.get(baseUrl);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        timePage.clickTimeTab();
        String timeTabHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Time Tab: " + timeTabHeading);
        timePage.clickTimesheetMenu();
        String timesheetMenuHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Timesheet Menu: " + timesheetMenuHeading);
        timePage.clickMyTimesheetOption();
        String myTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking My Timesheet Option: " + myTimesheetHeading);
        timePage.clickTimesheetMenu();
        timePage.EmployeeTimesheet() ;
        String emptyTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after clicking Empty Timesheet Option: " + emptyTimesheetHeading);
        timePage.viewSheet();
        String viewSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after viewing the sheet: " + viewSheetHeading);
        timePage.createSheet();
        String createSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after creating sheet: " + createSheetHeading);
        String createSheetStatus = timePage.getStatus(); // Assuming getStatus() method fetches the status text.
        System.out.println("Status after creating sheet: " + createSheetStatus);
        timePage.submitSheet();
        String submitSheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after submitting the sheet: " + submitSheetHeading);

        String submitSheetStatus = timePage.getStatus();
        System.out.println("Status after submitting sheet: " + submitSheetStatus);
        timePage.approveTimesheet();
        String approveTimesheetHeading = timePage.verifySheetHeading();
        System.out.println("Heading after approving timesheet: " + approveTimesheetHeading);
        String approveTimesheetStatus = timePage.getStatus();
        System.out.println("Status after approving timesheet: " + approveTimesheetStatus);

        timePage.clickAttendanceMenu();
        String attendanceMenuHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking Attendance Menu: " + attendanceMenuHeading);
        timePage.clickMyRecords();
        String myRecordsHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking My Records: " + myRecordsHeading);
        timePage.clickProjectInfoMenu();
        String projectInfoMenuHeading = timePage.getHeadingText();
        System.out.println("Heading after clicking Project Info Menu: " + projectInfoMenuHeading);
        timePage.clickCustomerOption();
        String customerOptionRecords = timePage.verifyRecords();
        System.out.println("Records after clicking Customer Option: " + customerOptionRecords);
    }
}
