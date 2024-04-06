package com.task.automation.core;

import com.task.automation.enums.DriverType;
import com.task.automation.utiities.Constants;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;

import static com.task.automation.utiities.Constants.REMOTE_URL;

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

    public WebDriver InitializeDriver(String browser) throws MalformedURLException, URISyntaxException {
        WebDriver driver ;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (DriverType.valueOf(browser.toUpperCase())){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case REMOTE_CHROME:
                capabilities.setBrowserName(Constants.REMOTE_CHROME);
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
               // capabilities.setCapability(CapabilityType.BROWSER_NAME,"chrome");
                driver = new RemoteWebDriver(new URI(REMOTE_URL).toURL(), capabilities);
                break;
            case REMOTE_FIREFOX:
                capabilities.setBrowserName(Constants.REMOTE_FIREFOX);
                capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
               // capabilities.setCapability(CapabilityType.BROWSER_NAME,"firefox");
                driver = new RemoteWebDriver(new URI(REMOTE_URL).toURL(), capabilities);
                break;
            default:
                throw new IllegalStateException("Invalid browser name" + browser);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return  driver;

    }
}
