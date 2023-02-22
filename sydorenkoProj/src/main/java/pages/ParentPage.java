package pages;

import org.hamcrest.CoreMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.*;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String base_url;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url().replace("[env]", System.getProperty("env", "qa"));
    }

    protected void waitChatToBeHide() {
        webDriverWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();

    /**
     * Example
     * google.com == google.com -> true
     */
    protected void checkURl() {
        assertEquals("Invalid page", base_url + getRelativeURL(), webDriver.getCurrentUrl());
    }

    /**
     * Example
     * google.com == google -> true
     */
    protected void checkURLContainsRelative() {
        assertThat("Invalid page ", webDriver.getCurrentUrl(), CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    //    https://qa-complexapp.onrender.com/post/.*/edit
//     "/post/[a-zA-Z0-9]*/edit"; -> edit post page
    protected void checkURLWithPattern() {
        String actualURL = webDriver.getCurrentUrl();
        assertTrue("\nActual URL " + actualURL + "\n" + "Expected URL " + base_url + getRelativeURL() + "\n", actualURL.matches(base_url + getRelativeURL()));
    }
}
