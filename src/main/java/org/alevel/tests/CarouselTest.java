package org.alevel.tests;
import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class CarouselTest extends BasePage {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Kseniia\\.cache\\selenium\\geckodriver\\win64\\0.33.0C\\geckodriver.exe");
        driver = new FirefoxDriver();

    }

    @Test
    public void testCarousel() {
        driver.get("https://yaposhka.com.ua/ua/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Ожидание видимости блока с каруселью
        By carouselBlockSelector = By.xpath("//div[@class='page home-page']");
        WebElement carouselBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(carouselBlockSelector));

        // Ожидание видимости карусели
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='owl-dots']")));

        // Локаторы XPath для каждой из пяти кнопок карусели
        List<String> carouselItemXPaths = List.of(
                "//button[@class='owl-dot'][1]",
                "//button[@class='owl-dot'][2]",
                "//button[@class='owl-dot'][3]",
                "//button[@class='owl-dot'][4]"
        );

        int expectedInactiveDots = carouselItemXPaths.size();

        // Прокручиваем карусель, кликая на каждую из пяти кнопок
        for (String itemXPath : carouselItemXPaths) {
            // Используйте локатор XPath для каждой кнопки
            By currentLocator = By.xpath(itemXPath);

            // Находим кнопку и кликаем на нее
            WebElement carouselDot = wait.until(ExpectedConditions.visibilityOfElementLocated(currentLocator));
            carouselDot.click();

            // Ожидание, что новый элемент карусели становится видимым
            By activeDotLocator = By.xpath("//button[@class='owl-dot active']");
            wait.until(ExpectedConditions.presenceOfElementLocated(activeDotLocator));
        }

        // Проверяем, что только один элемент стал активным
        List<WebElement> activeDots = carouselBlock.findElements(By.xpath("//button[@class='owl-dot active']"));
        Assert.assertEquals(activeDots.size(), 1, "Неверное количество активных элементов в карусели");

        // Проверяем, что количество неактивных элементов соответствует ожидаемому
        List<WebElement> inactiveDots = carouselBlock.findElements(By.xpath("//button[@class='owl-dot' and not(@class='owl-dot active')]"));
        Assert.assertEquals(inactiveDots.size(), expectedInactiveDots, "Неверное количество неактивных элементов в карусели");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
