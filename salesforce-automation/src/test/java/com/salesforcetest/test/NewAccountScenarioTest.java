package com.salesforcetest.test;

import java.io.IOException;
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
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.NewAccountScenario;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

public class NewAccountScenarioTest {

	private static WebDriver driver;

	private NewAccountScenario nas = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;

	
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nas = new NewAccountScenario(driver);

		//new SalesforceLogin(driver).login(Constants.salesforce_username, Constants.salesforce_password);
		new SalesforceLogin(driver).login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff");
	}

	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		nas.setTestReporter(testReporter);
	}

	@Test(testName = "Scenario_1_1", description = "Scenario 1.1 and 1.2", priority = 1)
	public void test_create_internal_account() throws InterruptedException, IOException {
		//driver.get(Constants.salesforce_url);

		nas.create_internal_account_and_contact();
	}

	@Test(testName = "Scenario_1_3", description = "Scenario 1.3 and 1.4", priority = 2)
	public void test_create_external_account() throws InterruptedException, IOException {
		//driver.get(Constants.salesforce_url);

		nas.create_external_account_and_contact();
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			testReporter.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			testReporter.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			testReporter.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(testReporter) : It ends the current test and prepares to create HTML
		// report
		extent.endTest(testReporter);
	}

	@AfterClass(alwaysRun = true)
	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
