package com.pragmatic.tests.javascript;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * This class demonstrates how to handle JavaScript popups (Alert, Confirm, Prompt) using Selenium WebDriver in Java.
 * It interacts with a sample webpage that contains buttons for triggering JavaScript alerts, confirmation, and prompt popups.
 * The test cases validate that the appropriate messages are displayed on the webpage after interacting with the popups.
 */
public class JavaScriptPopupsTest {

    WebDriver driver;

    /**
     * This method sets up the WebDriver and opens the webpage with JavaScript popups.
     * The browser is maximized for better visibility of the elements.
     */
    @BeforeMethod
    public void setUp() {
        // Set up ChromeDriver with options
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        // Open the URL with JavaScript popups demo page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/javascript-popups.html");

        // Maximize the browser window
        driver.manage().window().maximize();
    }

    /**
     * This test case validates handling a simple JavaScript alert popup.
     * It clicks the 'Show Alert' button, accepts the alert, and checks the message on the webpage.
     */
    @Test
    public void testAlertPopup() {
        // Click the "Show Alert" button to trigger the alert popup
        WebElement alertButton = driver.findElement(By.xpath("//button[text()='Show Alert']"));
        alertButton.click();

        // Switch to the alert and accept it
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();

        // Validate if the message is displayed after the alert is triggered
        WebElement outputMessage = driver.findElement(By.id("outputMessage"));
        Assert.assertEquals(outputMessage.getText(), "Alert displayed.");

        // Validate the alert text
        Assert.assertEquals(alertText, "This is a simple alert!");
    }

    /**
     * This test case validates handling a JavaScript confirmation popup.
     * It clicks the 'Show Confirmation' button, accepts the confirmation, and checks the message on the webpage.
     */
    @Test
    public void testConfirmPopup() {
        // Click the "Show Confirmation" button to trigger the confirm popup
        WebElement confirmButton = driver.findElement(By.xpath("//button[text()='Show Confirmation']"));
        confirmButton.click();

        // Switch to the confirm popup, get the message, and accept it (click 'OK')
        Alert confirmAlert = driver.switchTo().alert();
        String confirmText = confirmAlert.getText();
        confirmAlert.accept();

        // Validate if the message is displayed after the confirmation is triggered
        WebElement outputMessage = driver.findElement(By.id("outputMessage"));
        Assert.assertEquals(outputMessage.getText(), "Action confirmed.");

        // Validate the confirm popup text
        Assert.assertEquals(confirmText, "Do you confirm this action?");
    }

    /**
     * This test case validates handling a JavaScript prompt popup.
     * It clicks the 'Show Prompt' button, enters text in the prompt, accepts it, and checks the message on the webpage.
     */
    @Test
    public void testPromptPopup() {
        // Click the "Show Prompt" button to trigger the prompt popup
        WebElement promptButton = driver.findElement(By.xpath("//button[text()='Show Prompt']"));
        promptButton.click();

        // Switch to the prompt popup, send text, and accept it
        Alert promptAlert = driver.switchTo().alert();
        String promptText = promptAlert.getText();
        promptAlert.sendKeys("Test message");
        promptAlert.accept();

        // Validate if the message is displayed after the prompt is triggered
        WebElement outputMessage = driver.findElement(By.id("outputMessage"));
        Assert.assertTrue(outputMessage.getText().contains("You entered: Test message"));

        // Validate the prompt popup text
        Assert.assertEquals(promptText, "Please enter your message:");
    }

    /**
     * This method tears down the WebDriver instance after each test case.
     * It ensures that the browser is closed after the tests are completed.
     */
    @AfterMethod
    public void tearDown() {
        // Close the browser after test
        if (driver != null) {
            driver.quit();
        }
    }
}
