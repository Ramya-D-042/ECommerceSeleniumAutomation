package com.automationexercise.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        // Open Chrome browser
        driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open Automation Exercise website
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyHomePage() {

        // Get the title of the webpage
        String title = driver.getTitle();

        // Print the title
        System.out.println("Page Title: " + title);

        // Verify that the title contains Automation Exercise
        Assert.assertTrue(title.contains("Automation Exercise"));
    }

    @AfterMethod
    public void tearDown() {

        // Close the browser
        driver.quit();
    }
}