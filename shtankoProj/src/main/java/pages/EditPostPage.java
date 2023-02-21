package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(xpath = "//*[@class=\"btn btn-primary\"]")
    private WebElement buttonSaveUpdates;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = ".//*[@class=\"small font-weight-bold\"]")
    private WebElement buttonBackToPost;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }
    public EditPostPage enterEditTextInInputTitle(String postTitle){
        enterTextInToElement(inputTitle,postTitle);
        return this;
    }
    public EditPostPage clickOnSaveUpdateButton(){
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkTextInSuccessUpdatedMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element", expectedMessage,successMessage.getText());
        return this;
    }

    public PostPage clickOnBackPostButton() {
        clickOnElement(buttonBackToPost);
        return new PostPage(webDriver);
    }
}
