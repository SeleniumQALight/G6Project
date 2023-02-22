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

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    @FindBy(xpath = "//option[@value = 'One Person']")
    private WebElement textInElement;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage(){
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));
        return this;

    }
    public CreatePostPage enterTextInInputTitle(String postTitle){
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String postTitle){
        enterTextInToElement(inputBody, postTitle);
        return this;
    }

    public PostPage clickOnSavePostButton(){
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions(String valueInDD){
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;
    }

    public CreatePostPage selectElementInDropdownByUI(){
        selectTextInDropDownByUI(dropDownOptions, textInElement);
        return this;
    }


}
