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

    @FindBy(xpath = ".//*[@class=\"d-flex justify-content-between\"]")
    private WebElement titleNewPost;
    @FindBy(xpath = ".//html/body/div[2]/div[3]/p/i/text()")
    private WebElement notePost;

    @FindBy(xpath = ".//html/body/div[2]/div[3]/p/i/u")
    private WebElement statusPost;

    //ми вказуємо що в нас ще є пов'язані єлементи в іншому класі
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement(){
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage is not loaded",
                isElementDisplayed(buttonEdit));
        return this;
    }

    //Як що текст повідомлення який ми очікуємо однаковий тест пройден, як що в тексті буде різниця то тест фейлд
    public PostPage checkTextInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element", expectedMessage, successMessage.getText());
        return this;
    }
    public PostPage checkTextInNewTitle(String expectedTitle){
        Assert.assertEquals("Text in success title element", expectedTitle, titleNewPost.getText());
        return this;
    }
    public PostPage checkNotePost(){
        Assert.assertFalse("Text is displayed",isElementDisplayed(notePost));
        return this;
    }

    public PostPage checkStatusPost(String expectedStatus){
        Assert.assertEquals("Text in success status element",expectedStatus,statusPost.getText());
        return this;
    }




}
