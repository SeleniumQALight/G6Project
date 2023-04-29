package privateBank;

import api.privateBankAPI.PBEndPoints;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PrivateBank {
    WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = ".//div[@data-cource_type = 'posts_course']//tbody//td[@id]")
    private List<WebElement> currencyList;

    public PrivateBank(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public List<WebElement> getCurrencyList() {
        return currencyList;
    }

    public void openPBWeb(){
        try{
            webDriver.get(PBEndPoints.pbWeb);
            logger.info("PB web is opened");
        }catch (Exception e){
            Assert.fail("Something when wrong " + e);
        }
    }
}
