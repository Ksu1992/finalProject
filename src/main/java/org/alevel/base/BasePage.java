package org.alevel.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected WebDriver driver;

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



