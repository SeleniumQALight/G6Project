package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//div[@class = 'container py-md-5 container--narrow']//h2//img")
    private WebElement actualLoginName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded"
                , isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIsLoginNameDisplayed(String expectedLoginName){
        Assert.assertEquals("Login name is wrong", expectedLoginName, actualLoginName.getText());
        return this;
    }
}
