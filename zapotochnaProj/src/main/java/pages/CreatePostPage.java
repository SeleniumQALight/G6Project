package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[@class = 'btn btn-primary']")
    private WebElement buttonSave;

    @FindBy(tagName = "select" )
    private WebElement dropDownOptions;


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
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {

        enterTextInToElement(inputTitle, postTitle);

        return this;
    }

    public CreatePostPage enterTextInInputBody(String postBody) {

        enterTextInToElement(inputBody, postBody);
        return this;
    }

    public PostPage clickOnSavePostButton() {

        clickOnElement(buttonSave);
        return  new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDropDown) { //працює із закритим дропдауном

        selectTextInDropDown(dropDownOptions, textInDropDown);
        return this;
    }
    public CreatePostPage selectValueInDropDownOptions (String valueInDD){ //працює із закритим дропдауном
        selectValueInDropDown(dropDownOptions, valueInDD);

        return this;

    }

//hw3:
    public CreatePostPage selectTextInDropDownByUI(String valueByUi) {
        selectValueByUiInDropDown(dropDownOptions, valueByUi);


        return null;
    }


    

}
