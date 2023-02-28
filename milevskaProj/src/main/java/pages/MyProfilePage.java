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
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']//h2")
    private WebElement loggedUser;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    private WebElement buttonSaveUpdates;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }
 public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
 }
    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage checkIsCorrectUserLogin(String username){
        Assert.assertEquals("This not correct user", username, loggedUser.getText());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle){
        List <WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while(!listOfPosts.isEmpty() && counter>0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
            .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();

            logger.info("Post was deleted with title" + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;
        }
        if (listOfPosts.size() ==0) {
            logger.info("All posts was deleted with title" + postTitle);
        }else{
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message delete Post is not displayed ", isElementDisplayed(successDeletePostMessage));
        return this;
    }


    public EditPostPage openEditPageForPostWithTitle(String postTitle){
        List <WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        clickOnElement(String.format(titlePost, postTitle));
        new PostPage(webDriver).checkIsRedirectToPostPage()
                .clickOnEditButton()
                .checkIsRedirectToEditPostPage();
        return new EditPostPage(webDriver);
    }

    public MyProfilePage checkIsPostWasEdited(String postTitle, String postTitleEdited){
        List <WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        List <WebElement> listOfPostsEdited = getPostsListWithTitle(postTitleEdited);
        if (listOfPosts.size() ==0){
            logger.info("Post was edited");
        }else {
            logger.info("Post with the same title exists");
            deletePostsWithTitleTillPresent(postTitle);
        }
        if (listOfPostsEdited.size() == 0){
            logger.error("Edited post doesn't display on My Profile page");
            Assert.fail("Edited post doesn't display on My Profile page");
        }else{
            logger.info("Post was edited");
        }
        return this;
    }

    public MyProfilePage deleteEditedPost(String postTitleEdited){
        List <WebElement> listOfPostsEdited = getPostsListWithTitle(postTitleEdited);
        int counter = listOfPostsEdited.size();
        while(!listOfPostsEdited.isEmpty() && counter>0){
            clickOnElement(String.format(titlePost, postTitleEdited));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();

            logger.info("Post was deleted with title" + postTitleEdited);
            listOfPostsEdited = getPostsListWithTitle(postTitleEdited);
            counter--;
        }
        if (listOfPostsEdited.size() ==0) {
            logger.info("All posts was deleted with title" + postTitleEdited);
        }else{
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }
}
