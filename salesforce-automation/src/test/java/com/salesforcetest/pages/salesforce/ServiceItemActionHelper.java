package com.salesforcetest.pages.salesforce;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class ServiceItemActionHelper {

	protected void select_service_item_list_option(WebDriver driver, String serviceItemLinkText,
			WebElement serviceItemsTab, WebElement serviceItemsPageOptions) {
		Utils.sleep(4);

		serviceItemsTab.click();
		
		Utils.sleep(4);

		serviceItemsPageOptions.click();

		Utils.sleep(4);

		WebElement dropdown = driver.findElement(By.className("forceVirtualAutocompleteMenuList"));

		dropdown.findElement(By.linkText(serviceItemLinkText)).click();
	}
	protected void change_service_item_owner(WebDriver driver, String type, String ownerSearchTxt) {
		Utils.sleep(2);
		driver.navigate().refresh();
		Utils.sleep(4);

		Actions builder = new Actions(driver);
		//WebElement ownerEl = driver.findElement(By.cssSelector("button[title='Change Owner']"));
		WebElement ownerEl = driver.findElement(By.xpath("//button[@title='Change Owner']/parent::*"));
		builder.moveToElement(ownerEl).click().build().perform();

		Utils.sleep(2);

		WebElement parent = driver.findElement(By.className("modal-container"));

		if (type != "People") {
			// Change People to Queues
			parent.findElement(By.className("entityMenuTrigger")).click();

			Utils.sleep(1);

			// types - 1. People 2. Queues
			driver.findElement(By.linkText(type)).click();

			Utils.sleep(2);
		}

		WebElement combo = parent.findElement(By.className("autocompleteWrapper"));

		// Fill owner name
		combo.findElement(By.tagName("input")).sendKeys(ownerSearchTxt);

		Utils.sleep(2);

		parent.findElement(By.cssSelector("div[title='" + ownerSearchTxt + "']")).click();

		parent.findElement(By.className("nonEditableOptions")).click();

		Utils.sleep(2);

		//parent.findElement(By.cssSelector("button[title='Change Owner']")).click();
		driver.findElement(By.xpath("//button[@value='change owner']/parent::*")).click();

		Utils.sleep(2);
	}

	protected void assert_error_msg(WebDriver driver, String expectedMsg) throws Exception {
		//List<WebElement> errorList = driver.findElements(By.className("errorsList"));
		List<WebElement> errorList = driver.findElements(By.xpath("//*[contains(@class,'errorsList')]"));
		boolean found = false;
		for (WebElement el : errorList) {
			//String html = el.getAttribute("innerHTML");
			String html = el.getText();
			System.out.println("Actual Error: " + html);
			System.out.println("Expected Error: " + expectedMsg);
			if (html.contains(expectedMsg)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("Error message not verified");
		}
	}

	protected WebElement getElementByLableText(WebDriver driver, String lableTxt) {
		//WebElement el = driver.findElement(By.xpath("//label[.//span[text()='" + lableTxt + "']]"));
		WebElement el = driver.findElement(By.xpath("//label[text()='" + lableTxt + "']"));
		//return driver.findElement(By.id(el.getAttribute("for")));
		return driver.findElement(By.xpath("//label[text()='"+lableTxt+"']/following-sibling::*[2]/descendant::input"));
	}

	protected void change_service_item_status(WebDriver driver, String fromStatus, String toStatus,
			WebElement saveButton) throws Exception {
		Utils.sleep(3);

		Utils.scrollWindow(driver, 100);
		Actions action = new Actions(driver);
		// Select status
		Utils.sleep(1);
		//WebElement el = driver.findElement(By.cssSelector("button[title='Edit Status']"));
		WebElement el = driver.findElement(By.xpath("//button[@title='Edit Status']"));
		action.moveToElement(el).click().build().perform();

		Utils.sleep(1);
		try {
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@role='list']/div[2]/descendant::span[text()='Service Item Number']")));
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//span[text()='Service Item Number']")));
			//System.out.println("I am here");
			Utils.sleep(2);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@role='list']/div[4]/descendant::a[@class='select']")));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Status']/parent::*/descendant::input")));
		} catch (Exception e) {
			System.out.println("I am failed");
		}
		Utils.sleep(2);
		/*try {
			driver.findElement(By.linkText(fromStatus)).click();
		} catch (Exception e) {
			
		}*/

		//Utils.sleep(2);

		// Select Archived
		try {
			//WebElement status = driver.findElement(By.linkText(toStatus));
			WebElement status = driver.findElement(By.xpath("//span[@title='"+toStatus+"']"));
			action.moveToElement(status).click().build().perform();
		} catch (Exception e) {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
					driver.findElement(By.xpath("//span[@title='"+toStatus+"']")));
			//WebElement status = driver.findElement(By.linkText(toStatus));
			WebElement status = driver.findElement(By.xpath("//span[@title='"+toStatus+"']"));
			action.moveToElement(status).click().build().perform();
		}
		Utils.sleep(2);
		
		// Save
		saveButton.click();

		Utils.sleep(4);
	}

	protected void is_document_by_doc_name_found(WebDriver driver, String docName, int threshold, int counter)
			throws Exception {
		Utils.sleep(4);

		//driver.findElement(By.linkText("Related")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Related']")));
		Utils.sleep(2);
		//List<WebElement> files = driver.findElements(By.className("forceContentVirtualRelatedListStencil"));
		List<WebElement> files = driver.findElements(By.xpath("//*[contains(@class,'forceContentVirtualRelatedListStencil')]"));
		boolean fileFound = false;

		for (int ii = 0; ii < files.size(); ii++) {
			String fileName = files.get(ii).findElement(By.tagName("a")).getAttribute("title");
			//System.out.println(fileName);
			if (docName.contains(fileName)) {
				fileFound = true;
				Assert.assertTrue(fileFound);
				break;
			}
		}
		
		if(!fileFound) {
			counter++;
			if (counter < threshold) {
				Utils.sleep(5);
				driver.navigate().refresh();
				is_document_by_doc_name_found(driver, docName, threshold, counter);
			} else {
				Assert.assertTrue(false);
				throw new Exception("Timeout: Attachment not found.");
			}
		} else {
			Utils.sleep(2);
		}
		
	}
	
	protected void click_email_and_send(WebDriver driver, WebElement composeBox, String toEmail,
			boolean withAttachments) throws Exception {
		Robot robot = new Robot();
		Utils.sleep(2);
		// Clicking email tab
		//driver.findElement(By.linkText("Email")).click();
		//driver.findElement(By.xpath("//span[text()='Email']/parent::a")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Email']/parent::a")));
		Utils.sleep(2);

		Utils.scrollWindow(driver,100);
		Utils.sleep(1);
		//try {
		//driver.findElement(By.xpath("//a[@class='select']/parent::*")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@class='select']")));
		Utils.sleep(2);
		//driver.findElement(By.xpath("//div[@class='select-options']/ul/li/a[@title='USCIS No Reply <no-reply@uscis.dhs.gov>']")).click();
		driver.findElement(By.xpath("//a[@title='USCIS No Reply <no-reply@uscis.dhs.gov>']")).click();
		Utils.sleep(2);
		//} catch (Exception e) {
			
		//}
		// clicking cross in to email
		//List<WebElement> sections = composeBox.findElement(By.cssSelector("div"))
		//				.findElements(By.className("forcePageBlockSectionRow"));
		List<WebElement> sections = composeBox.findElements(By.xpath("//div[contains(@class,'forcePageBlockSectionRow')]"));
		try {
		Utils.sleep(1);
		// removing to address
		//sections.get(1).findElement(By.className("deleteAction")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", sections.get(1).findElement(By.className("deleteAction")));
		Utils.sleep(2);
		} catch (Exception e) {
			
		}
		//sections.get(1).findElement(By.className("uiInputTextForAutocomplete")).sendKeys(toEmail);
		sections.get(1).findElements(By.xpath("//*[contains(@class,'uiInputTextForAutocomplete')]")).get(1).sendKeys(toEmail);
		//driver.findElement(By.linkText("Email")).click();
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		
		/*driver.findElement(By.xpath("//input[@maxlength='3000']")).click();
		driver.findElement(By.xpath("//input[@maxlength='3000']")).clear();
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@maxlength='3000']")).sendKeys(Constants.email_subject);*/
		Utils.scrollWindow(driver,450);
		Utils.sleep(2);
		if (withAttachments) {
			// use templates
			// old
//			composeBox.findElement(By.cssSelector("div")).findElement(By.xpath("div[3]/div[1]"))
//					.findElement(By.className("uiPopupTrigger")).click();

			// use templates
			//List<WebElement> iconList = driver.findElements(By.className("cuf-attachmentsItem"));

			// Click adding template
			//iconList.get(3).click();
			
			
			//driver.findElement(By.xpath("//*[@class='cuf-attachmentsItem'][3]/descendant::a")).click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@class='cuf-attachmentsItem'][3]/descendant::a")));
			Utils.sleep(2);

			//driver.findElement(By.linkText("Insert a template...")).click();
			//driver.findElement(By.xpath("//*[text()='Insert a template...']")).click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[text()='Insert a template...']")));
			//Actions actObj = new Actions(driver);
			//actObj.moveToElement(driver.findElement(By.xpath("//div/ul/li[1]/a[contains(@title,'Insert a template')]"))).build().perform();
			
			Utils.sleep(1);
			/*robot.keyPress(KeyEvent.VK_DOWN);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Utils.sleep(2);*/
			/*robot.keyPress(KeyEvent.VK_DOWN);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_DOWN);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(3);*/
			WebElement modal = driver.findElement(By.xpath("//div[contains(@class,'modal-body')]"));

			//Select templateDrop = new Select(modal.findElement(By.tagName("select")));
			Select templateDrop = new Select(driver.findElement(By.xpath("//*[text()='My Templates']/parent::*")));

			templateDrop.selectByVisibleText("All Templates");

			Utils.sleep(4);

			//modal.findElement(By.cssSelector("a[title='Notification Letter Without Credit Monitoring']")).click();
			//Actions action = new Actions(driver);
			//WebElement el = driver.findElement(By.xpath("//button[text()='Notification Letter Without Credit Monitoring']/parent::*/parent::*/parent::*"));
			//action.moveToElement(el).clickAndHold().build().perform();
			//driver.findElement(By.xpath("//button[text()='Notification Letter Without Credit Monitoring']/parent::*/parent::*/parent::*")).click();
			//driver.findElement(By.xpath("html/body/div[5]/div[2]/div[2]/div[2]/div/div[2]/div[3]/div[2]/div/div/table/tbody/tr[1]/th/a")).click();*/
			driver.findElement(By.xpath("//input[@class='templateSearch input']")).sendKeys("Notification Letter Without Credit Monitoring");
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(2);
			/*Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_TAB);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_TAB);
			Utils.sleep(1);*/
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(4);
			try {
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//button[@title='Notification Letter Without Credit Monitoring']")));
			} catch (Exception e) {
				
			}
			/*WebElement subjectInput = getElementByLableText(driver, "Subject");
			subjectInput.clear();
			subjectInput.sendKeys(Constants.email_subject);*/
			Utils.scrollWindow(driver, -300);
			//driver.findElement(By.xpath("//input[@maxlength='3000']")).click();
			driver.findElement(By.xpath("//input[@maxlength='3000']")).clear();
			Utils.sleep(1);
			driver.findElement(By.xpath("//input[@maxlength='3000']")).sendKeys(Constants.email_subject);
			Utils.sleep(1);
			Utils.scrollWindow(driver, 400);
		}

		// click send button
		// NEW
		Utils.sleep(3);
		
		//driver.findElement(By.xpath("//span[text()='Send']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Send']")));
		Utils.sleep(5);
	}

	protected void update_service_item_type(WebDriver driver, String type, WebElement saveButton) throws Exception {
		// click update type button
		Actions action = new Actions(driver);
		Utils.scrollWindow(driver, 50);
		//WebElement el = driver.findElement(By.cssSelector("button[title='Edit Type']"));
		WebElement el = driver.findElement(By.xpath("//button[@title='Edit Type']"));
		action.moveToElement(el).doubleClick().build().perform();
		Utils.sleep(4);
		//try {
		try {
			//driver.findElement(By.linkText("--None--")).click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//label[text()='Type']/parent::*/descendant::input")));
			Utils.sleep(3);
		} catch (Exception e) {
			
		}
			//driver.findElement(By.linkText(type)).click();
		driver.findElement(By.xpath("//*[@title='"+type+"']")).click();
			Utils.sleep(2);
			// Save
			saveButton.click();
			Utils.sleep(4);
			driver.navigate().refresh();
			Utils.sleep(2);
		//} catch (Exception e) {
			//driver.switchTo().alert().accept();
		//}
	}

	protected String getSuccessMessage(WebElement successPopup) {
		return successPopup.getText();
	}

	// Assuming service item is opened
	protected String getServiceItemNo(WebDriver driver) {
		
		driver.navigate().refresh();
		
		Utils.sleep(2);
		
//		List<WebElement> list = driver.findElements(By.className("forceHighlightsDesktopListRecordItem"));
//		
//		WebElement labelSpan = list.get(2).findElement(By.cssSelector("span[title='Service Item Number']"));
//		
//		// NOTE: broken into separate statements for clarity. Could be done as one statement.
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		WebElement parentElement = (WebElement)executor.executeScript("return arguments[0].parentNode;", labelSpan);
//		
//		Utils.sleep(2);
		
		//return parentElement.findElement(By.className("uiOutputText")).getText();
		return driver.findElement(By.xpath("//*[@title='Service Item Number']/following-sibling::*/*/*")).getText();
	}
	
	protected void putAttachment(WebDriver driver, String attachmentPath) throws Exception {
		//driver.findElement(By.linkText("Related")).click();
driver.navigate().refresh();
		
		Utils.sleep(4);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Related']")));
		Utils.sleep(4);
		
		//WebElement section = driver.findElement(By.tagName("lightning-primitive-file-droppable-zone"));
		WebElement section = driver.findElement(By.xpath("//span[contains(text(),'Upload Files')]"));
		section.click();
		
		Utils.sleep(3);
		
		StringSelection s = new StringSelection(attachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();
		
		// For windows
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		Utils.sleep(8);
		try {
//		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.className("modal-footer")).findElement(By.tagName("button"))));
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[contains(text(),'1 of 1 file uploaded')]"))));
		} catch (Exception e) {
			
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//*[contains(text(),'1 of 1 file uploaded')]")));
				Utils.sleep(2);
				driver.findElement(By.xpath("//span[text()='Done']/parent::button")).click();
		
		Utils.sleep(2);
	}

}
