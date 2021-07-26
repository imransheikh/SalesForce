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
import com.salesforcetest.pages.aao.QualityControlPercentage;
import com.salesforcetest.pages.aao.ResponseBuilder;
import com.salesforcetest.pages.aao.ResponseDetail;
import com.salesforcetest.pages.aao.ServiceItem;
import com.salesforcetest.pages.aao.ServiceItemDetail;
import com.salesforcetest.pages.aao.ServiceItemEdit;
import com.salesforcetest.pages.aao.TrackingRecord;
import com.salesforcetest.pages.aao.UpdateMultipleServiceItems;
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

public class AAO_Update_Multiple_ServiceItems {
	
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
	public static Hold hld;
	public static String receiptno,fileno;
	public static String serviceitemno,afbserviceitemno;
	public static String Responseno;
	public static ServiceItemEdit editpage;
	public static QualityControlPercentage percentage;
	public static ResponseBuilder builder;
	public static ResponseDetail respdetail;
	public static UpdateMultipleServiceItems updatemultipleitemspage;
	public AaoHelper helper;
	static ExtentReports extent;
	public static String newSINo, emailLink, subjectLine, responseNumber,screenShotPath;
	static ExtentTest testReporter;
	String workingDir = System.getProperty("user.dir");
	
	
	
	
	@Given("^For Update Multiple Service Items scenario set the initial conditions$")
	public void for_Update_Multiple_Service_Items_scenario_set_the_initial_conditions() throws Throwable {
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
	  updatemultipleitemspage=page.getServiceItemsPage();
	  editpage=page.getEdit();
	  hld=page.getHold();
	  loginpage.AaoLogin();
	  
	   useractionspage.navigateToHomePage();
	}

