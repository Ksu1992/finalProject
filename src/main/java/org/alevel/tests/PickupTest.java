package org.alevel.tests;

import org.alevel.base.DriverFactory;
import org.alevel.pages.CartPage;
import org.alevel.pages.PickupPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class PickupTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.createFirefoxDriver();
        driver.get("https://yaposhka.com.ua/ua/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "productData")
    public Object[][] createProductData() {
        return new Object[][]{
                {"Супи", "Рамен з морепродуктами"},
                {"Салати", "Зелений салат з горіховим соусом"},
                {"Від шефа", "Боул з куркою Кацу"}
        };
    }

    @Test(dataProvider = "productData")
    public void testPlaceOrder(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        PickupPage pickupPage = new PickupPage(driver);

        // Навигация на страницу продукта и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Создание экземпляра Actions
        Actions actions = new Actions(driver);
    }
}

        // Наведение на элемент, который находится в header

