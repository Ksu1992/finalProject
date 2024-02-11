package org.alevel.pages;
import org.alevel.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class PickupPage extends BasePage {
    private WebDriverWait wait;
    private WebElement placeOrder;

    public PickupPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    public PickupPage hoverOnMiniCartHeader() {
        WebElement headerElement = driver.findElement(miniCartHeaderLocator);
        new Actions(driver).moveToElement(headerElement).perform();
        return this;
    }
    public PickupPage clickCheckoutButton() {
        WebElement orderButton = wait.until(ExpectedConditions.elementToBeClickable(orderButtonLocator));
        orderButton.click();
        return this;
    }

    public void fillShippingInformation(String firstName, String phone, String city, String pickup, String paymentMethods, String checkOut) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInputLocator));
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInputLocator));
        phoneInput.click();
        phoneInput.sendKeys(phone);

        // Находим элемент для выбора самовывоза
        WebElement element = driver.findElement(elementLocator);

        // Используем Actions для выполнения клика
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        // Выбор адреса самовывоза
        WebElement hoursElement = wait.until(ExpectedConditions.visibilityOfElementLocated(hoursLocator));
        hoursElement.click();

        // Выбор метода оплаты
        WebElement paymentMethodsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodsInputLocator));
        paymentMethodsInput.click();
        this.placeOrder = wait.until(ExpectedConditions.elementToBeClickable(placeOrderLocator));

        Assert.assertTrue(placeOrder.isEnabled(), "Кнопка 'Заказать' неактивна");
    }
}


