package com.salesforcetest.mapper.uatg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Customer_Search_Main;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_Customer_Search_Mapper {
	private static WebDriver driver;

	private HD_ISO_VSC_Customer_Search_Main main = null;
	
	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();
	
	private ExtentTest testReporter;

	@Given("^User is logged in with \"(.*)\"$")
	public void init(String user) {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		main = new HD_ISO_VSC_Customer_Search_Main(driver);
		
		testReporter = extent.startTest("HD_ISO_VSC_Customer_Search_Mapper");

		main.setTestReporter(testReporter);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}

	@When("^Internal User logged in with user \"(.*)\"$")
	public void logged_in_with_internal_user(String user) {
		main.logged_in_with_internal_user(user);
	}

	@When("^Search customer \"(.*)\"$")
	public void search_customer(String customer) {
		main.search_customer(customer);
	}
	
	@When("^Update the user$")
	public void update_customer() {
		main.update_customer();
	}
	
	@Then("^Close the reporter$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}

	@Then("^Close the browser$")
	public void flushReporter() {
		driver.quit();
	}
}
