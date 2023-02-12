package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends HeaderElement {
//    @FindBy(xpath = ".//*[@href='/create-post']")
//    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

//    @FindBy(xpath = ".//button[@class ='btn btn-sm btn-secondary']")
//    private WebElement signOutButton;

//    public boolean isButtonSignOutDisplayed() {
//        return isElementDisplayed(signOutButton);
//    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        //login
        loginPage.fillingLoginFormWithValidCred();
        //check that we are in home page
        checkIsRedirectToHomePage();

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("Home Page is not loaded", isButtonSignOutDisplayed());

        return this;
    }

//    public CreatePostPage clickOnCreatePostButton() {
//        clickOnElement(buttonCreatePost);
//
//        return new CreatePostPage(webDriver);
//    }
}
