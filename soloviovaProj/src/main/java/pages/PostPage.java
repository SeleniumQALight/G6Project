package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElements;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//a[@data-original-title='Edit']")
    private WebElement buttnEdit;
    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;
    @FindBy(xpath = ".//h2")
    private WebElement titleText;
    @FindBy(xpath = ".//i[contains(text(),' Note: This post was written for ')]")
    private WebElement postLabel;
    @FindBy(xpath = ".//p//u")
    private WebElement labelValue;

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage is not load", isElementDisplayed(buttnEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        forTextComparing(expectedMessage, successMessage);
        return this;
    }

    public PostPage checkTextInTitleElement(String expectedTitle) {
        forTextComparing(expectedTitle, titleText);
        return this;
    }

    public PostPage checkIsLabelPresent() {
        Assert.assertTrue("Not is not found", isElementDisplayed(postLabel));
        return this;
    }

    public PostPage checkLabelValue(String expectedLabelValue) {
        forTextComparing(expectedLabelValue, labelValue);
        return this;
    }
}
