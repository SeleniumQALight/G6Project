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
    private WebElement usernameInMyProfile;

    @FindBy(xpath = ".//h2/text")
    private WebElement usernameTextInMyProfile;

    private String titlePost = ".//*[text()='%s']";



    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar) );
        return this;
    }

    public MyProfilePage checkIsUserNameDisaplyed(){
        Assert.assertTrue("Username is not disaplyed", isElementDisplayed(usernameInMyProfile));
        return this;
    }

    public MyProfilePage checkifUserNameCorrect (String expectedUserName){
        forTextComparing(expectedUserName, usernameTextInMyProfile);
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
    Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }
}
