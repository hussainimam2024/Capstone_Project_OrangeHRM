package com.example.capstone_project_orangehrm.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AdminPage {
    private WebDriver driver;
    private By adminTab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a/span\n");  // Adjust this according to actual admin tab locator
    private By addUserButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button");
    private By usernameField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private By passwordField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private By confirmPasswordField = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private By saveButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
    private By editUserButton = By.xpath("(//i[@class='oxd-icon bi-pencil-fill'])[1]");
    private By editusername = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private By savebutton_Edit = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/button[2]");
    private By deleteUserButton = By.xpath("(//button[@type='button'])[9]");
    private By confirmDeleteButton = By.xpath("/html/body/div/div[3]/div/div/div/div[3]/button[2]");
    private By Jobmenu = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/span");
    private By JobTitle = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[2]/ul/li[1]/a");
    private By NationalitiesTab = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[4]/ul/div[2]/li/a\n");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void clickAdminTab() {
        waitForElementToBeClickable(adminTab).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Admin tab clicked successfully.");
    }
    public void clickAddUser() {
        WebElement addUserElement = driver.findElement(addUserButton);
        addUserElement.click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Add user button clicked successfully.");
    }
    public void fillInUserDetails(String username, String password, String confirmPassword) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(confirmPassword);
        System.out.println("User details filled successfully.");
    }
    public void clickSave() {
        driver.findElement(saveButton).click();
        waitForCompletion_Save(); // Introduce a delay
        System.out.println("User details saved successfully.");
    }
    private void waitForCompletion_Save() {
        try {
            Thread.sleep(10000); // Wait for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickEditUser() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollBy(0, 45);");
            try {
                Thread.sleep(120);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement editButton = driver.findElement(editUserButton);
        editButton.click();
        System.out.println("Edit user button clicked successfully.");
    }
    public void editUserfields(String newUsername) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameElement = wait.until(ExpectedConditions.elementToBeClickable(editusername));
        usernameElement.clear();
        usernameElement.sendKeys(newUsername);
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(savebutton_Edit));
        saveButton.click();
        waitForCompletion_Edit();
        System.out.println("Username edited and changes saved successfully.");
    }
    private void waitForCompletion_Edit() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickDeleteUser() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 45);");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteUserButton));
        deleteButton.click();
        waitForPopupToBeVisible();
        System.out.println("Delete user button clicked successfully.");
    }
    private void waitForPopupToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDeleteButton));
        System.out.println("Delete confirmation popup displayed.");
    }
    public void clickConfirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmDeleteBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteBtn.click();
        System.out.println("Delete action confirmed successfully.");
    }

    public void Jobmenubutton(){
        WebElement job = driver.findElement(Jobmenu);
        job.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Job button button clicked successfully.");

    }
    public void JobTitleOptions(){
        WebElement title = driver.findElement(JobTitle);
        title.click();
        System.out.println("Job Title button clicked");
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
