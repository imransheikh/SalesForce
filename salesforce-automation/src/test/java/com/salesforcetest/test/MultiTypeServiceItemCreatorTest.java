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

public class MultiTypeServiceItemCreatorTest {

	private static WebDriver driver;

	private MultiTypeServiceItemCreator serviceItemCreator = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;
	
	private final String INTERNAL_CONTACT= "Test Automation Internal Contact";

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

		//new SalesforceLogin(driver).login(Constants.salesforce_username, Constants.salesforce_password);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff");
	}

	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		serviceItemCreator.setTestReporter(testReporter);
	}

	@Test(testName = "Scenario_4", description = "Scenario 4 - Creating Privacy Impact Assessment Service Item", priority = 1)
	public void create_pia_service_item_manually_test_Scenario_4() throws Exception {
		String contact = "Test Automation Internal Contact";
		serviceItemCreator.create_pia_service_item_manually(contact);
		
		serviceItemCreator.send_email_from_pia_service_item(Constants.dhs_email);
		
		serviceItemCreator.send_pia_service_item_for_approval();
	}

	@Test(testName = "Scenario_5_1", description = "Scenario 5.1 - Creating System of Records Notice Service Item", priority = 2)
	public void create_sorn_service_item_manually_test_Scenario_5_1() throws Exception {
		serviceItemCreator.create_sorn_service_item_manually(INTERNAL_CONTACT);
	}
	
	@Test(testName = "Scenario_5_2", description = "Scenario 5.2 - Closing System of Records Notice Service Item", priority = 3)
	public void close_sorn_service_item_manually_test_Scenario_5_2() throws Exception {
		serviceItemCreator.close_sorn_service_item();
	}

	@Test(testName = "Scenario_6", description = "Scenario 6 - Create a Privacy Notice Service Item", priority = 4)
	public void create_privacy_service_item_manually_test_Scenario_6() throws Exception {
		serviceItemCreator.create_privacy_service_item_manually(INTERNAL_CONTACT);
	}

	@Test(testName = "Scenario_7", description = "Scenario 7 - Merge Duplicate Service Items", priority = 5)
	public void merge_compliance_service_item_test_Scenario_7() throws Exception {
		serviceItemCreator.create_pta_service_item_manually(Constants.privacy_pia_attachment_url,INTERNAL_CONTACT);
		String parentServiceItemNo = serviceItemCreator.getServiceItem();
		
		serviceItemCreator.create_pta_service_item_manually(Constants.sorn_attachment_url,INTERNAL_CONTACT);
		String childServiceItemNo = serviceItemCreator.getServiceItem();
		//String parentServiceItemNo = "00021704";
		//String childServiceItemNo = "00021704";
		serviceItemCreator.merge_service_items(parentServiceItemNo, childServiceItemNo);
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