	@When("^For Update Multiple Service Items scenario  Registered user is logged in as \"([^\"]*)\"$")
	public void for_Update_Multiple_Service_Items_scenario_Registered_user_is_logged_in_as(String arg1) throws Throwable {
		try {
		extent = new ExtentReports(workingDir+"\\test-report\\AAOUpdateMultipleSi"+AaoHelper.randomDateTime()+".html", true);
		testReporter = extent.startTest("AAO_Update_Multiple_ServiceItems");
		
		
		 
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
	@Then("^For Update Multiple Service Items scenario Superuser creates an application with Filetype as \"([^\"]*)\"$")
	public void for_Update_Multiple_Service_Items_scenario_Superuser_creates_an_application_with_Filetype_as(String arg1) throws Throwable {
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

	@And("^For Update Multiple Service Items scenario clicks to create \"([^\"]*)\" from application detail$")
	public void for_Update_Multiple_Service_Items_scenario_clicks_to_create_from_application_detail(String arg1) throws Throwable {
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

	@And("^For Update Multiple Service Items scenario creates serviceitem with the following data$")
	public void for_Update_Multiple_Service_Items_scenario_creates_serviceitem_with_the_following_data(DataTable arg1) throws Throwable {
		List<Map<String, String>> list = arg1.asMaps(String.class, String.class);
		
		String itemtype = list.get(0).get("Item Type");
		String office = list.get(0).get("Office");
		
		String officercmts = list.get(0).get("Officer Comments");
		String supervisorcmts = list.get(0).get("Supervisor Comments");
		String supervisor = list.get(0).get("Supervisor");
		String editor = list.get(0).get("Editor");
		String contact = list.get(0).get("Contact");
		String account = list.get(0).get("Account");
		String business= list.get(0).get("Business");
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

	@Then("^For Update Multiple Service Items scenario create AFB service item$")
	public void for_Update_Multiple_Service_Items_scenario_create_AFB_service_item() throws Throwable {
	    try {
	    	sidetailpage.createAfbItem();
	    	editpage.inputAfbItemInfo();
	    
	    	afbserviceitemno=sidetailpage.getServiceItemNumber();
	    	testReporter.log(LogStatus.PASS, "Create AFB service item");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Create AFB service item");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	@Then("^For Update Multiple Service Items scenario verify AAO Total Time Clock field is displayed and  verify the value is \"([^\"]*)\"$")
	public void for_Update_Multiple_Service_Items_scenario_verify_AAO_Total_Time_Clock_field_is_displayed_and_verify_the_value_is(String arg1) throws Throwable {
	    try {
	    	boolean b=sidetailpage.verifyAaoClockAfb(arg1);
	    	Assert.assertTrue(b);
	    	testReporter.log(LogStatus.PASS, "Verify AAO Clock on AFB page layout");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Verify AAO Clock on AFB page layout");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	   
	@Then("^For Update Multiple Service Items scenario navigate to Update Multiple Service Items screen$")
	public void for_Update_Multiple_Service_Items_scenario_navigate_to_Update_Multiple_Service_Items_screen() throws Throwable {
	    try {
	    	useractionspage.goToUpdateMultipleServiceItems();
	    	
	    	testReporter.log(LogStatus.PASS, "navigate to update multiple service items page");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "navigate to update multiple service items page");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}

	@Then("^For Update Multiple Service Items scenario input filenumber and search$")
	public void for_Update_Multiple_Service_Items_scenario_input_filenumber_and_search() throws Throwable {
	    try {
	    	updatemultipleitemspage.selectAction();
	    	updatemultipleitemspage.selectResponsibleParty();
	    	updatemultipleitemspage.enterFileNumber(fileno);
	    	testReporter.log(LogStatus.PASS, "Enter filenumber and search");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Enter file number and search");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	
	@Then("^For Update Multiple Service Items scenario verify AAO and AFB items show up in the search results$")
	public void for_Update_Multiple_Service_Items_scenario_verify_AAO_and_AFB_items_show_up_in_the_search_results() throws Throwable {
	    try {
	    	boolean b=updatemultipleitemspage.verifySearchResults(serviceitemno,"AAO Service Item",afbserviceitemno,"AFB Service Item");
	    	Assert.assertTrue(b);
	    	
	    	testReporter.log(LogStatus.PASS, "verify AAO and AFB items show up");
		}
		 catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "verify AAO and AFB items show up");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				e.printStackTrace();
				extent.endTest(testReporter);
				extent.flush();
				extent.close();
				Assert.assertTrue(false);
	}
	}
	    
	    @Then("^For Update Multiple Service Items scenario click on AAO Service item from the search results$")
	    public void for_Update_Multiple_Service_Items_scenario_click_on_AAO_Service_item_from_the_search_results() throws Throwable {
	        try {
	        	updatemultipleitemspage.clickServiceItem(serviceitemno);
	        	AaoHelper.switch_to_tab(driver,1);
	        	testReporter.log(LogStatus.PASS, "click on AAO Service item from the search results");
			}
			 catch (Exception e) {
					testReporter.log(LogStatus.FAIL, "click on AAO Service item from the search results");
					screenShotPath = GetScreenShot.capture(driver);
					testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
					e.printStackTrace();
					extent.endTest(testReporter);
					extent.flush();
					extent.close();
					Assert.assertTrue(false);
		}
	       
	    }

	    @And("^For Update Multiple Service Items scenario close the AFB Service item$")
	    public void for_Update_Multiple_Service_Items_scenario_close_the_AFB_Service_item() throws Throwable {
	        try {
	        	sidetailpage.editAfbItem();
	        	editpage.editAfbStatus();
	        	testReporter.log(LogStatus.PASS, "close AFB Service item");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "close AFB Service Item");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	    }
	    

	    @And("^For Update Multiple Service Items scenario navigate to Update Multiple Service Items page$")
	    public void for_Update_Multiple_Service_Items_scenario_navigate_to_Update_Multiple_Service_Items_page() throws Throwable {
	    	 try {
	 	    	useractionspage.goToUpdateMultipleServiceItems();
	 	    	AaoHelper.switch_to_tab(driver, 0);
	 	    	testReporter.log(LogStatus.PASS, "navigate to update multiple service items page");
	 		}
	 		 catch (Exception e) {
	 				testReporter.log(LogStatus.FAIL, "navigate to update multiple service items page");
	 				screenShotPath = GetScreenShot.capture(driver);
	 				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	 				e.printStackTrace();
	 				extent.endTest(testReporter);
	 				extent.flush();
	 				extent.close();
	 				Assert.assertTrue(false);
	 	}
	    }

	    @And("^For Update Multiple Service Items scenario search by filenumber$")
	    public void for_Update_Multiple_Service_Items_scenario_search_by_filenumber() throws Throwable {
	    	  try {
	  	    	updatemultipleitemspage.selectAction();
	  	    	updatemultipleitemspage.selectResponsibleParty();
	  	    	updatemultipleitemspage.enterFileNumber(fileno);
	  	    	testReporter.log(LogStatus.PASS, "Enter filenumber and search");
	  		}
	  		 catch (Exception e) {
	  				testReporter.log(LogStatus.FAIL, "Enter file number and search");
	  				screenShotPath = GetScreenShot.capture(driver);
	  				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	  				e.printStackTrace();
	  				extent.endTest(testReporter);
	  				extent.flush();
	  				extent.close();
	  				Assert.assertTrue(false);
	  	}
	    }

	    @Then("^For Update Multiple Service Items scenario verify AFB Service Item is not returned and AAO Service Item is returned$")
	    public void for_Update_Multiple_Service_Items_scenario_verify_AFB_Service_Item_is_not_returned_and_AAO_Service_Item_is_returned() throws Throwable {
	        try {
	        	updatemultipleitemspage.verifyAaoServiceItemReturned(serviceitemno);
	        	
	        	testReporter.log(LogStatus.PASS, "Verify only AAO Service Item is returned");
	  		}
	  		 catch (Exception e) {
	  				testReporter.log(LogStatus.FAIL, "Verify only AAO Service Item is returned");
	  				screenShotPath = GetScreenShot.capture(driver);
	  				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
	  				e.printStackTrace();
	  				extent.endTest(testReporter);
	  				extent.flush();
	  				extent.close();
	  				Assert.assertTrue(false);
	  	}
	    }
	    

	    @Then("^log out for Current scneario AAO Update Multiple Service Items$")
	    public void log_out_for_Current_scneario_AAO_Update_Multiple_Service_Items() throws Throwable {
	    	loginpage.Aaologout();
	    }

	    @Then("^Stop Report Generation for current scenario AAO Update Multiple Service Items$")
	    public void stop_Report_Generation_for_current_scenario_AAO_Update_Multiple_Service_Items() throws Throwable {
	    	extent.endTest(testReporter);
			extent.flush();
			extent.close();
	    }

	    @Then("^close the browser for current scenario AAO Update Multiple Service Items$")
	    public void close_the_browser_for_current_scenario_AAO_Update_Multiple_Service_Items() throws Throwable {
	    	driver.close();
		    driver.quit();
	    }


		    

	  
	 




	

	
	    
	

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\udanturthy\\\\Downloads\\\\chromedriver_win32 _2.44\\\\chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		AAO_Update_Multiple_ServiceItems a=new AAO_Update_Multiple_ServiceItems();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.get("https://uscis--uatg.my.salesforce.com/?login");
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
		UpdateMultipleServiceItems p=new UpdateMultipleServiceItems(driver);
		
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			

	p.selectResponsibleParty();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
