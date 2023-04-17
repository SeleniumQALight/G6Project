package postTest;

import baseTest.PPBaseTest;
import org.junit.Test;
//import org.junit.After;
//import org.junit.Test;





public class DELETE_ME_TEST extends PPBaseTest {


//    PPLoginPage ppLoginPage = new PPLoginPage(DriverHelper.getWebDriver());

    @Test
    public void TC() {
        ppLoginPage
                .openPrivatBankPage()
                .getUiCurrencyBuyValueAndSave("USD")
                ;
    }

}
