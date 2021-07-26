package com.salesforcetest.mapper.recr;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Customer_Search_Main;
import com.salesforcetest.main.uatg.HD_ISO_VSC_Refresh_Existing_User_Record;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HD_ISO_VSC_Existing_User_Refresh {
	private static WebDriver driver1;

	private HD_ISO_VSC_Refresh_Existing_User_Record main = null;
	String workingDir = System.getProperty("user.dir");
	private static ExtentReports extent1 ;

	private ExtentTest testReporter1;

	@Given("^Registered User is logged in with \"(.*)\"$")
	public void init(String user) {
		extent1 = new ExtentReports(workingDir+"\\test-report\\HDVSCscenarioDirectApprovalandClosure_"+randomDateTime1()+".html", true);
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver1 = new ChromeDriver();
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		main = new HD_ISO_VSC_Refresh_Existing_User_Record(driver1);
		testReporter1 = extent1.startTest("HD Approval and Close SI QC 100%");
		main.setTestReporter(testReporter1);
		main.setTestReporter(extent1);
		new SalesforceLogin(driver1).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	@Then("Set QC Percentage to \"(.*)\"$")
	public void set_QC_Percentage(String percentage) throws IOException {
		main.Set_QC_Percentage(percentage);
	}
	@Then("Verifying the current logged in user profile$")
	public void verify_Logged_In_User_Profile() throws IOException {
		main.verifyLoggedInUserProfile();
	}
	@When("^Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) throws IOException {
		main.searchUser(user);
	}
	@Then("^Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) throws IOException {
		main.logInAsInternalUser(user);
	}
	@When("^Search for customer \"(.*)\"$")
	public void search_customer(String customer) throws IOException {
		main.customerSearch(customer);
	}
	@Then("^Refresh the customer data and wait for Refresh successful message$")
	public void update_customer() throws IOException {
		main.customerDataRefresh();
	}
	@Then("^Global Search with Customer ID \"(.*)\"$")
	public void Global_Search_With_Customer_ID(String applicationId) throws IOException {
		main.searfchForApplicationRecord(applicationId);
	}
	@Then("^Open application record from search result \"(.*)\"$")
	public void Open_Application_Record(String applicationId) throws IOException {
		main.openRecordFromGlobalSearchResult(applicationId);
	}
	@Then("^Click on the new service item button$")
	public void Open_Application_Record() throws IOException {
		main.clickOnCreateNewItem();
	}
	@And("^Provide Organization name for new service item \"(.*)\"$")
	public void Enter_Org_Name(String orgName) throws IOException {
		main.giveOrgName(orgName);
	}
	@And("^Provide Email ID for new service item \"(.*)\"$")
	public void Enter_Email_ID(String email) throws IOException {
		main.giveEmailId(email);
	}
	@And("^Provide Sender type for new service item \"(.*)\"$")
	public void Enter_Sender_Type(String senderType) throws IOException {
		main.giveSenderType(senderType);
	}
	@And("^Provide Subject and Description for new service item$")
	public void Enter_Sub_Des() throws IOException {
		main.giveSubjectAndDes();
	}
	@And("^Provide Category and Kind for new service item$")
	public void Enter_Cat_Kind() throws IOException {
		main.giveCategoryAndKind();
	}
	@And("^Provide Service Item Origin for new service item \"(.*)\"$")
	public void Enter_SI_Origin(String serviceItem) throws IOException {
		main.giveSIOrigin(serviceItem);
	}
	@And("^Provide Initial Queue for new service item \"(.*)\"$")
	public void Enter_Initial_Queue(String initialQueue) throws IOException {
		main.giveSInitialQueue(initialQueue);
	}
	@And("^Provide Receive Date for new service item \"(.*)\"$")
	public void Enter_Received_Date(String receivedDate) throws IOException {
		main.giveReceivedDate(receivedDate);
	}
	@Then("^Saving new service item$")
	public void Save_SI() throws IOException {
		main.saveNewServiceItem();
	}
	@Then("^Verify New Service Item number and Details$")
	public void Verify_New_SI() throws IOException {
		main.validateNewlyCreatedSI();
	}
	@Then("^Create a new response for new Service Item$")
	public void Create_New_Response() throws IOException {
		main.createNewResponse();
	}
	@Then("^Verify newly created service response status$")
	public void Verify_New_Service_Response() throws IOException {
		main.verifySR();
	}
	@Then("^Verify logging in as HD ISO VSC user can change the service item owner to queue VAWA_I918inquiriesNSC$")
	public void Verify_the_Owner_Change_HD_ISO_VSC() throws IOException {
		main.verifyUserCanChangeOwnerAsHDISOVSCuser();
	}
	@Then("^Logout from current profile$")
	public void log_Out() throws IOException {
		main.logOut();
	}
	@When("^Search for required Supervisor User \"(.*)\"$")
	public void search_For_Supervisor_User(String user) throws IOException {
		main.searchWithSupervisorId(user);
	}
	@Then("^Logging in as Supervisor user and verifying \"(.*)\"$")
	public void logging_In_As_Supervisor_User_And_Verify_Profile(String user) throws IOException {
		main.loginWithSupervisorId(user);
	}
	@Then("^Select required service item with Supervisor user to approve/reject$")
	public void Select_Required_Service_item() throws IOException {
		main.selectRequiredSI();
	}
	@Then("^Verify logging in as ONE HD SUPERVISOR user can change the service item owner to queue VAWA_ISOA$")
	public void Verify_the_Owner_Change_One_HD_Supervisor() throws IOException {
		main.verifyUserCanChangeOwnerAsONEHDSUPERVISORuser();
	}
	@Then("^Approve the selected service request$")
	public void Approve_Selected_Service_Rqst() throws IOException {
		main.approveTheServiceRqst();
	}
	@Then("^Validate approved request response$")
	public void Validate_Approved_Rqst() throws IOException {
		main.validateApproveRqstResponse();
	}
	@Then("^Validate the Error message while change the status as close and close the service Item after giving correct values$")
	public void Close_Approved_Rqst() throws IOException {
		main.closeRqstResponse();
	}
	@Then("^Stop Report Generation for current scenario$")
	public void getResult() {
		extent1.endTest(testReporter1);
		extent1.flush();
		extent1.close();
	}
	@Then("^Close the browser for current scenario$")
	public void flushReporter() {
		driver1.quit();
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
}
