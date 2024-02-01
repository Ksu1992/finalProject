package org.alevel.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;

public class SocialMediaTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Инициализация WebDriver
        driver = new FirefoxDriver(); // Инициализация драйвера (замените на нужный)
        driver.get("https://yaposhka.com.ua/ua/");
    }

    @DataProvider(name = "socialMediaButtons")
    public Object[][] socialMediaButtons() {
        return new Object[][]{
                //{"Instagram"},
                {"Facebook"},
                //{"Telegram"},
                {"YouTube"}
        };
    }

    @Test(dataProvider = "socialMediaButtons")
    public void clickSocialMediaButton(String socialMedia) {
        // Находим кнопку социальной сети в футере
        WebElement socialMediaButton = driver.findElement(By.xpath("//li[@class='social-item " + socialMedia.toLowerCase() + "']"));

        // Кликаем на кнопку
        socialMediaButton.click();

        // Дождитесь загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Ожидание изменения URL
        wait.until(ExpectedConditions.urlContains("https://www." + socialMedia.toLowerCase() + "."));

        // Проверка наличия всплывающего окна и его закрытие
        if (isPopupPresent(wait, socialMedia)) {
            closePopup(wait, socialMedia);
        }

        // Получаем новый URL
        String newUrl = driver.getCurrentUrl();

        // Проверяем, что URL содержит часть, характерную для соответствующей социальной сети
        Assert.assertTrue(newUrl.contains(socialMedia.toLowerCase()), "Не удалось перейти на страницу " + socialMedia);
    }

    private boolean isPopupPresent(WebDriverWait wait, String socialMedia) {
        // Проверка наличия всплывающего окна
        By popupLocator = getPopupLocator(socialMedia);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private void closePopup(WebDriverWait wait, String socialMedia) {
        // Закрытие всплывающего окна
        By popupLocator = getPopupLocator(socialMedia);
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(popupLocator));
        popup.click();

        // Дождитесь, пока всплывающее окно исчезнет
        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupLocator));
    }

    private By getPopupLocator(String socialMedia) {
        // Вернуть соответствующий локатор в зависимости от переданной социальной сети
        switch (socialMedia.toLowerCase()) {
            case "instagram":
                return By.xpath("//div[contains(@class, 'x1i10hfl') and contains(text(), 'Обновить страницу')]");
            case "facebook":
                return By.xpath("your-facebook-popup-id");
            case "telegram":
                return By.xpath("//div[@class='x1n2onr6 x1ja2u2z x78zum5 x2lah0s xl56j7k x6s0dn4 xozqiw3 x1q0g3np xi112ho x17zwfj4 x585lrc x1403ito x972fbf xcfux6l x1qhh985 xm0m39n x9f619 xn6708d x1ye3gou xtvsq51 x1r1pt67']//span[text()='Разрешить все cookie']");
            case "youtube":
                return By.xpath("//button[@aria-label='Принять все']");
            default:
                throw new IllegalArgumentException("Неизвестная социальная сеть: " + socialMedia);
        }
    }

    @AfterMethod
    public void tearDown() {
        // Закрываем браузер после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }
}






