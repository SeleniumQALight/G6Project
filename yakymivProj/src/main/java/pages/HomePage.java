package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isButtonDisplayed(buttonSignOut);
        }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.fillingLoginFormWithValidCred();
        checkIsRedirectHomePage();
        return this;
    }

    private HomePage checkIsRedirectHomePage() {
        Assert.assertTrue("Home Page is not loaded", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage ClickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
