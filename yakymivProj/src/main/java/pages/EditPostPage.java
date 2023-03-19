package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath =  ".//a[@class='small font-weight-bold']")
    private WebElement permalinkOnEditPage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPage() {
        checkURLWithPattern();
        waitChatToBeHide();
        Assert.assertTrue("EditPage is not loaded", isElementDisplayed(permalinkOnEditPage));
        return this;
    }


    public EditPostPage editTitleOfCreatedPost(String title_edited) {
        enterTextToElement(inputTitle, title_edited);
        return this;
    }

    public PostPage clickSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }
}
