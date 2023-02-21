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

    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isButtonDisplayed(buttonSignOut);
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
