package com.salesforcetest.stepdefinition.aao;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.aao.Accounts;
import com.salesforcetest.pages.aao.ApplicationDetail;
import com.salesforcetest.pages.aao.Applications;
import com.salesforcetest.pages.aao.Hold;
import com.salesforcetest.pages.aao.Home;
import com.salesforcetest.pages.aao.LogIn;
import com.salesforcetest.pages.aao.ServiceItem;
import com.salesforcetest.pages.aao.ServiceItemDetail;
import com.salesforcetest.pages.aao.ServiceItemEdit;
import com.salesforcetest.pages.aao.TrackingRecord;
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

public class AAO_Revert_Closed_ServiceItem {

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
	public static TrackingRecord rec;
	public static Hold hld;
	public static Accounts accts;
	public static String receiptno,fileno;
	public static String serviceitemno;
	public static ServiceItemEdit editpage;
	public AaoHelper helper;
	static ExtentReports extent;
	public static String newSINo, emailLink, subjectLine, responseNumber,screenShotPath;
	static ExtentTest testReporter;
	String workingDir = System.getProperty("user.dir");

	
	@Given("^For AAO Close To Open Scenario set the initial conditions$")
	public void for_AAO_Close_To_Open_Scenario_set_the_initial_conditions() throws Throwable {
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
	  rec=page.getTrackingRecord();
	  hld=page.getHold();
	  accts=page.getAccount();
	  editpage=page.getEdit();
	 
	
	 
	   loginpage.AaoLogin();
	  
	   useractionspage.navigateToHomePage();
	}
	
@When("^For AAO Close To Open scenario  Registered user is logged in as \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_scenario_Registered_user_is_logged_in_as(String arg1) throws Throwable {
	try {
		
		extent = new ExtentReports(workingDir+"\\test-report\\AAORevertClosedServiceItem"+AaoHelper.randomDateTime()+".html", true);
			testReporter = extent.startTest("AAO_Revert_Closed_ServiceItem");
			
			
			 
		 homepage.clickManageUsers();
		   homepage.clickUsers();
		   homepage.selectAllAaoUsers();
		   homepage.searchAaoUser(arg1);
		   useractionspage.clickLogInForAaoUser();
		   testReporter.log(LogStatus.PASS, "Verifying Registered User is Logged in as Super User:");
	}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verifying Registered User is Logged in as Super User::");
			screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
			}
	}


@Then("^For AAO Close To Open scenario Superuser creates an application with Filetype as \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_scenario_Superuser_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
	try {
		 apppage.createApplication(arg1);
		 receiptno=detailpage.getReceiptNumber();
		 fileno=detailpage.getFileNumber();
		 System.out.println(fileno);
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

@And("^For AAO Close To Open scenario clicks to create \"([^\"]*)\" from application detail$")
public void for_AAO_Close_To_Open_scenario_clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

@And("^For AAO Close To Open scenario creates serviceitem with the following data$")
public void for_AAO_Close_To_Open_scenario_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
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


@Then("^For AAO Close To Open Scenario update Final Decision to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_update_Final_Decision_to(String arg1) throws Throwable {
	try {

	sidetailpage.setFinalDecision(arg1);
	 testReporter.log(LogStatus.PASS, "Update Final Decision");
	}
 catch (Exception e) {
	testReporter.log(LogStatus.FAIL, "Update Final Decision");
	screenShotPath = GetScreenShot.capture(driver);
	testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	e.printStackTrace();
	extent.endTest(testReporter);
	extent.flush();
	extent.close();
	Assert.assertTrue(false);
}
}

@And("^For AAO Close To Open Scenario update responsible party to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_update_responsible_party_to(String arg1) throws Throwable {
   try {
	   sidetailpage.selectRespParty(arg1);
		 
		  
		  testReporter.log(LogStatus.PASS, "update responsible party to issued decisions queue");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "update responsible party to issued decisions queue");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
   
}

@And("^For AAO Close To Open Scenario verify status and milestone is updated to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_verify_status_and_milestone_is_updated_to(String arg1) throws Throwable {
	try {
		  Assert.assertTrue(sidetailpage.verifyStatus(arg1));
		  Assert.assertTrue(sidetailpage.verifyMileStone(arg1));
		  testReporter.log(LogStatus.PASS, "verify status and milestone is updated to Issued");
		}catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "verify status and milestone is updated to Issued");
			screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}  
}

