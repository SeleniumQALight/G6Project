package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElement;
import pages.CreatePostPage;
import pages.ProfilePage;

public class HeaderElements extends CommonActionsWithElement {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement profileButton;

    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;


    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
       // PageFactory.initElements(webDriver, this);
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(signOutButton);
    }
}
