package com.salesforcetest.mapper.srire2e;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
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
import com.salesforcetest.pages.salesforce.uatg.RefreshExistingUserRecordAction;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_New_Service_Response_Rejection_Flow {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo,screenShotPath;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent;
	
	static ExtentTest testReporter;
	@Given("^For rejection and approval of response scenario Registered User is logged in with \"(.*)\"$")
	public void init(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\E2EflowWithAdditionalReviewEnabledScenarioWithHDusr"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("End to End Approval and Rejection Flow");
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
	@Then("For rejection and approval of response scenario set QC Percentage to \"(.*)\"$")
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
	@Then("For rejection and approval of response scenario Verifying the current logged in user profile$")
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
	@When("^For rejection and approval of response scenario Search for required Internal User \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying \"(.*)\"$")
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
	@When("^For rejection and approval of response scenario Search for customer \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Refresh the customer data and wait for Refresh successful message$")
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
	@Then("^For rejection and approval of response scenario Open the application record details from Refresh User page \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Click on create new service item \"(.*)\"$")
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
	@And("^To create new service item Provide all new mandatory data$")
	public void create_new_service_item(DataTable dt) throws IOException {
		//need to code as per feature file.
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
			setParamSubjectAndDesAndFormType(subject, description, formType);
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
	@Then("^For rejection and approval of response scenario Verify New Service Item number and Details$")
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
	@Then("^For rejection and approval of response scenario Create a new response for new Service Item$")
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
	@Then("^For rejection and approval of response scenario Verify newly created service response status$")
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
	@Then("^For rejection and approval of response scenario Logout from current profile$")
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
	@When("^For rejection and approval of response scenario Search for required Supervisor User \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Select required service item with Supervisor user to reject$")
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
	@Then("^For rejection and approval of response scenario Reject the selected service request$")
	public void Reject_Selected_Service_Rqst() throws IOException {
		try {
			rejectServiceRqst();
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
	@Then("^For rejection and approval of response scenario Verify the rejected service request status$")
	public void Verification_of_Rejected_Service() throws IOException {
		try {
			validateRejectedResponse();
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
	@Then("^For rejection and approval of response scenario Logout from supervisor profile$")
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
	@When("^For rejection and approval of response scenario Search for required Internal User which created the response \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying User which created the response \"(.*)\"$")
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
	@Then("^For rejection and approval of response scenario Edit pre-created response and resubmit$")
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
	@Then("^For rejection and approval of response scenario Logout from response editor's profile$")
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
	@When("^For rejection and approval of response scenario Search for required Internal User Supervisor user \"(.*)\"$")
	public void search_For_Internal_User3(String user) throws IOException {
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
	@Then("^For rejection and approval of response scenario Logging in as Internal user and verifying Supervisor user \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile3(String user) throws IOException {
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
	@Then("^For rejection and approval of response scenario Select required service item with Supervisor user to approve$")
	public void Select_Required_Service_item2() throws IOException {
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
	@Then("^For rejection and approval of response scenario Approve the selected service request$")
	public void Approve_Selected_Service_Rqst() throws IOException {
		try {
			approveServiceRqst();
			testReporter.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service request/response");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For rejection and approval of response scenario Validate approved request response status changed to sent$")
	public void Validate_Approved_Rqst() throws IOException {
		try {
			validateApprovedResponse();
			testReporter.log(LogStatus.PASS, "Validate the approved request response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the approved request response");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For rejection and approval of response scenario$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For rejection and approval of response scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(4);
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
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
		 
	    .withTimeout(20, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver);
		try {
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e) {
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
	public void searchHDISOVSCitems(String user) throws AWTException { 
		try {
		//clear all tab code
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
		WebDriverWait wait = new WebDriverWait (driver, 400);
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
	public void setEmail(String email) { //String email
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
	}
	public void selectSenderType(String senderType) { //String senderType
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
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
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
		driver.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(1);
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Logout']")));
		Utils.sleep(6);
	}
	//Call supervisor function to log in with supervisor
	public void selectRequiredDropdownlist() {
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
		Utils.sleep(4);
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
		Utils.sleep(1);
		driver.findElement(By.xpath("//a[text()='"+newSINo+"']")).click();
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
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@data-value='Approve']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto Approved from System.");
		Utils.sleep(2);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(2);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateApprovedResponse() {
		for (int itr=0; itr <4; itr++) {
			try {
				driver.switchTo().defaultContent();
				driver.navigate().refresh();
				Utils.sleep(2);
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
				element = driver.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
				highlightElement();
				element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
				scrollingFunction();
				element = driver.findElement(By.xpath("//th[text()='Sent']"));
				break;
			}catch (Exception e){
				System.out.println("Iteration Count:"+itr);
			}
			}
		element = driver.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void rejectServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		try {
		element = driver.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[4]/td[4]/descendant::img[@title='Checked']"));
		highlightElement();
		} catch (Exception e) {
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']"));
		driver.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']")).click();
		clickOnSaveSI();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		ele = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Checked']"));
		highlightElement();
		}
		Utils.sleep(2);
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
	public void rejectServiceRqstWithoutAddReview() {
		WebDriverWait wait = new WebDriverWait (driver, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		ele = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Checked']"));
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
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@id='input-10']")).click();
		}
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Reject']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//input[@id='input-11']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//*[@data-value='Plain Language']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//textarea[@id='input-3']")).sendKeys("Auto Reject from System.");
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
	public void validateResubmitStatus() {
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
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		try {
			element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='Approved']"));
			highlightElement();
		} catch (Exception e) {
			element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='Sent']"));
			highlightElement();	
		}
		driver.switchTo().defaultContent();
	}
	public void validateRejectedResponse() {
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
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='Edits Required']"));
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
		}
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		int numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='Edits Required']"));
		highlightElement();
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[1]/a[text()='Edit']"));
		highlightElement();
		driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[1]/a[text()='Edit']")).click();
		driver.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//span/br[1]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//span/br[1]"))).click().build().perform();
		Utils.sleep(2);
		((JavascriptExecutor)driver).executeScript("arguments[0].textContent = arguments[1];", driver.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")), "Good day, Changes for edit.");
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
//***********************************************************IPO Functions***************************************************************
	@Given("^For Ipo rejection and approval of response scenario Registered User is logged in with \"(.*)\"$")
	public void init_For_Ipo(String user) {
		extent = new ExtentReports(workingDir+"\\test-report\\E2EflowWithAdditionalReviewEnabledScenarioWithIpousr"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("End to End Approval and Rejection Flow For IPO user on an existing Service Item");
		try {
			launch();
			switchToClassicView();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("For Ipo rejection and approval of response scenario set QC Percentage to \"(.*)\"$")
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
	@Then("For Ipo rejection and approval of response scenario Verifying the current logged in user profile$")
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
	@When("^For Ipo rejection and approval of response scenario Search for required Internal User \"(.*)\"$")
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
	@Then("^For Ipo rejection and approval of response scenario Logging in as Internal user and verifying \"(.*)\"$")
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
	@Then("^For Ipo rejection and approval of response scenario Opening an Existing SI with Receipt No \"(.*)\" to verify Additional Review Checkbox functionality$")
	public void Opening_Existing_service_Item(String receiptNo) {
		try {
			openRandomSIFromOpenItems(receiptNo);
			testReporter.log(LogStatus.PASS, "Opening an Existing Service Item to verify Additional Review Checkbox functionality");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening an Existing Service Item to verify Additional Review Checkbox functionality");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Create a new response for random Service Item$")
	public void Create_New_Response_For_Ipo() {
		try {
			createNewResponseForIpo();
			testReporter.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Verify newly created service response status \"(.*)\"$")
	public void Verify_New_Service_Response_For_Ipo(String status) {
		try {
			validateExistingSINewResponseStatus(status);
			testReporter.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Logout from current profile$")
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
	@When("^For Ipo rejection and approval of response scenario Search for required Super User \"(.*)\"$")
	public void search_For_Supervisor_User_For_Ipo(String user) {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with Ipo Super User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with Ipo Super User");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile_For_Ipo(String user) {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Login with IPO SUPER USER");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Login with IPO SUPER USER");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Select required service item with Super user to reject$")
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
	@Then("^For Ipo rejection and approval of response scenario Reject the selected service request$")
	public void Reject_Selected_Service_Rqst_For_Ipo() {
		try {
			rejectServiceRqst();
			testReporter.log(LogStatus.PASS, "Reject the service request with proper comments");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reject the service request with proper comments");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Verify the rejected service request status$")
	public void Verification_of_Rejected_Service_For_Ipo() {
		try {
			validateRejectedResponse();
			testReporter.log(LogStatus.PASS, "Verify rejection status verification");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify rejection status verification");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Logout from supervisor profile$")
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
	@When("^For Ipo rejection and approval of response scenario Search for required Internal User which created the response \"(.*)\"$")
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
	@Then("^For Ipo rejection and approval of response scenario Logging in as Internal user and verifying User which created the response \"(.*)\"$")
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
	@Then("^For Ipo rejection and approval of response scenario Edit pre-created response and resubmit$")
	public void Edit_Resubmit_the_response_For_Ipo() {
		try {
			searchWithStoredItem();
			editRejectedSI();
			testReporter.log(LogStatus.PASS, "Log in as IPO CSR user and edit the service item and resubmit.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as IPO CSR user and edit the service item and resubmit.");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Logout from response editor's profile$")
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
	@When("^For Ipo rejection and approval of response scenario Search for required Internal User Supervisor user \"(.*)\"$")
	public void search_For_Internal_User3_For_Ipo(String user) {
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
	@Then("^For Ipo rejection and approval of response scenario Logging in as Internal user and verifying Supervisor user \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile3_For_Ipo(String user) {
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
	@Then("^For Ipo rejection and approval of response scenario Select required service item with Supervisor user to approve$")
	public void Select_Required_Service_item2_For_Ipo() {
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
	@Then("^For Ipo rejection and approval of response scenario Approve the selected service request$")
	public void Approve_Selected_Service_Rqst_For_Ipo() {
		try {
			approveServiceRqst();
			testReporter.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service request/response");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^For Ipo rejection and approval of response scenario Validate approved request response status changed to sent$")
	public void Validate_Approved_Rqst_For_Ipo() {
		try {
			validateExistingSINewResponseStatus("Sent");
			testReporter.log(LogStatus.PASS, "Validate the approved request response");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the approved request response");
			getResult_For_Ipo();
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
	@Then("^Stop Report Generation for current scenario For Ipo rejection and approval of response scenario$")
	public void getResult_For_Ipo() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For Ipo rejection and approval of response scenario$")
	public void flushReporter_For_Ipo() {
		driver.close();
		driver.quit();
	}
//***********************************************************Main Functions**************************************************************
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
	public void createNewResponseForIpo() throws AWTException {
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
		//element = driver.findElement(By.xpath("//span[contains(text(),'Is selected?')]/parent::label")); //Intentionally kept for future change.
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
	public void validateExistingSINewResponseStatus(String status) {
		int numberOfRows = 0;
		try {
		if (status == "Sent") {
			fetchCorrectIframe(By.xpath("//div[@aria-label='Compose text']/p[1]"));
			element = driver.findElement(By.xpath("//a[text()='View Response']"));
			scrollingFunction();
			Utils.sleep(2);
			element.click();
			Utils.sleep(4);
			driver.switchTo().defaultContent();
		}
		} catch (Exception e) {
			
		}
		for (int itr = 0; itr<4;itr++){
		try {
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			Utils.sleep(4);
			try {
				ele = By.xpath(".//span[@class='tabText' and text()='Details']");
				fluentWaitForElementVisibility();
				ele = By.xpath(".//span[@class='tabText' and text()='New Response']");
				driver.findElement(ele).click();
				Utils.sleep(2);
			} catch (Exception e) {
				ele = By.xpath(".//span[@class='tabText' and text()='"+newSINo+"']");
				driver.findElement(ele).click();
				Utils.sleep(2);
			}
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		numberOfRows = driver.findElements(By.xpath("//th[text()='Response Number']/parent::tr/following-sibling::tr")).size();
		numberOfRows = numberOfRows + 1;
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']"));
		highlightElement();
		if (status == "Sent") {
			element = driver.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
			scrollingFunction();
			element = driver.findElement(By.xpath("//th[text()='Sent']"));
			highlightElement();
		}
		break;
		} catch (Exception e) {
			System.out.println("Iterator :"+itr);
		}
		}
		element = driver.findElement(By.xpath("//tr["+numberOfRows+"]/td[text()='"+status+"']"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void setPercentageForIpo(String percentageValue) throws AWTException {
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
	public void selectRequiredDropdownlistForIpo() {
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
		System.out.println("Clicked on an Existing service item :"+newSINo);
		Utils.sleep(4);
		driver.switchTo().defaultContent();
	}
//***************************************************************************************************************************************
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
	//Intentionally kept for future testing purpose.
	/*public static void main(String[] args) throws AWTException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		HD_ISO_VSC_New_Service_Response_Rejection_Flow ref = new HD_ISO_VSC_New_Service_Response_Rejection_Flow(driver);
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
		Thread.sleep(5000);
		ref.searchHDISOVSCitems("IPO CSR");
		ref.logInAsInternalUser("IPO CSR");
		ref.openRandomSIFromOpenItems();
		ref.createNewResponseForIpo();
		ref.validateExistingSINewResponseStatus("Under Review");
	}*/
}
