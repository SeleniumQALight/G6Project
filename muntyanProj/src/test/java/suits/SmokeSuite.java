package suits;

import loginTest.LoginTestWIthPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWIthPageObject.class,
        CreatePostTest.class
})

public class SmokeSuite {
}
