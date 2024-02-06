package org.alevel.tests;


import org.alevel.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.By;

public class ClickTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        // Инициализация WebDriver и настройка необходимых параметров
        driver = new FirefoxDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        driver.get("https://yaposhka.com.ua/ua/");
    }

    @AfterMethod
    public void tearDown() {
        // Закрытие браузера после каждого теста
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





    // Добавьте другие элементы здесь


    @Test(dataProvider = "linksProvider")
    public void testElementClick(String elementName, By locator, String expectedUrlStartsWith, By uniqueElementLocator) {
        homePage = new HomePage(driver);
        homePage.clickOnElementUsingActions(locator);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(uniqueElementLocator));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.startsWith(expectedUrlStartsWith), "URL не начинается с ожидаемого: " + expectedUrlStartsWith + " после клика на " + elementName);
        Assert.assertTrue(driver.findElement(uniqueElementLocator).isDisplayed(), "Уникальный элемент не найден");
    }
}


































//    @DataProvider(name = "linksProvider")
//    public Object[][] createData() {
//        return new Object[][]{
////                { "Логотип", By.xpath("//a[@class='logo']"), "https://expected-url-for-logo.com/" },
////                { "Навигационное меню", By.xpath("//div[@class='header-menu-container']"), "https://expected-url-for-navigation-menu.com/" },
////                { "Ссылка Домой", By.xpath("//div[@class='page-footer']"), "https://expected-url-for-home-page.com/" },
////                { "Ссылка 'Для нас'", By.xpath("//div[@class='footer-block footer-list footer-pages-nav']"), "https://expected-url-for-for-us.com/" },
//                {"Ресторани", By.cssSelector("li.level0.ui-menu-item:nth-of-type(1) > a.menu-item.level-top"), "https://yaposhka.com.ua/ua/"},
////                { "Контакты", By.xpath("//div[@class='footer-block footer-list contacts-column']"), "https://expected-url-for-contacts.com/" },
////                { "Поисковая строка", By.xpath("//div[@class='search-container']"), "https://expected-url-for-search-box.com/" },
////                { "Список популярных продуктов", By.xpath("//div[contains(@class, 'home-category-favorites-container')]"), "https://expected-url-for-popular-products-list.com/" }
//        };
//    }
//
//
//    @Test(dataProvider = "linksProvider")
//    public void testElementClick(String elementName, By locator, String expectedUrl, By uniqueElementLocator) {
//        homePage = new HomePage(driver);
//        homePage.clickOnElementUsingActions(locator);
//
//        // Ожидание перехода на новую страницу и появления уникального элемента
//        new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.visibilityOfElementLocated(uniqueElementLocator));
//
//        String actualUrl = driver.getCurrentUrl();
//        Assert.assertEquals(actualUrl, expectedUrl, "URL не соответствует ожидаемому после клика на " + elementName);
//
//        // Проверка наличия уникального элемента на странице
//        Assert.assertTrue(driver.findElement(uniqueElementLocator).isDisplayed(), "Уникальный элемент не отображается на странице");
//    }
//
//
//    @DataProvider(name = "linksProvider")
//    public Object[][] createData(){}
//        return new Object[][]{
//                // Пример: добавление локатора уникального элемента для каждого URL
//                {"Ресторани", By.cssSelector("li.level0.ui-menu-item:nth-of-type(1) > a.menu-item.level-top"), "https://yaposhka.com.ua/ua/", By.id("unique-element-id")},
//                // Добавьте другие элементы здесь
//        }
//
//









