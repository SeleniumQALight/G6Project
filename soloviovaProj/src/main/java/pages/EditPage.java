package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class EditPage extends ParentPage {

    @FindBy(xpath = ".//button[@class='btn btn-primary' and contains(text(), 'Save Updates')]")
    private WebElement saveUpdates;
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = ".//*[text()='Post successfully updated.']")
    private WebElement updateMessage;

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public EditPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public EditPage checkIsRedirectToEditPage() {
        Assert.assertTrue("Edit page is not loaded", isElementDisplayed(saveUpdates));
        return this;
    }

    public EditPage postTitleEdit(String postTitle) {
        enterTextToElement(inputTitle, postTitle);
        return this;
    }

    public EditPage saveUpdatedTitle() {
        clickOnElement(saveUpdates);
        return this;
    }

    public EditPage checkSaccessUpdateMessage(String updateSuccessMessage) {
        forTextComparing(updateSuccessMessage, updateMessage);
        return this;
    }

}
