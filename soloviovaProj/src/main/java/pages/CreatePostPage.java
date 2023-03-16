package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    //.//input[@name=‘title'] shortcut.
    @FindBy(name = "title")
    private WebElement inputTitle;
    //.//textarea[@name=‘body']
    @FindBy(name = "body")
    private WebElement inputBody;
    //.//button[@class='btn btn-primary’]
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveNewPostButton;
    @FindBy(tagName = "select")
    private WebElement dropDownOptions;
    @FindBy(xpath = ".//option[@value='One Person']")
    private WebElement option;
    @FindBy(xpath = ".//input[@type='checkbox']")
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
        Assert.assertTrue("Create Post Page is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String body_title) {
        enterTextToElement(inputBody, body_title);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(saveNewPostButton);
        return new PostPage(webDriver);
    }


    public CreatePostPage selectTextInDropDownOptions(String textInDD) {
        selectTextInDropDown(dropDownOptions, textInDD);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions(String valueInDD) {
        selectValueFromDropDown(dropDownOptions, valueInDD);
        return this;
    }

    public CreatePostPage selectTextInDropDownWithUI() {
        clickOnElement(dropDownOptions);
        clickOnElement(option);
        return this;
    }

    public CreatePostPage selectCheckbox() {
        if(!checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("Checkbox is selected");
        }else{
            logger.info("Checkbox is already selected");
        }
        return this;
    }

    public CreatePostPage unSelectCheckbox(){
        if(checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("Checkbox is unchecked");
        }else{
            logger.info("Checkbox is already unchecked");
        }
        return this;
    }

    public CreatePostPage checkboxState(String state){
        if(state.equalsIgnoreCase("check")){
            selectCheckbox();
        }else if(state.equalsIgnoreCase("uncheck")){
            unSelectCheckbox();
        }else{
            logger.error(state + " is unknown command.");
            Assert.fail(state + " is unknown command.");
        }
        return this;
    }
}
