package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends ParentPage {

@FindBy(xpath=".//img[@class='avatar-small']")
private WebElement avatar;


    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public ParentPage checkIsRedirectProfilePage(){
        //TODO check URL
        Assert.assertTrue("My ProfilePage is not loaded",isElementDisplayed(avatar));
        return  this;
    }
}
