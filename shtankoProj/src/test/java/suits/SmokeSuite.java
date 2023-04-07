package suits;

import apiTests.ApiTests;
import loginTest.LoginTestWhitPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWhitPageObject.class,
        CreatePostTest.class,
        ApiTests.class
})
public class SmokeSuite {
}
