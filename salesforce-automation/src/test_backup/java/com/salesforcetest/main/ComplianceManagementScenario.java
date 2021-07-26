package com.salesforcetest.main;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.salesforcetest.pages.gmail.MailSender;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.ServiceItemsAction;
import com.salesforcetest.shared.Compliance;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Utils;

public class ComplianceManagementScenario
{

	private WebDriver driver;
	
	public ComplianceManagementScenario(WebDriver driver) {
		this.driver = driver;
	}

	public void send_report_email_from_gmail(Reporter reporter) {
		try {
			MailSender sender = new MailSender(driver);

			sender.login(reporter.getAccountUrl(), reporter.getEmail(), reporter.getUserName(), reporter.getPassword());

			sender.compose_and_send_email_with_attachment(reporter.getReportingEmail(), ((Compliance) reporter.getType()).getSubject(),
					((Compliance) reporter.getType()).getMessage(), ((Compliance) reporter.getType()).getAttachment());
		} catch (Exception e) {
			Utils.log("Error in Compliance email");
			e.printStackTrace();
		}
		
	}

	public void switch_to_another_tab() {
		//Creating the JavascriptExecutor interface object by Type casting		
        JavascriptExecutor js = (JavascriptExecutor)driver;	
		js.executeScript("window.open('about:blank','_blank');");
		
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public void login_saleforce(String sfUserName, String sfPass) {
		SalesforceLogin login = new SalesforceLogin(driver);
		login.login(sfUserName, sfPass);
	}

	public void select_and_assert_compliance_queue() {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			
			serviceAction.select_compliance_queue_list();
			
			serviceAction.assert_compliance_name();
			
		} catch (Exception e) {
			Utils.log("Error in select_and_view_complience_queue method");
			e.printStackTrace();
		}
	}

	public void open_compliance_ticket() {
		try {
			ServiceItemsAction serviceAction = new ServiceItemsAction(driver);
			serviceAction.select_compliance();
		} catch (Exception e) {
			Utils.log("Error in open_incident_ticket method");
			e.printStackTrace();
		}
		
	}
	
	
}
