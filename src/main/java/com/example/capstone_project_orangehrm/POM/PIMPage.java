
package com.example.capstone_project_orangehrm.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PIMPage {
    private WebDriver driver;

    // Locators
    private By pimTab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span");
    private By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private By totalRecords = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
    private By searchEmpID = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input");

    // Constructor
    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    // Wait for element to be visible
    private WebElement waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    private WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Click PIM tab
    public void clickPimTab() {
        waitForElementToBeClickable(pimTab).click();
        System.out.println("PIM tab clicked successfully.");
    }

    // Search employee by clicking the Search button
    public void searchButtonClick() {
        waitForElementToBeClickable(searchButton).click();
        System.out.println("Search button clicked successfully.");
    }

    public void viewEmployeeDetails() {
        // Smooth scroll down
        // Smooth scroll down
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 10; i++) {  // Scroll 15 times (adjust this for the distance to scroll)
            js.executeScript("window.scrollBy(0, 40);");  // Scroll down by 60px each time
            try {
                Thread.sleep(150);  // Adjust the sleep time to control the scroll speed (150ms for smooth speed)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        WebElement detailsElement = waitForElementToBeVisible(totalRecords);
        String detailsText = detailsElement.getText();

        if (!detailsText.isEmpty()) {
            System.out.println("Employee details verified successfully.");
        } else {
            System.out.println("No employee details found.");
        }
    }

    // Verify total records displayed on the website
    public String getTotalRecords() {
        WebElement recordsElement = waitForElementToBeVisible(totalRecords);
        String recordsText = recordsElement.getText();
        System.out.println("Total records displayed: " + recordsText);
        return recordsText;
    }

    public void searchEmployeeById(String employeeId) {
        WebElement empIdField = waitForElementToBeVisible(searchEmpID);
        empIdField.clear();
        empIdField.sendKeys(employeeId);
        searchButtonClick();
        System.out.println("Searched employee by ID: " + employeeId);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollBy(0, 60);");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            String idXpath = "/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[2]/div";
            String firstNameXpath = "/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[3]/div";
            String lastNameXpath = "/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[4]/div";

            String id = driver.findElement(By.xpath(idXpath)).getText();
            String firstName = driver.findElement(By.xpath(firstNameXpath)).getText();
            String lastName = driver.findElement(By.xpath(lastNameXpath)).getText();

            System.out.println("Employee Details:");
            System.out.println("ID: " + id);
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
        } catch (Exception e) {
            System.out.println("Error fetching employee details: " + e.getMessage());
        }
    }



}
