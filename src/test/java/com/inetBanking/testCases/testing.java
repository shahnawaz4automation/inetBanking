package com.inetBanking.testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class testing {
	WebDriver  driver;

	@BeforeClass
	public void setup() {
		ChromeOptions co = new ChromeOptions();
		//co.setBrowserVersion("116");
		co.setBinary("C:\\Users\\shah4\\.cache\\selenium\\chrome\\chrome-win64\\chrome.exe");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
	}

	@Test
	public void test() throws Exception {
		driver.get("https://demo.guru99.com/v4/");
		String pageTitle = driver.getTitle();
		System.out.println("Page title is: "+ pageTitle);
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("shahnawazm");
		Thread.sleep(1000);
		String text = driver.findElement(By.xpath("//div//h2[contains(text(),'Guru99 Bank')]")).getText();
		System.out.println(text);
		String text1 = driver.findElement(By.xpath("//div//h2[contains(text(),'Guru99 Bank')]")).getAttribute("class");
		System.out.println(text1);
//		System.out.println("URL loaded and entered username");
//		String currentUrl = driver.getCurrentUrl();
//		System.out.println("Current URL is: "+ currentUrl);
//		driver.navigate().refresh();
//		Thread.sleep(1000);
//		driver.navigate().back();
//		Thread.sleep(1000);
//		driver.navigate().forward();
//		driver.navigate().to("https://demo.guru99.com/v4/");
//		List<WebElement> list = driver.findElements(By.xpath("//input[@name='uid']"));
//		System.out.println("Count of found elements: "+list.size());	
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}