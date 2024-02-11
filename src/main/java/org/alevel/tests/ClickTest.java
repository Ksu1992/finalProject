package org.alevel.tests;
import org.alevel.pages.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClickTest extends BaseTest {
    @Test(dataProvider = "linksProvider")
    public void testElementClick(String elementName, By locator, String expectedUrlStartsWith, By uniqueElementLocator) {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnElementUsingActions(locator);

        homePage.waitForElementVisibility(uniqueElementLocator);

        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.startsWith(expectedUrlStartsWith), "URL не начинается с ожидаемого: " + expectedUrlStartsWith + " после клика на " + elementName);
        Assert.assertTrue(driver.findElement(uniqueElementLocator).isDisplayed(), "Уникальный элемент не найден");
    }
}