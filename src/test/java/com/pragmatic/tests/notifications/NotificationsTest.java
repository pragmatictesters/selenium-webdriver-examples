package com.pragmatic.tests.notifications;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver and navigate to the notifications page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/notifications.html");  // Update the URL accordingly
    }

    @Test
    public void testSuccessAlert() {
        WebElement successButton = driver.findElement(By.xpath("//button[text()='Show Success Alert']"));
        successButton.click();

        // Wait for the success alert to appear
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert success')]")));
        Assert.assertTrue(successAlert.isDisplayed(), "Success alert should be visible");

        // Verify the alert message
        Assert.assertEquals(successAlert.getText(), "Operation completed successfully!");

        // Verify the background color
        String successColor = successAlert.getCssValue("background-color");
        Assert.assertEquals(successColor, "rgba(76, 175, 80, 1)", "Success alert background color mismatch");

        // Wait for the alert to disappear
        wait.until(ExpectedConditions.invisibilityOf(successAlert));
    }

    @Test
    public void testWarningAlert() {
        WebElement warningButton = driver.findElement(By.xpath("//button[text()='Show Warning Alert']"));
        warningButton.click();

        // Wait for the warning alert to appear
        WebElement warningAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert warning')]")));
        Assert.assertTrue(warningAlert.isDisplayed(), "Warning alert should be visible");

        // Verify the alert message
        Assert.assertEquals(warningAlert.getText(), "Warning: Check your input.");

        // Verify the background color
        String warningColor = warningAlert.getCssValue("background-color");
        Assert.assertEquals(warningColor, "rgba(255, 152, 0, 1)", "Warning alert background color mismatch");

        // Wait for the alert to disappear
        wait.until(ExpectedConditions.invisibilityOf(warningAlert));
    }

    @Test
    public void testErrorAlert() {
        WebElement errorButton = driver.findElement(By.xpath("//button[text()='Show Error Alert']"));
        errorButton.click();

        // Wait for the error alert to appear
        WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'alert error')]")));
        Assert.assertTrue(errorAlert.isDisplayed(), "Error alert should be visible");

        // Verify the alert message
        Assert.assertEquals(errorAlert.getText(), "Error: Something went wrong.");

        // Verify the background color
        String errorColor = errorAlert.getCssValue("background-color");
        Assert.assertEquals(errorColor, "rgba(244, 67, 54, 1)", "Error alert background color mismatch");

        // Wait for the alert to disappear
        wait.until(ExpectedConditions.invisibilityOf(errorAlert));
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}