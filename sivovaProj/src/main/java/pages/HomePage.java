package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage{
    @FindBy (xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    private HeaderElements headerElement = new HeaderElements(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }


    public HeaderElements getHeaderElement() {
        return headerElement;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);

        if(!isButtonSignOutDisplayed()) {
        // login
        loginPage.fillingLoginFormWithValidCred();}

        // verify that we are on the home page
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("Home Page is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }


}
