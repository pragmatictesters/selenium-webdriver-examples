package com.pragmatic.tests.popup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModalPopupTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open the modal popup page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/model-popup.html");
    }

    @Test
    public void testModalPopupHandling() throws InterruptedException {
        // Verify page title
        Assert.assertEquals(driver.getTitle(), "Modal Popup Demo");

        // Find and click the "Open Modal" button
        WebElement openModalButton = driver.findElement(By.xpath("//button[text()='Open Modal']"));
        openModalButton.click();

        // Wait for the modal to appear
        WebElement modal = driver.findElement(By.id("modal"));
        Assert.assertTrue(modal.isDisplayed(), "Modal is not displayed!");

        // Verify the modal message
        WebElement modalMessage = driver.findElement(By.xpath("//div[@class='modal-content']//h3"));
        Assert.assertEquals(modalMessage.getText(), "This is a Modal Popup");

        // Verify the modal description
        WebElement modalDescription = driver.findElement(By.xpath("//div[@class='modal-content']//p"));
        Assert.assertEquals(modalDescription.getText(), "Click the button below to close the modal.");

        // Find and click the "Close" button inside the modal
        WebElement closeModalButton = driver.findElement(By.xpath("//button[text()='Close']"));
        closeModalButton.click();

        // Wait for the modal to disappear
        Thread.sleep(1000); // Use explicit wait instead of sleep for better practice

        // Verify that the modal is no longer displayed
        Assert.assertFalse(modal.isDisplayed(), "Modal is still displayed after closing!");

        // Verify the disappearing message after closing the modal
        WebElement message = driver.findElement(By.id("message"));
        Assert.assertTrue(message.isDisplayed(), "Disappearing message is not displayed!");
        Assert.assertEquals(message.getText(), "Modal has been closed!");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}

