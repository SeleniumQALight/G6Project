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

    @FindBy(xpath = " .//*[@class='alert alert-success text-center']")
    private WebElement successDeletePostMessage;


    private String titlePost = ".//*[text() = '%s']"; //щоб знайти свій текст по тайтлу, описали локатор окремою змінною.


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
        Assert.assertTrue("MyProfilePage", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostsListWithTittle(String title) {
        return webDriver.findElements(
                By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals(" Number of posts with title ", 1, getPostsListWithTittle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTittleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTittle(postTitle);

        int counter = listOfPosts.size();

        while (!listOfPosts.isEmpty() && counter > 0) {
            clickOnElement(String.format(titlePost, postTitle));

            new PostPage(webDriver)  //це повертає сторінку поста зі всiма методами
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent()
            ;
            logger.info("Post was deleted  with tittle " + postTitle);
            listOfPosts = getPostsListWithTittle(postTitle);
            counter--;
        }
        if (listOfPosts.size() == 0) {
            logger.info(" All posts were deleted with title " + postTitle);
        } else {
            logger.info("delete error");
            Assert.fail("delete failed");
        }


        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {

        Assert.assertTrue(" Message delete Post is not displayed", isElementDisplayed(successDeletePostMessage));

        return this;
    }
}
