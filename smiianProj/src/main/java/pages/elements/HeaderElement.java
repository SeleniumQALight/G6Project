package pages.elements;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;
import pages.CreatePostPage;
import pages.HomePage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionWithElements {

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement buttonLogOut;

    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;


    public HeaderElement(WebDriver webDriver) {            //constructor
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){

        return isObjectDisplayed(buttonLogOut);
    }

//    public static HomePage checkIsRedirectToHomePage() {
//        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
//
//        return this;
//    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);

        return new CreatePostPage(webDriver);
    }
}
