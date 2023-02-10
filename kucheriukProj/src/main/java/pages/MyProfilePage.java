package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    private String titlePost = ".//*[text()='%s']";

    @FindBy(xpath = ".//div[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL
        Assert.assertTrue("MyProfilePage is not loaded"
                , isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while (!listOfPosts.isEmpty() && counter > 0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent()
            ;
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostListWithTitle(postTitle);
            counter--;
        }
        if(listOfPosts.size() == 0) {
            logger.info("All posts were deleted with title " + postTitle);
        }else {
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message delete Post is not displayed"
                , isElementDisplayed(successDeletePostMessage));
        return this;
    }
}
