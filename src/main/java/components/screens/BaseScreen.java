package components.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.utils.WebDriverUnpackUtility;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public abstract class BaseScreen {

    public AppiumDriver<? extends MobileElement> driver;

    public BaseScreen(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
    }

    public AppiumDriver<? extends MobileElement> getDriver() {
        return driver;
    }

    public static MobileElement mobileElement;

    public String platform;

    public String getPlatform() {
        platform = WebDriverUnpackUtility.getPlatform(driver);
        return platform;
    }

    public boolean isiOSPlatform() {
        return getPlatform().equalsIgnoreCase("iOS");
    }

    public boolean verifyText(String s) {
        List<? extends MobileElement> element;
        if(isiOSPlatform())
        {
            //IOS code
            element=driver.findElements(MobileBy.xpath(""));
        }else {
            element=driver.findElements(MobileBy.xpath("//android.widget.TextView[@text='"+s+"'][1]"));
        }
        return (element.size()>0);
    }

    public boolean waitForElementPresent(MobileElement element, long timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.pollingEvery(100, TimeUnit.MILLISECONDS);
            wait.ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception ex) {
            System.out.println("\nException from waitForElementPresent :" + ex.toString());
            return false;
        }
    }

    public void clickButton(String s)
    {
        if(isiOSPlatform())
        {//IOS code
        }else {
            waitTillElementVisibilty(driver.findElement(MobileBy.xpath("//android.widget.Button[@text='"+s+"'][1]"))).click();
        }
    }


    public MobileElement waitTillElementVisibilty(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver,30);
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void scroll(String direction, int duration) {
        Double screenHeightStart,screenHeightEnd;
        Dimension dimensions = driver.manage().window().getSize();

            screenHeightStart = dimensions.getHeight() * 0.5;
            screenHeightEnd = dimensions.getHeight() * 0.2;

        int scrollStart = screenHeightStart.intValue();
        int scrollEnd = screenHeightEnd.intValue();
        if (direction == "UP") {
            driver.swipe(0, scrollEnd, 0, scrollStart, duration);
        } else if (direction == "DOWN") {
            driver.swipe(0, scrollStart, 0, scrollEnd, duration);
        }
    }
    public void scroll(String direction, int duration, int times) {
        for(int i=0;i<times;i++) {
            scroll(direction, duration);
        }
    }

    public boolean isElementPresent(MobileElement mobileElement) {
        try {
            return waitTillElementVisibilty(mobileElement).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }



}
