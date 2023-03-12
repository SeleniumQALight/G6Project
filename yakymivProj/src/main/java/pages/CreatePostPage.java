package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;


    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    @FindBy(xpath = ".//input[@name = 'uniquePost']")
    private WebElement checkboxOfPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkUrl();
        waitChatToBeHide();
        Assert.assertTrue("CreatePostPage is not loaded",
                isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String post_title) {
        enterTextToElement(inputTitle, post_title);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String post_body) {
        enterTextToElement(inputBody, post_body);
        return this;
    }

    public CreatePostPage selectTextDropDownOptions(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectValueDropDownOptions(String value) {
        selectValueInDropDown(dropDownOptions, value);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUI(String text){
        selectTextInDropDownByUI(dropDownOptions,text);
        return this;
    }

    public PostPage clickSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreatePostPage setUpCheckBoxCreatePage (String state){
            setUpCheckBox(checkboxOfPost,state);
        return this;
    }


}
