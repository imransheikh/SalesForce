package com.salesforcetest.pages.salesforce;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class ServiceItemsAction {

	private WebDriver driver;

	@FindBy(xpath = "//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/one-app-nav-bar/nav/ul/li[5]/a")
	WebElement serviceItemsTab;
	
	@FindBy(xpath = "//*[@id=\"brandBand_1\"]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[1]/div/div[2]/h1/a")
	WebElement serviceItemsPageOptions;
	
	@FindBy(xpath = "//*[@id=\"oneHeader\"]/div[3]/one-appnav/div/one-app-nav-bar/nav/ul/li[5]/one-app-nav-bar-item-dropdown/div/one-app-nav-bar-menu-button")
	WebElement serviceItemsDropDownObj;

	@FindBy(name = "refreshButton")
	WebElement refreshBtn;
	
	@FindBy(className = "listViewContent")
	WebElement listDiv;
	
	@FindBy(className = "supportPublisherQuickSendEmail")
	WebElement composeBox;
	
	List<WebElement> sections;
	
	public ServiceItemsAction(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void select_incident_queue_list() throws Exception {

		Utils.sleep(3);

		WebElement serviceItemsDropDown = serviceItemsDropDownObj.findElement(By.tagName("a"));
		serviceItemsDropDown.click();

		Utils.sleep(3);

		WebElement incidentLink = serviceItemsDropDownObj.findElement(By.linkText("Incident Queue"));
		incidentLink.click();
		
		Utils.sleep(3);
	}

	public String assert_incident_name_get_service_item_number() throws Exception {
		boolean loop = true;
		int counter = 1; int rounds = 60;
		String serviceItemNo = null;
		while (loop) {
			refreshBtn.click();

			Utils.sleep(5);

			String actualSubject = listDiv.findElement(By.tagName("table"))
					.findElement(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(4)")).getText();

			if (actualSubject.contains(Constants.incident_subject)) {
				Assert.assertEquals(actualSubject, Constants.incident_subject);
				serviceItemNo = listDiv.findElement(By.tagName("table"))
						.findElement(By.cssSelector("tbody > tr:nth-child(1) > th")).getText();
				loop = false;
			} else {
				counter++;
			}

			if (counter == rounds) {
				loop = false;
			}
		}
		
		if(rounds == 60) {
			throw new Exception("Service Item Number not found.");
		}
		return serviceItemNo;
	}
	
	public void assert_compliance_name() throws Exception {
		boolean loop = true;
		int counter = 1; int rounds = 60;
		while (loop) {
			refreshBtn.click();

			Utils.sleep(5);

			String actualSubject = listDiv.findElement(By.tagName("table"))
					.findElement(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(6)")).getText();

			if (actualSubject.contains(Constants.compliance_subject)) {
				Assert.assertEquals(actualSubject, Constants.compliance_subject);
				loop = false;
			} else {
				counter++;
			}

			if (counter == rounds) {
				loop = false;
			}
		}
		
		if(rounds == 60) {
			throw new Exception("Complience number not found.");
		}
	}

	public void select_incident_and_change_owner(String serviceItemNo) throws Exception {
		listDiv.findElement(By.linkText(serviceItemNo)).click();
		
		Actions builder = new Actions(driver);
		
		WebElement ownerEl = driver.findElement(By.className("ownerName"));
		builder.moveToElement(ownerEl).click().build().perform();
		
		WebElement ownerElBtn = driver.findElement(By.className("changeOwnerLink"));
		ownerElBtn.click();
		
		Utils.sleep(2);
		
		WebElement parent = driver.findElement(By.className("modal-container"));
		
		WebElement combo = parent.findElement(By.className("autocompleteWrapper"));
		
		combo.findElement(By.tagName("input")).sendKeys("Privacy Staff");
		
		Utils.sleep(3);
		
		combo.findElement(By.className("listContent")).findElement(By.cssSelector("ul > li:nth-child(2)")).click();
		
		parent.findElement(By.className("nonEditableOptions")).click();
		
		Utils.sleep(2);
		
		parent.findElement(By.cssSelector("button[title='Change Owner']")).click();
		
		Utils.sleep(5);
	}
	
	public void select_compliance_queue_list() throws Exception {
		Utils.sleep(3);

		WebElement serviceItemsDropDown = serviceItemsDropDownObj.findElement(By.tagName("a"));
		serviceItemsDropDown.click();

		Utils.sleep(3);

		WebElement incidentLink = serviceItemsDropDownObj.findElement(By.linkText("Compliance Queue"));
		incidentLink.click();
		
		Utils.sleep(3);
		
	}
	
	public void select_compliance() throws Exception {
		WebElement incidentLink = listDiv.findElement(By.tagName("table"))
				.findElement(By.cssSelector("tbody > tr:nth-child(1) > th > span > a"));
		
		incidentLink.click();
		
		Actions builder = new Actions(driver);
		
		WebElement ownerEl = driver.findElement(By.className("ownerName"));
		builder.moveToElement(ownerEl).click().build().perform();
		
		WebElement ownerElBtn = driver.findElement(By.className("changeOwnerLink"));
		ownerElBtn.click();
		
		Utils.sleep(2);
		
		WebElement parent = driver.findElement(By.className("modal-container"));
		
		WebElement combo = parent.findElement(By.className("autocompleteWrapper"));
		
		combo.findElement(By.tagName("input")).sendKeys("Privacy Staff");
		
		Utils.sleep(3);
		
		combo.findElement(By.className("listContent")).findElement(By.cssSelector("ul > li:nth-child(2)")).click();
		
		parent.findElement(By.className("nonEditableOptions")).click();
		
		Utils.sleep(2);
		
		parent.findElement(By.cssSelector("button[title='Change Owner']")).click();
		
	}

	
	public void select_service_item(String serviceItemNo) throws Exception {
		
		Utils.sleep(2);
		serviceItemsTab.click();
		
		Utils.sleep(2);
		
		serviceItemsPageOptions.click();
		
		Utils.sleep(2);
		
		WebElement dropdown = driver.findElement(By.className("forceVirtualAutocompleteMenuList"));
		
		dropdown.findElement(By.linkText("My Service Items")).click();
		
		Utils.sleep(3);
		
		//clicking service item
		listDiv.findElement(By.linkText(serviceItemNo)).click();
		
		Utils.sleep(3);
	}
	
	public void click_email_and_send() throws Exception {

		//Clicking email tab
		driver.findElement(By.linkText("Email")).click();
		
		//clicking cross in to email
		
		sections = composeBox.findElement(By.cssSelector("div")).findElements(By.className("forcePageBlockSectionRow"));
		
		//removing to address
		sections.get(1).findElement(By.className("deleteAction")).click();
		
		sections.get(1).findElement(By.className("uiInputTextForAutocomplete")).sendKeys("zabid@acumensolutions.com");
		
		Utils.sleep(2);
		
		//use templates
		composeBox.findElement(By.cssSelector("div")).findElement(By.xpath("div[3]/div[1]")).findElement(By.className("uiPopupTrigger")).click();
		
		driver.findElement(By.linkText("Insert a template...")).click();
		
		Utils.sleep(3);
		
		WebElement modal = driver.findElement(By.className("modal-body"));
		
		Select templateDrop = new Select(modal.findElement(By.tagName("select")));
		
		templateDrop.selectByVisibleText("Sample Lightning Templates");
		
		Utils.sleep(5);
		
		modal.findElement(By.cssSelector("a[title='SAMPLE: Called, But No Answer']")).click();
	}
	
	public void send_email_from_service_item() throws Exception {
		WebElement subjectLabel = driver.findElement(By.xpath("//label[.//span[text()='Subject']]"));
		WebElement subjectInput = driver.findElement(By.id(subjectLabel.getAttribute("for")));
		
		subjectInput.clear();
		subjectInput.sendKeys(Constants.email_subject);
		
		Utils.scrollWindow(driver);
		
		//click send button
		composeBox.findElement(By.cssSelector("div")).findElement(By.xpath("div[3]/div[2]/button")).click();
		
		Utils.sleep(5);
	}

	public void verify_sent_document_in_service_items(String docName) {
		driver.findElement(By.linkText("RELATED")).click();
		
		List<WebElement> files = driver.findElements(By.className("forceContentVirtualRelatedListStencil "));
		boolean fileFound = false;
		for(int ii=0;ii<files.size();ii++) {
			String fileName = files.get(ii).findElement(By.tagName("a")).getAttribute("title");
			if(docName.contains(fileName)) {
				fileFound = true;
				Assert.assertTrue(fileFound);
				break;
			} else {
				Utils.sleep(5);
				driver.navigate().refresh();
				verify_sent_document_in_service_items(docName);
			}
		}
		
		
	}

	public void change_voilator() throws Exception {
		
		Actions action = new Actions(driver);
		//Select voilator
		
		WebElement el = driver.findElement(By.cssSelector("button[title='Edit Violator']"));
		action.moveToElement(el).click().build().perform();
		
		Utils.scrollWindow(driver);
		
		List<WebElement> list = driver.findElements(By.className("uiAutocompleteList "));
		
		List<WebElement> options = list.get(1).findElements(By.className("forceSearchInputLookupDesktopOption"));
		
		options.get(0).findElement(By.tagName("a")).click();
		
		//filling SIR Number
		
		WebElement parentLabel = driver.findElement(By.xpath("//label[.//span[text()='SIR Number']]"));
		
		driver.findElement(By.id(parentLabel.getAttribute("for"))).sendKeys("123456");
		
		Utils.scrollWindow(driver);
		
		//filling Incident Number
		
		WebElement incidentNumLbl = driver.findElement(By.xpath("//label[.//span[text()='Incident Number']]"));
		
		driver.findElement(By.id(incidentNumLbl.getAttribute("for"))).sendKeys("123456");	
		
		//filling Incident Number
		
		WebElement serviceNowNumLbl = driver.findElement(By.xpath("//label[.//span[text()='ServiceNow Number']]"));
		
		driver.findElement(By.id(serviceNowNumLbl.getAttribute("for"))).sendKeys("123456");	
		
//		Utils.scrollWindow(driver);
		
		WebElement masterIncident = driver.findElement(By.xpath("//span[.//span[text()='Master Incident']]"));
		
		WebElement followingSibling = masterIncident.findElement(By.xpath("following-sibling::*"));
		
		followingSibling.findElement(By.className("select")).click();
		
		//clicking YEs
		driver.findElement(By.xpath("/html/body/div[7]/div/ul/li[2]/a")).click();
		
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		
		Utils.sleep(5);
	}

	public void close_service_item() throws Exception {
		
		Actions action = new Actions(driver);
		//Select status
		
		WebElement el = driver.findElement(By.cssSelector("button[title='Edit Status']"));
		action.moveToElement(el).click().build().perform();
		
		driver.findElement(By.linkText("In Progress")).click();
		
		 By dd = By.xpath("/html/body/div[7]/div/ul");
		Utils.scrollDown(driver, dd);
		
		WebElement ddList = driver.findElement(dd);
		
		//Select Archive
		ddList.findElement(By.linkText("Archived")).click();
		
		//Save
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		
		Utils.sleep(5);
		
		/**
		 * Edit Again
		 */
		
		driver.navigate().refresh();
		
		WebElement statusEl = driver.findElement(By.cssSelector("button[title='Edit Status']"));
		action.moveToElement(statusEl).click().build().perform();
		
		driver.findElement(By.linkText("Archived")).click();
		
		ddList = driver.findElement(dd);
		//Select Closed
		ddList.findElement(By.linkText("Closed")).click();
		
		//try to Save
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		
		//TODO put Assert on error message
		
//		List<WebElement> errorList= driver.findElements(By.className("errorsList"));
//		String error1 = "";
//		try {
//			error1 = errorList.get(0).findElement(By.cssSelector("li")).getText();
//			Assert.assertNotEquals(error1, "");
//		} catch(Exception e) {
//			error1 = errorList.get(1).findElement(By.cssSelector("li")).getText();
//			Assert.assertNotEquals(error1, "");
//		}
		
		
		Utils.sleep(5);
		 	
		/**
		 * Edit Again
		 */
		
//		WebElement type = driver.findElement(By.cssSelector("button[title='Edit Type']"));
//		action.moveToElement(el).click().build().perform();
		
		driver.findElement(By.linkText("Suspected Incident")).click();
		
		//select non incident
		By dd1 = By.xpath("/html/body/div[8]/div/ul");
		ddList = driver.findElement(dd1);
		ddList.findElement(By.linkText("Non-Incident")).click();
		
		//try to Save
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		
		//TODO put Assert on error message
//		String error2 = driver.findElement(By.className("errorsList")).findElement(By.cssSelector("li")).getText();
//		
//		Assert.assertNotEquals(error2, "");
		
		WebElement incidentNumLbl = driver.findElement(By.xpath("//label[.//span[text()='Reason']]"));
		
		driver.findElement(By.id(incidentNumLbl.getAttribute("for"))).sendKeys("This is non incident");
		
		Utils.sleep(3);
		//try to Save
		driver.findElement(By.cssSelector("button[title='Save']")).click();
		
		Utils.sleep(5);
	}

}
