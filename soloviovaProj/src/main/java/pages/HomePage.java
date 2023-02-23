package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage {

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
        if (!headerElements.isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithWalidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("Home page is not loaded. ", headerElements.isButtonSignOutDisplayed());
        return new HomePage(webDriver);
    }

}
