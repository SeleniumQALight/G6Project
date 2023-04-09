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

    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement deleteMessage;

    private String titlePost = ".//*[text()='%s']";

    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public List<WebElement> getPostListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost,title)));
    }

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

    public MyProfilePage deletePostWithTitleTillPresent(String postTitle){
        List<WebElement> listOfPosts = getPostListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter>0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with " + postTitle);
            listOfPosts = getPostListWithTitle(postTitle);
            counter --;
        }
        if (listOfPosts.size() == 0){
            logger.info("All posts were deleted");
        }else {
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Delete message is not displayed", isElementDisplayed(deleteMessage));
        return this;
    }

    public PostPage clickOnPostWithTitle(String post_title) {
        clickOnElement(String.format(titlePost, post_title));
        return new PostPage(webDriver);
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
