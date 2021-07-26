package com.salesforcetest.stepdefinition.aao;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.aao.ApplicationDetail;
import com.salesforcetest.pages.aao.Applications;
import com.salesforcetest.pages.aao.Home;
import com.salesforcetest.pages.aao.LogIn;
import com.salesforcetest.pages.aao.MonthlyGoals;
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

public class AAO_Monthly_Goals_Flow {
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
	public static String weight,title;
	public static ServiceItemEdit editpage;
	public static MonthlyGoals goals;
	public AaoHelper helper;
	static ExtentReports extent;
	public static String newSINo, emailLink, subjectLine, responseNumber,screenShotPath;
	static ExtentTest testReporter;
	String workingDir = System.getProperty("user.dir");
	
	@Given("^For Monthly Goals Flow  set the initial conditions$")
	public void for_Monthly_Goals_Flow_set_the_initial_conditions() throws Throwable {
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
	 editpage=page.getEdit();
	  goals=page.getMonthlyGoals();
	  loginpage.AaoLogin();
	  
	   useractionspage.navigateToHomePage();
	}

	@When("^For Monthly Goals Flow  Registered user is logged in as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_Registered_user_is_logged_in_as(String arg1) throws Throwable {
try {
			
			extent = new ExtentReports(workingDir+"\\test-report\\AAOResponseApproval"+AaoHelper.randomDateTime()+".html", true);
				testReporter = extent.startTest("AAO_Monthly_Goals");
				
				
				 
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
	

	@Then("^For Monthly Goals Flow open Monthly Goals in a separate tab  for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_open_Monthly_Goals_in_a_separate_tab_for(String arg1) throws Throwable {
	    
try {
		   useractionspage.goToMonthlyGoals();
		 //  goals.getInitialActual(arg1);
		   AaoHelper.switch_to_tab(driver, 0);
		   testReporter.log(LogStatus.PASS, "open monthly goals in a separate tab");
		 		}
		 		 catch (Exception e) {
		 				testReporter.log(LogStatus.FAIL, "open monthly goals in a separate tab");
		 				screenShotPath = GetScreenShot.capture(driver);
		 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		 				e.printStackTrace();
		 				extent.endTest(testReporter);
		 				extent.flush();
		 				extent.close();
		 				Assert.assertTrue(false);
		 	}
}
	
	@And("^For Monthly Goals Flow set  Title for \"([^\"]*)\" as \"([^\"]*)\" and Form weight for \"([^\"]*)\" as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_set_Title_for_as_and_Form_weight_for_as(String arg1, String arg2, String arg3, String arg4) throws Throwable {
	  try {
		  useractionspage.goToContactAndSetTitle(arg1, arg2);
		  useractionspage.goToFormSetWeight(arg3, arg4);
		  testReporter.log(LogStatus.PASS, "Set title for file clerk and form weight");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Set title for file clerk and form weight");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}

	}
	@And("^For Monthly Goals Flow update Title for \"([^\"]*)\" as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_update_Title_for_as(String arg1, String arg2) throws Throwable {
		 try {
			  useractionspage.goToContactAndSetTitle(arg1, arg2);
			  AaoHelper.sleep(4);
			  title=useractionspage.getTitle();
			  System.out.println(title);
			  testReporter.log(LogStatus.PASS, "Set title for officer");
			  	
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "Set title for officer");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}

	}



	@And("^For Monthly Goals Flow Superuser creates an application with Filetype as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_Superuser_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
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

	@And("^For Monthly Goals Flow clicks to create \"([^\"]*)\" from application detail$")
	public void for_Monthly_Goals_Flow_clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

	@And("^For Monthly Goals Flow creates serviceitem with the following data$")
	public void for_Monthly_Goals_Flow_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
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
			testReporter.log(LogStatus.FAIL, "Provide contact,account,attorney :"+contact+" ,"+account+" ,"+business+","+attorney);
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
	@Then("^For Monthly Goals Flow for the service item select internal contact as \"([^\"]*)\" and final decision as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_for_the_service_item_select_internal_contact_as_and_final_decision_as(String arg1, String arg2) throws Throwable {
	    try {
	    	sidetailpage.selectInternalContact();
	    	sidetailpage.lookUpContact(arg1);
	    	sidetailpage.setFinalDecision(arg2);
	    	
	    	testReporter.log(LogStatus.PASS, "select internal contact and select final decision");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "select internal contact and select final decision");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	    }

	@Then("^For Monthly Goals Flow update Responsible Party to \"([^\"]*)\" and save$")
	public void for_Monthly_Goals_Flow_update_Responsible_Party_to_and_save(String arg1) throws Throwable {
	   

		try {
		
			  sidetailpage.selectRespParty(arg1);
			
			 if((title.equalsIgnoreCase("AEO II"))| (title.equalsIgnoreCase("AEO III")))
				 weight="1";
			
			 else if(title.equals(""))
				 weight="0";
			 else
			  weight=sidetailpage.getWeight();
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

	
	@And("^For Monthly Goals Flow verify the monthly goals record is updated for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_verify_the_monthly_goals_record_is_updated_for(String arg1) throws Throwable {
	    try {
	    	boolean b=goals.verifyActual(arg1, weight);
	    	System.out.println(b);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for internal contact");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for internal contact");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}

	    @Then("^update the completion weight to \"([^\"]*)\"$")
	    public void update_the_completion_weight_to(String arg1) throws Throwable {
	        try {
	        	AaoHelper.switch_to_tab(driver, 0);
	        	sidetailpage.updateCompletionWeight(arg1);
	        	 if(title.equalsIgnoreCase("AEO II")| (title.equalsIgnoreCase("AEO III")))
					 weight="1";
				 else
	        	weight=sidetailpage.getWeight();
	        	System.out.println(weight);
	        	testReporter.log(LogStatus.PASS, "update completion weight");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "update completion weight");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	        
	    }

	    @And("^verify the weight is updated in the monthly goals record for \"([^\"]*)\"$")
	    public void verify_the_weight_is_updated_in_the_monthly_goals_record_for(String arg1) throws Throwable {
	    	try {
		    	boolean b=goals.verifyActual(arg1, weight);
		    	System.out.println(b);
		    	Assert.assertTrue(b);
		    	 testReporter.log(LogStatus.PASS, "verify weight is updated for internal contact");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "verify weight is updated for internal contact");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	
	}
	
	@Then("^update the issued date$")
	public void update_the_issued_date() throws Throwable {
	  try {
		  AaoHelper.switch_to_tab(driver, 0);
		  sidetailpage.updateIssuedDate();
		  testReporter.log(LogStatus.PASS, "update issued date");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "update issued date");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@Then("^Verify the actual is updated in the goals record for the contact \"([^\"]*)\"$")
	public void verify_the_actual_is_updated_in_the_goals_record_for_the_contact(String arg1) throws Throwable {
	   
	    try {
	    	
	    	boolean b=goals.verifyActualForUpdatedDate(arg1, weight);
	    	Assert.assertTrue(b);
	    	System.out.println(b);
	    	 testReporter.log(LogStatus.PASS, "actual is updated in the goals record for the contact");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "actual is updated in the goals record for the contact");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.close();
				Assert.assertTrue(false);
	}
	}
	
	@Then("^For Monthly Goals Flow change the internal contact to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_change_the_internal_contact_to(String arg1) throws Throwable {
		 try {
			 AaoHelper.switch_to_tab(driver, 0);
		
		    	sidetailpage.selectInternalContact();
		    	sidetailpage.lookUpContact(arg1);
		    	sidetailpage.pressSave();
		    	 testReporter.log(LogStatus.PASS, "Change the internal contact");
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "change the internal contact");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.close();
					Assert.assertTrue(false);
		}
	}
	@And("^For Monthly Goals Flow Verify the monthly goals actual  is updated for that month for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_Verify_the_monthly_goals_actual_is_updated_for_that_month_for(String arg1) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(weight,arg1);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for new  contact");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for new contact");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}
	
	@Then("^For Monthly Goals Flow Close Monthly Goals tab$")
	public void for_Monthly_Goals_Flow_Close_Monthly_Goals_tab() throws Throwable {
	    try {
	    	driver.close();
	    	AaoHelper.switch_to_tab(driver, 0);
	    	testReporter.log(LogStatus.PASS, "Close current tab");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "Close current tab");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	    
	}
	
	@Then("^For Monthly Goals Flow Open Monthly Goals tab in separate tab$")
	public void for_Monthly_Goals_Flow_Open_Monthly_Goals_tab_in_separate_tab() throws Throwable {
	   try {
		  useractionspage.goToMonthlyGoals();
		  useractionspage.goToServiceItems();
		  useractionspage.findServiceItem(serviceitemno);
		  useractionspage.clickServiceItem(serviceitemno);
		   testReporter.log(LogStatus.PASS, "Open Monthly Goals tab");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Open Monthly Goals tab");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	
	@Then("^For Monthly Goals Flow Select \"([^\"]*)\" for triage completed by$")
	public void for_Monthly_Goals_Flow_Select_for_triage_completed_by(String arg1) throws Throwable {
	    try {
	    	
	    	sidetailpage.selectTriage(arg1);
	    	 testReporter.log(LogStatus.PASS, "Select an offficer for triage completed by: "+arg1);
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "Select an officer for triage completed by"+arg1);
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}

	@And("^For Monthly Goals Flow verify that  triage officer \"([^\"]*)\" gets \"([^\"]*)\" point for current month$")
	public void for_Monthly_Goals_Flow_verify_that_triage_officer_gets_point_for_current_month(String arg1, String arg2) throws Throwable {
	
	    try {
	       goals.verifyActual(arg1, arg2);
	       testReporter.log(LogStatus.PASS, "Verify that triage officer "+arg1+"gets 1 point");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "Verify that triage officer "+arg1+"gets 1 point");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}    
	   
	}

	@Then("^For Monthly Goals Flow change the officer for triage completed by to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_change_the_officer_for_triage_completed_by_to(String arg1) throws Throwable {
	   
		 try {
		    	
		    	sidetailpage.selectTriage(arg1);
		    	 testReporter.log(LogStatus.PASS, "Change  offficer for triage completed by: "+arg1);
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "Change  officer for triage completed by"+arg1);
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
		}


	@Then("^For Monthly Goals Flow verify that the new triage officer \"([^\"]*)\" gets \"([^\"]*)\" point$")
	public void for_Monthly_Goals_Flow_verify_that_the_new_triage_officer_gets_point(String arg1, String arg2) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(arg2,arg1);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for new  contact"+arg1);
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for new contact"+arg1);
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}

	@Then("^For Monthly Goals Flow points are \"([^\"]*)\" for the existing triage officer \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_points_are_for_the_existing_triage_officer(String arg1, String arg2) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(arg1,arg2);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for existing offficer"+arg2);
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for existing officer"+arg2);
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}

	@Then("^For Monthly Goals Flow Change the triage completed on date$")
	public void for_Monthly_Goals_Flow_Change_the_triage_completed_on_date() throws Throwable {
	   try {
		   
		   sidetailpage.updateTriageDate();
		  
		    	 testReporter.log(LogStatus.PASS, "change triage completed date");
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "change triage completed  date");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.close();
					Assert.assertTrue(false);
		}
	   }
	

	@Then("^For Monthly Goals flow  Verify that points are updated for \"([^\"]*)\" according to the triage completed date\\.$")
	public void for_Monthly_Goals_flow_Verify_that_points_are_updated_for_according_to_the_triage_completed_date(String arg1) throws Throwable {
		 try {
		    	
		    	boolean b=goals.verifyActualForUpdatedDate(arg1, weight);
		    	Assert.assertTrue(b);
		    	System.out.println(b);
		    	 testReporter.log(LogStatus.PASS, "actual is updated in the goals record for the contact"+arg1);
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "actual is updated in the goals record for the contact"+arg1);
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.close();
					Assert.assertTrue(false);
		}
	}

	@Then("^For Monthly Goals Flow Select officer for BCU Checks Completed by to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_Select_officer_for_BCU_Checks_Completed_by_to(String arg1) throws Throwable {
		 try {
		    	
		    	sidetailpage.selectBcu(arg1);
		    	 testReporter.log(LogStatus.PASS, "Select an offficer for bcu checks completd by: "+arg1);
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "Select an officer for bcu checks completed by:"+arg1);
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	}

	@Then("^For Monthly Goals Flow verify that  \"([^\"]*)\" gets \"([^\"]*)\" point for current month$")
	public void for_Monthly_Goals_Flow_verify_that_gets_point_for_current_month(String arg1, String arg2) throws Throwable {
		 try {
		       goals.verifyActual(arg1, arg2);
		       testReporter.log(LogStatus.PASS, "Verify that bcu officer "+arg1+"gets 1 point");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "Verify that bcu officer "+arg1+"gets 1 point");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}    
	}

