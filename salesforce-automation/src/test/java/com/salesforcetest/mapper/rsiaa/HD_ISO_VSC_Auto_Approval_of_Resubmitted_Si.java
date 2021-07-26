package com.salesforcetest.mapper.rsiaa;

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
import com.salesforcetest.mapper.eaaqc.Email_Auto_Approval_QC_5_Percent_E2E;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.pages.salesforce.uatg.RefreshExistingUserRecordAction;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_Auto_Approval_of_Resubmitted_Si {
	static WebDriver driver;
	private WebElement element;
	public static String newSINo, subjectLine, responseNumber;
	private By ele;
	String screenShotPath;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent;

	static ExtentTest testReporter;
	@Given("^For rejection and auto approval of response scenario Registered User is logged in with \"(.*)\"$")
	public void init(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\AutoApprovalOfRejectedScenarioWithHD"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Auto Approval of Rejected Service Item after Resubmitted (Additional Review Required is Unchecked)");
		try {
			launch();
			switchToClassicView();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For rejection and auto approval of response scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage(String val) throws IOException {
		try {
			searchHDISOVSCitems("One HD Supervisor");
			logInAsInternalUser("One HD Supervisor");
			setPercentage(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For rejection and auto approval of response scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() throws IOException {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For rejection and auto approval of response scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For rejection and auto approval of response scenario Search for customer \"(.*)\"$")
	public void search_customer(String customer) throws AWTException, IOException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer() throws AWTException, IOException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details(String appNumber) throws IOException {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item(String appNumber) throws IOException {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For rejection and auto approval of response scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item(DataTable dt) throws IOException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
		try {
			setOrgName(fOrgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+fOrgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+fOrgName);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			subject = subject+randomDateTime1();
			setParamSubjectAndDesAndFormType(subject, description, formType);
			subjectLine = subject;
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamCategoryAndKind(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOrigin(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectInitialQueue(queue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+queue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+queue);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Verify New Service Item number and Details$")
	public void Verify_New_SI() throws IOException {
		String newlyCreatedSI = null;
		try {
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Create a new response for new Service Item$")
	public void Create_New_Response() throws IOException {
		try {
			createNewResponse();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Verify newly created service response status$")
	public void Verify_New_Service_Response() throws IOException {
		try {
			verifyNewResponseStatus();
			testReporter.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logout from current profile$")
	public void log_Out() throws IOException {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For rejection and auto approval of response scenario Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with One HD Supervisor");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with One HD Supervisor");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Select required service item with Supervisor user to reject$")
	public void Select_Required_Service_item() throws IOException {
		try {
			selectRequiredDropdownlist();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Reject the selected service request without marking additional review checkbox$")
	public void Reject_Selected_Service_Rqst() throws IOException {
		try {
			rejectServiceRqstWithoutAddReview();
			testReporter.log(LogStatus.PASS, "Reject the service request with proper comments");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reject the service request with proper comments");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Verify the rejected service request status \"(.*)\"$")
	public void Verification_of_Rejected_Service(String status) throws IOException {
		try {
			validateRejectedResponse(status);
			testReporter.log(LogStatus.PASS, "Verify rejection status verification");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify rejection status verification");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logout from supervisor profile$")
	public void log_Out2() throws IOException {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For rejection and auto approval of response scenario Search for required Internal User which created the response \"(.*)\"$")
	public void search_For_Internal_User2(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logging in as Internal user and verifying User which created the response \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile2(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Edit pre-created response and resubmit$")
	public void Edit_Resubmit_the_response() throws IOException {
		try {
			searchWithStoredItem();
			editRejectedSI();
			testReporter.log(LogStatus.PASS, "Log in as HD ISC VSO user and edit the service item and resubmit.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as HD ISC VSO user and edit the service item and resubmit.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Validate resubmitted service response status changed to \"(.*)\"$")
	public void Validate_Resubmitted_SI_Status(String status) throws IOException {
		try {
			validateResubmitStatus(status);
			testReporter.log(LogStatus.PASS, "Validate resubmitted service response status changed to Approved");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate resubmitted service response status changed to Approved");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and auto approval of response scenario Logout from response editor's profile$")
	public void log_Out3() throws IOException {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For rejection and auto approval of response scenario$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For rejection and auto approval of response scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(2);
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
	      randomDateTime = "" + randomDateTime;
	      return randomDateTime;
	}
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
		 
	    .withTimeout(80, TimeUnit.SECONDS)
	 
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
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
	public void logInAsInternalUser(String user) {
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
	public void customerSearch(String customer) throws AWTException {
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		ele = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
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
	public void customerRefresh() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 300);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']")).click();
		Utils.sleep(2);
		ele = By.xpath(".//img[@alt='CONFIRM']");
		System.out.println("I am here3");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ele));
		} catch (Exception e) {
			
		}
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@class='x-btn-split']")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void clickOnRecordItemFromOwnerPage (String applicationRecord2){
		driver.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='EDVARD EDOUARD']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//h3[contains(text(),'Applications')]"));
		element = driver.findElement(By.xpath("//h3[contains(text(),'Applications')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='ApplicationPartiesApplicationDetailTable']")));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void searchForApplicationRecordWithAppNo(String applicationRecord) throws AWTException {
		driver.findElement(By.id("phSearchInput")).sendKeys(applicationRecord);
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/div[1]/span")).click();
		Utils.sleep(4);
	}
	public void openApplicationRecordFromGlobalSearchResultPanel(String applicationRecord2) {
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 15);
		fetchCorrectIframe(By.xpath("//*[text()='Show More']"));
		driver.findElement(By.xpath(".//*[@id='Application__c']/div[2]/div/div[1]/table/tbody")).click();
		driver.findElement(By.xpath("//*[text()='Show More']")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@src,'UnifiedSearchResults')]"))));
		element = driver.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		highlightElement();
		element.click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element = driver.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
	}
	public void createNewServiceItem() {
		driver.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void createNewServiceItemParam(String applicationRecord2) {
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element = driver.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
		driver.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void setOrgName(String orgName) {
		WebDriverWait wait = new WebDriverWait (driver, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input")).sendKeys(orgName);
	}
	public void setEmail(String email) {
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
	}
	public void selectSenderType(String senderType) {
		selectDropdownListValue(senderType, driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[2]/span/select")));
		Utils.sleep(2);
	}
	public void setRandomSubjectAndDesAndFormType() {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		Random randNumber = new Random();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys("Test Subject_"+randNumber.nextInt(10000));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys("Test Description_"+randNumber.nextInt(10000));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		Utils.sleep(2);
	}
	public void setParamSubjectAndDesAndFormType(String subject, String description, String formType) {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		Utils.sleep(2);
	}
	public void setRandomCategoryAndKind() {
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		Random randNumber = new Random();
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectRandomDropdownListValue(driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys("Auto Generated Comments_"+randNumber.nextInt(10000));
		Utils.sleep(2);
	}
	public void setParamCategoryAndKind(String category, String kind, String comments) {
		element = driver.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		selectDropdownListValue(category,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectDropdownListValue(kind,driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
		Utils.sleep(2);
	}
	public void selectSIOrigin(String serviceItem) { //String serviceItem
		try {
		selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[3]/td[2]/div/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/select")));	
		}
	}
	public void selectInitialQueue(String initialQueue) { //String initialQueue
		try {
		selectDropdownListValue(initialQueue, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[2]/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(initialQueue, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/span/select")));	
		}
	}
	public void setReceivedDate(String receivedDate) throws AWTException { //String receivedDate
		driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/input")).sendKeys(receivedDate);
		Robot robot = new Robot();
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(2);
	}
	public void clickOnSaveSI() {
		element = driver.findElement(By.xpath(".//*[@id='bottomButtonRow']"));
		scrollingFunction();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[contains(@value,'Save')][1]")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(8);
	}
	public String fetchServiceItemNo() {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		element = driver.findElement(By.xpath(".//div[@class='feedUpdateContainer']/div[1]/descendant::div[@class='feedcommenttext']/following-sibling::div"));
		highlightElement();
		newSINo = driver.findElement(By.xpath("//div[5]/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div[1]/a")).getText();
		
		System.out.println("Newly Generated Service Item Number is :"+newSINo);
		driver.switchTo().defaultContent();
		return newSINo;
	}
	public void createNewResponse() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
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
		driver.findElement(By.xpath(".//option[contains(text(),'HD Good Day')]")).click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'I-929 Sent to NVC')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]/preceding::option[1]"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[text()='Available Templates']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Closing')]"));
		Utils.sleep(1);
		element.click();
		Utils.sleep(1);
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
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void verifyNewResponseStatus() {
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		try {
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Under Review')]"));
		} catch (Exception e) {
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Submitted')]"));
		}
		highlightElement();
		Utils.sleep(2);
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
	public void selectRequiredDropdownlist() {
		//clear all tab code
		Utils.sleep(2);
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
		Utils.sleep(4);
		ele = By.xpath("//a[text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		scrollingFunction();
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click(); //// need to change
		System.out.println("Clicked on new service item :"+newSINo);
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	public void approveServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@name='Decision__c']")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@name='Decision__c']")).click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Approve']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto Approved from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void rejectServiceRqstWithoutAddReview() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		ele = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Not Checked']"));
		highlightElement();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@name='Decision__c']")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@name='Decision__c']")).click();
		}
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@data-value='Reject']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@name='Decision_Reason__c']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@data-value='Plain Language']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto Reject from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateResubmitStatus(String status) {
		for (int itr =0;itr<4;itr++) {
		try {
		driver.switchTo().defaultContent();
		driver.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		driver.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[text()='"+status+"']"));
		highlightElement();
		break;
		} catch (Exception e) {
			
		}
		}
		element = driver.findElement(By.xpath("//tr[2]/td[text()='"+status+"']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void validateRejectedResponse(String status) {
		driver.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//tr[2]/td[text()='"+status+"']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void searchWithStoredItem() throws AWTException {
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
		driver.findElement(By.id("phSearchInput")).sendKeys(newSINo);
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::ul[@class='autoCompleteGroup']/li[1]/a")).click();
		Utils.sleep(4);
	}
	public void editRejectedSI() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		try {
			fetchCorrectIframe(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo+"']"));
			driver.findElement(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo+"']")).click();
			driver.switchTo().defaultContent();
			Utils.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']")).click();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//span/br[1]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//span/br[1]"))).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = arguments[1];", driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")), "Good day, /br Changes for edit.");
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	public void setPercentage(String percentageValue) throws AWTException {
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
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
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
//*******************************************************Ipo Functions*****************************************************************
	@Given("^For IPO rejection and auto approval of response scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo(String user) {
		extent = new ExtentReports(workingDir+"\\test-report\\AutoApprovalOfRejectedScenarioWithIPO"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Auto Approval of Rejected Service Item after Resubmitted (Additional Review Required is Unchecked) For IPO user");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For IPO rejection and auto approval of response scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_Ipo(String val) {
		try {
			searchHDISOVSCitems("IPO Super User");
			logInAsInternalUser("IPO Super User");
			setPercentageForIpo(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For IPO rejection and auto approval of response scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile_For_Ipo() {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For IPO rejection and auto approval of response scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For IPO rejection and auto approval of response scenario Search for customer \"(.*)\"$")
	public void search_customer_For_Ipo(String customer) throws AWTException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer_For_Ipo() throws AWTException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details_For_Ipo(String appNumber) {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item_For_Ipo(String appNumber) {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For IPO rejection and auto approval of response scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item_For_Ipo(DataTable dt) {
		//need to code as per feature file.
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formNo = list.get(0).get("Form Number");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Issue");
		System.out.println("Issue Fetched ::"+category);
		String kind = list.get(0).get("Action");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String dateTime = list.get(0).get("DateTime");
		try {
			setEmailForIpo(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamSubjectAndDesAndFormTypeForIpo(subject, description, formNo, formType );
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setIssueAndAction(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOriginForIpo(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Verify New Service Item number and Details$")
	public void Verify_New_SI_For_Ipo() {
		String newlyCreatedSI = null;
		try {
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Create a new response for new Service Item$")
	public void Create_New_Response_For_Ipo() {
		try {
			createNewEmailResponseForIpo();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Verify newly created service response status$")
	public void Verify_New_Service_Response_For_Ipo() {
		try {
			verifyNewResponseStatus();
			testReporter.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logout from current profile$")
	public void log_Out_For_Ipo() {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For IPO rejection and auto approval of response scenario Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User_For_Ipo(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with One HD Supervisor");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile_For_Ipo(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with One HD Supervisor");
			Assert.assertTrue(false);
			getResult_For_Ipo();
			e.printStackTrace();
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Select required service item with Supervisor user to reject$")
	public void Select_Required_Service_item_For_Ipo() {
		try {
			selectRequiredDropdownlistForIpo();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Reject the selected service request without marking additional review checkbox$")
	public void Reject_Selected_Service_Rqst_For_Ipo() {
		try {
			rejectServiceRqstWithoutAddReview();
			testReporter.log(LogStatus.PASS, "Reject the service request with proper comments");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reject the service request with proper comments");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Verify the rejected service request status \"(.*)\"$")
	public void Verification_of_Rejected_Service_For_Ipo(String status) {
		try {
			validateRejectedResponse(status);
			testReporter.log(LogStatus.PASS, "Verify rejection status verification");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify rejection status verification");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logout from supervisor profile$")
	public void log_Out2_For_Ipo() {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For IPO rejection and auto approval of response scenario Search for required Internal User which created the response \"(.*)\"$")
	public void search_For_Internal_User2_For_Ipo(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logging in as Internal user and verifying User which created the response \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile2_For_Ipo(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Edit pre-created response and resubmit$")
	public void Edit_Resubmit_the_response_For_Ipo() {
		try {
			searchWithStoredItem();
			editRejectedSI();
			testReporter.log(LogStatus.PASS, "Log in as HD ISC VSO user and edit the service item and resubmit.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as HD ISC VSO user and edit the service item and resubmit.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Validate resubmitted service response status changed to \"(.*)\"$")
	public void Validate_Resubmitted_SI_Status_For_Ipo(String status) {
		try {
			validateResubmitStatusForIpo(status);
			testReporter.log(LogStatus.PASS, "Validate resubmitted service response status changed to "+status+".");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate resubmitted service response status changed to "+status+".");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For IPO rejection and auto approval of response scenario Logout from response editor's profile$")
	public void log_Out3_For_Ipo() {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For IPO rejection and auto approval of response scenario$")
	public void getResult_For_Ipo() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For IPO rejection and auto approval of response scenario$")
	public void flushReporter_For_Ipo() {
		driver.close();
		driver.quit();
	}	
	public void createNewEmailResponseForIpo() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Closing')]"));
		element.click();
		Utils.sleep(2);
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
	public void createNewEmailResponseForIpoWithAttachment() throws Exception {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Closing')]"));
		element.click();
		Utils.sleep(2);
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
		element = driver.findElement(By.xpath("//span[contains(text(),'isSelected')]/parent::label"));
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
		element = driver.findElement(By.xpath("//*[contains(text(),'Upload Files')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		attach_file_and_send(Constants.privacy_test_attachment_url, false);
		ele = By.xpath("//div[contains(text(),'File uploaded successfully')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		String status = "Under Review";
		driver.navigate().refresh();
		Utils.sleep(4);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']"));
		highlightElement();
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']/preceding-sibling::th/a"));
		responseNumber = element.getText();
		element.click();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+responseNumber+"')]"));
		element = driver.findElement(By.xpath("//*[text()='Files' and contains(@id,'RelatedFileList_title')]/parent::td"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//th[text()='Title']/parent::tr/following-sibling::tr/th[1]"));
		highlightElement();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
	}
	public void setPercentageForIpo(String percentageValue) throws AWTException {
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
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
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
	public void setReceiptAndCustNm(String receiptNm, String customerNm) {
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).clear();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNm);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/span/input")).clear();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/span/input")).sendKeys(customerNm);
	}
	public void setEmailForIpo(String email) {
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
	}
	public void setParamSubjectAndDesAndFormTypeForIpo(String subject, String description, String formNo, String formType) {
		element = driver.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		selectDropdownListValue(formNo, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/span/select")));
		//selectDropdownListValue(formType, driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/span/span/select")));
		Utils.sleep(2);
	}
	public void setIssueAndAction(String issue, String action, String comments) {
		element = driver.findElement(By.xpath("//*[contains(text(),'Issue / Action')]"));
		scrollingFunction();
		selectDropdownListValue(issue, driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::select")));
		selectDropdownListValue(action, driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::select")));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
	}
	public void selectSIOriginForIpo(String serviceItem) { //String serviceItem
		selectDropdownListValue(serviceItem, driver.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[2]/td[2]/div/span/select")));
	}
	public void selectRequiredDropdownlistForIpo() {
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
		driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='IPO All Open Service Items']")).click();
		Utils.sleep(2);
		try {
			ele = By.xpath("//a[text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			scrollingFunction();
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[@title='Date/Time Opened']")).click();
			Utils.sleep(2);
			ele = By.xpath("//a[text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			scrollingFunction();
		}
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
		System.out.println("Clicked on new service item :"+newSINo);
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
	public void validateResubmitStatusForIpo(String status) {
		try {
			fetchCorrectIframe(By.xpath("//div[@aria-label='Compose text']/p[1]"));
			element = driver.findElement(By.xpath("//a[text()='View Response']"));
			scrollingFunction();
			Utils.sleep(1);
			element.click();
			Utils.sleep(4);
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			
		}
		for (int itr=0; itr <4; itr++) {
		try {
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			driver.findElement(ele).click();
			driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
			fluentWaitForElementVisibility();
			driver.findElement(ele).click();
			Utils.sleep(2);
			fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
			driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//tr[2]/td[text()='"+status+"']"));
			highlightElement();
			break;
		} catch (Exception e){
			System.out.println("Iteration Count:"+itr);
		}
		}
		element = driver.findElement(By.xpath("//tr[2]/td[text()='"+status+"']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
//*************************************************************************************************************************************
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
//********************************************************IPO CSR Approval Functions****************************************************
	@Given("^For Ipo approval process of response scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo_App(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\IpoCsrApprovalReport"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Approval Scenario For IPO CSR user");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_Ipo_App(String val) throws IOException {
		try {
			searchHDISOVSCitems("IPO Super User");
			logInAsInternalUser("IPO Super User");
			setPercentageForIpo(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile_For_Ipo_App() throws IOException {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo approval process of response scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo_App(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo_App(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo approval process of response scenario Search for customer \"(.*)\"$")
	public void search_customer_For_Ipo_App(String customer) throws AWTException, IOException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer_For_Ipo_App() throws AWTException, IOException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details_For_Ipo_App(String appNumber) throws IOException {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item_For_Ipo_App(String appNumber) throws IOException {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For Ipo approval process of response scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item_For_Ipo_App(DataTable dt) throws IOException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formNo = list.get(0).get("Form Number");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Issue");
		System.out.println("Issue Fetched ::"+category);
		String kind = list.get(0).get("Action");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String dateTime = list.get(0).get("DateTime");
		try {
			setEmailForIpo(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamSubjectAndDesAndFormTypeForIpo(subject, description, formNo, formType );
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setIssueAndAction(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOriginForIpo(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Verify New Service Item number and Details$")
	public void Verify_New_SI_For_Ipo_App() throws IOException {
		String newlyCreatedSI = null;
		try {
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of a response scenario Create a new response with attachment for new Service Item and Verify newly created service response status and attachment$")
	public void Create_New_Response_For_Ipo_App() throws IOException {
		try {
			createNewEmailResponseForIpoWithAttachment();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item with attachemnt and verifying the status.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item with attachemnt and verifying the status.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Logout from current profile$")
	public void log_Out_For_Ipo_App() throws IOException {
		try {
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging out");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo approval process of response scenario Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User_For_Ipo_App(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with One HD Supervisor");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile_For_Ipo_App(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with IPO Super User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with IPO Super User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Select required service item with Super user to Approve$")
	public void Select_Required_Service_item_For_Ipo_App() throws IOException {
		try {
			selectRequiredDropdownlistForIpo();
			testReporter.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open the new service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Approve the selected service request$")
	public void Approve_Selected_Service_Rqst_For_Ipo_App() throws IOException {
		try {
			approveServiceRqst();
			testReporter.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service request/response");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo approval process of response scenario Verify the approved service request status \"(.*)\"$")
	public void Verification_of_Rejected_Service_For_Ipo_App(String status) throws IOException {
		try {
			validateResubmitStatusForIpo(status);
			testReporter.log(LogStatus.PASS, "Verify approved status verification");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify approved status verification");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For Ipo approval process of response scenario$")
	public void getResult_For_Ipo_App() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For Ipo approval process of response scenario$")
	public void flushReporter_For_Ipo_App() {
		driver.close();
		driver.quit();
	}
//**************************************************************************************************************************************
//****************************************HD Coach Flow******************************************************
	@Given("^For HD coach approval scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Coach(String user) {
        extent = new ExtentReports(workingDir+"\\test-report\\HD_Coach_2_approval_scenario"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Verify End to End flow for new response approval process with HD Coach 2 user after selecting queue as VAWA_HotlineFollowupI360.");
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
	}
	@Then("^For HD coach approval scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_Coach(String val) {
		try {
			searchHDISOVSCitems("One HD Supervisor");
			logInAsInternalUser("One HD Supervisor");
			setPercentage(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile_For_Coach() {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For HD coach approval scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Coach(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Coach(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For HD coach approval scenario Search for customer \"(.*)\"$")
	public void search_customer_For_Coach(String customer) throws AWTException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer_For_Coach() throws AWTException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details_For_Coach(String appNumber) {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item_For_Coach(String appNumber) {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For HD coach approval scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item_For_Coach(DataTable dt) {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
		try {
			setOrgName(fOrgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+fOrgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+fOrgName);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			subject = subject+randomDateTime1();
			setParamSubjectAndDesAndFormType(subject, description, formType);
			subjectLine = subject;
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamCategoryAndKind(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOrigin(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectInitialQueue(queue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+queue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+queue);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Verify New Service Item number and Details$")
	public void Verify_New_SI_For_Coach() {
		String newlyCreatedSI = null;
		try {
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Create a new response for new Service Item with attachment$")
	public void Create_New_Response_For_Coach() {
		try {
			createNewResponseWithAttachment();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item with attachment.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item with attachment.");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For HD coach approval scenario Validating the attachment$")
	public void Validate_Attachment_Is_Attached() throws IOException {
		try {
			validateTheAttachmentIsAttached();
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Verifying the attached file is present in the response and owner changed to HD Coach 2.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the attached file is present in the response and owner changed to HD Coach 2.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For HD coach approval scenario Search for required Internal coach User \"(.*)\"$")
	public void Login_as_HD_Coach_2(String user) {
		try {
			searchHDISOVSCitems(user);
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Logged in as HD Coach 2 user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logged in as HD Coach 2 user.");
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For HD coach approval scenario Validate the approved service item status changed to \"(.*)\"$")
	public void Approve_the_Service_Item(String stat) throws IOException {
		try {
			selectRequiredDropdownlist();
			approveServiceRqst();
			validateRejectedResponse(stat);
			testReporter.log(LogStatus.PASS, "Select "+newSINo+" service item and approve it and verfiy the response status changed to "+stat);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL,  "Select "+newSINo+" service item and approve it and verfiy the response status changed to "+stat);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For HD coach approval scenario$")
	public void getResult_For_Coach() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For HD coach approval scenario$")
	public void flushReporter_For_Coach() {
		driver.close();
		driver.quit();
	}
//***********************************************************************************************************
	public void createNewResponseWithAttachment() throws Exception {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
		driver.findElement(By.xpath("//a[@title='Feed']/span[1]")).click();
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
		driver.findElement(By.xpath(".//option[contains(text(),'HD Good Day')]")).click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//option[contains(text(),'I-929 Sent to NVC')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]/preceding::option[1]"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[text()='Available Templates']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//option[contains(text(),'HD Closing')]"));
		Utils.sleep(1);
		element.click();
		Utils.sleep(1);
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
		element = driver.findElement(By.xpath("//*[contains(text(),'Upload Files')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		attach_file_and_send(Constants.privacy_test_attachment_url, false);
		ele = By.xpath("//div[contains(text(),'File uploaded successfully')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
	}
	public void validateTheAttachmentIsAttached() throws IOException {
		String status = "Submitted";
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']"));
		highlightElement();
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']/preceding-sibling::th/a"));
		responseNumber = element.getText();
		element.click();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+responseNumber+"')]"));
		element = driver.findElement(By.xpath("//*[text()='Files' and contains(@id,'RelatedFileList_title')]/parent::td"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//th[text()='Title']/parent::tr/following-sibling::tr/th[1]"));
		highlightElement();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		try {
			ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
			driver.findElement(ele).click();
			driver.findElement(By.xpath("//span[text()='Details']/parent::span/parent::*")).click();
			Utils.sleep(2);
		} catch (Exception e) {
			ele = By.xpath(".//span[@class='tabText' and text()='New Response']");
			driver.findElement(ele).click();
			driver.findElement(By.xpath("//span[text()='Details']/parent::span/parent::*")).click();
			Utils.sleep(2);
		}
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		try {
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/span/a[contains(text(),'HD Coach 2')]"));
		highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.WARNING, "Verifying the attached file is present in the response and owner changed to HD Coach 2.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		element = driver.findElement(By.xpath("//span[contains(text(),'Initial Queue')]/parent::td/following-sibling::td[1]/div[contains(text(),'VAWA_HotlineFollowupI360')]"));
		highlightElement();
		driver.switchTo().defaultContent();
		// log out from here and log in as HD Coach
	}
	private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
		// click attachment
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
	//*************************************************ISO-12556****************************************************
	@Given("^For ISO12556 scenario Registered User is logged in with \"(.*)\"$")
	public void init_ISO12556(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\ISO_12556_Scenario"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("Verification of change the owner as One HD SUpervisor");
		try {
			launch();
			switchToClassicView();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For ISO12556 scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_ISO12556(String val) throws IOException {
		try {
			searchHDISOVSCitems("One HD Supervisor");
			logInAsInternalUser("One HD Supervisor");
			setPercentage(val);
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For ISO12556 response scenario Search for customer \"(.*)\"$")
	public void search_customer_ISO12556(String customer) throws AWTException, IOException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer_ISO12556() throws AWTException, IOException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details_ISO12556_ISO12556(String appNumber) throws IOException {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item_ISO12556(String appNumber) throws IOException {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For ISO12556 scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item_ISO12556(DataTable dt) throws IOException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String fOrgName = list.get(0).get("Org Name");
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Category");
		String kind = list.get(0).get("Kind");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String queue = list.get(0).get("Queue");
		String dateTime = list.get(0).get("DateTime");
		try {
			setOrgName(fOrgName);
			testReporter.log(LogStatus.PASS, "Give Organization Name : "+fOrgName);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Organization Name : "+fOrgName);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setEmail(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			subject = subject+randomDateTime1();
			setParamSubjectAndDesAndFormType(subject, description, formType);
			subjectLine = subject;
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamCategoryAndKind(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOrigin(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectInitialQueue(queue);
			testReporter.log(LogStatus.PASS, "Give Initial Queue : "+queue);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Initial Queue : "+queue);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario open any existing SI$")
	public void Open_Existing_SI_ISO12556() throws IOException {
		String newlyCreatedSI = null;
		try {
			openAnExistingSI();
			testReporter.log(LogStatus.PASS, "Captured service item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Captured service item number :"+newlyCreatedSI);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Change the owner to VAWA_DACADutyDesk and then change to VAWA_ISOA$")
	public void Create_New_Response_SI_ISO12556() throws IOException {
		try {
			setTheOwner();
			testReporter.log(LogStatus.PASS, "Verify after log in as One HD Supervisor, user is able to change the owner to VAWA_DACADutyDesk and then change to VAWA_ISOA");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify after log in as One HD Supervisor, user is able to change the owner to VAWA_DACADutyDesk and then change to VAWA_ISOA");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario ISO Queue Assignment selection for HD ISOA user$")
	public void Queue_ISO12556() throws IOException {
		try {
			setISOQueueAssignment();
			testReporter.log(LogStatus.PASS, "Assign the HD ISOA user with only VAWA_ISOA checkbox and uncheck all other checkboxes if those are checked.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Assign the HD ISOA user with only VAWA_ISOA checkbox and uncheck all other checkboxes if those are checked.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Logging is as HD ISOA$")
	public void Log_in_HD_ISOA() throws IOException {
		try {
			searchHDISOVSCitems("HD ISOA");
			logInAsInternalUser("HD ISOA");
			testReporter.log(LogStatus.PASS, "Logging in as HD ISOA");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging in as HD ISOA");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Mark existing open items as duplicate and open the new SI by assign a new service item$")
	public void Assign_New_SI() throws IOException {
		try {
			duplicateSIandOpenSI();
			testReporter.log(LogStatus.PASS, "For ISO12556 scenario Mark existing open items as duplicate and open the new SI by assign a new service item"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO12556 scenario Mark existing open items as duplicate and open the new SI by assign a new service item"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Open the selected SI and Verify the current Owner is HD ISOA$")
	public void Verify_The_Current_Owner() throws IOException {
		try {
			verifyTheOwner();
			testReporter.log(LogStatus.PASS, "For ISO12556 scenario Open the selected SI and Verify the current Owner is HD ISOA"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO12556 scenario Open the selected SI and Verify the current Owner is HD ISOA"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Verify as HD ISOA owner can be changed to VAWA_I918inquiriesNSC, HD Correspondence Queue and VAWA_DACADutyDesk$")
	public void Verify_Owner_Change_As_HDISOA() throws IOException {
		try {
			changeTheOwner();
			testReporter.log(LogStatus.PASS, "For ISO12556 scenario Verify as HD ISOA owner can be changed to VAWA_I918inquiriesNSC, HD Correspondence Queue and VAWA_DACADutyDesk");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO12556 scenario Verify as HD ISOA owner can be changed to VAWA_I918inquiriesNSC, HD Correspondence Queue and VAWA_DACADutyDesk");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Logging is as HD ISO VSC$")
	public void Log_in_HD_ISO_VSC() throws IOException {
		try {
			searchHDISOVSCitems("HD ISO VSC");
			logInAsInternalUser("HD ISO VSC");
			testReporter.log(LogStatus.PASS, "Logging in as HD ISO VSC");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging in as HD ISO VSC");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12556 scenario Select any random open SI and verify as HD ISO VSC, user can't change the owner to VAWA_DACADutyDesk$")
	public void HD_ISO_VSC_User_Owner_Change() throws IOException {
		try {
			verifyHDIsoVscUserChangeAccess();
			testReporter.log(LogStatus.PASS, "Select any random open SI and verify as HD ISO VSC, user can't change the owner to VAWA_DACADutyDesk");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Select any random open SI and verify as HD ISO VSC, user can't change the owner to VAWA_DACADutyDesk");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For Iso12556 scenario$")
	public void getResult_ISO12556() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For Iso12556 scenario$")
	public void flushReporter_Iso12556() {
		driver.close();
		driver.quit();
	}
	//**************************************************************************************************************
	public void openAnExistingSI() {
		try {
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
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
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@title='Date/Time Opened']")).click();
			Utils.sleep(1);
			newSINo = driver.findElements(By.xpath("//td[contains(@class,'CASES_CASE_NUMBER')]/div[not(contains(@title,'Service Item Number'))]/a")).get(0).getText();
			driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
			Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void setTheOwner() {
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_DACADutyDesk");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_DACADutyDesk')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_DACADutyDesk')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown1 = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown1.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_ISOA");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_ISOA')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_ISOA')]"));
		highlightElement();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
	}
	public void setISOQueueAssignment() {
		try {
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
			Utils.sleep(2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
			Utils.sleep(4);
			} catch (Exception e) {
				
			}
		driver.navigate().refresh();
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
				driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='ISO Queue Assignment']")));
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		int checkCount = 0;
		try {
			checkCount = driver.findElements(By.xpath("//td[text()='HD ISOA']/following-sibling::td/input[@checked='checked' and not(@title='VAWA_ISOA')]")).size();
		} catch (Exception e) {
			checkCount = 0;
			e.printStackTrace();
		}
		if (checkCount >1) {
			for(int itr = 0; itr <checkCount; itr++) {
				try {
				element = driver.findElements(By.xpath("//td[text()='HD ISOA']/following-sibling::td/input[@checked='checked' and not(@title='VAWA_ISOA')]")).get(itr);
				highlightElement();
				driver.findElements(By.xpath("//td[text()='HD ISOA']/following-sibling::td/input[@checked='checked' and not(@title='VAWA_ISOA')]")).get(itr).click();
				Utils.sleep(1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			element = driver.findElement(By.xpath("//td[text()='HD ISOA']/following-sibling::td/input[@checked='checked' and @title='VAWA_ISOA']/parent::*"));
			highlightElement();
		} catch (Exception e) {
			driver.findElement(By.xpath("//td[text()='HD ISOA']/following-sibling::td/input[@checked='checked' and @title='VAWA_ISOA']")).click();
			Utils.sleep(1);
		}
		driver.findElement(By.xpath("//div[@class='pbHeader']/table/tbody/tr[1]/td[2]/input[@value='Save']")).click();
		Utils.sleep(1);
		ele = By.xpath("//div[contains(text(),'Successfully updated queue assignments')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		currentUserLogOut();
	}
	public void duplicateSIandOpenSI () throws AWTException {
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
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			Utils.sleep(2);
			driver.switchTo().alert().accept();
			Utils.sleep(2);
		} catch (Exception e) {
			System.out.println("No Service Item number is present for duplication process.");
		}
		driver.findElement(By.xpath(".//input[@title='Refresh']")).click();
		Utils.sleep(3);
			try {
				driver.findElement(By.xpath("//input[@value='Assign a Service Item']")).click();
				wait.until(ExpectedConditions.alertIsPresent());
				Utils.sleep(1);
				robot.keyPress(KeyEvent.VK_ENTER);
				Utils.sleep(1);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Utils.sleep(3);
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
	public void verifyTheOwner() {
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/descendant::a[contains(text(),'HD ISOA')]"));
		highlightElement();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@class='relatedListIcon']/following::*[text()='Service Item History']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//strong[text()='VAWA_ISOA']/parent::td"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void changeTheOwner() {
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_I918inquiriesNSC");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_I918inquiriesNSC')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_I918inquiriesNSC')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown1 = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown1.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("HD Correspondence Queue");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'HD Correspondence Queue')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'HD Correspondence Queue')]"));
		highlightElement();
		Utils.sleep(1);
		
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown2 = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown2.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_DACADutyDesk");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_DACADutyDesk')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_DACADutyDesk')]"));
		highlightElement();
		Utils.sleep(1);
		driver.switchTo().defaultContent();
		currentUserLogOut();
	}
	public void verifyHDIsoVscUserChangeAccess() {
		openAnExistingSI();
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_DACADutyDesk");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//div[contains(text(),'Please make a request to your supervisor to update the Service Item Owner')]");
		fluentWaitForElementVisibility();
		driver.switchTo().defaultContent();
	}
	//**************************************************************************************************************
	//**************************ISO 12669 -IPO user take control button functionality*******************************
	@Given("^For Ipo ISO12669 scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo_App_ISO12669(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\ISO12669_IPO_TakeOwnerShip"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO CSR user takes ownership of a Service Item by clicking on the Take oWnership button");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage_For_Ipo_App_ISO12669(String val) throws IOException {
		try {
			searchHDISOVSCitems("IPO Super User");
			logInAsInternalUser("IPO Super User");
			setPercentageForIpo(val);
			currentUserLogOut();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile_For_Ipo_App_ISO12669() throws IOException {
		try {
			verifyProfile();
			testReporter.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo ISO12669 scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo_App_ISO12669(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo_App_ISO12669(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo ISO12669 scenario Search for customer \"(.*)\"$")
	public void search_customer_For_Ipo_App_ISO12669(String customer) throws AWTException, IOException {
		try {
			customerSearch(customer);
			testReporter.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search for required customer");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Refresh the customer data and wait for Refresh successful message$")
	public void update_customer_For_Ipo_App_ISO12669() throws AWTException, IOException {
		try {
			customerRefresh();
			testReporter.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Open the application record details from Refresh User page \"(.*)\"$")
	public void open_application_details_For_Ipo_App_ISO12669(String appNumber) throws IOException {
		try {
			clickOnRecordItemFromOwnerPage(appNumber);
			testReporter.log(LogStatus.PASS, "Opening the Application details from Customer refresh page.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the Application details from Customer refresh page.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Click on create new service item \"(.*)\"$")
	public void click_on_create_new_service_item_For_Ipo_App_ISO12669(String appNumber) throws IOException {
		try {
			createNewServiceItemParam(appNumber);
			testReporter.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Create new Service item button");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@And("^For Ipo ISO12669 scenario create new service item Provide all new mandatory data$")
	public void create_new_service_item_For_Ipo_App_ISO12669(DataTable dt) throws IOException {
		List<Map<String, String>> list = dt.asMaps(String.class, String.class);
		String email = list.get(0).get("Email");
		String senderType = list.get(0).get("Sender Type");
		String subject = list.get(0).get("Subject");
		String description = list.get(0).get("Description");
		String formNo = list.get(0).get("Form Number");
		String formType = list.get(0).get("Form Type");
		String category = list.get(0).get("Issue");
		System.out.println("Issue Fetched ::"+category);
		String kind = list.get(0).get("Action");
		String comm = list.get(0).get("Comments");
		String io = list.get(0).get("Item Origin");
		String dateTime = list.get(0).get("DateTime");
		try {
			setEmailForIpo(email);
			testReporter.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Email Id: "+email);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSenderType(senderType);
			testReporter.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setParamSubjectAndDesAndFormTypeForIpo(subject, description, formNo, formType );
			testReporter.log(LogStatus.PASS, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Subject,  Description and Form Type respectively: "+subject+" ,"+description+" and "+formType);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setIssueAndAction(category, kind, comm);
			testReporter.log(LogStatus.PASS, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Category, Kind Name and Comments respectively :"+category+" ,"+kind+" ,"+comm);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			selectSIOriginForIpo(io);
			testReporter.log(LogStatus.PASS, "Give Service Item Origin :"+io);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Service Item Origin :"+io);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			setReceivedDate(dateTime);
			testReporter.log(LogStatus.PASS, "Give Received Date (any past date) : "+dateTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Give Received Date (any past date) : "+dateTime);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		try {
			clickOnSaveSI();
			testReporter.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12669 scenario Verify New Service Item number and Details$")
	public void Verify_New_SI_For_Ipo_App_ISO12669() throws IOException {
		String newlyCreatedSI = null;
		try {
			newlyCreatedSI = (String) fetchServiceItemNo();
			testReporter.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12669 scenario Set the Owner as IPO SI Q user for the newly created Service Item$")
	public void Change_The_Owner_To_IPO() throws IOException {
		try {
			changeTheSIOwnerForIPO();
			testReporter.log(LogStatus.PASS, "For ISO12669 scenario Set the Owner as IPO SI Q user for the newly created Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "For ISO12669 scenario Set the Owner as IPO SI Q user for the newly created Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12669 scenario Click on take control button$")
	public void Take_control() throws IOException {
		try {
			takeControlOfIPO();
			testReporter.log(LogStatus.PASS, "Click on take control button and validate the owner changed to IPO CSR");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on take control button and validate the owner changed to IPO CSR");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12696 scenario Click on Close Service item button and verify SI status is closed$")
	public void Verify_The_Close_SI_button() throws IOException {
		try {
			verifyCloseSIBt();
			testReporter.log(LogStatus.PASS, "Click on Close Service item button and verify SI status is closed");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Click on Close Service item button and verify SI status is closed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For Iso12669 scenario$")
	public void getResult_ISO12669() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For Iso12669 scenario$")
	public void flushReporter_Iso12669() {
		driver.close();
		driver.quit();
	}
	public void changeTheSIOwnerForIPO() {
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("IPO SI Q");
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'IPO SI Q')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'IPO SI Q')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void takeControlOfIPO() {
		driver.navigate().refresh();
		Utils.sleep(2);
		WebDriverWait wait2 = new WebDriverWait (driver, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@class='pbHeader']/descendant::td[@id='topButtonRow']/input[@value='Take Ownership']")).click();
		wait2.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(1);
		driver.switchTo().alert().accept();
		Utils.sleep(1);
		driver.navigate().refresh();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
		driver.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		ele = By.xpath("//span[contains(text(),'Service Item Owner')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/descendant::a[contains(text(),'IPO CSR')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/descendant::a[contains(text(),'IPO CSR')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	//*********************************************************************************************************************************
	//**************************************************************************ISO12670**********************************************************************************************************************
	@Given("^For Ipo ISO12670 scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo_App_ISO12670(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\ISO12670_IPO_Set_Form_No"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO CSR user set the Form NO as Blank for Duplicate and Non Duplicate SI");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo ISO12670 scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo_App_ISO12670(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12670 scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo_App_ISO12670(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12670 scenario open any existing SI$")
	public void Open_Existing_SI_ISO12670() throws IOException {
		try {
			openAnExistingSIFor12670();
			testReporter.log(LogStatus.PASS, "Captured service item number :"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Captured service item number :"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12670 scenario Verify while saving an SI with Form No as blank, user is getting an error$")
	public void Edit_Form_No_SI_ISO12670() throws IOException {
		try {
			editTheFormNo();
			testReporter.log(LogStatus.PASS, "Edit the form no for the open SI by checking the SI status With Officer and verify blank form no save error coming or not.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Edit the form no for the open SI by checking the SI status With Officer and verify blank form no save error coming or not.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12670 scenario Mark the selected item as Duplicate$")
	public void Mark_As_Duplicate_Ipo_App_ISO12670() throws IOException {
		try {
			markDuplicate();
			testReporter.log(LogStatus.PASS, "Marking the selected service item as duplicate.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Marking the selected service item as duplicate.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12670 scenario Verify user successfully set blank form no for an duplicate SI$")
	public void Set_Form_As_Blank_For_Duplicate_SI_Ipo_App_ISO12670() throws IOException {
		try {
			editFormNoWithDuplicateSI();
			testReporter.log(LogStatus.PASS, "Verify user successfully set blank form no for an duplicate SI.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify user successfully set blank form no for an duplicate SI.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For ISO12670 scenario$")
	public void getResult_ISO12670() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For ISO12670 scenario$")
	public void flushReporter_ISO12670() {
		driver.close();
		driver.quit();
	}
	
	
	public void openAnExistingSIFor12670() {
		try {
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
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
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@title='Date/Time Opened']")).click();
			Utils.sleep(1);
			newSINo = driver.findElements(By.xpath("//td[contains(@class,'CASES_CASE_NUMBER')]/div[not(contains(@title,'Service Item Number'))]/a")).get(0).getText();
			driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
			Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void editTheFormNo() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]/div[2]/span/select"));
		selectDropdownListValue("With Officer", element);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]"));
		highlightElement();
		element = driver.findElement(By.xpath("//img[@name='How Can We Help You']/following::*[text()='How Can We Help You']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table/tbody/tr[1]/td[4]"));
		Actions actObj1 = new Actions(driver);
		actObj1.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//td[text()='Form Number']/following-sibling::td/descendant::select"));
		selectDropdownListValue("", element);
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='InlineEditDialog_buttons']/input[@value='OK']")).click();
		ele = By.xpath("//div[@class='pbSubsection'][3]/table/tbody/tr[1]/td[4]/descendant::*[text()='Deleted']");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		element = driver.findElement(By.xpath("//div[@class='listHoverLinks']"));
		scrollingFunction();
		driver.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Save')]")).click();
		Utils.sleep(4);
		ele = By.xpath(".//*[@id='errorDiv_ep']");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='How Can We Help You']/following::*[text()='How Can We Help You']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='errorMsg' and contains(text(),'Please update the')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//a[@title='Details']/span/span[1]"));
		scrollingFunction();
		driver.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Cancel')]")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		try {
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
			Utils.sleep(2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
			Utils.sleep(4);
			} catch (Exception e) {
				
			}
	}
	public void markDuplicate() {
		driver.navigate().refresh();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 14);
		driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver.findElement(By.xpath(".//input[@id='allBox']")).click();
		driver.findElement(By.xpath(".//input[@value='Mark as Duplicate']")).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(2);
		driver.switchTo().alert().accept();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void editFormNoWithDuplicateSI() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]/div[text()='Duplicate']"));
		highlightElement();
		element = driver.findElement(By.xpath("//img[@name='How Can We Help You']/following::*[text()='How Can We Help You']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table/tbody/tr[1]/td[4]"));
		Actions actObj1 = new Actions(driver);
		actObj1.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//td[text()='Form Number']/following-sibling::td/descendant::select"));
		selectDropdownListValue("", element);
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='InlineEditDialog_buttons']/input[@value='OK']")).click();
		ele = By.xpath("//div[@class='pbSubsection'][3]/table/tbody/tr[1]/td[4]/descendant::*[text()='Deleted']");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		element = driver.findElement(By.xpath("//div[@class='listHoverLinks']"));
		scrollingFunction();
		driver.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Save')]")).click();
		Utils.sleep(2);
		ele = By.xpath(".//*[@id='topButtonRow']/input[@title='Edit' and not(contains(@style,'display: none;'))]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='How Can We Help You']/following::*[text()='How Can We Help You']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][3]/table/tbody/tr[1]/td[4]/div[contains(text(),'')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	//***************************************************************************************************************************************
	//**********************************************************************************************ISO12696*********************************
	//Add this to ISO12669
	public void verifyCloseSIBt() {
		driver.navigate().refresh();
		Utils.sleep(4);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@title='Close Service Item']")).click();
		ele = By.xpath("//textarea[@maxlength='4000']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).sendKeys("Closing this Service item.");
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[1]")).click();
		ele = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]/div[text()='Closed']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	//***************************************************************************************************************************************
	//***************************************************************ISO12648********************************************************
	@Given("^For Ipo ISO12648 scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo_App_ISO12648(String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\ISO12648_IPO_Default_SentTO_"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO CSR user set default sent to mailbox");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo ISO12648 scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo_App_ISO12648(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO12648 scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo_App_ISO12648(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12648 scenario open any existing SI$")
	public void Open_Existing_SI_ISO12648() throws IOException {
		try {
			openAnExistingSIFor12648();
			testReporter.log(LogStatus.PASS, "Captured service item number :"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Captured service item number :"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO12648 scenario Validate the new response has a response recipient selected by default$")
	public void Default_MailTo_Selection_ISO12648() throws IOException {
		try {
			verifyDefaultEmailBoxIsSelected();
			testReporter.log(LogStatus.PASS, "Create a new response and validate response recipient mail is selected by default:"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Create a new response and validate response recipient mail is selected by default:"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For ISO12648 scenario$")
	public void getResult_ISO12648() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For ISO12648 scenario$")
	public void flushReporter_ISO12648() {
		driver.close();
		driver.quit();
	}
	public void openAnExistingSIFor12648() {
		try {
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
		Utils.sleep(4);
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
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='Recently Viewed Service Items']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@title='Date/Time Opened']")).click();
			Utils.sleep(1);
			newSINo = driver.findElements(By.xpath("//td[contains(@class,'CASES_CASE_NUMBER')]/div[not(contains(@title,'Service Item Number'))]/a")).get(0).getText();
			driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
			Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public void verifyDefaultEmailBoxIsSelected() {
		WebDriverWait wait = new WebDriverWait (driver, 14);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath("//span[contains(text(),'isSelected')]/parent::label"));
		highlightElement();
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		try {
			Utils.sleep(1);
			driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
			Utils.sleep(1);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
			Utils.sleep(4);
			} catch (Exception e) {
				
			}
		ele = By.xpath("//span[text()='Unsaved Changes']/parent::*");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//button[text()='Continue']/parent::*")).click();
		Utils.sleep(2);
	}
	//*******************************************************************************************************************************
	@Given("^For Ipo ISO13881-14883 scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo_App_ISO13881_14883 (String user) throws IOException {
        extent = new ExtentReports(workingDir+"\\test-report\\ISO13881-14883_Article_History_"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("IPO CSR user has article tab and Add History Checkbox is unchecked");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@When("^For Ipo ISO13881_14883 scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_For_Ipo_App_ISO13881_14883(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo ISO13881_14883 scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_For_Ipo_App_ISO13881_14883(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult_For_Ipo_App();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO13881_14883 scenario Verify article dropdown is available or not$")
	public void Open_Existing_SI_ISO13881_14883() throws IOException {
		try {
			verifyArticleDropdn();
			testReporter.log(LogStatus.PASS, "Article dropdown list item is available.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Article dropdown list item is not available.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For ISO13881_14883 scenario Validate Add history checkbox is not check while creating new response$")
	public void Default_MailTo_Selection_ISO13881_14883() throws IOException {
		try {
			
			testReporter.log(LogStatus.PASS, "Validate Add history checkbox is not check while creating new response"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate Add history checkbox is not check while creating new response"+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For ISO13881_14883 scenario$")
	public void getResult_ISO13881_14883() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For ISO13881_14883 scenario$")
	public void flushReporter_ISO13881_14883() {
		driver.close();
		driver.quit();
	}
	//*******************************************************************************************************************************
	public void verifyArticleDropdn () {
		try {
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
			Utils.sleep(2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
			Utils.sleep(4);
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
						driver.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Article']")));
				Utils.sleep(2);
	}
	public void validateAddHistoryChkbox () throws IOException {
		openAnExistingSI();
		WebDriverWait wait = new WebDriverWait (driver, 14);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element = driver.findElement(By.xpath("//*[contains(text(),'Add Email History')]/preceding-sibling::div[1]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//*[contains(text(),'Add Email History')]"));
		highlightElement();
		element = driver.findElement(By.xpath("//*[contains(text(),'Add Email History')]/input"));
		if (element.isSelected()) {
			testReporter.log(LogStatus.FAIL, "Add Email History checkbox is checked for SI : "+newSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		} else {
			testReporter.log(LogStatus.PASS, "Add Email History checkbox is not checked for SI : "+newSINo);
		}
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
	}
	public static void main(String[] args) throws Exception {
		HD_ISO_VSC_Auto_Approval_of_Resubmitted_Si gt = new HD_ISO_VSC_Auto_Approval_of_Resubmitted_Si();
		gt.launch();
		/*gt.searchHDISOVSCitems("One HD Supervisor");
		gt.logInAsInternalUser("One HD Supervisor");
		gt.openAnExistingSI();
		gt.setTheOwner();
		gt.setISOQueueAssignment();
		gt.searchHDISOVSCitems("HD ISOA");
		gt.logInAsInternalUser("HD ISOA");
		gt.duplicateSIandOpenSI();
		gt.verifyTheOwner();
		gt.changeTheOwner();
		gt.searchHDISOVSCitems("HD ISO VSC");
		gt.logInAsInternalUser("HD ISO VSC");
		gt.verifyHDIsoVscUserChangeAccess();*/
		//driver.close();
		//driver.quit();
		gt.searchHDISOVSCitems("IPO CSR");
		gt.logInAsInternalUser("IPO CSR");
		gt.validateAddHistoryChkbox();
		//Utils.sleep(8);
		//gt.changeTheSIOwnerForIPO();
		//gt.takeControlOfIPO();
		//newSINo = "06764572";
		//gt.searchWithStoredItem();
		//gt.validateTheAttachmentIsAttached();
		//gt.currentUserLogOut();
	}
}
