package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {


    private HeaderElement headerElement = new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/";
    }

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    WebElement successMessage;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']/h2")
    WebElement nameTitle;

    @FindBy(xpath = "//div[@class='body-content']/p/i[text()=' Note: This post was written for ']")
    WebElement textNote;


    @FindBy(xpath = "//div[@class='body-content']/p/i/u")
    WebElement textUnderline;



    @FindBy(xpath =".//button[@class='delete-post-button text-danger']")
    WebElement buttonDelete;




    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }


    public PostPage checkTextInSuccessMessage(String expectMessage) {
        String textSuccess = successMessage.getText();
        Assert.assertEquals("New post successfully created."
                , expectMessage, textSuccess);
        return this;
    }


    public HeaderElement getHeaderElement() {

        return headerElement;
    }


    public void setHeaderElement(HeaderElement headerElement) {

        this.headerElement = headerElement;
    }


    //3 задание . Проверка наличия тайтла
    public PostPage checkTitle(String title) {
        nameTitle.isDisplayed();
        Assert.assertEquals("Title is displayed", title, nameTitle.getText());
        return this;
    }

    public PostPage checkNoteDisplay() {
        textNote.isDisplayed();
        return this;
    }

    public PostPage checkUnderlineText(String text) {
        Assert.assertEquals("Underline text is displayed", text, textUnderline.getText());
        return this;
    }
    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
