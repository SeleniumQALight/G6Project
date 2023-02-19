package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.Assert.*;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = "//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = "//div[@class='container py-md-5 container--narrow']/h2")
    private WebElement userNamePlace;
    @FindBy(xpath = "//div[@class='alert alert-success text-center'][text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;
    private String titlePost = "//*[text()='%s']";

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
        assertTrue("MyProfilePage isn't loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIfMyProfilePageHasCorrectUser(String userName) {
        assertTrue("User name is not on page", userNamePlace.getText().contains(userName));
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        assertEquals("Number with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter > 0) {
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;
        }
        if (listOfPosts.isEmpty()) {
            logger.info("All posts were deleted with title " + postTitle);
        } else {
            logger.error("Post delete failed");
            fail("Post delete failed");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        assertTrue("Success Deleted PostMessage is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }
    public PostPage clickOnPostWithTitle(String postTitle) {
        clickOnElement(getPostsListWithTitle(postTitle).get(0));
        return new PostPage(webDriver);
    }
}
