package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class CreatePostPage extends HeaderElement {
    @FindBy(name = "title") // it's the same as locator .//*[@name = 'title']
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInputTitle(String postTitle) {

        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInputBody(String bodytext) {
        enterTextInToElement(inputBody, bodytext);

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

    public CreatePostPage selectValueInDropDownOptions (String valueInDD){
        selectValueInDropDown(dropDownOptions, valueInDD);
        return this;
    }


}
