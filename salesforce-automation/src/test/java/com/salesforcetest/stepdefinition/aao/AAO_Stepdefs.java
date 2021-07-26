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
	
	public class AAO_Stepdefs {
		
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
	

		@Given("^set the initial conditions$")
		public void set_the_initial_conditions() throws Throwable {
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

	
	@When("^Registered user is logged in as \"([^\"]*)\"$")
	public void registered_user_is_logged_in_as(String arg1) throws Throwable {
		try {
			
		extent = new ExtentReports(workingDir+"\\test-report\\AAOCreateReopenHold"+AaoHelper.randomDateTime()+".html", true);
			testReporter = extent.startTest("AAO Create_Reopen_Hold");
			
			
			 
		 homepage.clickManageUsers();
		   homepage.clickUsers();
		   homepage.selectAllAaoUsers();
		   homepage.searchAaoUser(arg1);
		   useractionspage.clickLogInForAaoUser();
		   testReporter.log(LogStatus.PASS, "Verifying Registered User is Logged in as Intake File Clerk:");
	}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verifying Registered User is Logged in as Intake File Clerk::");
			screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
			}
	}
		

	@And("^user creates an application with Filetype as \"([^\"]*)\"$")
	public void user_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
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

	@When("^clicks to create \"([^\"]*)\" from application detail$")
	public void clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

	@Then("^verify Receipt number,Responsible Party and AAO Recd Date are AutoPopulated$")
	public void verify_Receipt_number_Responsible_Party_and_AAO_Recd_Date_are_AutoPopulated() throws Throwable {
		try {
		Assert.assertTrue((sipage.verifyReceiptNumber(receiptno))&(sipage.verifyAaoRecdDate())&(sipage.verifyResponsibleParty()));
			testReporter.log(LogStatus.PASS, "Verifying ReceiptNumber,Responsible party and Aao Recd Date are Autopupulated");
		}
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Verifying ReceiptNumber,Responsible Party and Aao Recd Date are Autopopulated:");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	}
}
	    
	

	@When("^user clicks save without filling the required fields$")
	public void user_clicks_save_without_filling_the_required_fields() throws Throwable {
		try {
	    sipage.clickSave();
	    testReporter.log(LogStatus.PASS, "Clicking save without filling the required fields");
		}
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Clicking save without filling the required fields");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	}
	}

	@Then("^verify error messages are displayed$")
	public void verify_error_messages_are_displayed() throws Throwable {
		try {
				
		Assert.assertTrue(sipage.verifyErrorMessages());
		 testReporter.log(LogStatus.PASS, "Verifying ErrorMessages");
	}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verifying ErrorMessages:");
			screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	@When("^user creates Service Item with the following data and saves Service Item$")
	public void user_creates_Service_Item_with_the_following_data_and_saves_Service_Item(DataTable arg1) throws Throwable {
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
				testReporter.log(LogStatus.FAIL, "Provide contact,account,business,attorney :"+contact+" ,"+account+","+business+","+attorney);
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
	@Then("^verify the underlying benefit field displays underlying benefit value \"([^\"]*)\"$")
	public void verify_the_underlying_benefit_field_displays_underlying_benefit_value(String arg1) throws Throwable {
		
		
		try {
			 Assert.assertTrue(sidetailpage.verifyUnderlyingBenefit(arg1));
			 testReporter.log(LogStatus.PASS, "verifying underlying benefit");
				}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying underlying benefit:");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
	    
	}
	
	@Then("^verify the ReceiptNumber,FileNumber are AutoPopulated$")
	public void verify_the_ReceiptNumber_FileNumber_are_AutoPopulated() throws Throwable {
	    try {
	    	String r=sidetailpage.returnReceiptAutoPopulated();
	    	String f=sidetailpage.returnFileNoAutoPopulated();
	    	
	    	Assert.assertTrue((r.equals(receiptno))&(f.equals(fileno)));
			testReporter.log(LogStatus.PASS, "Verifying ReceiptNumber and FileNumber Autopupulated");
		}
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Verifying ReceiptNumber and File Number Autopopulated:");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	    }
	}

	@Then("^verify the AAO Received Date is autopopulated  and ARC Start Date is autopopulated$")
	public void verify_the_AAO_Received_Date_is_autopopulated_and_ARC_Start_Date_is_autopopulated() throws Throwable {
	    try {
	    	Assert.assertTrue(sidetailpage.verifyAaoRecdDateAutoPopulated()&(sidetailpage.verifyArcDateAutoPopulated()));
	    	testReporter.log(LogStatus.PASS, "Verifying Aao Recd Date and ARC Start Date are populated to today's date");
		}
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Verifying Aao Recd Date and ARC Start Date are populated to today's date:");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	    }
	    }
	

	@Then("^verify status is \"([^\"]*)\" and Milestone is \"([^\"]*)\"$")
	public void verify_status_is_and_Milestone_is(String arg1, String arg2) throws Throwable {
	    try {
	    	Assert.assertTrue(sidetailpage.verifyStatus(arg1)&(sidetailpage.verifyMileStone(arg2)));
	    	testReporter.log(LogStatus.PASS, "Verifying status as pending and Milestone as Mail Room");
	    }
	 catch (Exception e) {
		testReporter.log(LogStatus.FAIL, "Verifying status as pending and Milestone as Mail Room");
		screenShotPath = GetScreenShot.capture(driver);
		testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		e.printStackTrace();
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
		Assert.assertTrue(false);
	    }
	    	
	}


	
	@Then("^verify the tracking record is created with start milestone with the following data$")
	public void verify_the_tracking_record_is_created_with_start_milestone_with_the_following_data(DataTable arg1) throws Throwable {
		List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
	    String sm=list.get(0).get("StartMileStone");
	    String em=list.get(0).get("EndMileStone");
	    String sarc=list.get(0).get("StartArc");
	    String earc=list.get(0).get("EndArc");
	    String days=list.get(0).get("DaysWorked");
	    try {
	   Assert.assertTrue( rec.verifyTrackingRecord(sm,em,sarc,earc,days));
	   testReporter.log(LogStatus.PASS, "Verifying tracking record");
	}
	
	    catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verifying tracking record:");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			e.printStackTrace();
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
			Assert.assertTrue(false);
}
	}
	

	
	@When("^user updates the AAO Recd Date to two days before today$")
	public void user_updates_the_AAO_Recd_Date_to_two_days_before_today() throws Throwable {
		try {
	   sidetailpage.updateAaoRecdDate();
	  // sidetailpage.clickSave();
	   testReporter.log(LogStatus.PASS, "Updating Aao Recd Date to two days before today");
	}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Updating Aao Recd Date to two days before today");
				screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	
	
		
	
	@Then("^verify ARC start date is updated as two days before today$")
	public void verify_ARC_start_date_is_updated_as_two_days_before_today() throws Throwable {
		try {
			boolean found= sidetailpage.verifyArcStartDate();
			Assert.assertTrue((found));
			
			
			   testReporter.log(LogStatus.PASS, "Verifying Arc start Date to two days before today");
			}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying arc start Date to two days before today");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
	}

	@Then("^Verify AAo clock as \"([^\"]*)\"$")
	public void verify_AAo_clock_as(String arg1) throws Throwable {
		try {
			 boolean f= sidetailpage.verifyAaoClock(arg1);
			 Assert.assertTrue(f);
			 
			   testReporter.log(LogStatus.PASS, "Verifying AAO Clock");
			}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying AAO Clock");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
	}

	@Then("^Verify Adjudication clock as \"([^\"]*)\"$")
	public void verify_Adjudication_clock_as(String arg1) throws Throwable {
		try {
			  boolean f= sidetailpage.verifyAdjudicationClock(arg1);
			  Assert.assertTrue(f);
			   testReporter.log(LogStatus.PASS, "Verifying Adjudication clcik");
			}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying Adjudication clock");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
	}


		
		@And("^Verify left bookend tracking record is updated with following data$")
		public void verify_left_bookend_tracking_record_is_updated_with_following_data(DataTable arg1) throws Throwable {
			List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
		    
		    String sm=list.get(0).get("StartMileStone");
		    String em=list.get(0).get("EndMileStone");
		    String sarc=list.get(0).get("StartArc");
		    String earc=list.get(0).get("EndArc");
		    String days=list.get(0).get("DaysWorked");
		    try {
		    
		    boolean found=rec.verifyTrackingRecord(sm,em,sarc,earc,days);
		    Assert.assertTrue(found);
		    AaoHelper.sleep(4);
		    testReporter.log(LogStatus.PASS, "Verifying Left bookend tracking record is updated");
		}
		    catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verifying Left bookend tracking record is updated");
				screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
		}
		@Then("^verify start date is updated$")
		
		public void verify_start_date_is_updated() throws Throwable {
		   try {
			   boolean f=rec.verifyStartDate();
			   Assert.assertTrue(f);
			   AaoHelper.sleep(4);
			   testReporter.log(LogStatus.PASS, "Verifying  start Date");
			}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying start Date:");
					screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
		}
			   
		   @Then("^update responsible party and verify the following$")
		   public void update_responsible_party_and_verify_the_following(DataTable arg1) throws Throwable {
			   List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
				for(int i=0; i<list.size(); i++) {
				String respparty=	list.get(i).get("ResponsibleParty");
				String milestone=	list.get(i).get("MileStone");
				String endmilestone= list.get(i).get("EndMileStone");
				String startmilestone= list.get(i).get("StartMileStone");
				try {
					sidetailpage.updateRespParty(respparty);
					boolean b=sidetailpage.verifyMileStone(milestone);
					Assert.assertTrue(b);
					boolean f=rec.verifyTrackingRecordRespParty(startmilestone, endmilestone);
					Assert.assertTrue(f);
					 testReporter.log(LogStatus.PASS, "Updating responsible party and verifying tracking record");
				}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Updating responsible party and verifying tracking record");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
				}
				}
		   }
				
		   @Then("^verify the  responsible party picklist for the following data$")
		   public void verify_the_responsible_party_picklist_for_the_following_data(DataTable arg1) throws Throwable {
			   List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
				for(int i=0; i<list.size(); i++) {
				String name=	list.get(i).get("Name");
				System.out.println(name);
				try {
					boolean b=sidetailpage.verifyRespPartyList(name);
					Assert.assertTrue(b);
					testReporter.log(LogStatus.PASS, "Verifying  resp party picklist");
				}
				 catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying resp party pick list:");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
				}
				}
		   }
				
		   
		   @When("^user adds a Hold as \"([^\"]*)\"$")
		   public void user_adds_a_Hold_as(String arg1) throws Throwable {
		      try {
		    	  driver.navigate().back();
		    	  hld.createHold(arg1);
		    	  testReporter.log(LogStatus.PASS, "Adding AFB National Security Hold");
				}  catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "Adding AFB National Security Hold");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
		      
		   }

		   @Then("^verify error message is displayed and user is unable to add hold$")
		   public void verify_error_message_is_displayed_and_user_is_unable_to_add_hold() throws Throwable {
		       try {
		    	   Assert.assertTrue(hld.verifyErrorMessage());
		    	   testReporter.log(LogStatus.PASS, "Verify error message for AFB National Security hold");
				}  catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "Verify error message for AFB National Security hold");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
		   }
		       
				@Then("^add a Dol Hold as type \"([^\"]*)\"$")
				public void add_a_Dol_Hold_as_type(String arg1) throws Throwable {
					try {
					hld.clickCancel();
				    hld.createHold(arg1);
				    testReporter.log(LogStatus.PASS, "Adding DOL hold");
					}  catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Adding DOL Hold:");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
				}
				
				@Then("^verify the callupdate is autopopulated and cannot be edited$")
				public void verify_the_callupdate_is_autopopulated_and_cannot_be_edited() throws Throwable {
				   try {
					   hld.verifyCallUpDate();
					   testReporter.log(LogStatus.PASS, "verify callupdate is autopopulated and cannot be edited");
					}  catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify callupdate is autopopulated and cannot be edited");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
				  
				}

				@Then("^add a hold as type\"([^\"]*)\"$")
				public void add_a_hold_as_type(String arg1) throws Throwable {
				    try {
				    	hld.clickServiceItem();
				    	hld.createHold(arg1);
				    	   testReporter.log(LogStatus.PASS, "Adding second DOL hold");
					}  catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Adding second DOL Hold:");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
				}

				@Then("^verify that you receive an error message$")
				public void verify_that_you_receive_an_error_message() throws Throwable {
					try {
					   Assert.assertTrue(hld.verifyErrorMessage());
			    	   testReporter.log(LogStatus.PASS, "Verify error message for adding DOL hold twice");
					}  catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verify error message for adding DOL hold twice");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
			}
				}
				@Then("^add another hold as type \"([^\"]*)\"$")
				public void add_another_hold_as_type(String arg1) throws Throwable {
					try {
						hld.clickCancel();
					    hld.createHold(arg1);
					    testReporter.log(LogStatus.PASS, "Adding G-28 hold");
						}  catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "Adding G-28 Hold:");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
				}
				}

					@Then("^verify the status of si is updated with \"([^\"]*)\"$")
					public void verify_the_status_of_si_is_updated_with(String arg1) throws Throwable {
						try {
							hld.clickServiceItem();
					   boolean b= sidetailpage.verifyStatus(arg1);
					   Assert.assertTrue(b);
					    testReporter.log(LogStatus.PASS, "verifying si status is updated");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying si status is updated");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}
					}

				@Then("^verify the hold callupdate and hold days$")
				public void verify_the_hold_callupdate_and_hold_days() throws Throwable {
				   try {
					boolean b=   hld.verifyHold();
					Assert.assertTrue(b);
					testReporter.log(LogStatus.PASS, "verifying call update and hold days");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying call update and hold days");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@When("^user logsin as \"([^\"]*)\"$")
				public void user_logsin_as(String arg1) throws Throwable {
					try {
					loginpage.Aaologout();
					homepage.clickManageUsers();
					homepage.clickUsers();
					homepage.selectAllAaoUsers();
					homepage.searchAaoUser(arg1);
					useractionspage.clickLogInForAaoUser();
					testReporter.log(LogStatus.PASS, "verifying user logsin as Aao supervisor");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying user logs in as Aao Supervisor");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@And("^navigate to service item in the previous step$")
				public void navigate_to_service_item_in_the_previous_step() throws Throwable {
					try {
					    useractionspage.findServiceItem(serviceitemno);
					    useractionspage.clickServiceItem(serviceitemno);
					    
					    testReporter.log(LogStatus.PASS, "navigate to service item ");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "navigate to service item ");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}


				@Then("^remove the \"([^\"]*)\" hold$")
				public void remove_the_hold(String arg1) throws Throwable {
				   try {
					   hld.removeHold(arg1);
					   testReporter.log(LogStatus.PASS, "Remove DOL hold");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Remove DOL hold");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				
				    
				}
				@Then("^verify status updated to as \"([^\"]*)\"$")
				public void verify_status_updated_to_as(String arg1) throws Throwable {
				    try {
				    Assert.assertTrue(hld.verifyStatus(arg1));
				    	   testReporter.log(LogStatus.PASS, "Verify status as on hold");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verify status as on hold");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    
				}

				@Then("^login as \"([^\"]*)\"$")
				public void login_as(String arg1) throws Throwable {
					try {
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						testReporter.log(LogStatus.PASS, "verifying user logsin as Aao File clerk");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "verifying user logs in as Aao File clerk");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
				}
				
				@Then("^go to the service item$")
				public void go_to_the_service_item() throws Throwable {
					try {
					    useractionspage.findServiceItem(serviceitemno);
					    useractionspage.clickServiceItem(serviceitemno);
					    
					    testReporter.log(LogStatus.PASS, "Go to service item ");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Go to service item ");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				
				@Then("^update the final decision to \"([^\"]*)\" and verify error message displayed$")
				public void update_the_final_decision_to_and_verify_error_message_displayed(String arg1) throws Throwable {
				   
					try {
				    	sidetailpage.updateFinalDecisionVerifyError(arg1);
				    	testReporter.log(LogStatus.PASS, "updating the final decision to admin closed");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "updating final decision to admin closed");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^verify an error message is displayed for responsible party$")
				public void verify_an_error_message_is_displayed_for_responsible_party() throws Throwable {
				   try {
					 Assert.assertTrue(sidetailpage.verifyError());
					 testReporter.log(LogStatus.PASS, "verify error message is displayed for final decision");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify error message is displayed for final decision");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				  
				}

				@Then("^update the  responsible party to \"([^\"]*)\"$")
				public void update_the_responsible_party_to(String arg1) throws Throwable {
					   try {
						   hld.updateParty(arg1);
						   testReporter.log(LogStatus.PASS, "updating responsible party to issued decisions queue");
							
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "updating responsible party to issued decisions queue");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
				}

				@Then("^verify error is displayed for Final Decision$")
				public void verify_error_is_displayed_for_Final_Decision() throws Throwable {
				    try {
				    	Assert.assertTrue(sidetailpage.verifyFinalDecisionError());
				    	 testReporter.log(LogStatus.PASS, "verify error message is displayed for final decision");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify error message is displayed for final decision");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^delete the \"([^\"]*)\" hold$")
				public void delete_the_hold(String arg1) throws Throwable {
				    try {
				    	hld.removeHold(arg1);
				    	testReporter.log(LogStatus.PASS, "delete G-28 hold");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "delete G-28 hold");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				    
				

				@Then("^Verify the  status is \"([^\"]*)\"$")
				public void verify_the_status_is(String arg1) throws Throwable {
					 try {
					    	boolean b=hld.verifyStatus(arg1);
					    	Assert.assertTrue(b);
					    	testReporter.log(LogStatus.PASS, "verify status is pending");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "verify status is pending");
							screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
				}
				@Then("^update Final Decision to \"([^\"]*)\"$")
				public void update_Final_Decision_to(String arg1) throws Throwable {
					 try {
					    	sidetailpage.setFinalDecision(arg1);
					    	testReporter.log(LogStatus.PASS, "updating the final decision to admin closed");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "updating final decision to admin closed");
							screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
				}
				@Then("^verify status as \"([^\"]*)\" and Milestone as \"([^\"]*)\"$")
				public void verify_status_as_and_Milestone_as(String arg1, String arg2) throws Throwable {
				   try {
					  Assert.assertTrue(sidetailpage.verifyStatus(arg1));
					  Assert.assertTrue(sidetailpage.verifyMileStone(arg2));
					  testReporter.log(LogStatus.PASS, "verify status and milestone is updated to decision recorded");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify status and milestone is updated to decision recorded");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
					   
				   
				}
				@Then("^verify the tracking record with the following data$")
				public void verify_the_tracking_record_with_the_following_data(DataTable arg1) throws Throwable {
				    try {
				    	List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
					    
					    String sm=list.get(0).get("StartMileStone");
					  
					    
				    	boolean b=rec.verifyTrackingRecordDecision(sm);
				    	Assert.assertTrue(b);
				    	testReporter.log(LogStatus.PASS, "Verifying the tracking record with the updated decision");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying the tracking record with the updated decision");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    }
				
				@Then("^verify service history is updated with \"([^\"]*)\" by \"([^\"]*)\"$")
				public void verify_service_history_is_updated_with_by(String arg1, String arg2) throws Throwable {
				    try {
				    	Assert.assertTrue(sidetailpage.verifyServiceHistory(arg1, arg2));
				    	testReporter.log(LogStatus.PASS, "Verifying the service item history is updated");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying the service item history is updated");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    
				}

				@Then("^remove \"([^\"]*)\" hold$")
				public void remove_hold(String arg1) throws Throwable {
					 try {
						   hld.removeHold(arg1);
						   testReporter.log(LogStatus.PASS, "Remove G-28 hold");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "Remove G-28 hold");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
					
				}


				   
				@Then("^create an application on External Account with \"([^\"]*)\" and account name as \"([^\"]*)\"$")
				public void create_an_application_on_External_Account_with_and_account_name_as(String arg1, String arg2) throws Throwable {
				   try {
					   accts.createAccountAndCreateApplication(arg1, arg2);
					   testReporter.log(LogStatus.PASS, "verifying creating application and creating account name");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying creating application and creating account name ");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^create an Aao Si on the account with the following data$")
				public void create_an_Aao_Si_on_the_account_with_the_following_data(DataTable arg1) throws Throwable {
				    try {
				    	List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
				    	String recordtype=list.get(0).get("Record Type");
					    String itemType=list.get(0).get("Item Type");
					   
					    detailpage.fillAppInfo();
					    detailpage.clickCreateServiceItem();
					    sipage.selectRecordType(recordtype);
						   sipage.clickContinue();
					       //sipage.fillServiceItem(itemType);
					    sipage.clickSave();
					    
					    testReporter.log(LogStatus.PASS, "verifying creating SI on the account");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying creating SI on the account ");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@When("^user is logged in as \"([^\"]*)\"$")
				public void user_is_logged_in_as(String arg1) throws Throwable {
					try {
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						 testReporter.log(LogStatus.PASS, "verifying user is logged in as AAO Super User");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying user is logged in as AAO Super User");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    
				}


				@And("^search for the service item$")
				public void search_for_the_service_item() throws Throwable {
					try {
				    useractionspage.findServiceItem(serviceitemno);
				    useractionspage.clickServiceItem(serviceitemno);
				    
				    testReporter.log(LogStatus.PASS, "accessing si item on hold ");
				}catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "accessing si item on hold ");
					screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
				}  
				}


				@And("^Verify new  status as \"([^\"]*)\"$")
				public void verify_new_status_as(String arg1) throws Throwable {
				    try {
				    boolean b=	sidetailpage.verifyStatus(arg1);
				    System.out.println(b);
				    Assert.assertTrue(b);
				   
				    testReporter.log(LogStatus.PASS, "verifying status as pending");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying status as pending");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    }
				@Then("^update final decision to \"([^\"]*)\" and verify status as \"([^\"]*)\"$")
				public void update_final_decision_to_and_verify_status_as(String arg1, String arg2) throws Throwable {
				    try {
				    	sidetailpage.setFinalDecision(arg1);
				    	boolean b=hld.verifyStatus(arg2);
				    	System.out.println(b);
				    	Assert.assertTrue(b);
				    	testReporter.log(LogStatus.PASS, "updating final decision and verifying status");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "updating final decision and verifying status");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

			
				

				@When("^user updates the final decision to \"([^\"]*)\"$")
				public void user_updates_the_final_decision_to(String arg1) throws Throwable {
				    try {
				    	sidetailpage.setFinalDecision(arg1);
				    	testReporter.log(LogStatus.PASS, "updating the final decision");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "updating final decision");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				  
				}

				@Then("^verify the status as \"([^\"]*)\"$")
				public void verify_the_status_as(String arg1) throws Throwable {
				    try {
				    	Assert.assertTrue(hld.verifyStatus(arg1));
				    	testReporter.log(LogStatus.PASS, "Verifying the status");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying the status");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    	
				   
				}

				@Then("^save the final decision$")
				public void save_the_final_decision() throws Throwable {
				    try {
				    	hld.clickSave();
				    	testReporter.log(LogStatus.PASS, "Saving the final decision");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Saving the final decision");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@When("^responsible party is updated to \"([^\"]*)\"$")
				public void responsible_party_is_updated_to(String arg1) throws Throwable {
				   try {
					  hld.updateParty(arg1);
						testReporter.log(LogStatus.PASS, "updating responsible party");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "updating responsible party");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				 
				}

				@Then("^verify error message is displayed$")
				public void verify_error_message_is_displayed() throws Throwable {
					
					try {
						boolean b=sidetailpage.verifyError();
						Assert.assertTrue(b);
						testReporter.log(LogStatus.PASS, "Verifying error message");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verifying error message");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
						
					}
				
				@When("^user logs in as \"([^\"]*)\"$")
				public void user_logs_in_as(String arg1) throws Throwable {
					try {
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						 testReporter.log(LogStatus.PASS, "verifying user is logged in as AAO Back end file clerk");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying user is logged in as AAO Back end file clerk");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@Then("^access the si item in the previous step$")
				public void access_the_si_item_in_the_previous_step() throws Throwable {
					try {
					    useractionspage.findServiceItem(serviceitemno);
					    useractionspage.clickServiceItem(serviceitemno);
                        testReporter.log(LogStatus.PASS, "Accessing si item");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Accessing si item");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^update responsible party to \"([^\"]*)\"$")
				public void update_responsible_party_to(String arg1) throws Throwable {
				   try {
					   hld.updateParty(arg1);
					   testReporter.log(LogStatus.PASS, "updating responsible party");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "updating responsible party");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^verify status and milestone is updated to \"([^\"]*)\"$")
				public void verify_status_and_milestone_is_updated_to(String arg1) throws Throwable {
				    try {
				    	boolean b=hld.verifyStatus(arg1);
				    	Assert.assertTrue(b);
				    	boolean h=sidetailpage.verifyMileStone(arg1);
				    	Assert.assertTrue(h);
				    	   testReporter.log(LogStatus.PASS, "verifying status and milestone");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying status and milestone");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    }
				

				@Then("^verify tracking record is updated with the following data$")
				public void verify_tracking_record_is_updated_with_the_following_data(DataTable arg1) throws Throwable {
					  try {
					    	List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
						    
						    String em=list.get(0).get("EndMileStone");
						    String bookend=list.get(0).get("Bookend");
						    
					    	boolean b=rec.verifyRightBookendTracking(em, bookend);
					    	Assert.assertTrue(b);
					    	testReporter.log(LogStatus.PASS, "Verifying the tracking record with status and milestone");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "verifying the tracking record with status and milestone");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
				}
				
				
				@Then("^verify the service history is updated with \"([^\"]*)\" by \"([^\"]*)\"$")
				public void verify_the_service_history_is_updated_with_by(String arg1, String arg2) throws Throwable {
					driver.navigate().back();
					try {
				    	Assert.assertTrue(sidetailpage.verifyServiceHistory(arg1, arg2));
				    	testReporter.log(LogStatus.PASS, "Verifying the service item history is updated");
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying the service item history is updated");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    
				}
				
				@When("^user signsin as \"([^\"]*)\"$")
				public void user_signsin_as(String arg1) throws Throwable {
					try {
						useractionspage.goBack();
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						 testReporter.log(LogStatus.PASS, "verifying user is logged in as AAO File Intake Clerk");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying user is logged in as AAO File intake clerk");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^accessing the service item in the previous step$")
				public void accessing_the_service_item_in_the_previous_step() throws Throwable {
					try {
					    useractionspage.findServiceItem(serviceitemno);
					    useractionspage.clickServiceItem(serviceitemno);
                        testReporter.log(LogStatus.PASS, "Accessing si item");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Accessing si item");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@When("^user adds some text \"([^\"]*)\" to Service Item Group Field$")
				public void user_adds_some_text_to_Service_Item_Group_Field(String arg1) throws Throwable {
				    try {
				    	sidetailpage.addServiceItemGroup(arg1);
				    	
                       testReporter.log(LogStatus.PASS, "Add text to service item group field");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Add text to service item group field");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^verify that text \"([^\"]*)\" is added to the Service Item Group Field$")
				public void verify_that_text_is_added_to_the_Service_Item_Group_Field(String arg1) throws Throwable {
				    try {
				    	boolean b=sidetailpage.verifyServiceItemGroup(arg1);
				    	Assert.assertTrue(b);
				    	 testReporter.log(LogStatus.PASS, "Verify  text is added to service item group field");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verify text is added to service item group field");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@And("^verify the label \"([^\"]*)\"  in Adjudication Related List$")
				public void verify_the_label_in_Adjudication_Related_List(String arg1) throws Throwable {
				    try {
				    	 boolean b=sidetailpage.verifyEgregiousPublicSafety(arg1);
				    	 Assert.assertTrue(b);
				    	 testReporter.log(LogStatus.PASS, "Verify  EPS label in Adjudication Related List");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Verify EPS label in Adjudication Related List");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				   
				}


				@Then("^Reopen the service item$")
				public void reopen_the_service_item() throws Throwable {
				    try {
				    	sidetailpage.clickReopen();
				    	 testReporter.log(LogStatus.PASS, "Reopen si item");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "Reopen si item");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				    	
				    }
			

				@Then("^verify the following$")
				public void verify_the_following(DataTable arg1) throws Throwable {
					List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
				    
				    String aaotype=list.get(0).get("AAOType");
				    String motion=list.get(0).get("Motion");
				    String status=list.get(0).get("Status");
				    String party=list.get(0).get("ResponsibleParty");
				    String dec=list.get(0).get("FinalDecision");
				    try {
				    
				    boolean a=editpage.verifyAaoType(aaotype);
				    Assert.assertTrue(a);
				    boolean b=editpage.verifyMotion(motion);
				    Assert.assertTrue(b);
				    boolean c=editpage.verifyLstStatus(status);
				    Assert.assertTrue(c);
				    boolean d=editpage.verifyParty(party);
				    Assert.assertTrue(d);
				    boolean e=editpage.verifyFinalDecision(dec);
				    Assert.assertTrue(e);
				    testReporter.log(LogStatus.PASS, "Verifying the data on the service item edit page");
				    }catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying the data on the service item edit page");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				
				@When("^user updates AAO service Item Type to \"([^\"]*)\"$")
				public void user_updates_AAO_service_Item_Type_to(String arg1) throws Throwable {
				   try {
					   editpage.selectAaoType(arg1);
					   editpage.clickSave();
					   testReporter.log(LogStatus.PASS, "update aao service item type to appeal");
				    }catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "update aao service item type to appeal");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^verify error is displayed$")
				public void verify_error_is_displayed() throws Throwable {
				   try {
					   boolean b=editpage.verifyError();
					   Assert.assertTrue(b);
					   testReporter.log(LogStatus.PASS, "verify error message");
				    }catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify error message");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^update AAO Service item type to \"([^\"]*)\" and Motion type to \"([^\"]*)\"$")
				public void update_AAO_Service_item_type_to_and_Motion_type_to(String arg1, String arg2) throws Throwable {
				   try {
					   editpage.selectAaoType(arg1);
					   editpage.selectMotionType(arg2);
					   editpage.clickSave();
					   testReporter.log(LogStatus.PASS, "update service item type to motion and motion type to service motion to reopen");
				    }catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "update service item type to motion and motion type to service motion to reopen");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				   }
				

				@And("^verify error is displayed and record is not saved$")
				public void verify_error_is_displayed_and_record_is_not_saved() throws Throwable {
				    try {
				    	Assert.assertTrue(editpage.verifyRecordError());
				    	editpage.clickHere();
				    	testReporter.log(LogStatus.PASS, "verify can't save record error");
				    }catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify can't save record error");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				   }



				@When("^user updates responsible party to \"([^\"]*)\"$")
				public void user_updates_responsible_party_to(String arg1) throws Throwable {
					try {
						sidetailpage.clickReopen();
				    	editpage.selectParty(arg1);
				    	 testReporter.log(LogStatus.PASS, "update responsible party");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "update responsible party");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^verify new Si is generated$")
				public void verify_new_Si_is_generated() throws Throwable {
					try {
				    	boolean b=sidetailpage.newSIDisplayed(receiptno);
				    	Assert.assertTrue(b);
				    	 testReporter.log(LogStatus.PASS, "verify new si is generated");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify new si is generated");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}

				@Then("^old SI is displayed as link in the parent serviceitem field$")
				public void old_SI_is_displayed_as_link_in_the_parent_serviceitem_field() throws Throwable {
					try {
				    	boolean b=sidetailpage.verifyNewSI(serviceitemno);
				    	Assert.assertTrue(b);
				    	 testReporter.log(LogStatus.PASS, "verify old si is displayed as a link");
							
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verify old si is displayed as a link");
						screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
				}
				

                @When("^registered user logs in as \"([^\"]*)\"$")
                public void registered_user_logs_in_as(String arg1) throws Throwable {
                	try {
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						testReporter.log(LogStatus.PASS, "verifying user logsin as Aao Super User");
						}catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "verifying user logs in as Aao Super User");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
						}  
                 }

                @When("^user  logged in as \"([^\"]*)\"$")
                public void user_logged_in_as(String arg1) throws Throwable {
                	try {
						loginpage.Aaologout();
						homepage.clickManageUsers();
						homepage.clickUsers();
						homepage.selectAllAaoUsers();
						homepage.searchAaoUser(arg1);
						useractionspage.clickLogInForAaoUser();
						 testReporter.log(LogStatus.PASS, "verifying user is logged in as AAO Super User");
						
					}catch (Exception e) {
						testReporter.log(LogStatus.FAIL, "verifying user is logged in as AAO Super User");
						screenShotPath = GetScreenShot.capture(driver);
						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
						e.printStackTrace();
						extent.endTest(testReporter);
						extent.flush();
						extent.close();
						Assert.assertTrue(false);
					}  
                }

                @Then("^Superuser creates an application with Filetype as \"([^\"]*)\"$")
                public void superuser_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
                	try {
           			 apppage.createApplication(arg1);
           			 receiptno=detailpage.getReceiptNumber();
           			 fileno=detailpage.getFileNumber();
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

                @Then("^Superuser clicks to create \"([^\"]*)\" from application detail$")
                public void superuser_clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

                @Then("^Superuser  creates serviceitem with the following data$")
                public void superuser_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
                	List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
            		
        			String itemtype = list.get(0).get("Item Type");
        			String office = list.get(0).get("Office");
        			String division = list.get(0).get("Division");
        			String branch = list.get(0).get("Branch");
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
        	       sipage.fillServiceItemInfo(itemtype,"Officer",office);
        	  //  sipage.clickSave();
        	 // serviceitemno=sidetailpage.getServiceItemNumber();
        	  	//	System.out.println(serviceitemno);
        	  		 testReporter.log(LogStatus.PASS, "Provide Item Type,Office,division,branch :"+itemtype+" ,"+office);
        		}
        		 catch (Exception e) {
        				testReporter.log(LogStatus.FAIL, "Provide Item Type,Office,division,branch :"+itemtype+" ,"+office);
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
        				testReporter.log(LogStatus.FAIL, "Provide contact,account,business,attorney :"+contact+","+account+","+business+","+attorney);
        				screenShotPath = GetScreenShot.capture(driver);
        				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
        				e.printStackTrace();
        				extent.endTest(testReporter);
        				extent.flush();
        				extent.close();
        				Assert.assertTrue(false);
        	}
        	    
        	    try {
        	    	
        	    	sipage.fillSystemInfo("Internal Contact", internalcontact);
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

                @Then("^verify Superuser is able to create and delete \"([^\"]*)\" Hold$")
                public void verify_Superuser_is_able_to_create_and_delete_Hold(String arg1) throws Throwable {
                	try {
                	hld.createHold(arg1);
					hld.clickServiceItem();
					hld.removeHold(arg1);
					hld.verifyStatus("Pending");
					testReporter.log(LogStatus.PASS, "Verify superuser is able to create and delete Multiple Filings Hold");	
	        }
					 catch (Exception e) {
							testReporter.log(LogStatus.FAIL, "Verify SuperUser is able to create and delete Multiple Filings Hold");
							screenShotPath = GetScreenShot.capture(driver);
							testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
							e.printStackTrace();
							extent.endTest(testReporter);
							extent.flush();
							extent.close();
							Assert.assertTrue(false);
					}
                }

                @Then("^Verify following users are able to create and delete Multiple Filings hold$")
                public void verify_following_users_are_able_to_create_and_delete_Multiple_Filings_hold(DataTable arg1) throws Throwable {
                	 List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
        	    	 
     				for(int i=0; i<list.size(); i++) {
     				String name=	list.get(i).get("User");
     				System.out.println(name);
     				try {
     					
     					loginpage.Aaologout();
     					homepage.clickManageUsers();
     					homepage.clickUsers();
     					homepage.selectAllAaoUsers();
     					homepage.searchAaoUser(name);
     					useractionspage.clickLogInForAaoUser();
     					useractionspage.findServiceItem(serviceitemno);
     				    useractionspage.clickServiceItem(serviceitemno);
     				    hld.createHold("Multiple Filings");
     					hld.clickServiceItem();
     					hld.removeHold("Multiple Filings");
     				Assert.assertTrue(hld.verifyStatus("Pending"));
     				    
     					testReporter.log(LogStatus.PASS, "Verify"+""+name+" is able to create and delete Multiple Filings Hold");
     				}
     				 catch (Exception e) {
     						testReporter.log(LogStatus.FAIL, "Verify"+""+name+" is able to create and delete Multiple Filings Hold");
     						screenShotPath = GetScreenShot.capture(driver);
     						testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
     						e.printStackTrace();
     						extent.endTest(testReporter);
     						extent.flush();
     						extent.close();
     						Assert.assertTrue(false);
     				}
     				}
                }






                @Then("^log out for current scenario AAO End To End$")
                public void log_out_for_current_scenario_AAO_End_To_End() throws Throwable {
					loginpage.Aaologout();
				}

				@Then("^Stop Report Generation for current scenario AAO End To End$")
				public void stop_Report_Generation_for_current_scenario_AAO_End_To_End() throws Throwable {
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
				}

				@Then("^close the browser for current scenario AAO End To End$")
				public void close_the_browser_for_current_scenario_AAO_End_To_End() throws Throwable {
				    driver.close();
				    driver.quit();
				}

				    
		





				   
			


		
	

	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\udanturthy\\\\Downloads\\\\chromedriver_win32_78\\\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		AAO_Stepdefs a=new AAO_Stepdefs();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.get("https://uscis--uatg1.my.salesforce.com/?login");
		ServiceItemDetail d= new ServiceItemDetail(driver);
		ApplicationDetail det= new ApplicationDetail(driver);
		Accounts act=new Accounts(driver);
		ServiceItem s= new ServiceItem(driver);
		LogIn l=new LogIn(driver);
		Home h=new Home(driver);
		UserActions u=new UserActions(driver);
		Hold hld=new Hold(driver);
		TrackingRecord r=new TrackingRecord(driver);
		ServiceItemEdit ed=new ServiceItemEdit(driver);
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			

	ed.verifyRecordError();
	ed.clickHere();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}
