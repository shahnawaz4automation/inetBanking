package com.inetbanking2.utilities;

import java.io.IOException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.inetBanking.testCases.BaseClass;

public class CustomListener implements ITestListener {
	//Listern - listens to the configured event then performs action (class/suite level)
	@Override
	//onTestSuccess this method will be invoked
	public void onTestSuccess(ITestResult result) {
		//if statement is just an extra check, without if also it will work. see onTestFailure its working
		if(result.getStatus()==ITestResult.SUCCESS) {
			BaseClass.test.log(Status.PASS,
					MarkupHelper.createLabel(result.getName().toUpperCase() + ": is PASS", ExtentColor.GREEN));	
		}	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//Purpose: This line logs the actual error message or exception that caused the test to fail.
		//result.getThrowable().getMessage(): This retrieves the message from the Throwable (exception) that was thrown when the test failed.
		//Example Output: It might output the actual exception message, such as "java.lang.AssertionError: expected [true] but found [false]".
		//Below gives Detailed exception message
		BaseClass.test.log(Status.FAIL, result.getThrowable().getMessage());
		//Purpose: This line logs a custom message indicating the test has failed.
		//MarkupHelper.createLabel: This is used to create a label with a specific text and color.
		//result.getName().toUpperCase(): This gets the name of the test method and converts it to uppercase.
		//ExtentColor.RED: This sets the color of the label to red, visually indicating failure.
		//Example Output: It might output something like "TESTMETHODNAME: is FAIL" in red color in the report.
		//Below gives clear indication of failure
		BaseClass.test.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName().toUpperCase() + ": is FAIL", ExtentColor.RED));

		try {
			BaseClass.test.addScreenCaptureFromPath(BaseClass.captureScreenshot(BaseClass.driver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
