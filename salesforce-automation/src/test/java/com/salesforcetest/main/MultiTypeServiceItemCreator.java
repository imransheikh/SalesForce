package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;

public class MultiTypeServiceItemCreator extends CommonServiceItemTestScenario {

	private WebDriver driver;
	public static String screenShotPath;
	private ExtentTest testReporter;

	private String serviceItem = null;

	public String getServiceItem() {
		return serviceItem;
	}

	public MultiTypeServiceItemCreator(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}

	public String create_pia_service_item_manually(String contact) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			// Create PI service item
			// For PIA - Privacy Impact Assessment
			// For SORN - System of Records Notice
			// For Privacy Notice - Privacy Notice
			serviceItem = serviceAction.create_service_item_manually("Privacy Impact Assessment", null, contact);

			testReporter.log(LogStatus.PASS, "create_pi_service_item_manually");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_pi_service_item_manually");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
		
		return serviceItem;
	}

	public void send_email_from_pia_service_item(String toEmail) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			// trying sending service item to DHS
			serviceAction.send_email_from_service_item_from_dhs(toEmail);

			testReporter.log(LogStatus.PASS, "verify_sending_email_to_dhs_from_pi_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify_sending_email_to_dhs_from_pi_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void send_pia_service_item_for_approval() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			// Submit for approval with comments
			serviceAction.submit_service_item_for_approval("S4.3 - Sending PI Service Item for approval");

			testReporter.log(LogStatus.PASS, "verify_sending_pi_service_item_for_approval");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify_sending_pi_service_item_for_approval");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void close_pia_service_item() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			// Submit for approval with comments
			serviceAction.close_pia_service_item();

			testReporter.log(LogStatus.PASS, "close_pia_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "close_pia_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void create_sorn_service_item_manually(String contact) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceItem = serviceAction.create_service_item_manually("System of Records Notice", null, contact);

			testReporter.log(LogStatus.PASS, "create_sorn_service_item_manually");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_sorn_service_item_manually");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
	
	public void close_sorn_service_item() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.close_sorn_service_item();

			testReporter.log(LogStatus.PASS, "close_sorn_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "close_sorn_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void create_privacy_service_item_manually(String contact) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceItem = serviceAction.create_service_item_manually("Privacy Notice", null, contact);

			testReporter.log(LogStatus.PASS, "create_privacy_service_item_manually");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_privacy_service_item_manually");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void send_email_from_privacy_notice(String toEmail) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.send_email(toEmail);

			testReporter.log(LogStatus.PASS, "send_email_from_privacy_notice");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "send_email_from_privacy_notice");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public String create_pta_service_item_manually(String attachmentUrl, String contact) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceItem = serviceAction.create_service_item_manually("Privacy Threshold Analysis", attachmentUrl,
					contact);

			testReporter.log(LogStatus.PASS, "create_pta_service_item_manually");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create_pta_service_item_manually");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
		}
		return serviceItem;
	}

	public void close_pta_service_item() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.close_pta_service_item();

			testReporter.log(LogStatus.PASS, "close_pta_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "close_pta_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void merge_service_items(String parent, String child) throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			System.out.println("Merge Use case");

			System.out.println("Parent service problem:" + parent);

			System.out.println("Child service problem:" + child);

			serviceAction.merge_service_item(parent, child);

			testReporter.log(LogStatus.PASS, "merge_service_item");

			serviceAction.select_service_item(parent);

			serviceAction.verify_sent_document_in_service_items(Constants.sorn_attachment_url);

			testReporter.log(LogStatus.PASS, "verify_sent_document_in_service_items_after_merge");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify_sent_document_in_service_items_after_merge");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void close_privacy_service_item() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.closing_privacy_item();

			testReporter.log(LogStatus.PASS, "close_privacy_service_item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "close_privacy_service_item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}

	}

}
