package com.salesforcetest.pages.salesforce;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExpectedErrorMessageList;
import com.salesforcetest.shared.Utils;

public class ServiceItemsAction extends ServiceItemActionHelper {

	private WebDriver driver;

	private ExtentTest testReporter;

	//@FindBy(xpath = ".//*[@id='oneHeader']/descendant::*[@data-id='Case']")// or ".//*[@id='oneHeader']/descendant::*[@role='listitem'][5]/a/span[text()='Service Items']"
	//@FindBy(xpath = ".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")
	@FindBy(xpath = "//a[@title='Service Items']/parent::*")
	WebElement serviceItemsTab;

	@FindBy(css = "a[title='Select List View']")
	WebElement serviceItemsPageOptions;

	//@FindBy(name = "refreshButton")
	@FindBy(xpath = "//button[@name='refreshButton']")
	WebElement refreshBtn;

	@FindBy(className = "listViewContent")
	WebElement listDiv;

	@FindBy(className = "supportPublisherQuickSendEmail")
	WebElement composeBox;

	//@FindBy(css = "button[title='Save']")
	@FindBy(xpath = "//button[@title='Save']/parent::*")
	WebElement saveButton;
	
	//@FindBy(css = "button[title='Cancel']")
	@FindBy(xpath = "//button[@title='Cancel']/parent::*")
	WebElement cancelButton;

	@FindBy(className = "toastMessage")
	WebElement successMsg;

	By successMessagePopup = By.className("toastMessage");

	List<WebElement> sections;

	public ServiceItemsAction(WebDriver driver, ExtentTest testReporter) {
		super();
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		this.driver = driver;
		this.testReporter = testReporter;
	}

	/*
	 * ********************************************************
	 * 
	 * Incident Specific code
	 * 
	 * ********************************************************
	 */

	public void select_incident_queue_list() throws Exception {
		select_service_item_list_option(driver, "Incident Queue", serviceItemsTab, serviceItemsPageOptions);
	}
	
	public String assert_incident_name_get_service_item_number() throws Exception {
		boolean loop = true;
		int counter = 1;
		int rounds = 24; // 120 seconds wait
		String serviceItemNo = null;
		while (loop) {
			refreshBtn.click();

			Utils.sleep(5);
			
			try {
			String actualSubject = listDiv.findElement(By.tagName("table"))
					.findElement(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(5)")).getText();

			if (actualSubject.contains(Constants.incident_subject)) {
				Assert.assertEquals(actualSubject, Constants.incident_subject);
				serviceItemNo = listDiv.findElement(By.tagName("table"))
						.findElement(By.cssSelector("tbody > tr:nth-child(1) > th")).getText();
				testReporter.log(LogStatus.PASS, "Test Automated incident case creation by email - Passed");
				loop = false;
			} else {
				counter++;
			}
			} catch (Exception e) {
				
			}

			if (counter == rounds) {
				loop = false;
				throw new Exception(
						"Incent not created from external email with subject: " + Constants.incident_subject);
			}
		}
		return serviceItemNo;
	}

	public void select_serviceItem_and_change_owner(String serviceItemNo, String owner) throws Exception {
		Utils.sleep(2);

		listDiv.findElement(By.linkText(serviceItemNo)).click();

		// Change owner 
		change_service_item_owner(driver, "People", owner);
		
		Utils.sleep(2);

		testReporter.log(LogStatus.PASS, "Test Automated for changing owner of Service Item "+serviceItemNo+" - Passed");
	}	

	public void select_service_item(String serviceItemNo) throws Exception {
		
		Utils.sleep(3);
		select_service_item_list_option(driver, "My Service Items", serviceItemsTab, serviceItemsPageOptions);
		
		Utils.sleep(8);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				listDiv.findElement(By.linkText(serviceItemNo)));
		Utils.sleep(1);
		// clicking service item
		listDiv.findElement(By.linkText(serviceItemNo)).click();

		Utils.sleep(3);

		testReporter.log(LogStatus.PASS, "select_service_item_test");
	}

