package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']")
    private WebElement createPost;

    @FindBy(xpath = "//i")
    private WebElement notePost;

    @FindBy(xpath = "//body//u")
    private WebElement textUnderLine;

    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement updateMessage;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    public HeaderElement getHeaderElement(){
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage){
        assertEquals("Text in success message element", expectMessage, successMessage.getText());
        return this;
    }

    public PostPage checkTitleInCreatedPost(String title){
        assertEquals("Text in title", title, createPost.getText());
        return this;
    }

    public PostPage checkNoteInCreatedPost(String note) {
        assertTrue("Note is absent", notePost.getText().contains(note));
        return this;
    }

    public PostPage checkTextUnderLine(String text) {
        assertEquals("Text is absent", text, textUnderLine.getText());
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

    public PostPage checkTextInSuccessUpdateMessage(String textUpdateMessage) {
        assertEquals("Text is absent", textUpdateMessage, updateMessage.getText());
        return this;
    }
}

