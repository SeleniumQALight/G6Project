package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionsWithElements  {
    protected String base_url;


    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url().replace("[env]", System.getProperty("env", "aqa"))
                ;
    }

    protected void waiteChatToBeHide(){
        webDriverWait10.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath(".//*[@id = 'chat-wrapper']")));
    }

    abstract String getRelatedURL();

    /**
     * Example
     * google.com == google.com => true 
     */

    protected void checkURL () {
        Assert.assertEquals("Invalid page"
                , base_url +getRelatedURL()  , webDriver.getCurrentUrl());
    }

    /**
     *      * Example
         * google.com == google.com => true
   */ 

    protected void checkURL_ContainsRelative (){
        Assert.assertThat("",webDriver.getCurrentUrl()
                , CoreMatchers.containsString(base_url + getRelatedURL()));
    }

       protected void checkURLWithPattern (){
          String actuaURL = webDriver.getCurrentUrl();
          Assert.assertTrue("\nActual Url " + actuaURL +" \n" + "Expected URL" + base_url + getRelatedURL() + "\n"
                  , actuaURL.matches(base_url + getRelatedURL()));
       }
}
