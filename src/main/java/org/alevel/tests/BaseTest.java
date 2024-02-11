package org.alevel.tests;

import org.alevel.base.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest  {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createFirefoxDriver();
        driver.get("https://yaposhka.com.ua/ua/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


