package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    public HeaderElement headerElement = new HeaderElement(webDriver);
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
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
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSingOutDisplayed());
        return this;
    }
}
