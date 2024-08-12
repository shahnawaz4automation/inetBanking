package com.inetBanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//this class has constructor, elements and action methods
public class LoginPage {
	// 1st create WebDriver variable
	WebDriver ldriver;

	// 2nd create constructor (Purpose:	This constructor is part of the Page Object Model (POM) design pattern, 
	//where each web page of the application under test is represented by a separate class. 
	//The purpose of this constructor is to set up the WebDriver instance for the LoginPage and initialize its web elements so that they can be interacted with in tests.)
	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this); //This line initializes the WebElements in the LoginPage class using Selenium's PageFactory.
		// PageFactory.initElements() - is a method that initializes all the WebElements annotated with @FindBy, @FindBys, or @FindAll in the class.
		//The rdriver argument is the WebDriver instance used to locate elements.
		//The this keyword refers to the current instance of the LoginPage class.
		//2. When you call PageFactory.initElements(rdriver, this);,you're telling Selenium's PageFactory to initialize all the elements (like buttons, text fields, etc.) in the current LoginPage object.
		//3. How is it used?: By passing this, you're essentially saying, "initialize the web elements in this particular instance of the LoginPage class." The PageFactory uses the WebDriver (rdriver) to locate the elements on the web page and then links them to the corresponding fields in the LoginPage class.
	}
	
	//Locating elements
	@FindBy(name = "uid")
	WebElement txtUserName;

	@FindBy(name = "password")
	WebElement txtPassWord;

	@FindBy(name = "btnLogin")
	WebElement btnLogin;
	
	@FindBy(xpath = "//a[contains(text(),'Log out')]")
	WebElement btnLogout;

	// Action methods for these 3 elements
	public void setUserName(String uname) {
		txtUserName.sendKeys(uname);
	}

	public void setPassWord(String pwd) {
		txtPassWord.sendKeys(pwd);
	}

	public void clickSubmit() {
		btnLogin.click();
	}
	
	public void clickLogout() {
		btnLogout.click();
	}

}
