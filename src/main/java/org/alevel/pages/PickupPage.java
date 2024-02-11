package org.alevel.pages;
import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PickupPage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement placeOrder;

    public PickupPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void fillShippingInformation(String firstName, String phone, String city, String pickup, String paymentMethods, String checkOut) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='telephone'][1]")));
        phoneInput.click();
        phoneInput.sendKeys(phone);

        // Находим элемент для выбора самовывоза
        WebElement element = driver.findElement(By.xpath("//td[@id='label_method_selfget_selfget' and contains(text(), 'Самовивіз')]"));

        // Используем Actions для выполнения клика
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();

        // Выбор адреса самовывоза
        By hoursLocator = By.xpath("(//div[@class='title' and contains(text(), 'Харків вул. Ярослава Мудрого, 33/35')])");
        WebElement hoursElement = wait.until(ExpectedConditions.visibilityOfElementLocated(hoursLocator));
        hoursElement.click();

        // Выбор метода оплаты
        WebElement paymentMethodsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='radio' and starts-with(@name, 'payment[method]') and @value='checkmo']")));
        paymentMethodsInput.click();
        this.placeOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='place-order-primary']")));

        Assert.assertTrue(placeOrder.isEnabled(), "Кнопка 'Заказать' неактивна");
    }
}


