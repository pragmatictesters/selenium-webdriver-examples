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

public class BasicAutocompleteTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver and navigate to the autocomplete page
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://pragmatictesters.github.io/selenium-webdriver-examples/basic-autocomplete.html");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAutocomplete() {
        // Find the input element and type 'un'
        WebElement autocompleteInput = driver.findElement(By.id("autocomplete"));
        autocompleteInput.sendKeys("un");

        // Wait for the suggestions to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("autocomplete-list")));

        // Verify the suggestions are displayed
        WebElement suggestion = driver.findElement(By.xpath("//div[text()='United States']"));
        Assert.assertNotNull(suggestion);

        // Click on the suggestion
        suggestion.click();

        // Verify the value in the input field
        String selectedCountry = autocompleteInput.getAttribute("value");
        Assert.assertEquals(selectedCountry, "United States", "The selected country should be United States");
    }


}