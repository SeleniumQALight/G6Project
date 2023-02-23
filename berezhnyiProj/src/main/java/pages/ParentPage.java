package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseURL;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseURL = configProperties.base_url().replace("[env]", System.getProperty("env", "qa"));
    }

    protected void waitChatToHide(){
        webDriverWait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
    abstract String getRelativeURL();

    /**
     * google.com == google.com -> true
     */
    protected void checkURL(){
        Assert.assertEquals("Invalid page is shown",baseURL + getRelativeURL(), webDriver.getCurrentUrl());
    }

    /**
     * google.com == google -> true
     */
    protected void checkURLContainsRelative(){
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl() , CoreMatchers.containsString(baseURL + getRelativeURL()) );
    }


    //https://qa-complexapp.onrender.com/post/63f0f73a8edc6f00345e869f/edit
    //https://qa-complexapp.onrender.com/post/.*/edit
    //        "/post/[a-zA-Z0-9]*/edit";
protected void checkURLWithPattern(){
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\n Actual URL" + actualURL + " \n"
                + "Expected url" + baseURL + getRelativeURL() + "\n", actualURL.matches(baseURL + getRelativeURL()));
}
}
