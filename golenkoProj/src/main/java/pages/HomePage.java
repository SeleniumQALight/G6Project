package pages;


import org.apache.hc.core5.http.NameValuePair;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy (xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    HeaderElement headerElement = new HeaderElement(webDriver) ;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(signOutButton);
    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        if (!isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
