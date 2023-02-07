package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage {

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    private String titlePost = ".//*[text()='%s']";

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }


    public List<WebElement> getPostsListWithTitle(String title) {

        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public ProfilePage checkIsRedirectProfilePage() {
        //TODO check URL
        Assert.assertTrue("My ProfilePage is not loaded", isElementDisplayed(avatar));
        return new ProfilePage(webDriver);
    }

    public ProfilePage checkPostWasCreatred(String postTitle) {
        Assert.assertEquals("Incorrect count of posts", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }
}
