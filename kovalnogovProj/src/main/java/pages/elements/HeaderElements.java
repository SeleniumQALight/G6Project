package pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElement;
import pages.ProfilePage;

public class HeaderElements extends CommonActionsWithElement {
@FindBy(xpath = ".//*[@data-original-title='My Profile']")
private WebElement profileButton;


    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    public ProfilePage clickOnProfileButton(){
        clickOnElement(profileButton);
        return new ProfilePage(webDriver);
    }
}
