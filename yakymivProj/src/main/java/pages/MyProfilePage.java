package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy(xpath = ".//h2")
    private WebElement userName;

    private String titlePost = ".//*[text()='%s']";

    public List<WebElement> getPostListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost,title)));
    }

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

    public MyProfilePage checkPostWasCreated(String post_title) {
        Assert.assertEquals("Number of posts with title ",1 , getPostListWithTitle(post_title).size());
        return this;
    }
}
