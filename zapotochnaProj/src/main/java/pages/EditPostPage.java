package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class EditPostPage extends ParentPage{
@FindBy(name = "title")
private WebElement inputTitle;

@FindBy(id = "post-body")
private WebElement inputBody;

@FindBy(xpath = ".//button[@class = 'btn btn-primary']")
private WebElement buttonSave;

@FindBy(tagName = "select" )
private WebElement dropDownOptions;

@FindBy(xpath = ".//*[@class='text-primary mr-2']")
private  WebElement buttonEditPost;

@FindBy(xpath = ".//*[text()='« Back to post permalink']")
private WebElement buttonBackToPost;

@FindBy (xpath = ".//button[@class='btn btn-primary']")
private WebElement saveUpdatesButton;

@FindBy (xpath =  ".//*[text()='Post successfully updated.']")
private WebElement successUpdateMessage;
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public EditPostPage(WebDriver webDriver) {

        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }


    public EditPostPage checkIsRedirectToEditPostPage(){
        checkURLWithPattern();

        waitChatToBeHide();
    //   Assert.assertTrue("Edit Post Page is not loaded", isElementDisplayed(buttonBackToPost));
        return this;
    }


    public EditPostPage editTitle(String postTitleEdited) {

        inputTitle.clear();
        enterTextInToElement(inputTitle,postTitleEdited);
        return this;
    }


}

