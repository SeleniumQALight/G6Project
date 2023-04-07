package suits;

import categories.SmokeTestFilter;
import loginTest.LoginTestWIthPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registrationTest.RegistrationErrorTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class) // it is possible also to Exclude categorues
@Suite.SuiteClasses({
        LoginTestWIthPageObject.class,
        RegistrationErrorTest.class,
        CreatePostTest.class
})

public class SmokeSuiteWithCategories {
}
