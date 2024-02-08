package org.alevel.tests;

import org.alevel.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\driver\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.get("https://yaposhka.com.ua");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getLogoLocator()));
        Assert.assertTrue(homePage.isLogoDisplayed(), "Логотип не отображается");

        // Проверка меню навигации
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getNavigationMenuLocator()));
        Assert.assertTrue(homePage.isNavigationMenuDisplayed(), "Меню навигации не отображается");

        // Проверка поисковой строки
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getSearchBoxLocator()));
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Поиск товаров отсутствует");

        // Проверка списка популярных продуктов
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getPopularProductsListLocator()));
        Assert.assertTrue(homePage.isPopularProductsListDisplayed(), "Список популярных товаров не отображается");

        // Проверка других элементов
        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getHomePageLocator()));
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Страница домашней страницы не отображается");

        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getForUsLocator()));
        Assert.assertTrue(homePage.isForUsDisplayed(), "Секция 'Для нас' не отображается");

        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getProductCatalogLocator()));
        Assert.assertTrue(homePage.isProductCatalogDisplayed(), "Каталог продуктов не отображается");

        wait.until(ExpectedConditions.visibilityOfElementLocated(homePage.getContactsLocator()));
        Assert.assertTrue(homePage.isContactsDisplayed(), "Контактная информация не отображается");
        }
    }
