package stepDefinitions;

import Methods.PageObject_GenericMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import resources.base;
import pageObjects.NetbankPage;

public class NetbankSteps extends base {

    public ExtentReports report;
    public ExtentTest logger;


    @Then("^I validate netbank login page has loaded$")
    public void i_verify_ui_ofTravelPage(){
        NetbankPage netbankPage = new NetbankPage(driver);
        netbankPage.validateLogon();
    }

}
