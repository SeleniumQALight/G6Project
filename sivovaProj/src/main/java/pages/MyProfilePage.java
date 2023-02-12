package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']") private WebElement avatar;
    @FindBy(tagName = "h2") private WebElement userName;

    private String titlePost = ".//*[text()='%s']";
    @FindBy (xpath = ".//*[text()='Post successfully deleted']") private WebElement successDeletePostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkUserNameDisplayed(String expectedUserName) {
        Assert.assertEquals("Incorrect user name is shown", expectedUserName, userName.getText());
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


    public MyProfilePage deletePostWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter>0){
            clickElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickDeleteButton()
                    .checkIsSuccessDeletePostMessagePresent()
            ;
            logger.info("Post was deleted with title "+ postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--; // counter = counter - 1
        }
        if (listOfPosts.size() ==  0) {
        logger.info("All posts were deleted with title " + postTitle);
        } else {
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message Delete post is not shown", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    public PostPage openPostWithTitle(String postTitle) {
        try {
        webDriver.findElement(By.xpath(String.format(titlePost,postTitle))).click();
        return new PostPage(webDriver);
        } catch (Exception e){
            printErrorAndStopTest(e);
            return null;
        }
    }
}
