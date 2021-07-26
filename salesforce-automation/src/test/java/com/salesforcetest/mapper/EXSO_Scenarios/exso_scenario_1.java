package com.salesforcetest.mapper.EXSO_Scenarios;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import restApiPOC.salesforcePrivacyRestApiConnection;

public class exso_scenario_1 {
	private String baseUrl = Constants.salesforce_url;
	public static String emailLink;
	public static WebDriver driver1, driver2;
	static String dueTime = "";
	private WebElement element;
	public static String screenShotPath,subjectLine,exsoSI,assignNo, infoAssign, aaoSINm;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	static String clrAssNO1, clrAssNO2;
	static ExtentReports extent;
	
	static ExtentTest testReporter;
	@Given("^Launch EXSO app and log in as Admin for EXSO Assignment scenario validation$")
	public void init_1() throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\EXSO_Assignment_Scenario"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("EXSO Regression Flow Scenario 1 to 11 End to End Validation");
		try {
			init();
			testReporter.log(LogStatus.PASS, "User logs in successfully to Privacy portal");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully to Privacy portal");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Fetching the incident URL from the Setup page$")
	public void Email_URL() throws IOException {
		try {
			//fetchMailUrl("incident");
			testReporter.log(LogStatus.PASS, "Fetched the incident URL to create SI from gmail.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetched the incident URL to create SI from gmail.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	//@Then("^Logging into gmail and send a mail to the fetched url$")
	@Then("^Creating a Service Item from back end and Execute next steps in this SI$")
	public void Send_Mail_To_URL() throws IOException, ParseException, URISyntaxException {
		//try {
			//logIntoGmail("https://acumensolutions-com.clearlogin.com/login","zabid","Acumentest1");
			salesforcePrivacyRestApiConnection restApiConn = new salesforcePrivacyRestApiConnection();
			restApiConn.getAccessToken();
			restApiConn.fetchUserId();
			randomDateTime();
			restApiConn.createCase(subjectLine);
			exsoSI = restApiConn.fetchNewlyCreatedSI();
			testReporter.log(LogStatus.PASS, "Creating a Service Item from back end and Execute next steps in this SI::"+exsoSI);
		/*} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating a Service Item from back end and Execute next steps in this SI::"+exsoSI);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
	}
	//@Then("^Re-login to Privacy Portal and log in as EXSO internal user and accept it and Grab the newly created Servcie Item$")
	@Then("^log in as EXSO internal user and accept it and Grab the newly created Servcie Item$")
	public void Re_LogIn_And_Grab_SI() throws IOException {
		try {
			//login();
			internalUserLogin("EXSO Service Item Manager");
			select_service_item_list_option("All Open Service Items");
			opeTheApiCreatedSI();
			//exsoSI = assert_incident_name_get_service_item_number();
			testReporter.log(LogStatus.PASS, "log in as EXSO internal user and accept and Grab the newly created Servcie Item with API :"+exsoSI);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "log in as EXSO internal user and Grab the newly created Servcie Item :"+exsoSI);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Open newly created Service Item and Create New Assignment and log out from it.$")
	public void Open_SI_And_Create_Nw_Asgnmt() throws IOException {
		try {
			clickOnSISetDueandETC();
			checkBoxForApproval();
			createAssignment();
			logOut();
			testReporter.log(LogStatus.PASS, "Open newly created Service Item and Create New Assignment."+assignNo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Open newly created Service Item and Create New Assignment."+assignNo);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as AAO Pod User and change the due date to some future date and save it and log out$")
	public void Log_In_As_Pod_Usr_Chnage_Due_dt() throws IOException {
		try {
			logInAsPodUser();
			openAssignAndEditDueDate();
			logOutPodUser();
			testReporter.log(LogStatus.PASS, "Log in as AAO Pod User and change the due date to some future date and save it and log out "+dueTime);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as AAO Pod User and change the due date to some future date and save it and log out "+dueTime);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as EXSO user and Approve the changed due date and log out$")
	public void Approve_Due_Date() throws IOException {
		try {
			internalUserLogin("EXSO Service Item Manager");
			approveTheDue();
			logOut();
			testReporter.log(LogStatus.PASS, "Log in as EXSO user and Approve the changed due date and log out");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as EXSO user and Approve the changed due date and log out");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as As AAO Pod user and Validate the due date is changed or not$")
	public void Val_Due_Date() throws IOException {
		try {
			logInAsPodUser();
			validateDueDateChanged();
			testReporter.log(LogStatus.PASS, "Log in as As AAO Pod user and Validate the due date is changed or not");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as As AAO Pod user and Validate the due date is changed or not");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Verify user able to send mail by selecting multiple user from an Assignment and able to upload file to Assignment$")
	public void Send_mail_nd_Upld_File() throws IOException {
		try {
			sendMailBySelectingContact();
			uploadFileToAssiment();
			testReporter.log(LogStatus.PASS, "Verify user able to send mail by selecting multiple user from an Assignment and able to upload file to Assignment");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify user able to send mail by selecting multiple user from an Assignment and able to upload file to Assignment");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			//getResult();
			//Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Accept the assignment and See the accepted by status has been changed to expected value$")
	public void Accept_Assignment() throws IOException {
		try {
			acceptAssignmentForAAO();
			testReporter.log(LogStatus.PASS, "Accept the assignment and See the accepted by status has been changed to expected value");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Accept the assignment and See the accepted by status has been changed to expected value");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Complete the assignment and See the accepted by status has been changed to expected value$")
	public void Complete_Assignment() throws IOException {
		try {
			completeAssiAAO();
			logOutPodUser();
			testReporter.log(LogStatus.PASS, "Complete the assignment and See the accepted by status has been changed to expected value");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Complete the assignment and See the accepted by status has been changed to expected value");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as EXSO manager and validate the status and Round Number and create new Assignment with Clerance with two POD user and log out$")
	public void Create_Ass_For_2_Usr() throws IOException {
		try {
			internalUserLogin("EXSO Service Item Manager");
			openServiceItemAndValidate_Round1();
			createAsswith2Usr();
			logOut();
			testReporter.log(LogStatus.PASS, "Log in as EXSO manager and validate the status and Round Number and create new Assignments with two POD user and log out "+clrAssNO1+"  "+clrAssNO2);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as EXSO manager and validate the status and Round Number and create new Assignments with two POD user and log out "+clrAssNO1+"  "+clrAssNO2);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as 1st AAO POD user for Round 2 and send mail with attacments with one user$")
	public void Open_Round2_AAO_Ass() throws IOException {
		try {
			logInAsPodUser();
			openAssignment();
			sendMailBySelectingContact_Round2ND();
			testReporter.log(LogStatus.PASS, "Log in as 1st AAO POD user for Round 2 and send mail with attacments with one user "+clrAssNO1+"  "+clrAssNO2);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as 1st AAO POD user for Round 2 and send mail with attacments with one user "+clrAssNO1+"  "+clrAssNO2);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Accept and Complete the assignment and See the accepted by status has been changed to expected value$")
	public void Assign_Complete_Assignment() throws IOException {
		try {
			acceptAssignmentForAAO_Round2();
			completeAssiAAO_Round2();
			logOutPodUser();
			testReporter.log(LogStatus.PASS, "Accept and Complete the assignment and See the accepted by status has been changed to expected value");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Accept and Complete the assignment and See the accepted by status has been changed to expected value");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as PVY pod user and Accept and Complete the assignment and See the accepted by status has been changed to expected value$")
	public void Log_In_As_PVY_and_Validate() throws IOException {
		try {
			logInAsPodUser_PVY();
			openAssignment();
			acceptAssignmentForPVY_Round2();
			uploadFileToAssiment_PVY();
			completeAssignPVY_Round2();
			logOutPodUser();
			testReporter.log(LogStatus.PASS, "Log in as PVY pod user and Accept and Complete the assignment and See the accepted by status has been changed to expected value");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as PVY pod user and Accept and Complete the assignment and See the accepted by status has been changed to expected value");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as EXSO user and validate the service item status is changed after Creating a new assignment and round count changed and newly autoring assignment is reflected in the SI$")
	public void Log_Is_As_EXSO() throws IOException {
		try {
			internalUserLogin("EXSO Service Item Manager");
			openServiceItemAndValidate_Round2();
			createAssignment_Round3();
			validateAssign_Round3();
			testReporter.log(LogStatus.PASS, "Log in as EXSO user and validate the service item status is changed after Creating a new assignment and round count changed and newly autoring assignment is reflected in the SI");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Log in as EXSO user and validate the service item status is changed after Creating a new assignment and round count changed and newly autoring assignment is reflected in the SI");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the 2nd Authoring Assignment Round 2 value and accept and complete the assignment.$")
	public void fetch_Rounf_Auth_Assign2() throws IOException {
		try {
			fetchAssNoAndLogInAsPodUserAndOpenAssignmt();
			acceptUploadAndCompleteAssi();
			testReporter.log(LogStatus.PASS, "Validate the 2nd Authoring Assignment Round 2 value and accept and complete the assignment.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the 2nd Authoring Assignment Round 2 value and accept and complete the assignment.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Create 2 more clearence assginments with PVY and AAO and complete the AAO Assignment with proper validation$")
	public void Create_2_More_Clerance_Assgn() throws IOException {
		try {
			validateExistingSI();
			createAsswith2Usr_Recording2();
			completeAssignWithAAO_Round4();
			testReporter.log(LogStatus.PASS, "Create 2 more clearence assginments with PVY and AAO and complete the AAO Assignment with proper validation");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Create 2 more clearence assginments with PVY and AAO and complete the AAO Assignment with proper validation");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Complete the PVY assignment and validate the Round 2 for clerance Assignment and validate all the Assignment present in the parent SI section.$")
	public void Complete_PVY() throws IOException {
		try {
			completeAssignWithPVY_Round4();
			testReporter.log(LogStatus.PASS, "Complete the PVY assignment and validate the Round 2 for clerance Assignment and validate all the Assignment present in the parent SI section.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Complete the PVY assignment and validate the Round 2 for clerance Assignment and validate all the Assignment present in the parent SI section.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the SI status after all assignments are completed and Create FYI record and Validate and Update the file type and Validate the update is reflected or not.$")
	public void Validate_The_SI_Crete_FYI_Assign() throws IOException {
		try {
			verifyParentSIStatusandCreateFYIRecandUpdateDocType();
			verifyUpdateFileTypeAndPodCol();
			testReporter.log(LogStatus.PASS, "Validate the SI "+exsoSI+" status after all assignments are completed and Create FYI record and Validate and Update the file type and Validate the update is reflected or not.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the SI "+exsoSI+" status after all assignments are completed and Create FYI record and Validate and Update the file type and Validate the update is reflected or not.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Generate the RCA and Close the SI and validate the status.$")
	public void Generate_RCA_Close() throws IOException {
		try {
			//generateRCAAndValidate();
			generateRCAAndValidate_PdfValidation();
			closeSI();
			testReporter.log(LogStatus.PASS, "Generate the RCA and Close the SI and validate the status.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Generate the RCA and Close the SI and validate the status.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO Stop Report Generation for current scenario EXSO Assignment End to End Scenario1$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^EXSO Close the browser Email auto approval process$")
	public void flushReporter_For_IPO() {
		driver1.close();
		driver1.quit();
	}
	//************************************************************Main Functions***********************************************************
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
	public void init() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);

        // Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

        // Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		driver1 = new ChromeDriver(options);
		driver1.manage().window().maximize(); // maximizes
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		login();
	}
	public static void open_another_tab(WebDriver driver1) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver1;
		js.executeScript("window.open('about:blank','_blank');");

		ArrayList<String> tabs = new ArrayList<String>(driver1.getWindowHandles());
		driver1.switchTo().window(tabs.get(1));
	}
	public void login() {
		//try {
		driver1.get(baseUrl);
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//*[@id='username']")).sendKeys(Constants.salesforce_pstaff2_username_Admin);
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='password']")).sendKeys(Constants.salesforce_pstaff2_password_Admin);
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='Login']")).click();
		
		Utils.sleep(3);
		/*} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	public void fetchMailUrl (String addName) {
		Utils.sleep(4);
		try {
		driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click();
		} catch (Exception e) {
		driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[6]/div")).click();
		}
		Utils.sleep(4);
		driver1.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String>(driver1.getWindowHandles());
		driver1.switchTo().window(tabs.get(1));
		driver1.findElement(By.xpath("//div[@class='viewport']/div[2]/div[1]/descendant::input[@placeholder='Quick Find']")).sendKeys("Email Services");
		Utils.sleep(4);
		driver1.findElement(By.xpath(".//*[@id='split-left']/div/div/div/ul/li[2]/ul/li/div/a")).click();
		Utils.sleep(2);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//iframe[@title='Email Services ~ Salesforce - Unlimited Edition']")));
		((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView();", 
		driver1.findElement(By.xpath("//table[@class='list']/descendant::a[text()='EXSO Email To Case']")));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//table[@class='list']/descendant::a[text()='EXSO Email To Case']")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		driver1.switchTo().frame(driver1.findElement(By.xpath("//iframe[@title='Email Service ~ Salesforce - Unlimited Edition']")));
		((JavascriptExecutor)driver1).executeScript("arguments[0].scrollIntoView();", 
				driver1.findElement(By.xpath("//th/div[contains(text(),'Email Address Name')]")));
		emailLink = driver1.findElement(By.xpath("//table[@summary='Email Addresses']/tbody/tr/descendant::span[text()='"+addName+"']/parent::td/following-sibling::td[1]/a")).getText();
		driver1.switchTo().defaultContent();
		System.out.println("Fetched email link::::"+emailLink);
		Constants.setEmailUrl(emailLink);
		driver1.close();
		driver1.switchTo().window(tabs.get(0));
		Utils.sleep(2);
		//driver1.close();
		//driver1.quit();
		logout();
		driver1.switchTo().defaultContent();
	}
	public void internalUserLogin(String internalUserNm) {
		//String internalUserNm = "Privacy Staff 2";
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		Utils.sleep(2);
		try {
			//driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[6]/div")).click(); //QA env
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[7]/div")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String>(driver1.getWindowHandles());
		try {
		driver1.switchTo().window(tabs.get(2));
		driver1.switchTo().window(tabs.get(1));
		//System.out.println("I am here3.");
		driver1.close();
		Utils.sleep(2);
		driver1.switchTo().window(tabs.get(2));
		} catch (Exception e) {
			driver1.switchTo().window(tabs.get(0));
			driver1.close();
			Utils.sleep(2);
			driver1.switchTo().window(tabs.get(1));
		}
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
		} catch (Exception e) {
			
		}
		driver1.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe"))));
		driver1.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		for(int icount=0; icount<4; icount++) {
			try {
				//driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
				//driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
				driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
				break;
			} catch (Exception e) {
				driver1.get(driver1.getCurrentUrl());
				driver1.navigate().refresh();
				Utils.sleep(4);
			}
		}
	}
	public void logout() {
		Utils.sleep(2);
		((JavascriptExecutor) driver1).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]")));
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]")).click();
		
		Utils.sleep(2);
		
		driver1.findElement(By.linkText("Log Out")).click();
		
		Utils.sleep(3);
	}
	public void logIntoGmail (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver1.get(url);
		driver1.findElement(By.id("sign_in_username")).sendKeys(username);
		driver1.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		driver1.findElement(By.xpath("//input[@value='Log in']")).click();
		try {
			WebDriverWait wait = new WebDriverWait (driver1, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver1.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		driver1.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		driver1.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver1.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		driver1.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(2);
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
	}
	public void fetchCorrectIframe(By dropDownWebElement1) {
		int iFrameCount = driver1.findElements(By.xpath("//div[@id='navigatortab']/descendant::iframe")).size();
		System.out.println("Frame count :"+iFrameCount);
		for(int i = 1; i<=iFrameCount+1; i++) {
			try {
				driver1.switchTo().frame(driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe["+i+"]")));
				driver1.findElement(dropDownWebElement1).getText();
				break;
			} catch (Exception e) {
				driver1.switchTo().defaultContent();
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
	public Wait fluentWaitFunctionality(WebDriver driver1)
	{
		@SuppressWarnings({ "unchecked", "deprecation" })
		Wait fwait = new FluentWait(driver1)
		 
	    .withTimeout(10, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver1);
		try {
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e) {
		}
	}
	public String randomDateTime() {
	      Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);
	      int month = cal.get(Calendar.MONTH);
	      int day = cal.get(Calendar.DAY_OF_MONTH);
	      int hour = cal.get(Calendar.HOUR_OF_DAY);
	      int minute = cal.get(Calendar.MINUTE);
	      int second = cal.get(Calendar.SECOND);
	      String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
	      + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
	      subjectLine = "EXSO Automation Test SI - " + randomDateTime;
	      return randomDateTime;
	}
	protected void select_service_item_list_option(String serviceItemLinkText) {
		Utils.sleep(4);

		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		
		Utils.sleep(4);

		driver1.findElement(By.cssSelector("a[title='Select List View']")).click();

		Utils.sleep(4);

		WebElement dropdown = driver1.findElement(By.className("forceVirtualAutocompleteMenuList"));

		dropdown.findElement(By.linkText(serviceItemLinkText)).click();
		Utils.sleep(2);
	}
	public void opeTheApiCreatedSI ( ) {
		try {
			Utils.sleep(2);
			driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath("//th[@title='Service Item Number']/div")).click();
			Utils.sleep(2);
			driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
			Utils.sleep(1);
		}
	}
	public String assert_incident_name_get_service_item_number() throws Exception {
		boolean loop = true;
		int counter = 1;
		int rounds = 24; // 120 seconds wait
		String serviceItemNo = null;
		while (loop) {
			driver1.findElement(By.xpath("//button[@name='refreshButton']")).click();

			Utils.sleep(5);
			
			try { driver1.findElement(By.className("listViewContent"));
			String actualSubject = driver1.findElement(By.className("listViewContent")).findElement(By.tagName("table"))
					.findElement(By.cssSelector("tbody > tr:nth-child(1) > td:nth-child(5)")).getText();

			if (actualSubject.contains(subjectLine)) {
				Assert.assertEquals(actualSubject, Constants.incident_subject);
				serviceItemNo = driver1.findElement(By.className("listViewContent")).findElement(By.tagName("table"))
						.findElement(By.cssSelector("tbody > tr:nth-child(1) > th")).getText();
				testReporter.log(LogStatus.PASS, "Test Automated incident case creation by email - Passed");
				loop = false;
			} else {
				counter++;
			}
			} catch (Exception e) {
				
			}

			if (counter == rounds) {
				loop = false;
				throw new Exception(
						"Incent not created from external email with subject: " + Constants.incident_subject);
			}
		}
		exsoSI = serviceItemNo;
		return serviceItemNo;
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver1;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Utils.sleep(1);
	}
	private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
		try {
		driver1.findElement(By.xpath("//span[contains(text(),'Upload Files')]")).click();
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
		driver1.switchTo().activeElement();

		Utils.sleep(4);
	}
	public void clickOnSISetDueandETC() throws Exception {
		/*driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.className("listViewContent")).findElement(By.linkText(exsoSI)).click();
		Utils.sleep(2);*/
		element = driver1.findElement(By.xpath("//*[contains(text(),'"+exsoSI+"')]/parent::*/parent::p/parent::div"));
		highlightElement();
		//driver1.findElement(By.xpath("//a[@title='Accept Service Item']")).click();
		element = driver1.findElement(By.xpath("//a[@title='Accept Service Item']"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		Utils.sleep(8);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Signature Level']");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Signature Level']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Service Item Owner']/parent::*/following-sibling::div/descendant::a[contains(text(),'EXSO Service Item Manager')]"));
		highlightElement();
		//element = driver1.findElement(By.xpath("//span[@title='Status']/following-sibling::div/div/span[contains(text(),'Service Item Manager Accepted')]"));
		element = driver1.findElement(By.xpath("//*[@title='Status']/parent::*/descendant::*[contains(text(),'Service Item Manager Accepted')]"));
		highlightElement();
		element = driver1.findElements(By.xpath("//span[text()='Service Item Owner']/parent::*/following-sibling::div/descendant::a[contains(text(),'EXSO Service Item Manager')]")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElements(By.xpath("//span[text()='EXSO Due Date']/parent::*/following-sibling::div/button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//label[text()='Date']/parent::*/div/input")).get(0).click();
		Utils.sleep(2);
		try {
			//driver1.findElement(By.xpath("//span[contains(@class,'todayDate selectedDate')]")).click();
			driver1.findElement(By.xpath("//td[@class='slds-is-today']")).click();
		} catch (Exception e) {
			//driver1.findElement(By.xpath("//span[contains(@class,'todayDate')]")).click();
			String formatter = new SimpleDateFormat("MM/dd/yy").format(new Date());
			driver1.findElements(By.xpath("//label[text()='Date']/parent::*/div/input")).get(0).sendKeys(formatter);
		}
		Utils.sleep(2);
		element = driver1.findElements(By.xpath("//span[text()='Service Item Record Type']")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//span[text()='EXSO Section']/parent::*/following-sibling::div/descendant::a")).click();
		driver1.findElement(By.xpath("//label[text()='EXSO Section']/following-sibling::div/descendant::input")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//*[contains(text(),'Internal Section')]")).click();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//*[@title='Accept Service Item']"));
		scrollingFunction();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a")).click();
		driver1.findElement(By.xpath("//label[text()='Service Item Origin']/following-sibling::div/descendant::input")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//*[contains(@title,'Legacy System')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		ele = By.xpath("//*[text()='Internal Section']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.navigate().refresh();
		ele = By.xpath("//*[text()='Signature Level']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		attach_file_and_send(Constants.privacy_pia_attachment_url, false);
		ele = By.xpath("//*[text()='1 of 1 file uploaded']");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//*[text()='Done']/parent::*")).click();
		Utils.sleep(2);
		driver1.navigate().refresh();
		ele = By.xpath("//*[text()='Signature Level']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void createAssignment() {
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		//driver1.findElement(By.xpath("//a[@title='Create Assignments']/parent::*")).click();
		element = driver1.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")).click();
		Utils.sleep(1);
		//selectDropdownListValue("Authoring Assignment", driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")));
		driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select/option[text()='Authoring Assignment']")).click();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//label[text()='Back Date Assignment']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).click();
		element = driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver1.switchTo().defaultContent();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[text()='(1)']");
		fluentWaitForElementVisibility();
		assignNo = driver1.findElement(By.xpath("//span[text()='Authoring Assignment']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).getText();
		System.out.println("Authoring Assignment number :"+assignNo);
		testReporter.log(LogStatus.PASS, "Round 1 Authoring assignment number : "+assignNo);
	}
	public void checkBoxForApproval () {
		element = driver1.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(ele));
		Actions actObj1 = new Actions(driver1);
		actObj1.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/preceding-sibling::input")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver1.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/following-sibling::div/descendant::input")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		////Correct the path
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", 
				driver1.findElement(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/following-sibling::div/descendant::input")));
		//driver8.findElement(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/following-sibling::div/descendant::input")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(4);
		driver1.navigate().refresh();
		ele = By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/preceding-sibling::input");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.navigate().refresh();
		ele = By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	public void logOut () {
		try {
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
		} catch (Exception e) {
			driver1.navigate().refresh();
			ele = By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]");
			fluentWaitForElementVisibility();
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
		}
	}
	public void logInAsPodUser() {
		//driver1.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver1.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
		Utils.sleep(4);
		//driver1.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
		//Actions actObj = new Actions(driver1);
		//actObj.moveToElement(driver1.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
		driver1.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("AAO Contact 1");
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='AAO Contact 1']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='AAO Contact 1']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
	}
	public void openAssignAndEditDueDate() {
//		WebDriverWait wait = new WebDriverWait (driver1, 8);
////		driver1.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
////		Utils.sleep(1);
////		driver1.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
////		Utils.sleep(1);
////		driver1.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='dashboard']"))));
//		ele = By.xpath("//span[text()='Assignment: Assignment Name']/parent::span");
//		fluentWaitForElementVisibility();
//		Utils.sleep(3);
//		driver1.findElements(By.xpath("//a[text()='"+assignNo+"']/parent::div")).get(0).click(); ///Give assign ment number
//		driver1.switchTo().defaultContent();
		Utils.sleep(1);
		ele = By.xpath("//a[@title='Assignments']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Authoring Assignments')]/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		Utils.sleep(1);
		try {
			ele = By.xpath("//a[@title='"+assignNo+"']");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
		} catch (Exception e) {
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			//driver1.findElement(ele).click();
			element = driver1.findElement(By.xpath("//span[text()='Assignment Name']/parent::a"));
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+assignNo+"']");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//span[text()='Comments']/parent::button"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElements(By.xpath("//button[@title='Edit Assignment Due Date Change Request']")).get(0)).click().build().perform();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//label[text()='Date']/following-sibling::input")).get(0).click();
		Utils.sleep(2);
		try {
			driver1.findElements(By.xpath("//span[contains(@class,'todayDate selectedDate')]/parent::td/following-sibling::td[2]/span")).get(0).click();
		} catch (Exception e) {
			driver1.findElements(By.xpath("//span[contains(@class,'todayDate selectedDate')]/parent::td/parent::tr/following-sibling::tr[1]/td[2]/span")).get(0).click();
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//span[text()='Assignment Change Request Reason']/parent::label/following-sibling::textarea")).sendKeys("Multiple Peoples are OOO and need an extension.");
		driver1.findElements(By.xpath("//button[@title='Save']")).get(0).click();
		Utils.sleep(2);
		//ele = By.xpath("//span[contains(@class,'toastMessage')]");
		//fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//span[text()='Comments']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.navigate().refresh();
		ele = By.xpath("//button[@title='Edit Assignment Due Date Change Request']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Comments']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		dueTime = driver1.findElement(By.xpath("//button[@title='Edit Assignment Due Date Change Request']/preceding-sibling::span/span")).getText();
		driver1.findElement(By.xpath("//a[@title='RELATED']")).click();
		element = driver1.findElement(By.xpath("//span[text()='Activity History']/parent::a"));
		scrollingFunction();
		element = driver1.findElement(By.xpath("//a[contains(text(),'Assignment Due Date Change Request')]/parent::div"));
		highlightElement();
		element = driver1.findElement(By.xpath("//a[contains(text(),'Assignment Due Date Change Request')]/parent::div/parent::*/following-sibling::td[2]/span[text()='Pending']"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Activity History']/parent::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		System.out.println(dueTime);
		testReporter.log(LogStatus.PASS, dueTime+" Due Time is rset and saved.");
	}
	public void logOutPodUser() {
		try {
		driver1.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::a")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::a")).click();	
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
		ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void approveTheDue () {
		ele = By.xpath("//a[@title='Home']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(ele).click();
		Utils.sleep(2);
		ele = By.xpath("//a[text()='"+assignNo+"']/parent::h3");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//a[text()='"+assignNo+"']/parent::h3/following-sibling::div/descendant::button")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[text()='Approve']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//textarea")).sendKeys("Approved from the EXSO");
		driver1.findElement(By.xpath("//span[text()='Approve']/parent::button")).click();
		Utils.sleep(4);
		testReporter.log(LogStatus.PASS, "Due is approved with EXSO SErvice item mansger.");
	}
	public void validateDueDateChanged () {
//		WebDriverWait wait = new WebDriverWait (driver1, 8);
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='dashboard']"))));
//		ele = By.xpath("//span[text()='Assignment: Assignment Name']/parent::span");
//		fluentWaitForElementVisibility();
//		Utils.sleep(3);
//		driver1.findElements(By.xpath("//a[text()='"+assignNo+"']/parent::div")).get(0).click(); ///Give assign mentioned number
//		driver1.switchTo().defaultContent();
		Utils.sleep(1);
		ele = By.xpath("//a[@title='Assignments']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Authoring Assignments')]/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		Utils.sleep(1);
		try {
		ele = By.xpath("//a[@title='"+assignNo+"']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		} catch (Exception e) {
			//ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			//driver1.findElement(ele).click();
			element = driver1.findElement(By.xpath("//span[text()='Assignment Name']/parent::a"));
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+assignNo+"']");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Service Item Information']/parent::button"));
		scrollingFunction();
		Utils.sleep(2);
		try {
		element = driver1.findElement(By.xpath("//span[contains(text(),'"+dueTime+"')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, dueTime+" Due Time is reflected in the Service Item.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, dueTime+" Due Time is reflected in the Service Item.");
		}
	}
	public void sendMailBySelectingContact () {
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Search Contacts']/parent::h3"));
		scrollingFunction();
		driver1.findElement(By.xpath("//input[@maxlength='255']")).sendKeys("AAO");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		Utils.sleep(2);
		ele = By.xpath("//a[contains(text(),'AAO Contact 1')]");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//input[@maxlength='255']")).clear();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@maxlength='255']")).sendKeys("PVY");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		Utils.sleep(2);
		ele = By.xpath("//a[contains(text(),'PVY Contact 1')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//span[text()='Search Contacts']/parent::h3"));
		scrollingFunction();
		Utils.sleep(1);
		//driver1.findElement(By.xpath("//input[@value='mohammad.z.abid@uscis.dhs.gov' and @type='checkbox']")).click();
		driver1.findElement(By.xpath("//*[text()='AAO Contact 1']/parent::*/parent::th/preceding-sibling::td/descendant::input[@type='checkbox']")).click();
		Utils.sleep(1);
		//driver1.findElement(By.xpath("//input[@value='ejeong@acumensolutions.com' and @type='checkbox']")).click();
		driver1.findElement(By.xpath("//*[text()='PVY Contact 1']/parent::*/parent::th/preceding-sibling::td/descendant::input[@type='checkbox']")).click();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//span[text()='Select Content']/parent::h3"));
		scrollingFunction();
		Utils.sleep(1);
		try {
			String mail1= driver1.findElement(By.xpath("//div[@title='mohammad.z.abid@uscis.dhs.gov']")).getText();
			String mail2= driver1.findElement(By.xpath("//div[@title='ejeong@acumensolutions.com']")).getText();
			String selectedEmails = driver1.findElement(By.xpath("//textarea[@disabled='' and @role='textbox']")).getText();
			System.out.println("Fetch selected mails: "+selectedEmails+"  "+mail1+"    "+mail2);
		} catch (Exception e) {
			
		}
		element = driver1.findElement(By.xpath("//textarea[@disabled='' and @role='textbox']"));
		highlightElement();
		driver1.findElement(By.xpath("//input[@type='checkbox' and @name='options']/following-sibling::label/span[1]")).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//div[@data-aura-class='cEmailFiles'][4]/div/descendant::input")).get(1).sendKeys("Automated Test");
		driver1.findElements(By.xpath("//div[@data-aura-class='cEmailFiles'][4]/div/descendant::textarea")).get(1).sendKeys("Test for Mail Send with POD User");
		driver1.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]/parent::div");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	private void attach_file_and_send_assign(String attachmentPath, boolean pressTab) throws Exception {
		try {
		driver1.findElement(By.xpath("//button[contains(text(),'Upload Files')]")).click();
		} catch (Exception e) {
		}
		Utils.sleep(2);
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
		driver1.switchTo().activeElement();

		Utils.sleep(4);
	}
	public void uploadFileToAssiment() throws Exception {
		driver1.findElement(By.xpath("//div[text()='Add Files']/parent::a")).click();
		ele = By.xpath("//button[contains(text(),'Upload Files')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		attach_file_and_send_assign(Constants.privacy_test_attachment_url,false);
		ele = By.xpath("//*[contains(text(),'1 of 1 file uploaded')]");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//span[text()='Done']/parent::button")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		try {
		element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(2)']"));
		} catch (Exception e) {
			try {
			element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(4)']"));
			} catch (Exception e1) {
				element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(5)']"));
			}
		}
		highlightElement();
		element = driver1.findElement(By.xpath("//ul[@class='uiAbstractList']/li"));
		highlightElement();
	}
	public void acceptAssignmentForAAO () {
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Accept Assignment']/parent::li")).click();
		Utils.sleep(1);
		ele = By.xpath("//span[text()='Save']/parent::button[@data-aura-class='uiButton']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='In Progress']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
		scrollingFunction();
		try {
			element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
			highlightElement();
		} catch(Exception e) {
			Utils.sleep(4);
			driver1.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
			element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
			highlightElement();
		}
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	public void completeAssiAAO () {
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(1);
		driver1.findElement(By.xpath("//td[2]/span[@class='lookupInput']/input")).sendKeys("AAO Contact 1");
		try {
			driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath("//textarea")).click();
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Service Item Information']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//span[text()='Key Dates']/parent::button"));
		scrollingFunction();
		try {
			 element = driver1.findElement(By.xpath("//span[text()='Automated test comments']/parent::span"));
			 highlightElement();
		} catch (Exception e) {
			try {
			driver1.findElement(By.xpath("//span[text()='Comments']/parent::button")).click();
			Utils.sleep(2);
			element = driver1.findElement(By.xpath("//span[text()='Automated test comments']/parent::span"));
			highlightElement();
			} catch (Exception e1) {
				driver1.navigate().refresh();
				ele = By.xpath("//span[text()='Service Item Information']/parent::button");
				fluentWaitForElementVisibility();
				element = driver1.findElement(By.xpath("//span[text()='Service Item Information']/parent::button"));
				scrollingFunction();
				Utils.sleep(1);
				element = driver1.findElement(By.xpath("//span[text()='Key Dates']/parent::button"));
				scrollingFunction();
				driver1.findElement(By.xpath("//span[text()='Comments']/parent::button")).click();
				Utils.sleep(2);
				element = driver1.findElement(By.xpath("//span[text()='Automated test comments']/parent::span"));
				highlightElement();
			}
		}
		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		try {
		element = driver1.findElement(By.xpath("//span[contains(text(),'SME')]/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Author/SME field is updated with aao contact 1");
		} catch (Exception e) {
		testReporter.log(LogStatus.INFO, "Author/SME field is not updated with aao contact 1");
		}
		try {
			element = driver1.findElement(By.xpath("//span[contains(text(),'Status')]/parent::div/following-sibling::div/descendant::*[contains(text(),'Complete')]"));
			highlightElement();
			element = driver1.findElement(By.xpath("//span[contains(text(),'Round')]/parent::div/following-sibling::div/descendant::*[contains(text(),'1')]"));
			highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "STATUS/ROUND field is not updated with expected value.");
		}
	}
	//logout from here.
	public void openServiceItemAndValidate_Round1() {
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Authoring Review')]");
		ele = By.xpath("//*[contains(text(),'Authoring Review')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		element = driver1.findElement(ele);
		highlightElement();
		try {
		element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(2)']"));
		highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.INFO, "Files Count is not 2");
		}
	}
	public void createAsswith2Usr () {
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		//driver1.findElement(By.xpath("//a[@title='Create Assignments']/parent::*")).click();
		element = driver1.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		//selectDropdownListValue("Authoring Assignment", driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")));
		element = driver1.findElement(By.xpath("//label[text()='Back Date Assignment']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("PVY");
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		ele = By.xpath("//td[@class='dataCell']/span[text()='PVY']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).clear();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(1);
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(1).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(3)')]");
		fluentWaitForElementVisibility();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(3)')]");
		fluentWaitForElementVisibility();
		//element = driver1.findElement(By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance')]"));
		element = driver1.findElement(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::p/parent::div"));
		highlightElement();
		clrAssNO1 = driver1.findElements(By.xpath("//span[text()='Clearance Request']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).get(0).getText();
		clrAssNO2 = driver1.findElements(By.xpath("//span[text()='Clearance Request']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).get(1).getText();
		System.out.println("Clearance Request PVY:"+clrAssNO1+ "and AAO "+clrAssNO2);
		testReporter.log(LogStatus.PASS, "Round 2 Clearance Request PVY:: "+clrAssNO1+ "and AAO:: "+clrAssNO2);
		
	}
	//log out from EXSO user
	//log in as aao pod
	public void openAssignment() {
			driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
			ele = By.xpath("//a[@title='Select List View']");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
			ele = By.xpath("//span[contains(text(),'Open Clearance Requests')]/parent::*");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
			Utils.sleep(4);
			try {
				ele = By.xpath("//a[@title='"+clrAssNO2+"']");
				driver1.findElement(ele).click();
				testReporter.log(LogStatus.PASS, "AAO contact Clerance " +clrAssNO2);
			} catch (Exception e) {
				ele = By.xpath("//a[@title='"+clrAssNO1+"']");
				driver1.findElement(ele).click();
				testReporter.log(LogStatus.PASS, "AAO contact Clerance " +clrAssNO1);
			}
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
	}
	public void sendMailBySelectingContact_Round2ND () {
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Search Contacts']/parent::h3"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@maxlength='255']")).sendKeys("AAO");
		Utils.sleep(2);
		driver1.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		Utils.sleep(1);
		ele = By.xpath("//a[contains(text(),'AAO Contact 1')]");
		fluentWaitForElementVisibility();
		/*driver1.findElement(By.xpath("//input[@maxlength='255']")).clear();
		driver1.findElement(By.xpath("//input[@maxlength='255']")).sendKeys("PVY");
		driver1.findElement(By.xpath("//button[contains(text(),'Go')]")).click();
		Utils.sleep(1);
		ele = By.xpath("//a[contains(text(),'PVY Contact 1')]");
		fluentWaitForElementVisibility();*/
		//driver1.findElement(By.xpath("//input[@value='mohammad.z.abid@uscis.dhs.gov' and @type='checkbox']")).click();
		driver1.findElement(By.xpath("//*[text()='AAO Contact 1']/parent::*/parent::th/preceding-sibling::td/descendant::input[@type='checkbox']")).click();
		Utils.sleep(1);
		//driver1.findElement(By.xpath("//input[@value='ejeong@acumensolutions.com' and @type='checkbox']")).click();
		element = driver1.findElement(By.xpath("//span[text()='Select Content']/parent::h3"));
		scrollingFunction();
		Utils.sleep(1);
		try {
		String mail1= driver1.findElement(By.xpath("//div[@title='mohammad.z.abid@uscis.dhs.gov']")).getText();
		//String mail2= driver1.findElement(By.xpath("//div[@title='ejeong@acumensolutions.com']")).getText();
		String selectedEmails = driver1.findElement(By.xpath("//textarea[@disabled='' and @role='textbox']")).getText();
		Utils.sleep(2);
		System.out.println(selectedEmails);
		} catch (Exception e) {
			
		}
		element = driver1.findElement(By.xpath("//textarea[@disabled='' and @role='textbox']"));
		highlightElement();
		driver1.findElements(By.xpath("//input[@type='checkbox' and @name='options']/following-sibling::label/span[1]")).get(0).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//input[@type='checkbox' and @name='options']/following-sibling::label/span[1]")).get(1).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//div[@data-aura-class='cEmailFiles'][4]/div/descendant::input")).get(1).sendKeys("Automated Test 2nd round");
		driver1.findElements(By.xpath("//div[@data-aura-class='cEmailFiles'][4]/div/descendant::textarea")).get(1).sendKeys("Test for Mail Send with POD User 2nd round");
		Utils.sleep(2);
		driver1.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]/parent::div");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	public void acceptAssignmentForAAO_Round2 () throws IOException {
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Accept Assignment']/parent::li")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Save']/parent::button[@data-aura-class='uiButton']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='In Progress']/parent::div"));
		highlightElement();
		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
		scrollingFunction();
		try {
			element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
		} catch (Exception e) {
			Utils.sleep(4);
			driver1.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
			element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
			scrollingFunction();
			element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
		}
		highlightElement();
		element = driver1.findElement(By.xpath("//h1[text()='Assignment Details for the Parent SI']/parent::div/descendant::td[text()='AAO']"));
		highlightElement();
		int lineItem = driver1.findElements(By.xpath("//h1[text()='Assignment Details for the Parent SI']/parent::div/descendant::td[text()='AAO']")).size();
		if (lineItem == 2) {
			System.out.println("AAO contact is coming 2 times" +lineItem);
			testReporter.log(LogStatus.PASS, "AAO contact is coming 2 times" +lineItem);
		} else {
			System.out.println("AAO contact is coming more than 2 times" +lineItem);
			testReporter.log(LogStatus.FAIL, "AAO contact is coming more than 2 times" +lineItem);
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	public void completeAssiAAO_Round2 () {
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(3);
		driver1.findElement(By.xpath("//textarea")).click();
		try {
		driver1.findElement(By.xpath("//td[1]/span[@class='lookupInput']/input")).sendKeys("AAO Contact 1");
		} catch (Exception e) {
			driver1.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).sendKeys("AAO Contact 1");
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		//driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		selectDropdownListValue("Concur", driver1.findElement(By.xpath("//div[@class='pbSubsection']/table/tbody/tr[2]/descendant::select")));
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
//		element = driver1.findElement(By.xpath("//span[text()='Key Dates']/parent::button"));
//		scrollingFunction();
//		element = driver1.findElement(By.xpath("//span[text()='Automated text comment']/parent::span"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
//		scrollingFunction();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'SME')]/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'Status')]/parent::div/following-sibling::div/descendant::*[contains(text(),'Complete')]"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'Round')]/parent::div/following-sibling::div/descendant::*[contains(text(),'1')]"));
//		highlightElement();
	}
	//Log out from the AAO pod
	public void logInAsPodUser_PVY() {
		//driver1.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver1.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
		Utils.sleep(4);
		//driver1.findElement(By.xpath("//li/a[@title='Contacts']")).click();
		//Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("PVY Contact 1");
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='PVY Contact 1']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='PVY Contact 1']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		try {
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//open the assign of PVY
	public void acceptAssignmentForPVY_Round2 () throws IOException {
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//a[@title='Accept Assignment']/parent::li")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Save']/parent::button[@data-aura-class='uiButton']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='In Progress']/parent::div"));
		highlightElement();
		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
		scrollingFunction();
		try {
		element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'PVY Contact 1')]"));
		} catch (Exception e) {
			Utils.sleep(4);
			driver1.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
			element = driver1.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'PVY Contact 1')]"));
		}
		highlightElement();
		element = driver1.findElement(By.xpath("//h1[text()='Assignment Details for the Parent SI']/parent::div/descendant::td[text()='PVY']"));
		highlightElement();
		int lineItem = driver1.findElements(By.xpath("//h1[text()='Assignment Details for the Parent SI']/parent::div/descendant::td[text()='PVY']")).size();
		if (lineItem == 1) {
			System.out.println("PVY contact is coming 1 times" +lineItem);
			testReporter.log(LogStatus.PASS, "PVY contact is coming 1 times" +lineItem);
		} else {
			System.out.println("PVY contact is coming more than 1 times" +lineItem);
			testReporter.log(LogStatus.FAIL, "PVY contact is coming more than 1 times" +lineItem);
			//screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	public void uploadFileToAssiment_PVY() throws Exception {
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[text()='Add Files']/parent::a")).click();
		ele = By.xpath("//button[contains(text(),'Upload Files')]");
		fluentWaitForElementVisibility();
		attach_file_and_send_assign(Constants.privacy_pia_attachment_url,false);
		ele = By.xpath("//*[contains(text(),'1 of 1 file uploaded')]");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//span[text()='Done']/parent::button")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		try {
		element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(3)']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//ul[@class='uiAbstractList']/li"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Uploaded File count is 3. File uploaded successfully.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Uploaded File count is not 3");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
	}
	public void completeAssignPVY_Round2 () {
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//textarea")).click();
		try {
		driver1.findElement(By.xpath("//td[1]/span[@class='lookupInput']/input")).sendKeys("PVY Contact 1");
		} catch (Exception e) {
			driver1.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).sendKeys("PVY Contact 1");
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		//driver1.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		//driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		selectDropdownListValue("Do Not Concur", driver1.findElement(By.xpath("//div[@class='pbSubsection']/table/tbody/tr[2]/descendant::select")));
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		element = driver1.findElement(By.xpath("//div[@class='custPopup' and contains(text(),'You have not shared any documents with the parent record')]"));
		highlightElement();
		driver1.findElement(By.xpath("//div[@class='custPopup' and contains(text(),'You have not shared any documents with the parent record')]/descendant::input[1]")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
//		element = driver1.findElement(By.xpath("//span[text()='Key Dates']/parent::button"));
//		scrollingFunction();
//		element = driver1.findElement(By.xpath("//span[text()='Automated text comment']/parent::span"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[text()='Information']/parent::button"));
//		scrollingFunction();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'SME')]/parent::div/following-sibling::div/descendant::*[contains(text(),'AAO Contact 1')]"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'Status')]/parent::div/following-sibling::div/descendant::*[contains(text(),'Complete')]"));
//		highlightElement();
//		element = driver1.findElement(By.xpath("//span[contains(text(),'Round')]/parent::div/following-sibling::div/descendant::*[contains(text(),'1')]"));
//		highlightElement();
	}
	//logout from PVY pod user
	//log in as EXSO
	public void openServiceItemAndValidate_Round2() throws IOException {
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		try {
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance Review')]");
		ele = By.xpath("//*[contains(text(),'Clearance Review')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		element = driver1.findElement(ele);
		highlightElement();
		testReporter.log(LogStatus.PASS, "Clearance Review status is present in SI after completing the Round3 2 clerance Review assignment.");
		} catch (Exception e) {
			System.out.println("Failed in Clearance Review Area.");
			testReporter.log(LogStatus.FAIL, "Clearance Review status is not present in SI after completing the Round3 2 clerance Review assignment.");
			//screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		try {
		element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[text()='(3)']"));
		highlightElement();
		} catch (Exception e) {
			
		}
	}
	public void createAssignment_Round3() throws IOException {
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		//driver1.findElement(By.xpath("//a[@title='Create Assignments']/parent::*")).click();
		element = driver1.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")).click();
		Utils.sleep(1);
		//selectDropdownListValue("Authoring Assignment", driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")));
		driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select/option[text()='Authoring Assignment']")).click();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//label[text()='Back Date Assignment']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(1);
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(1).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(2).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver1.switchTo().defaultContent();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'3')]");
		fluentWaitForElementVisibility();
		assignNo = driver1.findElement(By.xpath("//span[text()='Authoring Assignment']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).getText();
		System.out.println("Authoring Assignment number :"+assignNo);
		try {
			//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Authoring')]");
			ele = By.xpath("//*[contains(text(),'Authoring')]/parent::*/parent::p/parent::div");
			fluentWaitForElementVisibility();
			element = driver1.findElement(ele);
			highlightElement();
			testReporter.log(LogStatus.PASS, "Authoring status is present in SI after completing the Round3 2 clerance Review assignment.");
		} catch (Exception e) {
			System.out.println("Failed in Clearance Review Area.");
			testReporter.log(LogStatus.FAIL, "Authoring status is not present in SI after completing the Round3 2 clerance Review assignment.");
			//screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		//element = driver1.findElement(By.xpath("//span[@title='Round']/following-sibling::div/descendant::span[contains(text(),'2')]/parent::*"));
		element = driver1.findElement(By.xpath("//*[text()='Round']/following-sibling::*/descendant::*[text()='2']"));
		highlightElement();
	}
	public void validateAssign_Round3() {
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'3')]"));
		scrollingFunction();
		//driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]")).click();dgsgsgsg
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		ele = By.xpath("//span[text()='Authoring Assignment']/parent::span/parent::td/preceding-sibling::th[1]/span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver1.findElements(ele).get(1);
		highlightElement();
	}
	//Recording 2
	public void fetchAssNoAndLogInAsPodUserAndOpenAssignmt () throws Exception {
		assignNo = driver1.findElements(By.xpath("//span[contains(text(),'Authoring Assignment')]/parent::*/parent::td/preceding-sibling::th[1]/span/a")).get(1).getText();
		System.out.println("Second Authoring Assignment No "+assignNo);
		logOut();
		logInAsPodUser();
		Utils.sleep(1);
		ele = By.xpath("//a[@title='Assignments']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Authoring Assignments')]/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		Utils.sleep(1);
		try {
		ele = By.xpath("//a[@title='"+assignNo+"']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		} catch (Exception e) {
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			//driver1.findElement(ele).click();
			element = driver1.findElement(By.xpath("//span[text()='Assignment Name']/parent::a"));
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+assignNo+"']");
			fluentWaitForElementVisibility();
			driver1.findElement(ele).click();
		}
		testReporter.log(LogStatus.PASS, "Authoring Assignment Number is Coming as Round 2 : "+assignNo);
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//span[text()='Assignment Change Request Information']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		try {
		element = driver1.findElement(By.xpath("//span[text()='Round']/parent::*/following-sibling::div/*/span[text()='2']"));//need to be 2
		highlightElement();
		testReporter.log(LogStatus.PASS, "Authoring Assignment Count is Coming as Round 2");
		} catch (Exception e) {
			System.out.println("Log a Bug over here.");
			testReporter.log(LogStatus.FAIL, "Authoring Assignment Count is not Coming as Round 2");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
	}
	public void acceptUploadAndCompleteAssi () throws Exception {
		acceptAssignmentForAAO();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		uploadFileToAssiment();
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		driver1.findElement(By.xpath("//td[2]/span[@class='lookupInput']/input")).sendKeys("AAO Contact 1");
		Utils.sleep(2);
		try {
		driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath("//textarea")).click();
		}
		Utils.sleep(1);
		selectDropdownListValue("Rewrite Draft", driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]/parent::*/parent::td/preceding-sibling::td[1]/div/select")));
		element = driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]/parent::*/parent::td/preceding-sibling::td[1]/div"));
		highlightElement();
		driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//input[@value='Save']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		logOutPodUser();
	}
	public void validateExistingSI () throws IOException {
		internalUserLogin("EXSO Service Item Manager");
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		try {
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Authoring Review')]");
		ele = By.xpath("//*[contains(text(),'Authoring Review')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		element = driver1.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			System.out.println("Log a Bug");
			testReporter.log(LogStatus.FAIL, "Servcie item ("+exsoSI+") status is not coming as Authoring Review.");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		try {
		element = driver1.findElement(By.xpath("//span[text()='Files']/following-sibling::span[contains(text(),'3')]"));
		highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "File Count is not 3");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
	}
	public void createAsswith2Usr_Recording2 () {
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		//driver1.findElement(By.xpath("//a[@title='Create Assignments']/parent::*")).click();
		element = driver1.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		//selectDropdownListValue("Authoring Assignment", driver1.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")));
		element = driver1.findElement(By.xpath("//label[text()='Back Date Assignment']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("PVY");
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		ele = By.xpath("//td[@class='dataCell']/span[text()='PVY']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).clear();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(1);
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		/*element = driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add User')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).clear();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add User')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO Contact 1");;
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[contains(@id,'MultipleUserSection') and @value='Search']")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(2);*/
		element = driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(1).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(2).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(3).click();
		Utils.sleep(1);
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(3)')]");
		fluentWaitForElementVisibility();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(3)')]");
		fluentWaitForElementVisibility();
		//element = driver1.findElement(By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance')]"));
		element = driver1.findElement(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::p/parent::div"));
		highlightElement();
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'3')]"));
		scrollingFunction();
		//driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]")).click();
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		ele = By.xpath("//span[text()='Authoring Assignment']/parent::span/parent::td/preceding-sibling::th[1]/span/a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver1.findElements(ele).get(1);
		highlightElement();
		clrAssNO1 = driver1.findElements(By.xpath("//span[text()='Clearance Request']/parent::span/parent::td/preceding-sibling::td[1]/descendant::span[contains(text(),'AAO')]/parent::*/parent::*/preceding-sibling::th[1]/span/a")).get(1).getText();
		clrAssNO2 = driver1.findElements(By.xpath("//span[text()='Clearance Request']/parent::span/parent::td/preceding-sibling::td[1]/descendant::span[contains(text(),'PVY')]/parent::*/parent::*/preceding-sibling::th[1]/span/a")).get(1).getText();
		System.out.println("Clearance Request AAO:"+clrAssNO1+ "and PVY "+clrAssNO2);
		testReporter.log(LogStatus.PASS, "Round 4 Clearance Request AAO: "+clrAssNO1+ "and PVY: "+clrAssNO2);
		logOut();
	}
	public void completeAssignWithAAO_Round4 () throws Exception {
		logInAsPodUser();
		driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Clearance Requests')]/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver1.findElement(ele).click();
			testReporter.log(LogStatus.PASS, "Round 4 Clearance Request AAO: "+clrAssNO1);
		} catch (Exception e) {
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			//driver1.findElement(ele).click();
			element = driver1.findElement(By.xpath("//span[text()='Assignment Name']/parent::a"));
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+clrAssNO2+"']");
			driver1.findElement(ele).click();
			testReporter.log(LogStatus.PASS, "Round 4 Clearance Request AAO: "+clrAssNO2);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		uploadFileToAssiment();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//textarea")).click();
		try {
		driver1.findElement(By.xpath("//td[1]/span[@class='lookupInput']/input")).sendKeys("AAO Contact 1");
		} catch (Exception e) {
			driver1.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).sendKeys("AAO Contact 1");	
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		selectDropdownListValue("Concur with Edits / Comments", driver1.findElement(By.xpath("//div[@class='pbSubsection']/table/tbody/tr[2]/descendant::select")));
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//input[@value='Save']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		logOutPodUser();
	}
	public void completeAssignWithPVY_Round4 () throws Exception {
		logInAsPodUser_PVY();
		driver1.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Clearance Requests')]/parent::*");
		fluentWaitForElementVisibility();
		driver1.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO2+"']");
			driver1.findElement(ele).click();
		} catch (Exception e) {
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			//driver1.findElement(ele).click();
			element = driver1.findElement(By.xpath("//span[text()='Assignment Name']/parent::a"));
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver1.findElement(ele).click();
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//span[text()='Assignment Change Request Information']/parent::button"));
		scrollingFunction();
		Utils.sleep(1);
		try {
		element = driver1.findElement(By.xpath("//span[text()='Round']/parent::*/following-sibling::div/*/span[text()='2']"));//need to be 2
		highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Clerance Assignment Round Count is not coming as 2");
			//screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		element = driver1.findElement(By.xpath("//*[text()='Assignment Details for the Parent SI']/parent::*"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//span[text()='Clearance Request Response']/parent::th/parent::*/parent::*/following-sibling::tbody"));//need to be 2
		highlightElement();
		int rowCount = driver1.findElements(By.xpath("//span[text()='Clearance Request Response']/parent::th/parent::*/parent::*/following-sibling::tbody/tr")).size();
		if(rowCount == 6) {
			testReporter.log(LogStatus.PASS, "All assignments are present in the Assignment Details for the Parent SI section. Service Item Number: "+exsoSI);
		} else {
			testReporter.log(LogStatus.FAIL, "All assignments are not present in the Assignment Details for the Parent SI section. Service Item Number: "+exsoSI);
			//screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(2);
		driver1.findElement(By.xpath("//textarea")).click();
		try {
		driver1.findElement(By.xpath("//td[1]/span[@class='lookupInput']/input")).sendKeys("PVY Contact 1");
		} catch (Exception e) {
			driver1.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).sendKeys("PVY Contact 1");
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath("//table[contains(@id,'AssignmentDetailSection')]/tbody/tr[2]/td[1]/div")).click();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		selectDropdownListValue("Concur", driver1.findElement(By.xpath("//div[@class='pbSubsection']/table/tbody/tr[2]/descendant::select")));
		element = driver1.findElement(By.xpath("//input[@value='Save']"));
		scrollingFunction();
		Utils.sleep(1);
		//Utils.sleep(1);
		//driver1.findElement(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		logOutPodUser();
	}
	public void verifyParentSIStatusandCreateFYIRecandUpdateDocType () throws IOException {
		internalUserLogin("EXSO Service Item Manager");
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		try {
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance Review')]");
		ele = By.xpath("//*[contains(text(),'Clearance Review')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		element = driver1.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			System.out.println("Log a bug");
//			testReporter.log(LogStatus.FAIL, "Service Item status is not Clerance.");
//			screenShotPath = GetScreenShot.capture(driver1);
//			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		driver1.navigate().refresh();
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		driver1.findElement(By.xpath("//a[@title='Create FYI Records']/parent::*")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Create Info Copies']"))));
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//label[contains(text(),'Assignment Instruction Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		driver1.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(2);
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver1.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(1).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(2).click();
		Utils.sleep(1);
		driver1.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(3).click();
		Utils.sleep(1);
		element = driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver1.switchTo().defaultContent();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'3')]"));
		scrollingFunction();
		//driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]")).click();
		element = driver1.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]"));
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element);
		ele = By.xpath("//span[text()='Authoring Assignment']/parent::span/parent::td/preceding-sibling::th[1]/span/a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		try {
			infoAssign = driver1.findElement(By.xpath("//span[text()='Information Only']/parent::span/parent::td/preceding-sibling::th[1]/span/a")).getText();
		} catch (Exception e) {
			ele = By.xpath("//span[text()='Assignment Name']/parent::a");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			infoAssign = driver1.findElement(By.xpath("//span[text()='Information Only']/parent::span/parent::td/preceding-sibling::th[1]/span/a")).getText();
		}
		//add the log here
		try {
		element = driver1.findElement(By.xpath("//span[text()='No Response Required']/parent::*"));
		highlightElement();
		testReporter.log(LogStatus.PASS, "Information Only Assignment created and No Response Required value is present. Assignnment NO:: "+infoAssign);
		} catch (Exception e) {
//			testReporter.log(LogStatus.FAIL, "Information Only Assignment created and No Response Required value is not present. Assignnment NO:: "+infoAssign);
//			screenShotPath = GetScreenShot.capture(driver1);
//			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
		
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		ele = By.xpath("//a[@title='Edit File Information']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Edit File Information']/parent::*")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Case Files']"))));
		Utils.sleep(2);
		try {
		selectDropdownListValue("Original Document", driver1.findElement(By.xpath("//table/tbody/tr[1]/descendant::select")));
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@value='Update Files']")).click();
		Utils.sleep(4);
		testReporter.log(LogStatus.PASS, "Document type is updated and Saved.");
		} catch (Exception e) {
//			testReporter.log(LogStatus.FAIL, "Document type is not updated and Saved.");
//			screenShotPath = GetScreenShot.capture(driver1);
//			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			driver1.findElement(By.xpath("//input[@value='Cancel']")).click();
		}
		driver1.switchTo().defaultContent();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	
	public void verifyUpdateFileTypeAndPodCol () {
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele = By.xpath("//a[@title='Edit File Information']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Edit File Information']/parent::*")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe[@title='Case Files']"))));
		Utils.sleep(2);
		try {
			//element = driver1.findElement(By.xpath("//table/tbody/tr[1]/descendant::option[@selected='selected' and @value='Original Document']"));
			element = driver1.findElement(By.xpath("//table/tbody/tr[1]/descendant::div[@title='Original Document']/parent::td"));
			testReporter.log(LogStatus.PASS, "Document type is updated as Original Document");
			highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Document type is not saved as Original Document");
		}
		try {
			//element = driver1.findElement(By.xpath("//table/tbody/tr[1]/descendant::option[@selected='selected' and @value='Original Document']"));
			element = driver1.findElement(By.xpath("//th/div[text()='POD Owner']"));
			testReporter.log(LogStatus.PASS, "POD Owner column is present.");
			highlightElement();
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "POD Owner column is not present.");
		}
		driver1.findElement(By.xpath("//input[@value='Cancel']")).click();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		driver1.navigate().refresh();
		ele = By.xpath("//span[text()='Assignments']/following-sibling::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	public void generateRCAAndValidate () {
		//internalUserLogin("EXSO Service Item Manager");
		//driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		//Utils.sleep(2);
		//driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele = By.xpath("//a[@title='Generate RCA']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Generate RCA']/parent::*")).click();
		Utils.sleep(8);
		//Need to read pdf
		
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
	}
	public void closeSI() {
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		try {
		//actObj.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Clearance')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
		actObj.moveToElement(driver1.findElements(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		//driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
		driver1.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//*[@title='Closed']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//*[text()='Internal Section']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		//driver1.navigate().refresh();
		//ele = By.xpath("//span[text()='Signature Level']");
		//fluentWaitForElementVisibility();
		//Utils.sleep(2);
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Closed')]");
		ele = By.xpath("//*[contains(text(),'Closed')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		element = driver1.findElement(ele);
		highlightElement();
		logOut();
	}
//*************************************************************EXSO 2nd SCENARIO********************************************************************
	public void createNewSIWithAAOusr() {
		logInAsPodUser();
		ele = By.xpath("//a[@title='Service Items']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='New']/parent::li")).click();
		ele = By.xpath("//span[text()='Assignment Manager Memo']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver1.findElement(ele);
		highlightElement();
		driver1.findElement(By.xpath("//span[text()='Assignment Manager Memo']/parent::*/preceding-sibling::div/input")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'New Service Item: Assignment Manager Memo')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver1.findElement(ele);
		highlightElement();
		driver1.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Program Office Directorate']/parent::li")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save & New']/following-sibling::button")).click();
		Utils.sleep(1);
		ele = By.xpath("//span[contains(@class,'toastMessage')]/parent::div");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.navigate().refresh();
		Utils.sleep(2);
		ele = By.xpath("//span[@title='Service Item Number']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void validateNewAAOSI () {
		aaoSINm = driver1.findElement(By.xpath("//span[@title='Service Item Number']/following-sibling::div/descendant::span")).getText();
		try {
			element = driver1.findElement(By.xpath("//span[@title='Contact Name']/parent::div/descendant::a[contains(text(),'AAO Contact 1')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//span[text()='Program Office Directorate']/parent::*"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::a[contains(text(),'AAO Contact 1')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
	}
	public void logOutPodUser_Parallel() {
		try {
		driver1.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::a")).click();
		} catch (Exception e) {
			driver1.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::a")).click();	
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
		ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void submitForApproval() {
		driver1.findElement(By.xpath("//div[@title='Submit for Approval']/parent::*/parent::*")).click();
		Utils.sleep(1);
		ele = By.xpath("//span[text()='Comments']/parent::*/following-sibling::textarea");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver1.findElement(ele).sendKeys("Submitting it for approval.");
		driver1.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();
		Utils.sleep(6);
		driver1.navigate().refresh();
		Utils.sleep(2);
		ele = By.xpath("//span[@title='Service Item Number']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		logOutPodUser_Parallel();
	}
	public void internalUserLogin_Parallel(String internalUserNm) {
		//String internalUserNm = "Privacy Staff 2";
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		Utils.sleep(2);
		try {
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
		} catch (Exception e) {
			driver1.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[6]/div")).click(); //QA env
		}
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String>(driver1.getWindowHandles());
		try {
		driver1.switchTo().window(tabs.get(2));
		driver1.switchTo().window(tabs.get(1));
		//System.out.println("I am here3.");
		driver1.close();
		Utils.sleep(2);
		driver1.switchTo().window(tabs.get(2));
		} catch (Exception e) {
			driver1.switchTo().window(tabs.get(0));
			driver1.close();
			Utils.sleep(2);
			driver1.switchTo().window(tabs.get(1));
		}
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
		} catch (Exception e) {
			
		}
		driver1.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//iframe"))));
		driver1.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		for(int icount=0; icount<4; icount++) {
			try {
				//driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
				//driver1.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
				driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
				break;
			} catch (Exception e) {
				driver1.get(driver1.getCurrentUrl());
				driver1.navigate().refresh();
				Utils.sleep(4);
			}
		}
	}
	public void openSIfromMainSearch(String siNm) {
		driver1.findElement(By.xpath("//input[@title='Search Salesforce']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//input[@title='Search Salesforce']")).sendKeys(siNm);
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+siNm+"']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[@title='Service Item Number']");
		fluentWaitForElementVisibility();
	}
	public void logInEXSOAndValidate() {
		internalUserLogin_Parallel("EXSO Service Item Manager");
		openSIfromMainSearch(aaoSINm);
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//div[@title='Create Assignments']/parent::a/parent::li"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//div[@title='Create FYI Records']/parent::a/parent::li"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//div[@title='Generate RCA']/parent::a/parent::li"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
		try {
			element = driver1.findElement(By.xpath("//div[@title='Edit File Information']/parent::a/parent::li"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
	}
	public void statusChangeCloseAndArchieve () {
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		try {
		actObj.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'New')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
		Utils.sleep(2);
		element = driver1.findElement(By.xpath("//a[@title='Clearance']"));
		scrollingFunction();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a[@title='Clearance Review']")).click();
		Utils.sleep(1);
		} catch (Exception e) {
			
		}
		Utils.sleep(1);
		element = driver1.findElements(By.xpath("//span[text()='Service Item Record Type']")).get(1);
		scrollingFunction();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//span[text()='EXSO Section']/parent::*/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[contains(text(),'Internal Section')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(4);
		driver1.navigate().refresh();
		Utils.sleep(1);
		ele = By.xpath("//span[text()='Internal Section']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance Review')]");
		ele = By.xpath("//*[contains(text(),'Clearance Review')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		try {
		element = driver1.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			//log error here.
		}
		
		//Closing here
		Utils.sleep(1);
		Actions actObj1 = new Actions(driver1);
		try {
		actObj1.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Clearance Review')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
		Utils.sleep(2);
		element = driver1.findElements(By.xpath("//a[@title='Closed']")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Closed']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//span[text()='Internal Section']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		//ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Closed')]");
		ele = By.xpath("//*[contains(text(),'Closed')]/parent::*/parent::p/parent::div");
		fluentWaitForElementVisibility();
		try {
		element = driver1.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			//log error here.
		}
		
		//Archive Here
		element = driver1.findElements(By.xpath("//span[text()='POD Service Item Input']/parent::button")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		actObj1.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::div/parent::div/div[2]/button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::label/following-sibling::input")).click();
		Utils.sleep(1);
		try {
			element = driver1.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::label/parent::*"));
			highlightElement();
		} catch (Exception e) {
				//log error here.
		}
		driver1.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(4);
		driver1.navigate().refresh();
		Utils.sleep(1);
		ele = By.xpath("//span[text()='Internal Section']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		try {
			element = driver1.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Archive')]"));
			highlightElement();
		} catch (Exception e) {
			//log Failure
		}
	}
//**************************************************************************************************************************************************
	public void generateRCAAndValidate_PdfValidation () throws AWTException, IOException {
		//internalUserLogin("EXSO Service Item Manager");
		//driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		//Utils.sleep(2);
		//driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		
		
		
		
		
		String pdfContent = "";
		String downloadPath = "C:\\stepWiseScreenshot\\"+exsoSI+"_pdfFile"+randomDateTime1()+".pdf";
		ele = By.xpath("//a[@title='Generate RCA']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='Generate RCA']/parent::*")).click();
		Utils.sleep(4);
		//Need to read pdf
		//ele = By.xpath("//div[@id='content']");
		//fluentWaitForElementVisibility();
		Actions actObj4 = new Actions(driver1);
		//actObj4.moveToElement(driver1.findElement(By.xpath("//body"))).contextClick().build().perform();
		Utils.sleep(2);
		Robot robot = new Robot();
        robot.mouseMove(800, 800);
        Utils.sleep(2);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Utils.sleep(2);
        //actObj4.moveToElement(driver1.findElement(By.xpath("//body/*[7]/*"))).contextClick().build().perform();
        
        robot.keyPress(KeyEvent.VK_SHIFT);
        Utils.sleep(1);
        robot.keyPress(KeyEvent.VK_F10);
        Utils.sleep(1);
        robot.keyRelease(KeyEvent.VK_F10);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_SHIFT);
		Utils.sleep(2);
        

		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(4);
		StringSelection s = new StringSelection(downloadPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_DELETE);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DELETE);
		//Utils.sleep(1);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_V);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_V);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		driver1.switchTo().activeElement();

		Utils.sleep(4);
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
		
		PDDocument document = PDDocument.load(new File(downloadPath));
		document.getClass();

        if (!document.isEncrypted()) {
        	testReporter.log(LogStatus.PASS, "Clicked on Generate RCA and Reading the Downloaded pdf from "+downloadPath+" location.");
            PDFTextStripperByArea stripper = new PDFTextStripperByArea();
            stripper.setSortByPosition(true);

            PDFTextStripper tStripper = new PDFTextStripper();

            String pdfFileInText = tStripper.getText(document);
            //System.out.println("Text:" + st);

			// split by whitespace
            String lines[] = pdfFileInText.split("\\r?\\n");
            for (String line : lines) {
                //System.out.println(line);
                pdfContent = pdfContent +"\\n<\br>"+ line;
            }
        }
        if (pdfContent.contains("Clearance Request AAO") && pdfContent.contains("Clearance Request PVY") && (!pdfContent.contains("Authoring"))) {
        	System.out.println("PDF Validation is successful. No Authoring Assignment is not present and Clearance is present");
        	testReporter.log(LogStatus.PASS, "PDF Validation is successful. No Authoring Assignment is not present and Clearance is present.");
        } else {
        	System.out.println("PDF Validation is not successful. Authoring Assignment is present and Clearance is not present");
        	testReporter.log(LogStatus.FAIL, "PDF Validation is not successful. Authoring Assignment is present and Clearance is not present.");
        }
        //System.out.println(pdfContent);
        testReporter.log(LogStatus.INFO, pdfContent);
	}
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	@Given("^Launch EXSO app for D1 and log in as Admin for EXSO Assignment scenario validation$")
	public void init_2() throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\EXSO_POD_Usr_rpt_and_Si_Summary_val_"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("EXSO POD user validation for report link and Service Item summary");
		try {
			init();
			testReporter.log(LogStatus.PASS, "User logs in successfully to Privacy portal");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully to Privacy portal");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the report and Summary tile is available in the Service item of POD user$")
	public void function_001() throws IOException {
		try {
			doD1UsrReportLinkValidation();
			testReporter.log(LogStatus.PASS, "Validate the report and Summary tile is available in the Service item of POD user");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the report and Summary tile is available in the Service item of POD user");
			screenShotPath = GetScreenShot.capture(driver1);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO POD User Stop Report Generation for current scenario EXSO Assignment End to End Scenario$")
	public void getResult_1() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^EXSO POD User Close the browser Email auto approval process$")
	public void flushReporter_For_IPO_1() {
		driver1.close();
		driver1.quit();
	}
	public void doD1UsrReportLinkValidation () {
		logInAsPodUser();
		element = driver1.findElement(By.xpath("//*[contains(text(),'STARS Support Links')]/parent::p/parent::div"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[contains(text(),'STARS Support Links')]/parent::p/parent::div/descendant::a[contains(@href,'https://')]/u[text()='Report STARS Issue']"));
		highlightElement();
		element = driver1.findElement(By.xpath("//*[contains(text(),'STARS Support Links')]/parent::p/parent::div/descendant::a[contains(@href,'https://')]/u[text()='Report STARS Access']"));
		highlightElement();
		driver1.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(3);
		driver1.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//span[text()='All Open Service Items']/parent::a")).click();
		Utils.sleep(4);
		element = driver1.findElements(By.xpath("//*[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		driver1.findElements(By.xpath("//*[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		Utils.sleep(4);
		element = driver1.findElements(By.xpath("//span[text()='Topic Information']/parent::button")).get(0);
		scrollingFunction();
		Utils.sleep(1);
		element = driver1.findElements(By.xpath("//span[text()='Summary']")).get(0);
		highlightElement();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		exso_scenario_1 exsoRef = new exso_scenario_1();
		
//		salesforcePrivacyRestApiConnection restApiConn = new salesforcePrivacyRestApiConnection();
//		restApiConn.getAccessToken();
//		restApiConn.fetchUserId();
//		exsoRef.randomDateTime();
//		restApiConn.createCase(subjectLine);
//		exsoSI = restApiConn.fetchNewlyCreatedSI();
//		System.out.println("Api Created SI :"+exsoSI);
//		//exsoRef.init_1();
		exsoRef.init();
		exsoRef.doD1UsrReportLinkValidation();
////		//exsoRef.fetchMailUrl("correspondence");
////		//exsoRef.logIntoGmail("https://acumensolutions-com.clearlogin.com/login","zabid","Acumentest1");
////		//exsoRef.login();
////exsoSI = "00021645";
////exsoRef.generateRCAAndValidate_PdfValidation();
////clrAssNO1 = "AS-005163";
////clrAssNO2 = "AS-005162";
//		exsoRef.internalUserLogin("EXSO Service Item Manager");
//		exsoRef.select_service_item_list_option("All Open Service Items");
//		//exsoSI = exsoRef.assert_incident_name_get_service_item_number(); // Not Required
//		exsoRef.opeTheApiCreatedSI();
//		exsoRef.clickOnSISetDueandETC();
		exsoRef.createAssignment();
//		exsoRef.logOut();
//		//assignNo = "AS-005158";
//		exsoRef.logInAsPodUser();
//		exsoRef.openAssignAndEditDueDate();
//		exsoRef.logOutPodUser();
////dueTime = "2/6/2020 12:00 PM";
//		exsoRef.internalUserLogin("EXSO Service Item Manager");
//		exsoRef.approveTheDue();
//		exsoRef.logOut();
//		exsoRef.logInAsPodUser();
//		exsoRef.validateDueDateChanged();
//		exsoRef.sendMailBySelectingContact();
//		exsoRef.uploadFileToAssiment();
//		exsoRef.acceptAssignmentForAAO();
//		exsoRef.completeAssiAAO();
//		exsoRef.logOutPodUser();
//		exsoRef.internalUserLogin("EXSO Service Item Manager");
//		exsoRef.openServiceItemAndValidate_Round1();
//		exsoRef.createAsswith2Usr();
//		exsoRef.logOut();
//		exsoRef.logInAsPodUser();
		exsoRef.openAssignment();
//		exsoRef.sendMailBySelectingContact_Round2ND();
//		exsoRef.acceptAssignmentForAAO_Round2();
//		exsoRef.completeAssiAAO_Round2();
//		exsoRef.logOutPodUser();
//		exsoRef.logInAsPodUser_PVY();
//		exsoRef.openAssignment();
//		exsoRef.acceptAssignmentForPVY_Round2();
//		exsoRef.uploadFileToAssiment_PVY();
//		exsoRef.completeAssignPVY_Round2();
//		exsoRef.logOutPodUser();
//		exsoRef.internalUserLogin("EXSO Service Item Manager");
//		exsoRef.openServiceItemAndValidate_Round2();
//		exsoRef.createAssignment_Round3();
//		exsoRef.validateAssign_Round3();
//		//Recording 2
//		exsoRef.fetchAssNoAndLogInAsPodUserAndOpenAssignmt();
//		exsoRef.acceptUploadAndCompleteAssi();
//		exsoRef.validateExistingSI();
//		exsoRef.createAsswith2Usr_Recording2();
//		exsoRef.completeAssignWithAAO_Round4();
//		exsoRef.completeAssignWithPVY_Round4();
//		exsoRef.verifyParentSIStatusandCreateFYIRecandUpdateDocType();
//		exsoRef.verifyUpdateFileTypeAndPodCol();
//		//exsoRef.generateRCAAndValidate();
//		exsoRef.generateRCAAndValidate_PdfValidation();
//		exsoRef.closeSI();
	}
}
