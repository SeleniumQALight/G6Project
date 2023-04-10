package StepDefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class Registration_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @When("^User type '(.*)' login into 'Username' registration input on 'Login' page$")
    public void user_type_login_into_Username_registration_input_on_Login_page(String username) throws Throwable {
     loginPage.typeUserNameForRegistration(username);
    }

    @When("^User type '(.*)' email into 'Email' registration input on 'Login' page$")
    public void user_type_email_into_Email_registration_input_on_Login_page(String email) throws Throwable {
      loginPage.typeEmailForRegistration(email);
    }

    @When("^User type '(.*)' password into 'Password' registration input on 'Login' page$")
    public void user_type_password_into_Password_registration_input_on_Login_page(String password) throws Throwable {
       loginPage.typePasswordForRegistration(password);
    }

    @Then("^User sees alerts messages with text '(.*)'$")
    public void user_sees_alerts_messages_with_text_hello(String error) throws Throwable {
     loginPage.checkErrorMessageUniversal(error);
    }

}
