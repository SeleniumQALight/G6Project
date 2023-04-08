package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;

import pages.LoginPage;
import pages.elements.HeaderElements;

public class LoginPage_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());
    HeaderElements headerElement = new HeaderElements(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.typeUserName(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_qwerty_passWord_into_PassWord_input_on_Login_page(String pass) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.typeUserPassword(pass);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.clickSignIn();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String expectedError) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        loginPage.checkAlertInCentre(expectedError);
    }
}
