package com.pragmatic.tests.autocomplete;

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

public class SlowLoadingAutocompleteTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver and navigate to the slow loading autocomplete page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // Increased wait time for slow loading
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/slow-loading-autocomplete.html");
    }

    @Test
    public void testSlowLoadingAutocomplete() {
        // Find the input element and type 'un' (this will trigger slow autocomplete)
        WebElement autocompleteInput = driver.findElement(By.id("autocomplete-slow"));
        autocompleteInput.sendKeys("un");

        // Wait for the suggestions to appear (the delay in suggestions makes this critical)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete-list-slow")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='United States']")));

        // Verify the suggestions are displayed
        WebElement suggestion = driver.findElement(By.xpath("//div[text()='United States']"));
        Assert.assertNotNull(suggestion, "Suggestion for 'United States' should be visible");

        // Click on the suggestion
        suggestion.click();

        // Verify the value in the input field
        String selectedCountry = autocompleteInput.getAttribute("value");
        Assert.assertEquals(selectedCountry, "United States", "The selected country should be United States");
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
}