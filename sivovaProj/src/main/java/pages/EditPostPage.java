package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends CreatePostPage {
    @FindBy(id = "post-title")
    private WebElement postTitle;
    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }
    public PostPage modifyPostTitle(String newTitle) {
        postTitle.clear();
        enterTextInInputTitle(newTitle);
        clickElement(buttonSaveUpdates);
        return new PostPage(webDriver);
    }

}
