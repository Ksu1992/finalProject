package org.alevel.base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;
    protected By searchBoxLocator = By.xpath("//input[@id='search']");
    protected By searchResultsLocator = By.xpath("//span[@class='base']");
    protected By searchIconLocator = By.xpath("//form[@id='search_mini_form']");
    protected By addToCartButtonLocator = By.xpath("//button[@id='product-addtocart-button']");
    protected By orderButtonLocator = By.xpath("//button[@id='top-cart-btn-checkout']");
    protected By hoursLocator = By.xpath("(//div[@class='title' and contains(text(), 'Харків вул. Ярослава Мудрого, 33/35')])");
    protected By miniCartHeaderLocator = By.xpath("//div[@class='minicart-header grid-extend']");
    protected By firstNameInputLocator = By.xpath("//input[@name='firstname']");
    protected By phoneInputLocator = By.xpath("//input[@name='telephone'][1]");
    protected By elementLocator = By.xpath("//td[@id='label_method_selfget_selfget' and contains(text(), 'Самовивіз')]");
    protected By paymentMethodsInputLocator = By.xpath("//input[@id='portmone']");
    protected By placeOrderLocator = By.xpath("//div[@class='place-order-primary']");
    protected By removeItemButtonLocator = By.xpath("//a[@class='action delete icon-block']");
    protected By itemCountSpanLocator = By.xpath("//span[@class='counter qty']");
    protected By itemQuantityFieldLocator = By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']");
    protected By productActionsLocator = By.xpath("//div[@class='product-actions']");
    protected By logoLocator = By.xpath("//a[@class='logo']");
    protected By navigationMenuLocator = By.xpath("//div[@class='header-menu-container']");
    protected By homePageLocator = By.xpath("//div[@class='page-footer']");
    protected By forUsLocator = By.xpath("//div[@class='footer-block footer-list footer-pages-nav']");
    protected By restorationsLocator = By.cssSelector("li.level0.ui-menu-item:nth-of-type(1) > a.menu-item.level-top");
    protected By contactsLocator = By.xpath("//div[@class='footer-block footer-list contacts-column']");
    protected By searchBoxContainerLocator = By.xpath("//div[@class='search-container']");
    protected By popularProductsListLocator = By.xpath("//div[contains(@class, 'home-category-favorites-container')]");
    protected By feedbackButtonLocator = By.xpath("//div[@class='footer-block additional-column grid-extend']//span[@class='complain-button']");
    protected By firstNameFeedbackInputLocator = By.xpath("//input[@id='complain_name' and @name='name']");
    protected By phoneFeedbackInputLocator = By.xpath("//input[@id='complain_phone' and @name='telephone']");
    protected By commentFeedbackInputLocator = By.xpath("//textarea[@name='note' and @id='complain_note']");
    protected By carouselBlockSelectorLocator = By.xpath("//div[@class='page home-page']");
    protected By carouselDotsSelectorLocator = By.xpath("//div[@class='owl-dots']");
    protected By activeDotSelectorLocator = By.xpath("//button[@class='owl-dot active']");
    protected By inactiveDotSelectorLocator = By.xpath("//button[@class='owl-dot' and not(@class='owl-dot active')]");
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected BasePage() {
        this.driver = createDriver();
    }

    private WebDriver createDriver() {
        // Вызываем метод для создания драйвера Firefox
        return DriverFactory.createFirefoxDriver();
    }
}



