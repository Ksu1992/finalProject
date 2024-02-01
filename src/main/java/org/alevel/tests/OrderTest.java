package org.alevel.tests;

import org.alevel.pages.CartPage;
import org.alevel.pages.CheckoutPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;

public class OrderTest {

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
                //{"Піца", "Піца з морепродуктами"},
                {"Салати", "Зелений салат з горіховим соусом"},
                //{"Бургери", "З куркою та беконовим джемом"}
        };
    }


    @Test(dataProvider = "productData")
    public void testPlaceOrder(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

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


        checkoutPage.fillShippingInformation("Ксения", "973944987", "", "Харків,Натальї Ужвій,64", "3", "116", "7", "345", "cash", "");
        System.out.println("wwf");
        //checkoutPage.assertOrderFields();
        System.out.println("wwf2");
// Клик на кнопку "Продолжить"
//        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("recall")));
//        continueButton.click();

// Пример ожидания видимости и заполнения города (переопределите под свои элементы и данные)
        // WebElement cityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        //cityElement.click();
        // cityElement.sendKeys("Your City");

// Пример ожидания и выбора метода оплаты (переопределите под свои элементы и данные)
//        WebElement paymentMethodElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio' and @name='payment[method]' and @value='checkmo']")));
//        paymentMethodElement.click();
//        paymentMethodElement.sendKeys("Your Payment Method");

// Проверка, что поля заказа заполнены после заполнения информации о доставке
    }

    // Метод для проверки заполненности полей заказа
//    private void assertOrderFields() {
//        // Проверка, что поле "ФИО" заполнено
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        WebElement fullNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
//        Assert.assertFalse(fullNameField.getText().isEmpty(), "Поле 'ФИО' не заполнено");
//
//        // Проверка, что поле "Телефон" заполнено
//        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='telephone'][1]")));
//        Assert.assertFalse(phoneField.getText().isEmpty(), "Поле 'Телефон' не заполнено");
//
//        // Проверка, что поле "Адрес" заполнено
//        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='courier-address-autocomplete-input' and @name='custom_attributes[courier_address]' and @aria-required='true' and @aria-invalid='false']")));
//        Assert.assertFalse(addressField.getText().isEmpty(), "Поле 'Адрес' не заполнено");
//
//        // Проверка, что поле "Подъезд" заполнено
//        WebElement entranceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'courier_entrance')]")));
//        Assert.assertFalse(entranceField.getText().isEmpty(), "Поле 'Подъезд' не заполнено");
//
//        // Проверка, что поле "Этаж" заполнено
//        WebElement floorField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'courier_floor')]")));
//        Assert.assertFalse(floorField.getText().isEmpty(), "Поле 'Этаж' не заполнено");
//
//        // Проверка, что поле "Квартира" заполнено
//        WebElement apartmentField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'courier_apartment')]")));
//        Assert.assertFalse(apartmentField.getText().isEmpty(), "Поле 'Квартира' не заполнено");
//
//        // Проверка, что поле "Домофон" заполнено
//        WebElement intercomField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@name, 'courier_intercom_code')]")));
//        Assert.assertFalse(intercomField.getText().isEmpty(), "Поле 'Домофон' не заполнено");
//
//        // Проверка, что поле "Метод оплаты" заполнено
//        WebElement paymentMethodField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio' and starts-with(@name, 'payment[method]') and @value='checkmo']")));
//        Assert.assertFalse(paymentMethodField.getText().isEmpty(), "Поле 'Метод оплаты' не заполнено");
//    }
}
