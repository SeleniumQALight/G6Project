package pages;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionsWithElements{
    protected String base_url;
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = "https://[env]-complexapp.onrender.com"
                .replace("[env]", System.getProperty("env", "qa"));
    }
    protected void waitChatToBeHide(){
        webDriverWait10
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();
    protected void checkURL(){
        Assert.assertEquals("Invalid page", base_url + getRelativeURL() , webDriver.getCurrentUrl());
    }

    protected void checkURLContainsRelative (){
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(), CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    protected void checkURLWithPattern(){
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\n Actual URl: " + actualURL + "\n"
                        + "Expected URL: " + base_url + getRelativeURL() + "\n"
                ,  actualURL.matches(base_url + getRelativeURL()));
    }
}
