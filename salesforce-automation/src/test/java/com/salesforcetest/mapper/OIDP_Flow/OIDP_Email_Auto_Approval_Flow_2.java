package com.salesforcetest.mapper.OIDP_Flow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
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

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OIDP_Email_Auto_Approval_Flow_2 {
	public static WebDriver driver;
	private WebElement element;
	public static String newSINo, emailLink, subjectLine, responseNumber, relatedSINo, linkNumber, cloneSINo, screenShotPath;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	
	static ExtentReports extent;

	static ExtentTest testReporter;
	@Given("^OIDP Email Auto Approval Flow 2 Registered User is logged in with \"(.*)\"$")
	public void init(String user) throws IOException {
		extent = new ExtentReports(workingDir+"\\test-report\\OIDPAutoApprovalFlow2"+randomDateTime1()+".html", true);
		testReporter = extent.startTest("OIDP Auto Approval Flow 2");
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
	@When("^OIDP Email Auto Approval Flow 2 Search for required Internal User \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 Logging in as Internal user and verifying \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 process set QC Percentage to \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 Fetching email EmailRelayRoutingHandler from by navigating to email services area of row \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 Logging into email \"(.*)\" with user id \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 Log Into as OIDP Analyst user and capture new service item \"(.*)\"$")
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
	@Then("^OIDP Email Auto Approval Flow 2 Opening and Validating newly created Service Item$")
	public void Validate_New_Service_Item() throws IOException {
		try {
			validateTheNewSI();
			testReporter.log(LogStatus.PASS, "Opening the newly created service item and validate it. Parent SI :"+newSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Opening the newly created service item and validate it.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@And("^OIDP Email Auto Approval Flow 2 Edit the new service item with expected test data$")
	public void Edit_Service_Item(DataTable dt) throws IOException {
		try {
			List<Map<String, String>> list = dt.asMaps(String.class, String.class);
			String senderType = list.get(0).get("Sender Type");
			String formType = list.get(0).get("Form Type");
			String filingType = list.get(0).get("Filling Type");
			String category = list.get(0).get("Category");
			String kind = list.get(0).get("Kind");
			String comments = list.get(0).get("Comments");
			editTheNewServiceItem(senderType, formType, filingType, category, kind, comments);
			testReporter.log(LogStatus.PASS, "Editing the service item and saving it");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Editing the service item and saving it");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Creating a new response and Saving it as draft$")
	public void Save_Response_as_Draft() throws IOException {
		try {
			createNewEmailResponse();
			testReporter.log(LogStatus.PASS, "Saving the response as Draft");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Saving the response as Draft");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Create a HOLD TYPE as \"(.*)\" and comments as \"(.*)\"$")
	public void Create_Hold(String hType, String Comments) throws IOException {
		try {
			createHold(hType, Comments);
			testReporter.log(LogStatus.PASS, "Creating a HOLD");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating a HOLD");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Validate the error while marking COMPLETED status of Active HOLD associated to Service Item$")
	public void Validate_Err_For_Hold_Stat_Completed() throws IOException {
		try {
			validateError();
			testReporter.log(LogStatus.PASS, "Validate the error while marking COMPLETED status of Active HOLD associated to Service Item");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Validate the error while marking COMPLETED status of Active HOLD associated to Service Item");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Removing the HOLD and Marking the Service iTem as COMPLETED$")
	public void Removing_Hold_And_Marking_Completed() throws IOException {
		try {
			removeHoldAndMarkCompleted();
			testReporter.log(LogStatus.PASS, "Removing the HOLD and Marking the Service iTem as COMPLETED");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Removing the HOLD and Marking the Service iTem as COMPLETED");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Creating and Verification a new Related service item with ORIGIN as \"(.*)\"$")
	public void Create_Related_SI(String siOrigin) throws IOException {
		try {
			relatedServiceItemCreation(siOrigin);
			verifyChildServiceItem();
			testReporter.log(LogStatus.PASS, "Creating and Verification a new Related service item. Related SI:"+relatedSINo);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating and Verification a new Related service item. Related SI:"+relatedSINo);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Creating a new Service LINK with contact name as \"(.*)\" and role as \"(.*)\"$")
	public void Creating_a_New_Service_Link(String contactNm, String role) throws IOException {
		try {
			serviceLinkCreation(contactNm, role);
			testReporter.log(LogStatus.PASS, "Creating a new Service LINK. Link Number :"+linkNumber);
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Creating a new Service LINK. Link Number :"+linkNumber);
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 Create a CLONE SI and MERGE it to Parent Service ITEM$")
	public void CloneAndMergeVerification() throws IOException {
		try {
			cloneMerge();
			testReporter.log(LogStatus.PASS, "Create a CLONE SI("+cloneSINo+") and MERGE it to Parent Service ("+newSINo+") ITEM.");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "Create a CLONE SI("+cloneSINo+") and MERGE it to Parent Service ("+newSINo+") ITEM.");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^OIDP Email Auto Approval Flow 2 SUBMITTING the daft version response and check response status is changed to SENT$")
	public void Submit_Draft_Response() throws IOException {
		try {
			submitResponseFromClone();
			testReporter.log(LogStatus.PASS, "SUBMITTING the daft version response and check response status is changed to SENT");
		} catch (Exception e) {
			testReporter.log(LogStatus.FAIL, "SUBMITTING the daft version response and check response status is changed to SENT");
			screenShotPath = GetScreenShot.capture(driver);
			testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	
	
	@Then("^Stop Report Generation for current scenario For OIDP Email Auto Approval Flow 2 scenario$")
	public void getResult() {
		//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
		extent.endTest(testReporter);
		extent.flush();
		extent.close();
	}
	@Then("^Close the browser For OIDP Email Auto Approval Flow 2 scenario$")
	public void flushReporter() {
		driver.close();
		driver.quit();
	}
	//**************************************Main Functions****************************************
	
		public void launch() {
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize(); // maximizes
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			new SalesforceLogin(driver).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
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
				Utils.sleep(1);
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
			attach_file_and_send(Constants.sorn_attachment_url, false);
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

			Utils.sleep(1);
		}
		
		public void captureTheServiceItem() {
			Utils.sleep(4);
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
					driver.findElement(By.xpath("//span[text()='Date/Time Opened']/parent::a")).click();
					Utils.sleep(2);
					driver.findElement(By.xpath(".//*[@id='split-left']/descendant::button[@title='Refresh']")).click();
				}
			}
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
		}
		public void validateTheNewSI() {
			ele = By.xpath("//div[@class='uiTabBar']/ul/li[2]/a[@title='Related']");
			fluentWaitForElementVisibility();
			element = driver.findElement(By.xpath(".//a[contains(text(),'"+subjectLine+"')]/parent::div/parent::th"));
			highlightElement();
			element = driver.findElement(By.xpath("//span[text()='Emails']/parent::a"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//div[@class='filerow']"));
			highlightElement();
			Utils.sleep(1);
			driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void editTheNewServiceItem(String senderType, String formType, String filingType, String category, String kind, String comments) {
			driver.findElement(By.xpath("//a[@title='Edit']/parent::*")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[text()='Inquiry Information']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver.findElement(ele);
			scrollingFunction();
			driver.findElement(By.xpath("//span[text()='Sender Type']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+senderType+"']")).click();//Applicant
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Form Number']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+formType+"']")).click();//G1041
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Filing Type']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+filingType+"']")).click();//Adoptions
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Resolution Details']/parent::*"));
			scrollingFunction();
			driver.findElement(By.xpath("//span[text()='Category']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+category+"']")).click();//LFA - Lockbox Filing Assistance
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Kind']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+kind+"']")).click();//Card Error – Benefit/Validity Date/Etc
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Comments']/parent::*/following-sibling::textarea")).sendKeys(comments);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			highlightElement();
			Utils.sleep(1);
			driver.navigate().refresh();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void createNewEmailResponse() throws Exception {
			element = driver.findElement(By.xpath("//span[text()='Emails']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Emails']"));
			scrollingFunction();
			Utils.sleep(1);
			//driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
			//Utils.sleep(4);
			element = driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(4);
			WebDriverWait wait = new WebDriverWait (driver, 30);
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
			Utils.sleep(4);
			element = driver.findElement(By.xpath("//*[contains(text(),'Response Recipients')]"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//label[text()='Response Subject:']"));
			scrollingFunction();
			Utils.sleep(2);
			try {
				driver.findElements(By.xpath("//input[@maxlength='255']")).get(1).sendKeys(" - Test Draft");
				Utils.sleep(2);
				element = driver.findElements(By.xpath("//input[@maxlength='255']")).get(1);
			} catch (Exception e) {
				driver.findElement(By.xpath("//input[@maxlength='255']")).sendKeys(" - Test Draft");
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
			//Select 2nd one
			element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Wrong Address')]"));
			scrollingFunction();
			Utils.sleep(1);
			Actions actObj = new Actions(driver);
			actObj.moveToElement(driver.findElement(By.xpath(".//option[contains(text(),'OIDP Wrong Address')]"))).doubleClick().build().perform();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'No Clear Connection To USCIS/Lockbox')]")).click();
			Utils.sleep(1);
			driver.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
			//Select 3rd one
			element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]/preceding::option[1]"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath(".//option[contains(text(),'OIDP Closings')]"));
			//scrollingFunction();
			actObj.moveToElement(element).doubleClick().build().perform();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'Internal')]"));
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
			element = driver.findElement(By.xpath(".//*[@id='saveDraftButton']/button"));
			scrollingFunction();
			Utils.sleep(1);
			element.click();
			Utils.sleep(1);
			ele = By.xpath("//div[contains(text(),'Your draft response has been saved successfully.')]");
			fluentWaitForElementVisibility();
			driver.switchTo().defaultContent();
			ele = By.xpath("//button[@title='Close Case']");
			fluentWaitForElementVisibility();
			driver.findElement(ele).click();
			Utils.sleep(2);
			driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Responses']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[@title='Emails']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//th[@title='Response Number']/parent::tr/parent::*/following-sibling::tbody/tr/th"));
			highlightElement();
			driver.switchTo().defaultContent();
		}
		public void createHold(String holdType, String comments) {
			element = driver.findElement(By.xpath("//span[@title='Service Item-Contact Links']"));
			scrollingFunction();
			Utils.sleep(2);
			//driver.findElement(By.xpath("//span[text()='Holds']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
			element = driver.findElement(By.xpath("//span[text()='Holds']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Hold Type']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+holdType+"']")).click();//Transaction in Process
			Utils.sleep(2);
			driver.findElement(By.xpath("//textarea[@maxlength='255']")).sendKeys(comments);
			Utils.sleep(1);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			ele = By.xpath("//span[contains(text(),'Close Hold')]/parent::button");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver.findElement(ele).click();
			Utils.sleep(2);
			driver.navigate().refresh();
			Utils.sleep(2);
			try {
				ele = By.xpath("//button[@title='Close Case']");
				fluentWaitForElementVisibility();
				driver.findElement(ele).click();
				Utils.sleep(2);
			} catch (Exception e) {
				
			}
			try {
			ele = By.xpath("//span[contains(text(),'Close Case')]/parent::button");
			//fluentWaitForElementVisibility();
			//Utils.sleep(2);
			driver.findElement(ele).click();
			} catch (Exception e) {
				
			}
			Utils.sleep(2);
			//driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void validateError() {
			//driver.findElement(By.xpath("//a[@title='Edit']")).click();
			try {
				element = driver.findElement(By.xpath("//a[@title='Edit']/div"));
				//scrollingFunction();
				//Utils.sleep(2);
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			} catch (Exception e) {
				driver.navigate().refresh();
				Utils.sleep(2);
				ele = By.xpath("//a[@title='Edit']/parent::*");
				fluentWaitForElementVisibility();
				Utils.sleep(2);
				driver.findElement(By.xpath("//a[@title='Edit']/parent::*")).click();
			}
			Utils.sleep(4);
			element = driver.findElements(By.xpath("//span[text()='Status']/parent::span")).get(1);
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Status']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='Completed']")).click();//Transaction in Process
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(1);
			ele = By.xpath("//li[contains(text(),'Please remove the Hold(s) before you close the Service Item')]");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			element = driver.findElement(ele);
			highlightElement();
			driver.findElement(By.xpath("//button[@title='Cancel']")).click();
			Utils.sleep(2);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void removeHoldAndMarkCompleted() {
			element = driver.findElement(By.xpath("//span[@title='Service Item-Contact Links']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[@title='Service Item-Contact Links']"));
			scrollingFunction();
			Utils.sleep(1);
			//driver.findElement(By.xpath("//a[contains(text(),'Hold')]/parent::*/parent::*/following-sibling::td[4]/div/a")).click(); --Don't uncomment.
			element = driver.findElement(By.xpath("//a[contains(text(),'Hold')]/parent::*/parent::*/following-sibling::td[4]/div/a"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@role='menu']/ul/li[1]/a")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Resume Date']/parent::*/following-sibling::div/a")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[contains(@class,'todayDate selectedDate')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver.navigate().refresh();
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//*[text()='Assigned']/parent::*/parent::p/parent::*"));
			highlightElement();
			
			driver.findElement(By.xpath("//a[@title='Edit']/parent::*")).click();
			Utils.sleep(4);
			element = driver.findElement(By.xpath("//span[text()='Status']/parent::span"));
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Status']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='Completed']")).click();//Transaction in Process
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			highlightElement();
			driver.navigate().refresh();
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			try {
				element = driver.findElement(By.xpath("//*[text()='Completed']/parent::*/parent::p/parent::*"));
				highlightElement();
			} catch (Exception e) {
				driver.navigate().refresh();
				ele = By.xpath("//a[@title='Edit']/parent::*");
				fluentWaitForElementVisibility();
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//*[text()='Completed']/parent::*/parent::p/parent::*"));
				highlightElement();
			}
		}
		public void relatedServiceItemCreation(String siOrigin) {
			element = driver.findElement(By.xpath("//span[@title='Service Item Team']"));
			scrollingFunction();
			Utils.sleep(3);
			element = driver.findElement(By.xpath("//span[@title='Service Item Team']"));
			scrollingFunction();
			Utils.sleep(2);
			//driver.findElement(By.xpath("//span[text()='Related Service Items']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
			element = driver.findElement(By.xpath("//span[text()='Related Service Items']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::*/preceding-sibling::div[1]"));
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[@title='"+siOrigin+"']")).click();//Transaction in Process
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Subject']/parent::label/following-sibling::input")).sendKeys("Test Child Related SI of "+newSINo);
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Received Date']/parent::*"));
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath(".//label[text()='Date']/following-sibling::a[@class='datePicker-openIcon display']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[contains(@class,'todayDate selectedDate')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void verifyChildServiceItem () {
			driver.findElements(By.xpath("//*[text()='Details']")).get(1).click();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Resolution Details']/parent::button"));
			scrollingFunction();
			Utils.sleep(2);
			relatedSINo = driver.findElement(By.xpath("//span[text()='Service Item Number']/parent::div/following-sibling::div/descendant::*[@data-output-element-id='output-field']")).getText();
			System.out.println("Related Service Item Number : "+relatedSINo);
			element = driver.findElement(By.xpath("//span[text()='Parent Service Item']/parent::div/following-sibling::div/descendant::a[contains(text(),'"+newSINo+"')]"));
			highlightElement();
			Utils.sleep(1);
			element.click();
			Utils.sleep(2);
			try {
			element = driver.findElements(By.xpath("//span[@title='Service Item Comments']")).get(1);
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElements(By.xpath("//a[contains(text(),'"+relatedSINo+"')]/parent::*")).get(1);
			highlightElement();
			Utils.sleep(1);
			} catch (Exception e) {
				element = driver.findElement(By.xpath("//span[@title='Service Item Comments']"));
				scrollingFunction();
				Utils.sleep(2);
				element = driver.findElement(By.xpath("//a[contains(text(),'"+relatedSINo+"')]/parent::*"));
				highlightElement();
				Utils.sleep(1);
			}
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			try {
			driver.findElement(By.xpath("//button[@title='Close "+relatedSINo+"']")).click();
			Utils.sleep(2);
			} catch (Exception e) {
				
			}
			//driver.navigate().refresh();
			//Utils.sleep(4);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void serviceLinkCreation(String contactNm, String role) {
			driver.findElement(By.xpath("//*[@title='Related']")).click();
			element = driver.findElement(By.xpath("//span[@title='Files']"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[@title='Files']"));
			scrollingFunction();
			Utils.sleep(1);
			//driver.findElement(By.xpath("//span[text()='Service Item-Contact Links']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
			element = driver.findElement(By.xpath("//span[text()='Service Item-Contact Links']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@title='Search Contacts']")).sendKeys(contactNm);//Zaim Abid
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@class='listContent']/ul[contains(@class,'lookup__list')]/li[1]")).click();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Role']"));
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Role']/parent::*/following-sibling::div/div[1]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[contains(text(),'"+role+"')]")).click();//AILA Liaison
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Service Item']/parent::div/following-sibling::div/descendant::a[contains(text(),'"+newSINo+"')]"));
			highlightElement();
			Utils.sleep(1);
			linkNumber = driver.findElement(By.xpath("//span[text()='Service Item-Contact Link Number']/parent::div/following-sibling::div/descendant::*[contains(@data-output-element-id,'output-field')]")).getText();
			driver.findElement(By.xpath("//button[@title='Close "+linkNumber+"']")).click();
			Utils.sleep(2);
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
		}
		public void cloneMerge () {
			driver.findElement(By.xpath("//div[text()='Clone']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			element = driver.findElement(ele);
			highlightElement();
			Utils.sleep(2);
			element = driver.findElements(By.xpath("//*[contains(text(),'"+subjectLine+"')]/parent::div/parent::*/parent::*/parent::*/parent::h1")).get(1);
			highlightElement();
			Utils.sleep(1);
			element = driver.findElements(By.xpath("//span[text()='Related Service Items']/parent::a")).get(1);
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElements(By.xpath("//span[text()='Related Service Items']/parent::a")).get(1);
			scrollingFunction();
			Utils.sleep(1);
			String time = driver.findElement(By.xpath("//th[contains(text(),'Field')]/parent::tr/parent::*/following-sibling::tbody/tr[1]/th/span")).getText();
			element = driver.findElement(By.xpath("//th[contains(text(),'Field')]/parent::tr/parent::*/following-sibling::tbody/tr[2]/th/span[contains(text(),'"+time+"')]"));
			highlightElement();
			Utils.sleep(1);
			driver.navigate().refresh();
			Utils.sleep(4);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver.findElements(By.xpath("//*[text()='Details']")).get(0).click();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Service Item Details']/parent::button"));
			scrollingFunction();
			Utils.sleep(2);
			cloneSINo = driver.findElement(By.xpath("//span[text()='Service Item Number']/parent::div/following-sibling::div/descendant::*[@data-output-element-id='output-field']")).getText();
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElements(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'Email')]")).get(0);
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'Test Automation OIDP Flow')]"));
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'Applicant')]"));
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'G')]"));
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'zabid')]"));
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Inquiry Information']/parent::*/parent::h3/parent::div/descendant::*[contains(text(),'Ado')]"));
			highlightElement();
			Utils.sleep(2);
			/*driver.navigate().refresh();
			Utils.sleep(4);
			driver.navigate().refresh();
			Utils.sleep(4);*/
			driver.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			
			Utils.sleep(2);
			driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
			Utils.sleep(2);
			driver.findElements(By.xpath("//*[text()='Details']")).get(1).click();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[text()='Response Details']/parent::button"));
			scrollingFunction();
			Utils.sleep(2);
			WebDriverWait wait = new WebDriverWait (driver, 30);
			
			fetchCorrectIframe2(By.xpath("//table[@class='detailList']/tbody/tr/td[1]/a[contains(text(),'Merge Duplicate Service Item')]"));
			Utils.sleep(1);
			driver.findElement(By.xpath("//table[@class='detailList']/tbody/tr/td[1]/a[contains(text(),'Merge Duplicate Service Item')]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@maxlength='255']")).sendKeys(cloneSINo);
			Utils.sleep(1);
			driver.findElement(By.xpath("//a[@title='Master Lookup (New Window)']")).click();
			Utils.sleep(3);
			
			System.out.println("Window Count :: "+driver.getWindowHandles().size());
			String winHandleBefore = driver.getWindowHandle();
			try {
			for(String winHandle : driver.getWindowHandles()){
				System.out.println(winHandle);
				if (!winHandleBefore.equals(winHandle)) {
			    driver.switchTo().window(winHandle);
				}
			}
			Utils.sleep(4);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//frame[@id='resultsFrame']"))));
			driver.findElement(By.xpath(".//table/tbody/descendant::a[text()='"+cloneSINo+"']")).click();
			Utils.sleep(2);
			driver.switchTo().window(winHandleBefore);
			driver.switchTo().defaultContent();
			} catch (Exception e) {
				driver.switchTo().window(winHandleBefore);
				driver.switchTo().defaultContent();
			}
			fetchCorrectIframe2(By.xpath("//table[@class='detailList']/tbody/tr/td[1]/a[contains(text(),'Merge Duplicate Service Item')]"));
			driver.findElement(By.xpath("//input[@type='submit' and @value='Merge']")).click();
			Utils.sleep(8);
			driver.switchTo().defaultContent();
			driver.navigate().refresh();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(4);
			element = driver.findElement(By.xpath("//*[text()='Archived']/parent::*/parent::p/parent::*"));
			highlightElement();
			Utils.sleep(1);
		}
		public void submitResponseFromClone () throws Exception {
			driver.findElement(By.xpath("//button[@title='Close "+newSINo+"']")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//a[@title='"+cloneSINo+"']")).click();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[@title='Emails']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[@title='Emails']"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//th[@title='Response Number']/parent::tr/parent::*/following-sibling::tbody/tr[1]/td[3]/div/a"));
			//element = driver.findElement(By.xpath("//span[text()='Related Service Items']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			//element.click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//li[@class='uiMenuItem']/a[@title='Edit']")).click();
			Utils.sleep(2);
			
			WebDriverWait wait = new WebDriverWait (driver, 30);
			
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
			element = driver.findElement(By.xpath("//label[text()='Response Subject:']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//*[contains(text(),'Upload Files')]"));
			//scrollingFunction();
			try {
				element.click();
			} catch (Exception e) {
				((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			}
			Utils.sleep(4);
			attach_file_and_send(Constants.privacy_test_attachment_url, false);
			element = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
			scrollingFunction();
			Utils.sleep(2);
			element.click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver.switchTo().defaultContent();
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//button[contains(@title,'Close Edit RES')]")).click();
			Utils.sleep(2);
			driver.navigate().refresh();
			ele = By.xpath("//a[@title='Edit']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(4);
			try {
				driver.findElement(By.xpath("//button[contains(@title,'Close Edit RES')]")).click();
				Utils.sleep(2);
				//driver.navigate().refresh();
				ele = By.xpath("//a[@title='Edit']/parent::*");
				fluentWaitForElementVisibility();
				Utils.sleep(4);
			} catch (Exception e) {
				
			}
			Utils.sleep(5);
			
			element = driver.findElement(By.xpath("//*[text()='Approved']/parent::*/parent::p/parent::*"));
			highlightElement();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[@title='Emails']"));
			scrollingFunction();
			Utils.sleep(2);
			element = driver.findElement(By.xpath("//span[@title='Emails']"));
			scrollingFunction();
			Utils.sleep(1);
			for(int i =0;i<4;i++) {
			try {
				element = driver.findElement(By.xpath("//th[@title='Response Number']/parent::tr/parent::*/following-sibling::tbody/tr[1]/td[1]/span[text()='Sent']"));
				highlightElement();
				break;
			} catch (Exception e) {
				driver.navigate().refresh();
				Utils.sleep(2);
			}
			}
		}
//*************************Supporting functions**************************************
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
		      subjectLine = "Test Automation OIDP Flow 2 - " + randomDateTime;
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
		public void fetchCorrectIframe2(By dropDownWebElement1) {
			int iFrameCount = driver.findElements(By.xpath("//iframe[@title='accessibility title']")).size();
			System.out.println("Frame count :"+iFrameCount);
			for(int i = 0; i<=iFrameCount; i++) {
				try {
					driver.switchTo().frame(driver.findElements(By.xpath("//iframe[@title='accessibility title']")).get(i));
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
		//*************************************************************ISO12664************************************************************
		@Given("^OIDP ISO12664 scenario Registered User is logged in with \"(.*)\"$")
		public void init_ISO12664(String user) throws IOException {
			extent = new ExtentReports(workingDir+"\\test-report\\ISO12664_Service_Item_Link"+randomDateTime1()+".html", true);
			testReporter = extent.startTest("OIDP ");
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
		@When("^OIDP ISO12664 scenario Search for required Internal User \"(.*)\"$")
		public void search_For_Internal_User_ISO12664(String user) throws IOException {
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
		@Then("^OIDP ISO12664 scenario Logging in as Internal user and verifying \"(.*)\"$")
		public void logging_In_As_Internal_User_And_Verify_Profile_ISO12664(String user) throws IOException {
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
		@Then("^OIDP ISO12664 scenario Open an existing service item$")
		public void ISO12664_Open_An_Existing_DI() throws IOException {
			try {
				openAnyExistingSI();
				testReporter.log(LogStatus.PASS, "Open an existing service item and open that service item : "+newSINo);
			} catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Open an existing service item and open that service item : "+newSINo);
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				getResult();
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		@Then("^OIDP ISO12664 scenario Creating a new Service LINK with contact name as \"(.*)\" and role as \"(.*)\"$")
		public void Creating_a_New_Service_Link_ISO12664(String contactNm, String role) throws IOException {
			try {
				serviceLinkCreationWithRoleItemValidation(contactNm, role);
				testReporter.log(LogStatus.PASS, "Creating a new Service LINK. Link Number :"+linkNumber);
			} catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Creating a new Service LINK. Link Number :"+linkNumber);
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				getResult();
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		@Then("^Stop Report Generation for current scenario For OIDP ISO12664 scenario$")
		public void getResult_ISO12664() {
			//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
		}
		@Then("^Close the browser For OIDP ISO12664 scenario$")
		public void flushReporter_ISO12664() {
			driver.close();
			driver.quit();
		}
		public void openAnyExistingSI() {
			Utils.sleep(4);
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
			Utils.sleep(2);
			ele = By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/descendant::a");
			fluentWaitForElementVisibility();
			newSINo = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/descendant::a")).getText();
			driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/descendant::a")).click();
		}
		
		public void serviceLinkCreationWithRoleItemValidation(String contactNm, String role) {
			//driver.findElement(By.xpath("//a[@title='Related']")).click();
			element = driver.findElement(By.xpath("//span[@title='Files']"));
			scrollingFunction();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[@title='Files']"));
			scrollingFunction();
			Utils.sleep(1);
			//driver.findElement(By.xpath("//span[text()='Service Item-Contact Links']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
			element = driver.findElement(By.xpath("//span[text()='Service Item-Contact Links']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			driver.findElement(By.xpath("//input[@title='Search Contacts']")).sendKeys(contactNm);//Zaim Abid
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[@class='listContent']/ul[contains(@class,'lookup__list')]/li[1]")).click();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Role']"));
			scrollingFunction();
			Utils.sleep(1);
			driver.findElement(By.xpath("//span[text()='Role']/parent::*/following-sibling::div/div[1]")).click();
			Utils.sleep(2);
			driver.findElement(By.xpath("//ul[@class='scrollable']/li/a[contains(text(),'"+role+"')]")).click();//Congressional Liaison
			Utils.sleep(1);
			driver.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[@data-aura-class='forceActionsText']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver.findElement(By.xpath("//span[text()='Service Item']/parent::div/following-sibling::div/descendant::a[contains(text(),'"+newSINo+"')]"));
			highlightElement();
			Utils.sleep(1);
			linkNumber = driver.findElement(By.xpath("//span[text()='Service Item-Contact Link Number']/parent::div/following-sibling::div/descendant::*[contains(@data-output-element-id,'output-field')]")).getText();
			element = driver.findElement(By.xpath("//span[text()='Role']/parent::div/following-sibling::div/descendant::*[contains(text(),'"+role+"')]"));
			highlightElement();
		}
		//*********************************************************************************************************************************
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
		@Given("^OIDP Search Response Template Registered User is logged in with \"(.*)\"$")
		public void init_4(String user) throws IOException {
			extent = new ExtentReports(workingDir+"\\test-report\\OIDPSearchTemplate"+randomDateTime1()+".html", true);
			testReporter = extent.startTest("OIDP Auto Approval Flow 2");
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
		@When("^OIDP Search Response Template Search for required Internal User \"(.*)\"$")
		public void search_For_Internal_User_4(String user) throws IOException {
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
		@Then("^OIDP Search Response Template Logging in as Internal user and verifying \"(.*)\"$")
		public void logging_In_As_Internal_User_And_Verify_Profile_4(String user) throws IOException {
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
		@Then("^OIDP Search Response Template Click and open any random Service Item$")
		public void Select_Random_SI() throws IOException {
			try {
				selectRandomSI();
				testReporter.log(LogStatus.PASS, "Click and Open random service item "+newSINo);
			} catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Click and Open random service item "+newSINo);
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				getResult();
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		@Then("^OIDP Search Response Template Select Response Template with Search functionality$")
		public void Select_Template() throws IOException {
			try {
				selectRandomSI();
				testReporter.log(LogStatus.PASS, "Select Response Template with Search functionality and save");
			} catch (Exception e) {
				testReporter.log(LogStatus.FAIL, "Select Response Template with Search functionality and save");
				screenShotPath = GetScreenShot.capture(driver);
				testReporter.log(LogStatus.INFO, "Snapshot : " +testReporter.addScreenCapture(screenShotPath));
				getResult();
				Assert.assertTrue(false);
				e.printStackTrace();
			}
		}
		@Then("^Stop Report Generation for current scenario For OIDP Search Response Template$")
		public void getResult4() {
			//extent.endTest("HD_ISO_VSC_Existing_User_Record_Refresh");
			extent.endTest(testReporter);
			extent.flush();
			extent.close();
		}
		@Then("^Close the browser For OIDP Search Response Template$")
		public void flushReporter4() {
			driver.close();
			driver.quit();
		}
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	public void selectRandomSI () {
		Utils.sleep(4);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@title='Show Navigation Menu']")).click();
		Utils.sleep(1);
		element = driver.findElement(By.xpath(".//*[@id='navMenuList']/div/ul/descendant::a[contains(@title,'Service Items')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(2);
				element = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/span/a"));
				highlightElement();
				newSINo = driver.findElement(By.xpath("//table[@data-aura-class='uiVirtualDataTable']/tbody/tr[1]/th/span/a")).getText();
				System.out.println("Grapped New Random SI: "+newSINo);
		driver.findElement(By.xpath("//a[@title='"+newSINo+"']")).click();
	}
	public void selectResponseTemplate() {
		element = driver.findElement(By.xpath("//span[text()='Emails']"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[text()='Emails']"));
		scrollingFunction();
		Utils.sleep(1);
		//driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']")).click();
		element = driver.findElement(By.xpath("//span[text()='Responses']/parent::a/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New' and @role='button']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		Utils.sleep(4);
		WebDriverWait wait = new WebDriverWait (driver, 30);
		
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe[@title='accessibility title']"))));
		Utils.sleep(4);
		element = driver.findElement(By.xpath("//*[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//label[text()='Response Subject:']"));
		scrollingFunction();
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).click();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("OIDP Good Day");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='OIDP Good Day']/descendant::span[contains(text(),'OIDP Good Day')]")).click();
		Utils.sleep(2);
		element = driver.findElement(By.xpath("//span[contains(text(),'Selected')]/parent::div/following-sibling::div/descendant::*[text()='OIDP Good Day']"));
		highlightElement();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("No Clear Connection To USCIS/Lockbox");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='No Clear Connection To USCIS/Lockbox']/descendant::span[contains(text(),'No Clear Connection To USCIS/Lockbox')]")).click();
		Utils.sleep(2);
		
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).clear();
		driver.findElement(By.xpath("//span[@title='record']/following-sibling::input")).sendKeys("Internal");
		Utils.sleep(2);
		driver.findElement(By.xpath("//li[@data-templatename='Internal']/descendant::span[contains(text(),'Internal')]")).click();
		Utils.sleep(2);
		
	
		element = driver.findElement(By.xpath("//h3[text()='Email History']"));
		scrollingFunction();
		highlightElement();
		element = driver.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(1);
		element.click();
		Utils.sleep(1);
		ele = By.xpath("//button[text()='QC Review']");
		fluentWaitForElementVisibility();
		driver.findElement(ele).click();
		Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		ele = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver.findElement(By.xpath("//a[text()='View Response']")).click();
		driver.switchTo().defaultContent();
		Utils.sleep(4);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.switchTo().activeElement();
		element = driver.findElement(By.xpath("//span[text()='Status']/parent::*/following-sibling::div/descendant::*[text()='Under Review']"));
		highlightElement();
	}
	// Intentionally it was kept to test test each module seperately if there is nay script failure.
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//cloneSINo = "09420229";
		emailLink = "starsoidpqa@dhfvuuaxs0xh1f5gms9i3f49pgw3jbt0eczn6x18g8s53unt8.r-1mwheaa.cs32.apex.sandbox.salesforce.com";
		newSINo = "09420347";
		subjectLine = "Test Automation OIDP Flow 2 - 20201112423";
		OIDP_Email_Auto_Approval_Flow_2 refVar = new OIDP_Email_Auto_Approval_Flow_2();
		refVar.launch();
//		refVar.fetchEmailLink("starsoidpqa");
		refVar.logIntoGmail("https://acumensolutions-com.clearlogin.com/login","zabid",Constants.email_password);
		System.exit(0);
//		refVar.launch();
//		refVar.searchHDISOVSCitems("OIDP Supervisor");
//		refVar.logInAsInternalUser("OIDP Supervisor");
//		refVar.setPercentage("5");
//		refVar.logOut();
		refVar.searchHDISOVSCitems("OIDP Analyst");
		refVar.logInAsInternalUser("OIDP Analyst");
		//refVar.selectRandomSI();
		//refVar.selectResponseTemplate();
		//refVar.captureTheServiceItem();
		//refVar.validateTheNewSI();
		//refVar.editTheNewServiceItem("Applicant", "G1041", "Adoptions", "LFA - Lockbox Filing Assistance", "Card Error – Benefit/Validity Date/Etc", "Test Functionality");
		//refVar.createNewEmailResponse();
		//refVar.createHold("Transaction in Process", "Test Hold Functionality");
		//Utils.sleep(28);
		//refVar.validateError();
		//Utils.sleep(28);
		//refVar.removeHoldAndMarkCompleted();
		//refVar.relatedServiceItemCreation("Email");
		//refVar.verifyChildServiceItem();
		//refVar.serviceLinkCreation("Zaim Abid","AILA Liaison");
		refVar.openAnyExistingSI();
		refVar.serviceLinkCreationWithRoleItemValidation("Zaim Abid", "Congressional Liaison");
		//refVar.cloneMerge();
		//refVar.submitResponseFromClone();
	}

}
