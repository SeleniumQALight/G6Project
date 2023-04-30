package pagesPrivat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
        protected WebDriver webDriver;
        protected String home_url_privat = "https://privatbank.ua/";

        public BasePage(WebDriver webDriver){
            this.webDriver = webDriver;
            PageFactory.initElements(webDriver, this);
        }
}
