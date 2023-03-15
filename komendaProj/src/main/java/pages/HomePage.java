package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {

//    @FindBy(xpath = ".//button[text()='Sign Out']")
//    private WebElement buttonSignOut;

//    @FindBy(xpath = ".//*[@href='/create-post']")
//    private WebElement buttonCreatePost;

      private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }
    @Step
    public HeaderElement getHeaderElement() {
        return headerElement;
    }
    @Step
    public boolean isButtonSignOutDisplayed() {
        try {
            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver); // залогінитися
        loginPage.openLoginPage();
        if(!isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithValidCred(); // перевірити що ми на HomePage
        }
        checkIsRedirectToHomePage();
        return this;
    }
    @Step
    public HomePage checkIsRedirectToHomePage() {
        checkUrl();
        waitChatToBeHide();
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }
    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(headerElement.getButtonCreatePost());
        return new CreatePostPage(webDriver);
    }

}
