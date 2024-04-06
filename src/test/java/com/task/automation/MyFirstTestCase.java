package com.task.automation;



import com.task.automation.core.BaseTest;
import com.task.automation.helper.CustomHelper;
import com.task.automation.objects.Product;
import org.testng.annotations.Test;
import com.task.automation.pages.HomePage;

import java.io.IOException;


public class MyFirstTestCase extends BaseTest {


    @Test(groups = {"sanity","regression"})
    public void logoutUserCheckoutFlow() throws InterruptedException, IOException {
        Product product = new Product(98765);
        HomePage homePage = new HomePage(getDriver());
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart("blue",product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .enterBillingDetailsNewUser(CustomHelper.generateBillingData())
                .placeOrder()
                    .isOrderConfirmed();
    }


    @Test(enabled = false)
    public void loginUserCheckoutFlow() throws InterruptedException, IOException {
        Product product = new Product(98765);
        HomePage homePage = new HomePage(getDriver());
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart("blue",product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                 .loginWithUser()
                .enterBillingDetaisExistingUser(CustomHelper.generateBillingData())
                .placeOrder()
                .isOrderConfirmed();


    }


    @Test(groups = {"regression"})
    public void loginUserCheckoutFlowFaiedCase() throws InterruptedException, IOException {
        Product product = new Product(98765);
        HomePage homePage = new HomePage(getDriver());
        homePage.load()
                .goToStorePage()
                .searchProductWithColorAndAddToCart("red",product.getName(),1)
                .navigateToCartIfItemAdded()
                .proceedToCheckout()
                .loginWithUser()
                .enterBillingDetaisExistingUser(CustomHelper.generateBillingData())
                .placeOrder()
                .isOrderConfirmed();


    }


}
