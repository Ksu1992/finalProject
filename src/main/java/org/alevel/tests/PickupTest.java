package org.alevel.tests;
import org.alevel.pages.PickupPage;
import org.alevel.pages.ProductPage;
import org.testng.annotations.Test;

public class PickupTest extends BaseTest {
    @Test(dataProvider = "productData")
    public void testPlaceOrder(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        PickupPage checkoutPage = new PickupPage(driver);

        // Навигация на страницу продукта и добавление в корзину
        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        checkoutPage.hoverOnMiniCartHeader().clickCheckoutButton();

        // Заполнение полей на странице оформления заказа
        checkoutPage.fillShippingInformation("Ксения", "973944987", "", "", "cash", "cash");
    }
}
