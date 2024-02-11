package org.alevel.pages;

import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    private By quantityField = By.xpath("//span[@class='counter qty']");
    private By removeItemButton = By.xpath("//a[@class='action delete icon-block']");
    private By checkoutButton = By.xpath("//button[@id='top-cart-btn-checkout']");
    private By emptyCartMessage = By.xpath("//div[@class='empty-cart-message']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void changeQuantity(String quantity) {
        WebElement quantityInput = driver.findElement(quantityField);
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        quantityInput.sendKeys(Keys.RETURN);
    }

    public void removeItem() {
        driver.findElement(removeItemButton).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(emptyCartMessage).size() > 0;
    }

    public WebElement waitForVisibilityOfElementLocated(By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }
}
