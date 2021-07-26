package com.salesforcetest.pages.salesforce;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
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

import com.salesforcetest.shared.Utils;

public class SupervisorAction {

	private WebDriver driver;

	@FindBy(className = "pending-approval-card-row-button")
	List<WebElement> actionBtnList;

	@FindBy(linkText = "Approve")
	WebElement approveButton;

	@FindBy(linkText = "Reject")
	WebElement rejectButton;

	@FindBy(linkText = "Reassign")
	WebElement reassignButton;

	@FindBy(className = "modal-footer")
	WebElement modalFooter;

	@FindBy(tagName = "textarea")
	WebElement textBox;

	@FindBy(xpath = "//span[text()='Approve']")
	WebElement modalApproveBtn;
	
	@FindBy(xpath = "//span[text()='Reject']")
	WebElement modalRejectBtn;
	
	@FindBy(xpath = "//span[text()='Reassign']")
	WebElement modalReassignBtn;
	
	@FindBy(className = "toastMessage")
	WebElement successMsg;
	
	@FindBy(xpath = ".//*[@id='oneHeader']/descendant::*[@data-id='Contact']")
	WebElement contactsTab;
	
	@FindBy(linkText = "Zaim Abid")
	WebElement contactLink;
	
	@FindBy(css = "button[title='Edit In Training']")
	WebElement editInTraining;
	
	@FindBy(css = "button[title='Save']")
	WebElement traningSaveButton;
	
	public SupervisorAction(WebDriver driver) {
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		this.driver = driver;
	}

	public void approve() {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='home']")).click();
		Utils.sleep(1);
		WebDriverWait iWait = new WebDriverWait(driver, 10);
		Utils.sleep(5);
		iWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("pending-approval-card-row-button"))));
		actionBtnList.get(0).click();
		iWait.until(ExpectedConditions.visibilityOf(approveButton));
		Utils.sleep(4);

		approveButton.click();

		Utils.sleep(4);
		iWait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys("Approved");
		
		Utils.sleep(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", modalApproveBtn);
		Utils.sleep(2);
		modalApproveBtn.click();
		
		Utils.sleep(4);
		iWait.until(ExpectedConditions.visibilityOf(successMsg));
		Assert.assertEquals(successMsg.getText(), "Service Item was approved.");
	}

	public void reject() {
		WebDriverWait iWait = new WebDriverWait(driver, 10);
		Utils.sleep(5);
		iWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("pending-approval-card-row-button"))));
		actionBtnList.get(0).click();
		iWait.until(ExpectedConditions.visibilityOf(rejectButton));
		Utils.sleep(2);

		rejectButton.click();

		Utils.sleep(2);
		iWait.until(ExpectedConditions.visibilityOf(textBox));
		textBox.sendKeys("Rejected");
		
		Utils.sleep(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", modalRejectBtn);
		Utils.sleep(2);
		modalRejectBtn.click();
		
		Utils.sleep(4);
		iWait.until(ExpectedConditions.visibilityOf(successMsg));
		Assert.assertEquals(successMsg.getText(), "Service Item was rejected.");
	}

	public void reassign(String newUser) {
		WebDriverWait iWait = new WebDriverWait(driver, 10);
		Utils.sleep(5);
		iWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("pending-approval-card-row-button"))));
		
		actionBtnList.get(0).click();
		iWait.until(ExpectedConditions.visibilityOf(reassignButton));
		Utils.sleep(4);

		reassignButton.click();

		Utils.sleep(4);
		
		WebElement modal = driver.findElement(By.className("modal-body"));

		modal.findElement(By.className("nextApprover"))
				.findElement(By.tagName("input")).sendKeys(newUser);
		
		Utils.sleep(2);
		
		modal.findElement(By.cssSelector("div[title='"+newUser+"']")).click();
		
		textBox.sendKeys("Reassigned");
		
		Utils.sleep(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", modalReassignBtn);
		Utils.sleep(2);
		modalReassignBtn.click();
		
		Utils.sleep(3);
		iWait.until(ExpectedConditions.visibilityOf(successMsg));
		Assert.assertEquals(successMsg.getText(), "Service Item was reassigned.");
	}

	public void setup_training() {
		WebDriverWait iWait = new WebDriverWait(driver, 8);
		Utils.sleep(4);
		
		contactsTab.click();

		Utils.sleep(4);
		driver.findElement(By.cssSelector("a[title='Select List View']")).click();
		Utils.sleep(2);
		driver.findElements(By.xpath("//span[text()='All Contacts']/parent::a")).get(0).click();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("Zaim Abid");
		driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("OIT - Office of Information Technology");
		Utils.sleep(1);
		try {
			Robot robot = new Robot();
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(4);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		iWait.until(ExpectedConditions.visibilityOf(contactLink));
		contactLink.click();
		} catch (Exception e) {
			driver.navigate().refresh();
			Utils.sleep(2);
			try {
			iWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[@title='Name']/div/a"))));
			driver.findElement(By.xpath("//th[@title='Name']/div/a")).click();
			//driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("Zaim Abid");
			driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("OIT - Office of Information Technology");
			Utils.sleep(1);
			try {
				Robot robot = new Robot();
				Utils.sleep(2);
				robot.keyPress(KeyEvent.VK_ENTER);
				Utils.sleep(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Utils.sleep(4);
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			contactLink.click();
			} catch(Exception e2) {
				driver.navigate().refresh();
				Utils.sleep(2);
				iWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//th[@title='Name']/div/a"))));
				driver.findElement(By.xpath("//th[@title='Name']/div/a")).click();
				//driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("Zaim Abid");
				driver.findElement(By.xpath("//input[@name='Contact-search-input']")).sendKeys("Test Internal Account");
				Utils.sleep(1);
				try {
					Robot robot = new Robot();
					Utils.sleep(2);
					robot.keyPress(KeyEvent.VK_ENTER);
					Utils.sleep(1);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Utils.sleep(4);
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				contactLink.click();
			}
		}
		
		Utils.sleep(4);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", editInTraining);
		Utils.sleep(1);
		Actions action = new Actions(driver);
		action.moveToElement(editInTraining).click().build().perform();

		Utils.sleep(4);
		
		getElementByLabelText(driver, "In Training").click();
		
		Utils.sleep(4);
		iWait.until(ExpectedConditions.visibilityOf(traningSaveButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", traningSaveButton);
		Utils.sleep(1);
		traningSaveButton.click();
		
		Utils.sleep(4);
		try {
		iWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-aura-class='forcePageBlockSectionRow'][4]/div[1]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		driver.findElement(By.xpath("//div[@data-aura-class='forcePageBlockSectionRow'][4]/div[1]")));
		Utils.sleep(2);
		} catch (Exception e) {
			
		}
	}
	
	private WebElement getElementByLabelText(WebDriver driver, String lableTxt) {
		WebElement el = driver.findElement(By.xpath("//label[.//span[text()='" + lableTxt + "']]"));
		return driver.findElement(By.id(el.getAttribute("for")));
	}

}
