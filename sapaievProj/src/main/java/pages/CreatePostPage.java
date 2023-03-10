package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreatePostPage extends ParentPage{

    @FindBy(name="title")
    private WebElement inputTitle;


    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(xpath=".//button[@class='btn btn-primary']")
    private WebElement createButton;

    @FindBy(xpath="//div[@class='form-group']/select")
    private WebElement dropDownOptions;


    @FindBy(xpath="//input[@name='uniquePost']")
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


    public CreatePostPage operationWithCheckBox(String statusOfCheckbox){
        if (statusOfCheckbox.equalsIgnoreCase("check")){
            clickOnCheckBoxEnable(checkBox);
        }else if (statusOfCheckbox.equalsIgnoreCase("uncheck")) {
            clickOnCheckBoxDisable(checkBox);
        } else {
            logger.info("Please, select 'check' or 'uncheck'");
        }
        return this;
    }




}
