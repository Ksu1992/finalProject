package org.alevel.tests;

import org.alevel.base.DriverFactory;
import org.alevel.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createFirefoxDriver();
        driver.get("https://yaposhka.com.ua/");
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHomePageElements() {
        // Проверка логотипа
        Assert.assertTrue(homePage.isLogoDisplayed(), "Логотип не отображается");

        // Проверка меню навигации
        Assert.assertTrue(homePage.isNavigationMenuDisplayed(), "Меню навигации не отображается");

        // Проверка поисковой строки
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Поиск товаров отсутствует");

        // Проверка списка популярных продуктов
        Assert.assertTrue(homePage.isPopularProductsListDisplayed(), "Список популярных товаров не отображается");

        // Проверка других элементов
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Страница домашней страницы не отображается");
        Assert.assertTrue(homePage.isForUsDisplayed(), "Секция 'Для нас' не отображается");
        Assert.assertTrue(homePage.isProductCatalogDisplayed(), "Каталог продуктов не отображается");
        Assert.assertTrue(homePage.isContactsDisplayed(), "Контактная информация не отображается");
    }
}
