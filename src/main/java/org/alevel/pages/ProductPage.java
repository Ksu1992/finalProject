package org.alevel.pages;



import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private By addToCartButton = By.id("add-to-cart-button");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    // Другие методы, связанные со страницей товара
}
