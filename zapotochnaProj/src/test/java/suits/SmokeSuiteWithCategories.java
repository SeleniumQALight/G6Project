package suits;


import categories.SmokeTestFilter;
import suits.loginTest.LoginTestWithPageObject;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;
import registerTest.RegistrationErrorsTest;

@RunWith(Categories.class) // ми вказали щоб ранити цей набір класів
@Categories.IncludeCategory(SmokeTestFilter.class)  //вказали які категорії ми хочемо включити , exclude виключити

@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        RegistrationErrorsTest.class,
        CreatePostTest.class
})


public class SmokeSuiteWithCategories {



}
