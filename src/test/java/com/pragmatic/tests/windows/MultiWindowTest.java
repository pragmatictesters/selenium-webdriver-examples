package com.pragmatic.tests.windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class MultiWindowTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Open the multi-window page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/multiwindow.html");
    }

    @Test
    public void testMultiWindowHandling() {
        // Store the original window handle
        String originalWindow = driver.getWindowHandle();
        Assert.assertEquals(driver.getTitle(), "Multi-Window and Tab Handling");

        // Open LinkedIn in a new tab/window
        WebElement linkedInLink = driver.findElement(By.xpath("//a[text()='Open LinkedIn']"));
        linkedInLink.click();
        switchToNewWindow(originalWindow);
        Assert.assertTrue(driver.getTitle().contains("LinkedIn"));
        driver.close();
        driver.switchTo().window(originalWindow);

        // Open GitHub in a new tab/window
        WebElement gitHubLink = driver.findElement(By.xpath("//a[text()='Open GitHub']"));
        gitHubLink.click();
        switchToNewWindow(originalWindow);
        Assert.assertTrue(driver.getTitle().contains("GitHub"));
        driver.close();
        driver.switchTo().window(originalWindow);

        // Open Google in a new tab/window
        WebElement googleLink = driver.findElement(By.xpath("//a[text()='Open Google']"));
        googleLink.click();
        switchToNewWindow(originalWindow);
        Assert.assertTrue(driver.getTitle().contains("Google"));
        driver.close();
        driver.switchTo().window(originalWindow);

        // Verify we are back to the original window
        Assert.assertEquals(driver.getTitle(), "Multi-Window and Tab Handling");
    }

    /**
     * Switches to the new window that is not the original window.
     *
     * @param originalWindow The handle of the original window
     */
    private void switchToNewWindow(String originalWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> iterator = allWindows.iterator();
        while (iterator.hasNext()) {
            String windowHandle = iterator.next();
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}