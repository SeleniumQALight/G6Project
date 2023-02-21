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
        Assert.assertTrue("MyProfile Page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIsUserPresent(String name) {
        Assert.assertTrue("UserName is not valid", userName.getText().contains(name));
        return this;
    }
}
