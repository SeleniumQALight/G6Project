package StepDefinitions;


import apiTest.ApiProject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;

import org.junit.Assert;
import pages.PPLoginPage;

import static java.lang.Math.abs;


public class Api_Project_StepDefinitions {
    ApiProject apiProject = new ApiProject();
    PPLoginPage ppLoginPage = new PPLoginPage(DriverHelper.getWebDriver());



    @Given("^User send API request to PrivatBank to receive and save the exchange rate for '(.*)'$")
    public void user_Send_API_Request_To_PrivatBank_To_Receive_And_Save_The_Exchange_Rate(String currencyName) {
        apiProject.getFromPrivatCurrencyValueAndSave(currencyName);
    }

    @When("^User opened Ui of PrivatBank to receive and save the exchange rate for '(.*)'$")
    public void user_Opened_Ui_Of_PrivatBank_To_Receive_And_Save_The_Exchange_Rate_For_Currency(String currencyName) {
        ppLoginPage.openPrivatBankPage();
        ppLoginPage.getUiCurrencyBuyValueAndSave(currencyName);
        ppLoginPage.getUiCurrencySellValueAndSave(currencyName);
    }

    @Then("^User compare saved strings$")
    public void user_Compare_Saved_Strings() {
        Assert.assertEquals("Buy values are not equal", TestData.apiCurrencyValueBuy, TestData.uiCurrencyValueBuy, 0);
        Assert.assertEquals("Sell values are not equal", TestData.apiCurrencyValueSell, TestData.uiCurrencyValueSell, 0);
    }
}
