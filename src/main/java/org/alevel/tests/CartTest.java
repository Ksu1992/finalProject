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
        Assert.assertNotEquals(itemCount, "0", "Cart is empty, but should have items.");
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
        Assert.assertEquals(actualQuantity, "5", "Quantity did not update correctly.");
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
        Assert.assertTrue(isItemRemoved, "Item is still present in the cart after removal.");
    }
}
