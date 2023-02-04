package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {


    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;


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


    public boolean isSignOutButtonDisplayed() {
        return isElementPresented(signOutButton);

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




