package org.alevel.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        options.addArguments("--headless");
        return new FirefoxDriver(options);
    }
}
