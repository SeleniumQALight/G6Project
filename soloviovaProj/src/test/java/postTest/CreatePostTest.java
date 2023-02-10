package postTest;

import baseTest.BaseTest;
import libraries.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_soloviova_" + Util.getDateAndTimeFormatted();
    final String BODY_TITLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam mattis faucibus malesuada. Pellentesque pretium eget felis sit amet pretium. Maecenas sollicitudin aliquet dignissim. Sed aliquam lorem non elementum tincidunt. Curabitur varius risus eu nibh porta pellentesque. Phasellus mollis sed dui sed facilisis.";
    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(BODY_TITLE)
                .selectTextInDropDownOptions("Приватне повідомлення")
              //  .selectValueInDropDownOptions("One Person")
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkPostWasCreated(POST_TITLE)
        ;
    }
}
