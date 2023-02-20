package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
//    @FindBy(xpath = ".//*[@href='/create-post']")
//    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
    //    @FindBy(xpath = ".//button[@class ='btn btn-sm btn-secondary']")
//    private WebElement signOutButton;

//    public boolean isButtonSignOutDisplayed() {
//        return isElementDisplayed(signOutButton);
//    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        if(!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("Home Page is not loaded", headerElement.isButtonSignOutDisplayed());

        return this;
    }

//    public CreatePostPage clickOnCreatePostButton() {
//        clickOnElement(buttonCreatePost);
//
//        return new CreatePostPage(webDriver);
//    }
}
