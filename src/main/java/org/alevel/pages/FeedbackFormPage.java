package org.alevel.pages;
import org.alevel.base.BasePage;
import org.alevel.pages.components.Footer;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FeedbackFormPage extends BasePage {
    private WebDriverWait wait;

    public FeedbackFormPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void openFeedbackForm() throws InterruptedException {
        WebElement feedbackButton = driver.findElement(feedbackButtonLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", feedbackButton);
        Thread.sleep(3000); // Consider using a more robust waiting mechanism
        feedbackButton.click();
    }

    public void fillFeedbackForm(String name, String phone, String message) {
        WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameFeedbackInputLocator));
        firstNameInput.click();
        firstNameInput.sendKeys(name);

        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneFeedbackInputLocator));
        phoneInput.click();
        phoneInput.sendKeys(phone);

        WebElement commentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(commentFeedbackInputLocator));
        commentInput.click();
        commentInput.sendKeys(message);
    }

    public String getFirstNameValue() {
        return driver.findElement(firstNameFeedbackInputLocator).getAttribute("value");
    }

    public String getPhoneValue() {
        return driver.findElement(phoneFeedbackInputLocator).getAttribute("value");
    }

    public String getCommentValue() {
        return driver.findElement(commentFeedbackInputLocator).getAttribute("value");
    }

    public static String formatPhoneNumber(String digits) {
        // Check if the input has the correct length
        if (digits.length() != 9) {
            throw new IllegalArgumentException("Invalid phone number length");
        }

        // Format the phone number
        return "+38(0" + digits.substring(0, 2) + ")" + digits.substring(2, 5) + "-" + digits.substring(5, 7) + "-" + digits.substring(7);
    }
}
