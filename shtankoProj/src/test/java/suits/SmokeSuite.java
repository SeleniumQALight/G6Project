package suits;

import loginTest.LoginTestWhitPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWhitPageObject.class,
        CreatePostTest.class
})
public class SmokeSuite {
}
