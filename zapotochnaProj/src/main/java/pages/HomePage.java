package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(buttonSignOut);
    }

    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.fillingLoginFormWithValidCred();
        checkIsRedirectToHomePage();
        return this;

    }

    public HomePage checkIsRedirectToHomePage() {

        Assert.assertTrue(" Home page is not loaded", isButtonSignOutDisplayed());


        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {

        clickOnElement(buttonCreatePost);

        return new CreatePostPage(webDriver);
    }


}
