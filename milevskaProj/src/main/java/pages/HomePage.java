package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.logging.Logger;

public class HomePage extends ParentPage{

    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    HeaderElement headerElement = new HeaderElement(webDriver);


    public HomePage(WebDriver webDriver) {
        super(webDriver);
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
        //login
        //check that we are in homepage
        if(!isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
        return this;

    }
    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

}


