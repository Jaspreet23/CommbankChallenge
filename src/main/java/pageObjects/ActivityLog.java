package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Methods.PageObject_GenericMethods;
import resources.base;

public class ActivityLog extends PageObject {

	public ActivityLog(WebDriver driver) {
		super(driver);

	}

	//
	@FindBy(xpath = ".//*[contains(text(),'Activity log')]")
	private WebElement btn_activityLog;

	// Activity Log - Quote Report <UAT>
	@FindBy(xpath = ".//*[contains(text(),'quote report - (UAT)')]")
	private WebElement actLog_quotereport_UAT;

	// Activity Log - Quote Report <SIT>
	@FindBy(xpath = ".//*[contains(text(),'quote report - (SIT)')]")
	private WebElement actLog_quotereport_SIT;

	// Text - Title

	// Select Discount
	@FindBy(xpath = ".//*[@id='discount']")
	private WebElement slider_discount;

	public void actLog_quoteReport() throws InterruptedException, IOException {

		driver.navigate().refresh();
		Thread.sleep(2000);
		PageObject_GenericMethods.clickOnElement(btn_activityLog, "Activiy Log - Selected");
		Thread.sleep(2000);
		if (base.AUTO_ENV.contains("UAT")) {
			PageObject_GenericMethods.clickOnElement(actLog_quotereport_UAT,
					"Quote Report downloaded from Activity Log");
		}
		if (base.AUTO_ENV.contains("SIT")) {
			PageObject_GenericMethods.clickOnElement(actLog_quotereport_SIT,
					"Quote Report downloaded from Activity Log");
		}
		PageObject_GenericMethods.watchFileAdded();
		PageObject_GenericMethods.clickOnElement(btn_activityLog, "Activiy Log - Selected");

	}
	// Button - Next Section

}
