package com.salesforcetest.test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.MultiTypeServiceItemCreator;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

public class Scenario15_ClosingPTAandPIA {
	private static WebDriver driver;

	private MultiTypeServiceItemCreator serviceItemCreator = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;

	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//new SalesforceLogin(driver).login(Constants.salesforce_username, Constants.salesforce_password);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff");
		serviceItemCreator = new MultiTypeServiceItemCreator(driver);
	}
	
	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		serviceItemCreator.setTestReporter(testReporter);
	}
	
	@Test(testName = "Scenario_15_1",
			description = "Scenario 15.1 - creating and closing PTA Service Item ",
			priority = 1)
	public void create_and_close_pta_service_item_manually_test_Scenario_15_1() throws Exception {
		String contact = "Test Automation Internal Contact";
		serviceItemCreator.create_pta_service_item_manually("", contact);
		
		serviceItemCreator.close_pta_service_item();
	}
	
	@Test(testName = "Scenario_15_2",
			description = "Scenario 15.2 - creating and closing PIA Service Item ",
			priority = 2)
	public void create_and_close_pia_service_item_manually_test_Scenario_15_2() throws Exception {
		String contact = "Test Automation Internal Contact";
		serviceItemCreator.create_pia_service_item_manually(contact);
		
		serviceItemCreator.close_pia_service_item();
	}
	
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		extent.endTest(testReporter);
	}

	@AfterClass(alwaysRun = true)
	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
