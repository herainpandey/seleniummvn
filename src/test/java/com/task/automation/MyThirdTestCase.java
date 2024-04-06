package com.task.automation;

import com.task.automation.core.BaseTest;
import com.task.automation.objects.BillingAddress;
import org.testng.annotations.Test;
import com.task.automation.pages.HomePage;
import com.task.automation.helper.CustomHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyThirdTestCase extends BaseTest {

    @Test(groups = "regression" , dataProvider="input")
    public void logoutUserCheckoutExcelFile(String product, String quantity) throws InterruptedException, IOException {
        HomePage homePage = new HomePage(getDriver());
        BillingAddress billingAddress = new BillingAddress();
        InputStream is = getClass().getClassLoader().getResourceAsStream("BillingAddress.json");

        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCartAsPerQuanity("blue",product,Integer.parseInt(quantity))
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(CustomHelper.generateBillingDataviJSON(is,  billingAddress))
                .placeOrder()
                .isOrderConfirmed();
    }




}
