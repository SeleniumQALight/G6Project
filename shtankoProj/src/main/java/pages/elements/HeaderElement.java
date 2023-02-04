package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElement;
import pages.MyProfilePage;


//Це клас який буде описувати тільки хедр який є на змінний на всіх пейджах
public class HeaderElement extends CommonActionsWithElement {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;
    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    //метод який буде клікати на цю кнопку
    //Як що в методі є return то треба указати шо ми повертаємо сторінку або щось інше...
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

}
