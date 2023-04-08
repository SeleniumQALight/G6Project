package pages;

import com.google.common.cache.AbstractCache;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy(xpath = ".//div[text()='Post successfully deleted']")
    private WebElement successDeletePosstMessage;


    private String titlePost=".//*[text()='%s']";



    @FindBy(xpath = "//span[@class='text-white mr-2']")
    private WebElement nameUserInProfile;


    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> poststList;


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


    // добавил проверку , что на странице профиля есть имя юзера, которым мы залогинились
    public MyProfilePage checkIsRedirectToMyProfilePageByName(String userName){
        Assert.assertEquals("MyProfilePage is not loaded", userName, nameUserInProfile.getText());
        return this;
    }



    public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public WebElement getPostWithTitle(String title){
        return webDriver.findElement(By.xpath(String.format(titlePost, title)));
    }



    public MyProfilePage checkPostWasCreated(String postTitle) {
       Assert.assertEquals("Number of posts with title",1,getPostsListWithTitle(postTitle).size());
        return this;
    }


    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listofPosts=getPostsListWithTitle(postTitle);
        int count=listofPosts.size();
        while (!listofPosts.isEmpty() && count>0){
            clickOnElement(String.format(titlePost, postTitle));
            new  PostPage((webDriver))
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();
            logger.info("Post was deleted with title " + postTitle);
            listofPosts=getPostsListWithTitle(postTitle);
            count--;
        }

        if(listofPosts.size()==0) {
            logger.info("All posts were deleted with title");
        }else {
            logger.error("Delete is failed");
            Assert.fail("Delete is failed");
        }
        return this;
    }



    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Message delete Post is not Displayed ",isElementDisplayed(successDeletePosstMessage));
        return this;
    }


    public EditPostPage editPostsWithTitle(String post_title, String new_postTitle) {
        WebElement post=getPostWithTitle(post_title);
        clickOnElement(post);
        new  PostPage((webDriver))
                .checkIsRedirectToPostPage()
                .clickOnEditButton()
                .enterTextInInputTitle(new_postTitle)
                .clickOnSaveButton();
                //.checkEditPostTitleMessage()
                //.getHeaderElement()
                //.clickOnMyProfileButton();
                //.checkSizeListOfEditedPost(new_postTitle);
        return new EditPostPage(webDriver);
    }







    public  MyProfilePage checkSizeListOfEditedPost(String newtitlePost){
        List<WebElement> listPosts=webDriver.findElements(By.xpath(String.format(titlePost, newtitlePost)));
        Assert.assertTrue("Size of edited post is 1",listPosts.size()==1);
        return this;
    }



    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts", expectedNumberOfPosts, poststList.size() );
    }
}
