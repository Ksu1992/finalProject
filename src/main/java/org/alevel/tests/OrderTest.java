package org.alevel.tests;
import org.alevel.pages.CheckoutPage;
import org.alevel.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.time.Duration;

public class OrderTest extends BaseTest {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);

        // Навигация на страницу продукта и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        // Создание экземпляра Actions
        Actions actions = new Actions(driver);

        // Наведение на элемент, который находится в header
        WebElement headerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='minicart-header grid-extend']")));
        actions.moveToElement(headerElement).perform();

        // Клик на кнопку оформления заказа
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='top-cart-btn-checkout']")));
        orderButton.click();

        // Ожидание видимости элемента на странице оформления заказа
        checkoutPage.fillShippingInformation("Ксения", "973944987", "Харків,Натальї Ужвій,64", "3", "116", "7", "345", "portmone");
        checkoutPage.assertOrderFields();
    }
}

