package com.salesforcetest.main;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Reporter;
import com.salesforcetest.shared.Utils;

public class Iso_11790_Internal_Function extends CommonServiceItemTestScenario{
	private WebDriver driver;
	public static String screenShotPath;
	private String serviceItemNumber;

	private ExtentTest testReporter;

	public Iso_11790_Internal_Function(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public void createNewIncidentSI() throws IOException {
		try {
		driver.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[title='New']")));
		driver.findElement(By.cssSelector("a[title='New']")).click();
		Utils.sleep(4);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//span[text()='Incident']")));
		driver.findElement(By.xpath("//span[text()='Incident']/parent::div/preceding-sibling::div[1]")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		WebElement el = driver.findElement(By.xpath("//label[.//span[text()='Contact Name']]"));
		driver.findElement(By.id(el.getAttribute("for"))).clear();
		driver.findElement(By.id(el.getAttribute("for"))).sendKeys("Privacy Staff");
		Utils.sleep(2);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[title='Privacy Staff']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		driver.findElement(By.cssSelector("div[title='Privacy Staff']")));
		Utils.sleep(1);
		driver.findElement(By.cssSelector("div[title='Privacy Staff']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//span[text()='Submitter']/parent::*/parent::*")));
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='1367 Protective Status Information']/parent::*/following-sibling::div/div[1]/descendant::a")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a[@title='No']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//span[text()='Details']")));
		driver.findElement(By.xpath("//span[text()='SIR Number']/parent::*/following-sibling::input")).sendKeys("2488888");
		//driver.findElement(By.xpath("//span[text()='Incident Number']/parent::*/following-sibling::input")).sendKeys("8888888888");
		driver.findElement(By.xpath("//span[text()='ECOP Number']/parent::*/following-sibling::input")).sendKeys("8888888888");
		driver.findElement(By.xpath("//span[text()='ServiceNow Number']/parent::*/following-sibling::input")).sendKeys("44444444488");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//span[text()='ServiceNow Number']/parent::*")));
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Master Incident']/parent::*/following-sibling::div/div[1]/descendant::a")).click();
		Utils.sleep(2);
		driver.findElements(By.xpath("//a[@title='No']")).get(1).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Save & New']/parent::button/following-sibling::button")).click();
		Utils.sleep(2);
		//(new WebDriverWait(driver, 40)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Related']/parent::*")));
		(new WebDriverWait(driver, 40)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Related']/parent::*")));
		Utils.sleep(2);
		testReporter.log(LogStatus.PASS, "For ISO 11790, Incident service item got created.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO 11790, Incident service item got creation failure.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			//Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
		}
	public void changeTheTypeField() throws IOException {
		try {
		//driver.findElement(By.xpath("//span[text()='Type']/parent::*/following-sibling::div")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Type']/parent::*/following-sibling::div")));
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Type']/parent::*/following-sibling::div/button")).click();
		Utils.sleep(1);
		//driver.findElement(By.xpath("//a[text()='Suspected Incident']")).click();
		driver.findElement(By.xpath("//label[text()='Type']/parent::*/descendant::input")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@title='--None--']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(2);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Type']/parent::*/following-sibling::div")));
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		driver.findElement(By.xpath("//span[text()='Type']/parent::*/following-sibling::div")));
		driver.navigate().refresh();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='In Progress']/parent::*/parent::*")));
		Utils.sleep(2);
		testReporter.log(LogStatus.PASS, "For ISO 11790, Change the charge type as None and save the incident service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO 11790, Change the charge type as None and save the incident service item failed.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
	public void blankAttachmentValidation() throws IOException {
		try {
		driver.findElement(By.xpath("//a[text()='Related']/parent::*")).click();
		Utils.sleep(4);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//span[text()='Files']/following-sibling::span[contains(text(),'0')]")));
		driver.navigate().refresh();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='In Progress']/parent::*/parent::*")));
		Utils.sleep(2);
		testReporter.log(LogStatus.PASS, "For ISO 11790, ValidATE no attachment is added.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO 11790, ValidATE no attachment is added scenario failed.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
	public void sendAMail() throws InterruptedException, IOException {
		try {
		//driver.findElement(By.xpath("//span[text()='Email']/parent::*")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Email']/parent::*")));
		Utils.sleep(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
		driver.findElement(By.xpath("//a[contains(text(),'Privacy Staff 2 <')]/parent::div")));
		driver.findElement(By.xpath("//input[@maxlength='3000']")).sendKeys("Test blank type field outbound email");
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//a[contains(text(),'Privacy Staff 2 <')]/parent::div")));
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe")));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Email Body']")));
		driver.findElement(By.xpath("//body")).click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].textContent = arguments[1];", driver.findElement(By.xpath(".//*[@id='editor_rta_body']")), "Test blank type field outbound email.");
		driver.findElement(By.xpath("//body")).sendKeys("Test blank type field outbound email.");
		//Utils.scrollWindow(driver, 200);
		driver.switchTo().defaultContent();
		//driver.findElement(By.xpath("//span[text()='Send']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Send']/parent::button")));
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-aura-class='forceActionsText']")));
		Utils.sleep(2);
		testReporter.log(LogStatus.PASS, "For ISO 11790, ValidATE user abled to send a mail for testing purpose.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO 11790, ValidATE user abled to send a mail for testing purpose failed.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			Assert.assertEquals(e.getLocalizedMessage(), "Passed");
			e.printStackTrace();
		}
	}
	public void setTestReporter(ExtentTest testReporter) {
		this.testReporter = testReporter;
	}
}
