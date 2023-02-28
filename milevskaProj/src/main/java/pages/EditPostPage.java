package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/post/.*/edit";
    }

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = "//a[@class = 'small font-weight-bold']")
    private WebElement backToPostLink;

    @FindBy(xpath = "//button[@class = 'btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//div[@class = 'alert alert-success text-center']")
    private WebElement successEditPostMessage;

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkIsSuccessMessageDisplays() {
        Assert.assertTrue("Message Post successfully updated is not displayed", isElementDisplayed(successEditPostMessage));
        return this;
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkURLWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("Edit page is not loaded", isElementDisplayed(backToPostLink));
        return this;
    }

    public EditPostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public EditPostPage editPostWithTitle(String editedPostTitle) {
        enterTextInInputTitle(editedPostTitle)
                .clickOnSaveUpdatesButton()
                .checkIsSuccessMessageDisplays();
        return this;
    }

}
