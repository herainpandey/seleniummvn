package com.task.automation.core;

import com.task.automation.core.DriverManager;
import com.task.automation.helper.CustomHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTest extends CustomHelper {

    private ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
    threadDriver.set(driver);
    }

    protected WebDriver getDriver(){
        return threadDriver.get();
    }


    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void initializeDriver(String browser) throws MalformedURLException, URISyntaxException {
        System.out.println("driver is initialised");
        this.setDriver(DriverManager.getInstance().InitializeDriver(browser));
    }

   @AfterMethod(alwaysRun = true)
    public void quitDriver(){
       threadDriver.get().quit();
    }
}

