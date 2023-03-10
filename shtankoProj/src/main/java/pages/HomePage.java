package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;


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

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        //як що ми не залогінени то виконуй дії далі
        loginPage.openLoginPage();
        if (!headerElement.isButtonSingOutDisplayed())
        {
        // залогінитись
        loginPage.fillingLoginFormWhitValidCred();
        }
        //перевірити що ми на HomePage
        checkIsRedirectToHomePage();
        return this;
    }
    @Step
    public HomePage checkIsRedirectToHomePage(){
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSingOutDisplayed());
        return this;
    }

    @Step
    public HomePage getUrlHomepage(){
        webDriver.get(base_url);
        return this;
    }
}
