package com.salesforcetest.main.uatg;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.uatg.CustomerSearchAction;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.pages.salesforce.uatg.RefreshExistingUserRecordAction;

import cucumber.api.java.en.Then;

public class HD_ISO_VSC_Refresh_Existing_User_Record {
	private WebDriver driver1;

	private ExtentTest testReporter1;
	private ExtentReports extent1;
	public static String screenShotPath;
	public HD_ISO_VSC_Refresh_Existing_User_Record(WebDriver driver1) {
		this.driver1 = driver1;
	}
	public void setTestReporter(ExtentTest testReporter1) {
		this.testReporter1 = testReporter1;
	}
	public void setTestReporter(ExtentReports extent1) {
		this.extent1 = extent1;
	}
	public void verifyLoggedInUserProfile() throws IOException {

		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.verifyProfile();
			testReporter1.log(LogStatus.PASS, "Verifying the current logged in user profile:");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Verifying the current logged in user profile:");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void logInAsInternalUser(String user) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.logInAsInternalUser(user);
			testReporter1.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void customerSearch(String customer) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.customerSearch(customer);
			testReporter1.log(LogStatus.PASS, "Search for required customer");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Search for required customer");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void Set_QC_Percentage(String val) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.searchHDISOVSCitems("One HD Supervisor");
			refObj.logInAsInternalUser("One HD Supervisor");
			refObj.setPercentage(val);
			refObj.currentUserLogOut();
			testReporter1.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void customerDataRefresh() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.customerRefresh();
			testReporter1.log(LogStatus.PASS, "Refreshing the input customer details and wait till refreshing mechanism is completed");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Refreshing the input customer details and wait till refreshing mechanism is completed");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void searchUser(String user) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.searchHDISOVSCitems(user);
			testReporter1.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void searfchForApplicationRecord(String applicationId) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.searchForApplicationRecordWithAppNo(applicationId);
			testReporter1.log(LogStatus.PASS, "Search with Application id in global search panel");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Search with Application id in global search panel");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void openRecordFromGlobalSearchResult(String applicationRecord2) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.openApplicationRecordFromGlobalSearchResultPanel(applicationRecord2);
			testReporter1.log(LogStatus.PASS, "Find and Open "+applicationRecord2+" record from Global Search result");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Find and Open "+applicationRecord2+" record from Global Search result");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void clickOnCreateNewItem() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.createNewServiceItem();
			testReporter1.log(LogStatus.PASS, "Click on Create new Service item button");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Click on Create new Service item button");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveOrgName(String orgName) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.setOrgName(orgName);
			testReporter1.log(LogStatus.PASS, "Give Organization Name : "+orgName);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Organization Name : "+orgName);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveEmailId(String email) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.setEmail(email);
			testReporter1.log(LogStatus.PASS, "Give Email Id: "+email);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Email Id: "+email);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveSenderType(String senderType) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.selectSenderType(senderType);
			testReporter1.log(LogStatus.PASS, "Give Sender Type Name : "+senderType);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Sender Type Name : "+senderType);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveSubjectAndDes() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.setRandomSubjectAndDesAndFormType();
			testReporter1.log(LogStatus.PASS, "Give Subject and Description Name.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Subject and Description Name.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveCategoryAndKind() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.setRandomCategoryAndKind();
			testReporter1.log(LogStatus.PASS, "Give Category and Kind Name.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Category and Kind Name.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveSIOrigin(String serviceItem) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.selectSIOrigin(serviceItem);
			testReporter1.log(LogStatus.PASS, "Give Service Item Origin :"+serviceItem);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Service Item Origin :"+serviceItem);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveSInitialQueue(String initialQueue) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.selectInitialQueue(initialQueue);
			testReporter1.log(LogStatus.PASS, "Give Initial Queue : "+initialQueue);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Initial Queue : "+initialQueue);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void giveReceivedDate(String receivedDate) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.setReceivedDate(receivedDate);
			testReporter1.log(LogStatus.PASS, "Give Received Date (any past date) : "+receivedDate);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Give Received Date (any past date) : "+receivedDate);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void saveNewServiceItem() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.clickOnSaveSI();
			testReporter1.log(LogStatus.PASS, "Saving this new service item data");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Saving this new service item data");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void validateNewlyCreatedSI() throws IOException {
		String newlyCreatedSI = null;
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			newlyCreatedSI = (String) refObj.fetchServiceItemNo();
			testReporter1.log(LogStatus.PASS, "Validate new service item and new Service Item number :"+newlyCreatedSI);
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Validate new service item and new Service Item number :"+newlyCreatedSI);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void createNewResponse() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.createNewResponse();
			testReporter1.log(LogStatus.PASS, "Creating new sevice response of new service item.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Creating new sevice response of new service item.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void verifySR() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.verifyNewResponseStatus();
			testReporter1.log(LogStatus.PASS, "Verify new sevice response status of new service item.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Verify new sevice response status of new service item.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void verifyUserCanChangeOwnerAsHDISOVSCuser() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.verifyTheChangeOwnerFunctionalityAsHDISOVSC();
			testReporter1.log(LogStatus.PASS, "Verify logging in as HD ISO VSC user can change the service item owner to queue VAWA_I918inquiriesNSC");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Verify logging in as HD ISO VSC user can change the service item owner to queue VAWA_I918inquiriesNSC");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void logOut() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.currentUserLogOut();
			testReporter1.log(LogStatus.PASS, "Logging out");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Logging out");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void searchWithSupervisorId(String user) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.searchHDISOVSCitems(user);
			testReporter1.log(LogStatus.PASS, "Search with One HD Supervisor");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Search with One HD Supervisor");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void loginWithSupervisorId(String user) throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.logInAsInternalUser(user);
			testReporter1.log(LogStatus.PASS, "Login with One HD Supervisor");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Login with One HD Supervisor");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void selectRequiredSI() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.selectRequiredDropdownlist();
			testReporter1.log(LogStatus.PASS, "Open the new service item");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Open the new service item");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void verifyUserCanChangeOwnerAsONEHDSUPERVISORuser() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.verifyTheChangeOwnerFunctionalityAsHDSupervisor();
			testReporter1.log(LogStatus.PASS, "Verify logging in as ONE HD SUPERVISOR user can change the service item owner to queue VAWA_ISOA");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Verify logging in as ONE HD SUPERVISOR user can change the service item owner to queue VAWA_ISOA");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void approveTheServiceRqst() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.approveServiceRqst();
			testReporter1.log(LogStatus.PASS, "Approve the service request/response");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Approve the service request/response");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void validateApproveRqstResponse() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.validateApprovedResponse();
			testReporter1.log(LogStatus.PASS, "Validate the approved request response");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Validate the approved request response");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
	public void closeRqstResponse() throws IOException {
		try {
			RefreshExistingUserRecordAction refObj = new RefreshExistingUserRecordAction(driver1);
			refObj.closureFunction();
			refObj.clickOnSaveSI();
			testReporter1.log(LogStatus.PASS, "Validate the Error message while change the status as close and close the service Item after giving correct values.");
		} catch (Exception e) {
			testReporter1.log(LogStatus.FAIL, "Validate the Error message while change the status as close and close the service Item after giving correct values.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter1.log(LogStatus.INFO, "Snapshot : " +testReporter1.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent1.endTest(testReporter1);
			extent1.flush();
			extent1.close();
			Assert.assertTrue(false);
		}
	}
}
