package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = "//div[text()='Post successfully updated.']")
    private WebElement editPostMessage;


    @FindBy(name="title")
    private WebElement inputTitle;


    @FindBy(id="post-body")
    private WebElement inputBody;

    @FindBy(xpath=".//button[@class='btn btn-primary']")
    private WebElement saveButton;


    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public EditPostPage checkEditPostTitleMessage(){
        isElementDisplayed(editPostMessage);
        return this;
    }


    public EditPostPage clickOnSaveButton(){
        clickOnElement(saveButton);
        return this;
    }


    public EditPostPage enterTextInInputTitle(String postTitle) {
        enterTextIntiElement(inputTitle, postTitle);
        return this;
    }

    public EditPostPage enterTextInIputBody(String test_text) {
        enterTextIntiElement(inputBody, test_text);
        return this;
    }









}
