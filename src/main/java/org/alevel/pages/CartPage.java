package org.alevel.pages;

import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private By quantityField = By.xpath("//span[@class='counter qty']"); // Идентификатор поля количества товара
    private By removeItemButton = By.xpath("//a[@class='action delete icon-block']"); // Кнопка удаления товара из корзины
    private By checkoutButton = By.xpath("//button[@id='top-cart-btn-checkout']"); // Кнопка перехода к оформлению заказа
    private By emptyCartMessage = By.xpath("//div[@class='empty-cart-message']"); // Элемент, указывающий на пустую корзину

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void changeQuantity(String quantity) {
        WebElement quantityInput = driver.findElement(quantityField);
        quantityInput.clear();
        quantityInput.sendKeys(quantity);
        quantityInput.sendKeys(Keys.RETURN); // Имитация нажатия Enter для подтверждения изменения
    }

    public void removeItem() {
        driver.findElement(removeItemButton).click();
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    // Метод для проверки, что корзина пуста
    public boolean isCartEmpty() {
        // Проверка наличия элемента, указывающего на пустую корзину
        return driver.findElements(emptyCartMessage).size() > 0;
    }

    // Другие методы, связанные со страницей корзины
}
