package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage{
    @FindBy(name = "title" ) private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']") private WebElement inputBody;

    @FindBy(xpath = ".//button[@class='btn btn-primary']") private WebElement savePostButton;

    @FindBy(tagName="select") private WebElement dropDownPost;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkRedirectToCreatePostPage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed (inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle,postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String postBody) {
        enterTextIntoElement(inputBody,postBody);
        return this;
    }

    public PostPage clickSavePostButton(){
        clickElement(savePostButton);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectPostTypeInDropdown(String textInDD) {
        selectTextInDropDown(dropDownPost, textInDD);
        return this;
    }

    public CreatePostPage selectPostTypeInDropdownByUI(String targetValue) {
        selectTextInDropDownByUI(dropDownPost, targetValue);
        return this;
    }

    public CreatePostPage selectValueInDropdownOptions (String valueDD){
        selectValueInDropdown(dropDownPost, valueDD);
        return this;
    }

}
