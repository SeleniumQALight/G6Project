package suits;


import categories.SmokeTestFilter;
import loginTest.LoginTestWhitPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationErrorTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWhitPageObject.class,
        RegistrationErrorTest.class,
        CreatePostTest.class
})
public class SmokeSuitsWithCategories {
}
