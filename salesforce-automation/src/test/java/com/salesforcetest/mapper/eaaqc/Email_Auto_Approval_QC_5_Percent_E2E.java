package com.salesforcetest.mapper.eaaqc;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Service_Request_Rejection_E2E;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Email_Auto_Approval_QC_5_Percent_E2E {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, emailLink, subjectLine, screenShotPath, fileNm;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent; //= ExtentReporter.getInstance().getExtentReports();

	static ExtentTest testReporter;
	/*
	 * Below all are cucumber calling functions. These functions are used to map cucumber feature file scenarios and 
	 * also functions are used to log status of test scenario steps in test result execution steps.
	 */
	@Given("^Email auto approval process Registered User is logged in with \"(.*)\"$")
	public void init(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\EmailSIAutoApprovalScenarioWithHDISOVSC"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("HD VSC user Service Item creation from email and Auto approval");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() throws IOException {
		try {
			switchToClassicView();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
	public void Fetch_Email_From_Email_Services(String rowNo) throws IOException {
		try {
			fetchEmailLink(rowNo);
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email(String url, String username) throws IOException {
		try {
			logIntoGmail(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^Email auto approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage(String val) throws IOException, AWTException {
		try {
			setPercentage(val);
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Fetch the Service item number which is created through Email$")
	public void Fetch_The_SI_Number() throws IOException {
		try {
			hotLineDropDown();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Email auto approval process Log Into as HD VSC user and mark openend items as duplicate and Assign a new item with user \"(.*)\"$")
	public void Fetch_The_SI_Number(String user) throws IOException {
		try {
			searchHDISOVSCitems("HD ISO VSC");
			logInAsInternalUser("HD ISO VSC");
			duplicateSIandOpenSI();
			editSIandSave();
			testReporter.log(LogStatus.PASS, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^To edit service item Provide all new mandatory data$")
	public void create_new_service_item(DataTable dt) throws IOException, AWTException {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String receiptNumber = list.get(0).get("Receipt No");//new
		String contactNm = list.get(0).get("Contact");//new
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formNo = list.get(0).get("Form No");//new
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
		String responseComments = list.get(0).get("Response Comments");//new
		try {
			setReceiptNumberAndContactName(receiptNumber, contactNm);
			testReporter.log(LogStatus.PASS, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setOrgName(fOrgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+fOrgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+fOrgName);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setformNumberAndFormType(formNo, formType);
			testReporter.log(LogStatus.PASS, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setParamCategoryAndKind(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectSIOrigin(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectInitialQueue(queue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+queue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+queue);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setResponseComments(responseComments);
			testReporter.log(LogStatus.PASS, "Provide response comments : "+responseComments);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide response comments : "+responseComments);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Validating edited service details saved reflecting in next page$")
	public void Validate_Edit_Service_Item_Details() throws IOException {
		try {
			validateEditedItemDetailsSaved();
			testReporter.log(LogStatus.PASS, "Validating edited service details saved reflecting in next page");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating edited service details saved reflecting in next page");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Creating new response and Validating the response status$")
	public void Create_New_Response() throws IOException {
		try {
			createNewEmailResponse();
			validateEmailRelatedNewResponse();
			testReporter.log(LogStatus.PASS, "Creating new response and Validating the response status");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new response and Validating the response status");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process Replying to the triggered email \"(.*)\" with user id \"(.*)\"$")
	public void Reply_Mail_Item(String url, String username) throws IOException {
		try {
			logIntoGmailForReplyValidation(url, username, Constants.email_password);
			testReporter.log(LogStatus.PASS, "Replying to service item email password to Create child service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Replying to service item email password to Create child service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("Email auto approval process to verify child service item created and parent service item present in the child service item$")
	public void Verify_Child_Item_Created() throws IOException {
		try {
			relatedServiceItems();
			testReporter.log(LogStatus.PASS, "Verify child service item created or not and parent service item number present in child service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify child service item created or not and parent service item number present in child service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for current scenario Email auto approval process$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser Email auto approval process$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	
	//************************************Main functions******************************************
	/*
	 * lanuch function is used to start the chrome browser and load the UATG log in page and log in with the credentials.
	 */
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	/*
	 * This is a generalized functions to highlight an object. It highlights a Webelement.
	 */
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(4);
	}
	/*
	 * This generalized function is used to select any visible dropdown value.
	 * It takes two arguments 
	 * listName: List value name
	 * dropDownWebElement: WebElement of dropdown field.
	 */
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
	}
	/*
	 * This function is used to find correct frame in the page by taking a valid Webelement as an argument which is inside that specified
	 * frame.
	 */
	public void fetchCorrectIframe(By dropDownWebElement1) {
		int iFrameCount = driver.findElements(By.xpath("//div[@id='navigatortab']/descendant::iframe")).size();
		System.out.println("Frame count :"+iFrameCount);
		for(int i = 1; i<=iFrameCount+1; i++) {
			try {
				driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe["+i+"]")));
				driver.findElement(dropDownWebElement1).getText();
				break;
			} catch (Exception e) {
				driver.switchTo().defaultContent();
			}
		}
	}
	/*
	 * This function is used to select any random list value by taking webelement of the dropdown object as an argument.
	 */
	public void selectRandomDropdownListValue(WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		Random randNumber = new Random();
		int rendGeneratedNo = randNumber.nextInt(dropdown.getOptions().size());
		if (rendGeneratedNo == 0) {
			rendGeneratedNo = rendGeneratedNo+1;
		} else if (rendGeneratedNo == dropdown.getOptions().size()) {
			rendGeneratedNo = rendGeneratedNo-1;
		}
		dropdown.selectByIndex(rendGeneratedNo);
	}
	@SuppressWarnings("rawtypes")
	public Wait fluentWaitFunctionality(WebDriver driver)
	{
		@SuppressWarnings({ "unchecked", "deprecation" })
		Wait fwait = new FluentWait(driver)
		 
	    .withTimeout(20, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver);
		try
		{
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e)
		{
		
		}
	}
	/*
	 * This function is used to verify and highlight the person name after successful logging into application.
	 */
	public void verifyProfile() {
		driver.findElement(By.id("userNavLabel")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='My Profile']")).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[@id='BackToServiceDesk_Tab']/a");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.id("tailBreadcrumbNode"));
		highlightElement();
		driver.findElement(ele).click();
		Utils.sleep(2);
	}
	/*
	 * This function is used to search with the internal user and navigate to log in page
	 * of that internal user.
	 */
	public void searchHDISOVSCitems(String user) throws AWTException { // String user
		try {
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		} catch (Exception e) {
			
		}
		driver.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		driver.findElement(By.id("phSearchInput")).sendKeys(user);
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::ul[@class='autoCompleteGroup']/li[1]/a")).click();
		Utils.sleep(4);
	}
	/*
	 * This function is used to scroll to a particular position of the page.
	 * It takes an webelement parameter.(It is declared as static variable named as 'element').
	 */
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	/*
	 * This function is used to login as Internal User.
	 */
	public void logInAsInternalUser(String user) {//String user
		WebDriverWait wait = new WebDriverWait (driver, 10);
		ele = By.id("moderatorMutton");
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
			fluentWaitForElementVisibility();
			element = driver.findElement(By.id("moderatorMutton"));
			element.click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(ele);
			fluentWaitForElementVisibility();
			element = driver.findElement(By.id("moderatorMutton"));
			element.click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath(".//a[@id='USER_DETAIL']/span")).click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
		driver.findElements(By.xpath("//*[text()='Active']")).get(0).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]")).click();
		driver.switchTo().defaultContent();
		ele = By.xpath("//a[text()='"+user+"']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='Back to USCIS CRM']")).click();
		Utils.sleep(2);
	}
	/*
	 * This function is used to set the QC percentage by taking percentage value as input value.
	 */
	public void setPercentage(String percentageValue) throws AWTException { //String applicationRecord
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Quality Control Percentage']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		ele = By.xpath(".//input[@title='QC Percentage for HD ISO VSC']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//table[@title='ISO Users']/tbody/tr[1]/td[1]/input[@type='checkbox']")).click();
		Utils.sleep(1);
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(percentageValue);
		Utils.sleep(1);
		//driver.findElement(By.xpath(".//div[contains(@class,'pbBottomButtons')]/descendant::input[@value='Set QC Percentage']")).click();
		driver.findElements(By.xpath(".//input[@value='Set QC Percentage']")).get(0).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to mark all old service item as duplicate to assign new service item.
	 */
	public void deleteExistingSI () {
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
		scrollingFunction();
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		try {
			driver.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver.findElement(By.xpath("//a[text()='Delete']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			Utils.sleep(2);
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to fetch email link for a specified row value.
	 * Here 'rowNo' is the argument which takes the row value.
	 */
	public void fetchEmailLink (String rowNo) {
		driver.findElement(By.id("setupLink")).click();
		ele = By.xpath(".//input[@id='setupSearch']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).sendKeys("email services");
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='Email Services']")).click();
		element = driver.findElement(By.xpath("//a[contains(text(),'Email Service Name')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[2]/a[text()='EmailRelayRoutingHandler']"));
		highlightElement();
		element.click();
		ele = By.xpath("//*[text()='Email Service: EmailRelayRoutingHandler']");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[text()='Email Addresses']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/descendant::span[text()='"+rowNo+"']/parent::td/following-sibling::td[1]/a"));
		highlightElement();
		emailLink = element.getText();
		System.out.println("Fetch email link :"+emailLink);
	}
	/*
	 * This generalized function is used to generate random value to provide a dynamic subject line of the service item.
	 */
	public String randomDateTime() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      subjectLine = "UATG Automation Test SI - " + randomDateTime;
	      return randomDateTime;
	}
	/*
	 * This function is used to send mail from gmail with the fetched email link to create a new service item from gmail.
	 */
	public void logIntoGmail (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver.get(url);
		Utils.sleep(20);
		try {
			Utils.sleep(2);
			driver.findElement(By.id("username")).sendKeys("udanturthy");
	//driver.findElement(By.id("password")).sendKeys(passowrd);
			Utils.sleep(5);
			driver.findElement(By.xpath("//div[3]/div/button")).click();
			Utils.sleep(1);
			driver.findElement(By.id("password")).sendKeys(passowrd);
		//driver.findElement(By.xpath("//input[@value='Log in']")).click();
			Utils.sleep(4);
			driver.findElement(By.xpath("//div[4]/div/button")).click();
		} catch (Exception e) {
			
		}
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[text()='skip']")).click();
		Utils.sleep(6);
		driver.findElement(By.xpath("//*[text()='Gmail']")).click();
		Utils.sleep(5);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		System.out.println(tabs2.size());
		Utils.sleep(4);
		driver.switchTo().window(tabs2.get(1));

		WebDriverWait wait = new WebDriverWait (driver, 10);
		//ele = By.id("username");
	//wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		//driver.findElement(By.id("username")).sendKeys(username+"@acumensolutions.com");
		Utils.sleep(10);
	//	driver.findElement(By.xpath("//div[1]/div/span/span")).click();
	//ele = By.id("password");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		//Utils.sleep(1);
	//	driver.findElement(By.id("password")).sendKeys(passowrd);
		//Utils.sleep(1);
		//driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		
	//	try {
			//WebDriverWait wait = new WebDriverWait (driver, 5);
		//ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
		//	wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			//driver.findElement(By.xpath("//span[text()='Continue']")).click();
		//} catch (Exception e) {
			
	//	}
		 driver.findElement((By.xpath("//div[2]/div/div[1]//span/span"))).click();
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	/*
	 * This function is used to select 'HD Hotline Follow-up I-360' value from
	 * dropdown list and fetch the newly created service item number.
	 */
	public void hotLineDropDown () {
		//clear all tab code
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
				Utils.sleep(4);
				WebDriverWait wait = new WebDriverWait (driver, 14);
				ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
				fluentWaitForElementVisibility();
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				Utils.sleep(2);
				Actions actObj = new Actions(driver);
				actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
				Utils.sleep(4);
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
				scrollingFunction();
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
				Utils.sleep(2);
				try {
				driver.findElement(By.xpath("//div[@title='Subject']")).click();
				Utils.sleep(2);
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
				highlightElement();
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
				highlightElement();
				newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText(); //// need to chang
				Utils.sleep(2);
				} catch (Exception e) {
					driver.findElement(By.xpath("//div[contains(@title,'Time Opened')]")).click();
					Utils.sleep(2);
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
					highlightElement();
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
					highlightElement();
					newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText(); //// need to chang
					Utils.sleep(1);
				}
				driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to mark all old service item as duplicate and assign newly created service item.
	 * it will iterate maximum 51 time to assign newly created service item.
	 */
	public void duplicateSIandOpenSI () throws AWTException {
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//button[contains(text(),'Don')]")).click();
			Utils.sleep(1);
		} catch (Exception e) {
			
		}
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		try {
		element = driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']/preceding::option[1]"));
		scrollingFunction();
		} catch (Exception e) {
			
		}
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		for (int itr = 0; itr < 50; itr++ ) {
		Robot robot = new Robot();
		try {
			driver.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver.findElement(By.xpath(".//input[@value='Mark as Duplicate']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			Utils.sleep(1);
			wait.until(ExpectedConditions.alertIsPresent());
			Utils.sleep(1);
			driver.switchTo().alert().accept();
			Utils.sleep(1);
		} catch (Exception e) {
			System.out.println("No Service Item number is present for duplication process.");
		}
		driver.findElement(By.xpath(".//input[@title='Refresh']")).click();
		Utils.sleep(3);
			try {
				driver.findElement(By.xpath("//input[@value='Assign a Service Item']")).click();
				wait.until(ExpectedConditions.alertIsPresent());
				Utils.sleep(1);
				if(driver.switchTo().alert().getText().contains("The service item could not be assigned.Update failed.")) {
					testReporter.log(LogStatus.FAIL, "Allert message exception. message: "+driver.switchTo().alert().getText());
					driver.switchTo().alert().accept();
					getResult();
					Assert.assertTrue(false);
					break;
				}
				robot.keyPress(KeyEvent.VK_ENTER);
				Utils.sleep(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']"));
				highlightElement();
				driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']")).click();
				break;
			} catch (Exception e) {
				System.out.println("Not found "+newSINo+" service item in this current duplication step of iteration no "+itr);
			}
		}
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to click on edit and save button.
	 */
	public void editSIandSave () {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to set the receipt number and contact name to the edit service item page.
	 */
	public void setReceiptNumberAndContactName (String receiptNo, String contactNm) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNo);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/div[@class='requiredInput']/span/a/img")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver.getWindowHandles().size());
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
		    driver.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='searchFrame']"))));
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@maxlength='80' and @name='lksrch']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@maxlength='80' and @name='lksrch']")).clear();
		driver.findElement(By.xpath("//input[@maxlength='80' and @name='lksrch']")).sendKeys(contactNm);
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@title='Go!']")).click();
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='resultsFrame']"))));
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='Contact_body']/table/tbody/descendant::a[text()='"+contactNm+"']")).click();
		Utils.sleep(2);
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to log out from current internal user.
	 */
	public void currentUserLogOut() {
		driver.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Logout']")));
		Utils.sleep(6);
	}
	/*
	 * This function is used to set organization name in the edit service item page.
	 */
	public void setOrgName(String orgName) { //String orgName
		WebDriverWait wait = new WebDriverWait (driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input")).sendKeys(orgName);
	}
	/*
	 * This function is used to set email id in the edit service item page.
	 */
	public void setEmail(String email) { //String email
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
	}
	/*
	 * This function is used to generate random number.
	 * It does not generate the subject line.
	 */
	public String randomDateTime1() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      randomDateTime = "" + randomDateTime;
	      return randomDateTime;
	}
	/*
	 * This function is used to select the given sender type from dropdown list.
	 */
	public void selectSenderType(String senderType) { //String senderType
		try {
			selectDropdownListValue(senderType, driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[2]/span/select")));
		} catch (Exception e) {
			fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
			selectDropdownListValue(senderType, driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[2]/span/select")));
		}
		Utils.sleep(1);
	}
	/*
	 * This function is used to set random subject and description and form type from dropdown list.
	 */
	public void setRandomSubjectAndDesAndFormType() {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		Random randNumber = new Random();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys("Test Subject_"+randNumber.nextInt(10000));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys("Test Description_"+randNumber.nextInt(10000));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		Utils.sleep(1);
	}
	/*
	 * This function is used to provide for a specified subject line and description to the service item.
	 * subject: This argument takes service item subject line input.
	 * description: This argument takes service item description line input.
	 */
	public void setParamSubjectAndDesAndFormType(String subject, String description) {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		Utils.sleep(1);
	}
	/*
	 * This function is used to select the form number and form type from dropdown list.
	 * formNumber: Valid form number.
	 * formType: Valid form type. 
	 */
	public void setformNumberAndFormType(String formNumber, String formType) {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		try {
		selectDropdownListValue(formNumber, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/span/select")));
		selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/span/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(formNumber, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/div/span/select")));
			selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		}
		Utils.sleep(1);
	}
	/*
	 * This function is used to select random Category and kind from available dropdown list value. 
	 */
	public void setRandomCategoryAndKind() {
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		Random randNumber = new Random();
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys("Auto Generated Comments_"+randNumber.nextInt(10000));
		Utils.sleep(1);
	}
	/*
	 * This function is used to select category, kind and comments by taking input.
	 * category: Provide any valid category for service item creation.
	 * kind:  Provide any valid kind for service item creation.
	 * comments: Provide any comments.
	 */
	public void setParamCategoryAndKind(String category, String kind, String comments) {
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		selectDropdownListValue(category,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectDropdownListValue(kind,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
		Utils.sleep(1);
	}
	/*
	 * This function is used to select Service Item IO origin from the dropdown list value.
	 * serviceItem: Provide a valid IO Origin value from dropdown list.
	 */
	public void selectSIOrigin(String serviceItem) { //String serviceItem
		selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/select")));
	}
	/*
	 * This function is used to select initial queue from dropdown list value.
	 * initialQueue: Provide a valid Initial queue value from dropdown list.
	 */
	public void selectInitialQueue(String initialQueue) { //String initialQueue
		try {
		selectDropdownListValue(initialQueue, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[2]/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(initialQueue, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/span/select")));
		}
	}
	/*
	 * This function is used to set time and date in the service item page.
	 * receivedDate: Provide a date and time in '10/4/2018 9:43 AM' format.
	 */
	public void setReceivedDate(String receivedDate) throws AWTException { //String receivedDate
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/input")).sendKeys(receivedDate);
		Robot robot = new Robot();
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(2);
	}
	/*
	 * This function is used to give user defined comments.
	 */
	public void setResponseComments(String text) throws AWTException { //String receivedDate
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[3]/td[4]/textarea")).sendKeys(text);
	}
	/*
	 * This function is used to set comments when a IPO CSR user tries to edit/create a service item.
	 */
	public void setResponseCommentsIpo(String text) throws AWTException { //String receivedDate
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/textarea")).sendKeys(text);
	}
	/*
	 * This function is used to save a particular service item.
	 */
	public void clickOnSaveSI() {
		element = driver.findElement(By.xpath(".//*[@id='bottomButtonRow']"));
		scrollingFunction();
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[contains(@value,'Save')][1]")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(8);
	}
	/*
	 * This function is used to validate the saved SI application nuber and contact name.
	 */
	public void validateEditedItemDetailsSaved() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]"));
		highlightElement();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to validate the service response status is changed to 'Sent' or not.
	 * This function will iterate 5 times till it finds the desired response status then it would log a defect.
	 */
	public void validateEmailRelatedNewResponse() {
		for (int iterator=0; iterator<4;iterator++) {
		try {
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath(".//span[@class='tabText' and text()='New Response']");
			fluentWaitForElementVisibility();
			driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath(".//span[@class='tabText' and text()='New Response']");
			fluentWaitForElementVisibility();
			driver.findElement(ele).click();
			Utils.sleep(2);
			fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
			driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
			highlightElement();
			element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
			scrollingFunction();
			element = driver.findElement(By.xpath("//th[text()='Sent']"));
			highlightElement();
			break;
		} catch (Exception e) {
			System.out.println("Iteration Count :"+iterator);
		}
		}
		element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void createNewEmailResponse() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Greetings')]"));
		scrollingFunction();
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'HD Greeting')]")).click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Change of Address')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'HD Change of Address')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'3156:Request to Change Address to Attorney')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD ISOA Closing')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD ISOA Closing')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	//******************************************************IPO Related Script********************************************************************************
	@Given("^IPO Email auto approval process Registered User is logged in with \"(.*)\"$")
	public void init_For_IPO(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\EmailSIAutoApprovalScenarioWithIpo"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO user Service Item creation from email and Auto approval");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp$")
	public void verify_Logged_In_User_Profile_For_IPO() throws IOException {
		try {
			switchToClassicView();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile and switches to classic view if it is in lightning exp view:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
	public void Fetch_Email_From_Email_Services_For_IPO(String rowNo) throws IOException {
		try {
			fetchEmailLink(rowNo);
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email_For_IPO(String url, String username) throws IOException {
		try {
			logIntoGmail(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^IPO Email auto approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_IPO(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_IPO(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_IPO(String val) throws IOException {
		try {
			setPercentageForIpo(val);
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Fetch the Service item number which is created through Email$")
	public void Fetch_The_SI_Number_For_IPO() throws IOException {
		try {
			ipoSuperUserEmailToQ("IPO Email To Case Q");
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Email auto approval process Log Into as IPO Normal user and mark openend items as duplicate and Assign a new item with user \"(.*)\"$")
	public void Fetch_The_SI_Number_For_IPO(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			logInAsInternalUser(user);
			duplicateSIandOpenSI();
			editSIandSave();
			testReporter.log(LogStatus.PASS, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^IPO To edit service item Provide all new mandatory data$")
	public void create_new_service_item_For_IPO(DataTable dt) throws IOException {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String receiptNumber = list.get(0).get("Receipt No");//new
		String contactNm = list.get(0).get("Contact");//new
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String formNo = list.get(0).get("Form No");//new
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Issue");
		String kind = list.get(0).get("Action");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String dateTime = list.get(0).get("DateTime");
		String responseComments = list.get(0).get("Response Comments");//new
		try {
			setReceiptNumberAndContactNameForIpo(receiptNumber, contactNm);
			testReporter.log(LogStatus.PASS, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set receipt number and contact name respectively : "+receiptNumber+" and "+contactNm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setformNumberAndFormType(formNo, formType);
			testReporter.log(LogStatus.PASS, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			issueAndAction(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Issue, Action Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Issue, Action Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectSIOriginForIpo(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setResponseCommentsIpo(responseComments);
			testReporter.log(LogStatus.PASS, "Provide response comments : "+responseComments);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide response comments : "+responseComments);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("IPO Email auto approval process Validating edited service details saved reflecting in next page$")
	public void Validate_Edit_Service_Item_Details_For_IPO() throws IOException {
		try {
			validateEditedItemDetailsSaved();
			testReporter.log(LogStatus.PASS, "Validating edited service details saved reflecting in next page");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating edited service details saved reflecting in next page");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("IPO Email auto approval process Creating new response and Validating the response status$")
	public void Create_New_Response_For_IPO() throws IOException {
		try {
			createNewEmailResponseForIpo();
			validateEmailRelatedNewResponse();
			testReporter.log(LogStatus.PASS, "Creating new response and Validating the response status");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new response and Validating the response status");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO Stop Report Generation for current scenario Email auto approval process$")
	public void getResult_For_IPO() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^IPO Close the browser Email auto approval process$")
	public void flushReporter_For_IPO() {
		driver.close();
		driver.quit();
	}
	
	//****************************************************Main Functions*****************************************************
	/*
	 * This function is used to fetch the newly created service item number with ipo super log in.
	 */
	public void ipoSuperUserEmailToQ (String dropDownSelect) {
		//clear all tab code
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
				Utils.sleep(4);
				WebDriverWait wait = new WebDriverWait (driver, 14);
				ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
				fluentWaitForElementVisibility();
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				Utils.sleep(2);
				Actions actObj = new Actions(driver);
				actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
				Utils.sleep(4);
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
				Utils.sleep(2);
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='"+dropDownSelect+"']")).click();
				Utils.sleep(2);
				try {
					driver.findElement(By.xpath("//div[contains(@title,'Time Opened')]")).click();
					Utils.sleep(2);
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
				scrollingFunction();
				highlightElement();
				element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
				highlightElement();
				newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText();
				Utils.sleep(1);
				} catch (Exception e) {
					driver.findElement(By.xpath("//div[contains(@title,'Time Opened')]")).click();
					Utils.sleep(2);
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
					highlightElement();
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
					highlightElement();
					newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText(); //// need to chang
					Utils.sleep(1);
				}
				driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to create new response with ipo user.
	 */
	public void createNewEmailResponseForIpo() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"))).doubleClick().build().perform();
		/*element = driver.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element);*/
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		/*element.click();
		element.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]"));
		//scrollingFunction();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Closing')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		/*ele = By.xpath("//div[contains(text(),'select one or more recipients before submitting')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Go Back')]")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		//element = driver.findElement(By.xpath("//span[contains(text(),'Is selected?')]/parent::label"));
		element = driver.findElements(By.xpath("//span[contains(text(),'isSelected')]/parent::label")).get(0);
		element.click();
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();*/
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to set QC percentage for Ipo user.
	 */
	public void setPercentageForIpo(String percentageValue) throws AWTException { //String applicationRecord
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Quality Control Percentage']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).click();
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).click();
		driver.findElement(By.xpath("//input[@id='phSearchInput']")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		ele = By.xpath(".//input[@title='QC Percentage for IPO CSR']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//table[@title='ISO Users']/tbody/tr[1]/td[1]/input[@type='checkbox']")).click();
		Utils.sleep(1);
		driver.findElement(ele).clear();
		driver.findElement(ele).sendKeys(percentageValue);
		Utils.sleep(1);
		//driver.findElement(By.xpath(".//div[contains(@class,'pbBottomButtons')]/descendant::input[@value='Set QC Percentage']")).click();
		driver.findElements(By.xpath(".//input[@value='Set QC Percentage']")).get(0).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	/*
	 * This function is used to set receipt number and contact name.
	 */
	public void setReceiptNumberAndContactNameForIpo (String receiptNo, String contactNm) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNo);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/descendant::img[@class='lookupIcon']")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver.getWindowHandles().size());
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
		    driver.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='searchFrame']"))));
		driver.findElement(By.xpath("//input[@id='lksrch']")).sendKeys(contactNm);
		driver.findElement(By.xpath("//input[@name='go']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='resultsFrame']"))));
		driver.findElement(By.xpath(".//*[@id='Contact_body']/table/tbody/tr[2]/th/a[text()='"+contactNm+"']")).click();
		Utils.sleep(2);
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
	}
	/*
	 * This function is used to set issue, action and comments for IPO service item.
	 */
	public void issueAndAction(String issue, String action, String comments) {
		element = driver.findElement(By.xpath("//*[contains(text(),'Issue / Action')]"));
		scrollingFunction();
		selectDropdownListValue(issue, driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::select")));
		selectDropdownListValue(action, driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
	}
	/*
	 * This function is used to set the IO origin for IPO service item.
	 */
	public void selectSIOriginForIpo(String serviceItem) { //String serviceItem
		element = driver.findElement(By.xpath("//*[contains(text(),'Service Item Details')]"));
		scrollingFunction();
		selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[2]/td[2]/div/span/select")));
	}
	//********************************************************************************************************************************************************
	/*
	 * This is used to change the log into classic view.
	 */
	public void switchToClassicView() {
		try {
		WebDriverWait wait = new WebDriverWait (driver, 5);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement((By.xpath("//button[contains(@class,'oneUserProfileCardTrigger')]")))));
		driver.findElement((By.xpath("//button[contains(@class,'oneUserProfileCardTrigger')]"))).click();
		Utils.sleep(1);
		driver.findElement((By.xpath("//a[contains(text(),'Switch to Salesforce Classic')]"))).click();
		ele = By.xpath(".//*[@id='BackToServiceDesk_Tab']/a");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		} catch (Exception e) {
			
		}
	}
	/*
	 * This function is used to reply back from a received mail from gmail with attachment.
	 */
	public void logIntoGmailForReplyValidation (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
	    robot.delay(1000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('about:blank','_blank');");
	    robot.delay(2000);
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys("udanturthy");
		Utils.sleep(4);
		driver.findElement(By.xpath("//div/div[3]/div/button")).click();
		Utils.sleep(4);
		driver.findElement(By.id("password")).sendKeys(passowrd);
		Utils.sleep(4);
		driver.findElement(By.xpath("//div/div[4]/div/button")).click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[text()='skip']")).click();
		Utils.sleep(6);
		driver.findElement(By.xpath("//*[text()='Gmail']")).click();
		Utils.sleep(5);
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		System.out.println(tabs2.size());
		Utils.sleep(4);
		driver.switchTo().window(tabs2.get(2));

		Utils.sleep(4);
      driver.findElement((By.xpath("//div[2]/div/div[1]//span/span"))).click();
		Utils.sleep(4);
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		Utils.sleep(8);
		try {
			driver.findElement(By.xpath("//div[text()='Updates' and contains(@data-tooltip,'Personal')]")).click();
			Utils.sleep(4);
			List<WebElement> email = driver.findElements(By.cssSelector("div.xT>div.y6>span.bog"));
	
			for(WebElement emailsub : email) {
				System.out.println(emailsub.getText()+" email subjects....");
			    if(emailsub.getText().contains(subjectLine) == true){
			           emailsub.click();
			           break;
			        }
			}
			ele = By.cssSelector("div[data-tooltip='More']");
			fluentWaitForElementVisibility();
			driver.findElement(By.cssSelector("div[data-tooltip='Reply']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[text()='Primary']")).click();
			Utils.sleep(2);
			List<WebElement> email = driver.findElements(By.cssSelector("div.xT>div.y6>span"));

			for(WebElement emailsub : email) {
				System.out.println(emailsub.getText()+" email subjects....");
			    if(emailsub.getText().contains(subjectLine) == true) {
			           emailsub.click();
			           break;
			        }
			}
			ele = By.cssSelector("div[data-tooltip='More']");
			fluentWaitForElementVisibility();
			driver.findElement(By.cssSelector("div[data-tooltip='Reply']")).click();
		}
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[contains(@class,'aoD hl')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@tabindex='-1']/following-sibling::textarea")).sendKeys(emailLink);
		Utils.sleep(1);
		driver.findElement(By.className("Am")).sendKeys("Automated reply mail to create child service item.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		testReporter.log(LogStatus.PASS, "Replied to the service item with "+subjectLine+" subject line from gmail.");
	}
	/*
	 * This function is used to valiedated related service item which was created from gmail response.
	 */
	public void relatedServiceItems() throws IOException {
		String flag = "FOUND";
		driver.switchTo().defaultContent();
		Utils.sleep(20);//Waiting for email child item creation to be completed.
		for(int count =0;count<2;count++) {
		try {
			for (int loop = 0;loop<8;loop++) {
			try {
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath(".//span[@class='tabText' and text()='New Response']");//New Response
			fluentWaitForElementVisibility();
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath(".//span[@class='tabText' and text()='New Response']");//New Response
			fluentWaitForElementVisibility();
			driver.findElement(ele).click();
			Utils.sleep(2);
			fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
			driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//h3[text()='Related Service Items']"));
			scrollingFunction();
			Utils.sleep(2);
				element = driver.findElement(By.xpath("//th[text()='Service Item']/parent::tr/following-sibling::tr[1]/th/a"));
				highlightElement();
				testReporter.log(LogStatus.PASS, "Child service item number which is created from mail is :"+element.getText());
				element.click();
				break;
				} catch (Exception e) {
					Utils.sleep(120);	
				}
			}
			driver.switchTo().defaultContent();
			Utils.sleep(2);
			fetchCorrectIframe(By.xpath("//td[text()='Parent Service Item']/following-sibling::td[1]/div/a[text()='"+newSINo+"']"));
			ele = By.xpath("//td[text()='Parent Service Item']/following-sibling::td[1]/div/a[text()='"+newSINo+"']");//change
			fluentWaitForElementVisibility();
			element = driver.findElement(By.xpath("//td[text()='Parent Service Item']/following-sibling::td[1]/div/a[text()='"+newSINo+"']"));//change
			scrollingFunction();
			testReporter.log(LogStatus.PASS, "Opened the child service item and parent SI "+newSINo+" is present in the page.");
			highlightElement();
			flag = "Passed";
			break;
		} catch (Exception e) {
			flag = "Failed";
		}
		}
		if (flag == "Failed") {
			testReporter.log(LogStatus.FAIL, "Opened the child service item failure(Child item did not get created) or child SI of parent SI "+newSINo+" is not present in the page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
	}
	//*******************************IPO-10911 Functions************************************************
	@Given("^IPO_10911 Email auto approval process Registered User is logged in with \"(.*)\"$")
	public void init_For_IPO_10911(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\IPO_10911_Scenario_Validation"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO 10911 scenario Email CC BCC and load email attachment validation");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp$")
	public void verify_Logged_In_User_Profile_For_IPO_10911() throws IOException {
		try {
			switchToClassicView();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile and switches to classic view if it is in lightning exp view:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
	public void Fetch_Email_From_Email_Services_For_IPO_10911(String rowNo) throws IOException {
		try {
			fetchEmailLink(rowNo);
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email_For_IPO_10911(String url, String username) throws IOException {
		try {
			logIntoGmailWithAttachment(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^IPO_10911 Email auto approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_IPO_10911(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_IPO_10911(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_IPO_10911(String val) throws IOException {
		try {
			setPercentageForIpo(val);
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Fetch the Service item number which is created through Email$")
	public void Fetch_The_SI_Number_For_IPO_10911() throws IOException {
		try {
			ipoSuperUserEmailToQ("IPO Email To Case Q");
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetch the Service Item number whose subject line is "+subjectLine+" and SI number "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Email auto approval process Log Into as IPO Normal user and mark openend items as duplicate and Assign a new item with user \"(.*)\"$")
	public void Fetch_The_SI_Number_For_IPO_10911(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			logInAsInternalUser(user);
			duplicateSIandOpenSI();
			editSIandSave();
			testReporter.log(LogStatus.PASS, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Marking all open items as duplicate and assign new SI and Open the item in Edit mode with "+user+" user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^IPO_10911 To edit service item Provide all new mandatory data$")
	public void create_new_service_item_For_IPO_10911(DataTable dt) throws IOException {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String receiptNumber = list.get(0).get("Receipt No");//new
		String contactNm = list.get(0).get("Contact");//new
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String formNo = list.get(0).get("Form No");//new
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Issue");
		String kind = list.get(0).get("Action");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String dateTime = list.get(0).get("DateTime");
		String responseComments = list.get(0).get("Response Comments");//new
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setformNumberAndFormType(formNo, formType);
			testReporter.log(LogStatus.PASS, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select FormNumber and Form Type respectively: "+formNo+" ,"+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			issueAndAction(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Issue, Action Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Issue, Action Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			selectSIOriginForIpo(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			setResponseCommentsIpo(responseComments);
			testReporter.log(LogStatus.PASS, "Provide response comments : "+responseComments);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide response comments : "+responseComments);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	
	@Then("^IPO_10911 create new service item response with multiple contacts and validating email attachment is appearing in the Load Attachment section$")
	public void Validate_Create_New_Response_with_attachment_For_IPO_10911() throws IOException {
		try {
			createNewIpoResponseFor10911();
			testReporter.log(LogStatus.PASS, "create new service item response with multiple contacts and validating email attachment is appearing in the Load Attachment section");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "create new service item response with multiple contacts and validating email attachment is appearing in the Load Attachment section");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Validate cc BCC email is appearing in the out bound mail section and check the response status is changed to Sent$")
	public void Validate_CC_BCC_Mail_For_IPO_10911() throws IOException {
		try {
			validateCCBCCEmail();
			testReporter.log(LogStatus.PASS, "Validate cc BCC email is appearing in the out bound mail section and check the response status is changed to Sent");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate cc BCC email is appearing in the out bound mail section and check the response status is changed to Sent");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^IPO_10911 Stop Report Generation for current scenario Email auto approval process$")
	public void getResult_For_IPO_10911() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^IPO_10911 Close the browser Email auto approval process$")
	public void flushReporter_For_IPO_10911() {
		driver.close();
		driver.quit();
	}
	//**************************************************************************************************
	//*******************************IPO-10911 Functions************************************************
	public void logIntoGmailWithAttachment (String url, String username, String passowrd) throws Exception {
		Robot robot = new Robot();
		driver.get(url);
		try {
		driver.findElement(By.id("sign_in_username")).sendKeys(username);
		driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		} catch (Exception e) {
			
		}
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.id("username");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(By.id("username")).sendKeys(username+"@acumensolutions.com");
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		ele = By.id("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		Utils.sleep(1);
		driver.findElement(By.id("password")).sendKeys(passowrd);
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		try {
			//WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		Utils.sleep(1);
		attach_file_and_send(Constants.privacy_pia_attachment_url, false);
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
		try {
		driver.findElement(By.cssSelector("[command='Files']")).click();
		} catch (Exception e) {
			
		}

		StringSelection s = new StringSelection(attachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();

		Utils.sleep(2);
		if (Constants.isWindows()) {
			// For windows
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			Utils.sleep(3);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} else {
			// Cmd + Tab is needed since it launches a Java app and the browser looses focus
			Utils.sleep(5);
			if (pressTab) {
				robot.keyPress(KeyEvent.VK_META);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_META);
				robot.keyRelease(KeyEvent.VK_TAB);
			}

			robot.keyPress(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_SHIFT);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_G);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_G);
			Utils.sleep(1);

			// Paste the clipboard value
			robot.keyPress(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_V);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_V);
			Utils.sleep(1);

			// Press Enter key to close the Goto window and Upload window
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(1);
		}

		// switch back
		driver.switchTo().activeElement();

		Utils.sleep(4);
	}
	public void createNewIpoResponseFor10911() {
		WebDriverWait wait = new WebDriverWait (driver, 300);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElements(By.xpath("//div[contains(text(),'Response Recipients')]")).get(0);
		scrollingFunction();
		driver.findElements(By.xpath("//input[@maxlength='255']")).get(0).sendKeys("IPO");
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Select Item 1']/preceding-sibling::span"))));
		driver.findElement(By.xpath("//span[text()='Select Item 1']/preceding-sibling::span")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Select Item 2']/preceding-sibling::span")).click();
		Utils.sleep(1);
		selectDropdownListValue("CC", driver.findElement(By.xpath("//tr[2]/td[4]/div/select")));
		Utils.sleep(1);
		testReporter.log(LogStatus.PASS, "CC email address is added.");
		selectDropdownListValue("BCC", driver.findElement(By.xpath("//tr[3]/td[4]/div/select")));
		Utils.sleep(1);
		testReporter.log(LogStatus.PASS, "BCC email address is added.");
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]"));
		//scrollingFunction();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Closing')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//button[contains(text(),'Save Draft')]"));
		Utils.sleep(2);
		element.click();
		ele = By.xpath("//div[contains(text(),'Your draft response has been saved successfully.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//button[contains(text(),'Save Draft')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//button[contains(text(),'Load Original Attachments')]")).click();
		Utils.sleep(2);
		ele = By.xpath("//div[contains(text(),'File uploaded successfully')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//button[contains(text(),'Load Original Attachments')]"));
		scrollingFunction();
		fileNm = driver.findElement(By.xpath("//button[@title='Delete Attachment']/preceding-sibling::button")).getAttribute("title");
		Utils.sleep(1);
		System.out.println("File Name"+fileNm);
		testReporter.log(LogStatus.PASS, "Loded file name "+fileNm);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(3);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[text()='View Response']")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//h3[text()='Files']"));
		element = driver.findElement(By.xpath("//a[text()='"+fileNm+"']/parent::*"));
		highlightElement();
		testReporter.log(LogStatus.PASS, fileNm+" attachment file is present in view response page.");
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']")).click();
		driver.switchTo().defaultContent();
	}
	//call the response check functions
	public void validateCCBCCEmail() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
		highlightElement();
		element = driver.findElement(By.xpath("//input[@value='Send an Email']/parent::*"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//tr[2]/th[text()='Sent']/following-sibling::td[2]/a")).click();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//h3[text()='Address Information']/parent::*"));
		element = driver.findElement(By.xpath("//td[text()='To Address']/parent::tr"));
		highlightElement();
		element = driver.findElement(By.xpath("//td[text()='CC Address']/parent::tr"));
		highlightElement();
		element = driver.findElement(By.xpath("//td[text()='BCC Address']/parent::tr"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	@Given("^Response Search template selection process Registered User is logged in with \"(.*)\"$")
	public void init2(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\IPOSearchTemplate"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("HD VSC user Service Item creation from email and Auto approval");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Response Search template selection process Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile2() throws IOException {
		try {
			switchToClassicView();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^Response Search template selection process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User2(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Response Search template selection process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile2(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Response Search template selection open any random Service Item \"(.*)\"$")
	public void Open_Any_Random_SI(String user) throws IOException {
		try {
			openRandomSIFromOpenItems(user);
			testReporter.log(LogStatus.PASS, "Open any random existing service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open any random existing service item.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Response Search template selection Verify the new search functionality for template selection$")
	public void Response_Template_Selection() throws IOException {
		try {
			templateResponseSearchValidation();
			testReporter.log(LogStatus.PASS, "Select the response selection with New Search functionality and save the response.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select the response selection with New Search functionality and save the response.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for current scenario Response Search template selection$")
	public void getResult_Template() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser Response Search template selection$")
	public void flushReporter_Template() {
		driver.close();
		driver.quit();
	}
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	public void templateResponseSearchValidation () {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath("//div[contains(text(),'Email Template Search')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).click();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("IPO Greetings");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='IPO Greetings']/descendant::span[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[contains(text(),'Selected')]/parent::div/following-sibling::div/descendant::*[text()='IPO Greetings']"));
		highlightElement();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("ASC Appointment Language (due to C3 Conversion)");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='ASC Appointment Language (due to C3 Conversion)']/descendant::span[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(2);
		
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("IPO Closing");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='IPO Closing']/descendant::span[contains(text(),'IPO Closing')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void openRandomSIFromOpenItems(String receiptNo) { //Start function from here.
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		Utils.sleep(2);
		try {
			newSINo = driver.findElement(By.xpath(".//table[contains(@class,'row-table')]/tbody/tr[1]/td[5]/div/a/span[text()='"+receiptNo+"']/parent::a/parent::div/parent::td/preceding-sibling::td[1]/div/a")).getText();
			ele = By.xpath("//a[text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			scrollingFunction();
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[@title='Date/Time Opened']")).click();
			Utils.sleep(2);
			newSINo = driver.findElement(By.xpath(".//table[contains(@class,'row-table')]/tbody/tr[1]/td[5]/div/a/span[text()='"+receiptNo+"']/parent::a/parent::div/parent::td/preceding-sibling::td[1]/div/a")).getText();
			ele = By.xpath("//a[text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			scrollingFunction();
		}
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click(); //// need to change
		System.out.println("Clicked on random service item :"+newSINo);
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	//**************************************************************************************************
	//#####################################################CCD SCU Analyst 1###################################################################
	public String randomDateTime2() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      subjectLine = "Tier 4 Email to Case - " + randomDateTime;
	      return randomDateTime;
	}
	/*
	 * This function is used to send mail from gmail with the fetched email link to create a new service item from gmail.
	 */
	public void logIntoGmail1 (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver.get(url);
		try {
			Utils.sleep(2);
			driver.findElement(By.id("identifierId")).sendKeys(username+"@acumensolutions.com");
			//driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Next']/parent::*")).click();
		} catch (Exception e) {
			
		}
		WebDriverWait wait = new WebDriverWait (driver, 10);
		ele = By.id("username");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(By.id("username")).sendKeys(username+"@acumensolutions.com");
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		ele = By.id("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		Utils.sleep(1);
		driver.findElement(By.id("password")).sendKeys(passowrd);
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		try {
			//WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime2();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	public void selectTheEmailSIAndAssign (String dropDownSelect) {
		//clear all tab code
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
				Utils.sleep(4);
				WebDriverWait wait = new WebDriverWait (driver, 14);
				ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
				fluentWaitForElementVisibility();
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				Utils.sleep(2);
				Actions actObj = new Actions(driver);
				actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
				Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
				Utils.sleep(4);
				driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
				Utils.sleep(2);
				driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='"+dropDownSelect+"']")).click();
				Utils.sleep(2);
				try {
					driver.findElement(By.xpath("//div[contains(@title,'Time Opened')]")).click();
					Utils.sleep(2);
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
					scrollingFunction();
					highlightElement();
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[3]/div/a"));
					highlightElement();
					newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[3]/div/a")).getText();
					Utils.sleep(1);
				} catch (Exception e) {
					driver.findElement(By.xpath("//div[contains(@title,'Time Opened')]")).click();
					Utils.sleep(2);
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']"));
					highlightElement();
					element = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[3]/div/a"));
					highlightElement();
					newSINo = driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[3]/div/a")).getText(); //// need to chang
					Utils.sleep(1);
				}
				driver.findElement(By.xpath(".//span[text()='"+subjectLine+"']/parent::a/parent::div/parent::td/preceding-sibling::td[6]/descendant::input")).click();
				Utils.sleep(1);
				driver.findElement(By.xpath("//input[@value='Change Owner']")).click();
				Utils.sleep(1);
				driver.switchTo().defaultContent();
	}
	public void changeOwnerAndSave () {
		WebDriverWait wait = new WebDriverWait (driver, 14);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		ele = By.xpath("//input[@title='Owner name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@title='Owner name']/following-sibling::a")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver.getWindowHandles().size());
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@id='searchFrame']"))));
		driver.findElement(By.xpath("//input[@id='lksrch' and @placeholder='Search...']")).sendKeys("CCD");
		driver.findElement(By.xpath("//input[@id='lksrch' and @placeholder='Search...']/following-sibling::input")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@id='resultsFrame']"))));
		driver.findElement(By.xpath("//a[text()='CCD SCU Analyst 1']")).click();
		Utils.sleep(2);
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
//		driver.findElement(By.xpath("//input[@title='Owner name']")).sendKeys("CCD SCU Analyst 1");
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Owner Change is Successful");
	}
	public void openTheSI () {
//		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
//		Utils.sleep(2);
//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
//				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
//		Utils.sleep(4);
//		WebDriverWait wait = new WebDriverWait (driver, 14);
//		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
//		fluentWaitForElementVisibility();
//		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
//		Utils.sleep(2);
//		Actions actObj = new Actions(driver);
//		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
//		Utils.sleep(2);
//		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
//				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
//		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		Utils.sleep(1);
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//a[text()='"+newSINo+"']/parent::*/parent::*/following-sibling::td[1]/descendant::span[text()='CCD SCU Analyst 1']"));
		highlightElement();
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Service item is available in My Open Service Items dropdown list.");
	}
	public void EditFewFieldsandValidate () {
		WebDriverWait wait = new WebDriverWait (driver, 14);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("N600", driver.findElement(By.xpath("//td[text()='Form Number']/following-sibling::td[1]/descendant::select")));
		selectDropdownListValue("N-600 Application for Certificate of Citizenship", driver.findElement(By.xpath("//td[text()='Form Type']/following-sibling::td[1]/descendant::select")));
		driver.findElement(By.xpath("//input[@value='OK']")).click();
		Utils.sleep(2);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[3]/td[2]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("Address Change", driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[3]/td[2]/descendant::select")));
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[4]/td[2]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("Other Action", driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[4]/td[2]/descendant::select")));
		element = driver.findElements(By.xpath("//h3[contains(text(),'Customer Information')]")).get(0);
		scrollingFunction();
		
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::input[@maxlength='255']")).clear();
		
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::a/*")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver.getWindowHandles().size());
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@id='searchFrame']"))));
		driver.findElement(By.xpath("//input[@id='lksrch' and @placeholder='Search...']")).sendKeys("EDVARD EDOUARD");
		driver.findElement(By.xpath("//input[@id='lksrch' and @placeholder='Search...']/following-sibling::input")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@id='resultsFrame']"))));
		driver.findElements(By.xpath("//a[contains(text(),'EDVARD EDOUARD')]")).get(0).click();
		Utils.sleep(2);
		driver.switchTo().window(winHandleBefore);
		driver.switchTo().defaultContent();
		
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		//driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::input[@maxlength='255']")).sendKeys("EDVARD EDOUARD");
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[4]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[4]/descendant::input")).sendKeys("zabid@acumensolutions.com");
		
		element = driver.findElements(By.xpath("//h2[text()='Service Item Detail']")).get(0);
		scrollingFunction();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Form type, customer name etc edit is completed.");
	}
	public void checkErrorAfter1stSave() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//td[contains(@class,'todayDate')]")).click();
		Utils.sleep(2);
		element = driver.findElements(By.xpath("//h3[contains(text(),'Response Due Dates')]")).get(0);
		scrollingFunction();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][7]/table[@class='detailList']/tbody/tr[1]/td[1]"));
		highlightElement();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::div[@class='errorMsg']"));
		highlightElement();
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("ES GEN", driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::select")));
		Utils.sleep(1);
		
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[4]/td[2]/descendant::div[@class='errorMsg']"));
		highlightElement();
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[4]/td[2]"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("SCU", driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[4]/td[2]/descendant::select")));
		Utils.sleep(1);
		
		element = driver.findElements(By.xpath("//h2[text()='Service Item Detail']")).get(0);
		scrollingFunction();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		
		ele = (By.xpath("//div[@class='pbError' and contains(text(),'Action Other Reason')]"));
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//div[@class='pbError' and contains(text(),'Action Other Reason')]"));
		highlightElement();
		
		element = driver.findElements(By.xpath("//h3[contains(text(),'Customer Information')]")).get(0);
		scrollingFunction();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[3]/td[4]/descendant::div[@class='errorMsg' and contains(text(),'Additional')]"));
		highlightElement();
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[3]/td[4]"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@class='activeField']/descendant::textarea")).sendKeys("Adding a Secondary Mail");
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		
		element = driver.findElements(By.xpath("//h3[contains(text(),'Customer Information')]")).get(0);
		scrollingFunction();
		
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[6]/td[2]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@class='activeField']/descendant::textarea")).sendKeys("Adding Action Other Test");
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		
		element = driver.findElements(By.xpath("//h2[text()='Service Item Detail']")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Utils.sleep(8);
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Validation of dependent field and verification of error field is completed and Si is saved successfully.");
	}
	public void createNewEmailResponseForSCU() throws AWTException {
		driver.navigate().refresh();
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver.switchTo().defaultContent();
		//fetchCorrectIframe(By.xpath(".//span[text()='Response Recipients']"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//div[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//a[text()='EDVARD EDOUARD']/parent::*/parent::th/preceding-sibling::td/descendant::label/span[text()='EDVARD EDOUARD isSelected']"));
		highlightElement();
		Utils.sleep(1);
		driver.findElement(By.xpath("//a[text()='EDVARD EDOUARD']/parent::*/parent::th/preceding-sibling::td/descendant::label")).click();
		Utils.sleep(1);
		
		
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Recipient')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'To: Applicant')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element = driver.findElement(By.xpath(".//option[contains(text(),'CCD 300 Ready to File Has Questions')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'CCD 300 Ready to File Has Questions')]"))).doubleClick().build().perform();
		/*element = driver.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element);*/
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'301 Return of Incorrectly Addressed Applications')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		//element = driver.findElement(By.xpath(".//option[contains(text(),'CCD 430 Perm Resident and Naturalizat')]/preceding::option[1]"));
		//scrollingFunction();
		//Utils.sleep(2);
		/*element.click();
		element.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element);
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		element = driver.findElement(By.xpath(".//option[contains(text(),'CCD 430 Perm Resident and Naturalizat')]"));
		//scrollingFunction();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'431B Application for Replacement Naturalization/Citizenship Document (Form N-565')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		driver.findElement(By.xpath("//button[contains(text(),'Save Draft')]")).click();
		ele = By.xpath("//*[contains(text(),'Your draft response has been saved successfully.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		testReporter.log(LogStatus.PASS, "Response saved draft successfully.");
		element = driver.findElement(By.xpath(".//div[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[text()='Requester']/parent::td/preceding-sibling::td/descendant::label")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Please ensure that all selected recipients have an email address')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		testReporter.log(LogStatus.PASS, "Validation of no recipient is succesful");
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Go Back')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//div[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[text()='Requester']/parent::td/preceding-sibling::td/descendant::label")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//a[text()='EDVARD EDOUARD']/parent::*/parent::th/preceding-sibling::td/descendant::label")).click();
		Utils.sleep(1);
		
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Discard')]")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your draft response was discarded successfully.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		testReporter.log(LogStatus.PASS, "Validation of discard is successful.");
		
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Case Approved')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Referral on Approved Case')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		testReporter.log(LogStatus.PASS, "Response is successfully created.");
		/*ele = By.xpath("//div[contains(text(),'select one or more recipients before submitting')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//button[contains(text(),'Go Back')]")).click();
		element = driver.findElement(By.xpath("//*[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		//element = driver.findElement(By.xpath("//span[contains(text(),'Is selected?')]/parent::label"));
		element = driver.findElements(By.xpath("//span[contains(text(),'isSelected')]/parent::label")).get(0);
		element.click();
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();*/
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[text()='View Response']")).click();
		Utils.sleep(18);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateTheRes () {
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//h3[text()='Files']"));
		element = driver.findElement(By.xpath("//h3[text()='Approval History']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//a[text()='CCD CRU QC Review Queue']/parent::*/preceding-sibling::td[text()='Approved']"));
		highlightElement();
		
		element = driver.findElement(By.xpath("//h3[text()='Response Dates']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//span[contains(text(),'Response Submitted for Approval Date')]/parent::td/following-sibling::td[contains(text(),'AM') or contains(text(),'PM')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//td[contains(text(),'Sent to Quality Control Team Date')]/following-sibling::td[contains(text(),'AM') or contains(text(),'PM')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//td[contains(text(),'Approved by QC Date')]/following-sibling::td[contains(text(),'AM') or contains(text(),'PM')]"));
		highlightElement();
		try {
			element = driver.findElement(By.xpath("//span[contains(text(),'Response Sent Date')]/parent::td/following-sibling::td[contains(text(),'AM') or contains(text(),'PM')]"));
			highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Response Sent Date is not populated successfully.");
		}
		//testReporter.log(LogStatus.PASS, fileNm+" attachment file is present in view response page.");
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		//driver.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/descendant::a[text()='"+newSINo+"']")).click();
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "View Response page validation is successful.");
	}
	public void validateTheResFrmSI () {
		for (int iterator=0; iterator<4;iterator++) {
			try {
				driver.switchTo().defaultContent();
				driver.navigate().refresh();
				Utils.sleep(4);
				ele = By.xpath(".//span[@class='tabText' and text()='Details']");
				fluentWaitForElementVisibility();
				driver.navigate().refresh();
				Utils.sleep(2);
				ele = By.xpath(".//span[@class='tabText' and text()='Details']");
				fluentWaitForElementVisibility();
				Utils.sleep(2);
				fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
				driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
				Utils.sleep(1);
				element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
				scrollingFunction();
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
				highlightElement();
				element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
				scrollingFunction();
				element = driver.findElement(By.xpath("//th[text()='Sent']"));
				highlightElement();
				break;
			} catch (Exception e) {
				System.out.println("Iteration Count :"+iterator);
			}
			}
			element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
			highlightElement();
			element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']/following-sibling::td[2][contains(text(),'AM') or contains(text(),'PM') ]"));
			highlightElement();
			element = driver.findElements(By.xpath("//th[text()='Sent']")).get(0);
			highlightElement();
			element = driver.findElement(By.xpath("//h3[text()='Activity History']"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElements(By.xpath("//th[@scope='row']/parent::*/descendant::a[text()='EDVARD EDOUARD']")).get(0);
			highlightElement();
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			Utils.sleep(2);
	}
	public void closeSIValidation () {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("Closed", driver.findElement(By.xpath("//td[text()='Status']/following-sibling::td/descendant::select")));
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		ele = (By.xpath("//div[@class='pbError' and contains(text(),'comment')]"));
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//div[@class='pbError' and contains(text(),'comment')]"));
		highlightElement();
		
		element = driver.findElement(By.xpath("//h3[text()='Service Item Comments']"));
		scrollingFunction();
		driver.findElement(By.xpath("//h3[text()='Service Item Comments']/parent::*/following-sibling::td[1]/input")).click();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//textarea[@id='CommentBody']"));
		driver.findElement(By.xpath("//textarea[@id='CommentBody']")).sendKeys("Closing this service Item.");
		driver.findElements(By.xpath("//input[@type='submit' and @title='Save']")).get(1).click();
		driver.switchTo().defaultContent();
		driver.navigate().refresh();
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("Closed", driver.findElement(By.xpath("//td[text()='Status']/following-sibling::td/descendant::select")));
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Utils.sleep(8);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/div[text()='Closed']"));
		highlightElement();
		//driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Close of SI is successful.");
	}
	public void reOpenValidation () {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		//fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/*"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("Re-Opened", driver.findElement(By.xpath("//td[text()='Status']/following-sibling::td/descendant::select")));
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		ele = (By.xpath("//div[@class='pbError' and contains(text(),'comment')]"));
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//div[@class='pbError' and contains(text(),' Invalid Data')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//h3[text()='Re-Open']"));
		scrollingFunction();
		Utils.sleep(2);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][6]/table[@class='detailList']/tbody/tr[1]/td[2]"))).doubleClick().build().perform();
		selectDropdownListValue("Case Status", driver.findElement(By.xpath("//td[text()='Re-Open Reason']/following-sibling::td/descendant::select")));
		Utils.sleep(1);
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();
		element = driver.findElements(By.xpath("//h2[text()='Service Item Detail']")).get(0);
		scrollingFunction();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Utils.sleep(8);
		//driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Re-open is successful..");
	}
	public void decesionStatusChk () {
		//fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		element = driver.findElement(By.xpath("//h3[text()='Response Due Dates']"));
		scrollingFunction();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[2]/td[2]"))).doubleClick().build().perform();
		Utils.sleep(2);
		selectDropdownListValue("WH", driver.findElement(By.xpath("//div[@class='pbSubsection'][8]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::select")));
		Utils.sleep(1);
		
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][7]/table[@class='detailList']/tbody/tr[1]/td[4]"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//td[contains(@class,'todayDate')]")).click();
		
		actObj.moveToElement(driver.findElement(By.xpath("//div[@class='pbSubsection'][7]/table[@class='detailList']/tbody/tr[2]/td[4]"))).doubleClick().build().perform();
		Utils.sleep(2);
		driver.findElement(By.xpath("//textarea[@type='text' and @maxlength='255']")).sendKeys("On Leave");
		driver.findElements(By.xpath("//input[@value='OK']")).get(0).click();


		
		element = driver.findElements(By.xpath("//h2[text()='Service Item Detail']")).get(0);
		scrollingFunction();
		driver.findElement(By.xpath("//input[@title='Save']")).click();
		Utils.sleep(8);
		
		element = driver.findElement(By.xpath("//h3[text()='Response Due Dates']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][7]/table[@class='detailList']/tbody/tr[3]/td[4]/div[text()='Pending']"));
		highlightElement();
		driver.switchTo().defaultContent();
		testReporter.log(LogStatus.PASS, "Pending status is visible.");
	}
	public void logIntoGmailSCU (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver.get(url);
		try {
			Utils.sleep(2);
			driver.findElement(By.id("identifierId")).sendKeys(username+"@acumensolutions.com");
			//driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Next']/parent::*")).click();
		} catch (Exception e) {
			
		}
		WebDriverWait wait = new WebDriverWait (driver, 10);
		ele = By.id("username");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(By.id("username")).sendKeys(username+"@acumensolutions.com");
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		ele = By.id("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		Utils.sleep(1);
		driver.findElement(By.id("password")).sendKeys(passowrd);
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		try {
			//WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime2();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	//#####################################################CCD SCU Analyst 1###################################################################
	@Given("^SCU Email auto approval process Registered User is logged in with \"(.*)\"$")
	public void init_For_SCU(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\Tier4AutoApprovalSCU"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Tier 4 Auto Approval Validation for SCU User");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Email auto approval process Verifying the current logged in user profile and switches to classic view if it is in lightning exp$")
	public void verify_Logged_In_User_Profile_For_SCU() throws IOException {
		try {
			switchToClassicView();
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile and switches to classic view if it is in lightning exp view:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Email auto approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
	public void Fetch_Email_From_Email_Services_For_SCU(String rowNo) throws IOException {
		try {
			fetchEmailLink(rowNo);
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Email auto approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email_For_SCU(String url, String username) throws IOException {
		try {
			logIntoGmailSCU(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^SCU Email auto approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_SCU(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Email auto approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_SCU(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Select the dropdown value \"(.*)\"$")
	public void Select_The_Dropdown_SCU(String user) throws IOException {
		try {
			selectTheEmailSIAndAssign("CCD SCU Intake Queue");
			testReporter.log(LogStatus.PASS, "Select the dropdown value as CCD SCU Intake Queue");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select the dropdown value as CCD SCU Intake Queue");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Change the Owner and Save$")
	public void Function_001() throws IOException {
		try {
			changeOwnerAndSave ();
			testReporter.log(LogStatus.PASS, "Change the Owner "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Change the Owner "+subjectLine+" and SI number "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Open the service item from my service item area$")
	public void Function_0012() throws IOException {
		try {
			openTheSI();
			testReporter.log(LogStatus.PASS, "Open the service item from my service item area "+subjectLine+" and SI number "+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the service item from my service item area "+subjectLine+" and SI number "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Edit Requiored Field and validate the error messages$")
	public void Function_003() throws IOException {
		try {
			EditFewFieldsandValidate ();
			checkErrorAfter1stSave();
			testReporter.log(LogStatus.PASS, "Edit Requiored Field and validate the error messages");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Edit Requiored Field and validate the error messages");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Create New Response and validate from view response page$")
	public void Function_004() throws IOException {
		try {
			createNewEmailResponseForSCU();
			validateTheRes ();
			testReporter.log(LogStatus.PASS, "Create New Response and validate from view response page");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Create New Response and validate from view response page");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the response from Service Item Page$")
	public void Function_005() throws IOException {
		try {
			validateTheResFrmSI();
			testReporter.log(LogStatus.PASS, "Validate the response from the service item page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the response from the service item page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the service item while making the status as Closed$")
	public void Function_006() throws IOException {
		try {
			closeSIValidation();
			testReporter.log(LogStatus.PASS, "Close and validate the error msg while closing.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Close and validate the error msg while closing.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the service item while making the status as Re-Open$")
	public void Function_007() throws IOException {
		try {
			reOpenValidation();
			testReporter.log(LogStatus.PASS, "Re-Open and validate the error msg while Re-Opening.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Re-Open and validate the error msg while Re-Opening.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the service item after making referring party as WH and decesion status is changed to pending$")
	public void Function_008() throws IOException {
		try {
			decesionStatusChk();
			testReporter.log(LogStatus.PASS, "Validate the service item after making referring party as WH and decesion status is changed to pending");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the service item after making referring party as WH and decesion status is changed to pending");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^SCU Stop Report Generation for current scenario Email auto approval process$")
	public void getResult_For_SCU() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^SCU Close the browser Email auto approval process$")
	public void flushReporter_For_SCU() {
		driver.close();
		driver.quit();
	}
	/*
	 * Below main function is for testing purpose only....
	 */
	public static void main(String[] args) throws Exception {
		Email_Auto_Approval_QC_5_Percent_E2E gt = new Email_Auto_Approval_QC_5_Percent_E2E();
		gt.launch();
		gt.fetchEmailLink("CC");
		gt.logIntoGmailSCU("https://accounts.google.com/speedbump/samlconfirmaccount?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sarp=1&scc=0&osid=1&TL=AM3QAYZiNvgxQINbArO9gY65HIrpXjbF9XLN6aH7J4RrE9_Cqa2xMZZ1zSf8Q4Uv",
				"zabid","Acumentest3@");
		gt.launch();
		gt.searchHDISOVSCitems("CCD SCU Analyst 1");
		gt.logInAsInternalUser("CCD SCU Analyst 1");
		gt.selectTheEmailSIAndAssign("CCD SCU Intake Queue");
		gt.changeOwnerAndSave ();
		//newSINo = "10856960";
		gt.openTheSI ();
		gt.EditFewFieldsandValidate ();
		gt.checkErrorAfter1stSave();
		gt.createNewEmailResponseForSCU();
		gt.validateTheRes ();
		gt.validateTheResFrmSI ();
		gt.closeSIValidation ();
		gt.reOpenValidation();
		gt.decesionStatusChk();
//		gt.openRandomSIFromOpenItems("IPO CSR");
//		gt.templateResponseSearchValidation();
		//gt.ipoSuperUserEmailToQ("IPO Email To Case Q");
		//gt.currentUserLogOut();
		//gt.searchHDISOVSCitems("HD ISO VSC");
		//gt.logInAsInternalUser("HD ISO VSC");
		//gt.duplicateSIandOpenSI();
		//Utils.sleep(8);
		//gt.editSIandSave();
		//gt.setReceiptNumberAndContactName("WAC1690258857", "EDVARD EDOUARD");
		//gt.createNewIpoResponseFor10911();
		//gt.validateCCBCCEmail();
		//emailLink = "cishdqa@2k7m4p7no7eke6gewwvfqe38ioy6oah93ilae2z792qb0dt7fy.r-1owyeam.cs32.apex.sandbox.salesforce.com";
		//gt.logIntoGmailForReplyValidation("https://acumensolutions-com.clearlogin.com/login","zabid","Acumen123");
		//gt.verifyProfile();
		//gt.relatedServiceItems();
		/*Then Email auto approval process Replying to the triggered email "https://acumensolutions-com.clearlogin.com/login" with user id "zabid"
		  Then Email auto approval process to verify child service item created and parent service item present in the child service item*/
		/*Then Email auto approval process Replying to the triggered email "https://acumensolutions-com.clearlogin.com/login" with user id "zabid"
   			Then Email auto approval process to verify child service item created and parent service item present in the child service item*/
	}
}
