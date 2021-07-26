package com.salesforcetest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.salesforcetest.main.ComplianceManagementScenario;
import com.salesforcetest.shared.Compliance;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Driver;
import com.salesforcetest.shared.Reporter;

public class ComplianceManagementScenarioTest {
	private static WebDriver driver = Driver.getDriver();
	
	private ComplianceManagementScenario cms = null;
	
	@BeforeClass
	public void init() {
		cms = new ComplianceManagementScenario(driver);
	}
	
	@Test(testName = "Scenario_3_1", description = "Scenario 3.1", priority=3)
	public void ComplianceManagementScenario_Test_S31() {
		Compliance compliance = new Compliance(Constants.compliance_msg, Constants.compliance_subject, Constants.attachment_url);

		Reporter reporter = new Reporter(Constants.reporter_email,
				Constants.reporter_username, Constants.reporter_password,
				Constants.reporter_account_url, compliance, Constants.compliance_report_email);

		cms.send_report_email_from_gmail(reporter);
		
		cms.switch_to_another_tab();

		cms.login_saleforce(Constants.salesforce_username, Constants.salesforce_password);

		cms.select_and_assert_compliance_queue();
		
	}
	
	@Test(testName = "Scenario_2_2", description = "Scenario 2.1", priority=4)
	public void ComplianceManagementScenario_Test_S32() {
		cms = new ComplianceManagementScenario(driver);
		
		cms.open_compliance_ticket();
	}
}
