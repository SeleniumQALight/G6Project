package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy (name = "body")
    private WebElement inputBody;
    @FindBy(tagName = "select")
    private WebElement dropDownOptions;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successfulMessage;
    @FindBy(xpath = ".//a[contains(@href, '/post')]")
    private WebElement backToPostPermalink;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage changePostTitleTo(String postTitleEdited) {
        inputTitle.clear();
        enterTextIntoElement(inputTitle, postTitleEdited);
        return this;
    }

    public EditPostPage changePostBodyTo(String postBodyEdited) {
        inputBody.clear();
        enterTextIntoElement(inputBody, postBodyEdited);
        return this;
    }

    public EditPostPage selectValueInDropDownOptions (String valueInDropDown){
        selectValueInDropDown(dropDownOptions, valueInDropDown);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public EditPostPage checkIsSuccessUpdateMessagePresent(String expectedLoginName) {
        Assert.assertEquals("Wrong message is displayed", expectedLoginName, successfulMessage.getText());
        return this;
    }

    public PostPage clickOnBackToPostPermalink (){
        clickOnElement(backToPostPermalink);
        return new PostPage(webDriver);
    }
}
