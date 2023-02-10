package pages;


import elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static elements.HeaderElement.buttonCreatePost;
import static elements.HeaderElement.isSignOutButtonDisplayed;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);

    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        //login
        //check that we are in homepage
        loginPage.fillingLoginFormWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }


    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", isSignOutButtonDisplayed());
        return this;

    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


}




