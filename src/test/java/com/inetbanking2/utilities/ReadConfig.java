package com.inetbanking2.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	//First we need to create properties class object.
	Properties pro;
	//then create a constructor
	public ReadConfig() {

		File src = new File("./\\Configuration\\config.properties"); // src is variable referring to config.properties
																		// file

		try {
			FileInputStream fis = new FileInputStream(src); //we are reading the data from above file
			pro = new Properties(); 
			pro.load(fis); // using this object we are loading the file.
		} catch (Exception e) {
			System.out.println("Error message if the file is not there: " + e.getMessage());
		}
	}
	
	public String getApplicatoinURL() {
		String url = pro.getProperty("baseURL");
		return url;
	}
	
	public String getUsername() {
		String username = pro.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password = pro.getProperty("password");
		return password;
	}
	
	public String getName() {
		String name = pro.getProperty("name");
		return name;
	}
	
	public String getexecution_env() {
		String env = pro.getProperty("execution_env");
		return env;
	}
}


