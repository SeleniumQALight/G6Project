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
    private WebElement successDeleteMessage;
    @FindBy(xpath = ".//a[@class='text-primary mr-2']")
    private WebElement editButton;

    private String titlePost = ".//*[text()='%s']";
    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContains();
        waitChatToBeHide();
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Post is not unique", 1, getPostListWithTitle(postTitle).size());
        logger.info("Post has been created!");
        return this;
    }

    public MyProfilePage checkUserName(String expectedText) {
        forTextComparing(expectedText, userName);
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter > 0) {
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkSuccessDeleteMessagePresent()
            ;
            logger.info(postTitle + " post has been deleted");
            listOfPosts = getPostListWithTitle(postTitle);
            counter--;
        }
        if(listOfPosts.size() == 0){
            logger.info("All post have been deleted");
        }else {
            logger.error("Delete function fails");
            Assert.fail("Delete function fails");
        }
            return this;
    }

    private MyProfilePage checkSuccessDeleteMessagePresent() {
        Assert.assertTrue("Message about Delete Post is not displayed!", isElementDisplayed(successDeleteMessage));
        return this;
    }

    public PostPage clickOnPostTitle(String postTitle) {
        clickOnElement(String.format(titlePost,postTitle));
        return new PostPage(webDriver);
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
