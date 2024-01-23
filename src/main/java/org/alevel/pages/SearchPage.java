package org.alevel.pages;
import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    // Локатор для поисковой строки
    private By searchBoxLocator = By.name("Search");

    // Локатор для результатов поиска
    private By searchResultsLocator = By.xpath("//span[@class='base']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public static boolean areSearchResultsDisplayed(WebDriver driver, By locator) {
        // Проверяем, отображаются ли элементы на странице
        try {
            return driver.findElements(locator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean areSearchResultsMatchingQuery(WebDriver driver, By locator, String query) {
        // Проверяем, соответствуют ли результаты поиска заданному запросу
        try {
            List<WebElement> searchResults = driver.findElements(locator);
            for (WebElement result : searchResults) {
                if (result.getText().toLowerCase().contains(query.toLowerCase())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void searchFor(String query) {
        // Найти элемент поисковой строки и ввести запрос
        WebElement searchBox = driver.findElement(searchBoxLocator);
        searchBox.clear();
        searchBox.sendKeys(query);
        searchBox.submit(); // Или использовать sendKeys(Keys.RETURN);

        // Добавьте необходимое ожидание для загрузки результатов поиска
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.visibilityOfElementLocated(searchResultsLocator)
        );
    }

    public List<WebElement> getSearchResults() {
        // Получить результаты поиска
        return driver.findElements(searchResultsLocator);
    }
}
