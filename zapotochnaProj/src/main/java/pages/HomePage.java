package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

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


/*
    public boolean isButtonSignOutDisplayed() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
        return isElementDisplayed(buttonSignOut);
    }
*/
@Step

    public HomePage openHomePage() {

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();

        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;

    }
    @Step

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue(" Home page is not loaded",headerElement.isButtonSignOutDisplayed());
        return this;
    }




}
