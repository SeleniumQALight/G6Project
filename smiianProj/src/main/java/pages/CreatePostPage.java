package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy (name = "title")     //  == .//*[@name='title']
    private WebElement inputTitle;

    @FindBy (id = "post-body")
    private WebElement inputBody;

    @FindBy (xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSavePost;

    @FindBy (tagName = "select")    //          .//select
    private WebElement dropDownOptions;

    @FindBy (xpath = ".//select[@name='select1']")
    private WebElement createPostDropDown;

    @FindBy (xpath = ".//option[@value='One Person']")
    private WebElement createPostDropDownSecondOption;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }


    @Override
    String getRelativeURL() {       // сторінка створення поста
        return "/create-post";
    }


    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));

        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInBodyContent(String postBody) {
        enterTextIntoElement(inputBody, postBody);
        return this;
    }


    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOption(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);

        return this;
    }

    public CreatePostPage selectValueInDropDownOption(String valueInDD) {
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;
    }

    public CreatePostPage selectSecondTextInDropDownByUi() {
        selectTextInDropDownByUi(createPostDropDown, createPostDropDownSecondOption);
        return this;
    }



}
