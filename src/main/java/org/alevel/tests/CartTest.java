package org.alevel.tests;
import org.alevel.pages.CartPage;
import org.alevel.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
     @Test(dataProvider = "productData")
    public void testAddToCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        String itemCount = cartPage.getItemCountText();
        System.out.println(itemCount);
        Assert.assertNotEquals(itemCount, "0", "Корзина пуста, но должна содержать товары.");
    }

    @Test(dataProvider = "productData")
    public void testChangeItemQuantityInCart(String categoryName, String productName) throws InterruptedException {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        cartPage.hoverAndClickCheckout();
        cartPage.changeItemQuantity("5");

        String actualQuantity = cartPage.getItemQuantity();
        Assert.assertEquals(actualQuantity, "5", "Количество не обновилось корректно.");
    }

    @Test(dataProvider = "productData")
    public void testRemoveItemFromCart(String categoryName, String productName) {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        productPage.navigateToProductPage(categoryName, productName);
        productPage.addToCart();

        cartPage.hoverAndClickCheckout();
        cartPage.removeItem();

        boolean isItemRemoved = cartPage.isItemRemoved();
        Assert.assertTrue(isItemRemoved, "Товар все еще присутствует в корзине после удаления.");
    }
}
