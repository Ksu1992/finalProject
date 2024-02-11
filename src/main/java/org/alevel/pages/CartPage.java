package org.alevel.pages;
import org.alevel.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage hoverAndClickCheckout() {
        Actions actions = new Actions(driver);
        WebElement headerElement = driver.findElement(miniCartHeaderLocator);
        actions.moveToElement(headerElement).perform();

        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(orderButtonLocator))
                .click();

        return this;
    }

    public CartPage changeItemQuantity(String quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement quantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(itemQuantityFieldLocator));

        quantityField.click();
        quantityField.clear();

        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].value='5'", quantityField);

        return this;
    }

    public String getItemQuantity() {
        WebElement quantityField = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(itemQuantityFieldLocator));
        return quantityField.getAttribute("value");
    }

    public String getItemCountText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement qtyElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemCountSpanLocator));
        return qtyElement.getText();
    }


    public CartPage removeItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeItemButtonLocator)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(productActionsLocator));

        return this;
    }
    public boolean isItemRemoved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(productActionsLocator));
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
