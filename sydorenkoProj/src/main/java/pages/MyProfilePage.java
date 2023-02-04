package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatar;
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO Check URL
        assertTrue("MyProfilePage isn't loaded", isElementDisplayed(avatar));
        return this;
    }
}
