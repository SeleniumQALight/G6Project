package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElements extends CommonActionsWithElements {

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutDisplayed(){
        try {
            //webDriverWait15.until(ExpectedConditions.visibilityOf(buttonSignOut));
            return isElementDisplayed(buttonSignOut);
        }catch (Exception e){
            return false;
        }

    }
}
