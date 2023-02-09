package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{

    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }
    public boolean isButtonSingOutDisplayed(){
        try {
            return webDriver.findElement(
                    By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        //як що ми не залогінени то виконуй дії далі
        if (!isButtonSingOutDisplayed())
        {
        // залогінитись
        loginPage.fillingLoginFormWhitValidCred();
        }
        //перевірити що ми на HomePage
        checkIsRedirectToHomePage();
        return this;
    }
    public HomePage checkIsRedirectToHomePage(){
        Assert.assertTrue("HomePage is not loaded",isButtonSingOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
