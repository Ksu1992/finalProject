package org.alevel.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class FeedbackFormTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.get("https://yaposhka.com.ua/");
    }

    @DataProvider(name = "feedbackFormData")
    public Object[][] feedbackFormData() {
        return new Object[][]{
                {"John Doe", "974566574", "Hello, this is my feedback message."}
                // Добавьте другие тестовые данные по необходимости
        };
    }

    @Test(dataProvider = "feedbackFormData")
    public void fillAndSubmitFeedbackForm(String name, String phone, String message) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement feedbackButton = driver.findElement(By.xpath("//div[@class='footer-block additional-column grid-extend']//span[@class='complain-button']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", feedbackButton);
        Thread.sleep(3000);
        feedbackButton.click();

        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='complain_name' and @name='name']")));
        firstNameInput.click();
        firstNameInput.sendKeys(name);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='complain_phone' and @name='telephone']")));
        phoneInput.click();
        phoneInput.sendKeys(phone);

        WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name='note' and @id='complain_note']")));
        commentInput.click();
        commentInput.sendKeys(message);

        // Assert for first name input
        String firstNameValue = firstNameInput.getAttribute("value");
        Assert.assertEquals(firstNameValue, name, "First name input value is incorrect");

        // Assert for phone input
        String phoneValue = phoneInput.getAttribute("value");
        String formattedPhoneNumber = formatPhoneNumber(phone);
        Assert.assertEquals(phoneValue, formattedPhoneNumber, "Phone input value is incorrect");

        // Assert for comment input
        String commentValue = commentInput.getAttribute("value");
        Assert.assertEquals(commentValue, message, "Comment input value is incorrect");
    }

    @AfterMethod
    public void tearDown() {
        // Закрываем браузер после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }
    public static String formatPhoneNumber(String digits) {
        // Check if the input has the correct length
        if (digits.length() != 9) {
            throw new IllegalArgumentException("Invalid phone number length");
        }

        // Format the phone number
        return "+38(0" + digits.substring(0, 2) + ")" + digits.substring(2, 5) + "-" + digits.substring(5, 7) + "-" + digits.substring(7);
    }
}
