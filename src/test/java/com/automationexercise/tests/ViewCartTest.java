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

public class ViewCartTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        // Open Products page
        driver.get("https://automationexercise.com/products");
    }

    @Test
    public void verifyViewCart() {

        // Find the first Add to Cart button
        WebElement addToCartButton = driver.findElement(
                By.xpath("(//a[contains(@class,'add-to-cart')])[1]")
        );

        // Scroll to the Add to Cart button
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                addToCartButton
        );

        // Click Add to Cart using JavaScript
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                addToCartButton
        );

        // Wait for Added to Cart popup
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Find View Cart inside the popup
        WebElement viewCartButton = driver.findElement(
                By.xpath(
                    "//div[@id='cartModal']//a[contains(@href,'/view_cart')]"
                )
        );

        // Click View Cart
        viewCartButton.click();

        // Get current URL
        String currentUrl = driver.getCurrentUrl();

        System.out.println("Cart URL: " + currentUrl);

        // Verify Cart page opened
        Assert.assertTrue(
                currentUrl.contains("view_cart"),
                "Cart page was not opened"
        );

        // Verify product is present in cart
        boolean productInCart =
                driver.findElements(
                        By.xpath("//td[contains(@class,'cart_description')]")
                ).size() > 0;

        Assert.assertTrue(
                productInCart,
                "Product was not displayed in cart"
        );

        System.out.println("View Cart test passed!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}