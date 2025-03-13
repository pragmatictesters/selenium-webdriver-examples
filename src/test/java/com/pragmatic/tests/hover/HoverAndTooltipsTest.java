package com.pragmatic.tests.hover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class contains automated test scripts for verifying hover effects and tooltips
 * on the Tooltips and Hover Effects demo page. The tests are written using Selenium WebDriver
 * with TestNG for assertions.
 *
 * Features tested:
 * 1. Hover and Tooltip Verification:
 *    - Verifies that tooltips are displayed when hovering over elements.
 *    - Validates the text of the tooltips to ensure correctness.
 *
 * 2. Hover Color Change:
 *    - Checks the background color of elements before and after hover.
 *    - Ensures that the color change occurs when hovered.
 *
 * 3. Additional Info Tooltip:
 *    - Verifies the presence and content of an additional info tooltip that appears on hover.
 *
 * Pre-requisites:
 * - The demo page should be available at:
 *   https://pragmatictesters.github.io/selenium-webdriver-examples/tooltips-hover.html
 *
 * TestNG Annotations Used:
 * - @BeforeMethod: Initializes the WebDriver and opens the demo page.
 * - @AfterMethod: Closes the browser after the test execution.
 * - @Test: Represents individual test cases.
 *
 * Tools and Libraries:
 * - Selenium WebDriver for browser automation.
 * - TestNG for assertions and test management.
 * - Actions class for performing hover actions.
 *
 * Author: Pragmatic Testers
 * Date: 13 March 2025
 */

public class HoverAndTooltipsTest {

    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        // Navigate to the Selenium web form page
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/tooltips-hover.html");
    }

    @AfterMethod
    public void afterMethod() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHoverTooltipMessage() {
        // Locate the hover element with a tooltip
        WebElement hoverElement = driver.findElement(By.xpath("//p[contains(text(),'Hover over me!')]"));
        WebElement tooltip = driver.findElement(By.xpath("//p[contains(text(),'Hover over me!')]/span"));

        // Hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        // Assert that the tooltip is displayed and has the correct text
        Assert.assertTrue(tooltip.isDisplayed(), "Tooltip should be visible after hover.");
        Assert.assertEquals(tooltip.getText(), "Tooltip message here!", "Tooltip text mismatch.");
    }

    @Test
    public void testHoverColorChange() {
        // Locate the hover effect element
        WebElement hoverElement = driver.findElement(By.xpath("//p[contains(text(),'changes color')]"));

        // Get the initial background color
        String initialColor = hoverElement.getCssValue("background-color");

        System.out.println("initialColor = " + initialColor);
        // Hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        // Get the background color after hover
        String hoverColor = hoverElement.getCssValue("background-color");

        System.out.println("hoverColor = " + hoverColor);

        // Assert that the background color changes on hover
        Assert.assertNotEquals(hoverColor, initialColor, "Background color should change on hover.");
    }

    @Test
    public void testAdditionalInfoTooltip() {
        // Locate the additional info tooltip element
        WebElement hoverElement = driver.findElement(By.xpath("//div[contains(text(),'additional info')]"));
        //WebElement tooltip = driver.findElement(By.xpath("//div[contains(text(),'additional info')]/span"));
        WebElement tooltip = hoverElement.findElement(By.tagName("span"));

        // Hover over the element
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        // Assert that the tooltip is displayed and has the correct text
        Assert.assertTrue(tooltip.isDisplayed(), "Additional info tooltip should be visible after hover.");
        Assert.assertEquals(tooltip.getText(), "Additional details shown on hover.", "Tooltip text mismatch.");
    }
}