package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement titlePostPage;

    @FindBy(xpath = ".//*[text()=' Note: This post was written for ']")
    private WebElement notePostPage;

    @FindBy(xpath = ".//p//u")
    private WebElement bodyWithUnderline;

    @FindBy(xpath = ".//button[@data-toggle = 'tooltip'] ")
    private WebElement buttonDelete;

    private HeaderElement headerElement = new HeaderElement(webDriver);





    public HeaderElement getHeaderElement() {
        return headerElement;
    }



    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelatedURL() {
        return "/post/";
    }


    public PostPage checkIsRedirectToPostPage() {
        checkURL_ContainsRelative();
        waiteChatToBeHide();
        Assert.assertTrue("PostPage is not loaded",isElementDisplayed(buttonEdit));
return this;
    }
    public PostPage checkTextInSuccessMessage (String expectedMessage){
        Assert.assertEquals(""
                , expectedMessage, successMessage.getText());
        return this;
    }

    public PostPage checkisTitlePostDisplayed(){
        Assert.assertTrue("TitlePost is not disaplyed", isElementDisplayed(titlePostPage));
        return this;
    }

    public PostPage checkisNoteDisaplyed(){
        Assert.assertTrue("Note is not disaplyed", isElementDisplayed(notePostPage));
        return this;
    }

    public PostPage checkisBodyDisplayed(){
        Assert.assertTrue("Body is not disaplyed", isElementDisplayed(bodyWithUnderline));
        return this;
    }

    public PostPage checkisBodyEqualesToDDSelection(String expectedBodyValue){
        Assert.assertEquals (expectedBodyValue, bodyWithUnderline);
        return this;
    }


    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }

    public EditPostPage clickOnEditButton() {
        clickOnElement(buttonEdit);
        return new EditPostPage(webDriver);
    }
}
