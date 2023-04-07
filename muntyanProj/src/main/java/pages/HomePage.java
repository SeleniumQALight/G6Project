package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    private String titlePost = ".//*[text()='%s']";
//    @FindBy(xpath = ".//*[@href='/create-post']")
//    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelatedURL() {
        return "/";
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

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waiteChatToBeHide();
        Assert.assertTrue("Home Page is not loaded", headerElement.isButtonSignOutDisplayed());

        return this;
    }

    @Step
    public PostPage clickOnCreatedPost(String postTitle) {
        clickOnElement(String.format(titlePost, postTitle));
        return new PostPage(webDriver);
    }

//    public CreatePostPage clickOnCreatePostButton() {
//        clickOnElement(buttonCreatePost);
//
//        return new CreatePostPage(webDriver);
//    }
}
