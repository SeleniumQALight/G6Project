package pages;

import io.qameta.allure.Step;
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

    @Override
    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    @Step
    public EditPostPage enterTextInInputTitle(String titleText) {
        enterTextIntoElement(inputPostTitle, titleText);
        return this;
    }

    @Step
    public PostPage clickOnSaveUpdatesButton() {
        clickOnElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }
}
