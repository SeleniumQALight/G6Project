package pages;

import elements.HeaderElement;
import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;



public class PostPage extends CreatePostPage {

    @FindBy(xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy(xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//h2")
    private WebElement title;

    @FindBy(xpath = ".//i")
    private WebElement labelText;

    private HeaderElement headerElement = new HeaderElement(webDriver);


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage is not loaded",
                isElementDisplayed(buttonEdit));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expectedMessage) {
        Assert.assertEquals("Text in success message element: ",
                expectedMessage, successMessage.getText());

        return this;
    }

    public PostPage checkTextInTitle(String expectedMessage) {
        Assert.assertEquals("Text in the title: ", expectedMessage, title.getText());
        return this;
    }
}

//    public PostPage checkTextInLabel(String expectedMessage) {
//        try {
//            if (selectTextInDropDownOptions(expectedMessage).equals(TestData.DROP_DOWN_OPTION_ONE)) {
//                Assert.assertEquals("Text in the label: ", expectedMessage, labelText.getText());
//                return this;
//            } else if (selectTextInDropDownUI(expectedMessage).equals(TestData.DROP_DOWN_OPTION_TWO)) {
//                Assert.assertEquals("Text in the label: ", expectedMessage, labelText.getText());
//                return this;
//            } else if (selectTextInDropDownUI(expectedMessage).equals(TestData.DROP_DOWN_OPTION_THREE)) {
//                Assert.assertEquals("Text in the label: ", expectedMessage, labelText.getText());
//                return this;
//            } else {
//                logger.info("Element was not found");
//                return this;
//            }
//
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//            return this;
//        }

//    }
//}




