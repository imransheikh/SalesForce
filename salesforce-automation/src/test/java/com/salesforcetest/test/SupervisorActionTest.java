package com.salesforcetest.test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.SupervisorScenario;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

public class SupervisorActionTest {
	private static WebDriver driver;

	private SupervisorScenario supervisor = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;

	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		
		prefs.put("profile.default_content_setting_values.notifications", 2);

		        // Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		        // Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//new SalesforceLogin(driver).login(Constants.supervisor_salesforce_username, Constants.supervisor_salesforce_password);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Branch Chief");
		supervisor = new SupervisorScenario(driver);
	}

	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		supervisor.setTestReporter(testReporter);
	}

	@Test(testName = "Scenario_10_1", description = "Scenario 10.1 - Approve Service Item", priority = 1)
	public void approve_service_item_S10_1() throws IOException {
		supervisor.approve_service_item();
	}
	
	@Test(testName = "Scenario_10_2", description = "Scenario 10.1 - Reject Service Item", priority = 2)
	public void reject_service_item_S10_2() throws IOException {
		//driver.get(Constants.salesforce_url);
		supervisor.reject_service_item();
	}
	
	@Test(testName = "Scenario_11_1", description = "Scenario 11.1 - Reassign Service Item", priority = 3)
	public void reassign_service_item_S11_1() throws IOException {
		//driver.get(Constants.salesforce_url);
		String newUser = "Privacy Staff";
		supervisor.reassign_service_item(newUser);
	}
	
	@Test(testName = "Scenario_12_1", description = "Scenario 12.1 - Setting up training", priority = 4)
	public void setting_up_training_S12_1() throws IOException {
		//driver.get(Constants.salesforce_url);

		supervisor.set_up_training();
	}
	
//	@Test(testName = "Scenario_13_1", description = "Scenario 13.1 - Creating chatter Groups", priority = 5)
//	public void creating_chatter_group_S13_1() {
//		driver.get(Constants.salesforce_url);
//
//		supervisor.creating_chatter_group();
//	}
	
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
