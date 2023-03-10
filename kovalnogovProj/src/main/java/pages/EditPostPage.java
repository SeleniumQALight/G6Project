package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(name="title")
    private WebElement inputTitle;
    @FindBy(id="post-body")
    private WebElement inputPostBody;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement savePostButton;
    @FindBy(xpath = ".//a[@class='small font-weight-bold']")
    private WebElement backToPostButton;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "Need to fix relative URL via regex";
    }
    @Step
    public EditPostPage typePostTitle(String testTitle) {
        typeTextToElement(inputTitle ,testTitle);
        return this;
    }
    @Step
    public EditPostPage typePostBody(String text){
        typeTextToElement(inputPostBody,text);
        return this;
    }
    @Step
    public EditPostPage checkValueInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Message does not match with expected", expectedMessage, getText(successMessage));
        return this;
    }
    @Step
    public EditPostPage clickSaveUpdates(){
     clickOnElement(savePostButton);
        return this;
    }
    @Step
    public PostPage redirectToCurrentPostPage(){
        clickOnElement(backToPostButton);
        return new PostPage(webDriver);
    }
}
