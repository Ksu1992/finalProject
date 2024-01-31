package org.alevel.tests;

import org.alevel.base.BasePage;
import org.alevel.pages.CartPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

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
                {"Піца", "Піца з морепродуктами"},
                {"Салати", "Зелений салат з горіховим соусом"},
                {"Бургери", "З куркою та беконовим джемом"}
        };
    }

    @Test(dataProvider = "productData")
    public void testAddToCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Проверка, что товар добавлен в корзину.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement qtyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='counter qty']")));
        String itemCount = qtyElement.getText();
        System.out.println(itemCount);
        Assert.assertNotEquals(itemCount, "0", "Cart is empty, but should have items.");
    }


    @Test(dataProvider = "productData")
    public void testChangeItemQuantityInCart(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Создание экземпляра Actions
        Actions actions = new Actions(driver);

        // Наведение на элемент, который находится в header
        WebElement headerElement = driver.findElement(By.xpath("//div[@class='minicart-header grid-extend']")); // Замените на правильный id или другой локатор
        actions.moveToElement(headerElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));
        Thread.sleep(1000); // Пауза в 1 секунду (можно настраивать)
        orderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']")));

        // Находим поле ввода количества и изменяем значение
        WebElement quantityField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']"))); // Подтвердите правильность id
        quantityField.click(); // Активация поля ввода

        quantityField.clear(); // Очистка поля

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value='5'", quantityField);
        String actualQuantity = quantityField.getAttribute("value");

        System.out.print(actualQuantity);
        // Дожидаемся обновления значения в поле ввода
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']"), "value", "5"));

        // Получение и проверка актуального значения
        Thread.sleep(1000);

        Assert.assertEquals(actualQuantity, "5", "Quantity did not update correctly");
    }


    @Test(dataProvider = "productData")
    public void testRemoveItemFromCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        Actions actions = new Actions(driver);

        // Навигация на страницу товара и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        WebElement headerElement = driver.findElement(By.xpath("//div[@class='minicart-header grid-extend']")); // Замените на правильный id или другой локатор
        actions.moveToElement(headerElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));
        //Thread.sleep(1000); // Пауза в 1 секунду (можно настраивать)
        orderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='action delete icon-block']")));

        // Ожидание появления иконки корзины
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-actions']")));

        // Удаление товара из корзины
        cartPage.removeItem();

        // Ожидание исчезновения товара из корзины
        By uniqueItemLocator = By.xpath("//div[@class='product-actions']");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean isItemRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(uniqueItemLocator));

        // Проверка успешного удаления товара из корзины
        Assert.assertTrue(isItemRemoved, "Item is still present in the cart after removal");
    }
}