package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

//    public boolean isButtonSignOutDisplayed() {
//        try {
//            return webDriver.findElement(By.xpath(".//button[text()='Sign Out']")).isDisplayed();
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver); // залогінитися
        if(!headerElement.isButtonSignOutDisplayed()){
            loginPage.fillingLoginFormWithValidCred(); // перевірити що ми на HomePage
        }
        checkIsRedirectToHomePage();
        return this;
    }

//        public HomePage openHomePage() {
//        LoginPage loginPage = new LoginPage(webDriver); // залогінитися
//        loginPage.fillingLoginFormWithValidCred(); // перевірити що ми на HomePage
//        checkIsRedirectToHomePage();
//        return this;
//    }

    public HomePage checkIsRedirectToHomePage() {
        Assert.assertTrue("HomePage is not loaded", headerElement.isButtonSignOutDisplayed());
        return this;
    }

    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(headerElement.getButtonCreatePost());
        return new CreatePostPage(webDriver);
    }

    public HeaderElement getHeaderElement() {
          return headerElement;
    }

}
