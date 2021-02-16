package Methods;

import static org.testng.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import resources.base;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class PageObject_GenericMethods extends base {
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentReports report_new;
	public static ExtentTest logger_new;
	public static ExtentTest logger;
	public static ExtentTest eTestRegression;
	public static ExtentReports eReportRegression;

	public void setProperties() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

	}

	// Created by:Amar
	// Version1.0 Date&Time: 05/08/2019
	// Description: To enter string into a textbox.
	// Updated by:
	// Version1.1 Date&Time:
	// Update:

	public static void enterIntoTextbox(WebElement element, String str, String msg) throws IOException {
		try {
			element.sendKeys(str);
			System.out.println(msg + str);
			// log.info(msg+str);
			logger.pass(msg + str);

			// cell4.setCellValue("Pass");

		} catch (AssertionError e) {

			logger.fail(msg + str);
			logger_new.fail(msg + str);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void scrollSmooth_100() {
		for (int i = 0; i < 100; i++) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1)", "");
		}
	}

	public static void scroll_up() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
	}

	public static void enterIntoTextbox_isDisplayed(WebElement element, String str, String msg) throws IOException {

		try {
			if (element.isDisplayed()) {
				element.sendKeys(str);
				System.out.println(msg + str);
				// log.info(msg+str);
			}

			logger.pass(msg + str);

			// cell4.setCellValue("Pass");

		} catch (AssertionError e) {

			logger.fail(msg + str);
			logger_new.fail(msg + str);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void enterKey_enterIntoTextbox(WebElement element, String str, String loginfo) throws IOException {

		try {
			element.sendKeys(Keys.RETURN);
			element.sendKeys(str);
			System.out.println(loginfo + " : " + str);
			// log.info(loginfo+" : "+str);

			logger.pass(str);

			// cell4.setCellValue("Pass");

		} catch (AssertionError e) {

			logger.fail(str);
			logger_new.fail(str);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static void assert_ElementIsDisplayed(WebElement element, String msg)
			throws InterruptedException, IOException {

		try {
			if (element.isDisplayed()) {
				// Highlight_Element(element);
				Thread.sleep(2000);
				System.out.println(msg + " - is displayed");
				// log.info(msg+" - is displayed");
				// base.getScreenshot2();
			}

			else {
				// System.out.println("Failed"+msg+" - is not displayed");

				logger.fail("Failed" + msg + " - is not displayed");

			}
			logger.pass(msg + " - is displayed");

			PageObject_GenericMethods.report.flush();
		}

		catch (AssertionError e) {

			logger.fail(msg);
			logger_new.fail(msg);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void assert_ElementIsNotDisplayed(WebElement element, String msg)
			throws InterruptedException, IOException {

		try {
			if (!element.isDisplayed()) {
				// Highlight_Element(element);
				Thread.sleep(2000);
				System.out.println(msg + " - is not displayed as expected");
				// log.info(msg+" - is displayed");
				// base.getScreenshot2();
			}

			else {
				// System.out.println("Failed"+msg+" - is not displayed");

				// logger.fail("Failed" + msg + " - is displayed but wansn't supposed to be.");

			}
			logger.pass(msg + " - is not displayed as expected");

			PageObject_GenericMethods.report.flush();
		}

		catch (AssertionError e) {

			logger.fail(msg);
			logger_new.fail(msg);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void enterIntoTextbox_clear(WebElement element, String str, String msg)
			throws IOException, InterruptedException {
		try {
			// if(element.isDisplayed())
			// {
			// element.click();
			// }
			// else
			// {

			// logger.fail("Element is present but not displayed: "+msg);
			// }
		} catch (NoSuchElementException e) {

			logger.fail("Element is not present, hence not displayed as well: " + msg);
			logger_new.fail("Element is not present, hence not displayed as well: " + msg);
			PageObject_GenericMethods.report.flush();
		}

		Thread.sleep(2000);
		Highlight_Element(element);
		element.clear();
		Thread.sleep(2000);
		if (element.getText().isEmpty()) {
			System.out.println(msg + " - Data cleared");
			// logger.pass("Test Passed");
			logger.pass(msg + " - Data cleared");
		} else {
			System.out.println(element.getText());
			logger.pass(element.getText());

		}
		Thread.sleep(3000);
		element.sendKeys(str);
		System.out.println(msg + str);
		logger.info(msg + str);

		// log.info(msg+str);
		Thread.sleep(2000);
	}

	public static void enterIntoTextbox_clear_withoutScroll(WebElement element, String str, String msg)
			throws IOException, InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].sendKeys("+str+");"+element+")";
		js.executeScript("arguments[0].sendKeys(" + str + ");", element);
		// element.sendKeys(str);
		System.out.println(msg + str);
		logger.info(msg + str);

		// log.info(msg+str);
		Thread.sleep(2000);
	}

	public static void clickOnElement(WebElement element, String str) {
		Highlight_Element(element);

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isEnabled()) {

				element.click();

				System.out.println("Clicked on link: " + str);

				logger.pass("Clicked on link: " + str);

			} else {

				System.out.println(str + "- is not enabled");

				logger.fail(str + "- is not enabled");
				logger_new.fail(str + "- is not enabled");

				// log.info(str+"- is not enabled");
			}

		} catch (NoSuchElementException e) {

			logger.fail("Element click: " + str);
			logger_new.fail("Element click: " + str);

			PageObject_GenericMethods.report.flush();
		}

	}

	public static void clickOnElement_check(WebElement element, String str) {

		try {
			// Highlight_Element(driver, element);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isEnabled()) {
				System.out.println("before clicking:" + str + " " + element.getAttribute("checked"));
				element.click();
				if (element.getAttribute("checked") == "true") {
					System.out.println("Clicked on link: " + str);
					System.out.println("after clicking:" + str + " " + element.getAttribute("checked"));

					logger.pass("Clicked on link: " + str);
					logger.pass("Validated element checked after click");
				}

			} else {

				logger.fail(str + "- is not enabled");
				logger_new.fail(str + "- is not enabled");

				System.out.println(str + "- is not enabled");
				// log.info(str+"- is not enabled");
			}
		}

		catch (NoSuchElementException e) {

			logger.fail("Element click: " + str);
			logger_new.fail("Element click: " + str);

			PageObject_GenericMethods.report.flush();
		}

	}

	public static void clickOnElement_isDisplayed(WebElement element, String str) {
		// Highlight_Element(driver, element);

		try {

			if (element.isDisplayed()) {
				element.click();
				System.out.println("Done - Click on element: " + str);

				logger.pass("Done - Click on element: " + str);
			} else {

				System.out.println(str + "- is not displayed");

				logger.fail(str + "- is not displayed");
				logger_new.fail(str + "- is not displayed");
				// log.info(str+"- is not enabled");
			}

		}

		catch (NoSuchElementException e) {

			logger.fail("Element click: " + str);
			logger_new.fail("Element click: " + str);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static void clickOnElement_wait(WebElement element, String str, int seconds) {

		try {

			// Highlight_Element(driver, element);
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			if (element.isEnabled()) {
				element.click();

				logger.pass(str);

			} else {
				driver.navigate().refresh();
				WebDriverWait wait1 = new WebDriverWait(driver, 15);
				wait1.until(ExpectedConditions.elementToBeClickable(element));
				if (element.isEnabled()) {
					element.click();

					logger.pass(str);
				} else {
					System.out.println(str + "- is not enabled");

					logger.fail(str + "- is not enabled");
					logger_new.fail(str + "- is not enabled");
					// log.info(str+"- is not enabled");
				}

			}

		} catch (NoSuchElementException e) {

			logger.fail("Element click: " + str);
			logger_new.fail("Element click: " + str);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void scrollIntoView(int pixel) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("window.scrollTo(0,document.body.scrollHelight)");
		js.executeScript("scroll(0," + pixel + ");");

		Thread.sleep(500);

	}

	public static void selectElementAfterAvailable(String xpath, String msg) throws InterruptedException {
		PageObject_GenericMethods.waitForPresence(xpath);
		WebElement element = driver.findElement(By.xpath(xpath));
		PageObject_GenericMethods.selectElement(element, msg);
	}

	public static void selectElement(WebElement element, String msg) throws InterruptedException {

		try {

			// waitFor_elementToBeClickable(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);

			Action.moveToElement(element).click().build().perform();

			logger.pass(msg);

			System.out.println(msg);

		}

		catch (NoSuchElementException e) {

			logger.fail("Select element: " + msg);
			logger_new.fail("Select element: " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void selectElement_check(WebElement element, String msg) throws InterruptedException {

		try {

			// waitFor_elementToBeClickable(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);
			// System.out.println("before clicking:"+msg);
			Action.moveToElement(element).click().build().perform();

		}
		// log.info(msg);

		catch (NoSuchElementException e) {

			logger.fail("Select element: " + msg);
			logger_new.fail("Select element: " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void selectElement_check_datachecked(WebElement element, String msg) throws InterruptedException {

		try {
			String checked = "false";

			// waitFor_elementToBeClickable(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);
			// System.out.println("before clicking:"+msg);
			Action.moveToElement(element).click().build().perform();
			checked = element.getAttribute("data-checked");

			if (checked.equalsIgnoreCase("true")) {
				logger.pass("Check box is seleted for  - " + msg);
			} else {
				for (int i = 0; i <= 5; i++) {
					try {
						Action.moveToElement(element).click().build().perform();

						checked = element.getAttribute("data-checked");
						if (checked.equalsIgnoreCase("true"))
							break;
						else {

						}
					} catch (Exception e) {
						Action.moveToElement(element).click().build().perform();
						Thread.sleep(1000);
						System.out.println("catch ----");
					}
				}
			}
		}
		// log.info(msg);

		catch (NoSuchElementException e) {

			logger.fail("Select element: " + msg);
			logger_new.fail("Select element: " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void selectElement_datachecked(WebElement element) throws InterruptedException {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			ExpectedCondition<Boolean> elementAttributeEqualsString = arg0 -> element.getAttribute("data-checked")
					.equals("true");
			// new WebDriverWait(driver, 15)
			System.out.println("data-checked" + element.getAttribute("data-checked"));
			wait.until(elementAttributeEqualsString);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on waiting for element: " + element);
			logger_new.fail("Failed on waiting for element: " + element);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void waitForInvisibility(By byElement) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byElement));

		} catch (Exception e) {
		}
	}

	public static void waitForInvisibility(WebElement element) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.invisibilityOf(element));

		} catch (Exception e) {
		}
	}

	public static void selectElement_visible(WebElement element, String msg) throws InterruptedException {

		try {
			waitForVisibility(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);

			Action.moveToElement(element).click().build().perform();

			logger.pass(msg);
			System.out.println(msg);
			// log.info(msg);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();

		}
	}

	public static void selectElement_clickable(WebElement element, String msg) throws InterruptedException {
		try {

			waitFor_elementToBeClickable(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);
			Action.moveToElement(element).click().build().perform();

			logger.pass(msg);
			System.out.println(msg);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();

		}
	}

	public static void selectElement_isEnabled_Only(WebElement element, String msg) throws InterruptedException {

		try {
			if ((element.isEnabled()))

			{

				Actions Action = new Actions(driver);
				Action.moveToElement(element).click().build().perform();
				System.out.println(msg);

				logger.pass(msg);
				// log.info(msg);
				TimeUnit.SECONDS.sleep(2);
			}
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void selectElement_andcheckedstatus(WebElement element, String msg) throws InterruptedException {
		String checked = "false";
		checked = element.getAttribute("data-checked");
		if (checked.equalsIgnoreCase("true")) {
			logger.pass("Element is already selected - " + msg);
		} else {
			try {
				Actions Action = new Actions(driver);
				Action.moveToElement(element).click().build().perform();

				checked = element.getAttribute("data-checked");
				System.out.println("checkedstatus of" + msg + "--" + checked);
				for (int i = 0; i <= 5; i++) {
					try {
						checked = element.getAttribute("data-checked");
						if (checked.equalsIgnoreCase("true"))
							break;
						else {
							Action.moveToElement(element).click().build().perform();
							Thread.sleep(1000);
						}
					} catch (Exception e) {
						Action.moveToElement(element).click().build().perform();
						Thread.sleep(1000);
						System.out.println("catch ----");
					}
				}

				System.out.println(msg);

				logger.pass(msg);
				// log.info(msg);
				TimeUnit.SECONDS.sleep(2);
			}

			catch (Exception e) {
				e.printStackTrace();
				logger.fail("Failed on element select - " + msg);
				PageObject_GenericMethods.report.flush();
			}

		}

	}

	public static void checkStatus_andSelectElement(WebElement element, String msg) throws InterruptedException {
		String checked = "";
		checked = element.getAttribute("data-checked");
		if (checked.equalsIgnoreCase("false")) {
			System.out.println("Initially not selected --" + msg);
			Actions Action = new Actions(driver);
			Action.moveToElement(element).click().build().perform();
			for (int i = 0; i <= 5; i++) {
				try {
					checked = element.getAttribute("data-checked");
					if (checked.equalsIgnoreCase("true")) {
						System.out.println("Element is selected --" + msg);
						break;
					}

					else {
					}
				} catch (Exception e) {
					Action.moveToElement(element).click().build().perform();
					Thread.sleep(1000);
					System.out.println("catch ----");
				}
			}

		}

	}

	public static void selectElement_isEnabled(WebElement element, String msg) throws InterruptedException {

		// Highlight_Element(element);
		// TimeUnit.SECONDS.sleep(3);

		try {

			waitForVisibility(element);

			if (element.isEnabled()) {

				Actions Action = new Actions(driver);
				Action.moveToElement(element).click().build().perform();

				logger.pass(msg);
				System.out.println(msg);
				// log.info(msg);
				// TimeUnit.SECONDS.sleep(3);
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void click_button(WebElement element, String msg) throws InterruptedException {
		try {
			Actions Action = new Actions(driver);
			Action.moveToElement(element).click().build().perform();
			System.out.println(msg);
			logger.pass(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("Failed on click" + msg);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static void click_button_withoutScroll(WebElement element, String msg) throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
			// Actions Action = new Actions(driver);
			// Action.moveToElement(element).click().build().perform();
			System.out.println(msg);
			logger.pass(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("Failed on click" + msg);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static void click_button_withScroll(WebElement element, String msg) throws InterruptedException {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
			element.click();
			// Actions Action = new Actions(driver);
			// Action.moveToElement(element).click().build().perform();
			System.out.println(msg);
			logger.pass(msg);
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("Failed on click" + msg);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static void click_button_enabled(WebElement element, String msg) {

		try {
			waitFor_elementToBeClickable(element);
			Highlight_Element(element);

			Actions Action = new Actions(driver);
			Action.moveToElement(element).click().build().perform();
			System.out.println(msg);
			logger.pass(msg);

		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void selectCheckbox(WebElement element, String msg) throws InterruptedException {

		String checked = "false";

		try {
			// waitFor_elementToBeClickable(element);
			TimeUnit.SECONDS.sleep(1);
			Actions Action = new Actions(driver);
			Action.moveToElement(element).click().build().perform();
			checked = element.getAttribute("data-checked");
			for (int i = 0; i <= 5; i++) {
				try {
					if (checked.equalsIgnoreCase("true"))
						break;
					else {

					}
				} catch (Exception e) {

					Action.moveToElement(element).click().build().perform();
					Thread.sleep(1000);
					System.out.println("catch ----");
				}
			}

			System.out.println(msg);
			logger.pass(msg);
			// log.info(msg);

		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on element select - " + msg);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void waitForVisibility_withTime(WebElement element, int seconds) throws Error {
		try {

			try {
				new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
			}

		} catch (Exception e) {
			e.printStackTrace();

			// logger.fail("Failed on waiting for Element visibility"+element);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void waitForElementToBeClickable_withTime(WebElement element, int secs) throws Error {
		try {

			new WebDriverWait(driver, secs).until(ExpectedConditions.elementToBeClickable(element));
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on waiting for Element to be clickable" + element);
			PageObject_GenericMethods.report.flush();
		}

	}

	// The element must be on the DOM (HTML) and must be visible (displayed: not
	// none) with non-zero height and width
	public static void waitForVisibility(WebElement element) throws Error {
		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
		}

	}

	// Similar to waitForVisibility but the element isn't required to be visible
	// Some underlying inputs such as check boxes (and Yes No buttons in our case)
	// have hidden underlying hidden inputs
	public static void waitForPresence(String xpath) throws Error {
		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("Failed on waiting for visibility of xpath: " + xpath);
			PageObject_GenericMethods.report.flush();
		}
	}

	public static String RandomNumbers() {

		int aNumber = 0;
		aNumber = (int) ((Math.random() * 9000000) + 1000000);
		System.out.print((aNumber));
		return Integer.toString(aNumber);
	}

	public static void waitFor_elementToBeClickable(WebElement element) throws Error {
		try {

			new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on waiting for element to be clickable: " + element);
			logger_new.fail("Failed on waiting for element to be clickable: " + element);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void waitForElementAttributeEqualsString(WebElement element, String attribute, String expectedString)
			throws Error {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			ExpectedCondition<Boolean> elementAttributeEqualsString = arg0 -> element.getAttribute(attribute)
					.equals(expectedString);
			// new WebDriverWait(driver, 15)
			wait.until(elementAttributeEqualsString);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Failed on waiting for element: " + element);
			logger_new.fail("Failed on waiting for element: " + element);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void actionClick(WebElement element, String str) {

		waitForVisibility(element);
		Actions Action = new Actions(driver);
		Action.moveToElement(element).click().build().perform();

		System.out.println(str);

	}

	public static void assert_Title(WebElement element, String expTitle) {
		try {
			waitForVisibility(element);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Wait for Visibility: " + element);
			PageObject_GenericMethods.report.flush();
		}

		try {

			if (element.isDisplayed()) {
				System.out.println(element.getText());
				Assert.assertTrue(element.getText().contains(expTitle));
				// log.info("Successfully navigated to Screen "+expTitle);
				System.out.println("Successfully navigated to Screen " + expTitle);
				logger.pass("Successfully navigated to Screen " + expTitle);
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Assert title: " + expTitle);
			logger_new.fail("Assert title: " + expTitle);
			logger_new.fail("Assert title: " + expTitle);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void assert_TitleNew(WebElement element, String expTitle) {
		try {
			waitForVisibility(element);
		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Wait for Visibility: " + element);
			logger_new.fail("Wait for Visibility: " + element);
			PageObject_GenericMethods.report.flush();
		}

		try {

			if (element.isDisplayed()) {
				System.out.println(element.getText());
				Assert.assertTrue(element.getText().contains(expTitle));
				// log.info("Successfully navigated to Screen "+expTitle);
				System.out.println("Successfully navigated to Screen " + expTitle);
				logger.pass("Successfully navigated to Screen " + expTitle);
				logger_new.pass("Successfully navigated to Screen " + expTitle);
			}

		}

		catch (Exception e) {
			e.printStackTrace();

			logger.fail("Assert title: " + expTitle);
			logger_new.fail("Assert title: " + expTitle);
			logger_new.fail("Assert title: " + expTitle);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void Highlight_Element(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(element));
		}

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());

			logger.fail("Highlight Element: " + element);
			logger_new.fail("Highlight Element: " + element);
			PageObject_GenericMethods.report.flush();

		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 1px solid');", element);
	}

	public static void assert_Text(WebElement element, String str, String msg)
			throws InterruptedException, IOException {

		System.out.println(msg + element.getText());
		logger.info(msg + element.getText());
		// log.info(msg+element.getText());
		try {
			Assert.assertTrue(element.getText().contains(str));

			logger.pass(str);

		}

		catch (AssertionError e) {

			logger.fail(str);
			logger_new.fail(str);

			PageObject_GenericMethods.report.flush();

		}

	}

	public static void assert_Value(WebElement element, String str, String msg)
			throws InterruptedException, IOException {
		logger.info(msg + element.getAttribute("value"));
		System.out.println(msg + element.getAttribute("value"));
		// log.info(msg+element.getAttribute("value"));

		try {
			Assert.assertTrue(element.getAttribute("value").contains(str));

			logger.pass(str);

		}

		catch (AssertionError e) {

			logger.fail(str);
			logger_new.fail(str);
			PageObject_GenericMethods.report.flush();

		}

	}

	public static void assert_TextValue_afterUpdate(WebElement element, String str, String msg)
			throws InterruptedException, IOException {

		if (element.isDisplayed()) {
			if (!element.getText().equals(str)) {
				Thread.sleep(3000);
			}
			Thread.sleep(2000);
			System.out.println(msg + element.getText());
			logger.info(msg + element.getText());
			// log.info(msg+element.getText());

			try {
				Assert.assertTrue(element.getText().contains(str));

				logger.pass(str);

			}

			catch (AssertionError e) {

				logger.fail(str);
				logger_new.fail(str);
				PageObject_GenericMethods.report.flush();
			}

		}

		else {
			System.out.println("Failed" + msg + element.getText());
			logger.info("Failed" + msg + element.getText());
			logger_new.info("Failed" + msg + element.getText());
			// log.info("Failed"+msg+element.getText());

		}

	}

	public static void assert_TextElement(WebElement element, String str, String msg)
			throws InterruptedException, IOException {
		if (element.isDisplayed()) {

			Thread.sleep(2000);
			System.out.println(msg + element.getText());
			logger.info(msg + element.getText());

			// log.info(msg+element.getText());
			try {
				Assert.assertTrue(element.getText().contains(str));

				logger.pass("Expected:" + str + ", actual:" + element.getText());

			}

			catch (AssertionError e) {

				logger.fail("Expected:" + str + ", actual:" + element.getText());
				PageObject_GenericMethods.report.flush();

			}

		}

		else {

			logger.info("Failed" + msg + element.getText());
			logger_new.info("Failed" + msg + element.getText());
			System.out.println("Failed" + msg + element.getText());
			// log.info("Failed"+msg+element.getText());

		}

	}

	public static void assert_TextElementNew(WebElement element, String str, String msg)
			throws InterruptedException, IOException {
		if (element.isDisplayed()) {

			Thread.sleep(2000);
			System.out.println(msg + element.getText());
			logger.info(msg + element.getText());

			// log.info(msg+element.getText());
			try {
				Assert.assertTrue(element.getText().contains(str));

				logger.pass("Expected:" + str + ", actual:" + element.getText());
				logger_new.pass("Expected:" + str + ", actual:" + element.getText());

			}

			catch (AssertionError e) {

				logger.fail("Expected:" + str + ", actual:" + element.getText());
				logger_new.fail("Expected:" + str + ", actual:" + element.getText());
				PageObject_GenericMethods.report.flush();

			}

		}

		else {

			logger.info("Failed" + msg + element.getText());
			logger_new.info("Failed" + msg + element.getText());
			logger_new.info("Failed" + msg + element.getText());
			System.out.println("Failed" + msg + element.getText());
			// log.info("Failed"+msg+element.getText());

		}

	}

	public static void assert_TextElementEquals(WebElement element, String str, String msg)
			throws InterruptedException, IOException {
		if (element.isDisplayed()) {

			Thread.sleep(2000);
			System.out.println(msg + element.getText());
			logger.info(msg + element.getText());

			try {

				Assert.assertTrue(element.getText().equals(str));
				logger.pass(str);

			}

			catch (AssertionError e) {

				logger.fail(str);
				logger_new.fail(str);
				PageObject_GenericMethods.report.flush();

			}

		}

		else {

			logger.info("Failed" + msg + element.getText());
			logger_new.info("Failed" + msg + element.getText());
			System.out.println("Failed" + msg + element.getText());
			// log.info("Failed"+msg+element.getText());

		}

	}

	public static void assert_PProjection_Graph(WebElement element) throws IOException {
		waitForVisibility(element);
		try {
			if (element.isDisplayed()) {

				logger.pass("Success!!Premium projection graph is displayed");
				System.out.println("Success!!Premium projection graph is displayed");

				// base.getScreenshot2();
			}

			else {

				logger.fail("Failure!!Premium projection graph is not displayed");
				System.out.println("Failure!!Premium projection graph is not displayed");
				System.out.println("Failed" + element.getText());

				base.getScreenshot2();
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			logger.fail("Projection Graph: " + element);
			logger_new.fail("Projection Graph: " + element);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static int table_RowCount(List<WebElement> elements) throws InterruptedException {

		List<WebElement> rows = elements;
		if (rows.size() == 0) {
			Thread.sleep(2000);
			driver.navigate().refresh();
			if (rows.size() == 0) {
				Thread.sleep(2000);
				driver.navigate().refresh();
			}

		}

		return rows.size();

	}

	public static void assert_RowCount(List<WebElement> elements, int expected_rows)
			throws IOException, InterruptedException {
		int rowcount = table_RowCount(elements);
		System.out.println(rowcount);

		try {
			assertTrue(rowcount > expected_rows, "Pass! Expected number of rows are displayed: " + expected_rows);

			logger.pass("Pass! Expected number of rows are displayed: " + expected_rows);

		}

		catch (AssertionError e) {

			logger.fail("Fail! Expected number of rows are not displayed: " + expected_rows);
			logger_new.fail("Fail! Expected number of rows are not displayed: " + expected_rows);
			PageObject_GenericMethods.report.flush();
		}

	}

	public static void javaScriptExecutorClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static void javaScriptExecutorSendKeys(WebElement element, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value'," + value + ")", element);
	}

	public static void scrollIntoView(WebElement element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("window.scrollTo(0,document.body.scrollHelight)");
		js.executeScript("scroll(0,1000);");

		Thread.sleep(500);

	}

	public static void scrollIntoView_500() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView(true);",element);
		js.executeScript("window.scrollTo(0,document.body.scrollHelight)");
		js.executeScript("scroll(0,500);");

		Thread.sleep(500);

	}

	public static void scrollIntoView_height(WebElement element) throws InterruptedException {
		PageObject_GenericMethods.waitForVisibility_withTime(element, 15);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView(true);",element);
		// js.executeScript("window.scrollTo(0,document.body.scrollHelight)");
		// js.executeScript("scroll(0,2000);");
		// js.executeScript("arguments[0].scrollTop = arguments[1];",element, 1500);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		Thread.sleep(500);

	}

	public static void scrollToBottom1() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHelight)");
		Thread.sleep(500);

	}

	public static void scrollSmooth() {
		for (int i = 0; i < 1000; i++) {
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1)", "");
		}
	}

	public static void scrollToTop() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public static void scrollToBottom() {
		// ((JavascriptExecutor)
		// driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
		System.out.println("CTRL + END");
	}

	// This method creates an Extent Report file in TestResults folder of format
	// type- html
	public static void createReport() {

		// String timestamp=new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new
		// Date());
		// String fileName = folder+"_"+timestamp+".html";
		File reportfile_new = new File(
				System.getProperty("user.dir") + "\\TestResults\\AutomatedResults_" + AUTO_ENV + ".html");
		if (reportfile_new.exists()) {
			reportfile_new.delete();
		}

		ExtentHtmlReporter extent_new = new ExtentHtmlReporter(reportfile_new);
		report_new = new ExtentReports();
		report_new.attachReporter(extent_new);

	}

	// This method creates an Extent Report file in folder <Input Parameter> of
	// format type- html
	public static void createReport(String testName) {

		// String timestamp=new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new
		// Date());
		// String fileName = folder+"_"+timestamp+".html";
		File reportfile = new File(
				System.getProperty("user.dir") + "\\TestResults\\" + testName + "_" + AUTO_ENV + ".html");
		if (reportfile.exists()) {
			reportfile.delete();
		}

		ExtentHtmlReporter extent = new ExtentHtmlReporter(reportfile);
		report = new ExtentReports();
		report.attachReporter(extent);

	}

	// This method returns {String} fileName in folder <Input Parameter> of
	// fileType<Input Parameter>
	public static String createFileName(String folder, String fileType) {

		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());

		String fileName = System.getProperty("user.dir") + "\\TestResults\\" + "TestResults-" + folder + "." + fileType;

		return fileName;

		// logger.info(msg+str);
	}

	// This methods drags element on th Slider based on the Pixels input
	public static void drag_slider(WebElement element, int Pixels) {
		int width = element.getSize().getWidth();
		Actions SliderAction = new Actions(driver);
		SliderAction.clickAndHold(element).moveByOffset((-width / 2), 0).moveByOffset(Pixels, 0).release().perform();
	}

	@SuppressWarnings("rawtypes")
	public static void watchFileAdded() throws IOException, InterruptedException {
		Path watchFolder = Paths.get(base.downloadFilepath);
		WatchService watchService = FileSystems.getDefault().newWatchService();
		watchFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		boolean valid = true;
		do {
			// WatchKey watchKey = watchService.take();
			int waitTime = 120;
			WatchKey watchKey = watchService.poll(waitTime, TimeUnit.SECONDS);
			for (@SuppressWarnings("rawtypes")
			WatchEvent event : watchKey.pollEvents()) {

				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();
					if (fileName.endsWith("pdf")) {
						System.out.println("File Created:" + fileName);
						logger.pass("File Created: " + fileName);
						pdf_File_Name = fileName;
						return;
					} else if (fileName.endsWith("zip")) {
						System.out.println("Zip Folder Created:" + fileName);
						logger.pass("Zip Folder Created: " + fileName);
						zip_Folder_Name = fileName;
						System.out.println("zip_Folder_Name0:" + zip_Folder_Name);
						File directoryPath = new File(base.downloadFilepath + "\\" + zip_Folder_Name);
						System.out.println("directoryPath:" + directoryPath);
						String zipPath = base.downloadFilepath + "\\" + zip_Folder_Name;
						// List of all files and directories
						printFileList(zipPath);

						return;
					}

				}
				if (watchKey == null) {
					System.out.println("The expected report is not downloaded in -" + waitTime + " seconds");
					logger.fail("The expected report is not downloaded in -" + waitTime + " seconds");
					logger_new.fail("The expected report is not downloaded in -" + waitTime + " seconds");

					break;
				}
			}

			valid = watchKey.reset();

		} while (valid);

	}

	public static void printFileList(String filePath) {

		FileInputStream fis = null;
		ZipInputStream zipIs = null;
		ZipEntry zEntry = null;
		try {
			fis = new FileInputStream(filePath);
			zipIs = new ZipInputStream(new BufferedInputStream(fis));
			while ((zEntry = zipIs.getNextEntry()) != null) {
				System.out.println(zEntry.getName());
				pdf_File_Name = zEntry.getName();
				System.out.println("pdf_File_Name0:" + pdf_File_Name);
			}
			zipIs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void download() throws IOException, InterruptedException {

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		ChromeOptions options = new ChromeOptions();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, options);

	}

	public static void delete_PDF_Folder(String folderName) {

		File pdf_Folder = new File(base.downloadFilepath + "\\" + folderName);

		if (pdf_Folder.exists()) {
			FileHandler.delete(pdf_Folder);
		}

	}

	public static void make_PDF_Folder() {

		File pdf_Folder = new File(base.downloadFilepath + "\\" + "QR_TestResults");

		if (!(pdf_Folder.exists())) {
			pdf_Folder.mkdir();
		}

	}

	public static void make_PS_PDF_Folder() {

		File pdf_Folder = new File(base.downloadFilepath + "\\" + "PS_TestReports");

		if (!(pdf_Folder.exists())) {
			pdf_Folder.mkdir();
		}

	}

	public static void make_RT_PDF_Folder() {

		File pdf_Folder = new File(base.downloadFilepath + "\\" + "RT_TestReports");

		if (!(pdf_Folder.exists())) {
			pdf_Folder.mkdir();
		}

	}

	// static String pdf_File_Name = null;
	// static String zip_Folder_Name = null;

	public static void move_PDF_Added(String folderName, String type) {
		Path source_FilePass = null;
		Path target_FilePass = null;
		if (type.equals("annual")) {
			source_FilePass = Paths.get(base.downloadFilepath + "\\" + pdf_File_Name);
			target_FilePass = Paths.get(base.downloadFilepath + "\\" + "QR_TestResults" + "\\" + folderName + "_Annual_"
					+ base.AUTO_ENV + ".pdf");
		} else if (type.equals("monthly")) {
			source_FilePass = Paths.get(base.downloadFilepath + "\\" + pdf_File_Name);
			target_FilePass = Paths.get(base.downloadFilepath + "\\" + "QR_TestResults" + "\\" + folderName
					+ "_Monthly_" + base.AUTO_ENV + ".pdf");
		}

		FileUtils.deleteQuietly(new File(target_FilePass.toString()));
		try {
			Files.copy(source_FilePass, target_FilePass);
			File reportfile = new File(base.downloadFilepath + "\\" + pdf_File_Name);
			if (reportfile.exists()) {
				reportfile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void move_PDF_PS(String folderName, String type) throws IOException {
		Path source_FilePass = null;
		Path target_FilePass = null;
		System.out.println("zip_Folder_Name2:" + zip_Folder_Name);
		System.out.println("pdf_File_Name2:" + pdf_File_Name);
		// source_FilePass = Paths.get(base.downloadFilepath + "\\" + pdf_File_Name);
		// zip_Folder_Name = "policy-schedule-64c0e2a2-8db6-46f5-b775-1a105944971f";
		String srcZip = base.downloadFilepath + "\\" + zip_Folder_Name;
		String zipFolder = zip_Folder_Name.split("\\.")[0];

		String destDir = base.downloadFilepath + "\\" + zipFolder;
		Path path = Paths.get(base.downloadFilepath + "\\" + zipFolder);
		Files.createDirectory(path);
		unzip(srcZip, destDir);
		source_FilePass = Paths.get(base.downloadFilepath + "\\" + zipFolder + "\\" + pdf_File_Name);
		target_FilePass = Paths.get(base.downloadFilepath + "\\" + folderName + "\\" + type + ".pdf");
		FileUtils.deleteQuietly(new File(target_FilePass.toString()));
		System.out.println("source_FilePas:" + source_FilePass);
		System.out.println("target_FilePass:" + target_FilePass);
		try {
			Files.copy(source_FilePass, target_FilePass);
			File reportfile = new File(base.downloadFilepath + "\\" + zipFolder);
			if (reportfile.exists()) {
				FileUtils.deleteDirectory(reportfile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		File reportfile = new File(base.downloadFilepath + "\\" + zip_Folder_Name);
		if (reportfile.exists()) {
			reportfile.delete();
		}

	}

	public static void move_PDF_RT(String folderName, String type) {
		Path source_FilePass = null;
		Path target_FilePass = null;

		source_FilePass = Paths.get(base.downloadFilepath + "\\" + pdf_File_Name);
		target_FilePass = Paths.get(base.downloadFilepath + "\\" + folderName + "\\" + type + ".pdf");
		FileUtils.deleteQuietly(new File(target_FilePass.toString()));

		try {
			Files.copy(source_FilePass, target_FilePass);
			File reportfile = new File(base.downloadFilepath + "\\" + pdf_File_Name);
			if (reportfile.exists()) {
				reportfile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void move_PDF(String folderName, String type) {
		Path source_FilePass = null;
		Path target_FilePass = null;

		source_FilePass = Paths.get(base.downloadFilepath + "\\" + pdf_File_Name);
		target_FilePass = Paths.get(base.downloadFilepath + "\\" + folderName + "\\" + type + ".pdf");

		try {
			Files.copy(source_FilePass, target_FilePass);
			File reportfile = new File(base.downloadFilepath + "\\" + pdf_File_Name);
			if (reportfile.exists()) {
				reportfile.delete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void set_ResidentialAddress(WebElement tbox_address_search, WebElement tbox_address_select,
			String address) throws InterruptedException {

		// Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		PageObject_GenericMethods.scrollIntoView(tbox_address_search);
		tbox_address_search.sendKeys(address);
		// Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		tbox_address_select.click();

	}

	@SuppressWarnings("null")
	public static String getTFN() throws IOException {
		DataFormatter formatter = new DataFormatter();
		String TFN = null;
		File file = new File(System.getProperty("user.dir") + "\\\\src\\\\main\\\\java\\\\resources\\\\API.xlsx");
		FileInputStream fis = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("TFN");
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();

		for (int i = firstRowNum + 1; i < lastRowNum + 1; i++)

		{
			XSSFRow row1 = (XSSFRow) sheet.getRow(i);
			Cell cell1 = row1.getCell(1);
			if (cell1.getStringCellValue().contains("No")) {
				Cell cell0 = row1.getCell(0);
				TFN = formatter.formatCellValue(cell0);
				System.out.println("TFN: " + TFN);
				cell1.setCellValue("Yes");

				// Close input stream
				fis.close();
				FileOutputStream fos = new FileOutputStream(file);

				// write data in the excel file
				XSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
				workbook.write(fos);

				// FileOutputStream excelResultsFile = null;
				// workbook.write(excelResultsFile);

				// close output stream
				fos.close();
				workbook.close();
				break;
			}
		}

		return TFN;

	}

	public static void clickAndCheck(WebElement actualElement, String str, WebElement expectedElement)
			throws InterruptedException {

		PageObject_GenericMethods.Highlight_Element(actualElement);
		PageObject_GenericMethods.actionClick(actualElement, str);
		// new WebDriverWait(driver, 120)
		// .until(ExpectedConditions.visibilityOf(expectedElement));
		Thread.sleep(3000);
		for (int i = 0; i <= 5; i++) {
			try {
				// PageObject_GenericMethods.Highlight_Element(expectedElement);
				if (expectedElement.isDisplayed()) {

					System.out.println("clicked on -" + str);
					break;
				} else {
					System.out.println("click failed-" + i);
				}
			} catch (Exception e) {
				PageObject_GenericMethods.Highlight_Element(actualElement);
				PageObject_GenericMethods.actionClick(actualElement, str);
				Thread.sleep(5000);
				System.out.println("catch ----clickAndCheck");
			}
		}
	}

	public static void clickOnceAndCheck(WebElement actualElement, String str, WebElement expectedElement)
			throws InterruptedException {

		PageObject_GenericMethods.Highlight_Element(actualElement);
		PageObject_GenericMethods.actionClick(actualElement, str);
		// new WebDriverWait(driver, 120)
		// .until(ExpectedConditions.visibilityOf(expectedElement));
		Thread.sleep(3000);
		for (int i = 0; i <= 5; i++) {
			try {
				// PageObject_GenericMethods.Highlight_Element(expectedElement);
				if (expectedElement.isDisplayed()) {

					System.out.println("clicked on -" + str);
					break;
				} else {
					System.out.println("click failed-" + i);
				}
			} catch (Exception e) {

				Thread.sleep(2000);
				System.out.println("catch ----clickAndCheck");
			}
		}
	}

	public static void deleteCover(WebElement actualElement, String str, WebElement expectedElement)
			throws InterruptedException {

		PageObject_GenericMethods.Highlight_Element(actualElement);
		PageObject_GenericMethods.actionClick(actualElement, str);
		// new WebDriverWait(driver, 120)
		// .until(ExpectedConditions.visibilityOf(expectedElement));
		Thread.sleep(1000);
		for (int i = 0; i <= 5; i++) {
			try {
				// PageObject_GenericMethods.Highlight_Element(expectedElement);
				if (expectedElement.isDisplayed()) {

					System.out.println("clicked on -" + str);
					PageObject_GenericMethods.actionClick(expectedElement, "confirmDelete");

					break;
				} else {
					System.out.println("click failed-" + i);
				}
			} catch (Exception e) {
				PageObject_GenericMethods.Highlight_Element(actualElement);
				PageObject_GenericMethods.actionClick(actualElement, str);
				Thread.sleep(1000);
				System.out.println("catch ----clickAndCheck");
			}
		}
	}

	public static void clickAndCheck_withoutScroll(WebElement actualElement, String str, WebElement expectedElement)
			throws InterruptedException {

		// PageObject_GenericMethods.Highlight_Element(actualElement);
		// PageObject_GenericMethods.actionClick(actualElement,str);
		// waitForVisibility(actualElement);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", actualElement);

//		try {
//			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(expectedElement));
//		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
//			new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOf(expectedElement));
//		}

		Thread.sleep(1000);
		for (int i = 0; i <= 5; i++) {
			try {
				PageObject_GenericMethods.Highlight_Element(expectedElement);
				if (expectedElement.isDisplayed()) {

					System.out.println("clicked on -" + str);
					break;
				} else {
					System.out.println("click failed-" + i);
				}
			} catch (Exception e) {
				// PageObject_GenericMethods.Highlight_Element(actualElement);
				// PageObject_GenericMethods.actionClick(actualElement, str);

				// JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", actualElement);
				Thread.sleep(1000);
				System.out.println("catch ----clickAndCheck");
			}
		}

	}

	public static void extentReport_AutomatedTestResults() throws IOException {

		// createReport();
		String testName;

		// Map<String, Integer> testResultRecorder=new HashMap<String, Integer>();
		Set<Entry<String, Integer>> entries = testResultRecorder.entrySet();
		// TreeMap keeps all entries in sorted order
		TreeMap<String, Integer> sorted = new TreeMap<>(testResultRecorder);
		Set<Entry<String, Integer>> mappings = sorted.entrySet();

		// for(Entry<String, Integer> mapping : mappings)
		// { System.out.println(mapping.getKey() + " ==> " + mapping.getValue()); }

		// for (String i:testResultRecorder.keySet())
		// for (String i : sorted.keySet()) {

		// testName = i;
		// logger_new = PageObject_GenericMethods.report_new.createTest(i);
		// if (testResultRecorder.get(i) == 1) {
		// PageObject_GenericMethods.logger_new.pass(testName);
		// PageObject_GenericMethods.logger_new.info(CucumberScenariosDoc.testDetails(testName));

//			} else {
		// PageObject_GenericMethods.logger_new.fail(testName);
		// PageObject_GenericMethods.logger_new.info(CucumberScenariosDoc.testDetails(testName));
		// }
		// }

		if (testResultRecorder.get("testStatus") == 0) {
			PageObject_GenericMethods.logger_new.fail("Test failed, refer to individual result file for details");

		}
		PageObject_GenericMethods.report_new.flush();
	}

	public static void saveTestResult_final(String testName) throws IOException {
		PageObject_GenericMethods.logger_new.pass(testName);
		testResultRecorder.put("testStatus", 1);
	}

	public static String todaysDate_yyyyMMdd() {
		Date todayDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String todayString = formatter.format(todayDate);
		return todayString;
	}

	public static String todaysDate_ddMMMMyyyy_WithSpaces() {
		Date todayDate = Calendar.getInstance().getTime();
		return date_ddMMMMyyyy_WithSpaces(todayDate);
	}

	public static String getMonth() throws ParseException {
		Date todayDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
		String todayString = formatter.format(todayDate);
		Date d = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH).parse(todayString);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
		return monthName;
	}

	public static String nextYearsDate_ddMMMMyyyy_WithSpaces() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		return date_ddMMMMyyyy_WithSpaces(nextYear);
	}

	public static String todaysDate_ddMMyyyy_withslashes() {
		Date todayDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
		String todayString = formatter.format(todayDate);
		return todayString;
	}

	public static String date_ddMMMMyyyy_WithSpaces(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("d MMMM yyyy");
		String todayString = formatter.format(date);
		return todayString;
	}

	public static String todaysDate_YYYYMMDD() {
		Date todayDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String todayString = formatter.format(todayDate);
		return todayString;
	}

	public static String todaysDate_ddMMyyyy_AU_Format() {
		Date todayDate = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String todayString = formatter.format(todayDate);
		return todayString;
	}

	public static String nextYearDate_YYYYMMDD() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1); // to get previous year add -1
		Date nextYear = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String nextYearString = formatter.format(nextYear);
		return nextYearString;
	}

	public static void createReport_IRESS() {

		// String timestamp=new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new
		// Date());
		// String fileName = folder+"_"+timestamp+".html";
		File reportfile = new File(System.getProperty("user.dir") + "\\TestResults\\IRESS_" + IRESS + ".html");

		ExtentHtmlReporter extent = new ExtentHtmlReporter(reportfile);
		report = new ExtentReports();
		report.attachReporter(extent);

	}

	public static String captureScreenShot(WebDriver driver) throws IOException {
		String timeStamp;
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = downloadFilepath + "/Screenshots/" + timeStamp + "_FullScreen.png";
		File target = new File(dest);

		FileUtils.copyFile(src, target);
		// return dest;

		String screenshotPath = ".//Screenshots/" + timeStamp + "_FullScreen.png";
		return screenshotPath;
	}

	public static String captureScreenShot_complete(WebDriver driver) throws IOException {
		String timeStamp;
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String dest = downloadFilepath + "/Screenshots/" + timeStamp + "_FullScreen.png";
		File target = new File(dest);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		try {
			ImageIO.write(screenshot.getImage(), "PNG", new File(dest));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		String screenshotPath = ".//Screenshots/" + timeStamp + "_FullScreen.png";
		return screenshotPath;
	}

	public static void sendKeysDown(WebElement Element, int times) {

		Element.click();
		for (int i = 0; i < times; i++) {
			Element.sendKeys(Keys.ARROW_DOWN);
		}

	}

	public static void wait_tillTextEquals(WebElement elem, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 30, 1000);
		wait.until(waitForTextInElementEquals(elem, text));

	}

	public static ExpectedCondition<Boolean> waitForTextInElementEquals(WebElement elem, String text) {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					String elementText = elem.getText();
					return elementText.equals(text);
				} catch (StaleElementReferenceException var3) {
					return null;
				}
			}

			@Override
			public String toString() {
				return String.format("text ('%s') to be present in element %s", text, elem);
			}
		};
	}

	private static void unzip(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}