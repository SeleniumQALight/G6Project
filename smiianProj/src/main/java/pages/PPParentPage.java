package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


abstract public class PPParentPage extends PPCommonActionWithElements {

    protected String base_url = "https://privatbank.ua";


    public PPParentPage(WebDriver webDriver) {
        super(webDriver);
    }


    abstract String getRelativeURL();


    /**
     * Example
     * gooogle.com == gooogle.com -> true
     */
    protected void checkURL() {
        webDriverWait10.until(ExpectedConditions.urlToBe(base_url + getRelativeURL()));
        Assert.assertEquals("Invalid page", base_url + getRelativeURL() , webDriver.getCurrentUrl());
    }



    /**
     * Example
     * gooogle.com == gooogle -> true   //перевіряємо, чи містить URL шматочок gooogle
     */
    protected void checkURLContainsRelative() {
        Assert.assertThat("Invalid page ",webDriver.getCurrentUrl() , CoreMatchers.containsString(base_url + getRelativeURL()));
    }


    protected void checkURLWithPattern() {
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("Actual URL " + actualURL + " \n"
                + "Expected URL " + base_url + getRelativeURL() + "\n", actualURL.matches(base_url + getRelativeURL()));
    }


}
