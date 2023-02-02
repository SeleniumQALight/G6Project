package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    @FindBy(name="title")
    private WebElement inputTitle;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("CreatePostPage is not loaded"
                , isElementDisplayed(inputTitle));
        return this;
    }


    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextIntiElement(inputTitle, postTitle);
        return this;
    }
}
