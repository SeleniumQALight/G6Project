package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]") private WebElement editButton;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']") private WebElement successMessage;

    private HeaderElements headerElement = new HeaderElements(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO Check URL
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkTextInSuccessMessage (String expectedMessage) {
        Assert.assertEquals("Text in success message element doesn't match "
                , expectedMessage, successMessage.getText());
        return this;
    }
}
