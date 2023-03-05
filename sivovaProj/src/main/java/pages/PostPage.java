package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]") private WebElement editButton;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']") private WebElement successMessage;
    @FindBy(tagName = "h2") private WebElement createdPostTile;
    @FindBy(xpath = ".//div[@class ='body-content'][1]") private WebElement createdPostNote;
    @FindBy(xpath = ".//p[contains(text(), 'Is this post unique?')]") private WebElement uniqueMessage;



    private HeaderElements headerElement = new HeaderElements(webDriver);
    @FindBy (xpath = "//button[@class='delete-post-button text-danger']") private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    public HeaderElements getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(editButton));
        return this;
    }

    public PostPage checkCreatedPostTitle (String expectedTitle){
        Assert.assertEquals("Title is incorrect", expectedTitle, createdPostTile.getText() );
        return this;
    }

    public PostPage checkCreatedPostNote (String expectedNote){
        Assert.assertEquals("Note is incorrect", expectedNote, createdPostNote.getText() );
        return this;
    }

    public PostPage checkTextInSuccessMessage (String expectedMessage) {
        Assert.assertEquals("Text in success message element doesn't match "
                , expectedMessage, successMessage.getText());
        return this;
    }

    public MyProfilePage clickDeleteButton() {
        clickElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnEditButton() {
        clickElement(editButton);
        return new EditPostPage(webDriver);
    }

    public PostPage checkUniqueMessageDisplayed(String expectedMessage) {
        Assert.assertEquals("The post is not unique", expectedMessage, uniqueMessage.getText());
        return this;
    }


}
