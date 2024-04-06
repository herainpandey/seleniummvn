package com.task.automation.pages;

import com.task.automation.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage extends BasePage {
    private final By storeMenuLink = By.xpath(("//li[@id='menu-item-1227']/a"));
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load() throws IOException {
        load("/");
        return this;
    }
    public StorePage goToStorePage(){
        driver.findElement(storeMenuLink).click();
        return new StorePage(driver);
    }
}
