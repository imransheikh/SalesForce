package com.salesforcetest.shared;

import java.io.File;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * This should be a singleton class
 * 
 * @author Zaim3
 *
 */
public class ExtentReporter {

	private ExtentReports extent;

	private ExtentTest logger;
	
	private static ExtentReporter extentReporter = null;

	private ExtentReporter() {
		
	}
	
	public static ExtentReporter getInstance() {
		if(extentReporter == null) {
			extentReporter = new ExtentReporter();
			
			
			// ExtentReports(String filePath,Boolean replaceExisting)
			// filepath - path of the file, in .htm or .html format - path where your report
			// needs to generate.
			// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
			// it
			// True (default): the file will be replaced with brand new markup, and all
			// existing data will be lost. Use this option to create a brand new report
			// False: existing data will remain, new tests will be appended to the existing
			// report. If the the supplied path does not exist, a new file will be created.
			ExtentReports extent = new ExtentReports(
					System.getProperty("user.dir") + File.separatorChar + "test-report/PrivacyRegressionFlowScriptRunResults"+randomDateTime1()+".html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("Host Name", "SeleniumAutomation")
					.addSystemInfo("Environment", "Automation Testing")
						.addSystemInfo("User Name", "Zaim Abid");
			// loading the external xml file (i.e., extent-config.xml) which was placed
			// under the base directory
			// You could find the xml file below. Create xml file in your project and copy
			// past the code mentioned below
			extent.loadConfig(new File(System.getProperty("user.dir") + File.separatorChar + "extent-config.xml"));
			
			extentReporter.setExtentReports(extent);
		}
			
		return extentReporter;
	}
	public static String randomDateTime1() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      //System.out.printf("Now random "+randomDateTime);
	      randomDateTime = "" + randomDateTime;
	      return randomDateTime;
	}
	public static ExtentReporter getInstanceWithFileName(String reportFileName) {
		//if(extentReporter == null) {
			extentReporter = new ExtentReporter();
			
			
			// ExtentReports(String filePath,Boolean replaceExisting)
			// filepath - path of the file, in .htm or .html format - path where your report
			// needs to generate.
			// replaceExisting - Setting to overwrite (TRUE) the existing file or append to
			// it
			// True (default): the file will be replaced with brand new markup, and all
			// existing data will be lost. Use this option to create a brand new report
			// False: existing data will remain, new tests will be appended to the existing
			// report. If the the supplied path does not exist, a new file will be created.
			System.out.println(System.getProperty("user.dir"));
			ExtentReports extent = new ExtentReports(
					System.getProperty("user.dir")+"//test-report//"+reportFileName+".html", true);
			// extent.addSystemInfo("Environment","Environment Name")
			extent.addSystemInfo("Host Name", "SeleniumAutomation")
					.addSystemInfo("Environment", "Automation Testing")
						.addSystemInfo("User Name", "Zaim Abid");
			// loading the external xml file (i.e., extent-config.xml) which was placed
			// under the base directory
			// You could find the xml file below. Create xml file in your project and copy
			// past the code mentioned below
			extent.loadConfig(new File(System.getProperty("user.dir") + File.separatorChar + "extent-config.xml"));
			
			extentReporter.setExtentReports(extent);
		//}
			
		return extentReporter;
	}
	private void setExtentReports(ExtentReports extent) {
		this.extent = extent;
	}
	
	public ExtentReports getExtentReports() {
		return extent;
	}
	
	/**
	 * Test Pass example
	 */
	@Test
	public void passTest() {
		// extent.startTest("TestCaseName", "Description")
		// TestCaseName – Name of the test
		// Description – Description of the test
		// Starting test
		logger = extent.startTest("passTest");
		Assert.assertTrue(true);
		// To generate the log when the test case is passed
		logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	}

	/**
	 * Test Pass example
	 */
	@Test
	public void failTest() {
		logger = extent.startTest("failTest");
		Assert.assertTrue(false);
		logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
	}

	@Test
	public void skipTest() {
		logger = extent.startTest("skipTest");
		throw new SkipException("Skipping - This is not ready for testing ");
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}

	@AfterTest
	public void endReport() {
		// writing everything to document
		// flush() - to write or update test information to your report.
		extent.flush();
		// Call close() at the very end of your session to clear all resources.
		// If any of your test ended abruptly causing any side-affects (not all logs
		// sent to ExtentReports, information missing), this method will ensure that the
		// test is still appended to the report with a warning message.
		// You should call close() only once, at the very end (in @AfterSuite for
		// example) as it closes the underlying stream.
		// Once this method is called, calling any Extent method will throw an error.
		// close() - To close all the operation
		extent.close();
	}
}
