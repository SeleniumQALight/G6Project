package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
    @FindBy(xpath = ".//*[@class='btn btn-primary' and text()='Save Updates']")
    private WebElement saveUpdatedButton;

    @FindBy(xpath = ".//*[@name='title']")
    private WebElement titleInput;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center' and text()='Post successfully updated.']")
    private WebElement successAlert;

   private HeaderElement headerElement = new HeaderElement(webDriver);

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement(){
        return headerElement;
    }


    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkURLWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("EditPostPage is not opened", isElementDisplayed(saveUpdatedButton));
        return this;
    }

    public EditPostPage editPostTitle(String newPostTitle) {
        titleInput.clear();
        enterTextInToElement(titleInput, newPostTitle);
        clickOnElement(saveUpdatedButton);
        return this;
    }

    public EditPostPage checkMessagePostUpdated() {
        isElementDisplayed(successAlert);
        Assert.assertTrue("Post is not updated", isElementDisplayed(successAlert));
        return this;
    }
}
