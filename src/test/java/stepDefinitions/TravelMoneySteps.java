package stepDefinitions;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Methods.PageObject_GenericMethods;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import resources.base;
import pageObjects.TravelMoney;

public class TravelMoneySteps extends base {
	public static String client_Name;
	public ExtentReports report;
	public ExtentTest logger;

	@Before
	public void createReport() {
		AUTO_ENV = System.getProperty("AUTO_ENV");
		testResultRecorder.put("testStatus", 1);
		PageObject_GenericMethods.createReport();
	}

	@After
	public void TearDownTest() throws IOException {
		try {
			driver.quit();
			if (testType.contains("UI")) {
				PageObject_GenericMethods.logger = PageObject_GenericMethods.report.createTest("Final Screenshot");
				PageObject_GenericMethods.logger
						.addScreenCaptureFromPath(PageObject_GenericMethods.captureScreenShot(driver));
			}
			PageObject_GenericMethods.report.flush();

			PageObject_GenericMethods.extentReport_AutomatedTestResults();
			if (testType.contains("UI")) {
				driver.quit();
			}
		} catch (AssertionError e) {
			if (createReport = true) {
				PageObject_GenericMethods.logger.fail("Tear down at the conclusion of execution");
				PageObject_GenericMethods.logger = PageObject_GenericMethods.report.createTest("Final Screenshot");
				logger.addScreenCaptureFromPath(PageObject_GenericMethods.captureScreenShot(driver));
				PageObject_GenericMethods.report.flush();
				PageObject_GenericMethods.report_new.flush();
			}
		}
	}

	@Given("^Environment is configured$")
	public void environment_is_configured() throws Throwable {
		try {
			loadPropertiesFile();
			AUTO_ENV = System.getProperty("AUTO_ENV");

		} catch (AssertionError e) {

			logger.fail("Environment is configured");
			PageObject_GenericMethods.report.flush();
		}

	}

	@Then("^I verify UI for the travel page$")
	public void i_verify_ui_ofTravelPage(){
		TravelMoney travelMoney  = new TravelMoney(driver);
		travelMoney.validateTravelPageHasLoaded();
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws Throwable {
		if (testType.contains("UI")) {
			driver.quit();
		}
	}
}
