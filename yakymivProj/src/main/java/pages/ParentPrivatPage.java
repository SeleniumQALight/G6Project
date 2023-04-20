package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

abstract public class ParentPrivatPage {
    protected String base_url = "https://privatbank.ua/";
    protected WebDriver webDriver;


    public ParentPrivatPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

}
