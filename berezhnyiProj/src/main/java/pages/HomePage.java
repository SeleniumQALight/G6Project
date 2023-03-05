package pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.elements.HeaderElements;

public class HomePage extends ParentPage{

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    //now on Home page we have header with buttons
    private HeaderElements headerElements = new HeaderElements(webDriver);

    @FindBy(xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HeaderElements getHeaderElements() {
        return headerElements;
    }

    public boolean isButtonSignOutDisplayed(){
        try {
            //webDriverWait15.until(ExpectedConditions.visibilityOf(buttonSignOut));
            return isElementDisplayed(buttonSignOut);
        }catch (Exception e){
            return false;
        }

    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutDisplayed()) {
            loginPage.fillingLoginFormWithValidCred();
        }
        checkIsRedirectToHomePage();

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkURL();
        waitChatToHide();
        Assert.assertTrue("HomePage is not loaded", isButtonSignOutDisplayed());

        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

//    public boolean isButtonSignOutDisplayed(){
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        }catch (Exception e){
//            return false;
//        }
//    }

}
