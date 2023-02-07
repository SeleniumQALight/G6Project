package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatar;
    private String titlePost = "//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO Check URL
        assertTrue("MyProfilePage isn't loaded", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        assertEquals("Number with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }
}
