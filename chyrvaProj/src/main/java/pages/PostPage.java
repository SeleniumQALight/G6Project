package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//*[contains(text(), \"One Person\")]")
    private WebElement textOnePerson;
    @FindBy(xpath = ".//div[@class = 'body-content' ]")
    private WebElement noteText;
    @FindBy(xpath = ".//div[@class = 'd-flex justify-content-between']")
    private WebElement createdTitle;
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

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
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));

        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage) {
        Assert.assertEquals("Text in success message element", expectMessage, successMessage.getText());
        return this;
    }

    public PostPage checkNewTitleIsDisplayed(String expectTitle) {
        Assert.assertEquals("POST_TITLE", expectTitle, createdTitle.getText());
        return this;
    }

    public PostPage checkIsNoteDisplayedOnPostPage(String expectNote) {
        Assert.assertEquals("Note: This post was written for One Person", expectNote, noteText.getText());

        return this;
    }

    public PostPage checkTextisDisplayedInBody(String expectOnePersonText) {
        Assert.assertEquals("One Person", expectOnePersonText, textOnePerson.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
