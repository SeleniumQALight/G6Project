package StepDefinitions;

import api.ApiHelper;
import api.ApiHelperPrivat;
import api.dto.PrivatDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.PrivatHomePage;

import java.util.List;

public class Privat_StepDefinitions {

    PrivatHomePage privatHomePage = new PrivatHomePage(DriverHelper.getWebDriver());
    Logger logger = Logger.getLogger(getClass());

    @Given("^User get rate '(.*)' from UI$")
    public void userGetRateCurrencyFromUI(String currency) {
        privatHomePage.openHomePage();
        TestData.buyRateFromUi = privatHomePage.getBuyRateCurrency(currency);
        TestData.sellRateFromUi = privatHomePage.getSellRateCurrency(currency);
    }

    @When("^User get rate '(.*)' , '(.*)' from API$")
    public void userGetRateBaseCurrencyCurrencyFromAPI(String baseCurrency, String currency) {
        List<PrivatDTO> listCurrencyPrivat = ApiHelperPrivat.getCurrencyFromAPI();
        for (PrivatDTO element : listCurrencyPrivat){
            if (element.getBase_ccy().equalsIgnoreCase(baseCurrency) && element.getCcy().equalsIgnoreCase(currency)){
                TestData.buyRateFromApi = element.getBuy();
                TestData.sellRateFromApi = element.getSale();
            }
        }
    }
    @Then("^User compering currency from UI and API '(.*)' , '(.*)'$")
            public void userComperingCurrencyFromUIAndAPI(String baseCurrency, String currency) {
        Assert.assertEquals("Buy rate is not equals from UI and API", TestData.buyRateFromUi, TestData.buyRateFromApi);
        Assert.assertEquals("Sell rate is not equals from UI and API", TestData.sellRateFromUi, TestData.sellRateFromApi);
    }
}
