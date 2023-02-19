package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage {


@FindBy(xpath = ".//*[@href='/create-post']")
private WebElement buttonCreatePost;

    private HeaderElements headerElements ;
    public HomePage(WebDriver webDriver) {
        super(webDriver);
       this.headerElements=new HeaderElements(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!getHeaderElements().isButtonSignOutDisplayed()){
            loginPage.fillValidCreds();
        }
        checkIsRedirectToHomePage();
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHidden();
        Assert.assertTrue("Home page is not opened",headerElements.isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return  new CreatePostPage(webDriver);
    }
}
