package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

    @Step
    public boolean isButtonSignOutDisplayed(){
        try {
            return webDriver.findElement(
                    By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if(!isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();
        return this;
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());
        return this;
    }
    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
