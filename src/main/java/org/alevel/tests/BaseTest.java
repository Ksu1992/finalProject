package org.alevel.tests;
import org.alevel.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

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
    @DataProvider(name = "linksProvider")
    public Object[][] createData() {
        return new Object[][]{
                {"Ресторани", By.cssSelector("li.level0.ui-menu-item:nth-of-type(1) > a.menu-item.level-top"), "https://yaposhka.com.ua/", By.xpath("//div[@class='tab-toggle active']")},
                {"Доставка та оплата", By.cssSelector("li.level0.ui-menu-item:nth-of-type(2) > a.menu-item.level-top"), "https://yaposhka.com.ua/", By.xpath("//div[@class='supplements-table']")},
                {"Бонуси", By.cssSelector("li.level0.ui-menu-item:nth-of-type(3) > a.menu-item.level-top"), "https://yaposhka.com.ua/", By.xpath("//span[@class='base']")}
        };
    }
    @DataProvider(name = "feedbackFormData")
    public Object[][] feedbackFormData() {
        return new Object[][]{
                {"Маша Іванова", "974566574", "Hello, this is my feedback message."}
                // Добавьте другие тестовые данные по необходимости
        };
    }
    @DataProvider(name = "searchQueries")
    public Object[][] createSearchQueries() {
        return new Object[][]{
                {"Рис"},
                {"Вино"},
                {"Суп"},
                {"Салат"},
                {"Борщ"}
        };
    }
    @DataProvider(name = "productData")
    public Object[][] createProductData() {
        return new Object[][]{
                {"Супи", "Рамен з морепродуктами"},
                {"Салати", "Зелений салат з горіховим соусом"},
                {"Від шефа", "Боул з куркою Кацу"}
        };
    }
}


