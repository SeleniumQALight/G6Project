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

    public MyProfilePage (WebDriver webDriver) {
        super(webDriver);
    }

    private String titlePost = ".//*[text()='%s']";  // використовується в String.format

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        //TODO check URL
        Assert.assertTrue("MyProfilePage ss not loaded", isElementDisplayed(avatar));
        return  this;
    }

    public List<WebElement> getPostListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));   // String.format додає title замість %s
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {          // метод для перевірки того що пост створено
        Assert.assertEquals("Number of posts with title", 1, getPostListWithTitle(postTitle).size());   // перевіряє що кількість постів (getPostListWithTitl....size) дорівнює 1
        return this;
    }
}
