package pages;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElements;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String base_url = "https://[env]-complexapp.onrender.com";

    private HeaderElements headerElements = new HeaderElements(webDriver);

    public HeaderElements getHeaderElement() {
        return headerElements;
    }

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
        base_url = configProperties.base_url().replace("[env]", System.getProperty("env","qa"));

    }

    protected void waitChatToBeHide(){
        webDriverWait10
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(".//*[@id='chat-wrapper']")));
    }

    abstract String getRelativeURL();

    protected void checkUrl(){
        Assert.assertEquals("Invalid Page", base_url + getRelativeURL(), webDriver.getCurrentUrl());
    }

    protected void checkURLContainsRelative(){
        Assert.assertThat("Invalid Page ",webDriver.getCurrentUrl(),
                CoreMatchers.containsString(base_url + getRelativeURL()));
    }

    protected void checkURLWithPattern(){
        String actualURL = webDriver.getCurrentUrl();
        Assert.assertTrue("\nActual URL " + actualURL + "\n" +
                        "Expected URL " + base_url + getRelativeURL() + "\n",
                actualURL.matches(base_url + getRelativeURL()));
    }
}
