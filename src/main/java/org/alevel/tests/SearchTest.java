package org.alevel.tests;

import org.alevel.base.DriverFactory;
import org.alevel.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createFirefoxDriver();
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
                {"Рис"},
                {"Вино"},
                {"Суп"},
                {"Салат"},
                {"Борщ"}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testSearch(String query) {
        SearchPage searchPage = new SearchPage(driver);

        // Ввод запроса и выполнение поиска
        searchPage.searchFor(query);

        // Проверка, что результаты поиска отображаются
        Assert.assertTrue(searchPage.areSearchResultsDisplayed(), "Результат поиска не отображается");

        // Проверка, что результаты поиска соответствуют запросу
        Assert.assertTrue(searchPage.areSearchResultsMatchingQuery(query), "Результаты поиска не соответствуют запросу");
    }
}

