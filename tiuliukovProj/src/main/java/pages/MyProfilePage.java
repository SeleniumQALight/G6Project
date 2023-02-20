package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;
    private String titlePost = ".//*[text() = '%s']";

    @FindBy(xpath = ".//h2")
    private WebElement actualLoginName;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
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
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return this;
    }

    public MyProfilePage checkIsDefaultNameDisplayed(String expectedLoginName) {
        Assert.assertEquals("Wrong user name is displayed", expectedLoginName, actualLoginName.getText());
        return this;
    }

    public List<WebElement> getPostsWistWithTitle(String title){
        return  webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostsWistWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsWistWithTitle(postTitle);
        int counter = listOfPosts.size();

        while (!listOfPosts.isEmpty() && counter > 0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsWistWithTitle(postTitle);
            counter--;
        }
        if (listOfPosts.isEmpty()){
            logger.info("All posts were deleted with title " + postTitle);
        } else {
            logger.info("Delete Fail");
            Assert.fail("Delete Fail");
        }

        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message deletePost is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }
}
