package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


abstract public class ParentPage extends CommonActionsWithElements {
    protected String base_url = "https://qa-complexapp.onrender.com";
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    protected void waitChatToBeHide(){
        webDriverWait10
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();// возвращает стринг урла


    /**
     * Example
     *   google.com == google.com -> true
     */
    protected void checkURL(){
        Assert.assertEquals("Invalid page"
                , base_url + getRelativeURL(), webDriver.getCurrentUrl());
    }

    /**
     * Example
     * google.com == google -> true
     */

    protected void checkURLContainsRelative(){
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl()
                , CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    protected void checkURLWithPattern(){
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual URL " + actualURL + " \n"
                        + "Expected URL " + base_url + getRelativeURL() + "\n"
                , actualURL.matches(base_url + getRelativeURL()));
    }
}


//    protected WebDriver webDriver;
//    protected Logger logger = Logger.getLogger(getClass());
//
//    public ParentPage(WebDriver webDriver) {
//        this.webDriver = webDriver;
//        PageFactory.initElements(webDriver, this);


