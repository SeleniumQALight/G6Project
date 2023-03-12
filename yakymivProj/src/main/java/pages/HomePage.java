package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage {

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HeaderElements getHeaderElement() {
        return headerElements;
    }


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!getHeaderElement().isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectHomePage();
        return this;
    }

    @Step
    private HomePage checkIsRedirectHomePage() {
        checkUrl();
        waitChatToBeHide();
        Assert.assertTrue("Home Page is not loaded", headerElements.isButtonSignOutDisplayed());
        return this;
    }




}
