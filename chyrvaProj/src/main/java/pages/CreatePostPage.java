package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id= "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//*[@class = 'btn btn-primary']")
    private WebElement buttonSavePost;
    @FindBy(tagName = "select")
    private WebElement dropDownOptions;
    @FindBy(xpath = ".//*[@class = 'svg-inline--fa fa-edit fa-w-18']")
    private WebElement editIcon;
    @FindBy(xpath = ".//input[@type=\"checkbox\"]")
    private WebElement checkBox;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("CreatePostPage is not loaded ", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String bodyText) {

        enterTextInToElement(inputBody,bodyText);
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
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;

    }
    public CreatePostPage clickOnEditButton() {
        clickOnElement(editIcon);

        return new CreatePostPage(webDriver);
    }

    public CreatePostPage selectDesiredCheckBoxStatus(String checkBoxValue) {
        changeCheckBoxStatus(checkBox,checkBoxValue);
        return this;
    }

    public CreatePostPage enterTextInInputTitleDB(String bodyText) {

        enterTextInToElement(inputTitle,bodyText);
        return this;
    }
}
