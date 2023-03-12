package suits;


import categories.SmokeTestFilter;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationErrors;


@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)  //  Include включаються в сьют (Exclude - виключає)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationErrors.class,
        CreatePostTest.class
})
public class SmokeSuiteWithCategory {
}
