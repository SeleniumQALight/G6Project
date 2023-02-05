package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage{
@FindBy(xpath = ".//*[@data-original-title=\"Edit\"]")
private WebElement  editButton;

@FindBy(xpath = ".//div[@class='alert alert-success text-center']")
private WebElement  successMessage;
private HeaderElements headerElements;
    public PostPage(WebDriver webDriver) {
        super(webDriver);
        this.headerElements = new HeaderElements(webDriver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public PostPage checkIsRedirectToPostPage() {

        //todo checkc url
        Assert.assertTrue("PostPage is not opened", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkValueInSuccessMessage(String expectedMessage) {


        Assert.assertEquals("Message does not match with expected",expectedMessage, successMessage.getText() );
        return this;
    }
}
