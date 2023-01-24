package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage {
    protected Logger logger = Logger.getLogger( getClass() );
    protected WebDriver webDriver;


    public ParentPage( WebDriver webDriver ) {
        this.webDriver = webDriver;
    }


    private void typeText(String value ){
       // webDriver.findElement(  ).clear().;
    }
}
