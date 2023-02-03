package pages;

<<<<<<< HEAD
=======
import org.junit.Assert;
import org.openqa.selenium.By;
>>>>>>> main
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

<<<<<<< HEAD
=======
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

>>>>>>> main
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

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;

    public boolean isSignOutButtonDisplayed() {
        return isElementPresented(signOutButton);

    }

<<<<<<< HEAD
=======
    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
        return this;

    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

>>>>>>> main

}




