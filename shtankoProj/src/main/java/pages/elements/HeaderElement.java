package pages.elements;


import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CommonActionsWithElement;
import pages.CreatePostPage;
import pages.LoginPage;
import pages.MyProfilePage;


//Це клас який буде описувати тільки хедр який є на змінний на всіх пейджах
public class HeaderElement extends CommonActionsWithElement {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = "//*[@class=\"text-white mr-2\"]")
    private WebElement userLoginName;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSingOut;
    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    //метод який буде клікати на цю кнопку
    //Як що в методі є return то треба указати шо ми повертаємо сторінку або щось інше...

    @Step
    public MyProfilePage clickOnMyProfileButton() {
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSingOutDisplayed() {
        return isElementDisplayed(buttonSingOut);
    }

    @Step
    public LoginPage clickButtonSingOut() {
        clickOnElement(buttonSingOut);
        return new LoginPage(webDriver);
    }

    @Step
    public void checkButtonSingOutIsDisplayed(){
        Assert.assertTrue(buttonSingOut + "Button is not displayed",isElementDisplayed(buttonSingOut));
    }

    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
}
