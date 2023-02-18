package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginForWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSignOutDisplayed());
        return this;
    }
}
// НЕ ВИДАЛЯЮ БО ХОЧУ ПОКИ ЩО ЗАЛИШИТИ ДЛЯ ПРИКЛАДА!!!!!!!


//    public boolean isButtonSignOutDisplayed() {
//        try {
//            return webDriver.findElement(
//                    By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }