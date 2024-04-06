package com.task.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.task.automation.core.BasePage;

import java.util.List;

public class StorePage extends BasePage {

    private static final Logger log = LogManager.getLogger(StorePage.class.getName());

    private final  By search_box = By.xpath("//form[@role='search']/input[1]");
    private final By search_button = By.xpath("//form[@role='search']/button");
    private final By product_list = By.xpath("//ul[@class='products columns-4']//div[2]//h2");
    private final By view_cart_link = By.xpath("//ul[@class='products columns-4']//div[2]//a[contains(text(),'View cart')]");

    boolean productAdded= false;
    public StorePage(WebDriver driver) {
        super(driver);
    }


    public StorePage searchProductWithColorAndAddToCart(String color, String product, int quantity) throws InterruptedException {
        log.info("Going to enter color for Search");
        searchForColor(color);
        log.info("Going to enter product on cart");

        searchProductAndAddToCart(product, 1);
        return this;
    }

    public StorePage searchProductWithColorAndAddToCartAsPerQuanity(String color, String product, int quantity) throws InterruptedException {
        searchForColor(color);
        searchProductAndAddToCart(product, quantity);
        return this;
    }

    private void searchForColor(String color){
        driver.findElement(search_box).sendKeys(color);
        driver.findElement(search_button).click();
    }

    private void searchProductAndAddToCart(String product, int quantity) throws InterruptedException {
        if(isProductAvaiableThenAddToCart(driver.findElements(product_list), product, quantity)){
            log.info("Product added");

        }else{
            log.error("Product not found");
        }
    }
    private boolean isProductAvaiableThenAddToCart(List<WebElement> listOfProducts,String searchProduct, int quantity) throws InterruptedException {
        int count = quantity;
        for(WebElement product : listOfProducts){
            log.info("Product from list " +product.getText());
            if(product.getText().equalsIgnoreCase(searchProduct)){
                while(count >0) {
                    driver.findElement(By.xpath("//h2[text()='"+searchProduct+"']/parent::a/following-sibling::a")).click();
                    Thread.sleep(1000);
                    count--;
                }
                productAdded = true;
                return true;
            }
        }
        return false;
    }

    public CartPage navigateToCartIfItemAdded(){
        if(productAdded){
            driver.findElement(view_cart_link).click();
            return new CartPage(driver);
        }else{
            log.error("Item not added to cart");
            return null;
        }
    }
}
