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
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.IncidentManagementScenario;
import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Incident;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Utils;

/**
 * Scenario 2
 * 
 * @author Zaim3
 * 
 * Test 20 For test
 *
 */

public class IncidentManagementScenarioTest {
	
	private WebDriver driver;
	
	private IncidentManagementScenario ims = null;
	
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		
		ims = new IncidentManagementScenario(driver);
		//new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		//new SalesforceLogin(driver).fetchMailUrl("incident");
		new MailSender(driver).login(Constants.reporter_account_url, Constants.reporter_email, Constants.reporter_username, Constants.reporter_password);
		
		Utils.open_another_tab(driver);
		
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff 2");
	}
	
	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		ims.setTestReporter(testReporter);
	}
	
	@Test(testName = "Scenario_2_1", description = "Scenario 2.1", priority=1)
	public void IncidentManagementScenario_Test_S21() throws Exception {
		Incident incident = new Incident(Constants.incident_msg, Constants.incident_subject, Constants.sorn_attachment_url);

		Reporter reporter = new Reporter(Constants.reporter_email,
				Constants.reporter_username, Constants.reporter_password,
				Constants.reporter_account_url, incident, Constants.incident_report_email);
		
		//since login into sales force is taken care in incident test case. Just switching the tabs will work
		Utils.switch_to_tab(driver, 0);
		
		ims.send_report_email_from_gmail(reporter);
		
		//since login into sales force is taken care in incident test case. Just switching the tabs will work
		Utils.switch_to_tab(driver, 1);
		
		ims.select_and_assert_incident_queue();
	}
	
	@Test(testName = "Scenario_2_2", description = "Scenario 2.2", priority=2, dependsOnMethods = { "IncidentManagementScenario_Test_S21" })
	public void IncidentManagementScenario_Test_S22() throws Exception {
		Utils.switch_to_tab(driver, 1); // 1
				
		driver.get(driver.getCurrentUrl());

		ims.select_incident_and_change_owner();
	}
	
	@Test(testName = "Scenario_2_3", description = "Scenario 2.3", priority=3, dependsOnMethods = { "IncidentManagementScenario_Test_S22" })
	public void IncidentManagementScenario_Test_S23() throws Exception {
		Utils.switch_to_tab(driver, 1); // 1
		
		driver.get(driver.getCurrentUrl());

		ims.verify_submitter_voilator_on_service_item();
	}
	
	@Test(testName = "Scenario_2_4", description = "Scenario 2.4", priority=4)
	public void IncidentManagementScenario_Test_S24() throws Exception {
		Utils.switch_to_tab(driver, 1); //1
		
		//driver.get(Constants.salesforce_url);
		driver.get(driver.getCurrentUrl());
		
		ims.select_service_item_and_send_email(Constants.reporter_email, Constants.privacy_pia_attachment_url, Constants.incident_msg);
	}
	
	@Test(testName = "Scenario_2_5", description = "Scenario 2.5", priority=5, dependsOnMethods = { "IncidentManagementScenario_Test_S24" })
	public void IncidentManagementScenario_Test_S25() throws Exception {
		Utils.switch_to_tab(driver, 1); //1
		
		ims.select_service_item_and_send_email(Constants.reporter_email, Constants.privacy_pia_attachment_url,"");
	}

	@Test(testName = "Scenario_2_6", description = "Scenario 2.6", priority=6,dependsOnMethods = { "IncidentManagementScenario_Test_S23" })
	public void IncidentManagementScenario_Test_S26() throws Exception {
		Utils.switch_to_tab(driver, 1); //1
		
		driver.get(driver.getCurrentUrl());
		
		ims.closing_service_item();
	}
	
	@Test(testName = "Scenario_2_7", description = "Scenario 2.7", priority=7 ,dependsOnMethods = { "IncidentManagementScenario_Test_S26" })
	public void IncidentManagementScenario_Test_S27() throws Exception {
		Utils.switch_to_tab(driver, 0);
	
		ims.reply_closing_email_in_reporter_account();
		
		Utils.switch_to_tab(driver, 1);
		
		ims.verify_status_and_owner();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			testReporter.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			testReporter.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(testReporter) : It ends the current test and prepares to create HTML report
		extent.endTest(testReporter);
	}
	
	@AfterClass(alwaysRun=true)
	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
