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
    @FindBy(xpath = ".//div[@class = 'container py-md-5 container--narrow']//h2//img")
    private WebElement actualLoginName;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO checkURL
        Assert.assertTrue("MyProfilePage is not loaded"
                , isElementDisplayed(avatar));
        return this;
    }
    public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkIsLoginNameDisplayed(String expectedLoginName){
        Assert.assertEquals("Login name is wrong", expectedLoginName, actualLoginName.getText());
        return this;
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with number",1, getPostsListWithTitle(postTitle).size());
        return this;
    }
}
