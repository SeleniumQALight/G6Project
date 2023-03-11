package suits;

import LoginTest.LoginTestWithPageObject;
import RegistrationTest.RegistrationErrorTest;
import categories.SmokeTestFilter;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationErrorTest.class,
        CreatePostTest.class
})

public class SmokeSuitsWithCategories {

}