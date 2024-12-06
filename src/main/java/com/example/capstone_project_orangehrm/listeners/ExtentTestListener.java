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
    private WebDriver driver;
    private PrintStream originalStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    public ExtentTestListener(WebDriver driver){
        this.driver = driver;
    }
    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./Extentreports/ExtentReportAllModule.html");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Imam Hussain - OrangeHRM Automation Report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Imam Hussain");
        extent.setSystemInfo("Environment", "QA");
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
            String[] lines = consoleOutput.split("\\r?\\n");
            ExtentTest test = testThread.get();
            for (String line : lines) {
                test.info(line);
            }
        }
        byteArrayOutputStream.reset();
    }
}