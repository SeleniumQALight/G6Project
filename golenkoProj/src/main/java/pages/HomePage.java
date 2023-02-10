package pages;



import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;


public class HomePage extends ParentPage {
    private final HeaderElement headerElement = new HeaderElement(webDriver);

    HeaderElement headerElement = new HeaderElement(webDriver) ;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
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
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(headerElement.getButtonCreatePost());
        return new CreatePostPage(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
}
