package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement postTitle;

    @FindBy(xpath = ".//i")
    private WebElement note;

    @FindBy(xpath = ".//u")
    private WebElement selectedLabelForPost;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HeaderElements getHeaderElement() {
        return headerElements;
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }


    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element", expectedMessage, successMessage.getText());
        return this;
    }


    public PostPage checkPostTitle(String expected_title) {
        Assert.assertEquals("Text in created Post: ", expected_title, postTitle.getText());
        return this;
    }


    public PostPage checkPostNote(String expected_note) {
        Assert.assertTrue("Note is not present ", note.getText().contains(expected_note));
        return this;
    }

    public PostPage checkPostLabel(String expected_label) {
        Assert.assertEquals("Label in created Post: ", expected_label, selectedLabelForPost.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }
}
