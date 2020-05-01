package components.screens;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;


public class HomeScreen extends BaseClass{

    public AppiumDriver<? extends MobileElement> driver;

        @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
        public MobileElement signInButton;

        @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_email_login']")
        public MobileElement loginText;

        @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='ap_password']")
        public MobileElement passwordText;

        @AndroidFindBy(id = "signInSubmit")
        public MobileElement submit;

        @AndroidFindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
        public MobileElement searchText;

        @AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_suggestions_list_view']/android.widget.LinearLayout[2]")
        public MobileElement option;



    public HomeScreen(AppiumDriver<? extends MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);

    }

    public boolean verifyHomeScreen(){
        return isElementPresent(signInButton);
    }

    public boolean verifyLoginScreen(){
        return signInButton.getText().equalsIgnoreCase("Already a customer? Sign in");
    }

    public void enterCredentials() {
        signInButton.click();
        waitTillElementVisibilty(loginText).sendKeys(prop.getProperty("username"));
        clickButton("Continue");
        waitTillElementVisibilty(passwordText).sendKeys(prop.getProperty("password"));
        clickButton("Sign-In");
        waitTillElementVisibilty(searchText);
    }

    public void searchItem(String arg0)  {
        waitTillElementVisibilty(searchText).clear();
        searchText.click();
        searchText.setValue(arg0);
        waitTillElementVisibilty(option).click();
    }

    public void selectItem(String arg0) throws InterruptedException {
        scroll("DOWN", 1000,3);
        MobileElement element;
        if(isiOSPlatform()){
            //IOS element
            element=driver.findElement(MobileBy.xpath(""));
        }else {
            element=driver.findElement(MobileBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[" + arg0 + "]"));
        }
        waitTillElementVisibilty(element).click();

        sleep(3000);
        scroll("DOWN", 1000,4);
        clickButton("Add to Cart");
        clickButton("No Thanks");
        clickButton("Proceed to checkout");
    }

    public boolean checkHomeElementWithInline(String s) {
        return searchText.getText().equalsIgnoreCase(s);
    }
}
