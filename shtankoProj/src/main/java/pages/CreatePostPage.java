package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//*[@class=\"btn btn-primary\"]")
    private WebElement buttonSavePost;

    //скорочений запис цього локатора './/select'
    @FindBy(tagName = "select")
    private WebElement dropDownOptions;
    @FindBy(xpath = ".//input[@name='uniquePost']")
    private WebElement checkboxUnique;

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
        Assert.assertTrue("Create post Page is not loaded"
                ,isElementDisplayed(inputTitle));
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
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;
    }
    public CreatePostPage selectTextInUIDropDownOptions(String valueInUIDD){
        selectTextInUIDropDown(dropDownOptions, valueInUIDD);
        return this;
    }
    public CreatePostPage clickOnCheckbox(String targetValue) {
        statusCheckbox(checkboxUnique, targetValue);
        return this;
    }


}
