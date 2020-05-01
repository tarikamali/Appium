package steps;

import au.com.utils.Constants;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import components.screens.BaseClass;
import components.screens.HomeScreen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;


public class MobilitySteps extends BaseClass{
    public HomeScreen homeScreen;
    public MobilitySteps() {
    }

    @Before()
    public void setUp(Scenario scenario) {
        initialization();
        PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
        System.out.println("**********************************************");
        homeScreen= new HomeScreen(driver);
        System.out.println(scenario.getName()+" - scenario test execution started");
    }

    @After()
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String currentDir = System.getProperty("user.dir");
            FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
            byte[] fileContent = Files.readAllBytes(scrFile.toPath());
            scenario.embed(fileContent,"image/png");
        }
        Constants.NoResetApp=true;
        driver.quit();
        System.out.println(scenario.getName()+" - scenario test execution finished");
    }


    @Given("^I am on my main screen$")
    public void iAmOnMyJobsScreen() {
        Assert.assertTrue(homeScreen.verifyHomeScreen());
    }

    @When("^I see login screen$")
    public void iSeeLoginScreen() {
        Assert.assertTrue(homeScreen.verifyLoginScreen());
    }

    @When("^I enter username and password$")
    public void iEnterUsernameAndPassword() {
        homeScreen.enterCredentials();
    }

    @Then("^I am on home page$")
    public void iAmOnHomePage() {
        Assert.assertTrue(homeScreen.checkHomeElementWithInline("What are you looking for?"));
    }


    @When("^I Search \"([^\"]*)\"$")
    public void iSearch(String arg0) throws Exception {
        homeScreen.searchItem(arg0);
    }

    @And("^I select item index \"([^\"]*)\"$")
    public void iSelectItemIndex(String arg0) throws Throwable {

    }

    @And("^I select item index \"([^\"]*)\" and add to cart$")
    public void iSelectItemIndexAndAddToCart(String arg0) throws Throwable {
        homeScreen.selectItem(arg0);

    }
}
