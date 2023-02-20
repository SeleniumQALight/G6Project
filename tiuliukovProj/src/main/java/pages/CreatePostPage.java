package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy (xpath = ".//textarea[@name='body']")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveNewPost;
    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkURL();
        Assert.assertTrue("Create Post page is not loaded", isElementDisplayed(inputTitle));
        waitChatToBeHide();
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String postBody) {
        enterTextIntoElement(inputBody, postBody);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOption(String textInDropDown) {
        selectTextInDropDown(dropDownOptions, textInDropDown);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions (String valueInDropDown){
        selectValueInDropDown(dropDownOptions, valueInDropDown);
        return this;
    }

    public CreatePostPage selectTextInDropDownOptionByUI(String textInDropDown) {
        selectTextInDropDownByUI(dropDownOptions, textInDropDown);
        return this;
    }
}
