package suits;


import suits.loginTest.LoginTestWithPageObject;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class) // ми вказали щоб ранити цей набір класів

@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class
})


public class SmokeSuite {



}
