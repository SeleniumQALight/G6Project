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

    @FindBy(xpath =".//button[@class='btn btn-primary']")
    private WebElement buttonSavePost;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    @FindBy(xpath = "//input[@name='uniquePost']")
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
        Assert.assertTrue("CreatePost is not loaded"
                , isElementDisplayed(inputTitle));
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
    public CreatePostPage selectValueInDropDownOptionsUI(String valueInDD){
        selectTextInDropDownByUI(dropDownOptions, valueInDD);
        return this;
    }

    public CreatePostPage addChangedTitle(String newTitle) {
        inputTitle.clear();
        enterTextInToElement(inputTitle, newTitle);
        return this;
    }

    public CreatePostPage selectCheckboxStateSelected(String state) {
        setCheckboxState(checkBox, state);
        return this;
    }
    public void checkCheckbox(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox is checked");
        } else {
            logger.info("Checkbox is already checked");
        }
    }
    public void uncheckCheckbox(WebElement checkbox) {
        if (checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was unchecked");
        } else {
            logger.info("Checkbox is already unchecked");
        }
    }
    public void setCheckboxState(WebElement checkbox, String checkBoxState) {
        if (checkBoxState.equalsIgnoreCase("Check")) {
            checkCheckbox(checkbox);
        } else if (checkBoxState.equalsIgnoreCase("Uncheck")) {
            uncheckCheckbox(checkbox);
        } else {
            logger.error("Incorrect state of checkbox");
        }
    }
}
