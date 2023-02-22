package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostEditPage extends ParentPage{

    public PostEditPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    @FindBy(name = "title")     //  == .//*[@name='title']
    private WebElement inputTitle;

    @FindBy (xpath = ".//button[@class='btn btn-primary' and contains(text(), 'Save Updates')]")
    private WebElement buttonSaveUpdates;

    @FindBy (xpath = ".//div[@class='alert alert-success text-center' and contains(text(), 'Post successfully updated.')]")
    private WebElement successMessage;

    @FindBy (xpath = ".//a[@class='small font-weight-bold']")
    private WebElement buttonBackToPost;


    private HeaderElement headerElement = new HeaderElement(webDriver);   //  оголошує елемент headerElement
    public HeaderElement getHeaderElement() {                             //  метод звернення до елемента headerElement
        return headerElement;
    }

    public PostEditPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public PostEditPage clickOnSaveUpdate() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public PostEditPage checkSuccessMessageIsVisible() {
        Assert.assertTrue("Message is not visible", isElementDisplayed(successMessage));
        return this;
    }


    public PostEditPage checkOnThePostEditPage() {
        Assert.assertTrue("PostEditPage is not loaded", isElementDisplayed(buttonBackToPost));
        return this;
    }
}
