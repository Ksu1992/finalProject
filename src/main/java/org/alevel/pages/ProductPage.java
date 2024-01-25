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
    private By confirmationMessage = By.xpath("//div[@class='minicart-header grid-extend']");

    public ProductPage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver); // Инициализация Header
    }

    public void navigateToProductPage(String categoryName, String productName) {
        header.clickOnCategory(categoryName); // Переход к категории
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By productLink = By.xpath("//a[contains(text(), '" + productName + "')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(productLink)).click();
        System.out.println(categoryName);

//        switch (categoryName) {
//            case "Піца":
//                // ожидание для страницы пиццы
//                wait.until(ExpectedConditions.urlContains("https://yaposhka.com.ua/ua/pica-z-moreproduktami/"));
//                break;
//            case "Бургери":
//                // ожидание для страницы бургеров
//                wait.until(ExpectedConditions.urlContains("https://yaposhka.com.ua/ua/smiliva-kurochka/"));
//                break;
//            default:
//                throw new IllegalStateException("Category not recognized: " + categoryName);
//        }
    }


    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartBtn.click();
    }
}


