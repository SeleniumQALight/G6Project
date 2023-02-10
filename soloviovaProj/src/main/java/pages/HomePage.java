package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage {

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
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
        Assert.assertTrue("Home page is not loaded. ", headerElements.isButtonSignOutDisplayed());
        return new HomePage(webDriver);
    }

}
