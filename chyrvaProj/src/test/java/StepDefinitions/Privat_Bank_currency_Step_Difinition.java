package StepDefinitions;

import api.ApiPrivatHelper;
import api.dto.responseDto.ExchangeKursDto;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pages.PrivatHomePage;

import java.util.List;

public class Privat_Bank_currency_Step_Difinition {
Logger logger = Logger.getLogger(getClass());
    ApiPrivatHelper apiPrivatHelper = new ApiPrivatHelper();
    PrivatHomePage privatHomePage= new PrivatHomePage(DriverHelper.getWebDriver());
    List<ExchangeKursDto> CurrencyList = apiPrivatHelper.getCurrencyExchangeRate();
    @Given("^Get '(.*)' rate from API$")
    public void get_Currency_rate_from_API(String currency) throws Throwable {
        for (int i = 0; i < CurrencyList.size(); i++) {
            if (CurrencyList.get(i).getCcy().equalsIgnoreCase(currency)) {
                TestData.buyKursAPI = CurrencyList.get(i).getBuy();
                TestData.sellKursAPI = CurrencyList.get(i).getSale();
                logger.info(TestData.buyKursAPI + "," + TestData.sellKursAPI);

            }

        }


    }

    @When("^User opens Private bank 'HomePage' page$")
    public void userOpensPrivateBankHomePagePage() {
        privatHomePage.openHomePagePrivat();
        logger.info("Page was opened");
    }

    @And("^Get '(.*)' rate from UI$")
    public void getCurrencyRateFromUI(String currency) {
      TestData.buyKursUI = privatHomePage.getCurrencyBuyRate(currency);
        TestData.sellKursUI = privatHomePage.getCurrencySellRate(currency);
    }

    @Then("^User compare rate from UI and API$")
    public void userCompareRateFromUIAndAPI() {
        Assert.assertEquals("Rates doesn't match", TestData.buyKursAPI,TestData.buyKursUI);
        Assert.assertEquals("Rates doesn't match",TestData.sellKursAPI,TestData.sellKursUI);
        logger.info("Everything works!!!");
    }

}
