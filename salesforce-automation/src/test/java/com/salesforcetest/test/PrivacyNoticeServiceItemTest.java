package com.salesforcetest.test;

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
import com.salesforcetest.main.MultiTypeServiceItemCreator;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

public class PrivacyNoticeServiceItemTest {
	private static WebDriver driver;

	private MultiTypeServiceItemCreator serviceItemCreator = null;

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
		serviceItemCreator = new MultiTypeServiceItemCreator(driver);

		serviceItemCreator = new MultiTypeServiceItemCreator(driver);
		//Privacy Staff NOT Training
		//new SalesforceLogin(driver).login(Constants.privacy_not_training_username, Constants.privacy_not_training_password);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff NOT Training");
	}
	
	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		serviceItemCreator.setTestReporter(testReporter);
	}

	@Test(	testName = "Scenario_14_1", 
			description = "Scenario 14.1 - Create a Privacy Notice Service Item", 
			priority = 1)
	public void create_pn_service_item_manually_test_Scenario_14_1() throws Exception {
		String contact = "Privacy Staff NOT Training";
		serviceItemCreator.create_privacy_service_item_manually(contact);
	}
	
	@Test(	testName = "Scenario_14_2", 
			description = "Scenario 14.2 - Send Privacy Notice to OCC without Approval", 
			priority = 2,
			dependsOnMethods = { "create_pn_service_item_manually_test_Scenario_14_1" })
	public void approve_pn_service_item_manually_test_Scenario_14_2() throws Exception {
		serviceItemCreator.send_email_from_privacy_notice(Constants.sample_occ_email);
	}
	
	@Test(testName = "Scenario_14_3",
			description = "Scenario 14.3 - closing Privacy Notice Service Item ",
			priority = 3,
			dependsOnMethods = { "approve_pn_service_item_manually_test_Scenario_14_2" })
	public void close_pn_service_item_manually_test_Scenario_14_3() throws Exception {
		serviceItemCreator.close_privacy_service_item();
	}
	
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		extent.endTest(testReporter);
	}

	@AfterClass(alwaysRun = true)
	public void flushReporter() {
		extent.flush();
		//driver.quit();
	}
}
