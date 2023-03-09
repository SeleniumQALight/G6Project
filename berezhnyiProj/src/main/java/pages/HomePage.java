package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage{

    //now on Home page we have header with buttons
    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElements.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToHide();
        Assert.assertTrue("HomePage is not loaded", headerElements.isButtonSignOutDisplayed());

        return this;
    }

}
