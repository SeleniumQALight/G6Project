package pages;

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

    public CreatePostPage checkIsRedirectToCreatePostPage() {
       // checkURL();
        checkURLContainsRelative();
        waitChatToBeHidden();
        Assert.assertTrue("CreatePostPage is not displayed", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage typePostTitle(String testTitle) {
        typeTextToElement(inputTitle ,testTitle);
        return this;
    }


    public CreatePostPage typePostBody(String text){
        typeTextToElement(inputPostBody,text);
        return this;
    }

    public CreatePostPage selectItemInDropDown(String textinDD){
        selectTextInDropDown(dropDown,textinDD);
        return this;
    }

    public CreatePostPage selectItemInDropDownByUI(String textinDD){
        selectTextInDropDownByUi(dropDown,textinDD);
        return this;
    }

    public CreatePostPage selectValueInDropDown(String value){
        selectValueInDropDown(dropDown, value);
        return this;
    }
    public PostPage clickSavePostButton(){
        clickOnElement(savePostButton);
        return new PostPage( webDriver);
    }
}
