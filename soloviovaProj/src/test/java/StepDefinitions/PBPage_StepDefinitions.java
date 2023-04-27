package StepDefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.DriverHelper;
import libraries.PBTestData;
import org.junit.Assert;
import privateBank.PrivateBank;



public class PBPage_StepDefinitions {
    PrivateBank privateBank = new PrivateBank(DriverHelper.getWebDriver());

    @When("^User opens 'Private Bank' website$")
    public void userOpensPrivateBankWebsite() {
        privateBank.openPBWeb();
    }

    @And("^User obtains 'buy' and 'sale' rates for '(.*)' via Web$")
    public void userObtainsBuyAndSaleRatesForCurrencyViaWeb(String currencyName) {
        String buy = "_buy";
        String sale = "_sell";
        for (int i = 0; i < privateBank.getCurrencyList().size(); i++) {
            if (privateBank.getCurrencyList().get(i).getAttribute("id").contains(currencyName+buy)) {
                PBTestData.buyUI = privateBank.getCurrencyList().get(i).getText();
            }
            if (privateBank.getCurrencyList().get(i).getAttribute("id").contains(currencyName+sale)) {
                PBTestData.saleUI = privateBank.getCurrencyList().get(i).getText();
            }
        }
    }

    @Then("User compares obtained 'buy' and 'sale' rate of 'currencies' from API and Web")
    public void userComparesObtainedBuyAndSaleRateOfCurrenciesFromAPIAndWeb() {
        Double buyUiDouble = Double.parseDouble(PBTestData.buyUI);
        Double saleUiDouble = Double.parseDouble(PBTestData.saleUI);
        Assert.assertEquals("Rate does not match ", PBTestData.buyAPI, buyUiDouble);
        Assert.assertEquals("Rate does not match ", PBTestData.saleAPI, saleUiDouble);
    }
}
