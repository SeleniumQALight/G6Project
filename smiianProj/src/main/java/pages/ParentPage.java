package pages;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


abstract public class ParentPage extends CommonActionWithElements{

    protected String base_url;

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url()                                       // змінює [env] енв через налаштування в Edit Configuration
                .replace("[env]", System.getProperty("env", "qa"));   // за замовчуванням qa
    }

    protected void waitChatToBeHide() {
        webDriverWait10
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();

    /**
     * Example
     * gooogle.com == gooogle.com -> true
     */
    protected void checkURL() {
        Assert.assertEquals("Invalid page", base_url + getRelativeURL() , webDriver.getCurrentUrl());
    }


    /**
     * Example
     * gooogle.com == gooogle -> true   //перевіряємо, чи містить URL шматочок gooogle
     */
    protected  void checkURLContainsRelative() {
        Assert.assertThat("Invalid page ",webDriver.getCurrentUrl() , CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    //                  "/post/[a-zA-Z0-9]*/edit";           use in edit post page
    protected void checkURLWithPattern() {
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("Actual URL " + actualURL + " \n"
                + "Expected URL " + base_url + getRelativeURL() + "\n", actualURL.matches(base_url + getRelativeURL()));
    }


}
