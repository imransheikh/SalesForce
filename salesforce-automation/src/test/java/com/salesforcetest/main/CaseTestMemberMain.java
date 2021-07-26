package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.ServiceItemCaseMember;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;

public class CaseTestMemberMain {

	private WebDriver driver;

	private ExtentTest testReporter;

	private ServiceItemCaseMember page;

	public CaseTestMemberMain(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;

	}

	public void add_member_in_service_item(String teamMember) {
		try {
			page = new ServiceItemCaseMember(driver, testReporter);
			page.add_member_in_service_item(teamMember);
			testReporter.log(LogStatus.PASS, "add_member_in_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "add_member_in_service_item");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void view_edit_si_as_team_member(String serviceItemNo) {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.view_edit_si_as_team_member(serviceItemNo);

			testReporter.log(LogStatus.PASS, "view_edit_si_as_team_member");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "view_edit_si_as_team_member");
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
}
