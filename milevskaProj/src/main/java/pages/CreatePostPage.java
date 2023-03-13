package pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.checkerframework.checker.units.qual.A;
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

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkBox;

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
    public CreatePostPage selectCheckbox() {
        if(!checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("Checkbox was selected");
        }else{
            logger.info("Checkbox is already selected");
        }
        return this;
    }

    public CreatePostPage unSelectCheckbox(){
        if(checkBox.isSelected()){
            clickOnElement(checkBox);
            logger.info("Checkbox was unchecked");
        }else{
            logger.info("Checkbox is already unchecked");
        }
        return this;
    }

    public CreatePostPage checkboxState(String checkboxState){
        if(checkboxState.equalsIgnoreCase("check")){
            selectCheckbox();
        }else if(checkboxState.equalsIgnoreCase("uncheck")) {
            unSelectCheckbox();
        }else{
            Assert.fail("Checkbox state check failed");
        }
        return this;
     }
}

