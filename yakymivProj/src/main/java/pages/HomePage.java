package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage {

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HeaderElements getHeaderElement() {
        return headerElements;
    }


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.fillingLoginFormWithValidCred();
        checkIsRedirectHomePage();
        return this;
    }

    private HomePage checkIsRedirectHomePage() {
        Assert.assertTrue("Home Page is not loaded", headerElements.isButtonSignOutDisplayed());
        return this;
    }




}
