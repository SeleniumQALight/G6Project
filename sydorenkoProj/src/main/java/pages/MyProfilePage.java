package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = "//div[@class='container py-md-5 container--narrow']/h2")
    private WebElement userNamePlace;
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO Check URL
        assertTrue("MyProfilePage isn't loaded", isElementDisplayed(avatar));
        return this;
    }
    public MyProfilePage checkIfMyProfilePageHasCorrectUser(String userName) {
        assertTrue("User name is not on page", userNamePlace.getText().contains(userName));
        return this;
    }
}
