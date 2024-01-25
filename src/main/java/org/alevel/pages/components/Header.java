package org.alevel.pages.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import org.openqa.selenium.NoSuchElementException;


public class Header {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'Піца') or contains(text(), 'Pizza') or contains(text(), 'Пицца')]")
    private WebElement pizzaMenuLink;

    @FindBy(xpath = "//span[contains(text(), 'Салати') or contains(text(), 'Salads') or contains(text(), 'Салаты')]")
    private WebElement saladsMenuLink;

    @FindBy(xpath = "//span[contains(text(), 'Бургери') or contains(text(), 'Burgers') or contains(text(), 'Бургеры')]")
    private WebElement burgersMenuLink;

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickMenuLink(WebElement menuLink, String menuName) {
        try {
            if (menuLink.isDisplayed() && menuLink.isEnabled()) {
                menuLink.click();
                System.out.println("Clicked on " + menuName + " menu");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + menuName + " menu link not found.");
            throw e;
        } catch (Exception e) {
            System.out.println("Error clicking on " + menuName + " menu: " + e.getMessage());
            throw e;
        }
    }


    public void clickOnCategory(String categoryName) {
        switch (categoryName.toLowerCase()) {
            case "pizza":
            case "пицца":
            case "піца":
                clickMenuLink(pizzaMenuLink, categoryName);
                break;
            case "salads":
            case "салаты":
            case "салати":
                clickMenuLink(saladsMenuLink, categoryName);
                break;
            case "burgers":
            case "бургеры":
            case "бургери":
                clickMenuLink(burgersMenuLink, categoryName);
                break;
            default:
                System.out.println("Category not recognized: " + categoryName);
                break;
        }
    }

}


