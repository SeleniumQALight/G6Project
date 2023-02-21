package pages;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


abstract public class ParentPage extends CommonActionsWithElements{

    protected String base_URL;


    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_URL= configProperties.base_url()
                .replace("[env]",System.getProperty("env","qa"));
    }


    protected void waitChatToBeHide(){
        webDriverWait10
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();

    /**
     * Example
     * gooogle.com==gooogle.com -> true
     */

    protected void checkURL(){
        Assert.assertEquals("Invalid page"
                ,base_URL+getRelativeURL(),webDriver.getCurrentUrl());
    }

    /**
     * Example
     * gooogle.com==gooogle -> true
     */

    protected void checkURLContainsRelative(){
        Assert.assertThat("",webDriver.getCurrentUrl()
                , CoreMatchers.containsString(base_URL+getRelativeURL()));
    }

    //        "/post/[a-zA-Z0-9]*/edit";
    protected void checkUrlWithPattern(){
        String actualURL=webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual URL"+actualURL+" \n"
                                  +"Expected URL" + base_URL+getRelativeURL()+"\n"
                                  , actualURL.matches(base_URL+getRelativeURL()));

    }




}
