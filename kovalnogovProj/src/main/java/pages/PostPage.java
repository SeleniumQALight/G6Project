package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage {
    public static final String UNDERLINE = "underline";
    @FindBy(xpath = ".//*[@data-original-title=\"Edit\"]")
    private WebElement editButton;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']//h2")
    private WebElement createdPostTitle;

    @FindBy(xpath = ".//i")
    private WebElement note;

    @FindBy(xpath = ".//i/u")
    private WebElement dropdownValue;

    private HeaderElements headerElements;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement deleteButton;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
        this.headerElements = new HeaderElements(webDriver);

    }

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }
    @Step
    public PostPage checkIsRedirectToPostPage() {
       // checkURL();
        checkURLContainsRelative();
        waitChatToBeHidden();
        Assert.assertTrue("PostPage is not opened", isElementDisplayed(editButton));
        return this;
    }
    @Step
    public PostPage checkValueInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Message does not match with expected", expectedMessage, getText(successMessage));
        return this;
    }
    @Step
    public PostPage checkIsPostTitlePresent() {
        Assert.assertTrue("Title is not visible", isElementDisplayed(createdPostTitle));
        return this;
    }
    @Step
    public PostPage checkIsCreatedPostTitleTextEqualWithExpected(String title) {
        Assert.assertEquals("Title name does not match with expected",title,getText(createdPostTitle));
        return this;
    }
    @Step
    public PostPage checkIsNoteTextEqualWithExpected(String title) {
        Assert.assertEquals("Title name does not match with expected",title,getText(note));
        return this;
    }
    @Step
    public PostPage checkIsNoteContainsUnderlineText() {
        Assert.assertTrue("Text property text decoration does not equal "+ UNDERLINE,
                getTextDecorationCssProperty(dropdownValue).contains(UNDERLINE));
        return this;
    }
    @Step
    public ProfilePage clickOnDeleteButton() {
        clickOnElement(deleteButton);
        return new ProfilePage(webDriver);
    }
    @Step
    public EditPostPage editPost() {
        clickOnElement(editButton);
        return new EditPostPage(webDriver);
    }
}
