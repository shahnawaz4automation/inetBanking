package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@BeforeTest
	public void startReporter() {
		initializeReport();
	}
	@Parameters({"os","browser"})
	@Test(invocationCount = 2)
	public void loginTest(String os, String browser) throws IOException {
		driver.get(baseURL);

		String methodName = new Exception().getStackTrace()[0].getMethodName();
		test = extent.createTest(methodName, "Launch browser and website").assignAuthor("shahnawaz")
				.assignCategory("Smoke Test").assignDevice(browser);
		logger.info("URL is opened");
		test.log(Status.PASS, "user lauched website");

		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is entered");
		lp.setPassWord(password);
		logger.info("Passworkd is entered");

		lp.clickSubmit();
		logger.info("Clicked on submit");

		String title = driver.getTitle();
		System.out.println(title);
		logger.info("Title of the opened page after submit is: " + title);

		if (driver.getTitle().equalsIgnoreCase("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} else {
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}

}
