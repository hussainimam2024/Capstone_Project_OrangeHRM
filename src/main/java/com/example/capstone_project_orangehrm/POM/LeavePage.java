package com.example.capstone_project_orangehrm.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LeavePage {
    private WebDriver driver;
    private By leaveTab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a/span");
    private By leaveButton = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]");
    private By commentArea = By.cssSelector(".oxd-textarea.oxd-textarea--active.oxd-textarea--resize-vertical");
    private By applyButton = By.cssSelector("button[type='submit']");
    private By MyleaveButton  = By.cssSelector("header[class='oxd-topbar'] li:nth-child(2) a:nth-child(1)");
    private By leaverecods  = By.cssSelector("span[class='oxd-text oxd-text--span']");
    public LeavePage(WebDriver driver) {
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
    public void clickLeaveTab() {
        waitForElementToBeClickable(leaveTab).click();
        System.out.println("Leave tab clicked successfully.");
    }
    public void clickLeaveButton() {
        waitForElementToBeClickable(leaveButton).click();
        System.out.println("Leave button clicked successfully.");
    }
    public void enterComment(String reason) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(commentArea));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0, 30);");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement commentElement = driver.findElement(commentArea);
        commentElement.clear();
        commentElement.sendKeys(reason);
        System.out.println("Reason for leave entered: " + reason);
    }
    public void clickApplyButton() {
        waitForElementToBeClickable(applyButton).click();
        System.out.println("Apply button clicked successfully.");
    }
    public void MyLeave() {
        waitForElementToBeClickable(MyleaveButton).click();
        System.out.println("Leave button clicked successfully.");
    }
    public String getTotalLeave() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 4; i++) {
            js.executeScript("window.scrollBy(0, 60);");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement recordsElement = waitForElementToBeVisible(leaverecods);
        String recordsText = recordsElement.getText();
        System.out.println("Total Leave displayed: " + recordsText);

        return recordsText;
    }
    private By LeaveAssignMenu = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[7]/a");
    private By commentbox = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[4]/div/div/div/div[2]/textarea");
    private By AssignButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[5]/button");
    private By RequiredMsg = By.cssSelector(".oxd-text.oxd-text--p.orangehrm-form-hint");
    public void clickLeaveAssignMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement leaveAssignMenuElement = wait.until(ExpectedConditions.elementToBeClickable(LeaveAssignMenu));
        System.out.println("Clicking on Leave Assign Menu...");
        leaveAssignMenuElement.click();
        System.out.println("Leave Assign Menu clicked.");
    }
    public void fillCommentAndAssignLeave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement commentBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(commentbox));
        System.out.println("Filling the comment box...");
        commentBoxElement.sendKeys("Leave request for personal reasons");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 3; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement assignButtonElement = wait.until(ExpectedConditions.elementToBeClickable(AssignButton));
        assignButtonElement.click();
        System.out.println("Assign Button clicked.");
        WebElement requiredMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(RequiredMsg));
        String actualMessage = requiredMsgElement.getText();
        String expectedMessage = "No Record found";
        System.out.println("Leave not assigned due to Employee : No Record found");
        Assert.assertEquals(actualMessage, expectedMessage, "Leave not assigned due to Employee: No Record found");
        System.out.println("Message verified: " + actualMessage);
    }
    private By entitlementMenu= By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[3]/span");
    private By Addentitlement = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[3]/ul/li[1]/a");
    private By verifyheading = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/p");
    private By savebutton  =By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[4]/button[2]");
    public void clickEnititlement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement EntitleMenuElement = wait.until(ExpectedConditions.elementToBeClickable(entitlementMenu));
        EntitleMenuElement.click();
        System.out.println("Entitlement Menu clicked.");
    }
    public void clickAddEntitlement() {
        waitForElementToBeClickable(Addentitlement).click();
    }
    public String verifEntitlementHeading() {
        return waitForElementToBeVisible(verifyheading).getText();
    }
    public void submitEntitle() {
        waitForElementToBeClickable(savebutton ).click();
    }






}
