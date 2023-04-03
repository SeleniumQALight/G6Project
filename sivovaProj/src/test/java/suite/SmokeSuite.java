package suite;


import apiTest.ApiTest;
import com.google.protobuf.Api;
import loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class,
        ApiTest.class
})

public class SmokeSuite {
}
