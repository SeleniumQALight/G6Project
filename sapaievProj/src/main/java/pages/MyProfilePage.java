package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyProfilePage extends ParentPage{


    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;


    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement nameUserInProfile;


    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }



    public MyProfilePage checkIsRedirectToMyProfilePage() {
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }


    // добавил проверку , что на странице профиля есть имя юзера, которым мы залогинились
    public MyProfilePage checkIsRedirectToMyProfilePageByName(String userName){
        Assert.assertEquals("MyProfilePage is not loaded", userName, nameUserInProfile.getText());
        return this;
    }



}
