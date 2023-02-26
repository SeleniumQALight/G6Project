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
    private WebElement userVisibility;

    @FindBy(xpath = ".//div[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;


    private String titlePost = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded",
                isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkUserVisibility(String expectedMessage) {
        Assert.assertEquals("User: ", expectedMessage, userVisibility.getText());
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));

    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1,
                getPostsListWithTitle(postTitle).size());
        return this;

    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);

        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter > 0) {
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent()
            ;
            if (listOfPosts.size() != 0) {
                logger.info("Post was deleted with title " + postTitle);
            } else {
                logger.error("Delete fails");
                Assert.fail("Delete fails");
            }
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;

        }

        logger.info("All posts were deleted with title " + postTitle);
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message delete Post is not displayed",
                isElementDisplayed(successDeletePostMessage));
        return this;
    }

    public PostPage clickOnPost(String postTitle) {
        clickOnElement(String.format(titlePost, postTitle));
        return new PostPage(webDriver);
    }
}
