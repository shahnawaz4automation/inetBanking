package com.inetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws IOException, InterruptedException 
	{
		driver.get(baseURL);
		logger.info("URL is opened");
		
		LoginPage lp = new LoginPage(driver); // driver is coming from BaseClass
		lp.setUserName(username);
		logger.info("User name is provided");
		lp.setPassWord(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(3000);

		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();
		
		driver.switchTo().frame("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0");
		boolean ad = driver.getPageSource().contains("3rd party ad content");  
		
		if(ad=true) {
			driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		}
		driver.switchTo().defaultContent();
		
		logger.info("Providing customer details....");
		addcust.custName("Pavan");
		addcust.custgender("male");
		addcust.custdob("10", "15", "1985");
		Thread.sleep(3000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");

		String email = randomstring() + "@gmail.com"; // getting the randomstring() from BaseClass
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();

		Thread.sleep(3000);

		logger.info("Validation started....");
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

		if (res == true) {
			Assert.assertTrue(true);
			logger.info("Test case passed....");
		} else {
			captureScreen(driver, "addNewCustomer"); //calling captureScreen() from baseclass
			Assert.assertTrue(false);
			logger.info("Test Case Failed....");
		}

	}
}
