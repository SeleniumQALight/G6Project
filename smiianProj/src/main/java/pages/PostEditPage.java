package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostEditPage extends ParentPage{

    public PostEditPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(name = "title")     //  == .//*[@name='title']
    private WebElement inputTitle;

    @FindBy (xpath = ".//button[@class='btn btn-primary' and contains(text(), 'Save Updates')]")
    private WebElement buttonSaveUpdates;

    @FindBy (xpath = ".//div[@class='alert alert-success text-center' and contains(text(), 'Post successfully updated.')]")
    private WebElement successMessage;



    public PostEditPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public PostEditPage clickOnSaveUpdate() {
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    //  add method for checking success message


}
