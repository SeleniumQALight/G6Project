package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage{
    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    private HeaderElements headerElements = new HeaderElements(webDriver);


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public PostPage checkIsRedirectToPostPage() {
        //todo check url

        Assert.assertTrue("Page Post page is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element ", expectedMessage, successMessage.getText());

        return this;
    }
}
