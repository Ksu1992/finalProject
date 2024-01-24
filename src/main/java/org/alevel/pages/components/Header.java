package org.alevel.pages.components;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    private WebDriver driver;

    @FindBy(xpath = "//a[@class='header-link']")
    private WebElement headerLink;

    // Добавим @FindBy для других элементов меню, если они имеют уникальные селекторы
    // Например:
    @FindBy(xpath = "//span[@class='advancedmenu-link level0 level-top']//span[@class='text-block grid-extend']/span[@class='name' and (contains(text(), 'Sushi') or contains(text(), 'Суши') or contains(text(), 'Суші'))]")
    private WebElement sushiMenuLink;

    @FindBy(xpath = "//a[@class='advancedmenu-link level0 level-top']//span[@class='text-block grid-extend']/span[@class='name' and (contains(text(), 'Pizza') or contains(text(), 'Пицца') or contains(text(), 'Піца'))]")
    private WebElement pizzaMenuLink;

    @FindBy(xpath = "//a[@class='advancedmenu-link level0 level-top']//span[@class='text-block grid-extend']/span[@class='name' and (contains(text(), 'Supy') or contains(text(), 'Супы') or contains(text(), 'Супи'))]")
    private WebElement supyMenuLink;

    @FindBy(xpath = "//a[@class='advancedmenu-link level0 level-top']//span[@class='text-block grid-extend']/span[@class='name' and (contains(text(), 'Бургеры') or contains(text(), 'Бургери') or contains(text(), 'Burgery'))]")
    private WebElement burgeryMenuLink;
    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickHeaderLink() {
        headerLink.click();
    }

    public void clickSushiMenu() {
        sushiMenuLink.click();
    }

    public void clickPizzaMenu() {
        pizzaMenuLink.click();
    }

    public void clickSupyMenu() {supyMenuLink.click();
    }

    public void clickBurgeryMenu() {
        burgeryMenuLink.click();
    }

    // ... добавьте методы для других элементов по аналогии

    public void clickOnCategory(String categoryName) {
        WebElement categoryLink = driver.findElement(By.xpath("//a[contains(text(), '" + categoryName + "')]"));
        categoryLink.click();
    }
}


