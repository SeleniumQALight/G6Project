package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class=\'alert alert-success text-center\']")
    private WebElement successMessage;

    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']//h2")
    private WebElement postTitle;

    @FindBy(xpath = ".//i[contains(text(),' Note: This post was written for ')]")
    private WebElement note;

    @FindBy(xpath = ".//i[contains(text(),' Note: This post was written for ')]//u")
    private WebElement dropDownValue;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("Post Page is not loaded",
                isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element ",
                expectedMessage, successMessage.getText());
        return this;
    }

    public PostPage checkTitleIsDisplayed(String expectedPostTitle) {
        Assert.assertTrue("Title is not displayed", isElementDisplayed(postTitle));
        Assert.assertEquals("Wrong title", expectedPostTitle, postTitle.getText());
        return this;
    }


    public PostPage checkNoteIsDisplayed() {
        Assert.assertTrue("Not is not displayed", isElementDisplayed(note));
        return this;
    }

    public PostPage checkSelectedValueIsDisplayed(String expectedValue) {
        Assert.assertTrue("Value is not displayed", isElementDisplayed(dropDownValue));
        Assert.assertEquals("Wrong value", expectedValue, dropDownValue.getText());
        return this;
    }
}
