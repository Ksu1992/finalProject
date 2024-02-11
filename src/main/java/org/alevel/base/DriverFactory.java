package org.alevel.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    public DriverFactory(WebDriver driver) {
    }

    public static WebDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//        options.addArguments("-headless");
//        options.addArguments("--width=1920", "--height=1080");
        return new FirefoxDriver(options);
    }
    public static WebDriver createChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
