package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
    @FindBy(xpath = ".//*[@class='btn btn-primary' and text()='Save Updates']")
    private WebElement saveUpdatesButton;
    @FindBy(xpath = ".//*[@name='title']")
    private WebElement titleInput;
    @FindBy(xpath = ".//*[@class='alert alert-success text-center' and text()='Post successfully updated.']")
    private WebElement successMessage;
    public EditPostPage(WebDriver webDriver) { super(webDriver);}
    @Override
    String getRelativeURL() { return "/post/[a-zA-Z0-9]*/edit";}
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkURLWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("EditPostPage is not opened",
                isElementDisplayed(saveUpdatesButton));
        return this;
    }
    public EditPostPage enterNewPostTitle(String newPostTitle) {
        enterTextInToElement(titleInput, newPostTitle);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton (){
        clickOnElement(saveUpdatesButton);
        return this;
    }

    public EditPostPage checkMessagePostUpdated() {
        Assert.assertTrue("Post is not updated",
                isElementDisplayed(successMessage));
        return this;
    }

}
