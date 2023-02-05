package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreatePostPage extends ParentPage {
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveButton;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    List<WebElement> dropDownMenu = webDriver.findElements(By.tagName("select"));

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        Assert.assertTrue("CreatePostPage is not loaded", isElementDisplayed(inputTitle));
        return this;

    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextIntoPostBody(String bodyText) {
        enterTextInToElement(inputBody, bodyText);
        return this;
    }


    public PostPage clickOnSavePostButton() {
        clickOnElement(saveButton);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDropDown) {
        selectTextInDropDown(dropDownOptions, textInDropDown);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions(String valueInDropDown) {
        selectValueInDropDown(dropDownOptions, valueInDropDown);
        return this;
    }

    public CreatePostPage selectTextInDropDownUI(String option) {
        selectOptionInDropDownUsingIteration(dropDownMenu, option);
        return this;
    }
}
