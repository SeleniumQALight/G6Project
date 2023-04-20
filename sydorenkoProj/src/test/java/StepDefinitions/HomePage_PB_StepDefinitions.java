package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import pages.pb.HomePagePB;

public class HomePage_PB_StepDefinitions {
    HomePagePB homePagePB = new HomePagePB(DriverHelper.getWebDriver());

    @When("^Open the page of the PB$")
    public void openThePageOfThePB() {
        homePagePB.openHomePage();
    }

    @And("^Get the '(.*)'/'(.*)' exchange from the UI$")
    public void getTheCurrencyBaseCurrencyExchangeFromTheUI(String currency, String baseCurrency) {
        TestData.currencyPB.put(currency + baseCurrency + TestData.BUY_KEY_UI, homePagePB.getCurrencyBuy(currency));
        TestData.currencyPB.put(currency + baseCurrency + TestData.SELL_KEY_UI, homePagePB.getCurrencySell(currency));

    }
}