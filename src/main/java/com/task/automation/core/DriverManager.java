package com.task.automation.core;

import com.task.automation.enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {

    private static final DriverManager driverManager = new DriverManager();

    private DriverManager(){
    }

    public static DriverManager getInstance(){
        return  driverManager;
    }

    public String getBrowser(){
        return System.getProperty("browser");
    }

    public WebDriver InitializeDriver(String browser){
        WebDriver driver ;
        switch (DriverType.valueOf(browser.toUpperCase())){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name" + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return  driver;

    }
}
