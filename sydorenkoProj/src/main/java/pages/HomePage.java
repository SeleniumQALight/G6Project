package pages;

import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;
import pages.elements.HeaderElement;

import static org.junit.Assert.assertTrue;

public class HomePage extends ParentPage {

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

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
        assertTrue("HomePage isn't loaded", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }
}
