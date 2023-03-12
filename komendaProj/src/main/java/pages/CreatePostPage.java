package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
//  .//*[@name='title']
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy (xpath =".//button[@class='btn btn-primary']")
    private WebElement buttonSavePost;

//   './/select'
    @FindBy (tagName = "select")
    private WebElement dropDownOptions;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

    @FindBy(xpath = ".//input[@name = 'uniquePost']")
    private WebElement checkBoxPostUnique;



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
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String bodyText) {
        enterTextInToElement(inputBody, bodyText);
        return this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions(String valueInDD){
        selectValueInDropDown(dropDownOptions,valueInDD);
        return this;
    }

    public CreatePostPage selectTextInDropDownByUIOptions(String textInDD) {
        selectTextInDropDownByUI(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectCheckBoxState(String targetValue) {
        selectCheckBox(checkBox, targetValue);
        return this;
    }

    public CreatePostPage selectStatePostUnique(String state){
        selectCheckBox(checkBoxPostUnique, state);
        return this;
    }
}
