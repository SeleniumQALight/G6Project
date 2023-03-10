package suites;


import categoties.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postCreate.CreatePostTest;
import regisration.RegistrationErrorTestWithExel;

//@RunWith(Suite.class)
@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
///@Categories.ExcludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
       LoginTestWithPageObject.class,
        RegistrationErrorTestWithExel.class,
       CreatePostTest.class
})
public class SmokeTest {
}
