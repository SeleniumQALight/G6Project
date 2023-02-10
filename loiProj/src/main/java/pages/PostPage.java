package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div/h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//i[text()=' Note: This post was written for ']")
    private WebElement postNote;

    @FindBy(xpath = ".//i/u")
    private WebElement postTypeOfAccessRight;

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage isn't loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element", expectedMessage, successMessage.getText());
        return this;
    }

    public PostPage checkIsTitleMatches(String expectedPostTitle) {
        Assert.assertEquals("Title doesn't match", expectedPostTitle, postTitle.getText());
        return this;
    }

    public PostPage checkIsNotePresent() {
        Assert.assertTrue("Post note doesn't display", isElementDisplayed(postNote));
        return this;
    }

    public PostPage checkIsAccessRightMatches(String expectedAccessRight) {
        Assert.assertEquals("Access right doesn't match", expectedAccessRight, postTypeOfAccessRight.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
