package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {

    @FindBy(xpath = ".//a[text() = '« Back to post permalink']")
    private WebElement backToPostPermalink;

    @FindBy(xpath = ".//input[@name = 'title']")
    private WebElement titleInput;

    @FindBy(xpath = ".//button[@class= 'btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//div[@class= 'alert alert-success text-center']")
    private WebElement conformationMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public EditPostPage checkIsRedirectedToEditPostPage() {
        Assert.assertTrue("EditPostPage is not loaded", isElementDisplayed(backToPostPermalink));
        return this;
    }

    public EditPostPage enterTitle(String newTitle) {
        enterTextInToElement(titleInput, newTitle );
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new EditPostPage(webDriver);
    }

    public EditPostPage checkIsConformationMessageDisplayed() {
        isElementDisplayed(conformationMessage);
        return this;
    }
}


