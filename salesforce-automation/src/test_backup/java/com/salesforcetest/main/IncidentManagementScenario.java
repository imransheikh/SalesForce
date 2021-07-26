package com.salesforcetest.main;

import org.openqa.selenium.WebDriver;

import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.shared.Incident;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Scenario;
import com.salesforcetest.shared.Utils;

public class IncidentManagementScenario implements Scenario {
	
	private WebDriver driver;
	
	private String serviceItemNumber;

	public IncidentManagementScenario(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Scenario 2.1
	 * 
	 * @param cr
	 */

	public void send_report_email_from_gmail(Reporter cr) {
		try {
			MailSender sender = new MailSender(driver);

			sender.login(cr.getAccountUrl(), cr.getEmail(), cr.getUserName(), cr.getPassword());

			sender.compose_and_send_email_with_attachment(cr.getReportingEmail(), ((Incident) cr.getType()).getSubject(),
					((Incident) cr.getType()).getMessage(), ((Incident) cr.getType()).getAttachment());
		} catch (Exception e) {
			Utils.log("Error in incident email");
			e.printStackTrace();
		}
	}

	public void login_saleforce(String sfUserName, String sfPass) {
		SalesforceLogin login = new SalesforceLogin(driver);
		login.login(sfUserName, sfPass);
	}

	public void select_and_assert_incident_queue() {
		try {
			
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_incident_queue_list();
			
			serviceItemNumber = serviceAction.assert_incident_name_get_service_item_number();
			
		} catch (Exception e) {
			Utils.log("Error in select_and_view_complience_queue method");
			e.printStackTrace();
		}
	}
	
	/**
	 * Scenario 2.2
	 * 
	 * This step will make incident 'a service item'
	 */

	public void select_incident_and_change_owner() {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_incident_queue_list();
			
			serviceAction.select_incident_and_change_owner(serviceItemNumber);
		} catch (Exception e) {
			Utils.log("Error in open_incident_ticket method");
			e.printStackTrace();
		}
	}
	
	/**
	 * Scenario 2.3
	 * @throws Exception 
	 * 
	 * Test email communication
	 * 
	 */
	
	public void select_service_item_and_send_email(Reporter cr) {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_service_item(serviceItemNumber);
			
			serviceAction.click_email_and_send();
			
			serviceAction.send_email_from_service_item();
			
			MailSender sender = new MailSender(driver);
			
			//switch to gmail
			Utils.switch_to_tab(driver,0);
			
			sender.open_received_email();
			
			String attachment = ((Incident) cr.getType()).getAttachment();
			
			sender.reply_with_attachment(((Incident) cr.getType()).getMessage(), attachment);
			
			//back to sales force
			Utils.switch_to_tab(driver, 1);
			
			serviceAction.select_service_item(serviceItemNumber);
			
			serviceAction.verify_sent_document_in_service_items(attachment);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Update the incident information
	 * 
	 */
	public void verify_submitter_voilator_on_service_item() {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_service_item(serviceItemNumber);
			
			serviceAction.change_voilator();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Change the service item (incident) status to closed
	 * 
	 */
	public void closing_service_item() {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_service_item(serviceItemNumber);
			
			serviceAction.close_service_item();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
