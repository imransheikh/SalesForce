package com.salesforcetest.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
//import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.shared.Driver;
import com.salesforcetest.shared.ExtentReporter;

public class ReportingTest {
	
	WebDriver driver = Driver.getDriver();
	
	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private static ExtentTest logger;
	
	
	/**
	 * Test Pass example
	 */
	@Test(testName = "Scenario_2_1", description = "Scenario 2.1", priority=1)
	public void reportTest() {
		logger = extent.startTest("reportTest");
		Assert.assertTrue(true);
		logger.log(LogStatus.PASS, "Creating report Test Case Status is passed");
	}

	@AfterMethod
	public void getResult() {
//		if (result.getStatus() == ITestResult.FAILURE) {
//			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
//			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
//		}
//		// ending test
//		// endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}

	@AfterTest
	public void flushReport() {
		// writing everything to document
		// flush() - to write or update test information to your report.
		extent.flush();
	}
	
	@AfterSuite
	public void endReport() {
		// Call close() at the very end of your session to clear all resources.
		// If any of your test ended abruptly causing any side-affects (not all logs
		// sent to ExtentReports, information missing), this method will ensure that the
		// test is still appended to the report with a warning message.
		// You should call close() only once, at the very end (in @AfterSuite for
		// example) as it closes the underlying stream.
		// Once this method is called, calling any Extent method will throw an error.
		// close() - To close all the operation
		try {
		driver.quit();
		extent.close();
		} catch (Exception e) {
			
		}
	}
}
