package stepDefinitions;

import Methods.PageObject_GenericMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.*;
import resources.base;
import pageObjects.HomePage;

public class HomePageSteps extends base {
    public static String urlAUTO_ENV;
    public ExtentReports report;
    public ExtentTest logger;


    @Given("^I launch the homepage$")
    public void login_to_portal_as_something_user() throws Throwable {
        try {
            testType = "UI";
            driver = initializeDriver();
            AUTO_ENV = System.getProperty("AUTO_ENV");
            //url = prop.getProperty("base_url");
            String url = "https://www.commbank.com.au/";
            driver.manage().window().maximize();
            driver.get(url);
        }
        catch (AssertionError e) {
            PageObject_GenericMethods.report.flush();
        }
    }
    @And("^I validate homepage has loaded$")
    public void  i_validateUIForHomePage(){
        HomePage homePage = new HomePage(driver);
        homePage.validateUIForHomePage();
    }
    @When("^I click on Tell me more section$")
    public void i_clickOnSection(){
        HomePage homePage = new HomePage(driver);
        homePage.clickOnTransferMoney();
    }
    @Then("^I click on Netbank login$")
    public void i_click_on_netbank() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnNetBank();
    }

}
