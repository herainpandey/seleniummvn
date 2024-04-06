package com.task.automation.pages;


import com.task.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class OrderConfirmationPage extends BasePage {

    private final By confirmOrderText = By.cssSelector("p[class^='woocommerce-notice']");
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public void isOrderConfirmed(){
        Assert.assertEquals(driver.findElement(confirmOrderText).getText(),"Thank you. Your order has been received.");
    }



}
