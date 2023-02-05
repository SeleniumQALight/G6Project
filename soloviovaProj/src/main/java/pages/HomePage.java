package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;
    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement createPostButton;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(signOutButton);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        // log in method
        loginPage.fillingLoginFormWithWalidCred();
        // check where wee are
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("Home page is not loaded. ", isButtonSignOutDisplayed());
        return this;
    }


    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }
}
