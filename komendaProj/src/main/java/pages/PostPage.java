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
}
