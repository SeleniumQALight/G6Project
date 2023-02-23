package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;
import static org.junit.Assert.assertEquals;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement titleCreated;

    @FindBy(xpath = ".//i")
    private WebElement textNote;

    @FindBy(xpath = ".//u")
    private WebElement TextUnderLineFromDD;

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
        Assert.assertTrue("PostPage is not load ", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage) {
        Assert.assertEquals("Text in success message element ", expectMessage, successMessage.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkTitleCreatedPost(String postTitle) {
    Assert.assertEquals("This text in title is absent",postTitle , titleCreated.getText());
        return this;
    }

    public PostPage checkNoteIsDisplayedAfterCreatedPost(String note) {
        Assert.assertTrue("Note was not display", textNote.getText().contains(note) );
    return this;
    }

    public PostPage checkTextUnderLineChooseDropDown(String textUnderLine) {
        Assert.assertEquals("Text with under line was absent on page",textUnderLine ,TextUnderLineFromDD.getText());
        return this;
    }
}
