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
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;
    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = "//div[@class='d-flex justify-content-between']/h2")
    private WebElement createdPostTitle;
    @FindBy(xpath = "//i")
    private WebElement noteOnCreatedPost;
    @FindBy(xpath = "//body//u")
    private WebElement underlinedTextInBody;
    @FindBy(xpath = "//p[text()='Is this post unique? : yes']")
    private WebElement uniquePostLabel;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
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

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public CreatePostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new CreatePostPage(webDriver);
    }

    public PostPage checkIsPostUnique() {
        assertTrue("Post is not unique", isElementDisplayed(uniquePostLabel));
        return this;
    }
}
