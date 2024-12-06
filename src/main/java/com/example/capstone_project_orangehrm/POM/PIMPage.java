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
    private By pimTab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[2]/a/span");
    private By InfoTab = By.xpath("/html/body/div/div[1]/div[1]/aside/nav/div[2]/ul/li[6]/a/span");
    private By infoname = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[1]/div[1]/div[1]/h6");
    private By AddButton = By.xpath("(//a[normalize-space()='Add Employee'])[1]");
    private By Empfirstname = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[1]/div[2]/input");
    private By Emplastname = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div[2]/input");
    private By EmpId = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div[2]/div[1]/div[2]/div/div/div[2]/input");
    private By savebutton = By.xpath("(//button[normalize-space()='Save'])[1]");
    private By searchButton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private By totalRecords = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span");
    private By searchEmpID = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input");
    private By Reporttab  = By.cssSelector("header[class='oxd-topbar'] li:nth-child(4) a:nth-child(1)");
    private By reportbutton = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div[1]/div/div[3]/div/button[3]");
    private By verifyreportheading = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div[1]/h6");
    private By firstNameedit = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[1]/div[2]/input");
    private By lastnameedit  =By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[1]/div/div/div/div[2]/div[3]/div[2]/input");
    private By empid = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[2]/div[1]/div[1]/div/div[2]/input");
    private By saveafteredit = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[4]/button");
    public PIMPage(WebDriver driver) {
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
    public void clickPimTab() {
        waitForElementToBeClickable(pimTab).click();
        System.out.println("PIM tab clicked successfully.");
    }
    public void clickInfoTab(){
        waitForElementToBeClickable(InfoTab).click();
        System.out.println("Info tab clicked successfully");
    }
    public void clickAddButton() {
        waitForElementToBeClickable(AddButton).click();
        System.out.println("Add Employee button clicked successfully.");
    }
    public void enterFirstName(String firstName) {
        WebElement firstNameField = waitForElementToBeVisible(Empfirstname);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        System.out.println("Entered first name: " + firstName);

    }
    public void enterLastName(String lastName) {
        WebElement lastNameField = waitForElementToBeVisible(Emplastname);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        System.out.println("Entered last name: " + lastName);
    }
    public void enterEmployeeId(String employeeId) {
        WebElement employeeIdField = waitForElementToBeVisible(EmpId);
        employeeIdField.clear();
        employeeIdField.sendKeys(employeeId);
        System.out.println("Entered employee ID: " + employeeId);
    }
    public void clickSaveButton() {
        waitForElementToBeClickable(savebutton).click();
        System.out.println("Clicked Save button.");
        waitForElementToBeClickable(saveafteredit).click();
        System.out.println("Clicked Save button after updating personal details.");
    }
    public void searchButtonClick() {
        waitForElementToBeClickable(searchButton).click();
        System.out.println("Search button clicked successfully.");
    }
    public void viewEmployeeDetails() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 10; i++) {
            js.executeScript("window.scrollBy(0, 40);");
            try {
                Thread.sleep(150);
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
    public void ReportGenerate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement web = wait.until(ExpectedConditions.elementToBeClickable(Reporttab));
        web.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 8; i++) {
            js.executeScript("window.scrollBy(0, 60);");
            try {
                Thread.sleep(150);  // Optional, to mimic natural scrolling
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        WebElement report = wait.until(ExpectedConditions.visibilityOfElementLocated(reportbutton));  // Wait for visibility
        js.executeScript("arguments[0].scrollIntoView(true);", report);  // Scroll to the report button if needed
        wait.until(ExpectedConditions.elementToBeClickable(report));  // Ensure it's clickable
        report.click();
    }
    public String Verifyheading() {
        WebElement recordsElement = waitForElementToBeVisible(verifyreportheading);
        String recordsText = recordsElement.getText();
        return recordsText;
    }
    public String VerifyInfoEmp_name() {
        System.out.println("Attempting to locate the employee name using locator: " + infoname);
        try {
            WebElement recordsElement = waitForElementToBeVisible(infoname);
            String recordsText = recordsElement.getText();
            System.out.println("Employee name fetched: " + recordsText); // Print the fetched text
            return recordsText;
        } catch (Exception e) {
            System.err.println("Error fetching employee name: " + e.getMessage());
            throw e;
        }
    }
    public void updateFirstName(String firstName) {
        try {
            WebElement firstNameField = waitForElementToBeVisible(firstNameedit);
            firstNameField.clear();
            firstNameField.sendKeys(firstName);
            System.out.println("Updated first name: " + firstName);
        } catch (Exception e) {
            System.out.println("Failed to update first name: " + e.getMessage());
        }
    }
    public void updateLastName(String lastName) {
        try {
            WebElement lastNameField = waitForElementToBeVisible(lastnameedit);
            lastNameField.clear();
            lastNameField.sendKeys(lastName);
            System.out.println("Updated last name: " + lastName);
        } catch (Exception e) {
            System.out.println("Failed to update last name: " + e.getMessage());
        }
    }
    public void updateEmployeeId(String employeeId) {
        try {
            WebElement employeeIdField = waitForElementToBeVisible(empid);
            employeeIdField.clear();
            employeeIdField.sendKeys(employeeId);
            System.out.println("Updated employee ID: " + employeeId);
        } catch (Exception e) {
            System.out.println("Failed to update employee ID: " + e.getMessage());
        }
    }
    public void clickSaveButtonAfterEdit() {
        try {
            WebElement saveButton = waitForElementToBeClickable(saveafteredit);
            saveButton.click();
            System.out.println("Clicked Save button after editing personal details.");
        } catch (Exception e) {
            System.out.println("Failed to click Save button: " + e.getMessage());
        }
    }
    public void scrollToPersonalDetailsEdit() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", waitForElementToBeVisible(firstNameedit));
            System.out.println("Scrolled to personal details edit section.");
        } catch (Exception e) {
            System.out.println("Error while scrolling: " + e.getMessage());
        }
    }
    private By configuremenu = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]/span");
    private By optionalOption  = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[1]/a");
    private By verifyTextOption = By.cssSelector(".oxd-text.oxd-text--p.orangehrm-main-title");
    private By toggleButton1  =By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div/div/label/span");
    private By toggleButton2 = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/label/span");
    private By configureSave = By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/form/div[3]/button");
    private By customOption  = By.xpath("/html/body/div/div[1]/div[1]/header/div[2]/nav/ul/li[1]/ul/li[2]/a");
    private By verifytextcustom = By.cssSelector(".oxd-text.oxd-text--h6.orangehrm-main-title");
    public void clickConfigureMenu() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement configureMenuElement = wait.until(ExpectedConditions.elementToBeClickable(configuremenu));
        configureMenuElement.click();
        System.out.println("Configure Menu clicked.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void clickOptionalOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement optionalOptionElement = wait.until(ExpectedConditions.elementToBeClickable(optionalOption));
        optionalOptionElement.click();
        System.out.println("Optional Option clicked.");
    }
    public String getVerifyTextOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement verifyTextOptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(verifyTextOption));
        String text = verifyTextOptionElement.getText();
        System.out.println("Retrieved text from Optional Option page: " + text);
        return text;
    }
    public void toggleButton1() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toggleButton1Element = wait.until(ExpectedConditions.elementToBeClickable(toggleButton1));
        toggleButton1Element.click();
        System.out.println("Toggle Button 1 clicked.");
    }
    public void toggleButton2() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toggleButton2Element = wait.until(ExpectedConditions.elementToBeClickable(toggleButton2));
        toggleButton2Element.click();
        System.out.println("Toggle Button 2 clicked.");
    }
    public void clickConfigureSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement configureSaveElement = wait.until(ExpectedConditions.elementToBeClickable(configureSave));
        configureSaveElement.click();
        System.out.println("Save button clicked in Configure Menu.");
    }
    public void clickCustomOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement customOptionElement = wait.until(ExpectedConditions.elementToBeClickable(customOption));
        customOptionElement.click();
        System.out.println("Custom Option clicked.");
    }
    public String getVerifyTextCustom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement verifyTextCustomElement = wait.until(ExpectedConditions.visibilityOfElementLocated(verifytextcustom));
        String text = verifyTextCustomElement.getText();
        System.out.println("Retrieved text from Custom Option page: " + text);
        return text;
    }
}
