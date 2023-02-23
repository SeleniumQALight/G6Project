package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends CreatePostPage {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveButton;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }
}
