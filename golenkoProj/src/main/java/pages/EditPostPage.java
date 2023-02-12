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

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        Assert.assertTrue("EditPostPage is not opened",
                isElementDisplayed(saveUpdatedButton));
        return this;
    }

    public EditPostPage editPostTitle(String newTitle) {
//        clickOnElement(titleInput);
        titleInput.clear();
        enterTextIntoElement(titleInput, newTitle);
        clickOnElement(saveUpdatedButton);
        return this;
    }

    public EditPostPage checkMessagePostUpdated() {
        isElementDisplayed(successAlert);
        return this;
    }
}
