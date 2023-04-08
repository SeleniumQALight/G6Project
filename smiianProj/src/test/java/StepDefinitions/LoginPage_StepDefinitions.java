package StepDefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import libs.DriverHelper;
import libs.TestData;
import pages.LoginPage;

public class LoginPage_StepDefinitions {
    LoginPage loginPage = new LoginPage(DriverHelper.getWebDriver());

    @Given("^User open 'Login' page$")
    public void user_open_Login_page() throws Throwable {
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
        loginPage.clickOnButtonLogin();
    }

    @Then("^User sees alert message with text '(.*)'$")
    public void user_sees_alert_message_with_text_Invalid_username_pasword(String expectedText) throws Throwable {
        loginPage.checkAlertInCenter(expectedText);
    }




    @When("^User enters valid login into 'Login' input on 'Login' page$")
    public void user_Enters_Valid_Login_Into_Login_Input_On_Login_Page() {
        loginPage.enterUserNameIntoInputLogin(TestData.VALID_LIGIN);
    }

    @And("User enters valid password into '' input on {string} page")
    public void userEntersValidPasswordIntoPasswordInputOnLoginPage() {
    }
}
