package com.pragmatic.tests.shadowdom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShadowDomTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open the Shadow DOM demo page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/shadow-dom.html");
    }

    @Test
    public void testShadowDomInteraction() {
        // Verify page title
        Assert.assertEquals(driver.getTitle(), "Shadow DOM Demo for Selenium WebDriver");

        // Access the shadow host element
        WebElement shadowHost = driver.findElement(By.id("shadowHost"));

        // Get the shadow root using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        SearchContext shadowRoot = (SearchContext) js.executeScript("return arguments[0].shadowRoot;", shadowHost);

        // Locate the message inside the shadow DOM
        WebElement shadowMessage = shadowRoot.findElement(By.cssSelector(".message"));
        Assert.assertEquals(shadowMessage.getText(), "Hello from the Shadow DOM!");

        // Locate the button inside the shadow DOM and click it
        WebElement shadowButton = shadowRoot.findElement(By.cssSelector("button"));
        shadowButton.click();

        // Verify the output message after clicking the button
        WebElement outputMessage = driver.findElement(By.id("outputMessage"));
        Assert.assertEquals(outputMessage.getText(), "Button inside Shadow DOM clicked!");
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
