package com.salesforcetest.mapper.OIDP_Flow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OIDP_Approval_Rejection_Flow {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, emailLink, subjectLine, responseNumber,screenShotPath;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent;

	static ExtentTest testReporter;
	@Given("^OIDP Email Rejection/Approval process Registered User is logged in with \"(.*)\"$")
	public void init(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\OIDPRejectionFlowE2E"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("OIDP Rejection Approval process");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^OIDP Email Rejection/Approval process Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email rejection/approval process set QC Percentage to \"(.*)\"$")
	public void Set_QC_Percentage(String val) throws IOException {
		try {
			setPercentage(val);
			logOut();
			driver.close();
			driver.quit();
			testReporter.log(LogStatus.PASS, "Set percentage to "+val+" successful.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Set percentage to "+val+" successful.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
	public void Fetch_Email_From_Email_Services(String rowNo) throws IOException {
		try {
			launch();
			fetchEmailLink(rowNo);
			testReporter.log(LogStatus.PASS, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Fetching email from by navigating to email services EmailRelayRoutingHandler area");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Logging into email \"(.*)\" with user id \"(.*)\"$")
	public void Log_Into_Email(String url, String username) throws IOException {
		try {
			logIntoGmail(url, username, Constants.email_password);
			launch();
			testReporter.log(LogStatus.PASS, "Logging into email to send email to create a Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Logging into email to send email to create a Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Log Into as OIDP Analyst user and capture new service item \"(.*)\"$")
	public void Fetch_The_SI_Number(String user) throws AWTException, IOException {
		try {
			searchHDISOVSCitems(user);
			logInAsInternalUser(user);
			captureTheServiceItem();
			testReporter.log(LogStatus.PASS, "Capturing the newly created service item with "+user+" user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Capturing the newly created service item with "+user+" user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Creating new response and Validating the response status$")
	public void Create_New_Response() throws Exception {
		//try {
			validateEmailSI();
			createNewEmailResponse();
			logOut();
			testReporter.log(LogStatus.PASS, "Creating new response with attachment and Validating the response status");
		/*} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating new response with attachment and Validating the response status");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
	}
	@Then("^OIDP Email Rejection/Approval process reject the service response with Supervisor user$")
	public void Reject_New_Response() throws IOException {
		try {
			searchHDISOVSCitems("OIDP Supervisor");
			logInAsInternalUser("OIDP Supervisor");
			rejectResponse();
			logOut();
			testReporter.log(LogStatus.PASS, "Reject the service response with supervisor user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reject the service response with supervisor user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Rejection/Approval process Resubmit the service response with analyst user$")
	public void Resubmit_Reject_New_Response() throws AWTException, IOException {
		try {
			searchHDISOVSCitems("OIDP Analyst");
			logInAsInternalUser("OIDP Analyst");
			resubmitTheResponse();
			logOut();
			testReporter.log(LogStatus.PASS, "Resubmit the Rejected service response with analyst user after edit.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Resubmit the Rejected service response with analyst user after edit.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("OIDP Email Rejection/Approval process Approve the service response with Supervisor user$")
	public void Approve_New_Response() throws IOException {
		try {
			searchHDISOVSCitems("OIDP Supervisor");
			logInAsInternalUser("OIDP Supervisor");
			approveResponse();
			logOut();
			testReporter.log(LogStatus.PASS, "Approve the service response with supervisor user.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Approve the service response with supervisor user.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("OIDP Email Rejection/Approval process Reply from email and verify the status of service item$")
	public void Reply_To_Mail() throws AWTException {
		try {
			logIntoGmailForReplyValidation("https://acumensolutions.onelogin.com/login2/","udanturthy",Constants.email_password);
			searchHDISOVSCitems("OIDP Analyst");
			logInAsInternalUser("OIDP Analyst");
			verifySIStatus();
			logOut();
			testReporter.log(LogStatus.PASS, "Reply from email and verify the status of service item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Reply from email and verify the status of service item");
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for current scenario For OIDP rejection and approval of response scenario$")
	public void getResult() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For OIDP rejection and approval of response scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	//**************************************Main Functions****************************************
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	public void switchToClassicView() throws IOException {
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
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		}
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
		driver.findElement(By.xpath("//*[text()='Service Cloud User']")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]")).click();
		driver.switchTo().defaultContent();
		ele = By.xpath(".//*[@id='oneHeader']/descendant::*[contains(text(),'"+user+"')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		try {
		List<WebElement> list = driver.findElements(By.xpath(".//*[@id='oneHeader']/descendant::button[contains(@title,'Close')]"));
		int tab = driver.findElements(By.xpath(".//*[@id='oneHeader']/descendant::button[contains(@title,'Close')]")).size();
		for (int loop =0; loop<tab; loop++) {
			list.get(loop).click();
			Utils.sleep(2);
		}
		} catch(Exception e) {
			
		}
		
	}
	public void setPercentage(String percentageValue) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver, 14);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Quality Control Percentage')]"));
		scrollingFunction();
		element.click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='Quality Control Percentage']"))));
		ele = By.xpath("//input[@title='QC Percentage for OIDP Analyst']");
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
		ele = By.xpath(".//*[contains(text(),'Successfully updated QC Percentage for user')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[contains(text(),'Successfully updated QC Percentage for user')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(1);
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
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
	public void fetchEmailLink (String rowNo) {
		driver.findElement(By.id("setupLink")).click();
		ele = By.xpath(".//input[@id='setupSearch']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).sendKeys("email services");
		Utils.sleep(2);
		driver.findElement(By.xpath(".//a[text()='Email Services']")).click();
		element = driver.findElement(By.xpath("//a[contains(text(),'Email Service Name')]"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[2]/a[text()='EmailRelayRoutingHandler']"));
		highlightElement();
		element.click();
		ele = By.xpath("//*[text()='Email Service: EmailRelayRoutingHandler']");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath(".//*[text()='Email Addresses']"));
		scrollingFunction();
		element = driver.findElement(By.xpath("//table[@class='list']/tbody/descendant::span[text()='"+rowNo+"']/parent::td/following-sibling::td[1]/a"));
		highlightElement();
		emailLink = element.getText();
		System.out.println("Fetch email link :"+emailLink);
	}
	public void logIntoGmail (String url, String username, String passowrd) throws Exception {
		Robot robot = new Robot();
		driver.get(url);
		Utils.sleep(2);
		try {
		driver.findElement(By.id("sign_in_username")).sendKeys(username);
		//driver.findElement(By.id("username")).sendKeys(username);
		//Utils.sleep(2);
		driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		//driver.findElement(By.id("password")).sendKeys(passowrd);
		//driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		/*try {
			WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			//ele = By.xpath("//button[text()='skip']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
			//driver.findElement(By.xpath("//button[text()='skip']")).click();
		} catch (Exception e) {
			
		}*/
		} catch (Exception e) {
			
		}
		WebDriverWait wait = new WebDriverWait (driver, 10);
		ele = By.id("username");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		driver.findElement(By.id("username")).sendKeys("udanturthy"+"@acumensolutions.com");
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		ele = By.id("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		Utils.sleep(1);
		driver.findElement(By.id("password")).sendKeys(passowrd);
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//button[text()='skip']")).click();
		Utils.sleep(6);
		driver.findElement(By.xpath("//*[text()='Gmail']")).click();
		Utils.sleep(5);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		System.out.println(tabs2.size());
		Utils.sleep(4);
		driver.switchTo().window(tabs2.get(1));
		try {
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			//ele = By.xpath("//button[text()='skip']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
			//driver.findElement(By.xpath("//button[text()='skip']")).click();
		} catch (Exception e) {
			
		}
		 driver.findElement((By.xpath("//div[2]/div/div[1]//span/span"))).click();
		 Utils.sleep(3);
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(ele).click();
		ele = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(ele).click();
		driver.findElement(By.xpath("//span[text()='Cc' and contains(@data-tooltip,'Add Cc')]")).click();
		driver.findElement(ele).sendKeys(emailLink);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//textarea[@name='cc']")).sendKeys("zabid@acumensolutions.com");
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine);
		Utils.sleep(1);
		attach_file_and_send(Constants.privacy_pia_attachment_url, false);
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Message sent.']");
		fluentWaitForElementVisibility();
		driver.close();
		driver.quit();
	}
	private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
		try {
		driver.findElement(By.cssSelector("[command='Files']")).click();
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
		driver.switchTo().activeElement();

		Utils.sleep(4);
	}
	
	public void captureTheServiceItem() {
		Utils.sleep(3);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Service Items')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'OIDP Lockbox Support')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(4);
		for (int iCount=0; iCount<88; iCount++) {
			try {
				Utils.sleep(4);
				element = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/descendant::*[text()='"+subjectLine+"']"));
				highlightElement();
				newSINo = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/descendant::*[text()='"+subjectLine+"']/parent::div/parent::div/parent::*/parent::*/parent::td/preceding-sibling::th/span/a")).getText();
				System.out.println("Newly Generated SI :"+newSINo);
				break;
			} catch (Exception e) {
				driver.findElement(By.xpath(".//*[@id='split-left']/descendant::button[@title='Refresh']")).click();
			}
		}
		driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/descendant::a[@title='"+newSINo+"']/parent::*/parent::*/preceding-sibling::td[1]/span/span/label")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='split-left']/descendant::a[@title='Accept']")).click();
		ele = By.xpath("//*[contains(text(),'You have accepted Service Items from the queue and are now their owner.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
	}
	public void validateEmailSI() {
		driver.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'All Open OIDP Service Items')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[text()='Date/Time Opened']/parent::a")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		}
		Utils.sleep(3);
//		ele = By.xpath("//a[@class='tabHeader']/span[text()='Details']");
//		fluentWaitForElementVisibility();
//		driver.findElements(By.xpath("//a[@class='tabHeader']/span[text()='Details']")).get(0).click();
		ele = By.xpath("//a[text()='Details']");
		fluentWaitForElementVisibility();
		driver.findElements(By.xpath("//a[text()='Details']")).get(0).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[text()='Contact Name']/parent::*/following-sibling::div[1]"));
		highlightElement();
		driver.findElement(By.xpath("//span[text()='Contact Name']/parent::*/following-sibling::div[1]/button")).click();
		driver.findElement(By.xpath("//span[text()='Contact Name']/parent::*/following-sibling::div[1]/descendant::input")).sendKeys("Usha Danturthy");
//		driver.findElement(By.xpath("//label[text()='Contact Name']/parent::*/div[1]/descendant::input")).sendKeys("Za");
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[@class='listContent']/ul[contains(@class,'lookup__list')]/li[1]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(8);
		driver.navigate().refresh();
		ele = By.xpath("//a[@title='Edit']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		//driver.findElement(By.xpath("//div[@class='listViewContent']/descendant::a[text()='"+subjectLine+"']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(By.xpath("//div[@class='listViewContent']/descendant::a[text()='"+subjectLine+"']")));
		ele = By.xpath("//a[@class='tabHeader']/span[text()='Message']");
		fluentWaitForElementVisibility();
		driver.findElement(By.xpath("//a[@title='Message']/parent::*/following::li[1]/a[@title='Details']")).click();
		ele = By.xpath("//span[text()='Address Information']");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		scrollingFunction();
		element = driver.findElement(By.xpath("//span[text()='CC Address']/parent::div/following-sibling::div[1]"));
		highlightElement();
		driver.findElement(By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']/li[2]/a[@title='"+newSINo+"']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[text()='Emails']/parent::a"));
		scrollingFunction();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
		element = driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.sleep(4);
	}
	public void createNewEmailResponse() throws Exception {
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//label[text()='Response Subject:']"));
		scrollingFunction();
		Utils.sleep(2);
		try {
			driver.findElements(By.xpath("//input[@maxlength='255']")).get(1).sendKeys(" Edited_SubjectLine");
			Utils.sleep(2);
			element = driver.findElements(By.xpath("//input[@maxlength='255']")).get(1);
		} catch (Exception e) {
			driver.findElement(By.xpath("//input[@maxlength='255']")).sendKeys(" Edited_SubjectLine");
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//input[@maxlength='255']"));
		}
		highlightElement();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'OIDP Good Day')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Adoption Contacts (I-800) Hague')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'OIDP Adoption Contacts (I-800) Hague')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Adoption Contacts (I-800) Hague')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Congressional')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//*[contains(text(),'Add Email History')]"));
		scrollingFunction();
		highlightElement();
		element = driver.findElement(By.xpath("//h3[text()='Email History']"));
		scrollingFunction();
		highlightElement();
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
		element = driver.findElements(By.xpath("//span[contains(text(),'isSelected')]/parent::label")).get(0);
		element.click();
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element.click();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//*[contains(text(),'Upload Files')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		attach_file_and_send(Constants.privacy_test_attachment_url, false);
		element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();*/
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a[text()='View Response']")).click();
		Utils.sleep(4);
		driver.switchTo().defaultContent();
		driver.switchTo().defaultContent();
		ele = By.xpath(".//a[contains(text(),'Related')]/parent::*");
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		fluentWaitForElementVisibility();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
				driver.findElement(ele));
		try {
		element = driver.findElement(By.xpath("//div[@class='filerow']"));
		highlightElement();
		Utils.sleep(2);
		} catch (Exception e) {
			
		}
		driver.close();
		Utils.sleep(1);
		driver.switchTo().window(tabs.get(0));
		Utils.sleep(2);
		driver.findElements(By.xpath("//span[@title='Case']/following-sibling::span[text()='"+newSINo+"']")).get(1).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//p[text()='Status']/parent::*"));
		scrollingFunction();
		highlightElement();
		try {
			driver.findElement(By.xpath("//button[@title='Close "+newSINo+"']")).click();
		} catch (Exception e) {
			
		}
		Utils.sleep(2);
	}
	public void logOut () {
		try {
			//driver.navigate().refresh();
			//Utils.sleep(4);
			driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
		} catch (Exception e) {
			driver.navigate().refresh();
			ele = By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]");
			fluentWaitForElementVisibility();
			driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
		}
	}
	public void rejectResponse () {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Responses')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		driver.findElement(By.xpath("//span[text()='Responses']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'OIDP Response Approvers')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		try {
		element = driver.findElements(By.xpath("//a[@title='"+newSINo+"']/parent::span/parent::td/following-sibling::td[3]/span/div")).get(0);
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[text()='Response Submitted for Approval Date']/parent::*/parent::*")).click();
			Utils.sleep(2);
			element = driver.findElements(By.xpath("//a[@title='"+newSINo+"']/parent::span/parent::td/following-sibling::td[3]/span/div")).get(0);
		}
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']/parent::*/parent::*"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@name='Decision__c']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@data-value='Reject']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@name='Decision_Reason__c']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Other']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto Reject from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(2);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//button[@title='Close "+newSINo+"']")).click();
		} catch (Exception e) {
			
		}
	}
	public void resubmitTheResponse() {
		try {
			driver.findElement(By.xpath("//button[@title='Close "+newSINo+"']")).click();
		} catch (Exception e) {
			
		}
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Service Items')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		driver.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'All Open OIDP Service Items')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[text()='Date/Time Opened']/parent::a")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		}
		Utils.sleep(4);
		ele = By.xpath("//a[text()='Details']/parent::*");
		fluentWaitForElementVisibility();
		element = driver.findElement(By.xpath("//span[text()='Emails']/parent::a"));
		scrollingFunction();
		Utils.sleep(2);
		try {
			//driver.findElement(By.xpath("//td/span[text()='Edits Required']/parent::*/following-sibling::td[2]/div/a")).click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//td/span[text()='Edits Required']/parent::*/following-sibling::td[2]/div/a")));
		} catch (Exception e) {
			//driver.findElement(By.xpath("//td/span[text()='Edits Required']/parent::*/following-sibling::td[2]/div")).click();
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", 
					driver.findElement(By.xpath("//td/span[text()='Edits Required']/parent::*/following-sibling::td[2]/div")));
		}
		Utils.sleep(4);
		driver.findElement(By.xpath("//li[@class='uiMenuItem']/a")).click();
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElements(By.xpath("//iframe[@title='accessibility title']")).get(1)));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath("//span/p[1]"))).click().build().perform();
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
	public void approveResponse () {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Responses')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Responses']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'OIDP Response Approvers')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		driver.findElements(By.xpath("//a[@title='"+newSINo+"']/parent::span/parent::td/following-sibling::td[3]/span/div")).get(0).click();
		Utils.sleep(4);
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@name='Decision__c']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//*[@data-value='Approve']")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto approved from System.");
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(2);
		ele = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element = driver.findElement(ele);
		highlightElement();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void logIntoGmailForReplyValidation (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
	    robot.delay(1000);
	    JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('about:blank','_blank');");
	    robot.delay(2000);
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
		driver.get(url);
		driver.findElement(By.id("sign_in_username")).sendKeys(username);
		driver.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		try {
			WebDriverWait wait = new WebDriverWait (driver, 5);
			ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver.navigate().refresh();
		ele = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//div[text()='Primary']")).click();
			Utils.sleep(8);
			List<WebElement> email = driver.findElements(By.cssSelector("div.xT>div.y6>span"));
	
			for(WebElement emailsub : email) {
				System.out.println(emailsub.getText()+" email subjects....");
			    if(emailsub.getText().contains(subjectLine) == true){
			           emailsub.click();
			           break;
			        }
			}
			ele = By.cssSelector("div[data-tooltip='More']");
			fluentWaitForElementVisibility();
			driver.findElement(By.cssSelector("div[data-tooltip='Reply']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//div[text()='Updates']")).click();
			Utils.sleep(2);
			List<WebElement> email = driver.findElements(By.cssSelector("div.xT>div.y6>span"));

			for(WebElement emailsub : email) {
				System.out.println(emailsub.getText()+" email subjects....");
			    if(emailsub.getText().contains(subjectLine) == true) {
			           emailsub.click();
			           break;
			        }
			}
			ele = By.cssSelector("div[data-tooltip='More']");
			fluentWaitForElementVisibility();
			driver.findElement(By.cssSelector("div[data-tooltip='Reply']")).click();
		}
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[contains(@class,'aoD hl')]")).click();
		Utils.sleep(2);
		driver.findElements(By.xpath("//input[@tabindex='-1']/following-sibling::textarea")).get(0).sendKeys(emailLink);
		//driver.findElement(By.xpath("//div[contains(@class,'aoD hl')]")).sendKeys(emailLink);
		Utils.sleep(1);
		driver.findElement(By.className("Am")).sendKeys("Test Reply");
		Utils.sleep(1);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(4);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		}
	public void verifySIStatus() {
		try {
			driver.findElement(By.xpath("//button[@title='Close "+newSINo+"']")).click();
		} catch (Exception e) {
			
		}
		Utils.sleep(4);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Service Items')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		driver.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'All Open OIDP Service Items')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(4);
		try {
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//span[text()='Date/Time Opened']/parent::a")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		}
		ele = By.xpath("//a[text()='Details']/parent::*");
		fluentWaitForElementVisibility();
		try {
			element = driver.findElement(By.xpath("//span[text()='Approved']"));
			highlightElement();
		} catch (Exception e) {
			element = driver.findElement(By.xpath("//*[text()='New Reply']/parent::*/parent::p/parent::*"));
			highlightElement();
		}
		element = driver.findElement(By.xpath("//span[text()='Emails']/parent::a"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[text()='Sent']"));
		highlightElement();
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
	      subjectLine = "Test Automation OIDP Regression Flow - " + randomDateTime;
	      return randomDateTime;
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
		 
	    .withTimeout(40, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver);
		try
		{
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e)
		{
		}
		}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
//*************************************************************ISO12559##ISO12560************************************************************
	@Given("^OIDP ISO12559_ISO12560 scenario Registered User is logged in with \"(.*)\"$")
	public void init_ISO12559_ISO12560(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\ISO12559_ISO12560_OIDP_Customer_ENQ_and_Attachment_Layout"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("OIDP New Response don't have customer enquery and attacment layout is under response recipient");
		try {
			launch();
			testReporter.log(LogStatus.PASS, "User logs in successfully");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "User logs in successfully");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@When("^OIDP ISO12559_ISO12560 scenario Search for required Internal User \"(.*)\"$")
	public void search_For_Internal_User_ISO12559_ISO12560(String user) throws IOException {
		try {
			searchHDISOVSCitems(user);
			testReporter.log(LogStatus.PASS, "Search with required User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Search with required User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP ISO12559_ISO12560 scenario Logging in as Internal user and verifying \"(.*)\"$")
	public void logging_In_As_Internal_User_And_Verify_Profile_ISO12559_ISO12560(String user) throws IOException {
		try {
			logInAsInternalUser(user);
			testReporter.log(LogStatus.PASS, "Verify log in as Internal User");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Verify log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP ISO12559_ISO12560 scenario Opening an Existing Service Item with OIDP analyst USer$")
	public void Open_An_Existing_SI_ISO12559_ISO12560() throws IOException {
		try {
			openAnExistingSI();
			testReporter.log(LogStatus.PASS, "Opened successfully an OIDP SI :"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Not able to open an Existing OIDP SI.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP ISO12559_ISO12560 scenario Validation of new response creation page$")
	public void Validate_New_Response_Page_SI_ISO12559_ISO12560() throws IOException {
		//try {
			ValidateNewResponseCreationPage();
			testReporter.log(LogStatus.PASS, "Attachment section is coming after the Response Recipeint");
		/*} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Attachment section is not coming after the Response Recipeint");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}*/
	}
	@Then("^Stop Report Generation for current scenario For OIDP ISO12559_ISO12560 response scenario$")
	public void getResult_ISO12559_ISO12560() {
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For OIDP ISO12559_ISO12560 response scenario$")
	public void flushReporter_ISO12559_ISO12560() {
		driver.close();
		driver.quit();
	}
	public void openAnExistingSI() {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Service Items')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Service Items']/following-sibling::a[@title='Select List View']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//div[@class='listContent']/descendant::span[contains(text(),'Recently Viewed')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
		newSINo = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/descendant::a")).getText();
		driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
	}
	public void ValidateNewResponseCreationPage() {
		ele = By.xpath("//a[@title='Edit']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[text()='Emails']/parent::a"));
		scrollingFunction();
		Utils.sleep(2);
		//driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
		element = driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Utils.sleep(2);
		try {
			driver.findElement(By.xpath("//span[text()='Customer Inquiry']/parent::*")).getText();
			testReporter.log(LogStatus.FAIL, "OIDP new response page has Customer Enquiery section present");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
		} catch(Exception e) {
			testReporter.log(LogStatus.PASS, "OIDP new response page has Customer Enquiery section is not present");
		}
		element = driver.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Greetings')]"));
		element.click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'OIDP Good Day')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Adoption Contacts (I-800) Hague')]"));
		Actions actObj = new Actions(driver);
		actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'OIDP Adoption Contacts (I-800) Hague')]"))).doubleClick().build().perform();
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Adoption Contacts (I-800) Hague')]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]"));
		actObj.moveToElement(element).doubleClick().build().perform();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Congressional')]"));
		Utils.sleep(1);
		element.click();
		driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(2);
		element.click();
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//label[text()='Response Subject:']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//label[contains(text(),'Attachments:')]"));
		highlightElement();
		driver.switchTo().defaultContent();
	}
//*******************************************************************************************************************************************
	public static void main(String[] args) throws Exception {
		subjectLine = "Test Automation OIDP Regression Flow - 202002122432";
		newSINo = "09419441";
		emailLink = "starsoidpqa@17ryu2x1ttsh5hq862yzmo1kf4yw8unukh7wye8z2w2x30zu6n.r-1slleai.cs32.apex.sandbox.salesforce.com";
		OIDP_Approval_Rejection_Flow refVar = new OIDP_Approval_Rejection_Flow();
		refVar.launch();
		//refVar.fetchEmailLink("starsoidpqa");
		//refVar.logIntoGmail("https://acumensolutions-com.clearlogin.com/login","zabid",Constants.email_password);
		//refVar.launch();
		//refVar.searchHDISOVSCitems("OIDP Analyst");
		//refVar.logInAsInternalUser("OIDP Analyst");
		//refVar.captureTheServiceItem();
		//refVar.validateEmailSI();
		refVar.createNewEmailResponse();
		//refVar.logOut();
		//refVar.searchHDISOVSCitems("OIDP Supervisor");
		//refVar.logInAsInternalUser("OIDP Supervisor");
		//refVar.rejectResponse();
//		refVar.searchHDISOVSCitems("OIDP Analyst");
//		refVar.logInAsInternalUser("OIDP Analyst");
//		refVar.resubmitTheResponse();
//		refVar.logOut();
//		refVar.searchHDISOVSCitems("OIDP Supervisor");
//		refVar.logInAsInternalUser("OIDP Supervisor");
//		refVar.approveResponse();
//		refVar.logOut();
//		refVar.logIntoGmailForReplyValidation("https://acumensolutions-com.clearlogin.com/login","zabid",Constants.email_password);
		refVar.searchHDISOVSCitems("OIDP Analyst");
		refVar.logInAsInternalUser("OIDP Analyst");
		refVar.verifySIStatus();
		//refVar.setPercentage("100");
	}
}
