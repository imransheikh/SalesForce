package com.salesforcetest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.salesforcetest.main.IncidentManagementScenario;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Utils;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Driver;
import com.salesforcetest.shared.Incident;

/**
 * Scenario 2
 * 
 * @author Zaim3
 *
 */
public class IncidentManagementScenarioTest {
	
	private static WebDriver driver = Driver.getDriver();
	
	private IncidentManagementScenario ims = null;
	
	@BeforeClass
	public void init() {
		ims = new IncidentManagementScenario(driver);
	}
	
	@Test(testName = "Scenario_2_1", description = "Scenario 2.1", priority=1)
	public void IncidentManagementScenario_Test_S21() {
		Incident incident = new Incident(Constants.incident_msg, Constants.incident_subject, Constants.attachment_url);

		Reporter reporter = new Reporter(Constants.reporter_email,
				Constants.reporter_username, Constants.reporter_password,
				Constants.reporter_account_url, incident, Constants.incident_report_email);

		ims.send_report_email_from_gmail(reporter);
		
		Utils.switch_to_another_tab(driver);
		
		ims.login_saleforce(Constants.salesforce_username, Constants.salesforce_password);

		ims.select_and_assert_incident_queue();
	}
	
	@Test(testName = "Scenario_2_2", description = "Scenario 2.2", priority=2)
	public void IncidentManagementScenario_Test_S22() {
		driver.get(Constants.salesforce_url);

		ims.select_incident_and_change_owner();
	}
	
	@Test(testName = "Scenario_2_4", description = "Scenario 2.4", priority=4)
	public void IncidentManagementScenario_Test_S24() {
		driver.get(Constants.salesforce_url);

		ims.verify_submitter_voilator_on_service_item();
	}
	
	@Test(testName = "Scenario_2_5", description = "Scenario 2.5", priority=5)
	public void IncidentManagementScenario_Test_S25() {
		driver.get(Constants.salesforce_url);

		Incident incident = new Incident(Constants.incident_msg, Constants.incident_subject, Constants.attachment_url_reply);
		Reporter reporter = new Reporter(Constants.reporter_email,
				Constants.reporter_username, Constants.reporter_password,
				Constants.reporter_account_url, incident, Constants.incident_report_email);
		
		ims.select_service_item_and_send_email(reporter);
	}
	
	@Test(testName = "Scenario_2_6", description = "Scenario 2.6", priority=6)
	public void IncidentManagementScenario_Test_S26() {
		driver.get(Constants.salesforce_url);
		ims.closing_service_item();
	}
	
	@AfterClass
	public void destroy() {
//		if(driver != null)
//		driver.close();
	}
}
