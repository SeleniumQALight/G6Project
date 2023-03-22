package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPage extends ParentPage{

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = ".//input[@name = 'title']")
    private WebElement namePostTitle;

    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement successesUpdatedMessage;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/.*/edit";
    }

    public EditPage checkIsRedirectToEditPage() {
        checkURLWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("Edit page is not opened", isElementDisplayed(buttonSaveUpdates));
        return this;
    }

    public EditPage editPostTitle(String newPostTitle) {
        enterTextIntoElement(namePostTitle, newPostTitle);
        return this;
    }

    public EditPage clickOnButtonSaveUpdates() {
       clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPage checkSuccessesUpdatedMessageDisplayed(String updatedMessage) {
        Assert.assertEquals("Successes message is not displayed",updatedMessage,successesUpdatedMessage.getText());
        return this;
    }
}
