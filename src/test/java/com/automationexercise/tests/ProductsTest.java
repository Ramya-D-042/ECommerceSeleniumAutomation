package com.automationexercise.tests;

import java.time.Duration;

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

public class ProductsTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyProductsPage() {

        // Wait until Products link is available
        WebElement productsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[href='/products']")
                )
        );

        // Click Products
        productsLink.click();

        // Wait until Products URL is loaded
        wait.until(
                ExpectedConditions.urlContains("/products")
        );

        String currentUrl = driver.getCurrentUrl();

        System.out.println("Current URL: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("/products"),
                "Products page was not opened"
        );

        System.out.println("Products page test passed!");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}