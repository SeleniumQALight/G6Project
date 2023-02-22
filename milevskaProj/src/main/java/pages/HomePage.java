package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.logging.Logger;

public class HomePage extends ParentPage{

    public HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @FindBy(xpath = ".//button[@class ='btn btn-sm btn-secondary']")
    private WebElement buttonSignOut;

    public boolean isButtonSignOutDisplayed(){
        return isElementDisplayed(buttonSignOut);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        //login
        //check that we are in homepage
        if(!isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;

    }
    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

}


