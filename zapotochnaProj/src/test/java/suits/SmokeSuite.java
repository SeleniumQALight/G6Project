package suits;


import apiTests.ApiTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Suite.class) // ми вказали щоб ранити цей набір класів

@Suite.SuiteClasses({
        CreatePostTest.class,

        ApiTests.class
})


public class SmokeSuite {



}
