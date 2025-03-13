package com.pragmatic.tests.dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Selenium WebDriver TestNG example for demonstrating various dropdown types.
 * Demonstrates single select, multi select, grouped select, datalist, and date list dropdowns.
 * Uses Select class for dropdown interactions and keyboard actions for datalist selection.
 * Author: Pragmatic Testers
 */
public class DropdownsTest {

    private WebDriver driver;
    private static final String URL = "https://pragmatictesters.github.io/selenium-webdriver-examples/dropdowns.html";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSingleSelectDropdown() {
        WebElement singleSelect = driver.findElement(By.id("singleSelect"));
        Select select = new Select(singleSelect);
        select.selectByIndex(1); // Select "Apple"
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Apple");

        select.selectByValue("banana");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Banana");

        select.selectByVisibleText("Cherry");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Cherry");
    }

    @Test
    public void testMultiSelectDropdown() {
        WebElement multiSelect = driver.findElement(By.id("multiSelect"));
        Select select = new Select(multiSelect);
        select.selectByVisibleText("Red");
        select.selectByVisibleText("Blue");
        select.selectByVisibleText("Green");

        Assert.assertTrue(select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals("Red")));
        Assert.assertTrue(select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals("Blue")));
        Assert.assertTrue(select.getAllSelectedOptions().stream().anyMatch(e -> e.getText().equals("Green")));
    }

    @Test
    public void testGroupedSelectDropdown() {
        WebElement groupedSelect = driver.findElement(By.id("groupedSelect"));
        Select select = new Select(groupedSelect);
        select.selectByVisibleText("BMW");
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "BMW");
    }

    @Test
    public void testDatalistDropdown() throws InterruptedException {
        WebElement countryInput = driver.findElement(By.id("countryInput"));
        countryInput.click();  // Ensure focus on the input field
        countryInput.sendKeys("United States");
        String selectedCountry = countryInput.getAttribute("value");
        Assert.assertEquals(selectedCountry, "United States");
    }



    @Test
    public void testDateListDropdown() {
        WebElement dateInput = driver.findElement(By.id("dateInput"));
        dateInput.sendKeys("2025-03-17");
        Assert.assertEquals(dateInput.getAttribute("value"), "2025-03-17");
    }
}
