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

public class AddToCart {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");
    }

    @Test
    public void verifyAddToCart() {

        // Find first Add to Cart button
        WebElement addToCartButton = driver.findElement(
                By.xpath("(//a[contains(@class,'add-to-cart')])[1]")
        );

        // Scroll to the button
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        addToCartButton
                );

        // Click using JavaScript
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].click();",
                        addToCartButton
                );

        // Give the website time to display confirmation
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Verify Add to Cart confirmation
        boolean added =
                driver.getPageSource().contains("Added!");

        Assert.assertTrue(
                added,
                "Product was not added to cart"
        );

        System.out.println("Add to Cart test passed!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}