package org.alevel.pages;
import org.alevel.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CarouselPage extends BasePage {
    private WebDriverWait wait;

    public CarouselPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void waitForCarouselVisibility() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(carouselBlockSelectorLocator));
        wait.until(ExpectedConditions.visibilityOfElementLocated(carouselDotsSelectorLocator));
    }

    public void clickCarouselDot(int index) {
        List<WebElement> dots = driver.findElements(By.xpath("//button[@class='owl-dot']"));
        if (index >= 0 && index < dots.size()) {
            dots.get(index).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(activeDotSelectorLocator));
        } else {
            throw new IndexOutOfBoundsException("Carousel dot index out of bounds.");
        }
    }

    public int getNumberOfActiveDots() {
        return driver.findElements(activeDotSelectorLocator).size();
    }

    public int getNumberOfInactiveDots() {
        List<WebElement> inactiveDots = driver.findElements(inactiveDotSelectorLocator);
        return inactiveDots.size();
    }
}
