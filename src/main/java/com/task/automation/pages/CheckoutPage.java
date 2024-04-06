package com.task.automation.pages;

import com.task.automation.objects.BillingAddress;
import com.task.automation.utiities.PropertyUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import com.task.automation.core.BasePage;

import java.io.IOException;
import java.util.List;


public class CheckoutPage extends BasePage {


    @FindBy(id = "place_order")
    private WebElement placeOrderBtn;

    @CacheLookup
    @FindBy(css = ".blockUI.blockOverlay")
    private List<WebElement> overays;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public CheckoutPage enterBillingDetailsNewUser(BillingAddress billingAddress){

        driver.findElement(By.id("billing_first_name")).sendKeys(billingAddress.getFirstName());
        driver.findElement(By.id("billing_last_name")).sendKeys(billingAddress.getLastName());
        driver.findElement(By.id("billing_company")).sendKeys(billingAddress.getCompany());
        selectFromDropDown(By.id("select2-billing_country-container"),billingAddress.getCountry());
        driver.findElement(By.id("billing_address_1")).sendKeys(billingAddress.getAddressLineOne());
        js.executeScript("arguments[0].value='"+billingAddress.getCity()+"';",driver.findElement(By.id("billing_city")));
        selectFromDropDown(By.id("select2-billing_state-container"), billingAddress.getState());
        driver.findElement(By.id("billing_postcode")).sendKeys(billingAddress.getPostalCode());
        driver.findElement(By.id("billing_email")).sendKeys(billingAddress.getEmail());
        return this;

        /* Select country = new Select(driver.findElement(By.id("billing_country")));
        country.selectByVisibleText(billingAddress.getCountry());*/
        /*Select state = new Select(driver.findElement(By.id("billing_state")));
        state.selectByVisibleText(billingAddress.getState());*/
    }

    private void selectFromDropDown(By element,String value){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        WebElement element1 = driver.findElement(By.xpath("//li[text()='"+value+"']"));
        js.executeScript("arguments[0].scrollIntoView(true);",element1 );
        element1.click();

    }

    public CheckoutPage enterBillingDetaisExistingUser(BillingAddress billingAddress){

        Select country = new Select(driver.findElement(By.id("billing_country")));
        country.selectByVisibleText(billingAddress.getCountry());
        Select state = new Select(driver.findElement(By.id("billing_state")));
        state.selectByVisibleText(billingAddress.getState());
        return this;
    }


    public OrderConfirmationPage placeOrder() throws InterruptedException {
        waitForOverlayToDisapper(overays);
        placeOrderBtn.click();
        return new OrderConfirmationPage(driver);
    }

    public CheckoutPage loginWithUser() throws IOException {
        driver.findElement(By.xpath("//a[contains(text(),'Click here to login')]")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys(PropertyUtils.getInstance().getProperty("username"));
        driver.findElement(By.cssSelector("#password")).sendKeys(PropertyUtils.getInstance().getProperty("password"));
        driver.findElement(By.cssSelector("button[class^='woocommerce']")).click();
        return this;
    }

}

