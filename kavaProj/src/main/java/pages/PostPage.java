package pages;

import elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PostPage extends CreatePostPage {

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement title;

    @FindBy(xpath = ".//i")
    private WebElement labelText;

    @FindBy(xpath = ".//i/u")
    private WebElement usersText;

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;


    private HeaderElement headerElement = new HeaderElement(webDriver);


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @Override
    String getRelativeURL() {
        return "/post";
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded",
                isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element: ",
                expectedMessage, successMessage.getText());
        return this;
    }

    public PostPage checkTextInLabel() {
        Assert.assertTrue("Text in label: ", labelText.isDisplayed());
        return this;

    }

    public PostPage checkUsersText(String expectedMessage) {
        Assert.assertEquals("Text: ",
                expectedMessage, usersText.getText());
        return this;
    }

    public PostPage checkTextInTitle(String expectedMessage) {
        Assert.assertEquals("Text in the title: ", expectedMessage, title.getText());
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









