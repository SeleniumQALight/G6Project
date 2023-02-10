package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']") private WebElement avatar;
    @FindBy(tagName = "h2") private WebElement userName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkUserNameDisplayed(String expectedUserName) {
        Assert.assertEquals("Incorrect user name is shown", expectedUserName, userName.getText());
        return this;
    }
}
