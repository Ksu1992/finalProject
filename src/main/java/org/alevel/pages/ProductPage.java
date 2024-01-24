package org.alevel.pages;

import org.alevel.base.BasePage;
import org.alevel.pages.components.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private Header header;

    private By addToCartButton = By.xpath("//button[@id='product-addtocart-button']");
    private By confirmationMessage=By.xpath("//div[@class='minicart-header grid-extend']");

    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver); // Инициализация Header
    }

    public void navigateToProductPage(String categoryName, String productName) {
        header.clickOnCategory(categoryName); // Переход к категории

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By productLink = By.xpath("//a[contains(text(), '" + productName + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLink)).click();

        // уникальный элемент на странице товара, который подтверждает ее загрузку
        By productDetail = By.xpath("//div[@class='content-style-extend']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productDetail));

    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartBtn.click();

        By confirmationMessage = By.id("confirmation-message");
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)); // Ожидание подтверждения добавления в корзину
    }

    public boolean isProductInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-item-count")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

