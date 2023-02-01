package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {


    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;


    public HomePage(WebDriver webDriver) {

        super(webDriver);
    }




    public boolean isButtonSignOutDisplayed() {
            return isButtonDisplayed(buttonSignOut);
    }


    public boolean isButtonSignInDisplayed() {
        return isButtonDisplayed(buttonSignIn);
    }
}
