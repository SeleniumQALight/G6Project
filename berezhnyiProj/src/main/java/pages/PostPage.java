package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']/h2")
    private WebElement createdPostTitle;

    @FindBy(xpath = ".//i")
    private WebElement noteTitle;

    @FindBy(xpath = ".//div[@class='body-content']/p/i/u")
    private WebElement privacyUnderlined;

    private HeaderElements headerElements = new HeaderElements(webDriver);



    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToHide();
        Assert.assertTrue("Page Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element ", expectedMessage, successMessage.getText());

        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public PostPage checkPostTitleIsDisplayed(String title) {
        Assert.assertEquals("Post title is not displayed", title ,createdPostTitle.getText() );
        return this;
    }

    public PostPage checkCorrectNoteIsDisplayed(String note) {
        Assert.assertTrue("Note is not displayed", noteTitle.getText().contains(note));
        return this;
    }

    public PostPage checkPrivacyUnderlinedTextIs(String privacyValue) {
        Assert.assertEquals("Underlined privacy option is not present", privacyValue, privacyUnderlined.getText());
        return this;
    }

    public EditPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new EditPage(webDriver);
    }
}
