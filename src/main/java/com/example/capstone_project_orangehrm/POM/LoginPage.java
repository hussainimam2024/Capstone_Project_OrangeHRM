package com.example.capstone_project_orangehrm.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    // Locators for login page elements
    private By usernameField = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
    private By passwordField = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
    private By loginButton = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
    private By forgotPasswordLink = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[4]/p");

    // Locators for Forgot Password page elements
    private By usernameAfterForgotField = By.xpath("/html/body/div/div[1]/div[1]/div/form/div[1]/div/div[2]/input");
    private By resetButton = By.xpath("/html/body/div/div[1]/div[1]/div/form/div[2]/button[2]");
    private By resetPasswordVerifyText = By.xpath("/html/body/div/div[1]/div[1]/div/h6");

    // Locators for error messages
    private By passwordErrorMessage = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/span");
    private By invalidErrorMessage = By.xpath("/html/body/div/div[1]/div/div[1]/div/div[2]/div[2]/div[1]/div[1]/div[1]/p");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
<<<<<<< HEAD
=======

    // Utility method to wait for an element to be visible
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void enterUsername(String username) {
        WebElement usernameElement = waitForElementToBeVisible(usernameField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        waitFor(Duration.ofMillis(500)); // Use shorter, non-blocking wait
<<<<<<< HEAD
        System.out.println("Username entered successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public void enterPassword(String password) {
        WebElement passwordElement = waitForElementToBeVisible(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
        waitFor(Duration.ofMillis(500)); // Use shorter, non-blocking wait
<<<<<<< HEAD
        System.out.println("Password entered successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public void clickLogin() {
        WebElement loginButtonElement = waitForElementToBeClickable(loginButton);
        loginButtonElement.click();
        waitFor(Duration.ofMillis(500));
<<<<<<< HEAD
        System.out.println("Login button clicked successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public void clickForgotPassword() {
        WebElement forgotPasswordElement = waitForElementToBeClickable(forgotPasswordLink);
        forgotPasswordElement.click();
        waitFor(Duration.ofMillis(500));
<<<<<<< HEAD
        System.out.println("Forgot password link clicked successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public void enterUsernameAfterForgot(String username) {
        WebElement usernameAfterForgotElement = waitForElementToBeVisible(usernameAfterForgotField);
        usernameAfterForgotElement.clear();
        usernameAfterForgotElement.sendKeys(username);
        waitFor(Duration.ofMillis(500));
<<<<<<< HEAD
        System.out.println("Username entered after forgot password successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public void clickResetButton() {
        WebElement resetButtonElement = waitForElementToBeClickable(resetButton);
        resetButtonElement.click();
        waitFor(Duration.ofMillis(500));
<<<<<<< HEAD
        System.out.println("Reset button clicked successfully.");
=======
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public String getResetPasswordVerifyText() {
        WebElement verifyTextElement = waitForElementToBeVisible(resetPasswordVerifyText);
        waitFor(Duration.ofMillis(500));
<<<<<<< HEAD
        String text = verifyTextElement.getText();
        System.out.println("Reset password verification text fetched successfully.");
        return text;
=======
        return verifyTextElement.getText();
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public String getErrorMessage() {
        WebElement errorElement = waitForElementToBeVisible(passwordErrorMessage);
<<<<<<< HEAD
        String errorMessage = errorElement.getText();
        System.out.println("Password error message fetched successfully.");
        return errorMessage;
=======
        return errorElement.getText();
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    public String getErrorMessageInvalidLogin() {
        WebElement errorElement = waitForElementToBeVisible(invalidErrorMessage);
<<<<<<< HEAD
        String errorMessage = errorElement.getText();
        System.out.println("Invalid login error message fetched successfully.");
        return errorMessage;
=======
        return errorElement.getText();
>>>>>>> a30d47b917ee089a67c55e7cba98888e404405ed
    }

    // Utility method to add a short wait (non-blocking)
    private void waitFor(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            e.printStackTrace();
        }
    }
}

