package com.pragmatic.tests.dragdrop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class contains automated test scripts to verify drag-and-drop functionality
 * on the Drag and Drop Demo page. The tests are written using Selenium WebDriver
 * with TestNG for assertions.
 *
 * Features tested:
 * 1. Drag and Drop Verification:
 *    - Drags an element from the source area to the target drop area.
 *    - Verifies that the element is successfully dropped by checking the message.
 *
 * Pre-requisites:
 * - The demo page should be available at:
 *   https://pragmatictesters.github.io/selenium-webdriver-examples/drag-and-drop.html
 *
 * TestNG Annotations Used:
 * - @BeforeMethod: Initializes the WebDriver and opens the demo page.
 * - @AfterMethod: Closes the browser after the test execution.
 * - @Test: Represents the drag-and-drop test case.
 *
 * Tools and Libraries:
 * - Selenium WebDriver for browser automation.
 * - TestNG for assertions and test management.
 * - Actions class for performing drag-and-drop actions.
 *
 * Author: Pragmatic Testers
 * Date: 13 March 2025
 */
public class DragAndDropTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        // Navigate to the Drag and Drop demo page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/drag-and-drop.html");
    }

    @AfterMethod
    public void afterMethod() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test to verify drag and drop functionality.
     * Steps:
     * 1. Locate the draggable item and drop area.
     * 2. Perform the drag-and-drop operation using Actions class.
     * 3. Validate the success message after dropping.
     */
    @Test
    public void testDragAndDrop() {
        WebElement dragItem = driver.findElement(By.id("dragItem"));
        WebElement dropArea = driver.findElement(By.id("dropArea"));
        WebElement outputMessage = driver.findElement(By.id("outputMessage"));

        // Perform drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(dragItem, dropArea).perform();

        // Verify the success message
        String expectedMessage = "Item successfully dropped!";
        String actualMessage = outputMessage.getText();
        Assert.assertEquals(actualMessage, expectedMessage, "Drag and drop message mismatch!");
    }
}
