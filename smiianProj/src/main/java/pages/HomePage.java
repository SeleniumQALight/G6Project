package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class HomePage extends ParentPage {
//    @FindBy(xpath = ".//*[@href='/create-post']")
//    private WebElement buttonCreatePost;
    private HeaderElement headerElement = new HeaderElement(webDriver);

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return headerElement;
    }

//    @FindBy(xpath = ".//button[@class='btn btn-sm btn-secondary']")
//    private WebElement buttonLogOut;



//    public boolean isButtonSignOutDisplayed(){
//
//        return isObjectDisplayed(buttonLogOut);
//    }

    public HomePage openHomePage() {                                        // 01
        LoginPage loginPage = new LoginPage(webDriver);

        //залогінитись
        loginPage.fillingLoginFormWithValidCred();

        //перевірити що ми знаходимось на HomePage
        checkIsRedirectToHomePage();

        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", HeaderElement.isButtonSignOutDisplayed());

        return this;
    }

//    public CreatePostPage clickOnCreatePostButton() {
//        clickOnElement(buttonCreatePost);
//
//        return new CreatePostPage(webDriver);
//    }
}
