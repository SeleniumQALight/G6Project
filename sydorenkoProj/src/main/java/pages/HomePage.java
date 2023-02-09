package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import static org.junit.Assert.assertTrue;

public class HomePage extends ParentPage {

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;
    @FindBy(xpath = "//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(buttonSignOut);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        if (!isButtonSignOutDisplayed())
            loginPage.fillingLoginFormWithValidCred();
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        assertTrue("HomePage isn't loaded", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
