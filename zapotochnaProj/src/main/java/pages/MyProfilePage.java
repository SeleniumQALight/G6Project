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

    private String titlePost = ".//*[text() = '%s']"; //щоб знайти свій текст по тайтлу, описали локатор окремою змінною.

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }



    public MyProfilePage checkIsRedirectToMyProfilePage() {

        Assert.assertTrue("MyProfilePage" , isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement>getPostsListWithTittle(String title) {


        return webDriver.findElements(
                By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
Assert.assertEquals(" Number of posts with title ", 1,getPostsListWithTittle(postTitle).size());
        return this;
    }
}
