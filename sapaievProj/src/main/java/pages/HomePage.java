package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {


    private HeaderElement headerElement=new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }



    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;


    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }




    public boolean isButtonSignOutDisplayed() {
            return isElementDisplayed(buttonSignOut);
    }


    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }





    public HomePage openHomePage() {
        LoginPage loginPage=new LoginPage(webDriver);
        if(!isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();

        return this;
    }




    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded",isButtonSignOutDisplayed());
        return this;
    }


    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);



        return new CreatePostPage(webDriver);
    }
}
