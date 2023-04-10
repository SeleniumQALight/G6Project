package pages;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


//тут все наслідується від CommonActionsWithElement
//все що пишется в класі CommonActionsWithElement передається сюди, а потім наслідується в інші класи
abstract public class ParentPage extends CommonActionsWithElement{
    protected String base_url;
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url()
                .replace("[env]",System.getProperty("env", "aqa"));
    }
    protected void waitChatToBeHide(){
        webDriverWait10
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }
    abstract String getRelativeURL(); //всі хто будуть наслідуватись з цього класу будуть повертати Стрінг(вони будуть повертати відповідну урлу нашої пейджі)
    //метод по перевірці url

    /**
     * Example
     * google.com == google.com -> true
     */
    protected void checkURL(){
        Assert.assertEquals("Invalid page", base_url + getRelativeURL(),webDriver.getCurrentUrl());
    }

    /**
     * Example
     * google.com == google -> true
     */
    protected void checkURLContainsRelative(){
        Assert.assertThat("Invalid page", webDriver.getCurrentUrl(),
                CoreMatchers.containsString(base_url + getRelativeURL()));
    }
    //        "/post/[a-zA-Z0-9]*/edit";
    // https://qa-complexapp.onrender.com/post/.*/edit
    protected void checkURLWhitPattern(){
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual URL" + actualURL + "\n" + "Expected URL" + base_url + getRelativeURL() + "\n"
                , actualURL.matches(base_url + getRelativeURL()));
    }
}
