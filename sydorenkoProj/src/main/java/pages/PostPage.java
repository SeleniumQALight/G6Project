package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//a[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
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
        assertTrue("PostPage isn't loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage) {
        assertEquals("Text in success message element ", expectMessage, successMessage.getText());
        return this;
    }
}
