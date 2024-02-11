package org.alevel.tests;
import org.alevel.pages.CartPage;
import org.alevel.pages.CheckoutPage;
import org.alevel.pages.ProductPage;
import org.testng.annotations.Test;

public class OrderTest extends BaseTest {
    @Test(dataProvider = "productData")
    public void testPlaceOrder(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
        CartPage cartPage = new CartPage(driver);

        // Навигация на страницу продукта и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        cartPage.hoverAndClickCheckout();

        // Ожидание видимости элемента на странице оформления заказа
        checkoutPage.fillShippingInformation("Ксения", "973944987", "Харків,Натальї Ужвій,64", "3", "116", "7", "345", "portmone");
        checkoutPage.assertOrderFields();
    }
}

