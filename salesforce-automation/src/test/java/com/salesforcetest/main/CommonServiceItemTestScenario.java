package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class CommonServiceItemTestScenario {

	/**
	 * Sending email from service item and replying back with an attachment
	 * 
	 * @param serviceItemNumber
	 * @param driver
	 * @param testReporter
	 * @param attachment
	 * @param msg
	 * @throws Exception
	 */
	public static void select_service_item_and_send_email(String serviceItemNumber, WebDriver driver,
			ExtentTest testReporter, String toEmail, String attachment, String msg) throws Exception {

		if (serviceItemNumber == null || serviceItemNumber.isEmpty())
			throw new Exception("Service Item Number not generated");

		ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

		serviceAction.select_service_item(serviceItemNumber);

		serviceAction.send_email_from_service_item(toEmail);

		// switch to gmail
		Utils.switch_to_tab(driver, 0);

		MailSender sender = new MailSender(driver);

		sender.open_received_email(Constants.email_subject, true);

		sender.reply_with_attachment(msg, attachment);
	}

	/**
	 * Verifying attachment
	 * 
	 * @param serviceItemNumber
	 * @param driver
	 * @param testReporter
	 * @param attachment
	 * @throws Exception
	 */
	public static void select_service_item_and_verify_attachment(String serviceItemNumber, WebDriver driver,
			ExtentTest testReporter, String attachment) throws Exception {

		ServiceItemsAction serviceAction = new ServiceItemsAction(driver, testReporter);

		serviceAction.select_service_item(serviceItemNumber);

		serviceAction.verify_sent_document_in_service_items(attachment);
	}

	/**
	 * Send email
	 * 
	 * @param driver
	 * @param reporter email
	 * @param subject
	 * @param msg
	 * @param attachment
	 * @throws Exception
	 */
	public static void send_report_email_from_gmail(WebDriver driver, String email, String subject, String msg,
			String attachment) throws Exception {
		MailSender sender = new MailSender(driver);
		sender.compose_and_send_email_with_attachment(email, subject, msg, attachment);
	}
}
