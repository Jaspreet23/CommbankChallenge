package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

//import jdk.internal.jline.internal.Log;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//@SuppressWarnings("restriction")
public class base {

	// public static WebDriver driver;
	public static WebDriver driver;
	public static Properties prop;
	public static String AUTO_ENV;
	public static Map<String, String> data_Map = new HashMap<String, String>();
	public static String testType = "";
	public static String downloadFilepath;
	public static boolean createReport = false;
	public static Map<String, Integer> testResultRecorder = new HashMap<String, Integer>();

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			getScreenshot2();
		}

	}

	@SuppressWarnings({ "deprecation", "resource" })
	public static WebDriver initializeDriver() throws IOException {
		// AUTO_ENV=System.getProperty("AUTO_ENV");
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/resources/data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			// execute in chrome driver
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//main//resources//drivers//chromedriver.exe");
			downloadFilepath = System.getProperty("user.dir") + "\\TestResults";
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE);
			// options.addArguments("headless");
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");

			options.addArguments("--dns-prefetch-disable");
			options.addArguments("--disable-gpu");
			// options.addArguments("--auto-open-devtools-for-tabs");
			options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// WebDriver driver = new ChromeDriver(options);
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(3L, TimeUnit.SECONDS);

		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "//src//main//resources//drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "//src//main//resources//drivers//IEDriverServer.exe");

			driver = new InternetExplorerDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if (browserName.equals("msedge")) {
			System.setProperty("webdriver.edge.driver",
					System.getProperty("user.dir") + "//src//main//resources//drivers//msedgedriver.exe");

			driver = new EdgeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String cur_browserName = cap.getBrowserName().toLowerCase();
		System.out.println(browserName);
		String os = cap.getPlatform().toString();
		System.out.println(os);
		String v = cap.getVersion().toString();
		System.out.println(v);
		return driver;

	}

	public static void loadPropertiesFile() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);

	}

	public static void getScreenshot2() throws IOException {
		String timeStamp;
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// WebDriver returned = new Augmenter().augment(driver);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "PNG", new File(
				System.getProperty("user.dir") + "/TestResults/Screenshots/_" + timeStamp + "_" + "FullScreen.png"));
	}


}
