package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage{

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;

    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }
     public boolean isButtonSingOutDisplayed(){
            return isElementDisplayed(buttonSingOut);
        }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        // залогінитись
        loginPage.fillingLoginFormWhitValidCred();
        //перевірити що ми на HomePage
        checkIsRedirectToHomePage();
        return this;
    }
    public HomePage checkIsRedirectToHomePage(){
        Assert.assertTrue("HomePage is not loaded",isButtonSingOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
