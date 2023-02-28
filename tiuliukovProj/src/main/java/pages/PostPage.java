package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttonEdit;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = ".//div[@class='d-flex justify-content-between']")
    private WebElement actualTitle;
    @FindBy(xpath = ".//div[@class='body-content']//i")
    private WebElement actualLabel;
    @FindBy(xpath = ".//div[@class='body-content']//u")
    private WebElement actualOptionValue;
    @FindBy(xpath = ".//*[@class='body-content'][2]")
    private WebElement actualBodyContent;
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    private HeaderElement headerElement = new HeaderElement(webDriver);


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
        Assert.assertTrue("Post Page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectMessage){
        Assert.assertEquals("Text is success message element",expectMessage, successMessage.getText());
        return this;
    }

    public PostPage checkPostTitle (String expectTitle){
        Assert.assertEquals("Wrong post title",expectTitle, actualTitle.getText());
        return this;
    }

    public PostPage checkPostLabel(String expectLabel, String option){
        Assert.assertEquals("Wrong post lable",expectLabel + option, actualLabel.getText());
        return this;
    }

    public PostPage checkPostOptionValue (String expectOptionValue){
        Assert.assertEquals("Wrong post option value",expectOptionValue, actualOptionValue.getText());
        return this;
    }

    public PostPage checkBodyContent(String postBodyContent) {
        Assert.assertEquals("Wrong post option value",postBodyContent, actualBodyContent.getText());
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
