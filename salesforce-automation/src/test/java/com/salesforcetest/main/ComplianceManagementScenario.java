package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Compliance;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Reporter;

public class ComplianceManagementScenario extends CommonServiceItemTestScenario{

	private WebDriver driver;
	public static String screenShotPath;
	private String serviceItemNumber;

	private ExtentTest testReporter;

	public ComplianceManagementScenario(WebDriver driver) {
		this.driver = driver;
	}

	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}
	
	public String getServiceItemNumber() {
		return serviceItemNumber;
	}

	public void send_report_email_from_gmail(Reporter reporter) throws Exception {
		try {
			
			String subject = ((Compliance) reporter.getType()).getSubject();
			System.out.println(subject);
			String msg = ((Compliance) reporter.getType()).getMessage();
			String attachment = ((Compliance) reporter.getType()).getAttachment();
			
			send_report_email_from_gmail(driver, reporter.getReportingEmail(), subject,
					msg, attachment);
			
			testReporter.log(LogStatus.PASS, "send_report_email_from_gmail");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "send_report_email_from_gmail");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}

	}
	
	public void send_report_email_from_gmail(String email, String subject, String msg, String attachment) throws Exception {
		try {
			send_report_email_from_gmail(driver, email, subject,
					msg, attachment);
			
			testReporter.log(LogStatus.PASS, "send_report_email_from_gmail");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "send_report_email_from_gmail");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void select_and_assert_compliance_queue() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_compliance_queue_list();

			serviceItemNumber = serviceAction.assert_compliance_name_get_service_item_number();
			if (serviceItemNumber == null || serviceItemNumber.isEmpty()) {
				throw new Exception("Service Item Number not generated");
			}
			testReporter.log(LogStatus.PASS, "select_and_assert_compliance_queue");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "select_and_assert_compliance_queue");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void select_compliance_and_change_ownership() throws Exception {
		//try {
			System.out.println(serviceItemNumber);
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_compliance_queue_list();

			serviceAction.select_serviceItem_and_change_owner(serviceItemNumber, Constants.compliance_owner);

			testReporter.log(LogStatus.PASS, "select_compliance_and_change_ownership");
//		} catch (Exception e) {
//			testReporter.log(LogStatus.FAIL, "select_compliance_and_change_ownership");
//			screenShotPath = GetScreenShot.capture(driver);
//			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
//			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
//			e.printStackTrace();
//		}
	}
	
	public void update_service_item_type() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);
			
			serviceAction.update_service_item_type_field();
			
			testReporter.log(LogStatus.PASS, "update_service_item_type");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "update_service_item_type");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void verify_status_change() throws Exception {
		try {
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_service_item(serviceItemNumber);

			serviceAction.verify_compliance_service_item_status_change(serviceItemNumber);
			
			testReporter.log(LogStatus.PASS, "verify_status_change_for_compliance");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify_status_change_for_compliance");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	/**
	 * send email from service item
	 * 
	 * @param reporter
	 * @throws Exception 
	 */
	public void select_service_item_and_send_email(String toEmail, String attachment, String msg) throws Exception {
		//serviceItemNumber = "00017076";
		try {
		Constants.compliance_subject = Constants.compliance_subject+"_Duplicate";
			Constants.email_subject = Constants.compliance_subject;
			if(!"".equals(msg)) {
				select_service_item_and_send_email(serviceItemNumber, driver, testReporter,toEmail,attachment,msg);
				testReporter.log(LogStatus.PASS, "select_service_item_and_send_email");
			}
			else {
				select_service_item_and_verify_attachment(serviceItemNumber, driver, testReporter,attachment);
				testReporter.log(LogStatus.PASS, "select_service_item_and_verify_attachment");
			}
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "select_service_item_and_send_email_and_verify_attachment");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}

	public void send_email_to_dhs(String dhsEmail) throws Exception {
		try {
			if (serviceItemNumber == null || serviceItemNumber.isEmpty())
				throw new Exception("Service Item Number not generated");

			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			serviceAction.select_service_item(serviceItemNumber);

			serviceAction.send_email_from_service_item_from_dhs(dhsEmail);

		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "send_email_to_dhs");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
		
	}
	
	public void submit_service_item_for_approval() throws Exception {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

			//Submit for approval with comments
			serviceAction.submit_service_item_for_approval("S3.8 Sending for approval");
			
			testReporter.log(LogStatus.PASS, "submit_service_item_for_approval");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "submit_service_item_for_approval");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		
	}
}
