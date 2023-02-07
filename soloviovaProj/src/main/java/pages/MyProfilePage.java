package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage {

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//h2")
    private WebElement userName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        // TODO check URL
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkUserName(String expectedText){
        forTextComparing(expectedText, userName);
        return this;
    }
}
