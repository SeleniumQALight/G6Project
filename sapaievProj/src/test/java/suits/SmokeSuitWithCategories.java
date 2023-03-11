package suits;

import LoginTest.LoginTestWithPageObject;
import categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationErrorsTest;


@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationErrorsTest.class,
        CreatePostTest.class
})

public class SmokeSuitWithCategories {

}
