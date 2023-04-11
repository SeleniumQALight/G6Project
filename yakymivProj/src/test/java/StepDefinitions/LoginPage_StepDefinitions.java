package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page() throws Throwable {
        loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) throws Throwable {
        loginPage.enterUserNameintoInputLogin(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_qwerty_passWord_into_PassWord_input_on_Login_page(String password) throws Throwable {
        loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() throws Throwable {
        loginPage.clickOnButtonLogin();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_password(String expectedText) throws Throwable {
        loginPage.checkAlertInCenter(expectedText);
    }

    @When("^User enters '(.*)' login into 'UserName' input on 'Login' page$")
    public void user_enters_login_into_UserName_input_on_Login_page(String userName) throws Throwable {
        loginPage.enterUserNameintoRegistrationForm(userName);
    }

    @When("^User enters '(.*)' email into 'Email' input on 'Login' page$")
    public void user_enters_email_into_Email_input_on_Login_page(String email) throws Throwable {
        loginPage.enterEmailInRegistrationForm(email);
    }

    @When("^User enters '(.*)' password into 'Password' input on 'Login' page$")
    public void user_enters_password_into_Password_input_on_Login_page(String pass) throws Throwable {
        loginPage.enterPasswordInRegistrationForm(pass);
    }

    @Then("^User sees error message with text '(.*)'$")
    public void user_sees_error_message_with_text(String expectedText) throws Throwable {
        loginPage.checkErrorMessage(expectedText);
    }
}