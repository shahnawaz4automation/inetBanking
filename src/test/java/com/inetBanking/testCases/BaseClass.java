package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking2.utilities.ReadConfig;

public class BaseClass {
	// constructor will be invoked and the config.properties file will be loaded
	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicatoinURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver; // initialized
	public static Logger logger;

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String br) {
		// Log4j configuation code lines
		logger = LogManager.getLogger(getClass());

		if (br.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (br.equals("edge")) {
			driver = new EdgeDriver();
		} else if (br.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public void initializeReport() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentSparkReport.html");
		sparkReporter.config().setDocumentTitle("AutomationReport");
		sparkReporter.config().setReportName("Automation Test Execution Report");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter); // using the extent reference we are attaching a report which is of type spark
		
	}

	public static String captureScreenshot(WebDriver driver) throws IOException {
		String FileSeparator = System.getProperty("file.separator"); // On Windows, it returns a backslash (\).
		String Extent_report_path = "." + FileSeparator + "Reports";
		String ScreenshotPath = Extent_report_path + FileSeparator + "screenshots";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotName = "screenshot" + Math.random() + ".png";
		String screenshotpath = ScreenshotPath + FileSeparator + screenshotName;
		File trg = new File(screenshotpath);
		FileUtils.copyFile(src, trg);
		return "." + FileSeparator + "screenshots" + FileSeparator + screenshotName;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		extent.flush();		
	}

	public String randomstring() {
		String generatedString = RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	}

	public String randomNum() {
		String generatedNumber = RandomStringUtils.randomNumeric(8);
		return generatedNumber;
	}
}
