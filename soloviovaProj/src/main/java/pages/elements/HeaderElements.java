package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElements extends CommonActionWithElements {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;
    @FindBy(xpath = ".//a[@href='/create-post']")
    private WebElement createPostButton;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(signOutButton);
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(createPostButton);
        return new CreatePostPage(webDriver);
    }

}
