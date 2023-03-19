package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

import java.sql.SQLException;

public class HomePage extends ParentPage{

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectedToHomePage();
        return this;
    }

    public HomePage openHomePageDBLogin(String login) throws SQLException, ClassNotFoundException {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormFromDB(login);
        }
        checkIsRedirectedToHomePage();
        return this;
    }

    public HomePage checkIsRedirectedToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }


}
