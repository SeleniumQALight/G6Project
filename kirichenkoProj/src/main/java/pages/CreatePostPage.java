package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy (xpath = ".//button[text() = 'Save New Post']")
    private WebElement buttonSavePost;
    @FindBy (tagName = "select")
    private WebElement dropDownOptions;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("CreatePostPage is not loaded"
                , isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInToBody(String postBody) {
        enterTextInToElement(inputBody, postBody);
        return this;
    }
    public PostPage clickOnSaveButton(){
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);

        return this;
    }

    public CreatePostPage selectValueInDropDownOption (String valueInDD){
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;
    }

    public CreatePostPage selectTextInDropDownOptionByUI(String textInDropDown){
        selectTextInDropDownByUI(dropDownOptions, textInDropDown);
        return this;
    }
}
