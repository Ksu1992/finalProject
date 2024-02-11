package org.alevel.tests;
import org.alevel.pages.FeedbackFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FeedbackFormTest extends BaseTest {
    @Test(dataProvider = "feedbackFormData")
    public void fillAndSubmitFeedbackForm(String name, String phone, String message) throws InterruptedException {
        FeedbackFormPage feedbackFormPage = new FeedbackFormPage(driver);
        feedbackFormPage.openFeedbackForm();
        feedbackFormPage.fillFeedbackForm(name, phone, message);

        String firstNameValue = feedbackFormPage.getFirstNameValue();
        Assert.assertEquals(firstNameValue, name, "Значение в поле ввода имени неверно.");

        String phoneValue = feedbackFormPage.getPhoneValue();
        String formattedPhoneNumber = FeedbackFormPage.formatPhoneNumber(phone);
        Assert.assertEquals(phoneValue, formattedPhoneNumber, "Значение в поле ввода телефона неверно.");

        String commentValue = feedbackFormPage.getCommentValue();
        Assert.assertEquals(commentValue, message, "Значение в поле ввода комментария неверно.");
    }
}
