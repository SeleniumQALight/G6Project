package pages;


import elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static elements.HeaderElement.isSignOutButtonDisplayed;

public class HomePage extends ParentPage {


    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    private HeaderElement headerElement = new HeaderElement(webDriver);


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

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isSignOutButtonDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }


    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", isSignOutButtonDisplayed());
        return this;

    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }


}




