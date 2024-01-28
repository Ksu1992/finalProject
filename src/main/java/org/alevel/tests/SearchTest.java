package org.alevel.tests;
import org.alevel.base.BasePage;
import org.alevel.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("web-driver.firefox.driver", "path/to/firefox driver");
        driver = new FirefoxDriver();
        driver.get("https://yaposhka.com.ua/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "searchQueries")
    public Object[][] createSearchQueries() {
        return new Object[][]{
                {"Соус"},
                {"Бургер"},
                {"Вино"},
                {"Суп"},
                {"Шаурма"},
                {"Десерти"}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testSearch(String query) {
        // Найти иконку поиска и кликнуть по ней
        WebElement searchIcon = driver.findElement(By.xpath("//form[@id='search_mini_form']"));
        searchIcon.click();

        // Ожидание, чтобы поле поиска стало видимым
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='search']")));

        // Ввести запрос и нажать Enter
        searchBox.sendKeys(query + Keys.RETURN);

        // Ожидание загрузки результатов поиска

        wait.until(ExpectedConditions.urlContains("q=")); // Ожидать изменения URL, связанного с запросом


        // Проверка, что результаты поиска отображаются
        Assert.assertTrue(SearchPage.areSearchResultsDisplayed(driver, By.xpath("//span[@class='base']")), "Результат поиска не отображается");

        // Проверка, что результаты поиска соответствуют запросу
        Assert.assertTrue(SearchPage.areSearchResultsMatchingQuery(driver, By.xpath("//span[@class='base']"), query), "Результаты поиска не соответствуют запросу");
    }
}
