package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//*[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']//h2")
    private WebElement successTitle;

    @FindBy(xpath = ".//*[contains(text(),'Note: This post was written for')]")
    private WebElement successNote;

    @FindBy(xpath = ".//div[@class='body-content']//u")
    private WebElement successValueFromDropdown;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getRelativeURL() {
        return "/post/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage){
        Assert.assertEquals("Text in success massage element ", expectMessage, successMessage.getText());
        return this;
    }
     public PostPage checkTitleOfCreatedPost(String expectTitle){
        Assert.assertEquals("Not expected title", expectTitle, successTitle.getText());
         return this;
     }

     public PostPage checkLabelNote(String expectNote){
         isElementDisplayed(successNote);
         Assert.assertEquals("Not expected note",expectNote, successNote.getText());
         return this;
     }

     public PostPage checkCorrectSelectedValueInDropdown(String selectedValueInDropdown){
         Assert.assertEquals("Note label is not displayed", selectedValueInDropdown, successValueFromDropdown.getText());
         return this;
     }

    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
    public EditPostPage clickOnEditButton(){
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }

}
