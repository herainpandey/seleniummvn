package com.task.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.task.automation.core.BasePage;

public class CartPage extends BasePage {

    private final By checkoutButton = By.xpath("//a[contains(text(),'Proceed to checkout')]");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage proceedToCheckout() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(checkoutButton).click();
        return new CheckoutPage(driver);
    }






}
