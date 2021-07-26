package com.salesforcetest.test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.main.CaseTestMemberMain;
import com.salesforcetest.main.MultiTypeServiceItemCreator;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.ExtentReporter;
import com.salesforcetest.shared.Utils;

public class CaseTeamMemberTest {
	private WebDriver driver;
	
	private CaseTestMemberMain main = null;
	
	private static ExtentReports extent = ExtentReporter.getInstance().getExtentReports();
	
	private ExtentTest testReporter;
	
	private String serviceItem;
	
	@BeforeClass
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // maximizes
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		
		main = new CaseTestMemberMain(driver);
	}
	
	@BeforeMethod
	public void inject_reporter(Method method) {
		testReporter = extent.startTest(method.getName());
		main.setTestReporter(testReporter);
	}
	
	@Test(testName = "Scenario_16_1", description = "Scenario 16.1", priority=1)
	public void Add_Case_Team_Memeber() throws Exception {
		SalesforceLogin sf = new SalesforceLogin(driver);
		
		//sf.login(Constants.salesforce_username, Constants.salesforce_password);
		sf.login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff");
		MultiTypeServiceItemCreator serviceItemCreator = new MultiTypeServiceItemCreator(driver);
		serviceItemCreator.setTestReporter(testReporter);
		
		String contact = "Test Automation Internal Contact";
		serviceItem = serviceItemCreator.create_pia_service_item_manually(contact);
		
		String teamMember = "Privacy Staff 2";
		main.add_member_in_service_item(teamMember);
		
		sf.logout();
		driver.close();
		driver.quit();
	}
	
	@Test(testName = "Scenario_16_2", description = "Scenario 16.2", priority=2)
	public void View_Edit_SI_As_Case_Team_Memeber() {
		init();
		main.setTestReporter(testReporter);
		SalesforceLogin sf = new SalesforceLogin(driver);
		
		//sf.login(Constants.salesforce_pstaff2_username, Constants.salesforce_pstaff2_password);
		sf.login(Constants.salesforce_pstaff2_username_Admin, Constants.salesforce_pstaff2_password_Admin);
		new SalesforceLogin(driver).internalUserLogin("Privacy Staff 2");
		main.view_edit_si_as_team_member(serviceItem);
		
		sf.logout();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		try {
		extent.endTest(testReporter);
		} catch (Exception e) {
			
		}
	}

	@AfterClass(alwaysRun = true)
	public void flushReporter() {
		extent.flush();
		driver.quit();
	}
}
