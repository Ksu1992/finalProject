package org.alevel.pages.components;

import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopupPage extends BasePage {

    public PopupPage(WebDriver driver) {
        this.driver = driver;
    }

    // ... другие методы и переменные класса ...

    // Метод для закрытия всплывающего окна, если оно отображается
    public void closePopupIfPresent() {
        By popupLocator = By.xpath("//button[@class='action-primary action-accept']");

        // Проверяем, существует ли всплывающее окно на странице
        if (isElementPresent(popupLocator)) {
            // Закрываем всплывающее окно
            driver.findElement(popupLocator).click(); // Пример клика по элементу, можно использовать другие методы закрытия
        }
    }

    // Метод для проверки существования элемента на странице
    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
