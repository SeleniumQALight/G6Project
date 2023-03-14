package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class EditPage extends ParentPage{

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveButton;

    @FindBy(name = "title")
    private WebElement titleInput;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElements headerElements = new HeaderElements(webDriver);


    public EditPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public EditPage checkIsRedirectedToEditPage() {
        waitChatToHide();
        Assert.assertTrue("Edit page was not opened", isElementDisplayed(saveButton));
        return this;
    }

    public EditPage editPostTitle(String postTitleEdited) {
        enterTextIntoElement(titleInput, postTitleEdited);
        return this;
    }

    public EditPage clickTheSaveUpdatesButton() {
        clickOnElement(saveButton);
        return this;
    }

    public EditPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Message does not correspond to expected", expectedMessage, successMessage.getText());
        return this;
    }
}
