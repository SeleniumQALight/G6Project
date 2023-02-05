package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//h2[contains(text(), 'qaauto')]")
    private WebElement userName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }
    public MyProfilePage checkUserName(String expectedUserName){
        Assert.assertEquals("The userName is displayed",expectedUserName,userName.getText());
        return this;
    }

}
