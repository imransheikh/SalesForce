package com.salesforcetest.mapper.pcqs_integration;

import java.awt.AWTException;
import java.util.Calendar;
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
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PCQS_Integration_Overall_Scenario {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, receiptNo, role, formType, receivedDate;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent; //= ExtentReporter.getInstance().getExtentReports();

	static ExtentTest testReporter;
	@Given("^For PCQS scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_PCQS(String user) {
        extent = new ExtentReports(workingDir+"\\test-report\\PCQSscenario"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("PCQS scenario validation for user");
		try {
			launch();
			switchToClassicView();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		//new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	@When("^For PCQS scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_PCQS(String user) {
		//System.out.println("User: "+user);
		//main.searchUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User" +user);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User" + user);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_PCQS(String user) {
		//main.logInAsInternalUser(user);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For PCQS scenario Search for customer \"(.*)\"$")
	public void search_customer_For_PCQS(String customer) throws AWTException {
		//System.out.println("Customer: "+customer);
		//main.customerSearch(customer);
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Add the customer data and wait for Customer tab opens successfully with name \"(.*)\" dob \"(.*)\" and source \"(.*)\"$")
	public void Add_customer_For_PCQS(String customerNm, String dob, String source) throws AWTException {
		//main.customerDataRefresh();
		try {
			customerAdd(customerNm, dob, source);
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Refresh the customer data and wait for Refresh successful message with name \"(.*)\" dob \"(.*)\" and source \"(.*)\"$")
	public void update_customer_For_PCQS(String customerNm, String dob, String source) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			customerRefresh(customerNm, dob, source);
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario To validate post refresh opened tab for \"(.*)\"$")
	public void validate_post_refresh_complition_page(String name) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateOpenTabAfterRefresh(name);
			testReporter.log(LogStatus.PASS, "Validating personal details page and highlight required elements");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating personal details page and highlight required elements");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario of UCC DC CRU To validate post refresh opened tab for \"(.*)\"$")
	public void validate_post_refresh_complition_page_ForUCC(String name) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateOpenTabAfterRefresh_ForUCC(name);
			testReporter.log(LogStatus.PASS, "Validating personal details page and highlight required elements");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating personal details page and highlight required elements");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario of HD ISO VSC To validate post refresh opened tab for \"(.*)\"$")
	public void validate_post_refresh_complition_page_For_HD(String name) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateOpenTabAfterRefresh_ForHDISOVSC(name);
			testReporter.log(LogStatus.PASS, "Validating personal details page and highlight required elements for HD ISO VSC");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating personal details page and highlight required elements for HD ISO VSC");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario of IPO CSR To validate post refresh opened tab for \"(.*)\"$")
	public void validate_post_refresh_complition_page_For_Ipo(String name) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			validateOpenTabAfterRefresh_ForIpo(name);
			testReporter.log(LogStatus.PASS, "Validating personal details page and highlight required elements for ipo CSR");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validating personal details page and highlight required elements for ipo CSR");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Open application receipt page with given input \"(.*)\"$")
	public void open_application_receipt(String applicationRecord2) throws AWTException {
		//main.customerDataRefresh();
		try {
			//RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction();
			clickOnRecordItemFromOwnerPageAndGrabDetailsOfReceipt (applicationRecord2);
			testReporter.log(LogStatus.PASS, "Opening application receipt page and grab all details and highlight all required elements");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening application receipt page and grab all details and highlight all required elements");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Validate application receipt page$")
	public void validate_application_receipt() throws AWTException {
		try {
			validateItemsPresentInApplicationReceiptPage();
			validateItemsPresentInApplicationReceiptPage_Section2();
			validateItemsPresentInApplicationReceiptPage_Section3();
			validateItemsPresentInApplicationReceiptPage_Section4();
			testReporter.log(LogStatus.PASS, "Validate application receipt page and grab all details and highlight all required elements");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate application receipt page and grab all details and highlight all required elements");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Validate customer search with firstname \"(.*)\" lastname \"(.*)\" and dob \"(.*)\"$")
	public void search_with_few_criteria_and_validate_search_results(String startNm, String lastNm, String dobNm) throws AWTException {
		try {
			customerSearchWithFirstNmLastNmDOB(startNm, lastNm, dobNm);
			clickOnCustomerNmFrmSearchPage();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Validate search criteria with first name, last name and dob and validate search result page");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate search criteria with first name, last name and dob and validate search result page");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Delete customer which was added$")
	public void delete_Added_Customer() throws AWTException {
		try {
			deleteAddCustomer();
			testReporter.log(LogStatus.PASS, "Delete the added user with admin.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Delete the added user with admin.");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Search with ELIS Account number \"(.*)\"$")
	public void Customer_Search_With_ELIS_Acc(String elisAcc) throws AWTException {
		try {
			customerSearchWithELISAccNo(elisAcc);
			testReporter.log(LogStatus.PASS, "Search with ELIS Account "+elisAcc);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with ELIS Account "+elisAcc);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Validate the customer personal detail page of Elis Account \"(.*)\"$")
	public void validate_Customer_Profile_Page(String receiptNumber) throws AWTException {
		try {
			validateOpenTabAfterRefreshForELIS(receiptNumber);
			testReporter.log(LogStatus.PASS, "Validate personal detail page of "+receiptNumber);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate personal detail page of "+receiptNumber);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For PCQS scenario Validate the application area of Elis Account \"(.*)\"$")
	public void validate_Application_Receipt_Area(String receiptNumber) throws AWTException {
		try {
			verify_application_area_of_receipt_no_for_Elis(receiptNumber);
			testReporter.log(LogStatus.PASS, "Verifying petitioner role with one receipt number and receipt number related validation "+receiptNumber);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying petitioner role with one receipt number and receipt number related validation "+receiptNumber);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For PCQS scenario$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		driver.close();//Remove later
		driver.quit();//Remove later
	}
	@Then("^Close the browser For PCQS scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
//*******************************************Raw Code for PCQS*********************************************************
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//main = new HD_ISO_VSC_Service_Request_Rejection_E2E();
		
		//testReporter = extent.startTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		//main.setTestReporter(testReporter);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
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
	public void searchHDISOVSCitems(String user) throws AWTException { // String user
		//String user = "HD ISO VSC";
		try {
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		} catch (Exception e) {
			
		}
		//************
		driver.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		driver.findElement(By.id("phSearchInput")).sendKeys(user);
		Utils.sleep(2);
		try {
		driver.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::ul[@class='autoCompleteGroup']/li[1]/a")).click();
		Utils.sleep(4);
		} catch (Exception e) {
			logInFromSearch (user);
			testReporter.log(LogStatus.PASS, "Not able to find "+user+" customer from auto complete search dropdown so went to search people panel and logging in.");
		}
		/*Robot robot = new Robot();
		Utils.sleep(4);
		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);*/
	}
	public void logInAsInternalUser(String user) {//String user
		//String user = "HD ISO VSC";
		WebDriverWait wait = new WebDriverWait (driver, 2);
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
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]"));
		}
		driver.findElement(By.xpath("//*[text()='Service Cloud User']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]")).click();
		driver.switchTo().defaultContent();
		ele = By.xpath("//a[text()='"+user+"']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[contains(text(),'Back to')]")).click();
		Utils.sleep(2);
	}
	public void customerSearch(String customer) throws AWTException {//String customer
		//String customer = "A214018103";
		driver.navigate().refresh();
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		//************
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		driver.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		ele = By.xpath("//*[text()='Customer Search']");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[1]/td[1]/input")).sendKeys(customer);
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='asyncSearchButton']")).click();
		Utils.sleep(4);
		ele = By.xpath(".//*[@id='asynchronousResults']/descendant::*[contains(text(),'Customers Matching Primary/Secondary')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
	}
	public void customerRefresh(String customerNm, String dob2, String source) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 300);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		try {
			element = driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']"));
			highlightElement();
			element = driver.findElement(By.xpath("//*[@id='asynchAccountResultTable']/descendant::a[text()='"+customerNm+"']"));
			highlightElement();
			element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/descendant::td[text()='"+dob2+"']"));
			highlightElement();
			element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/descendant::td[text()='"+source+"']"));
			highlightElement();
			driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']")).click();
			Utils.sleep(2);
		} catch (Exception e) {
			driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Add Customer']")).click();
			Utils.sleep(2);
		}
		ele = By.xpath(".//img[@alt='CONFIRM']");
		//System.out.println("I am here3");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@class='x-btn-split']")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		try {
		element = driver.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void customerAdd(String customerName, String dob1, String Source) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 300);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		try {
			driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']")).click();
			Utils.sleep(2);
		} catch (Exception e) {
			element = driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Add Customer']"));
			highlightElement();
			element = driver.findElement(By.xpath("//*[@id='asynchAccountResultTable']/descendant::*[text()='"+customerName+"']"));
			highlightElement();
			element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/descendant::td[text()='"+dob1+"']"));
			highlightElement();
			element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/descendant::td[text()='"+Source+"']"));
			highlightElement();
			driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Add Customer']")).click();
			Utils.sleep(2);
		}
		driver.switchTo().defaultContent();
		ele = By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']");
		//System.out.println("I am here3");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			
		}
		Utils.sleep(4);
	}
	public void validateOpenTabAfterRefresh_ForIpo (String customerName) {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Items option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Open Activities option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Activity History option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Person Account History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Person Account History option found in the Personal Detail page.");
		try {
			element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Response Recipients')]"));
			highlightElement();
			testReporter.log(LogStatus.FAIL, "Response Recipients option found in the Personal Detail page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.PASS, "Response Recipients option should not be found in the Personal Detail page.");
		}
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Customer Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			testReporter.log(LogStatus.FAIL, "Successfully updated the customer profile alert Refresh process is unsuccessfull.");
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateOpenTabAfterRefresh_ForHDISOVSC (String customerName) {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Items option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Aliases')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Aliases option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Open Activities option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Activity History option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Person Account History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Person Account History option found in the Personal Detail page.");
		try {
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Response Recipients')]"));
		highlightElement();
		testReporter.log(LogStatus.FAIL, "Response Recipients option found in the Personal Detail page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.PASS, "Response Recipients option should not be found in the Personal Detail page.");
		}
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Customer Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			testReporter.log(LogStatus.FAIL, "Successfully updated the customer profile alert Refresh process is unsuccessfull.");
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateOpenTabAfterRefresh_ForUCC (String customerName) {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Items option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Alerts')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Alerts option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Open Activities option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Activity History option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Item-Contact Links')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Item-Contact Links option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Person Account History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Person Account History option found in the Personal Detail page.");
		try {
			element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Response Recipients')]"));
			highlightElement();
			testReporter.log(LogStatus.FAIL, "Response Recipients option found in the Personal Detail page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.PASS, "Response Recipients option should not be found in the Personal Detail page.");
		}
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Customer Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			testReporter.log(LogStatus.FAIL, "Successfully updated the customer profile alert Refresh process is unsuccessfull.");
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateOpenTabAfterRefresh (String customerName) {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Items option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Open Activities option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Activity History option found in the Personal Detail page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Alerts')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Alerts option found in the Personal Detail page.");
		try {
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Response Recipients')]"));
		highlightElement();
		testReporter.log(LogStatus.FAIL, "Response Recipients option found in the Personal Detail page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.PASS, "Response Recipients option should not be found in the Personal Detail page.");
		}
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Customer Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			testReporter.log(LogStatus.FAIL, "Successfully updated the customer profile alert Refresh process is unsuccessfull.");
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void clickOnRecordItemFromOwnerPageAndGrabDetailsOfReceipt (String applicationRecord2) {
		fetchCorrectIframe(By.xpath("//h3[contains(text(),'Applications')]"));
		element = driver.findElement(By.xpath("//h3[contains(text(),'Applications')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ApplicationPartiesApplicationDetailTable']")));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		highlightElement();
		receiptNo = applicationRecord2;
		System.out.println("Receipt Number : "+receiptNo);
		testReporter.log(LogStatus.PASS, receiptNo+" Receipt Number fetched from personal detail page.");
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[1]/div"));
		highlightElement();
		role = element.getText();
		System.out.println("Role : "+role);
		testReporter.log(LogStatus.PASS, role+" role is fetched from personal detail page.");
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[2]/div"));
		highlightElement();
		formType = element.getText();
		System.out.println("Form Type : "+formType);
		testReporter.log(LogStatus.PASS, formType+" Form Type is fetched from personal detail page.");
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[3]/div"));
		highlightElement();
		receivedDate = element.getText();
		System.out.println("Received Date : "+receivedDate);
		testReporter.log(LogStatus.PASS, receivedDate+" Received Date is fetched from personal detail page.");
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		element.click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateItemsPresentInApplicationReceiptPage () {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		//String applicationRecord2 = "WAC1690258857";
		fetchCorrectIframe(By.xpath(".//*[@id='topButtonRow']/input[@value='Create Service Item']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Service Items option found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Open Activities option found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Activity History option found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Alerts')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Alerts option found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Application History')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Application History option found in the Application Receipt page.");
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Application Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			testReporter.log(LogStatus.FAIL, "Successfully updated the application alert Refresh process is unsuccessfull.");
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//td[text()='Receipt Number']/following-sibling::td[text()='"+receiptNo+"']"));
		highlightElement();
		testReporter.log(LogStatus.PASS, receiptNo+" Receipt Number found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//*[contains(text(),'Form Type')]/parent::td/following-sibling::td[text()='"+formType+"']"));
		highlightElement();
		testReporter.log(LogStatus.PASS, formType+" Form Type found in the Application Receipt page.");
		//receivedDate = receivedDate.replaceAll("-", "/");
		element = driver.findElement(By.xpath("//*[contains(text(),'Received Date')]/parent::td/following-sibling::td[1]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, element.getText()+" Received Date found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//*[contains(text(),'FCO')]/parent::td/following-sibling::td"));
		highlightElement();
		System.out.println("FCO value :"+element.getText());
		testReporter.log(LogStatus.PASS, element.getText()+" FCO found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//*[contains(text(),'Application Time Elapsed (Days)')]/parent::td/following-sibling::td"));
		highlightElement();
		System.out.println("Application Time Elapsed (Days) :"+element.getText());
		testReporter.log(LogStatus.PASS, element.getText()+" Application Time Elapsed (Days) found in the Application Receipt page.");
		element = driver.findElement(By.xpath("//*[contains(text(),'Date of Adverse Decision')]/parent::td/following-sibling::td"));
		highlightElement();
		System.out.println("Date of Adverse Decision :"+element.getText());
		testReporter.log(LogStatus.PASS, element.getText()+" Date of Adverse Decision found in the Application Receipt page.");
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateItemsPresentInApplicationReceiptPage_Section2 () { 
		fetchCorrectIframe(By.xpath("//*[contains(text(),'Application Parties')]"));
		element = driver.findElement(By.xpath("//*[contains(text(),'Application Parties')]"));
		scrollingFunction();
		driver.switchTo().frame(driver.findElement(By.xpath("//*[contains(text(),'Application Parties')]/parent::div/following-sibling::div[1]/descendant::iframe[1]")));
		element = driver.findElement(By.xpath(".//*[@id='appParties']/table/tbody/tr/td[2]/div"));
		highlightElement();
		element = driver.findElement(By.xpath(".//*[@id='appParties']/table/tbody/tr/td[3]/div"));
		highlightElement();
		element = driver.findElement(By.xpath(".//*[@id='appParties']/table/tbody/tr/td[4]/div"));
		highlightElement();
		element = driver.findElement(By.xpath(".//*[@id='appParties']/table/tbody/tr/td[5]/div"));
		highlightElement();
		element = driver.findElement(By.xpath(".//*[@id='appParties']/table/tbody/tr/td[6]/div"));
		highlightElement();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateItemsPresentInApplicationReceiptPage_Section3 () { 
		fetchCorrectIframe(By.xpath("//*[contains(text(),'Application Details')]"));
		element = driver.findElement(By.xpath("//*[contains(text(),'Application Details')]"));
		scrollingFunction();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ApplicationPCQSDetails']")));
		element = driver.findElement(By.xpath("//span[text()='History']/preceding-sibling::table"));
		highlightElement();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateItemsPresentInApplicationReceiptPage_Section4 () { 
		fetchCorrectIframe(By.xpath("//*[contains(text(),'Application Details')]"));
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ActionHistoryPage']")));
		element = driver.findElement(By.xpath("//a[contains(text(),'Alien Number Histories')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//span[text()='Alien Number Histories']/parent::div/descendant::button[text()='Next']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[text()='Alien Number Histories']/parent::div/descendant::span[contains(text(),'page')]/parent::*/following-sibling::input"));
		highlightElement();
		element = driver.findElement(By.xpath("//span[text()='Alien Number Histories']/parent::div/descendant::button[text()='Previous']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//a[contains(text(),'Receipt Number Histories')]"));
		highlightElement();
		element.click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[text()='Receipt Number Histories']/parent::div/descendant::button[text()='Next']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[text()='Receipt Number Histories']/parent::div/descendant::span[contains(text(),'page')]/parent::*/following-sibling::input"));
		highlightElement();
		element = driver.findElement(By.xpath("//span[text()='Receipt Number Histories']/parent::div/descendant::button[text()='Previous']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		//clear all tab code
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
				//************
	}
	public void customerSearchWithFirstNmLastNmDOB(String firstNm, String lastNm, String dob) throws AWTException {//String customer
		//String customer = "A214018103";
		//clear all tab code
		driver.navigate().refresh();
		Utils.sleep(4);
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
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
		//driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		driver.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		ele = By.xpath("//*[text()='Customer Search']");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[2]/td[1]/input")).sendKeys(firstNm);
		Utils.sleep(1);
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[2]/td[2]/input")).sendKeys(lastNm);
		Utils.sleep(1);
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[2]/td[3]/input")).sendKeys(dob);
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='asyncSearchButton']")).click();
		Utils.sleep(4);
		ele = By.xpath(".//*[@id='asynchronousResults']/descendant::*[contains(text(),'Customers Matching Primary/Secondary')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
	}
	public void clickOnCustomerNmFrmSearchPage() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 5);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/tbody/tr/th[1]/a"));
		highlightElement();
		element.click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//*[text()='Person Account Detail']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void currentUserLogOut() {
		element = driver.findElement(By.xpath(".//*[@id='userNavLabel']"));
		scrollingFunction();
		driver.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Logout']")));
		Utils.sleep(4);
	}
	public void deleteAddCustomer() {
		WebDriverWait wait = new WebDriverWait (driver, 5);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		element = driver.findElement(By.xpath(".//*[@id='asynchAccountResultTable']/tbody/tr/th[1]/a"));
		highlightElement();
		element.click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//*[text()='Person Account Detail']"));
		highlightElement();
		element = driver.findElement(By.xpath("//*[text()='Person Account Detail']/parent::td/following-sibling::td/input[@value='Delete']"));
		highlightElement();
		element.click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		ele = By.xpath("//span[contains(text(),'Delete Confirmation')]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//table[@class='x-toolbar-ct']/descendant::button[text()='Yes']")).click();
		Utils.sleep(4);
	}
	public void customerSearchWithELISAccNo(String elisAccNo) throws AWTException {//String customer
		//String customer = "A214018103";
		//clear all tab code
		driver.navigate().refresh();
		Utils.sleep(4);
				driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
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
		//driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")));
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		driver.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		ele = By.xpath("//*[text()='Customer Search']");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[1]/td[2]/input")).sendKeys(elisAccNo);
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='asyncSearchButton']")).click();
		Utils.sleep(4);
		ele = By.xpath(".//*[@id='asynchronousResults']/descendant::*[contains(text(),'Customers Matching Primary/Secondary')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
	}
	public void validateOpenTabAfterRefreshForELIS (String customerName) {
		WebDriverWait wait = new WebDriverWait (driver, 80);
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='"+customerName+"']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Service Items')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Open Activities')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Activity History')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Alerts')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//a[@class='linklet']/*[contains(text(),'Response Recipients')]"));
		highlightElement();
		/*driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@value='Refresh Customer Data from PCQS']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		if (driver.switchTo().alert().getText().contains("Successfully updated")) {
			//testReporter.log(LogStatus.PASS, driver.switchTo().alert().getText()+" alert box message verified and Refresh process is successful.");
		} else {
			//testReporter.log(LogStatus.FAIL, "Successfully updated the customer profile alert Refresh process is unsuccessfull."+driver.switchTo().alert().getText());
		}
		driver.switchTo().alert().accept();
		Utils.sleep(4);*/
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//*[text()='Person Account Detail']"));
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@title='AccountBanner']")));
		element = driver.findElement(By.xpath("//span[contains(text(),'USCIS Account ID')]"));
		highlightElement();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void verify_application_area_of_receipt_no_for_Elis (String applicationRecord2) {
		fetchCorrectIframe(By.xpath("//h3[contains(text(),'Applications')]"));
		element = driver.findElement(By.xpath("//h3[contains(text(),'Applications')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ApplicationPartiesApplicationDetailTable']")));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		//scrollingFunction();
		highlightElement();
		receiptNo = applicationRecord2;
		System.out.println("Receipt Number : "+receiptNo);
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[1]/div"));
		highlightElement();
		role = element.getText();
		System.out.println("Role : "+role);
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[2]/div"));
		highlightElement();
		formType = element.getText();
		System.out.println("Form Type : "+formType);
		element = driver.findElement(By.xpath("//div[@title='"+applicationRecord2+"']/parent::td/following-sibling::td[3]/div"));
		highlightElement();
		receivedDate = element.getText();
		System.out.println("Received Date : "+receivedDate);
		element = driver.findElement(By.xpath("//div[text()='Petitioner']"));
		highlightElement();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
						driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
	}
	public void logInFromSearch (String userName) {
		 //userName = "OoC Internal User";
		 driver.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::*[contains(text(),'Search for')]")).click();
		 fetchCorrectIframe(By.xpath(".//*[@id='User']/descendant::span[contains(text(),'People')]"));
		 element = driver.findElement(By.xpath(".//*[@id='User']/descendant::span[contains(text(),'People')]"));
		 scrollingFunction();
		 Utils.sleep(1);
		 driver.findElement(By.xpath(".//*[@id='User_body']/table/tbody/tr[2]/descendant::a[contains(text(),'"+userName+"')]")).click();
		 driver.switchTo().defaultContent();
		 Utils.sleep(2);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(1);
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
	}
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
	      //System.out.printf("Now random "+randomDateTime);
	      randomDateTime = "" + randomDateTime;
	      return randomDateTime;
	}
	public void fetchCorrectIframe(By dropDownWebElement1) {
		int iFrameCount = driver.findElements(By.xpath("//div[@id='navigatortab']/descendant::iframe")).size();
		System.out.println("Frame count :"+iFrameCount);
		for(int i = 1; i<=iFrameCount+1; i++) {
			try {
				driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe["+i+"]")));
				//Utils.sleep(1);
				driver.findElement(dropDownWebElement1).getText();
				break;
			} catch (Exception e) {
				driver.switchTo().defaultContent();
			}
		}
	}
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
		 
	    .withTimeout(40, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver);
		//long startTime = System.currentTimeMillis();
		try
		{
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e)
		{
			//System.out.println("In select fuctionality fluent wait timeout"+ele);
		}
		//long endTime = System.currentTimeMillis();
		//System.out.println("Time elapsed in fluent wait clickable "+(endTime-startTime)/1000);
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
//*******************************************Raw Code for PCQS*********************************************************
	public static void main(String[] args) throws AWTException {
	// TODO Auto-generated method stub
		PCQS_Integration_Overall_Scenario pcqsRef = new PCQS_Integration_Overall_Scenario();
		pcqsRef.launch();
		pcqsRef.switchToClassicView();
		pcqsRef.searchHDISOVSCitems("UCC-DC CRU Analyst 1");
		pcqsRef.logInAsInternalUser("UCC-DC CRU Analyst 1");
		/*pcqsRef.customerSearch("A214018103");
		pcqsRef.customerAdd("PAULA TURANO","1981-11-15","CIS");
		pcqsRef.validateOpenTabAfterRefresh("PAULA TURANO");
		pcqsRef.clickOnRecordItemFromOwnerPageAndGrabDetailsOfReceipt("MSC1691282989");
		pcqsRef.validateItemsPresentInApplicationReceiptPage();
		pcqsRef.validateItemsPresentInApplicationReceiptPage_Section2();
		pcqsRef.validateItemsPresentInApplicationReceiptPage_Section3();
		pcqsRef.validateItemsPresentInApplicationReceiptPage_Section4();
		pcqsRef.customerSearch("A214018103");
		pcqsRef.customerRefresh("PAULA TURANO","1981-11-15","CIS");
		pcqsRef.customerSearchWithFirstNmLastNmDOB("PAULA", "TURANO", "11151981");
		pcqsRef.clickOnCustomerNmFrmSearchPage();
		pcqsRef.currentUserLogOut();
		pcqsRef.customerSearch("A214018103");
		pcqsRef.deleteAddCustomer();*/
		/*pcqsRef.customerSearchWithELISAccNo("077190469400");
		pcqsRef.customerAdd("LEYNA BATTA","1981-05-12","ELIS2");
		pcqsRef.validateOpenTabAfterRefreshForELIS("LEYNA BATTA");
		pcqsRef.verify_application_area_of_receipt_no_for_Elis("IOE0945893030");
		pcqsRef.customerSearchWithFirstNmLastNmDOB("LEYNA", "BATTA", "05121981");
		pcqsRef.clickOnCustomerNmFrmSearchPage();
		pcqsRef.currentUserLogOut();
		pcqsRef.customerSearchWithELISAccNo("077190469400");
		pcqsRef.deleteAddCustomer();*/
	}
}