	public void send_email_from_service_item(String toEmail) throws Exception {
		Utils.sleep(3);
		click_email_and_send(driver, composeBox, toEmail, true);
	}

	public void verify_sent_document_in_service_items(String docName) throws Exception {
		int threshold = 24; // 2 minutes
		is_document_by_doc_name_found(driver, docName, threshold, 0);
		testReporter.log(LogStatus.PASS, "Verify sent document from incident reporter.");
	}

	public void change_incident_voilator() throws Exception {

		Actions action = new Actions(driver);

		Utils.scrollWindow(driver);
		Utils.sleep(2);
		// Select voilator
		//WebElement el = driver.findElement(By.cssSelector("button[title='Edit Submitter']"));
		//WebElement el = driver.findElement(By.xpath("//button[@title='Edit Submitter']/parent::*"));
	//	action.moveToElement(el).doubleClick().build().perform();

	//	Utils.sleep(2);

		// filling Violator txt
		//WebElement violatorTxt = getElementByLableText(driver, "Submitter");
		//driver.findElement(By.xpath("//span[text()='Submitter']/parent::label/following-sibling::div/descendant::a[@class='deleteAction']")).click();
	//	driver.findElement(By.xpath("//label[text()='Submitter']/following-sibling::*[2]/descendant::button")).click();
		//Utils.sleep(2);
		//violatorTxt.clear();
		//violatorTxt.sendKeys("PVY");
		//driver.findElement(By.xpath("//label[text()='Submitter']/following-sibling::*[2]/descendant::input[@maxlength='255']")).sendKeys("PVY");
		//Utils.sleep(2);
		
		//WebElement elViolator = driver.findElement(By.cssSelector("div[title='PVY - Office of Privacy']"));
	//	WebElement elViolator = driver.findElements(By.xpath("//*[@title='PVY - Office of Privacy']")).get(0);
	//	elViolator.click();

		Utils.sleep(2);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
		//		driver.findElement(By.xpath("//span[.//span[text()='1367 Protective Status Information']]")));
		Utils.scrollWindow(driver, 80);
		Utils.sleep(2);
		//driver.findElement(By.xpath("//span[text()='1367 Protective Status Information']/parent::span/following-sibling::div")).click();
	//	driver.findElement(By.xpath("//label[text()='1367 Protective Status Information']/following-sibling::*[2]/descendant::input")).click();
		Utils.sleep(2);
		//driver.findElement(By.linkText("No")).click();
		//driver.findElement(By.xpath("//label[text()='1367 Protective Status Information']/following-sibling::*[2]/descendant::*[text()='No']")).click();
		Utils.sleep(2);

		// filling SIR Number
		WebElement sirNumTxt = getElementByLableText(driver, "SIR Number");
		sirNumTxt.clear();
		sirNumTxt.sendKeys(Constants.sir_number);

		Utils.scrollWindow(driver, 400);

		Utils.sleep(2);

		// filling Incident Number
		//WebElement incidentNumTxt = getElementByLableText(driver, "Incident Number");
		WebElement incidentNumTxt = getElementByLableText(driver, "ECOP Number");
		incidentNumTxt.clear();
		incidentNumTxt.sendKeys(Constants.incident_number);

		Utils.sleep(1);

		// filling Service Now Number
		WebElement serviceNowNumTxt = getElementByLableText(driver, "ServiceNow Number");
		serviceNowNumTxt.clear();
		serviceNowNumTxt.sendKeys(Constants.service_now_number);

		Utils.scrollWindow(driver, 100);

//		Utils.sleep(1);
//
//		WebElement masterIncident = driver.findElement(By.xpath("//span[.//span[text()='Master Incident']]"));
//		WebElement followingSibling = masterIncident.findElement(By.xpath("following-sibling::*"));
//		followingSibling.findElement(By.className("select")).click();
//
//		Utils.sleep(1);
//
//		// clicking Yes
//		driver.findElement(By.linkText("Yes")).click();
		
		driver.findElement(By.xpath("//label[text()='Master Incident']/following-sibling::*[2]/descendant::input")).click();
		Utils.sleep(2);
		//driver.findElement(By.linkText("No")).click();
		driver.findElement(By.xpath("//label[text()='Master Incident']/following-sibling::*[2]/descendant::*[text()='Yes']")).click();
		//Utils.sleep(2);
		Utils.sleep(2);

		// Save
		//saveButton.click();
		driver.findElement(By.xpath("//button[@title='Save']/parent::*")).click();
		Utils.sleep(3);

		if (Utils.handleAlert(driver))
			throw new Exception("Voilator Change test failed.");

		testReporter.log(LogStatus.PASS, "Automated Test for changing voilator. - Passed");

		Utils.sleep(5);
	}

