package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    private HeaderElement headerElement=new HeaderElement(webDriver);

    @Step
    public HeaderElement getHeaderElement() {
        return headerElement;
    }


  /*  @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

*/



    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }



    /*

    public boolean isButtonSignOutDisplayed() {

        return isElementDisplayed(buttonSignOut);
    }


    public boolean isButtonSignInDisplayed() {

        return isElementDisplayed(buttonSignIn);
    }


*/


    public HomePage openHomePage() {
        LoginPage loginPage=new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!headerElement.isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();

        return this;
    }




    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded",headerElement.isButtonSignOutDisplayed());
        return this;
    }

/*
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);



        return new CreatePostPage(webDriver);
    }

 */


}
