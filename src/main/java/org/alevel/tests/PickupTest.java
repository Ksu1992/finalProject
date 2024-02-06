package org.alevel.tests;

import org.alevel.pages.CartPage;

import org.alevel.pages.PickupPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    public void testPlaceOrder(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        PickupPage checkoutPage = new PickupPage(driver);

        // Навигация на страницу продукта и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Создание экземпляра Actions
        Actions actions = new Actions(driver);

        // Наведение на элемент, который находится в header
        WebElement headerElement = driver.findElement(By.xpath("//div[@class='minicart-header grid-extend']"));
        actions.moveToElement(headerElement).perform();

        // Клик на кнопку оформления заказа
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));
        orderButton.click();


        // Ожидание видимости элемента на странице оформления заказа
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));


       // Заполнение полей на странице оформления заказа

        checkoutPage.fillShippingInformation("Ксения", "973944987", "", "", "cash",   "cash");


    }
}