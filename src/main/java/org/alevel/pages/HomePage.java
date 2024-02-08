package org.alevel.pages;
import org.alevel.base.BasePage;
import org.alevel.pages.components.Footer;
import org.alevel.pages.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

    // Локаторы
    private By logoLocator = By.xpath("//a[@class='logo']");
    private By navigationMenuLocator = By.xpath("//div[@class='header-menu-container']");
    private By homePageLocator = By.xpath("//div[@class='page-footer']");
    private By forUsLocator = By.xpath("//div[@class='footer-block footer-list footer-pages-nav']");
    private By restorans = By.cssSelector("li.level0.ui-menu-item:nth-of-type(1) > a.menu-item.level-top");
    private By contactsLocator = By.xpath("//div[@class='footer-block footer-list contacts-column']");
    private By searchBoxLocator = By.xpath("//div[@class='search-container']");
    private By popularProductsListLocator = By.xpath("//div[contains(@class, 'home-category-favorites-container')]");

    // Footer и Header
    private Footer footer;
    private Header header;

    public HomePage(WebDriver driver) {
        super(driver);
        footer = new Footer(driver);
        header = new Header(driver);
    }

    // Методы для проверки наличия элементов
    public boolean isLogoDisplayed() {
        return driver.findElement(logoLocator).isDisplayed();
    }

    public boolean isNavigationMenuDisplayed() {
        return driver.findElement(navigationMenuLocator).isDisplayed();
    }

    public boolean isHomePageDisplayed() {
        return driver.findElement(homePageLocator).isDisplayed();
    }

    public boolean isForUsDisplayed() {
        return driver.findElement(forUsLocator).isDisplayed();
    }

    public boolean isProductCatalogDisplayed() {
        return driver.findElement(restorans).isDisplayed();
    }

    public boolean isContactsDisplayed() {
        return driver.findElement(contactsLocator).isDisplayed();
    }

    public boolean isSearchBoxDisplayed() {
        return driver.findElement(searchBoxLocator).isDisplayed();
    }

    public boolean isPopularProductsListDisplayed() {
        return driver.findElement(popularProductsListLocator).isDisplayed();
    }

    // Геттеры для локаторов
    public By getLogoLocator() {
        return logoLocator;
    }

    public By getNavigationMenuLocator() {
        return navigationMenuLocator;
    }

    public By getHomePageLocator() {
        return homePageLocator;
    }

    public By getForUsLocator() {
        return forUsLocator;
    }

    public By getProductCatalogLocator() {
        return restorans;
    }

    public By getContactsLocator() {
        return contactsLocator;
    }

    public By getSearchBoxLocator() {
        return searchBoxLocator;
    }

    public By getPopularProductsListLocator() {
        return popularProductsListLocator;
    }

    // Делегирование вызовов методов Footer и Header


    public void clickOnHeaderMenu(String menuName) {
        header.clickOnCategory(menuName);
    }


    // Методы для взаимодействия с элементами через Actions
    public void clickOnElementUsingActions(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).click().perform();
    }

    public void clickOnNavigationMenu() {
        clickOnElementUsingActions(navigationMenuLocator);
    }

    public void clickOnHomePageLink() {
        clickOnElementUsingActions(homePageLocator);
    }

    public void clickOnForUsLink() {
        clickOnElementUsingActions(forUsLocator);
    }

    public void clickOnProductCatalog() {
        clickOnElementUsingActions(restorans);
    }

    public void clickOnContacts() {
        clickOnElementUsingActions(contactsLocator);
    }

    public void clickOnSearchBox() {
        clickOnElementUsingActions(searchBoxLocator);
    }

    public void clickOnPopularProductsList() {
        clickOnElementUsingActions(popularProductsListLocator);
    }
}

