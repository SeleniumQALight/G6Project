package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends  ParentPage {

    @FindBy (xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy (xpath = ".//div[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    @FindBy (xpath = ".//div[@class='container py-md-5 container--narrow']//h2")
    private WebElement profileName;


    public MyProfilePage (WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeURL() {    // адреса моєї сторінки
        return "/profile/";
    }


    private String titlePost = ".//*[text()='%s']";  // використовується в String.format

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("MyProfilePage is not loaded", isElementDisplayed(avatar));
        return  this;
    }

    public List<WebElement> getPostListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));   // String.format додає title замість %s
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {          // метод для перевірки того що пост створено
        Assert.assertEquals("Number of posts with title", 1, getPostListWithTitle(postTitle).size());   // перевіряє що кількість постів (getPostListWithTitl....size) дорівнює 1
        return this;
    }

    public MyProfilePage deletePostWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostListWithTitle(postTitle);

        int counter = listOfPosts.size(); //змінна яку будемо викор. для обмеження ітерацій у разі помилки

        while (!listOfPosts.isEmpty() && counter>0) {           //вписуємо каунтер
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                 .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                 .checkIsRedirectToMyProfilePage()
                 .checkIsSuccessDeletedPostMessagePresent()
            ;
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostListWithTitle(postTitle);
            counter--;     //зменшуємо каунтер на 1
        }
        if (listOfPosts.size() == 0) {
            logger.info("All posts were deleted with title " + postTitle);
        } else {
            logger.error("Delete failed");
            Assert.fail("Delete failed");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletedPostMessagePresent() {
        Assert.assertTrue("Message delete Post is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    public MyProfilePage checkIsLoggedUserNameOnPage (String profileNameText) {
        Assert.assertEquals("", profileNameText, profileName.getText());
        return this;
    }
}
