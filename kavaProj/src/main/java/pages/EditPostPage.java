package pages;

import elements.HeaderElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends CreatePostPage {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//button[@class='btn btn-primary']")
    private WebElement saveUpdates;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement updatesSuccessful;


    private HeaderElement headerElement = new HeaderElement(webDriver);


    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EditPostPage enterTextInInputTitle(String postTitle) {
        enterTextInToElement(inputTitle, postTitle);
        return this;
    }

    public EditPostPage clickOnSaveUpdatesButton() {
        clickOnElement(saveUpdates);
        return this;
    }

    public EditPostPage checkIfPostWasUpdated(String expectedMessage) {
        Assert.assertEquals("Text in message: ", expectedMessage, updatesSuccessful.getText());
        return this;
    }



    public HeaderElement getHeaderElement() {
        return headerElement;
    }


}
