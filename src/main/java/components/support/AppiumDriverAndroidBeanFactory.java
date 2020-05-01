package components.support;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;



public class AppiumDriverAndroidBeanFactory {

        public AppiumDriver<? extends MobileElement> getDriver() throws MalformedURLException {
        File app  = new File(System.getProperty("user.dir")+ "\\src\\main\\resources\\Amazon_shopping.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        System.out.println("\nApplication under - Android");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
            capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
            capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.APP, app);

            AppiumDriver<? extends MobileElement> driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            return driver;
    }

    }

