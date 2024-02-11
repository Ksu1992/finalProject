package org.alevel.pages;

import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class CheckoutPage extends BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void fillShippingInformation(String firstName, String phone, String address, String entrance, String apartment, String floor, String intercomCode, String paymentMethods) {
        // Ожидание и заполнение полей информации о доставке
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
        firstNameInput.click();
        firstNameInput.sendKeys(firstName);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='telephone'][1]")));
        phoneInput.click();
        phoneInput.sendKeys(phone);

        // Клик по полю адреса
        WebElement addressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='courier-address-autocomplete-input' and @name='custom_attributes[courier_address]' and @aria-required='true' and @aria-invalid='false']")));
        addressInput.click();
        // Ввод адреса
        addressInput.sendKeys(address);

        // Ожидание видимости выпадающего списка
        By dropdownLocator = By.xpath("//div[@class='pac-container pac-logo']/div[@class='pac-item']");
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));

        // Ожидание видимости конкретного элемента в выпадающем списке
        By optionLocator = By.xpath("//div[@class='pac-container pac-logo']//div[@class='pac-item']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        // Клик по выбранному элементу
        option.click();

        // Ожидание устаревания элемента
        wait.until(ExpectedConditions.stalenessOf(option));

        // Поиск элемента после перехода на новую страницу
        WebElement entranceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='custom_attributes[courier_entrance]']")));
        entranceInput.sendKeys(entrance);

        WebElement apartmentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='custom_attributes[courier_apartment]']")));
        apartmentInput.sendKeys(apartment);

        WebElement floorInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='custom_attributes[courier_floor]']")));
        floorInput.sendKeys(floor);

        WebElement intercomCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='custom_attributes[courier_intercom_code]']")));
        intercomCodeInput.sendKeys(intercomCode);

        WebElement paymentMethodsInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='portmone']")));
        paymentMethodsInput.click();

    }


    // Метод для получения значения поля "ФИО"
    public String getFullName() {
        WebElement fullNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstname']")));
        return fullNameInput.getAttribute("value");
    }

    // Метод для получения значения поля "Телефон"
    public String getPhone() {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='telephone'][1]")));
        return phoneInput.getAttribute("value");
    }

    // Метод для получения значения поля "Адрес"
    public String getAddress() {
        WebElement addressInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='courier-address-autocomplete-input' and @name='custom_attributes[courier_address]' and @aria-required='true' and @aria-invalid='false']")));
        return addressInput.getAttribute("value");

    }

    // Метод для получения значения поля "Подъезд"
    public String getEntrance() {
        WebElement entranceInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='field entrance _required slide-label-wrap fill']//input[@name='custom_attributes[courier_entrance]']")));

        return entranceInput.getAttribute("value");
    }

    // Метод для получения значения поля "Квартира"
    public String getApartment() {
        WebElement apartmentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='custom_attributes[courier_apartment]']")));

        return apartmentInput.getAttribute("value");
    }

    // Метод для получения значения поля "Этаж"
    public String getFloor() {
        WebElement floorInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='custom_attributes[courier_floor]']")));

        return floorInput.getAttribute("value");
    }


    // Метод для получения значения поля "Домофон"
    public String getIntercom() {
        WebElement intercomCodeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.input-text[name='custom_attributes[courier_intercom_code]']")));

        return intercomCodeInput.getAttribute("value");
    }

    // Метод для получения значения поля "Метод оплаты"
    public String getPaymentMethod() {
        WebElement paymentMethodInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='portmone']")));
        return paymentMethodInput.getAttribute("value");
    }

    public void assertOrderFields() {
        Assert.assertFalse(getFullName().isEmpty(), "Поле 'Имя' не заполнено");
        //Assert.assertFalse(getPhone().isEmpty(), "Поле 'Телефон' не заполнено");
        // Assert.assertFalse(getAddress
    }
}