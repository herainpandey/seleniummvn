package com.task.automation;

import com.task.automation.core.BaseTest;
import com.task.automation.objects.BillingAddress;
import com.task.automation.objects.Product;
import org.testng.annotations.Test;
import com.task.automation.pages.HomePage;
import com.task.automation.helper.CustomHelper;

import java.io.IOException;
import java.io.InputStream;

public class MySecondTestCase extends BaseTest {

    @Test(groups = {"regression"})
    public void logoutUserCheckoutFlowDataViaJSON() throws InterruptedException, IOException {
        Product product = new Product(98765);
        HomePage homePage = new HomePage(getDriver());
        BillingAddress billingAddress = new BillingAddress();
        InputStream is = getClass().getClassLoader().getResourceAsStream("BillingAddress.json");

        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart("blue",product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(CustomHelper.generateBillingDataviJSON(is,  billingAddress))
                .placeOrder()
                .isOrderConfirmed();
    }



    @Test(groups = {"smoke","regression"})
    public void logoutUserCheckoutFlowDataViaGenericDesirialiser() throws InterruptedException, IOException {
        Product product = new Product(98765);
        HomePage homePage = new HomePage(getDriver());
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart("blue",product.getName(), 1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(CustomHelper.genericDeserialiser("BillingAddress.json",  BillingAddress.class))
                .placeOrder()
                .isOrderConfirmed();
    }

}
