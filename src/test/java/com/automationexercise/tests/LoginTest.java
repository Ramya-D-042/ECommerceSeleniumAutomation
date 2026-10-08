package com.automationexercise.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        // Open Chrome
        driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open Automation Exercise
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyValidLogin() {

        // Click Signup / Login
        driver.findElement(By.cssSelector("a[href='/login']")).click();

        // Enter email
        driver.findElement(By.cssSelector("input[data-qa='login-email']"))
                .sendKeys("bhavyad285@gmail.com");

        // Enter password
        driver.findElement(By.cssSelector("input[data-qa='login-password']"))
                .sendKeys("Ramya@123");

        // Click Login
        driver.findElement(By.cssSelector("button[data-qa='login-button']"))
                .click();

        // Verify that user is logged in
        boolean loggedIn = driver.getPageSource()
                .contains("Logged in as");

        Assert.assertTrue(loggedIn, "Login was not successful");
    }

    @AfterMethod
    public void tearDown() {

        // Close browser
        driver.quit();
    }
}