package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionsWithElements {
  protected String base_url ;


    public ParentPage(WebDriver webDriver) {
        super(webDriver);

        base_url = "https://[env]-complexapp.onrender.com".replace("[env]", System.getProperty("env", "qa"));
    }

    protected void waitChatToBeHide() {

        webDriverWait10
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();  //ми повинні знати зо у всіх Чайлдах повинен бути цей метод
    /**
    example
    google.com == google.com -> true
     */

    protected void checkURL(){
        Assert.assertEquals("Invalid PAGE URl  ",base_url + getRelativeURL() , webDriver.getCurrentUrl());
    }
    /**
     example
     google.com == google  -> true
     */
    protected void checkURLContainsRelative(){

        Assert.assertThat("Invalid PAGE URl  ", webDriver.getCurrentUrl(), CoreMatchers.containsString(base_url + getRelativeURL()));

    }

//        "/post/[a-zA-Z0-9]*/edit";
    protected void checkURLWithPattern(){

        String actualURL = webDriver.getCurrentUrl();
                Assert.assertTrue("\nActual URL " + actualURL + " \n "
                        + "Expected URL " + base_url+getRelativeURL() + " \n"
                        , actualURL.matches(base_url + getRelativeURL() )); //замість getrelative вказати урл яке потрібне
    }

}
