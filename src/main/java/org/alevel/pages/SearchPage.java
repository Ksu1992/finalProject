package org.alevel.pages;

import org.alevel.base.BasePage;
import org.alevel.base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage {

    // Локаторы для поисковой строки и результатов поиска
    private By searchBoxLocator = By.xpath("//input[@id='search']");
    private By searchResultsLocator = By.xpath("//span[@class='base']");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchFor(String query) {
        // Найти иконку поиска и кликнуть по ней
        WebElement searchIcon = driver.findElement(By.xpath("//form[@id='search_mini_form']"));
        searchIcon.click();

        // Ожидание видимости поля поиска
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBoxLocator));

        // Ввод запроса и выполнение поиска
        searchBox.sendKeys(query);
        searchBox.submit();

        // Ожидание загрузки результатов поиска
        wait.until(ExpectedConditions.urlContains("q="));
    }

    public boolean areSearchResultsDisplayed() {
        // Ожидание видимости результатов поиска
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultsLocator));

        // Проверка, отображаются ли результаты поиска
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        return !searchResults.isEmpty();
    }

    public boolean areSearchResultsMatchingQuery(String query) {
        // Проверка, соответствуют ли результаты поиска запросу
        List<WebElement> searchResults = driver.findElements(searchResultsLocator);
        for (WebElement result : searchResults) {
            if (!result.getText().toLowerCase().contains(query.toLowerCase())) {
                return false;
            }
        }
        return true;
    }
}

