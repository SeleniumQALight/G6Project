package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libraries.DriverHelper;
import pages.LoginPage;


public class RegistrationPage_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page to register$")
    public void userOpensLoginPageToRegister() {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' username into registration input$")
    public void userEntersCredentialsUsernameIntoRegistrationInput(String userName) {
        loginPage.enterUserNameToRegister(userName);
    }

    @And("^User enters '(.*)' into email registration input$")
    public void userEntersEmailIntoEmailRegistrationInput(String email) {
        loginPage.enterEmailToRegister(email);
    }

    @And("^User enters '(.*)' into password registration input$")
    public void userEntersPasswordIntoPasswordRegistrationInput(String password) {
        loginPage.enterPasswordToRegister(password);
    }

    @Then("^User observes '(.*)' on 'Login' page$")
    public void userObservesErrorMessageOnLoginPage(String message) {
        loginPage.checkRegistrationErrorsMessages(message);
    }
}
