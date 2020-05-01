package components.screens;

import components.support.AppiumDriverAndroidBeanFactory;
import components.support.AppiumDriverIOSBeanFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;



public class BaseClass extends BaseScreen {
    public static AppiumDriver<? extends MobileElement> driver;

    public static Properties prop;

    public BaseClass()  {
        super(driver);
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/config_test.properties");
        prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void initialization(){
        String profile = prop.getProperty("Profile");
        try
        {
            if(profile.equalsIgnoreCase("android"))
            {
                AppiumDriverAndroidBeanFactory appiumDriverAndroidBeanFactory=new AppiumDriverAndroidBeanFactory();
                driver=appiumDriverAndroidBeanFactory.getDriver();
            }else {
                AppiumDriverIOSBeanFactory appiumDriverIOSBeanFactory=new AppiumDriverIOSBeanFactory();
                driver=appiumDriverIOSBeanFactory.getDriver();
            }
        }
        catch (Exception e)
        {
            System.out.println("driver not initialised");

        }
    }
}
