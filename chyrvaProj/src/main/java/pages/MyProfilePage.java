package pages;

import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//h2[contains(text() ,'qaauto')]")
    private WebElement myProfilePageUserName;
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    private String titlePost = ".//*[text()='%s']";

//    public HeaderElement getHeaderElement() {
//        return headerElement;
//    }
//
//    private HeaderElement headerElement = new HeaderElement(webDriver);

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
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
                    .checkIsSuccessDeletePostMessagePresent()


            ;
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter --; //counter=counter-1
        }
        if (listOfPosts.size() == 0) {

            logger.info("All post were deleted with title" + postTitle);
        }else {
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {


        Assert.assertTrue("Message element", isElementDisplayed(successDeletePostMessage));


        return this;
    }


    public MyProfilePage checkUserIsDisplayedOnMyProfilePage(String expectUserName) {
        Assert.assertEquals("qaauto", expectUserName,myProfilePageUserName.getText());
        return this;
    }}
