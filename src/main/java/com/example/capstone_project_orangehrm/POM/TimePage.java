package com.example.capstone_project_orangehrm.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TimePage {
    private WebDriver driver;
    private By Timetab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[4]/a/span");
    private By Timesheetmenu = By.cssSelector("li[class='oxd-topbar-body-nav-tab --parent --visited'] span[class='oxd-topbar-body-nav-tab-item']");
    private By Mytimehsheetoption = By.cssSelector("li[class='--active oxd-topbar-body-nav-tab --parent --visited'] li:nth-child(1) a:nth-child(1)");
    private By Emp_timesheetoption = By.cssSelector("header[class='oxd-topbar'] li:nth-child(2) a:nth-child(1)");
    private By viewsheet = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button");
    private By verifysheetheading = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    private By Createsheet = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button");
    private By status = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/form/div[3]/div[1]/p");
    private By submit = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/form/div[3]/div[2]/button[2]");
    private By Approvebutton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/form/div[2]/button[2]");
    private By Attendencemenu = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span");
    private By Myrecords = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a");
    private By heading = By.cssSelector(".oxd-text.oxd-text--h5.oxd-table-filter-title");
    private By projectInfomenu = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[4]/span");
    private By customeroption = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/li[1]/a");
    private By verifyrecords = By.cssSelector("span[class='oxd-text oxd-text--span']");

    public TimePage(WebDriver driver) {
        this.driver = driver;
    }
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void clickTimeTab() {
        waitForElementToBeClickable(Timetab).click();
    }
    public void clickTimesheetMenu() {
        waitForElementToBeClickable(Timesheetmenu).click();
    }
    public void clickMyTimesheetOption() {
        waitForElementToBeClickable(Mytimehsheetoption).click();
    }
    public void EmployeeTimesheet() {
        waitForElementToBeClickable(Emp_timesheetoption).click();
    }
    public String getStatus() {
        return waitForElementToBeVisible(status).getText();
    }
    public void viewSheet() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementToBeClickable(viewsheet).click();
    }
    public String verifySheetHeading() {
        return waitForElementToBeVisible(verifysheetheading).getText();
    }
    public void createSheet() {
        waitForElementToBeClickable(Createsheet).click();
    }
    public void submitSheet() {
        waitForElementToBeClickable(submit).click();
    }
    public void approveTimesheet() {
        waitForElementToBeClickable(Approvebutton).click();
    }
    public void clickAttendanceMenu() {
        waitForElementToBeClickable(Attendencemenu).click();
    }
    public void clickMyRecords() {
        waitForElementToBeClickable(Myrecords).click();
    }
    public String getHeadingText() {
        return waitForElementToBeVisible(heading).getText();
    }
    public void clickProjectInfoMenu() {
        waitForElementToBeClickable(projectInfomenu).click();
    }
    public void clickCustomerOption() {
        waitForElementToBeClickable(customeroption).click();
    }
    public String verifyRecords() {
        return waitForElementToBeVisible(verifyrecords).getText();
    }
}
