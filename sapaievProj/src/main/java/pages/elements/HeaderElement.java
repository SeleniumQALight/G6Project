package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;


public class HeaderElement extends CommonActionsWithElements {

    @FindBy (xpath=".//*[@data-original-title='My Profile']")
    WebElement buttonMyProfile;


    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;


    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;


    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }


    public MyProfilePage clickOnMyProfileButton(){
       clickOnElement(buttonMyProfile);
       return new MyProfilePage(webDriver);
    }



    public boolean isButtonSignOutDisplayed() {

        return isElementDisplayed(buttonSignOut);
    }


    public boolean isButtonSignInDisplayed() {

        return isElementDisplayed(buttonSignIn);
    }



    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }





}
