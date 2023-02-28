package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//*[@data-original-title='Edit']")
    private WebElement buttonEdit;

    @FindBy (xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = ".//div[@class = 'd-flex justify-content-between'] ")
    private WebElement actualTitle;
    @FindBy(xpath = ".//div[@class = 'body-content']//i")
    private WebElement actualLabel;
    @FindBy(xpath = ".//div[@class = 'body-content']//i//u")
    private WebElement actualOptionValue;

    private HeaderElement headerElement = new HeaderElement(webDriver);
    @FindBy(xpath = "//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
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

    public PostPage checkTextInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element"
                , expectedMessage, successMessage.getText());
        return this;
    }
    public PostPage checkPostTitle (String expectedTitle){
        Assert.assertEquals("Test title is wrong", expectedTitle, actualTitle.getText());
        return this;
    }

    public PostPage checkPostLabel (String expectedLabel, String option){
        Assert.assertEquals("Label is wrong", expectedLabel+option, actualLabel.getText());
        return this;
    }

    public PostPage checkPostOptionValue (String expectedOptionValue){
        Assert.assertEquals("Wrong option value", expectedOptionValue, actualOptionValue.getText());
        return this;
    }

    public MyProfilePage clickOnDeletButton() {
        clickOnElement(buttonDelete);
        return new MyProfilePage(webDriver);
    }
}
