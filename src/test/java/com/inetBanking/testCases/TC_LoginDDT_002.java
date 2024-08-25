package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.inetBanking.pageObjects.LoginPage;
import com.inetbanking2.utilities.XLUtils;


public class TC_LoginDDT_002 extends BaseClass{
	@BeforeTest
	public void startReporter() {
		initializeReport();
	}
	@Parameters({"os","browser"})
	@Test(dataProvider =  "LoginData")  // data provider name we need to specify here
	public void LoginDDT(String user, String pwd, String os, String browser) { //LoginDDT will take 2 parameters so we need to specify - user,pwd
		driver.get(baseURL);
		logger.info("URL is opened");
		
		String methodName = new Exception().getStackTrace()[0].getMethodName();
		test = extent.createTest(methodName, "Launch browser and website").assignAuthor("shahnawaz")
				.assignCategory("Smoke Test").assignDevice(browser);
		logger.info("URL is opened");
		test.log(Status.PASS, "user lauched website");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Username is entered");
		lp.setPassWord(pwd);
		logger.info("Passworkd is entered");

		lp.clickSubmit();
		logger.info("Clicked on submit");	
		
		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // close alert
			driver.switchTo().defaultContent(); // will focus on main page 
			Assert.assertTrue(false);
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent() { // user defined method to check whether alert is present or no.
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/*
	 * Below is the dataprovider method, internally which will get the data from
	 * excel and store it in the 2D Array and will return the 2D Array
	 */
	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetbanking2/testData/LoginData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);// 1 0

			}
		}
		return logindata;
	}

}
