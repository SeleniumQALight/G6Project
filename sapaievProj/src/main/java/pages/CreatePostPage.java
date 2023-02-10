package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    @FindBy(name="title")
    private WebElement inputTitle;


    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(xpath=".//button[@class='btn btn-primary']")
    private WebElement createButton;

    @FindBy(tagName="select")
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
        enterTextIntiElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInIputBody(String test_text) {
        enterTextIntiElement(inputBody, test_text);
        return this;
    }

    public  PostPage clickButtonCreatePost() {
         clickOnElement(createButton);
         return new PostPage(webDriver);

    }


    public CreatePostPage selectTextInDropdownOptions(String textInDD) {
        selectTextInDropdown(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectValueInDropdownOptions(String valueInDropdown) {
        selectValueInDropdown(dropDownOptions, valueInDropdown);
        return this;
    }



    public CreatePostPage selectTextInDropDownByUIOptions(String textInDropdown){
        selectTextInDropDownByUI(dropDownOptions,textInDropdown);
        return this;
    }

}
