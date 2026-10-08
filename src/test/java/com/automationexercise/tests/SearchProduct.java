package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProduct {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");
    }

    @Test
    public void verifyProductSearch() {

        // Find the search box
        WebElement searchBox = driver.findElement(
                By.xpath("//input[@placeholder='Search Product']")
        );

        // Enter product name
        searchBox.sendKeys("Blue Top");

        // Find search button
        WebElement searchButton = driver.findElement(
                By.xpath("//button[@id='submit_search']")
        );

        // Click search button using JavaScript
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", searchButton);

        // Verify searched product
        boolean productDisplayed = driver.getPageSource()
                .contains("Blue Top");

        Assert.assertTrue(
                productDisplayed,
                "Blue Top product was not displayed"
        );

        System.out.println("Search Product test passed!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}