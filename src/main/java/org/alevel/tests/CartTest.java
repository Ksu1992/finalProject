package org.alevel.tests;

import org.alevel.base.DriverFactory;
import org.alevel.pages.CartPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest {

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
    public void testAddToCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='product-addtocart-button']")));
        orderButton.click();

        WebElement qtyElement = cartPage.waitForVisibilityOfElementLocated(By.xpath("//span[@class='counter qty']"));
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

        Actions actions = new Actions(driver);
        WebElement headerElement = driver.findElement(By.xpath("//div[@class='minicart-header grid-extend']"));
        actions.moveToElement(headerElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));
        Thread.sleep(1000);
        orderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']")));

        WebElement quantityField = cartPage.waitForVisibilityOfElementLocated(By.xpath("//div[@class='cart-item grid-extend']//input[@class='item-qty cart-item-qty']"));
        quantityField.click();
        quantityField.clear();
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].value='5'", quantityField);
        String actualQuantity = quantityField.getAttribute("value");

        System.out.print(actualQuantity);
        Thread.sleep(1000);
        Assert.assertEquals(actualQuantity, "5", "Quantity did not update correctly");
    }

    @Test(dataProvider = "productData")
    public void testRemoveItemFromCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        Actions actions = new Actions(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        WebElement headerElement = driver.findElement(By.xpath("//div[@class='minicart-header grid-extend']"));
        actions.moveToElement(headerElement).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));

        orderButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='action delete icon-block']")));

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product-actions']")));

        cartPage.removeItem();

        By uniqueItemLocator = By.xpath("//div[@class='product-actions']");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean isItemRemoved = wait.until(ExpectedConditions.invisibilityOfElementLocated(uniqueItemLocator));

        Assert.assertTrue(isItemRemoved, "Item is still present in the cart after removal");
    }
}
