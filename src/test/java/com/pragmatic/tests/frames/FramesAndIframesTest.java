package com.pragmatic.tests.frames;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FramesAndIframesTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open the Frames and IFrames Demo page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/frames.html");
    }

    @Test
    public void testIFrameInteraction() {
        // Switch to the iframe using a switch statement for multiple options
        switchToIFrame("element");

        // Verify the iframe content
        WebElement iframeContent = driver.findElement(By.tagName("p"));
        Assert.assertEquals(iframeContent.getText(), "This is content inside an IFrame. Interact with me!");

        // Click the button inside the iframe
        WebElement iframeButton = driver.findElement(By.tagName("button"));
        iframeButton.click();

        // Handle the alert from iframe
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "IFrame Button Clicked");
        alert.accept();

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }

    @Test
    public void testFrameInteraction() {
        // Switch to the frame using a switch statement for multiple options
        switchToFrame("index");

        // Verify the frame content
        WebElement frameContent = driver.findElement(By.tagName("p"));
        Assert.assertEquals(frameContent.getText(), "This is content inside a Frame. Try interacting here too!");

        // Click the button inside the frame
        WebElement frameButton = driver.findElement(By.tagName("button"));
        frameButton.click();

        // Handle the alert from frame
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "Frame Button Clicked");
        alert.accept();

        // Switch back to the main content
        driver.switchTo().defaultContent();
    }

    private void switchToIFrame(String switchOption) {
        // Switch statement to demonstrate various ways to switch to an iframe
        switch (switchOption) {  // Change to "id", "index", "element" as needed

            case "name":
                driver.switchTo().frame("iframeDemo");  // by name
                break;

            case "id":
                driver.switchTo().frame("iframeDemo");  // by id
                break;

            case "index":
                driver.switchTo().frame(0);  // by index
                break;

            case "element":
                WebElement iframeElement = driver.findElement(By.id("iframeDemo"));
                driver.switchTo().frame(iframeElement);  // by WebElement
                break;

            default:
                throw new IllegalArgumentException("Invalid switch method");
        }
    }

    private void switchToFrame(String switchOption) {
        // Switch statement to demonstrate various ways to switch to a frame
        switch (switchOption) {  // Change to "id", "index", "element" as needed

            case "name":
                driver.switchTo().frame("frameDemo");  // by name
                break;

            case "id":
                driver.switchTo().frame("frameDemo");  // by id
                break;

            case "index":
                driver.switchTo().frame(1);  // by index
                break;

            case "element":
                WebElement frameElement = driver.findElement(By.id("frameDemo"));
                driver.switchTo().frame(frameElement);  // by WebElement
                break;

            default:
                throw new IllegalArgumentException("Invalid switch method");
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}