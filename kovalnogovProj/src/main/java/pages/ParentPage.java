package pages;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


abstract public class ParentPage extends CommonActionsWithElement {

    protected String baseURL;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        baseURL = configProperties.base_url().replace("[env]",System.getProperty("env","qa"));
    }

    protected void waitChatToBeHidden() {
        wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeUrl();

    /**
     *
     */
    protected void checkURL() {
        Assert.assertEquals("Invalid page url", baseURL +getRelativeUrl(), webDriver.getCurrentUrl());

    }

    protected void checkURLContainsRelative(){
        Assert.assertThat("Invalid page url",
                webDriver.getCurrentUrl() ,
                CoreMatchers.containsString(baseURL+getRelativeUrl())
        );
    }

    protected void checkURLWithPattern(){
        String actualURL= webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual url "+ actualURL+" \n"+ "Expected URL"+ baseURL+getRelativeUrl()+ " \n"
                ,actualURL.matches(baseURL+getRelativeUrl()));
    }

}
