package com.salesforcetest.mapper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.NewAccountScenario;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccountCreationMapper {
	
	private static WebDriver driver;

	private NewAccountScenario nas = null;

	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();

	private ExtentTest testReporter;
	
	public void init(String user) {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nas = new NewAccountScenario(driver);
		testReporter = extent.startTest("Account creation");
		nas.setTestReporter(testReporter);
		
		String[] credentials = Utils.getCredential(user);
		new SalesforceLogin(driver).login(credentials[0], credentials[1]);
	}

	@When("^Create Internal Account and Contact$")
	public void test_create_internal_account() throws InterruptedException, IOException {
		driver.get(Constants.salesforce_url);

		nas.create_internal_account_and_contact();
	}

	@When("^Create External Account and Contact$")
	public void test_create_external_account() throws InterruptedException, IOException {
		driver.get(Constants.salesforce_url);

		nas.create_external_account_and_contact();
	}
	
	public void getResult() {
		extent.endTest(testReporter);
	}

	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
