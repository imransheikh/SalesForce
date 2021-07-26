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
import com.salesforcetest.main.Iso_11790_Internal_Function;
import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

public class ISO_11790_Remove_Functionality {
private WebDriver driver;
	
	private Iso_11790_Internal_Function ims = null;
	
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
		
		ims = new Iso_11790_Internal_Function(driver);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff 2");
	}
	
	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		ims.setTestReporter(testReporter);
	}
	@Test(testName = "Scenario_1_1", description = "Scenario 1.1", priority=1)
	public void ISO11790_IncidentManagementScenario_Test_S1_SI_Creation() throws InterruptedException, IOException {
		ims.createNewIncidentSI();
	}
	@Test(testName = "Scenario_1_2", description = "Scenario 1.2", priority=2)
	public void ISO11790_IncidentManagementScenario_Test_S2_Type_Field() throws InterruptedException, IOException {
		ims.changeTheTypeField();
	}
	@Test(testName = "Scenario_1_3", description = "Scenario 1.3", priority=3)
	public void ISO11790_IncidentManagementScenario_Test_S3_Blank_Attachment() throws InterruptedException, IOException {
		ims.blankAttachmentValidation();
	}
	@Test(testName = "Scenario_1_4", description = "Scenario 1.4", priority=4)
	public void ISO11790_IncidentManagementScenario_Test_S4_Send_Mail() throws InterruptedException, IOException {
		ims.sendAMail();
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
