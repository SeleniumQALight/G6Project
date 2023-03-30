package suits;


import apiTests.ApiTests;
import loginTest.LoginTest;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationErrorsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        RegistrationErrorsTest.class,
        ApiTests.class
})

public class SmokeSuite {
}