@Then("^For AAO Close To Open Scenario change the record type to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_change_the_record_type_to(String arg1) throws Throwable {
	 try {
	    	
	    	sidetailpage.clickChangeRecord();
	    	sipage.selectRecordType(arg1);
	    	sipage.clickContinue();
	    	
	   	 testReporter.log(LogStatus.PASS, "change the record type of the closed service item to AAO Service Item ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the record type of closed service item to AAO Service Item");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
	    
}

@And("^For AAO Close To Open Scenario  update status to \"([^\"]*)\" on the Service Item Edit Page$")
public void for_AAO_Close_To_Open_Scenario_update_status_to_on_the_Service_Item_Edit_Page(String arg1) throws Throwable {
    try {
    	editpage.selectStatus(arg1);
    	 testReporter.log(LogStatus.PASS, "Update status to pending");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Update status to pending");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	
}

@And("^For AAO Close To Open Scenario update Responsible Party to \"([^\"]*)\" on the Service Item Edit page and click save$")
public void for_AAO_Close_To_Open_Scenario_update_Responsible_Party_to_on_the_Service_Item_Edit_page_and_click_save(String arg1) throws Throwable {
    try {
    	editpage.selectParty(arg1);
    	
    	testReporter.log(LogStatus.PASS, "Update Responsible Party to Triage queue");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Update Responsible Party to Triage Queue");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	
}

@Then("^For AAO Close To Open Scenario issue the Service item again by updating responsible party to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_issue_the_Service_item_again_by_updating_responsible_party_to(String arg1) throws Throwable {
   try {
	   sidetailpage.selectRespParty(arg1);
	   testReporter.log(LogStatus.PASS, "Update Responsible Party to Issued queue");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Update Responsible Party to Issued Queue");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	
   
}

@Then("^For AAO Close To Open Scenario update the record type to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_update_the_record_type_to(String arg1) throws Throwable {
	 try {
	    	
	    	sidetailpage.clickChangeRecord();
	    	sipage.selectRecordType(arg1);
	    	sipage.clickContinue();
	    	
	   	 testReporter.log(LogStatus.PASS, "change the record type of the closed service item to File Request ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the record type of closed service item to File Request");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
}

@And("^For AAO Close To Open Scenario  update the status to \"([^\"]*)\" on the Service Item Edit Page$")
public void for_AAO_Close_To_Open_Scenario_update_the_status_to_on_the_Service_Item_Edit_Page(String arg1) throws Throwable {
	 try {
	    	editpage.editFileRequestStatus(arg1);
	    	 testReporter.log(LogStatus.PASS, "change the status to completed ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the status to completed");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
    
}

@Then("^For AAO Close To Open Scenario verify status as \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_verify_status_as(String arg1) throws Throwable {
   try {
	   editpage.verifyFileRequestStatus(arg1);
		 testReporter.log(LogStatus.PASS, "Verify the status to completed ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify the status to completed");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
	   
	   
   
}





@Then("^For AAO Close To Open Scenario Logoff and login as \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_Logoff_and_login_as(String arg1) throws Throwable {
	try {
		
	loginpage.Aaologout();
	homepage.clickManageUsers();
	homepage.clickUsers();
	homepage.selectAllAaoUsers();
	homepage.searchAaoUser(arg1);
	useractionspage.clickLogInForAaoUser();
	 testReporter.log(LogStatus.PASS, "log out and verify user is logged in as Derrick Dantzler");
	
}catch (Exception e) {
	testReporter.log(LogStatus.FAIL, "log out and verify user is logged in as Derrick Dantzler");
	screenShotPath = GetScreenShot.capture(driver);
	testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	e.printStackTrace();
	extent.endTest(testReporter);
	extent.flush();
	extent.close();
	Assert.assertTrue(false);
}  
}


@Then("^For AAO Close To Open scenario File Clerk creates an application with Filetype as \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_scenario_File_Clerk_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
	try {
		 apppage.createApplication(arg1);
		 receiptno=detailpage.getReceiptNumber();
		 fileno=detailpage.getFileNumber();
		 System.out.println(fileno);
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

@Then("^For AAO Close To Open scenario File Clerk clicks to create \"([^\"]*)\" from application detail$")
public void for_AAO_Close_To_Open_scenario_File_Clerk_clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

@Then("^For AAO Close To Open scenario File Clerk creates serviceitem with the following data$")
public void for_AAO_Close_To_Open_scenario_File_Clerk_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
	
	String itemtype = list.get(0).get("Item Type");
	String office = list.get(0).get("Office");
	
	String officercmts = list.get(0).get("Officer Comments");
	String supervisorcmts = list.get(0).get("Supervisor Comments");
	String supervisor = list.get(0).get("Supervisor");
	String editor = list.get(0).get("Editor");
	String contact = list.get(0).get("Contact");
	String business=list.get(0).get("Business");
	String account = list.get(0).get("Account");
	String attorney = list.get(0).get("Attorney");
	String internalcontact=list.get(0).get("Internal Contact");
	if(internalcontact.isEmpty())
	
		internalcontact="";
try {
   sipage.fillServiceItemInfo(itemtype,"Office",office);
//  sipage.clickSave();
// serviceitemno=sidetailpage.getServiceItemNumber();
	//	System.out.println(serviceitemno);
		 testReporter.log(LogStatus.PASS, "Provide Item Type,Office:"+itemtype+" ,"+office);
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

@Then("^For AAO Close To Open Scenario FileClerk update Final Decision to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_Final_Decision_to(String arg1) throws Throwable {
	try {

		sidetailpage.setFinalDecision(arg1);
		 testReporter.log(LogStatus.PASS, "Update Final Decision");
		}
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Update Final Decision");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	}
}

@And("^For AAO Close To Open Scenario  FileClerk update responsible party to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_responsible_party_to(String arg1) throws Throwable {
	 try {
		   sidetailpage.selectRespParty(arg1);
			 
			  
			  testReporter.log(LogStatus.PASS, "update responsible party to issued decisions queue");
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "update responsible party to issued decisions queue");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
}

@Then("^For AAO Close To Open Scenario FileClerk verify status and milestone is updated to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_verify_status_and_milestone_is_updated_to(String arg1) throws Throwable {
	try {
	  Assert.assertTrue(sidetailpage.verifyStatus(arg1));
	  Assert.assertTrue(sidetailpage.verifyMileStone(arg1));
	  testReporter.log(LogStatus.PASS, "verify status and milestone is updated to Issued");
	}catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "verify status and milestone is updated to Issued");
		screenShotPath = GetScreenShot.capture(driver);
	testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	}  
}

@Then("^For AAO Close To Open Scenario FileClerk change the record type to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_change_the_record_type_to(String arg1) throws Throwable {
	 try {
	    	
	    	sidetailpage.clickChangeRecord();
	    	sipage.selectRecordType(arg1);
	    	sipage.clickContinue();
	    	
	   	 testReporter.log(LogStatus.PASS, "change the record type of the closed service item to AAO Service Item ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the record type of closed service item to AAO Service Item");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
}

@And("^For AAO Close To Open Scenario FileClerk update status to \"([^\"]*)\" on the Service Item Edit Page$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_status_to_on_the_Service_Item_Edit_Page(String arg1) throws Throwable {
	 try {
	    	editpage.selectStatus(arg1);
	    	 testReporter.log(LogStatus.PASS, "change the status to pending ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the status to pending");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
}

@And("^For AAO Close To Open Scenario FileClerk update Responsible Party to \"([^\"]*)\" on the Service Item Edit page and click save$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_Responsible_Party_to_on_the_Service_Item_Edit_page_and_click_save(String arg1) throws Throwable {
	try {
    	editpage.selectParty(arg1);
    	
    	testReporter.log(LogStatus.PASS, "Update Responsible Party to Triage queue");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Update Responsible Party to Triage Queue");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	
}

@Then("^For AAO Close To Open Scenario error message is displayed and go to the service item detail page by clicking cancel$")
public void for_AAO_Close_To_Open_Scenario_error_message_is_displayed_and_go_to_the_service_item_detail_page_by_clicking_cancel() throws Throwable {
    try {
    	boolean b=editpage.verifyError();
    	 Assert.assertTrue(b);
    	 editpage.clickCancel();
    	 testReporter.log(LogStatus.PASS, "Verify error message is displayed when file clerk  changes record to AAO Service item");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify error message is displayed when file clerk  changes record to AAO Service item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	
}

@Then("^For AAO Close To Open Scenario FileClerk update the record type to \"([^\"]*)\"$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_the_record_type_to(String arg1) throws Throwable {
	 try {
	    	
	    	sidetailpage.clickChangeRecord();
	    	sipage.selectRecordType(arg1);
	    	sipage.clickContinue();
	    	
	   	 testReporter.log(LogStatus.PASS, "change the record type of the closed service item to File Request ");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "change the record type of closed service item to File Request");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
	    
}

@And("^For AAO Close To Open Scenario FileClerk update the status to \"([^\"]*)\" on the Service Item Edit Page$")
public void for_AAO_Close_To_Open_Scenario_FileClerk_update_the_status_to_on_the_Service_Item_Edit_Page(String arg1) throws Throwable {
	try {
    	editpage.editFileRequestStatus(arg1);
    	 testReporter.log(LogStatus.PASS, "change the status to completed ");
	}
	 catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "change the status to completed");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}	

}

@And("^For AAO Close To Open Scenario error message is displayed$")
public void for_AAO_Close_To_Open_Scenario_error_message_is_displayed() throws Throwable {
	 try {
	    	boolean b=editpage.verifyError();
	    	 Assert.assertTrue(b);
	    	 editpage.clickCancel();
	    	 testReporter.log(LogStatus.PASS, "Verify error message is displayed when file clerk  changes record to File Request");
	 }
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify error message is displayed when file clerk  changes record to File Request");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}	
}

@Then("^For AAO Close To Open Scenario log out$")
public void for_AAO_Close_To_Open_Scenario_log_out() throws Throwable {
	loginpage.Aaologout();
}

@And("^For Auto Close To Open Scenario Stop Report Generation for current scenario AAO End To End$")
public void for_Auto_Close_To_Open_Scenario_Stop_Report_Generation_for_current_scenario_AAO_End_To_End() throws Throwable {
	extent.endTest(testReporter);
	extent.flush();
	extent.close();
}

@And("^For Auto Close To Open Scenario close the browser$")
public void for_Auto_Close_To_Open_Scenario_close_the_browser() throws Throwable {
	driver.close();
    driver.quit();
}


}


