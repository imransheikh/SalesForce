package com.salesforcetest.main;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.SupervisorAction;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;

public class SupervisorScenario {

	private WebDriver driver;
	public static String screenShotPath;
	private ExtentTest testReporter;

	private SupervisorAction action = null;

	public SupervisorScenario(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}

	public void approve_service_item() throws IOException {
		action = new SupervisorAction(driver);
		try {
			action.approve();
			testReporter.log(LogStatus.PASS, "approve_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "approve_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}

	}

	public void reject_service_item() throws IOException {
		action = new SupervisorAction(driver);
		try {
			action.reject();
			testReporter.log(LogStatus.PASS, "reject_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "reject_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void reassign_service_item(String newUser) throws IOException {
		action = new SupervisorAction(driver);
		try {
			action.reassign(newUser);
			testReporter.log(LogStatus.PASS, "reassign_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "reassign_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void set_up_training() throws IOException {
		action = new SupervisorAction(driver);
		try {
			//In Training checked
			action.setup_training();
			
			//In Training unchecked
			action.setup_training();
			
			testReporter.log(LogStatus.PASS, "set_up_training");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "set_up_training");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}

	public void creating_chatter_group() throws IOException {
		action = new SupervisorAction(driver);
		try {
			// TODO implement scenario
			testReporter.log(LogStatus.PASS, "creating_chatter_group");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "creating_chatter_group");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getMessage(),"PASS");
			e.printStackTrace();
		}
	}
}