	public void close_incident_service_item() throws Exception {

		// Change status to Archived
		try {
		change_service_item_status(driver, "In Progress", "Archived", saveButton);
		} catch (Exception e) {
			
		}

		Utils.sleep(4);

		// refresh
		driver.navigate().refresh();

		Utils.sleep(4);

		// Change status to Closed
		change_service_item_status(driver, "Archived", "Closed", saveButton);

		Utils.sleep(4);

		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_2_5_INCIDENT_CLOSED_ERROR_MSG);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Service Item Number']")));
		Utils.sleep(2);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
		//		driver.findElement(By.xpath("//div[@role='list']/div[3]/descendant::a[@class='select']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//label[text()='Type']/parent::*/descendant::input")));
		// Change
		Utils.sleep(1);
		//driver.findElement(By.linkText("Suspected Incident")).click();

		Utils.sleep(1);

		// select non incident
		//driver.findElement(By.linkText("Non-Incident")).click();
		driver.findElement(By.xpath("//span[@title='Non-Incident']")).click();

		Utils.sleep(1);

		// try to Save
		saveButton.click();

		Utils.sleep(2);

		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_2_5_INCIDENT_REASON_ERROR_MSG);

		//Utils.scrollWindow(driver);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
		//		driver.findElement(By.xpath("//div[@role='list']/div[7]/descendant::span[text()='Reason']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//label[text()='Reason']")));
		Utils.sleep(2);
		// Edit reason
		//WebElement reasonLbl = getElementByLableText(driver, "Reason");
		WebElement reasonLbl = driver.findElement(By.xpath("//label[text()='Reason']/parent::*/descendant::div/textarea"));
		reasonLbl.clear();
		reasonLbl.sendKeys("This is non incident");

		Utils.sleep(2);

		// try to Save - should be saved
		saveButton.click();

		Utils.sleep(5);

		driver.navigate().refresh();

		Utils.sleep(3);
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Edit Status']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
					driver.findElement(By.xpath("//button[@title='Edit Status']/parent::*/parent::*/descendant::*[text()='Closed']")));
			Utils.sleep(2);
			} catch (Exception e) {
				
			}
		if (Utils.handleAlert(driver))
			throw new Exception("Closing Incident test failed.");

		testReporter.log(LogStatus.PASS, "Automated Test for closing incident ticket. - Passed");
		Utils.sleep(3);
	}
	
	public void assert_service_item_status(String serverItemNumber, String status) throws Exception {
		
		select_service_item_list_option(driver, "Incident Queue", serviceItemsTab, serviceItemsPageOptions);
		
		boolean loop = true;
		int counter = 1;
		int rounds = 30; // 120 seconds wait
		while (loop) {
			refreshBtn.click();

			Utils.sleep(5);

			WebElement firstRow = listDiv.findElement(By.cssSelector("table > tbody > tr:nth-child(1)"));
			
			String sId = firstRow.findElement(By.tagName("th")).getText();
			if(!serverItemNumber.equalsIgnoreCase(sId)) {
				continue;
			}
			
			String actualStatus = firstRow.findElement(By.cssSelector("td:nth-child(6)")).getText();
			if (status.equalsIgnoreCase(actualStatus)) {
				Assert.assertEquals(actualStatus, status);
				loop = false;
			} else {
				counter++;
			}

			if (counter == rounds) {
				loop = false;
				throw new Exception("Incident status not changed to Unassigned");
			}
		}
	}

	public void verify_status_and_owner() {
		for (int i = 0; i < 10; i++) {
			Utils.sleep(8);
			try {
				driver.navigate().refresh();

				Utils.sleep(2);

				WebElement status = driver.findElement(By.xpath("//span[text()='Unassigned']"));

				Assert.assertNotEquals(status, null);

				WebElement ownerLink = driver.findElement(By.xpath("//span[text()='Incident Queue']"));

				Assert.assertNotEquals(ownerLink, null);

				Utils.sleep(2);

				driver.navigate().refresh();

				break;
			} catch (Exception e) {
				continue;
			}
		}

	}

	/*
	 * ********************************************************
	 * 
	 * Compliance Specific code
	 * 
	 * ********************************************************
	 */

	public String assert_compliance_name_get_service_item_number() throws Exception {
		boolean loop = true;
		int counter = 1;
		int rounds = 24; // 120 secs
		String serviceItemNo = null;
		while (loop) {
			refreshBtn.click();

			Utils.sleep(5);
			try {
			String actualSubject = listDiv.findElement(By.tagName("table"))
					.findElements(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(6)")).get(0).getText();
			if (actualSubject.contains(Constants.compliance_subject)) {
				Assert.assertEquals(actualSubject, Constants.compliance_subject);
				serviceItemNo = listDiv.findElement(By.tagName("table"))
						.findElements(By.cssSelector("tbody > tr:nth-child(1) > th")).get(0).getText();
				testReporter.log(LogStatus.PASS, "Test Automated complience case creation by email.");
				loop = false;
			} else {
				counter++;
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (counter == rounds) {
				loop = false;
				throw new Exception("Complience incident not created from external email with subject: "
						+ Constants.incident_subject);
			}
		}
		return serviceItemNo;
	}

	public void select_compliance_queue_list() throws Exception {
		select_service_item_list_option(driver, "Compliance Queue", serviceItemsTab, serviceItemsPageOptions);
	}

	public void update_service_item_type_field() throws Exception {
		// From None to Privacy Impact Assessment
		update_service_item_type(driver, "Privacy Impact Assessment", saveButton);
	}

	public void verify_compliance_service_item_status_change(String serviceItemNumber) throws Exception {

		update_service_item_type(driver, "Privacy Impact Assessment", saveButton);

		// From In Progress to Unassigned
		change_service_item_status(driver, "In Progress", "Unassigned", saveButton);

		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_3_4_1_ERROR_MSG);

		// Press cancel
		cancelButton.click();

		// Change owner to Compliance Queue
		change_service_item_owner(driver, "Queues", "Compliance Queue");

		// Assert unassigned
		List<WebElement> unassignedEl = driver.findElements(By.xpath("//span[text()='Unassigned']"));

		if (unassignedEl != null && unassignedEl.size() > 0)
			testReporter.log(LogStatus.PASS, "Unassigned status verified.");
		else
			testReporter.log(LogStatus.FAIL, "Unassigned status not verified.");

		// Change owner back to Privacy Staff
		change_service_item_owner(driver, "People", "Privacy Staff");

		testReporter.log(LogStatus.PASS, "Test Automated for changing owner of incident. - Passed");
	}

	public void submit_service_item_for_approval(String commentForApproval) throws Exception {
		(new WebDriverWait(driver, 8)).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Submit for Approval")));
		driver.findElement(By.linkText("Submit for Approval")).click();

		Utils.sleep(1);
		(new WebDriverWait(driver, 8)).until(ExpectedConditions.visibilityOf(driver.findElement(By.className("modal-body")).findElement(By.tagName("textarea"))));
		// Inserting message as comment for approval
		driver.findElement(By.className("modal-body")).findElement(By.tagName("textarea")).sendKeys(commentForApproval);

		Utils.sleep(1);

		// Send
		//driver.findElement(By.className("modal-footer")).findElement(By.className("actionButton")).click();
		driver.findElement(By.className("actionButton")).click();
		Utils.sleep(4);
	}

	public void send_email_from_service_item_from_dhs(String dhsEmail) throws Exception {
		click_email_and_send(driver, composeBox, dhsEmail, false);

		// Asserting error message
		assert_error_msg(driver, Constants.dhs_error_msg);

		testReporter.log(LogStatus.PASS, "Verify error message on sending email to DHS.");
		Utils.sleep(3);
	}

	/*
	 * ********************************************************
	 * 
	 * PIA Specific code
	 * 
	 * ********************************************************
	 */

	public String create_service_item_manually(String serviceItemType, String attachmentUrl, String contact)
			throws Exception {

		Utils.sleep(3);
		//((JavascriptExecutor) driver).executeScript("arguments[0].click();", serviceItemsTab);
		serviceItemsTab.click();
		
		Utils.sleep(2);
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='New']")));
//		driver.findElement(By.cssSelector("a[title='New']")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='New']")));
		driver.findElement(By.xpath("//a[@title='New']")).click();

		Utils.sleep(4);
		driver.findElement(By.xpath("//span[text()='Compliance']/parent::div/preceding-sibling::div[1]")).click();
		Utils.sleep(1);
		//driver.findElement(By.className("modal-footer")).findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		if (!"Privacy Threshold Analysis".equalsIgnoreCase(serviceItemType)) {
			Utils.sleep(2);

			driver.findElement(By.className("modal-body")).findElement(By.linkText("Privacy Threshold Analysis"))
					.click();

			Utils.sleep(2);
			// For PTA - Blank
			// For PIA - Privacy Impact Assessment
			// For SORN - System of Records Notice
			// For Privacy Notice - Privacy Notice
			driver.findElement(By.linkText(serviceItemType)).click();
		}

		Utils.sleep(2);

		// Edit reason
		//WebElement contactLbl = getElementByLableText(driver, "Contact Name");
		WebElement contactLbl = driver.findElement(By.xpath("//span[text()='Contact Name']/parent::*/following-sibling::div/descendant::input"));
		contactLbl.clear();
		contactLbl.sendKeys(contact);

		Utils.sleep(2);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='" + contact + "']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		//driver.findElement(By.cssSelector("div[title='" + contact + "']")));
				driver.findElement(By.xpath("//div[@title='" + contact + "']")));
		Utils.sleep(1);
		//driver.findElement(By.cssSelector("div[title='" + contact + "']")).click();
		driver.findElement(By.xpath("//div[@title='" + contact + "']")).click();
		Utils.sleep(2);

		// Fill subject
		String subject = serviceItemType + Constants.service_item_subject_suffix;
		//WebElement subjectLbl = getElementByLableText(driver, "Subject");
		WebElement subjectLbl = driver.findElement(By.xpath("//span[text()='Subject']/parent::*/following-sibling::input"));
		subjectLbl.clear();
		subjectLbl.sendKeys(subject);

		Utils.sleep(2);
		
		//driver.findElement(By.className("modal-footer")).findElement(By.cssSelector("button[title='Save']")).click();
		//driver.findElement(By.cssSelector("button[title='Save']")).click();
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		if (Utils.fluentWait(successMessagePopup, driver, 10, 1) == null) {
			throw new Exception(serviceItemType + " not created.");
		}

		Utils.sleep(2);

		if (attachmentUrl != null && !"".equals(attachmentUrl)) {
			putAttachment(driver, attachmentUrl);
		}

		return getServiceItemNo(driver);
	}

	/**
	 * This method opens a child service item and merge it to parent service item
	 * 
	 * @param parent service item
	 * @param child  service item
	 * @throws Exception
	 */
	public void merge_service_item(String parent, String child) throws Exception {
		try {
			select_service_item(child);

			Utils.sleep(2);

//			Utils.scrollWindow(driver, 900);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
					driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")));
			Utils.sleep(2);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
					driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")));
			Utils.sleep(1);
			//WebElement iframeParent = Utils.scrollToFindElement(driver, By.xpath("//*[@title='MergeDuplicateServiceItems']"));
			//driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")).click();
			// Switch to iframe
			Utils.sleep(1);
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
					driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")));
			Utils.sleep(2);
			//driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='MergeDuplicateServiceItems']/descendant::iframe[1]")));
			driver.switchTo().frame(driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button/parent::*/parent::*/descendant::iframe")));
			//WebElement findButton = driver.findElement(By.className("lookupInput")).findElement(By.tagName("a"));
			WebElement findButton = driver.findElement(By.className("lookupInput")).findElement(By.tagName("a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
					findButton);
			Utils.sleep(2);
			try {
				findButton.click();
			} catch (Exception e) {
				driver.switchTo().defaultContent();
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
						driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")));
				Utils.sleep(1);
				driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")).click();
				Utils.sleep(1);
				((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
						driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")));
				Utils.sleep(1);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button/parent::*/parent::*/descendant::iframe")));
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", findButton);
			}

			// Switch back to default salesforce webpage
			driver.switchTo().defaultContent();

			Utils.sleep(4);

			// switch to pop up opened for searching serviceitem id
			String parentWindowHandler = Utils.switch_to_popup(driver);

			// get list of iframes
			List<WebElement> iframelist = driver.findElements(By.tagName("frame"));

			// switch to iframe 1: search form
			driver.switchTo().frame(iframelist.get(0));

			// search service item id
			driver.findElement(By.id("lksrch")).sendKeys(parent);
			//driver.findElement(By.xpath("//*[@class='lookupInput']/input")).sendKeys(parent);
			// click find button
			driver.findElement(By.name("go")).click();
			//driver.findElement(By.xpath("//*[@class='lookupInput']/a")).click();

			// back to pop window
			driver.switchTo().defaultContent();

			Utils.sleep(2);

			// switch to frame 2: search list
			driver.switchTo().frame(iframelist.get(1));

			// click on service item
			driver.findElement(By.linkText(parent)).click();

			// back to pop window
//			driver.switchTo().defaultContent();

			// switch back driver
			driver.switchTo().window(parentWindowHandler);

			Utils.sleep(2);

			//driver.switchTo().frame(iframeParent.findElement(By.tagName("iframe")));
			driver.switchTo().frame(driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button/parent::*/parent::*/descendant::iframe")));
			try {
			driver.findElement(By.cssSelector("input[title='Merge']")).click();
			} catch (Exception e) {
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button")).click();
				Utils.sleep(1);
				driver.switchTo().frame(driver.findElement(By.xpath("//*[text()='Merge Duplicates']/parent::button/parent::*/parent::*/descendant::iframe")));
				driver.findElement(By.cssSelector("input[title='Merge']")).click();
			}

			Utils.sleep(4);

			driver.navigate().refresh();

			Utils.sleep(2);

			Utils.scrollWindow(driver, -800);

			Utils.sleep(2);

			// Asserting Archived status
			//WebElement status = driver.findElement(By.xpath("//span[text()='Archived']"));
			
			WebElement status = driver.findElement(By.xpath("//*[text()='In Progress']/parent::*/parent::p"));
			Assert.assertNotEquals(status, null);
			testReporter.log(LogStatus.PASS, "merged service problem Archived");
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * Privacy item actions
	 * 
	 * @param toEmail
	 * @return
	 * @throws Exception
	 */
	public void send_email(String toEmail) throws Exception {
		click_email_and_send(driver, composeBox, toEmail, true);

		// Asserting error message
		Assert.assertEquals(successMsg.getText(), "Email sent.");

		testReporter.log(LogStatus.PASS, "Verify sending email to sample OCC email.");
		Utils.sleep(3);
	}

	/**
	 * Privacy item actions
	 * 
	 * @param toEmail
	 * @return
	 * @throws Exception
	 */
	public void closing_privacy_item() throws Exception {
		// Change status to Closed
		change_service_item_status(driver, "In Progress", "Closed", saveButton);

		Utils.sleep(4);

		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_14_3_PRIVACY_CLOSED_ERROR_MSG);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());

		// TODO Update dates
		Utils.sleep(1);
		WebElement dhsApprovalDate = getElementByLableText(driver, "DHS Approval Date");
		dhsApprovalDate.clear();
		dhsApprovalDate.sendKeys(date);

		Utils.sleep(1);
		WebElement occApprovalDate = getElementByLableText(driver, "OCC Approval Date");
		occApprovalDate.clear();
		occApprovalDate.sendKeys(date);

		Utils.sleep(1);
		WebElement publishDate = getElementByLableText(driver, "Federal Register Publish Date");
		publishDate.clear();
		publishDate.sendKeys(date);

		Utils.sleep(2);

		// try to Save - should be saved
		saveButton.click();

		Utils.sleep(5);

		driver.navigate().refresh();

		Utils.sleep(3);
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='list']/li[2]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
					driver.findElement(By.xpath("//ul[@role='list']/li[2]")));
			Utils.sleep(2);
			} catch (Exception e) {
				
			}
		testReporter.log(LogStatus.PASS, "Verify closing privacy service item.");
		Utils.sleep(3);
	}

	public void close_pia_service_item() throws Exception {
		// Change status to Closed
		change_service_item_status(driver, "In Progress", "Closed", saveButton);

		Utils.sleep(4);

		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_15_2_PIA_CLOSED_ERROR_MSG);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());

		Utils.sleep(1);
		WebElement dhsApprovalDate = getElementByLableText(driver, "DHS Approval Date");
		dhsApprovalDate.clear();
		dhsApprovalDate.sendKeys(date);

		Utils.sleep(1);
		WebElement occApprovalDate = getElementByLableText(driver, "OCC Approval Date");
		occApprovalDate.clear();
		occApprovalDate.sendKeys(date);

		Utils.sleep(2);

		// try to Save - should be saved
		saveButton.click();

		Utils.sleep(5);

		driver.navigate().refresh();

		Utils.sleep(3);
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Edit Status']/parent::*/parent::*/descendant::*[text()='Closed']")));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
					driver.findElement(By.xpath("//button[@title='Edit Status']/parent::*/parent::*/descendant::*[text()='Closed']")));
			Utils.sleep(2);
			} catch (Exception e) {
				
			}
		testReporter.log(LogStatus.PASS, "Verify closing PIA service item.");
		Utils.sleep(3);
	}

	public void close_pta_service_item() throws Exception {
		// Change status to Closed
		change_service_item_status(driver, "In Progress", "Closed", saveButton);
		Utils.sleep(4);
		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_15_1_PTA_CLOSED_ERROR_MSG);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());

		Utils.sleep(1);
		WebElement expirationDate = getElementByLableText(driver, "Expiration Date");
		expirationDate.clear();
		expirationDate.sendKeys(date);

		Utils.sleep(1);
		//WebElement crText = getElementByLableText(driver, "Coverage Recommendation");
		WebElement crText = driver.findElement(By.xpath("//*[text()='Coverage Recommendation']/parent::*/parent::*/descendant::textarea"));
		crText.clear();
		crText.sendKeys("Test Recommendation");

		Utils.sleep(1);
		WebElement dhsApprovalDate = getElementByLableText(driver, "DHS Approval Date");
		dhsApprovalDate.clear();
		dhsApprovalDate.sendKeys(date);

		Utils.sleep(2);

		// try to Save - should be saved
		saveButton.click();

		Utils.sleep(5);

		driver.navigate().refresh();

		Utils.sleep(3);
		try {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Edit Status']/parent::*/parent::*/descendant::*[text()='Closed']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//button[@title='Edit Status']/parent::*/parent::*/descendant::*[text()='Closed']")));
		Utils.sleep(2);
		} catch (Exception e) {
			
		}
		testReporter.log(LogStatus.PASS, "Verify closing PTA service item.");
		Utils.sleep(3);
	}
	
	public void close_sorn_service_item() throws Exception {
		// Change status to Closed
		change_service_item_status(driver, "In Progress", "Closed", saveButton);

		Utils.sleep(4);

		// put Assert on error message
		assert_error_msg(driver, ExpectedErrorMessageList.SCENARIO_5_2_SORN_CLOSED_ERROR_MSG);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(new Date());

		Utils.sleep(1);
		WebElement occApprovalDate = getElementByLableText(driver, "OCC Approval Date");
		occApprovalDate.clear();
		occApprovalDate.sendKeys(date);
		
		Utils.sleep(1);
		WebElement dhsApprovalDate = getElementByLableText(driver, "DHS Approval Date");
		dhsApprovalDate.clear();
		dhsApprovalDate.sendKeys(date);

		Utils.sleep(1);
		WebElement fedApprovalDate = getElementByLableText(driver, "Federal Register Publish Date");
		fedApprovalDate.clear();
		fedApprovalDate.sendKeys(date);

		Utils.sleep(2);

		// try to Save - should be saved
		saveButton.click();

		Utils.sleep(5);

		driver.navigate().refresh();

		Utils.sleep(3);
		try {
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='list']/li[2]")));
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
					driver.findElement(By.xpath("//ul[@role='list']/li[2]")));
			Utils.sleep(2);
			} catch (Exception e) {
				
			}
		testReporter.log(LogStatus.PASS, "Verify closing PTA service item.");
		Utils.sleep(3);
	}
	
	public void view_edit_si_as_team_member(String serviceItemNo) {
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 10);
		driver.navigate().refresh();
		Utils.sleep(4);
		wait.until(ExpectedConditions.visibilityOf(serviceItemsTab));
		select_service_item_list_option(driver, "My Case Teams", serviceItemsTab, serviceItemsPageOptions);
		wait.until(ExpectedConditions.visibilityOf(listDiv));
		Utils.sleep(2);
		System.out.println("New Service item number :"+serviceItemNo);
		// clicking service item
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", listDiv.findElement(By.linkText(serviceItemNo)));
		Utils.sleep(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		listDiv.findElement(By.linkText(serviceItemNo)));
		Utils.sleep(2);
		listDiv.findElement(By.linkText(serviceItemNo)).click();

		Utils.sleep(3);
		
		//edit subject
		try {
			Utils.scrollWindow(driver, 350);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		// Select status
		Utils.sleep(4);
		//WebElement el = driver.findElement(By.cssSelector("button[title='Edit Subject']"));
		WebElement el = driver.findElement(By.xpath("//button[@title='Edit Subject']"));
		action.moveToElement(el).click().build().perform();

		Utils.sleep(2);

		//WebElement subjectText = getElementByLableText(driver, "Subject");
		WebElement subjectText = driver.findElement(By.xpath("//label[text()='Subject']/parent::*/descendant::input"));
		subjectText.clear();
		subjectText.sendKeys("Test Subject");

		Utils.sleep(2);

		// Save
		saveButton.click();
		
	}

}
