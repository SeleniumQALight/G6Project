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

    @FindBy(tagName = "h2")
    private WebElement title;

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    private String titlePost = ".//*[text()='%s']";

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("MyProfilePage is not loaded"
                , isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIsUserNameMatches(String expectedUserName) {
        Assert.assertEquals("User name doesn't match", expectedUserName, title.getText());
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(postTitle).size());
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
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;
        }
        if (listOfPosts.size() == 0) {
            logger.info("All posts were deleted with title " + postTitle);
        } else {
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message delete post isn't displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    public PostPage clickOnPostItem(String postTitle) {
        clickOnElement(String.format(titlePost, postTitle));
        return new PostPage(webDriver);
    }
}
