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
import org.testng.annotations.Test;

import com.inetbanking2.utilities.ReadConfig;

public class BaseClass {
	//constructor will be invoked and the config.properties file will be loaded
	ReadConfig readConfig = new ReadConfig();

	public String baseURL = readConfig.getApplicatoinURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver; // initialized
	public static Logger logger;

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

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// File target = new File(System.getProperty("user.dir") +
		// "/Screenshots/"+tname+System.currentTimeMillis()+".png");
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
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
