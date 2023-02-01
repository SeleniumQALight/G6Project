package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

@FindBy(xpath = ".//button[@class=\"btn btn-sm btn-secondary\"]")
private WebElement signOutButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutDisplayed() {

//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
   return isElementDisplayed(signOutButton);
    }

}