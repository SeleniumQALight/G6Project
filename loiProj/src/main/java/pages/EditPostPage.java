package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage{
    @FindBy(id = "post-title")
    private WebElement inputPostTitle;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage enterTextInInputTitle(String titleText) {
        enterTextIntoElement(inputPostTitle, titleText);
        return this;
    }

    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }
}
