package com.salesforcetest.stepdefinition.aao;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.aao.ApplicationDetail;
import com.salesforcetest.pages.aao.Applications;
import com.salesforcetest.pages.aao.Home;
import com.salesforcetest.pages.aao.LogIn;
import com.salesforcetest.pages.aao.QualityControlPercentage;
import com.salesforcetest.pages.aao.ResponseBuilder;
import com.salesforcetest.pages.aao.ResponseDetail;
import com.salesforcetest.pages.aao.ServiceItem;
import com.salesforcetest.pages.aao.ServiceItemDetail;
import com.salesforcetest.pages.aao.ServiceItemEdit;
import com.salesforcetest.pages.aao.UserActions;
import com.salesforcetest.pages.aao.manager.DriverManager;
import com.salesforcetest.pages.aao.manager.PageManager;
import com.salesforcetest.pages.aao.utils.AaoHelper;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AAO_Response_Approval_QC_0 {
	
	public PageManager page;
	public static  LogIn loginpage;
	public static WebDriver driver;
	public DriverManager manager;
	public  static UserActions useractionspage;
	public  static Home homepage;
	public  static Applications apppage;
	public   static ApplicationDetail detailpage;
	public  static ServiceItem sipage;
	public static ServiceItemDetail sidetailpage;
	public String receiptno;
	public static String serviceitemno;
	public static String Responseno;
	public static ServiceItemEdit editpage;
	public static QualityControlPercentage percentage;
	public static ResponseBuilder builder;
	public static ResponseDetail respdetail;
	public AaoHelper helper;
	static ExtentReports extent;
	public static String newSINo, emailLink, subjectLine, responseNumber,screenShotPath;
	static ExtentTest testReporter;
	String workingDir = System.getProperty("user.dir");
	
	@Given("^For Auto Response Approval Scenario set the initial conditions$")
	public void for_Auto_Response_Approval_Scenario_set_the_initial_conditions() throws Throwable {
manager=new DriverManager().init();
		
		driver=manager.getDrvr();

         manager.Navigate();
      
		page=new PageManager(driver).init();
      
        
       
		loginpage=page.getLogInPage();
	   
	  useractionspage=page.getUserActionsPage();
	 
	  homepage=page.getHomePage();
	  
	 apppage=page.getApplicationPage();  
	 
	 detailpage=page.getApplicationDetailPage();
	 
	  sipage=page.getServiceItemPage();
	
	  sidetailpage=page.getServiceItemDetailPage();
	  percentage=page.getPercentage();
	  builder=page.getBuilder();
	  respdetail=page.getResponseDetail();
	  loginpage.AaoLogin();
	  
	   useractionspage.navigateToHomePage();
	}

	@When("^For Auto Response Approval Scenario  Registered user is logged in as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Registered_user_is_logged_in_as(String arg1) throws Throwable {
try {
			
			extent = new ExtentReports(workingDir+"\\test-report\\AAOResponseApproval_QC_0_Percent"+AaoHelper.randomDateTime()+".html", true);
				testReporter = extent.startTest("AAO_Auto_Response_Approval_QC_0_Percent");
				
				
				 
			 homepage.clickManageUsers();
			   homepage.clickUsers();
			   homepage.selectAllAaoUsers();
			   homepage.searchAaoUser(arg1);
			   useractionspage.clickLogInForAaoUser();
			   testReporter.log(LogStatus.PASS, "Verifying Registered User is Logged in as Aao SuperUser:");
		}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "Verifying Registered User is Logged in as Aao Super User::");
				screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
				}
	}

	@Then("^For Auto Response Approval Scenario Superuser creates an application with Filetype as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Superuser_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
		try {
			 apppage.createApplication(arg1);
			 receiptno=detailpage.getReceiptNumber();
			 testReporter.log(LogStatus.PASS, "Creating an application");
			}
		 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating an application:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}
			   
	}

	@And("^For Auto Response Approval Scenario clicks to create \"([^\"]*)\" from application detail$")
	public void for_Auto_Response_Approval_Scenario_clicks_to_create_from_application_detail(String arg1) throws Throwable {
		try {
			   detailpage.clickCreateServiceItem();
			   sipage.selectRecordType(arg1);
			   sipage.clickContinue();
			   testReporter.log(LogStatus.PASS, "Clicking create service item on the application detail page");
				}
			 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Clicking create service item on the application detail page");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
			}
	}

	@And("^For Auto Response Approval Scenario creates serviceitem with the following data$")
	public void for_Auto_Response_Approval_Scenario_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
		List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
		
		String itemtype = list.get(0).get("Item Type");
		String office = list.get(0).get("Office");
		
		String officercmts = list.get(0).get("Officer Comments");
		String supervisorcmts = list.get(0).get("Supervisor Comments");
		String supervisor = list.get(0).get("Supervisor");
		String editor = list.get(0).get("Editor");
		String contact = list.get(0).get("Contact");
		String account = list.get(0).get("Account");
		String business=list.get(0).get("Business");
		String attorney = list.get(0).get("Attorney");
		String internalcontact=list.get(0).get("Internal Contact");
		if(internalcontact.isEmpty())
		
			internalcontact="";
    try {
       sipage.fillServiceItemInfo(itemtype,"Office",office);
  //  sipage.clickSave();
 // serviceitemno=sidetailpage.getServiceItemNumber();
  	//	System.out.println(serviceitemno);
  		 testReporter.log(LogStatus.PASS, "Provide Item Type,Office :"+itemtype+" ,"+office);
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Item Type,Office :"+itemtype+" ,"+office);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}

    try {
    	sipage.fillStatusInfo(officercmts, supervisorcmts);
    	 testReporter.log(LogStatus.PASS, "Provide Officer Comments,Supervisor Comments :"+officercmts+" ,"+supervisorcmts);
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Officer Comments,Supervisor Comments :"+officercmts+" ,"+supervisorcmts);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
    
    try {
    	sipage.fillRespPartyInfo("Supervisor", supervisor, "Editor", editor);
    	 testReporter.log(LogStatus.PASS, "Provide Supervisor,Editor :"+supervisor+" ,"+editor);
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide Supervisor,Editor :"+supervisor+" ,"+editor);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
    
    try {
    	sipage.fillCustomerInfo("Contact", contact, "Account", account,business,attorney);
    	 testReporter.log(LogStatus.PASS, "Provide contact,account,business,attorney :"+contact+" ,"+account+" ,"+business+","+attorney);
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide contact,account,business,attorney :"+contact+" ,"+account+" ,"+business+","+attorney);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
    
    try {
    	if (internalcontact!="")
    	{
    	sipage.fillSystemInfo("Internal Contact", internalcontact);
    	}
    	sipage.clickSave();
    	 serviceitemno=sidetailpage.getServiceItemNumber();
 	  		System.out.println(serviceitemno);
    	 testReporter.log(LogStatus.PASS, "Provide internalcontact :"+internalcontact );
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Provide internalcontact :"+internalcontact);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
	}

	@And("^For Auto Response Approval Scenario sets qa percentage of officer to \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_sets_qa_percentage_of_officer_to(String arg1) throws Throwable {
try {
			
			percentage.setPercentageOfficer(arg1);
			testReporter.log(LogStatus.PASS, "Set qc percentage of officer to 0");
		}
		catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set qc percentge of officer to 0");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
	}

	@When("^For Auto Response Approval Scenario user is logged in as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_user_is_logged_in_as(String arg1) throws Throwable {
		try {
			useractionspage.goBack();
			useractionspage.goBack();
			loginpage.Aaologout();
			homepage.clickManageUsers();
			homepage.clickUsers();
			homepage.selectAllAaoUsers();
			homepage.searchAaoUser(arg1);
			useractionspage.clickLogInForAaoUser();
			 testReporter.log(LogStatus.PASS, "log out and verify user is logged in as AAO Officer");
			
		}catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "log out and verify user is logged in as AAO Officer");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}  
	}

	@Then("^For Auto Response Approval Scenario create a new response for the service item$")
	public void for_Auto_Response_Approval_Scenario_create_a_new_response_for_the_service_item() throws Throwable {
	    try {
	    	useractionspage.findServiceItem(serviceitemno);
	    	useractionspage.clickServiceItem(serviceitemno);
	    	builder.createResponse();
	    	 testReporter.log(LogStatus.PASS, "Create new response for the service item");
	    }catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Create new response for the service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}  
	}

	@And("^For Auto Response Approval Scenario Verify Status as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Verify_Status_as(String arg1) throws Throwable {
		try {
			Assert.assertTrue(respdetail.verifyStatus(arg1));
		    
		    testReporter.log(LogStatus.PASS, "Verify Status");
			}  catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify status");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@And("^For Auto Response Approval Scenario Verify Owner as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Verify_Owner_as(String arg1) throws Throwable {
		try {
			Assert.assertTrue(respdetail.verifyOwner(arg1));
		    
		    testReporter.log(LogStatus.PASS, "Verify Owner");
			}  catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify Owner");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	@And("^For Auto Response Approval Scenario Verify \"([^\"]*)\" Response version is created$")
	public void for_Auto_Response_Approval_Scenario_Verify_Response_version_is_created(String arg1) throws Throwable {
		  try {
		    	respdetail.clickServiceItem();
		    	sidetailpage.goToResponse();
		    	Responseno=respdetail.getResponseNumber();
		    	boolean b=respdetail.verifyResponseVersions(arg1);
		    	Assert.assertTrue(b);
		    	testReporter.log(LogStatus.PASS, "Verify response version is created");
		    }catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify response version is created");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@When("^For Auto Response Approval Scenario user signed in as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_user_signed_in_as(String arg1) throws Throwable {
		try {
			loginpage.Aaologout();
			homepage.clickManageUsers();
			homepage.clickUsers();
			homepage.selectAllAaoUsers();
			homepage.searchAaoUser(arg1);
			useractionspage.clickLogInForAaoUser();
			 testReporter.log(LogStatus.PASS, "Log out as AAO Officer and verifying user is signed in as AAO File Clerk");
			
		}catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log out as AAO offcier and verifying user is signed in as AAO File Clerk");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}  
	}

	@Then("^For Auto Response Approval Scenario access the service item created in the previous scenario and edit response submitted by AAO Officer$")
	public void for_Auto_Response_Approval_Scenario_access_the_service_item_created_in_the_previous_scenario_and_edit_response_submitted_by_AAO_Officer() throws Throwable {
		  try{
			  useractionspage.findServiceItem(serviceitemno);
			    useractionspage.clickServiceItem(serviceitemno);
			    sidetailpage.goToResponse();
			   respdetail.editResponse();

			   testReporter.log(LogStatus.PASS, "access the service item created in the previous scenario and edit response submitted by AAO Officer");
				
			}catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "access the service item created in the previous scenario and edit response submitted by AAO Officer");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
			}  
	}

	@Then("^For Auto Response Approval Scenario Approve the submitted Response$")
	public void for_Auto_Response_Approval_Scenario_Approve_the_submitted_Response() throws Throwable {
		 try {
		    	builder.approveResponse();
		    	   testReporter.log(LogStatus.PASS, "AAO File Clerk approves the response");
					
		   		}catch (Exception e) {
		    testReporter.log(LogStatus.FAIL, "AAO File Clerk approves the response");
		    					screenShotPath = GetScreenShot.capture(driver);
		    					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		    					e.printStackTrace();
		    					extent.endTest(testReporter);
		    					extent.flush();
		    					extent.close();
		    					Assert.assertTrue(false);
		    				}  
	}

	@Then("^For Auto Response Approval Scenario Verify status is updated to \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Verify_status_is_updated_to(String arg1) throws Throwable {
		try {
			Assert.assertTrue(respdetail.verifyStatus(arg1));
		    
		    testReporter.log(LogStatus.PASS, "Verify Status is updated to aaproved");
			}  catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify status is updated to approved");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@Then("^For Auto Response Approval Scenario Verify  Owner is updated to \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Verify_Owner_is_updated_to(String arg1) throws Throwable {
		try {
			Assert.assertTrue(respdetail.verifyOwner(arg1));
		    
		    testReporter.log(LogStatus.PASS, "Verify Owner is updated to AAO Officer");
			}  catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify Owner is updated to AAO Officer");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@Then("^For Auto Response Approval Scenario Verify new Response Event is created with decision as \"([^\"]*)\" and owner as \"([^\"]*)\"$")
	public void for_Auto_Response_Approval_Scenario_Verify_new_Response_Event_is_created_with_decision_as_and_owner_as(String arg1, String arg2) throws Throwable {
		try {
			Assert.assertTrue(respdetail.verifyResponseEvents(arg1,arg2));
			testReporter.log(LogStatus.PASS, "Verify Response event is created with decision as Approve and owner as AAo File Clerk");
		}  catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify Response Evenet is created with decision as Approve and owner as AAO File Clerk");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
	}

	@Then("^For Auto Response Approval Scenario log out$")
	public void for_Auto_Response_Approval_Scenario_log_out() throws Throwable {
		loginpage.Aaologout();
	}

	@Then("^For Auto Response Approval Scenario Stop Report Generation for current scenario AAO End To End$")
	public void for_Auto_Response_Approval_Scenario_Stop_Report_Generation_for_current_scenario_AAO_End_To_End() throws Throwable {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}

	@Then("^For Auto Response Approval Scenario close the browser$")
	public void for_Auto_Response_Approval_Scenario_close_the_browser() throws Throwable {
		driver.close();
	    driver.quit();
	}



}
