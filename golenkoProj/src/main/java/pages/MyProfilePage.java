package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{
    @FindBy (xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy (xpath = ".//div[@class='container py-md-5 container--narrow']//h2")
    private WebElement login;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL
        Assert.assertTrue("MyProfilePage is not loaded",
                isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIsCorrectLoginDisplayed(String expectedLogin) {
        Assert.assertTrue("Correct login is displayed", isElementDisplayed(login));
        Assert.assertEquals("Incorrect login", expectedLogin, login.getText());
        return this;
    }
}
