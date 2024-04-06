package com.task.automation.core;
import org.openqa.selenium.*;

public class CustomDriver{
    protected static WebDriver driver;
    protected static JavascriptExecutor js;

    public CustomDriver(){}
    public CustomDriver(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor) driver;
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void refersh(){
        driver.navigate().refresh();
    }



    }
