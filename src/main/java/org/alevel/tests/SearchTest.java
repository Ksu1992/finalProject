package org.alevel.tests;
import org.alevel.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {
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

