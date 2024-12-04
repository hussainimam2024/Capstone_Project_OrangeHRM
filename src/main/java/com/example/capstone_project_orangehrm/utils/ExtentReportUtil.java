//package com.example.capstone_project_orangehrm.utils;
//
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.example.capstone_project_orangehrm.Testcases.Login_AdminTest;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExtentReportUtil {
//    private static ExtentReports extentReports;
//    private static ExtentTest test;
//
//    // Initialize ExtentReports instance
//    public static void initializeReport() {
//        if (extentReports == null) {
//            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String reportPath = "ExtentReports_" + timestamp + ".html";
//            extentReports = new ExtentReports(reportPath, true);
//        }
//    }
//
//    // Start a test with module name (e.g., Login, Admin, etc.)
//    public static void startTest(String moduleName, WebDriver driver) {
//        test = extentReports.startTest(Login_AdminTest);
//
//        takeScreenshot(driver); // Take screenshot for test start if needed
//    }
//
//    // Log test step with status
//    public static void logStep(String message, String status) {
//        if (status.equalsIgnoreCase("PASS")) {
//            test.log(com.relevantcodes.extentreports.LogStatus.PASS, message);
//        } else if (status.equalsIgnoreCase("FAIL")) {
//            test.log(com.relevantcodes.extentreports.LogStatus.FAIL, message);
//        } else {
//            test.log(com.relevantcodes.extentreports.LogStatus.INFO, message);
//        }
//    }
//
//    // Take screenshot (optional) to include in report
//    public static void takeScreenshot(WebDriver driver) {
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String screenshotPath = "screenshots/" + System.currentTimeMillis() + ".png";
//        screenshot.renameTo(new File(screenshotPath));
//        test.addScreenCapture(screenshotPath);
//    }
//
//    // End the test
//    public static void endTest() {
//        extentReports.endTest(test);
//    }
//
//    // Flush the Extent report after tests are done
//    public static void flushReport() {
//        if (extentReports != null) {
//            extentReports.flush();
//        }
//    }
//}
