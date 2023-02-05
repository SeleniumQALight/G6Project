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
    @FindBy(xpath = "//div[@class='d-flex justify-content-between']/h2")
    private WebElement createdPostTitle;
    @FindBy(xpath = "//i")
    private WebElement noteOnCreatedPost;
    @FindBy(xpath = "//body//u")
    private WebElement underlinedTextInBody;

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

    public PostPage checkIfCreatedPostHasCorrectTitle(String title) {
        assertEquals("Text in title ", title, createdPostTitle.getText());
        return this;
    }

    public PostPage checkIfCreatedPostPageHasCorrectNote(String note) {
        assertTrue("Note is not on page", noteOnCreatedPost.getText().contains(note));
        return this;
    }

    public PostPage checkIfCreatedPostPageUnderLinedTextIs(String value) {
        assertEquals("Note is not on page", value, underlinedTextInBody.getText());
        return this;
    }
}
