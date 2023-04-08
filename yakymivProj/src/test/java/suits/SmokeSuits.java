package suits;

import LoginTest.LoginTestWithPageObject;

import apiTests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})

public class SmokeSuits {

}