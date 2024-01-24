package org.alevel.tests;

import org.alevel.base.BasePage;
import org.alevel.pages.CartPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CartTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver(); // Инициализация драйвера
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
                {"Піца", "Піца"},
                {"Супи", "Суп"},
                {"Бургери", "Бургер"}
        };
    }

    @Test(dataProvider = "productData")
    public void testAddToCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Проверка, что товар добавлен в корзину. Например, проверка наличия элемента или текста.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-item-count"))); // Пример: ожидание элемента, который показывает количество товаров в корзине

        // Получение количества товаров в корзине и проверка
        String itemCount = driver.findElement(By.id("cart-item-count")).getText();
        System.out.println(itemCount);
        Assert.assertNotEquals(itemCount, "0", "Cart is empty, but should have items.");
    }
}


//    @Test(dataProvider = "productData")
//    public void testChangeItemQuantityInCart(String categoryName, String productName) {
//        ProductPage productPage = new ProductPage(driver);
//        CartPage cartPage = new CartPage(driver);
//
//        productPage.navigateToProductPage(categoryName, productName);
//        productPage.addToCart();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("icon-cart"))); // Идентификатор иконки корзины
//
//        cartPage.changeQuantity("2"); // Пример изменения количества
//
//        wait.until(ExpectedConditions.attributeToBe(By.id("quantity-field"), "value", "2"));
//
//        String actualQuantity = driver.findElement(By.id("quantity-field")).getAttribute("value");
//        Assert.assertEquals(actualQuantity, "2", "Quantity did not update correctly");
//    }
//
//    @Test(dataProvider = "productData")
//    public void testRemoveItemFromCart(String categoryName, String productName) {
//        ProductPage productPage = new ProductPage(driver);
//        CartPage cartPage = new CartPage(driver);
//
//        productPage.navigateToProductPage(categoryName, productName);
//        productPage.addToCart();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-icon-block']/div[@class='icon-block']"))); // Ожидание появления иконки корзины
//
//        cartPage.removeItem();
//
//        By uniqueItemLocator = By.id("unique-item-id"); // Используйте актуальный локатор для вашего товара
//
//        boolean isItemRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(uniqueItemLocator));
//        Assert.assertTrue(isItemRemoved, "Item is still present in the cart after removal");
//    }
//
//
//}

