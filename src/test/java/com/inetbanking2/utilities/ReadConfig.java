package com.inetbanking2.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./\\Configuration\\config.properties"); // src is variable referring to config.properties
																		// file

		try {
			FileInputStream fis = new FileInputStream(src);
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
	
	public String getChromePath() {
		String chromepath = pro.getProperty("chromepath");
		return chromepath;
	}
	
	public String getEdgePath() {
		String edgepath = pro.getProperty("edgepath");
		return edgepath;
	}
	
	public String getFirefoxPath() {
		String firefoxpath = pro.getProperty("firefoxpath");
		return firefoxpath;
	}
	
	public String getName() {
		String name = pro.getProperty("name");
		return name;
	}
}


