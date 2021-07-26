package com.salesforcetest.main.uatg;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.uatg.CustomerSearchAction;

public class HD_ISO_VSC_Customer_Search_Main {
	private WebDriver driver;

	private ExtentTest testReporter;

	public HD_ISO_VSC_Customer_Search_Main(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}

	public void logged_in_with_internal_user(String user) {
		CustomerSearchAction customerSearch = new CustomerSearchAction(driver);

		try {
			customerSearch.login_internal_user(user);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void search_customer(String customer) {
		CustomerSearchAction customerSearch = new CustomerSearchAction(driver);

		try {
			customerSearch.search_customer(customer);

			testReporter.log(LogStatus.PASS, "create_external_account_and_contact");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_external_account_and_contact");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	public void update_customer() {
		CustomerSearchAction customerSearch = new CustomerSearchAction(driver);

		try {
			customerSearch.update_customer();

			testReporter.log(LogStatus.PASS, "create_external_account_and_contact");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_external_account_and_contact");
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
