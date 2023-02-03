package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage{


    private HeaderElement headerElement=new HeaderElement(webDriver);

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath =".//a[@data-original-title=\"Edit\"]")
    WebElement buttonEdit;

    @FindBy(xpath =".//div[@class='alert alert-success text-center']")
    WebElement successMessage;


    public PostPage checkIsRedirectToPostPage() {
        //TODO check URL
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }


    public PostPage checkTextInSuccessMessage(String expectMessage){
       String textSuccess=successMessage.getText();
       Assert.assertEquals("New post successfully created."
               , expectMessage,textSuccess);
        return this;
    }


    public HeaderElement getHeaderElement() {
        return headerElement;
    }


    public void setHeaderElement(HeaderElement headerElement) {
        this.headerElement = headerElement;
    }

}
