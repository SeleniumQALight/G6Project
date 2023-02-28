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

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']/h2")
    private WebElement nameTitle;

    @FindBy(xpath = "//div[@class='body-content']/p/i[text()=' Note: This post was written for ']")
    private WebElement nameNote;
    
    @FindBy(xpath = "//div[@class='body-content']/p/i/u")
    private WebElement textUnderLine;
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

    public PostPage checkTextInSuccessMessage(String expectMessage){
        Assert.assertEquals("Text in success message element", expectMessage, successMessage.getText());
        return this;
    }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);

        return new MyProfilePage(webDriver);
    }

    public PostPage checkIsTitleDisplayed(String title) {
        nameTitle.isDisplayed();
        Assert.assertEquals("Title is displayed", title, nameTitle.getText()); // 1. messeage, 2.expected виводимо те що очікуємо. 3.actual
        return this;
    }

    public PostPage checkIsNoteDisplayed(String note) {
        Assert.assertTrue("Not is not displayed", isElementDisplayed(nameNote));
        nameNote.isDisplayed();
        return this;
    }


    public PostPage checkIsUnderLineText(String text) {
        Assert.assertEquals("Wrong value", text, textUnderLine.getText());
        return this;
    }

    public EditPostPage clickOnEditPostButton() {
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }
}
