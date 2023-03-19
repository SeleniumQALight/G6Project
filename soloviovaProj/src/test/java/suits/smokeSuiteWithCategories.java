package suits;

import categories.SmokeTestFilter;
import invalidRegistrationTest.RegistrationErrorTest;
import loginTest.LogInWithKeys;
import loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;

@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTestFilter.class)
@Suite.SuiteClasses({
        LogInWithKeys .class,
        RegistrationErrorTest.class,
        CreatePostTest.class// will not run as there is no categories
})
public class smokeSuiteWithCategories {
}
