package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']") private WebElement avatar;

    private String titlePost = ".//*[text()='%s']";
    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(
                By.xpath(String.format(titlePost,title)));
    }
    public MyProfilePage checkPostWasCreated(String post_title) {
        Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(post_title).size());
        return this;
    }
}
