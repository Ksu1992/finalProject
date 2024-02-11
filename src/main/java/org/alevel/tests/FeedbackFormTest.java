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

        // Assert for first name input
        String firstNameValue = feedbackFormPage.getFirstNameValue();
        Assert.assertEquals(firstNameValue, name, "First name input value is incorrect");

        // Assert for phone input
        String phoneValue = feedbackFormPage.getPhoneValue();
        String formattedPhoneNumber = FeedbackFormPage.formatPhoneNumber(phone); // Ensure this method is accessible
        Assert.assertEquals(phoneValue, formattedPhoneNumber, "Phone input value is incorrect");

        // Assert for comment input
        String commentValue = feedbackFormPage.getCommentValue();
        Assert.assertEquals(commentValue, message, "Comment input value is incorrect");
    }
}
