package com.salesforcetest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.salesforcetest.main.NewAccountScenario;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Driver;

public class NewAccountScenarioTest {

	private static WebDriver driver = Driver.getDriver();
	
	private NewAccountScenario nas = null;
	
	@BeforeClass
	public void init() {
		nas = new NewAccountScenario(driver);
	}
	
	@Test(testName = "Scenario_1_1", description = "Scenario 1.1", priority=1)
	public void IncidentManagementScenario_Test_S26() {
		driver.get(Constants.salesforce_url);
		nas.closing_service_item();
	}
}
