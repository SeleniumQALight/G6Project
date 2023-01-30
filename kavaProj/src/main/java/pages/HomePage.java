package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
    private WebElement signOutButton;

    public void isSignOutButtonDisplayed() {
        isElementPresented(signOutButton);
    }


}
