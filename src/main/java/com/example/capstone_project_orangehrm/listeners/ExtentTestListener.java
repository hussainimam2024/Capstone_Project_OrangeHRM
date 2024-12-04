package com.example.capstone_project_orangehrm.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentTestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private WebDriver driver; // WebDriver is declared but initialized inside onTestStart
    private PrintStream originalStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReportFinal.html");
        sparkReporter.config().setDocumentTitle("Capstone project automation Report");
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Imam Hussain");
        extent.setSystemInfo("Environment", "QA");

        // Capture console output
        byteArrayOutputStream = new ByteArrayOutputStream();
        originalStream = System.out;
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Initialize WebDriver here
        driver = (WebDriver) result.getTestContext().getAttribute("driver"); // Assuming 'driver' is set in your tests
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
        logConsoleOutput();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testThread.get();
        test.fail(result.getThrowable());
        try {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null && !screenshotPath.isEmpty()) {
                test.addScreenCaptureFromPath(screenshotPath, "Test Failure Screenshot");
            } else {
                test.info("Screenshot could not be captured.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logConsoleOutput();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test skipped");
        logConsoleOutput();
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
        System.setOut(originalStream);
    }

    private String captureScreenshot(String screenshotName) throws IOException {
        if (driver == null) {
            System.out.println("WebDriver is not initialized. Cannot capture screenshot.");
            return null;
        }

        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File screenshotsDir = new File(System.getProperty("user.dir") + "/Screenshots/");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    private void logConsoleOutput() {
        String consoleOutput = byteArrayOutputStream.toString();
        if (!consoleOutput.isEmpty()) {
            testThread.get().info("Console Output: " + consoleOutput);
        }
        byteArrayOutputStream.reset();
    }
}

//package com.example.capstone_project_orangehrm.listeners;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class ExtentTestListener implements ITestListener {
//    private static ExtentReports extent;
//    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
//    private WebDriver driver; // Inject this via constructor or setup method if needed.
//
//    @Override
//    public void onStart(ITestContext context) {
//        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/ExtentReportFinal.html");
//        sparkReporter.config().setDocumentTitle("Automation Report");
//        sparkReporter.config().setReportName("Test Execution Report");
//        sparkReporter.config().setTheme(Theme.STANDARD);
//
//        extent = new ExtentReports();
//        extent.attachReporter(sparkReporter);
//        extent.setSystemInfo("Tester", "Imam Hussain");
//        extent.setSystemInfo("Environment", "QA");
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
//        testThread.set(test);
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        testThread.get().pass("Test passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        ExtentTest test = testThread.get();
//        test.fail(result.getThrowable());
//        try {
//            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
//            test.addScreenCaptureFromPath(screenshotPath, "Test Failure Screenshot");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Allow the next test to run even after a failure by not throwing any exceptions here
//        // TestNG will still continue with the remaining tests as expected.
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        testThread.get().skip("Test skipped");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        if (extent != null) {
//            extent.flush();
//        }
//    }
//
//    private String captureScreenshot(String screenshotName) throws IOException {
//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//
//        // Ensure the Screenshots directory exists
//        File screenshotsDir = new File(System.getProperty("user.dir") + "/Screenshots/");
//        if (!screenshotsDir.exists()) {
//            screenshotsDir.mkdirs();
//        }
//
//        // Capture screenshot
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//
//        // Define destination file path
//        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + "_" + dateName + ".png";
//        File finalDestination = new File(destination);
//
//        // Copy screenshot to the destination
//        FileUtils.copyFile(source, finalDestination);
//
//        return destination;
//    }
//}
