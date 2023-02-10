package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//h2[contains(text() ,'qaauto')]")
    private WebElement myProfilePageUserName;
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

//    public HeaderElement getHeaderElement() {
//        return headerElement;
//    }
//
//    private HeaderElement headerElement = new HeaderElement(webDriver);

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }


    public MyProfilePage checkUserIsDisplayedOnMyProfilePage(String expectUserName) {
        Assert.assertEquals("qaauto", expectUserName,myProfilePageUserName.getText());
        return this;
    }}
