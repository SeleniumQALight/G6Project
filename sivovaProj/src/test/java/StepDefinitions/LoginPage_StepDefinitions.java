package StepDefinitions;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import pages.HomePage;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebdriver());

    @Given("^User opens 'Login' page$")
    public void user_opens_Login_page() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
            loginPage.openLoginPage();
    }

    @When("^User enters '(.*)' login into 'Login' input on 'Login' page$")
    public void user_enters_wrong_login_login_into_Login_input_on_Login_page(String userName) throws Throwable {
       loginPage.enterUserNameIntoInputLogin(userName);
    }

    @When("^User enters '(.*)' passWord into 'PassWord' input on 'Login' page$")
    public void user_enters_qwerty_passWord_into_PassWord_input_on_Login_page(String password) throws Throwable {
       loginPage.enterPasswordIntoInputPassword(password);
    }

    @When("^User click on 'SingIn' button on 'Login' page$")
    public void user_click_on_SingIn_button_on_Login_page() throws Throwable {
        loginPage.clickButtonLogin();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String expectedError) throws Throwable {
      loginPage.checkErrorMessageInvalidLogin(expectedError);
    }


    @When("^User enters '(.*)' username into 'Username' input on 'Login' page$")
    public void userEntersUsernameUsernameIntoUsernameInputOnLoginPage(String username) {
        loginPage.enterUserNameIntoInputSignUp(username);
    }


    @When("^User enters '(.*)' email into 'Email' input on 'Login' page$")
    public void userEntersEmailEmailIntoEmailInputOnLoginPage(String email) {
        loginPage.enterEmailIntoInputSignUp(email);
    }


    @When("^User enters '(.*)' password into 'Password' input on 'Login' page$")
    public void userEntersPasswordPasswordIntoPasswordInputOnLoginPage(String password) {
        loginPage.enterPasswordInputSignUp(password);
    }

    @Then("^User sees error messages '(.*)'$")
    public void userSeesErrorMessagesMessages(String errorMessages) {
        loginPage.checkErrorMessages(errorMessages);
    }
}