	@Then("^For Monthly Goals Flow change the officer for bcu checks completed by to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_change_the_officer_for_bcu_checks_completed_by_to(String arg1) throws Throwable {
		 try {
		    	
		    	sidetailpage.selectTriage(arg1);
		    	 testReporter.log(LogStatus.PASS, "Change  offficer for bcu checks completed by: "+arg1);
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "Change  officer for bcu checks completed by"+arg1);
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	}

	@Then("^For Monthly Goals Flow verify that new bcu offficer \"([^\"]*)\" gets \"([^\"]*)\" point$")
	public void for_Monthly_Goals_Flow_verify_that_new_bcu_offficer_gets_point(String arg1, String arg2) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(arg2,arg1);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for new  contact"+arg1);
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for new contact"+arg1);
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}  
	}

	@Then("^For Monthly Goals Flow verify points are \"([^\"]*)\" for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_verify_points_are_for(String arg1, String arg2) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(arg1,arg2);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify monthly goals is updated for existing offficer"+arg2);
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify monthly goals is updated for existing officer"+arg2);
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}

	@Then("^For Monthly Goals Flow Change the BCU Checks completed on date$")
	public void for_Monthly_Goals_Flow_Change_the_BCU_Checks_completed_on_date() throws Throwable {
		try {
		 
		   sidetailpage.updateTriageDate();
		  
		    	 testReporter.log(LogStatus.PASS, "change bcu checks  completed date");
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "change bcu checks completed  date");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.close();
					Assert.assertTrue(false);
		}
	}

	@Then("^For Monthly Goals flow  Verify that points are updated for \"([^\"]*)\" according to the bcu completed date\\.$")
	public void for_Monthly_Goals_flow_Verify_that_points_are_updated_for_according_to_the_bcu_completed_date(String arg1) throws Throwable {
		 try {
		    	
		    	boolean b=goals.verifyActualForUpdatedDate(arg1, weight);
		    	Assert.assertTrue(b);
		    	System.out.println(b);
		    	 testReporter.log(LogStatus.PASS, "actual is updated in the goals record for the contact"+arg1);
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "actual is updated in the goals record for the contact"+arg1);
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.close();
					Assert.assertTrue(false);
		}
	}

	@Then("^For Monthly Goals Flow select \"([^\"]*)\" for Triage Completed By and select \"([^\"]*)\" for BCU Checks Completed By$")
	public void for_Monthly_Goals_Flow_select_for_Triage_Completed_By_and_select_for_BCU_Checks_Completed_By(String arg1, String arg2) throws Throwable {
	    try {
	    	
	    	sidetailpage.selectTriage(arg1);
	    	sidetailpage.selectBcu(arg1);
	    	 testReporter.log(LogStatus.PASS, "Select none for triage and bcu");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Select none for triage and bcu");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@Then("^For Monthly Goals flow  Verify that points are \"([^\"]*)\" for current triage officer \"([^\"]*)\"$")
	public void for_Monthly_Goals_flow_Verify_that_points_are_for_current_triage_officer(String arg1, String arg2) throws Throwable {
		 try {
			   boolean b=goals.verifyActualUpdated(arg2, arg1);
			   Assert.assertTrue(b);
			  
		    	 testReporter.log(LogStatus.PASS, "verify actual is updated to 0 for current triage officer"+arg2);
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "verify actual is updated to 0 for current triage officer"+arg2);
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
	}

	@Then("^For Monthly Goals flow  Verify that points are \"([^\"]*)\" for current bcu officer \"([^\"]*)\"$")
	public void for_Monthly_Goals_flow_Verify_that_points_are_for_current_bcu_officer(String arg1, String arg2) throws Throwable {
		 try {
			   boolean b=goals.verifyActualUpdated(arg2, arg1);
			   Assert.assertTrue(b);
			  
		    	 testReporter.log(LogStatus.PASS, "verify actual is updated to 0 for current bcu officer"+arg2);
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "verify actual is updated to 0 for current bcu officer"+arg2);
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@And("^For Monthly Goals Flow verify that the actual is updated to \"([^\"]*)\" for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_verify_that_the_actual_is_updated_to_for(String arg1, String arg2) throws Throwable {
		try {
	    	boolean b=goals.verifyActualForUpdatedContact(arg1, arg2);
	    	System.out.println(b);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify points are taken away for the previous contact for that month");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify points are taken away for the previous contact for that month");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}
	
	@And("^For Monthly Goals Flow update production expectations of \"([^\"]*)\" for all the months to \"([^\"]*)\" and  verify the sum is total of all the expectations$")
	public void for_Monthly_Goals_Flow_update_production_expectations_of_for_all_the_months_to_and_verify_the_sum_is_total_of_all_the_expectations(String arg1, String arg2) throws Throwable {
	    try {
	    	boolean b=goals.verifySumOfExpectations(arg1, arg2);
	    	Assert.assertTrue(b);
	    	 testReporter.log(LogStatus.PASS, "verify sum is equal to the total of all the production expectations entered");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify sum is equal to the total of all the production expectations entered");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	   	}

	@And("^For Monthly Goals Flow update goals of \"([^\"]*)\" for all the months to \"([^\"]*)\" and  verify the sum is total of all the goals$")
	public void for_Monthly_Goals_Flow_update_goals_of_for_all_the_months_to_and_verify_the_sum_is_total_of_all_the_goals(String arg1, String arg2) throws Throwable {
		 try {
		    boolean b=	goals.verifySumOfGoals(arg1, arg2);
		    Assert.assertTrue(b);
		    	 testReporter.log(LogStatus.PASS, "verify sum is equal to the total of all the goals entered");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "verify sum is equal to the total of all the goals entered");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	}
	
	@Then("^For Monthly Goals Flow change the record type to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_change_the_record_type_to(String arg1) throws Throwable {
	    try {
	    	
	    	sidetailpage.clickChangeRecord();
	    	sipage.selectRecordType(arg1);
	    	sipage.clickContinue();
	    	editpage.editSI();
	   	 testReporter.log(LogStatus.PASS, "change the record type of the closed service item to open ");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "change the record type of closed service item to open");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}	
	    
	}

	@And("^For Monthly Goals Flow verify that actual is updated  for \"([^\"]*)\" to \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_verify_that_actual_is_updated_for_to(String arg1, String arg2) throws Throwable {
	   try {
		   boolean b=goals.verifyActualUpdated(arg1, arg2);
		   Assert.assertTrue(b);
		  
	    	 testReporter.log(LogStatus.PASS, "verify actual is updated to 0");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "verify actual is updated to 0");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
		   
	   
	}


	@Then("^For Monthly Goals Flow login as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_login_as(String arg1) throws Throwable {
		try {
			 driver.close();
			  AaoHelper.switch_to_tab(driver, 0);
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

	@And("^For Monthly Goals navigate to monthly goals$")
	public void for_Monthly_Goals_navigate_to_monthly_goals() throws Throwable {
		try {
			   useractionspage.goToMonthlyGoals();
			 //  goals.getInitialActual(arg1);
			   AaoHelper.switch_to_tab(driver, 1);
			   testReporter.log(LogStatus.PASS, "open monthly goals in a separate tab");
			 		}
			 		 catch (Exception e) {
			 				testReporter.log(LogStatus.FAIL, "open monthly goals in a separate tab");
			 				screenShotPath = GetScreenShot.capture(driver);
			 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			 				e.printStackTrace();
			 				extent.endTest(testReporter);
			 				extent.flush();
			 				extent.close();
			 				Assert.assertTrue(false);
			 	}
	}

	@And("^For Monthly Goals Flow Verify there is only one monthly goals record for \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_Verify_there_is_only_one_monthly_goals_record_for(String arg1) throws Throwable {
	    try {
	    	boolean b=goals.verifySingleRecord(arg1);
	    	Assert.assertTrue(b);
	    	testReporter.log(LogStatus.PASS, "verify there is only one  monthly record for the contact");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify there is only one monthly record and there are no other records");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}
	}
	@Then("^For Monthly Goals Flow verify that production expectations  are non editable$")
	public void for_Monthly_Goals_Flow_verify_that_production_expectations_are_non_editable() throws Throwable {
		  try {
		    	boolean b=goals.verifyNotEditable();
		    	Assert.assertTrue(b);
		    	testReporter.log(LogStatus.PASS, "verify production expectations are not editable");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "verify production expectations are not editable");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	}

	@Then("^Verify that Personal Goals and Annual Goal is editable$")
	public void verify_that_Personal_Goals_and_Annual_Goal_is_editable() throws Throwable {
		 try {
		    	boolean b=goals.verifyEditable();
		    	Assert.assertTrue(b);
		    	testReporter.log(LogStatus.PASS, "verify personal goals and annual goal are  editable");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "verify personal goals and annual goal are  editable");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	}


	


	@Then("^For Monthly Goals Flow signin as \"([^\"]*)\"$")
	public void for_Monthly_Goals_Flow_signin_as(String arg1) throws Throwable {
		try {
			driver.close();
	    	
	    	AaoHelper.switch_to_tab(driver, 0);
			loginpage.Aaologout();
			homepage.clickManageUsers();
			homepage.clickUsers();
			homepage.selectAllAaoUsers();
			homepage.searchAaoUser(arg1);
			useractionspage.clickLogInForAaoUser();
			 testReporter.log(LogStatus.PASS, "log out and verify user is logged in as Mark Brunner");
			
		}catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "log out and verify user is logged in as Mark Brunner");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
		}  
	}

	@And("^For Monthly Goals go to monthly goals$")
	public void for_Monthly_Goals_go_to_monthly_goals() throws Throwable {
		try {
			   useractionspage.goToMonthlyGoals();
			 //  goals.getInitialActual(arg1);
			   AaoHelper.switch_to_tab(driver, 1);
			   testReporter.log(LogStatus.PASS, "open monthly goals in a separate tab");
			 		}
			 		 catch (Exception e) {
			 				testReporter.log(LogStatus.FAIL, "open monthly goals in a separate tab");
			 				screenShotPath = GetScreenShot.capture(driver);
			 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			 				e.printStackTrace();
			 				extent.endTest(testReporter);
			 				extent.flush();
			 				extent.close();
			 				Assert.assertTrue(false);
			 	}
	}

	@And("^For Monthly Goals Flow verify that there are monthly records of people under the supervisor$")
	public void for_Monthly_Goals_Flow_verify_that_there_are_monthly_records_of_people_under_the_supervisor() throws Throwable {
		try {
	    	boolean b=goals.verifyMultipleRecords();
	    	Assert.assertTrue(b);
	    	
	    	testReporter.log(LogStatus.PASS, "verify there are multiple records of people under the supervisor");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify there are multiple records of people under the supervisor");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}  
	}

	@And("^For Monthly Goals Flow update production expectations of any record for all the months to   and verify that sum is total of all the expectations$")
	public void for_Monthly_Goals_Flow_update_production_expectations_of_any_record_for_all_the_months_to_and_verify_that_sum_is_total_of_all_the_expectations() throws Throwable {
		
			try {
		    	boolean b=goals.updateExpectationsVerifySum();
		    	Assert.assertTrue(b);
		    	
		    	testReporter.log(LogStatus.PASS, "verify sum of expectations");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "verify sum of expectations");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}  
	}

	@And("^For Monthly Goals Flow update goals of any record for all the months  and verify that sum is total of all the goals$")
	public void for_Monthly_Goals_Flow_update_goals_of_any_record_for_all_the_months_and_verify_that_sum_is_total_of_all_the_goals() throws Throwable {
		try {
	    	boolean b=goals.updateGoalsVerifySum();
	    	Assert.assertTrue(b);
	    	
	    	testReporter.log(LogStatus.PASS, "verify sum of goals");
 		}
 		 catch (Exception e) {
 				testReporter.log(LogStatus.FAIL, "verify sum of goals");
 				screenShotPath = GetScreenShot.capture(driver);
 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
 				e.printStackTrace();
 				extent.endTest(testReporter);
 				extent.flush();
 				extent.close();
 				Assert.assertTrue(false);
 	}  
	}
	@Then("^For Monthly Goals Flow log out$")
	public void for_Monthly_Goals_Flow_log_out() throws Throwable {
		loginpage.Aaologout();
	}

	@And("^For Monthly Goals Flow  Stop Report Generation for current scenario AAO End To End$")
	public void for_Monthly_Goals_Flow_Stop_Report_Generation_for_current_scenario_AAO_End_To_End() throws Throwable {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}

	@And("^For Monthly Goals Flow close the browser$")
	public void for_Monthly_Goals_Flow_close_the_browser() throws Throwable {
		driver.close();
	    driver.quit();
	}







	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\udanturthy\\\\Downloads\\\\chromedriver_win32_78\\\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		AAO_Monthly_Goals_Flow app=new AAO_Monthly_Goals_Flow();
		WebDriver driver = new ChromeDriver();
		QualityControlPercentage p=new QualityControlPercentage(driver);
		ServiceItemDetail d= new ServiceItemDetail(driver);
		ResponseBuilder b= new ResponseBuilder(driver);
		ResponseDetail det=new ResponseDetail(driver);
		MonthlyGoals g=new MonthlyGoals(driver);
		UserActions u=new UserActions(driver);
		Home h= new Home(driver);
		driver.manage().window().maximize(); // maximizes
		driver.get("https://uscis--uatg.my.salesforce.com/?login");
		
		try {
		h.searchAaoUser("Dantzler, Derrick");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}

}

