package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    @FindBy(name="title")
private WebElement inputTitle;
    @FindBy(id="post-body")
    private WebElement inputPostBody;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement savePostButton;
    @FindBy(tagName = "select")
    private WebElement dropDown;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/create-post";
    }
    @Step
    public CreatePostPage checkIsRedirectToCreatePostPage() {
       // checkURL();
        checkURLContainsRelative();
        waitChatToBeHidden();
        Assert.assertTrue("CreatePostPage is not displayed", isElementDisplayed(inputTitle));
        return this;
    }
    @Step
    public CreatePostPage typePostTitle(String testTitle) {
        typeTextToElement(inputTitle ,testTitle);
        return this;
    }

    @Step
    public CreatePostPage typePostBody(String text){
        typeTextToElement(inputPostBody,text);
        return this;
    }
    @Step
    public CreatePostPage selectItemInDropDown(String textinDD){
        selectTextInDropDown(dropDown,textinDD);
        return this;
    }
    @Step
    public CreatePostPage selectItemInDropDownByUI(String textinDD){
        selectTextInDropDownByUi(dropDown,textinDD);
        return this;
    }
    @Step
    public CreatePostPage selectValueInDropDown(String value){
        selectValueInDropDown(dropDown, value);
        return this;
    }
    @Step
    public PostPage clickSavePostButton(){
        clickOnElement(savePostButton);
        return new PostPage( webDriver);
    }
}
