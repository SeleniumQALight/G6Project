package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;


public class HomePage extends ParentPage{

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }


    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        //як що ми не залогінени то виконуй дії далі
        if (!headerElement.isButtonSingOutDisplayed())
        {
        // залогінитись
        loginPage.fillingLoginFormWhitValidCred();
        }
        //перевірити що ми на HomePage
        checkIsRedirectToHomePage();
        return this;
    }
    public HomePage checkIsRedirectToHomePage(){
        Assert.assertTrue("HomePage is not loaded", getHeaderElement().isButtonSingOutDisplayed());
        return this;
    }
    public HomePage openNewTabHomePage(){
        userOpensNewTab();
        webDriver.get("https://qa-complexapp.onrender.com/");
        return this;
    }
}
