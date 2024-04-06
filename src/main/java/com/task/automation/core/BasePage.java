package com.task.automation.core;

import com.task.automation.utiities.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage extends CustomDriver {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void load(String endpoint) throws IOException {
        driver.get(PropertyUtils.getInstance().getProperty("baseurl")+ endpoint);
    }

    public void waitForOverlayToDisapper(List<WebElement> overlay){
        List<WebElement> overays = overlay;
        if(overays.size() > 0){
            wait.until(ExpectedConditions.invisibilityOfAllElements(overays));
            System.out.println("Overlay Invisible");
        }
        else{
            System.out.println("Overlay not found");
        }
    }
}
