package pages;

import elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends CreatePostPage {

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElement headerElement = new HeaderElement(webDriver);


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage is not loaded",
                isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element: ",
                expectedMessage, successMessage.getText());

        return this;
    }
}
