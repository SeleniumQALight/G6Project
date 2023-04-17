package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//h2[contains(text() ,'qaauto')]")
    private WebElement myProfilePageUserName;
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    @FindBy(name = "title")
    private WebElement inputTitle;


    private String titlePost = ".//*[text()='%s']";
    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

//    public HeaderElement getHeaderElement() {
//        return headerElement;
//    }
//
//    private HeaderElement headerElement = new HeaderElement(webDriver);

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile/";
    }
    @Step
    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("My Profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {

        return webDriver.findElements(
                By.xpath(String.format(titlePost, title)));
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
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--; //counter=counter-1
        }
        if (listOfPosts.size() == 0) {
            logger.info("All post were deleted with title" + postTitle);
        } else {
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {


        Assert.assertTrue("Message element", isElementDisplayed(successDeletePostMessage));


        return this;
    }


    public MyProfilePage clickOnCreatedPost(String postTitle) {
//        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
//        int counter = listOfPosts.size();
//        while (!listOfPosts.isEmpty() && counter > 0) {
        clickOnElement(String.format(titlePost, postTitle));
        new PostPage(webDriver)
                .checkIsRedirectToPostPage();


        return new MyProfilePage(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("Post page is not loaded", isElementDisplayed(inputTitle));

        return new CreatePostPage(webDriver);
    }

    public CreatePostPage openCreatePostPage() {
        CreatePostPage createPostPage = new CreatePostPage(webDriver);
        return new CreatePostPage(webDriver);
    }

    public MyProfilePage checkEditedPostOneAndDisplayed(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        Assert.assertTrue("Edited post is not displayed",isElementDisplayed(String.format(titlePost, postTitle)));
        logger.info("Edited Post was displayed " + postTitle);

        listOfPosts = getPostsListWithTitle(postTitle);
        counter = 1;

        Assert.assertFalse("More than 1 post was edited",listOfPosts.size() > 1);
        return this;
    }


    public MyProfilePage checkUserIsDisplayedOnMyProfilePage(String expectUserName) {
        Assert.assertEquals("qaauto", expectUserName,myProfilePageUserName.getText());
        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts,postsList.size());
    }
}
