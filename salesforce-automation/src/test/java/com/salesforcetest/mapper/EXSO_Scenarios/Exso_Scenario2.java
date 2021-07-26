package com.salesforcetest.mapper.EXSO_Scenarios;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Exso_Scenario2 {
	private String baseUrl = Constants.salesforce_url;
	public static WebDriver driver8;
	private WebElement element;
	public static String aaoSINm,screenShotPath, exsoSI,clrAssNO1,clrAssNO2;
	private By ele;
	String workingDir = System.getProperty("user.dir");
	static ExtentReports extent8;
	static ExtentTest testReporter8;
	@Given("^Launching the EXSO app and logging in as admin for AAO Service Item scenario2 validation$")
	public void init_1() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\EXSO_AAOContact1_Scenario"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("EXSO Regression Flow Scenario 12 End to End Validation For AAO Contact 1 Service Item Validation");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to Privacy portal");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to Privacy portal");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Logging in as AAO POD user and Create a New service item$")
	public void Create_New_SI_With_POD() throws IOException {
		try {
			createNewSIWithAAOusr();
			testReporter8.log(LogStatus.PASS, "Logged in as AAO Pod user and Create a new Service Item");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Logged in as AAO Pod user and Create a new Service Item");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the newly created POD user SI and fetch the SI number$")
	public void Validate_New_SI_With_POD() throws IOException {
		try {
			validateNewAAOSI();
			testReporter8.log(LogStatus.PASS, "Validate the newly created POD user SI and fetch the SI number: "+aaoSINm);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate the newly created POD user SI and fetch the SI number: "+aaoSINm);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Submit the newly created SI for approval$")
	public void Submit_New_SI_With_POD() throws IOException {
		try {
			submitForApproval();
			testReporter8.log(LogStatus.PASS, "Submit the newly created SI for approval and log out as POD user.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Submit the newly created SI for approval and log out as POD user.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as EXSO Manager and validate the POD user created SI$")
	public void Validate_New_SI_With_EXSO_usr() throws IOException {
		try {
			logInEXSOAndValidate();
			testReporter8.log(LogStatus.PASS, "Log in as EXSO Manager and validate the POD user created SI with all the expected buttons should be available as normal SI.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as EXSO Manager and validate the POD user created SI with all the expected buttons should be available as normal SI.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Change the SI status to Clearance Review and Close and Archive and validate the Si type$")
	public void Change_Stat_of_SI_With_EXSO_usr() throws IOException {
		try {
			statusChangeCloseAndArchieve();
			testReporter8.log(LogStatus.PASS, "Change the SI status to Clearance Review and Close and Archive and validate the Si type");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Change the SI status to Clearance Review and Close and Archive and validate the Si type");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO Stop Report Generation for current scenario EXSO Assignment Scenario12 End to End Scenario1$")
	public void getResult() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^EXSO Scenario12 Close the browser Email auto approval process$")
	public void flushReporter_For_IPO() {
		driver8.close();
		driver8.quit();
	}
//**************************************************************Main Functions**********************************************************************	
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
		driver8 = new ChromeDriver(options);
		driver8.manage().window().maximize(); // maximizes
		driver8.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		login();
	}
	public static void open_another_tab(WebDriver driver8) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver8;
		js.executeScript("window.open('about:blank','_blank');");

		ArrayList<String> tabs = new ArrayList<String>(driver8.getWindowHandles());
		driver8.switchTo().window(tabs.get(1));
	}
	public void login() {
		//try {
		driver8.get(baseUrl);
		Utils.sleep(2);
		driver8.findElement(By.xpath(".//*[@id='username']")).sendKeys(Constants.salesforce_pstaff2_username_Admin);
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='password']")).sendKeys(Constants.salesforce_pstaff2_password_Admin);
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='Login']")).click();
		
		Utils.sleep(3);
		/*} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	@SuppressWarnings("rawtypes")
	public Wait fluentWaitFunctionality(WebDriver driver8)
	{
		@SuppressWarnings({ "unchecked", "deprecation" })
		Wait fwait = new FluentWait(driver8)
		 
	    .withTimeout(10, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver8);
		try {
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele));
		}
		catch(Exception e) {
		}
	}
	public void logInAsPodUser() {
		//driver8.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver8.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
		Utils.sleep(4);
		//driver8.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
		//Actions actObj = new Actions(driver8);
		//actObj.moveToElement(driver8.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("AAO Contact 1");
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='AAO Contact 1']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='AAO Contact 1']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver8;
		js.executeScript("arguments[0].scrollIntoView();", element);
		Utils.sleep(1);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver8;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        Utils.sleep(2);
	}
	//*************************************************************EXSO 2nd SCENARIO********************************************************************
		public void createNewSIWithAAOusr() {
			logInAsPodUser();
			ele = By.xpath("//a[@title='Service Items']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='New']/parent::li")).click();
			ele = By.xpath("//span[text()='Assignment Manager Memo']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver8.findElement(ele);
			highlightElement();
			driver8.findElement(By.xpath("//span[text()='Assignment Manager Memo']/parent::*/preceding-sibling::div/span")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
			Utils.sleep(1);
			ele = By.xpath("//*[contains(text(),'New Service Item: Assignment Manager Memo')]");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			element = driver8.findElement(ele);
			highlightElement();
			driver8.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='Program Office Directorate']/parent::li")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save & New']/following-sibling::button")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[contains(@class,'toastMessage')]/parent::div");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
		}
		public void validateNewAAOSI () throws IOException {
			aaoSINm = driver8.findElement(By.xpath("//span[@title='Service Item Number']/following-sibling::div/descendant::span")).getText();
			try {
				element = driver8.findElement(By.xpath("//span[@title='Contact Name']/parent::div/descendant::a[contains(text(),'AAO Contact 1')]"));
				testReporter8.log(LogStatus.PASS, "Contact Name is updated as AAO Contact 1");
				highlightElement();
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Contact Name is not updated as AAO Contact 1");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Program Office Directorate']/parent::*"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Origin Type is selected as Program Office Directorate");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Origin Type is not selected as Program Office Directorate");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::a[contains(text(),'AAO Contact 1')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as AAO Contact 1");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Owner is not updated as AAO Contact 1");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
		}
		public void logOutPodUser_Parallel() {
			try {
			driver8.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::a")).click();
			} catch (Exception e) {
				driver8.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::a")).click();	
			}
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
			ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
		}
		public void submitForApproval() throws IOException {
			driver8.findElement(By.xpath("//div[@title='Submit for Approval']/parent::*/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//span[text()='Comments']/parent::*/following-sibling::textarea");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.findElement(ele).sendKeys("Submitting it for approval.");
			driver8.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();
			Utils.sleep(6);
			testReporter8.log(LogStatus.PASS, "Service Item is successfully submitted for approval.");
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
			Utils.sleep(4);
			try {
				driver8.navigate().refresh();
				Utils.sleep(4);
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as Operations Queue");
			} catch (Exception e) {
				testReporter8.log(LogStatus.WARNING, "Service Item Owner is not updated as Operations Queue");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Memo");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Memo");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			logOutPodUser_Parallel();
		}
		public void internalUserLogin_Parallel(String internalUserNm) {
			//String internalUserNm = "Privacy Staff 2";
			WebDriverWait wait = new WebDriverWait (driver8, 8);
			Utils.sleep(2);
			try {
				//driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[7]/div")).click();
			} catch (Exception e) {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[6]/div")).click(); //QA env
			}
			Utils.sleep(2);
			driver8.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
			Utils.sleep(2);
			ArrayList<String> tabs = new ArrayList<String>(driver8.getWindowHandles());
			try {
			driver8.switchTo().window(tabs.get(2));
			driver8.switchTo().window(tabs.get(1));
			//System.out.println("I am here3.");
			driver8.close();
			Utils.sleep(2);
			driver8.switchTo().window(tabs.get(2));
			} catch (Exception e) {
				driver8.switchTo().window(tabs.get(0));
				driver8.close();
				Utils.sleep(2);
				driver8.switchTo().window(tabs.get(1));
			}
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
			} catch (Exception e) {
				
			}
			driver8.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
			Utils.sleep(4);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe"))));
			driver8.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
			Utils.sleep(2);
			driver8.switchTo().defaultContent();
			for(int icount=0; icount<4; icount++) {
				try {
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
					driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
					break;
				} catch (Exception e) {
					driver8.get(driver8.getCurrentUrl());
					driver8.navigate().refresh();
					Utils.sleep(4);
				}
			}
		}
		public void openSIfromMainSearch(String siNm) {
			driver8.findElement(By.xpath("//input[@title='Search Salesforce']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//input[@title='Search Salesforce']")).sendKeys(siNm);
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+siNm+"']/parent::div/parent::a")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[@title='Service Item Number']");
			fluentWaitForElementVisibility();
		}
		public void logInEXSOAndValidate() throws IOException {
			internalUserLogin_Parallel("EXSO Service Item Manager");
			openSIfromMainSearch(aaoSINm);
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::span[contains(text(),'Operations Queue')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Owner is updated as Operations Queue");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Owner is not updated as Operations Queue");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//span[text()='Service Item Record Type']/parent::div/following-sibling::div/descendant::span[contains(text(),'Memo')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Memo");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Memo");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Create Assignments']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Create Assignments button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Create Assignments button is not present.");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Create FYI Records']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Create FYI Records button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Create FYI Records button is not present.");
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Generate RCA']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Generate RCA button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Generate RCA button is not present.");
			}
			try {
				element = driver8.findElement(By.xpath("//div[@title='Edit File Information']/parent::a/parent::li"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Edit File Information button is present.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Edit File Information button is not present.");
			}
		}
		public void statusChangeCloseAndArchieve () throws IOException {
			Utils.sleep(4);
			Actions actObj = new Actions(driver8);
			//try {
			//actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'New')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'New')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			//driver8.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			driver8.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//*[@title='Clearance']"));
			scrollingFunction();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//*[@title='Clearance Review']")).click();
			Utils.sleep(1);
			//} catch (Exception e) {
				
			//}
			Utils.sleep(1);
			//element = driver8.findElements(By.xpath("//span[text()='Service Item Record Type']")).get(0);
			//scrollingFunction();
			Utils.sleep(2);
			//driver8.findElement(By.xpath("//*[text()='EXSO Section']/parent::*/following-sibling::div/button")).click();
			element = driver8.findElement(By.xpath("//label[text()='EXSO Section']/following-sibling::div/descendant::input"));
			((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
			Utils.sleep(2);
			driver8.findElement(By.xpath("//*[contains(text(),'Internal Section')]")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(4);
			driver8.navigate().refresh();
			Utils.sleep(1);
			ele = By.xpath("//*[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			ele = By.xpath("//*[contains(text(),'Clearance Review')]/parent::*/parent::*/parent::span");
			fluentWaitForElementVisibility();
			try {
			element = driver8.findElement(ele);
			highlightElement();
			testReporter8.log(LogStatus.PASS, "Status is updated as Clearance Review");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Status is not updated as Clearance Review");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			
			//Closing here
			Utils.sleep(1);
			Actions actObj1 = new Actions(driver8);
			try {
			//actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Clearance Review')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'Clearance Review')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			//driver8.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			driver8.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElements(By.xpath("//*[@title='Closed']")).get(0);
			scrollingFunction();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//*[@title='Closed']")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			} catch (Exception e) {
				driver8.findElement(By.xpath("//button[@title='Save']")).click();
			}
			ele = By.xpath("//span[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//*[contains(text(),'Closed')]/parent::*/parent::*/parent::span");
			fluentWaitForElementVisibility();
			try {
			element = driver8.findElement(ele);
			highlightElement();
			testReporter8.log(LogStatus.PASS, "Status is updated as Closed");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Status is not updated as Closed");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			
			//Archive Here
			element = driver8.findElements(By.xpath("//span[text()='POD Service Item Input']/parent::button")).get(0);
			scrollingFunction();
			Utils.sleep(2);
			actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::div/parent::div/div[2]/button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::*/following-sibling::*/descendant::input")).click();
			Utils.sleep(1);
			try {
				element = driver8.findElement(By.xpath("//span[contains(text(),'Archive Service Item')]/parent::label/parent::*"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Archive Service Item is updated.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Archive Service Item is not updated.");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(4);
			driver8.navigate().refresh();
			Utils.sleep(2);
			driver8.navigate().refresh();
			Utils.sleep(2);
			ele = By.xpath("//*[text()='Internal Section']");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			try {
				//element = driver8.findElement(By.xpath("//*[contains(text(),'Archive')]/parent::*/parent::*/parent::span"));
				element = driver8.findElement(By.xpath("//*[contains(text(),'Closed')]/parent::*/parent::*/parent::span"));
				highlightElement();
				element = driver8.findElement(By.xpath("//*[text()='Service Item Record Type']/parent::*/parent::*/descendant::*[contains(text(),'Archive')]"));
				highlightElement();
				testReporter8.log(LogStatus.PASS, "Service Item Record Type is updated as Archive.");
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Service Item Record Type is not updated as Archive.");
			}
		}
	//**************************************************************************************************************************************************
	//********************************************************New Scenario***************************************************
		public void internalUserLogin(String internalUserNm) {
			//String internalUserNm = "Privacy Staff 2";
			WebDriverWait wait = new WebDriverWait (driver8, 8);
			Utils.sleep(2);
			try {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[7]/div")).click(); //QA env
			} catch (Exception e) {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
			}
			Utils.sleep(2);
			driver8.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
			Utils.sleep(2);
			ArrayList<String> tabs = new ArrayList<String>(driver8.getWindowHandles());
			try {
			driver8.switchTo().window(tabs.get(2));
			driver8.switchTo().window(tabs.get(1));
			//System.out.println("I am here3.");
			driver8.close();
			Utils.sleep(2);
			driver8.switchTo().window(tabs.get(2));
			} catch (Exception e) {
				driver8.switchTo().window(tabs.get(0));
				driver8.close();
				Utils.sleep(2);
				driver8.switchTo().window(tabs.get(1));
			}
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
			} catch (Exception e) {
				
			}
			driver8.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
			Utils.sleep(4);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe"))));
			driver8.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
			Utils.sleep(2);
			driver8.switchTo().defaultContent();
			for(int icount=0; icount<4; icount++) {
				try {
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
					//driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
					driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
					break;
				} catch (Exception e) {
					driver8.get(driver8.getCurrentUrl());
					driver8.navigate().refresh();
					Utils.sleep(4);
				}
			}
		}
		protected void select_service_item_list_option(String serviceItemLinkText) {
			Utils.sleep(4);

			driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
			
			Utils.sleep(4);

			driver8.findElement(By.cssSelector("a[title='Select List View']")).click();

			Utils.sleep(4);

			WebElement dropdown = driver8.findElement(By.className("forceVirtualAutocompleteMenuList"));

			dropdown.findElement(By.linkText(serviceItemLinkText)).click();
			Utils.sleep(2);
		}
		public void createNewServiceItem () {
			WebDriverWait wait = new WebDriverWait (driver8, 8);
			driver8.findElement(By.xpath("//button[@title='Refresh']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//button[@title='Refresh']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//div[text()='New']/parent::*")).click();
			Utils.sleep(1);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Service Item']")));
			driver8.findElement(By.xpath("//span[text()='Correspondence']/parent::div/preceding-sibling::div/span")).click();
			driver8.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Type']/parent::span/following-sibling::div/descendant::a[@class='select']")));
			driver8.findElement(By.xpath("//span[text()='Type']/parent::span/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[contains(text(),'Privacy Notice')]/parent::*")).click();
			driver8.findElement(By.xpath("//*[text()='EXSO Section']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[contains(text(),'Internal Section')]")).click();
			
			driver8.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[contains(text(),'Email')]")).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//span[text()='Policy Sub-Area 1']/parent::span"));
			scrollingFunction();
			driver8.findElement(By.xpath("//span[text()='Subject']/parent::*/following-sibling::input")).sendKeys("TEST EXSO 7.13");
			driver8.findElement(By.xpath("//span[text()='Description']/parent::*/following-sibling::textarea")).sendKeys("TEST EXSO 7.13");
			driver8.findElement(By.xpath("//span[text()='Summary']/parent::*/following-sibling::textarea")).sendKeys("TEST EXSO");
			
			driver8.findElement(By.xpath("//span[text()='Save & New']/parent::button/following-sibling::button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Service Item Number']")));
			exsoSI = driver8.findElement(By.xpath("//*[text()='Service Item Number']/following-sibling::*/*/*")).getText();
			System.out.println("EXSO Service Item number : "+exsoSI);
			Utils.sleep(2);
		}
		private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
			try {
			driver8.findElements(By.xpath("//span[contains(text(),'Upload Files')]")).get(0).click();
			} catch (Exception e) {
				//driver8.findElement(By.xpath("//span[text()='Show actions for this object']/parent::*")).click();
				try {
					element = driver8.findElement(By.xpath("//span[text()='Show actions for this object']/parent::*"));
					((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
					Utils.sleep(2);
					driver8.findElements(By.xpath("//a[@title='Add Files']")).get(0).click();
					Utils.sleep(2);
					driver8.findElement(By.xpath("//span[contains(text(),'Upload Files')]/parent::div/div/button")).click();
				}  catch (Exception e1) {
					//driver8.findElements(By.xpath("//a[@title='Add Files']")).get(0).click();
					((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElements(By.xpath("//a[@title='Add Files']")).get(0));
					Utils.sleep(2);
					driver8.findElement(By.xpath("//span[contains(text(),'Upload Files')]/parent::div/div/button")).click();
				}
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
			driver8.switchTo().activeElement();
			Utils.sleep(4);
			ele = By.xpath("//*[text()='1 of 1 file uploaded']");
			fluentWaitForElementVisibility();
			driver8.findElement(By.xpath("//span[text()='Done']/parent::*")).click();
			Utils.sleep(2);
		}
		private void attach_file_and_send_1(String attachmentPath, boolean pressTab) throws Exception {
			//driver8.findElements(By.xpath("//span[contains(text(),'Upload Files')]")).get(0).click();
			((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
					driver8.findElements(By.xpath("//span[contains(text(),'Upload Files')]")).get(0));
			

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
			driver8.switchTo().activeElement();
			Utils.sleep(4);
		}
		public void changeTheStatus () {
			Actions actObj = new Actions(driver8);
			try {
			actObj.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'New')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//a[@title='Clearance']"));
			scrollingFunction();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[@title='Clearance Review']")).click();
			Utils.sleep(1);
			} catch (Exception e) {
				
			}
			driver8.findElement(By.xpath("//button[@title='Save']")).click();
			Utils.sleep(4);
			driver8.navigate().refresh();
			Utils.sleep(1);
			ele = By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance Review')]");
			fluentWaitForElementVisibility();
			try {
			element = driver8.findElement(ele);
			highlightElement();
			} catch (Exception e) {
				//log error here.
			}
		}
		public void logOut () {
			try {
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
			} catch (Exception e) {
				driver8.navigate().refresh();
				ele = By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]");
				fluentWaitForElementVisibility();
				driver8.findElement(By.xpath(".//*[@id='oneHeader']/descendant::a[contains(text(),'Log out')]")).click();
			}
		}
		
		public void logInAsPodUser_Do() {
			//driver8.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
			driver8.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
			Utils.sleep(4);
			//driver8.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
			//Actions actObj = new Actions(driver8);
			//actObj.moveToElement(driver8.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
			driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("DO Advisors user");
			Utils.sleep(4);
			driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='DO Advisors user']/parent::div/parent::a")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[text()='DO Advisors user']/parent::div/parent::*");
			fluentWaitForElementVisibility();
			driver8.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
			ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
			fluentWaitForElementVisibility();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
		}
		public void createAssignment () {
			WebDriverWait wait = new WebDriverWait (driver8, 14);
			//driver8.findElement(By.xpath("//a[@title='Create Assignments']/parent::*")).click();
			element = driver8.findElement(By.xpath("//a[@title='Create Assignments']"));
			((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
			Utils.sleep(1);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
			//selectDropdownListValue("Authoring Assignment", driver8.findElement(By.xpath("//label[text()='Record Type']/parent::*/following-sibling::td[1]/select")));
			driver8.findElement(By.xpath("//label[text()='Assignment Due Date']/parent::th/following-sibling::td[1]/span/input")).click();
			Utils.sleep(2);
			driver8.findElement(By.xpath(".//*[@id='datePicker']/div[2]/div/a")).click();
			element = driver8.findElement(By.xpath("//label[text()='Back Date Assignment']"));
			scrollingFunction();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("Advisors");
			driver8.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//a[text()='Add']")).get(0).click();
			ele = By.xpath("//td[@class='dataCell']/span[text()='DO']");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
			scrollingFunction();
			driver8.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
			Utils.sleep(1);
			//driver8.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(1).click();
			//Utils.sleep(2);
			driver8.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
			Utils.sleep(6);
			driver8.switchTo().defaultContent();
			ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(1)')]");
			fluentWaitForElementVisibility();
			driver8.navigate().refresh();
			ele = By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(1)')]");
			fluentWaitForElementVisibility();
			//element = driver8.findElement(By.xpath("//span[@title='Status']/following-sibling::div/descendant::span[contains(text(),'Clearance')]"));
			element = driver8.findElement(By.xpath("//*[@title='Status']/parent::div/descendant::*[contains(text(),'Clearance')]"));
			highlightElement();
			clrAssNO1 = driver8.findElements(By.xpath("//span[text()='Clearance Request']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).get(0).getText();
			//clrAssNO2 = driver8.findElements(By.xpath("//span[text()='Clearance Request']/parent::div/preceding-sibling::div[contains(text(),'Record Type')]/parent::*/parent::*/parent::*/parent::*/descendant::a[@rel='noreferrer' and starts-with(text(),'AS-')]")).get(1).getText();
			System.out.println("Clearance Request PVY:"+clrAssNO1);
			//testReporter8.log(LogStatus.PASS, "Round 2 Clearance Request PVY:: "+clrAssNO1);
		}
		public void openAssignment() {
			driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
			ele = By.xpath("//a[@title='Select List View']");
			fluentWaitForElementVisibility();
			driver8.findElement(ele).click();
			ele = By.xpath("//span[contains(text(),'New Assignments - DO')]/parent::*");
			fluentWaitForElementVisibility();
			driver8.findElement(ele).click();
			Utils.sleep(4);
			try {
				ele = By.xpath("//a[@title='"+clrAssNO1+"']");
				driver8.findElement(ele).click();
				testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
			} catch (Exception e) {
				
			}
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
		}
		public void acceptAssignmentForDo () throws IOException {
			driver8.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver8.findElement(By.xpath("//a[@title='Accept Assignment']/parent::li")).click();
			Utils.sleep(2);
			ele = By.xpath("//span[text()='Save']/parent::button[@data-aura-class='uiButton']");
			fluentWaitForElementVisibility();
			driver8.findElement(ele).click();
			ele = By.xpath("//span[contains(@class,'toastMessage')]");
			fluentWaitForElementVisibility();
			Utils.sleep(2);
			driver8.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
			element = driver8.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='In Progress']/parent::div"));
			highlightElement();
			element = driver8.findElement(By.xpath("//span[text()='Information']/parent::button"));
			scrollingFunction();
			try {
				element = driver8.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'DO Advisors user')]"));
			} catch (Exception e) {
				Utils.sleep(8);
				driver8.navigate().refresh();
				ele = By.xpath("//span[text()='Service Item Information']/parent::button");
				fluentWaitForElementVisibility();
				element = driver8.findElement(By.xpath("//span[text()='Information']/parent::button"));
				scrollingFunction();
				element = driver8.findElement(By.xpath("//span[text()='Accepted By']/parent::div/following-sibling::div/descendant::*[contains(text(),'DO Advisors user')]"));
			}
			highlightElement();
			driver8.navigate().refresh();
			ele = By.xpath("//span[text()='Service Item Information']/parent::button");
			fluentWaitForElementVisibility();
		}
	public void executeApproval () {
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		driver8.findElement(By.xpath("//label[text()='Instructions']/following-sibling::div/textarea")).sendKeys("Test Functionality");
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[1]")).sendKeys("DO D1");
		Utils.sleep(1);
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[2]")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='DO D1 User']/parent::td/preceding-sibling::td[1]/a")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[3]")).click();
		Utils.sleep(2);
		//driver8.findElement(By.xpath("//label[text()='Instructions']/following-sibling::div/textarea")).sendKeys("Test Functionality");
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[1]")).sendKeys("DO D2");
		Utils.sleep(1);
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[2]")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='DO D2 User']/parent::td/preceding-sibling::td[1]/a")).click();
		Utils.sleep(1);
		element = driver8.findElement(By.xpath("//a[text()='DO D1 User']/parent::*"));
		highlightElement();
		element = driver8.findElement(By.xpath("//a[text()='DO D2 User']/parent::*"));
		highlightElement();
		WebElement From=driver8.findElement(By.xpath("//a[text()='DO D2 User']/parent::*"));	
        
        //Element on which need to drop.		
        WebElement To=driver8.findElement(By.xpath("//a[text()='DO D1 User']/parent::*"));					
        		
        //Using Action class for drag and drop.		
        Actions act=new Actions(driver8);
        act.dragAndDrop(From, To).build().perform();
        Utils.sleep(2);
        driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(4);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Executive Approval']/parent::div"));
		highlightElement();
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
        Utils.sleep(4);
        element = driver8.findElement(By.xpath("//span[text()='Executive Approvals']/following-sibling::span[contains(text(),'2')]"));
		highlightElement();
	}
	public void logOutPodUser_DO_Advisor() {
		try {
		driver8.findElement(By.xpath("//span[text()='DO Advisors user']/parent::a")).click();
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::a")).click();	
		}
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
		ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void logInAsPodUser_Do2_User() {
		//driver8.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver8.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
		Utils.sleep(4);
		//driver8.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
		//Actions actObj = new Actions(driver8);
		//actObj.moveToElement(driver8.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("DO D2 User");
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='DO D2 User']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='DO D2 User']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
	}
	public void logInAsPodUser_Do1_User() {
		//driver8.findElement(By.xpath("//span[text()='App Launcher']/parent::*")).click();
		driver8.navigate().to(Constants.salesforce_url+"lightning/o/Contact/home");
		Utils.sleep(4);
		//driver8.findElement(By.xpath("//span[text()='Contacts']/parent::*/parent::a")).click();
		//Actions actObj = new Actions(driver8);
		//actObj.moveToElement(driver8.findElements(By.xpath("//li/a[@title='Contacts']")).get(0)).doubleClick().build().perform();
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Contacts and more']")).sendKeys("DO D1 User");
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='DO D1 User']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='DO D1 User']/parent::div/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//a[@title='Show 8 more actions']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Log in to Community as User']")).click();
		ele = By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='USCIS Corr Mgmt']/parent::*")).click();
	}
	private void attach_file_and_send_assign(String attachmentPath, boolean pressTab) throws Exception {
		try {
		driver8.findElement(By.xpath("//button[contains(text(),'Upload Files')]")).click();
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
		driver8.switchTo().activeElement();

		Utils.sleep(4);
	}
	public void validateExecApprovalSection () throws Exception {
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clearance " +clrAssNO1);
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='Assignment Name']/parent::a")).click();
			Utils.sleep(2);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clearance " +clrAssNO1);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		element = driver8.findElement(By.xpath("//a[text()='DO D1 User']/parent::*"));
		highlightElement();
		element = driver8.findElement(By.xpath("//a[text()='DO D2 User']/parent::*"));
		highlightElement();
		driver8.findElement(By.xpath("//td[text()='In Progress']/parent::tr/td[1]/a")).click();
		ele = By.xpath("//*[contains(text(),'Only records with a status')]/parent::*");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(2);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	public void completeExecApproval() throws Exception {
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='In Progress']/parent::td/preceding-sibling::th/descendant::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Executive Approval Name']/parent::*");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Related']/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Files']/parent::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[text()='Add Files']/parent::a")).click();
		ele = By.xpath("//button[contains(text(),'Upload Files')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		attach_file_and_send_assign(Constants.privacy_test_attachment_url,false);
		ele = By.xpath("//*[contains(text(),'1 of 1 file uploaded')]");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//span[text()='Done']/parent::button")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//div[@class='filerow']"));
		highlightElement();
		
		driver8.findElement(By.xpath("//div[text()='Complete Executive Approval']/parent::a")).click();
		ele = By.xpath("//span[text()='Response']/parent::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Response']/parent::span/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[text()='Do Not Concur']/parent::li")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Comments']/parent::label/following-sibling::textarea")).sendKeys("Test do not Concur");
		driver8.findElement(By.xpath("//div[contains(@class,'footer')]/descendant::span[text()='Save']")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='Details']/parent::a")).click();
		Utils.sleep(2);
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Status']/parent::div/following-sibling::div/descendant::span[text()='Complete']");
		fluentWaitForElementVisibility();
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			Utils.sleep(4);
			element = driver8.findElement(ele);
			highlightElement();
		}
		element = driver8.findElement(By.xpath("//span[text()='Instructions']"));
		scrollingFunction();
		ele = By.xpath("//span[text()='Comments']/parent::div/following-sibling::div/descendant::span[contains(text(),'Test do not Concur')]");
		element = driver8.findElement(ele);
		highlightElement();
		ele = By.xpath("//span[text()='Last Modified By']/parent::div/following-sibling::div/descendant::a[text()='DO D2 User']");
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			Utils.sleep(4);
			element = driver8.findElement(ele);
			highlightElement();
		}
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Assignment']/parent::div/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(ele).click();
	}
	public void validateAssignStat() {
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//span[text()='Status']/following-sibling::div/descendant::span[text()='In Progress']"));
		highlightElement();
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//span[text()='Complete']/parent::td/preceding-sibling::th/descendant::a"));
		highlightElement();
		logoutD1D2PodUsr();
	}
	public void logoutD1D2PodUsr() {
		try {
			driver8.findElement(By.xpath("//span[text()='DO D2 User']/parent::a")).click();
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='DO D1 User']/parent::a")).click();	
		}
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[text()='Logout']/parent::*")).click();
		ele = By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[8]/span/button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
	}
	public void validateTheSiwithDoAdvUsr () {
		logInAsPodUser_Do();
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='Created Date']/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver8.findElements(By.xpath("//div[@class='filerow']")).get(0);
		highlightElement();
		element = driver8.findElements(By.xpath("//div[@class='filerow']")).get(1);
		highlightElement();
		ele = By.xpath("//span[text()='Complete']/parent::td/preceding-sibling::th/descendant::a");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		ele = By.xpath("//td[text()='Complete']");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Restart from Non-Concurrence']")).click();
		ele = By.xpath("//div[contains(text(),'Executive Approval process has been restarted')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='In Progress']"));
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(2);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		logOutPodUser_DO_Advisor();
	}
	public void revalidatewithD2Usr () {
		logInAsPodUser_Do2_User();
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='Created Date']/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		element = driver8.findElement(By.xpath("//a[text()='DO D2 User']/parent::*"));
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='In Progress']"));
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(2);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		System.out.println("Exective Approval Name : "
		+driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).getText());
		driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).click();
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*"));
		highlightElement();
		
		driver8.findElement(By.xpath("//div[text()='Complete Executive Approval']/parent::a")).click();
		ele = By.xpath("//span[text()='Response']/parent::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Response']/parent::span/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[text()='Concur with Edits / Comments']/parent::li")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Comments']/parent::label/following-sibling::textarea")).sendKeys("Test Concur with Edits or Comments");
		driver8.findElement(By.xpath("//div[contains(@class,'footer')]/descendant::span[text()='Save']")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='Details']/parent::a")).click();
		Utils.sleep(2);
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Status']/parent::div/following-sibling::div/descendant::span[text()='Complete']");
		fluentWaitForElementVisibility();
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			fluentWaitForElementVisibility();
			element = driver8.findElement(ele);
			highlightElement();
		}
		ele = By.xpath("//span[text()='Assignment']/parent::div/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(ele).click();
	}
	public void validateD1UserStatusAftReval () {
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D1')]"));
		highlightElement();
		
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		ele = By.xpath("//td[text()='Complete']");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Restart from Non-Concurrence']")).click();
		ele = By.xpath("//div[contains(text(),'This option is only available when at least one record has a status of')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Restart from Beginning']")).click();
		ele = By.xpath("//div[contains(text(),'Executive Approval process has been restarted')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='In Progress']"));
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='New']"));
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(1);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
	}
	public void markCompletedAgain () {
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		System.out.println("Exective Approval Name : "
		+driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).getText());
		driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).click();
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*"));
		highlightElement();
		
		driver8.findElement(By.xpath("//div[text()='Complete Executive Approval']/parent::a")).click();
		ele = By.xpath("//span[text()='Response']/parent::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Response']/parent::span/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[text()='Concur with Edits / Comments']/parent::li")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Comments']/parent::label/following-sibling::textarea")).sendKeys("Test Concur with Edits or Comments");
		driver8.findElement(By.xpath("//div[contains(@class,'footer')]/descendant::span[text()='Save']")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='Details']/parent::a")).click();
		Utils.sleep(2);
		driver8.navigate().refresh();
		Utils.sleep(4);
		ele = By.xpath("//span[text()='Status']/parent::div/following-sibling::div/descendant::span[text()='Complete']");
		fluentWaitForElementVisibility();
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			Utils.sleep(4);
			element = driver8.findElement(ele);
			highlightElement();
		}
		ele = By.xpath("//span[text()='Assignment']/parent::div/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(ele).click();
		Utils.sleep(2);
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		ele = By.xpath("//span[text()='Complete']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D2')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D1')]"));
		highlightElement();
		logoutD1D2PodUsr();
	}
	public void validateD1Usr () {
		logInAsPodUser_Do1_User();
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='Created Date']/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		System.out.println("Exective Approval Name 1 : "
		+driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).getText());
		//driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).click();
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D1')]"));
		highlightElement();
		driver8.findElement(By.xpath("//a[contains(@title,'more actions')]/parent::div")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//a[@title='Executive Approval']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		element = driver8.findElement(By.xpath("//a[text()='DO D1 User']/parent::*"));
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Restart from Beginning']")).click();
		ele = By.xpath("//div[contains(text(),'Executive Approval process has been restarted')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='In Progress']"));
		highlightElement();
		element = driver8.findElement(By.xpath("//td[text()='New']"));
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
        Utils.sleep(2);
		driver8.switchTo().defaultContent();
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D2')]"));
		highlightElement();
		element = driver8.findElement(By.xpath("//span[text()='New']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D1')]"));
		highlightElement();
		
		System.out.println("Exective Approval Name 2 : "
				+driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).getText());
				driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).click();
				element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*"));
				highlightElement();
		driver8.findElement(By.xpath("//div[text()='Complete Executive Approval']/parent::a")).click();
		ele = By.xpath("//span[text()='Response']/parent::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Response']/parent::span/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[text()='Concur with Edits / Comments']/parent::li")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Comments']/parent::label/following-sibling::textarea")).sendKeys("Test Concur with Edits or Comments");
		driver8.findElement(By.xpath("//div[contains(@class,'footer')]/descendant::span[text()='Save']")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='Details']/parent::a")).click();
		Utils.sleep(2);
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Status']/parent::div/following-sibling::div/descendant::span[text()='Complete']");
		fluentWaitForElementVisibility();
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			Utils.sleep(4);
			element = driver8.findElement(ele);
			highlightElement();
		}
		ele = By.xpath("//span[text()='Assignment']/parent::div/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(ele).click();
		
		//D1 user
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		ele = By.xpath("//th[text()='Executive Approval Name']");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		System.out.println("Exective Approval Name 3 : "
				+driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).getText());
				driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*/preceding-sibling::th/descendant::a")).click();
				element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*"));
				highlightElement();
				element = driver8.findElement(By.xpath("//span[text()='In Progress']/parent::*"));
				highlightElement();
				element = driver8.findElement(By.xpath("//span[text()='User']/parent::*/following-sibling::div/descendant::a[contains(text(),'D1')]/parent::div"));
				highlightElement();
				element = driver8.findElement(By.xpath("//span[text()='Sequence']/parent::div/following-sibling::div/descendant::span[text()='2']"));
				highlightElement();
		driver8.findElement(By.xpath("//div[text()='Complete Executive Approval']/parent::a")).click();
		ele = By.xpath("//span[text()='Response']/parent::span");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Response']/parent::span/following-sibling::div/descendant::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[text()='Concur']/parent::li")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='Comments']/parent::label/following-sibling::textarea")).sendKeys("Test Concur");
		driver8.findElement(By.xpath("//div[contains(@class,'footer')]/descendant::span[text()='Save']")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[text()='Details']/parent::a")).click();
		Utils.sleep(1);
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Status']/parent::div/following-sibling::div/descendant::span[text()='Complete']");
		fluentWaitForElementVisibility();
		try {
		element = driver8.findElement(ele);
		highlightElement();
		} catch (Exception e) {
			driver8.navigate().refresh();
			Utils.sleep(4);
			element = driver8.findElement(ele);
			highlightElement();
		}
		ele = By.xpath("//span[text()='Assignment']/parent::div/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(ele).click();
		driver8.findElement(By.xpath("//span[text()='RELATED']/parent::*")).click();
		element = driver8.findElement(By.xpath("//span[text()='Complete']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D1')]"));
		highlightElement();
		element = driver8.findElement(By.xpath("//span[text()='Complete']/parent::td/preceding-sibling::td[1]/a[contains(text(),'D2')]"));
		highlightElement();
		logoutD1D2PodUsr();
	}
	public void doAdvUserValidation () throws Exception {
		logInAsPodUser_Do();
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[text()='Created Date']/parent::*")).click();
			Utils.sleep(1);
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			//testReporter8.log(LogStatus.PASS, "DO contact Clerance " +clrAssNO1);
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//div[text()='Add Files']/parent::a")).click();
		ele = By.xpath("//button[contains(text(),'Upload Files')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		attach_file_and_send_assign(Constants.privacy_test_attachment_url,false);
		ele = By.xpath("//*[contains(text(),'1 of 1 file uploaded')]");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//span[text()='Done']/parent::button")).click();
		ele = By.xpath("//span[contains(@class,'toastMessage')]");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//div[@class='filerow']"));
		highlightElement();
		
		driver8.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		Utils.sleep(1);
		Utils.sleep(3);
		driver8.findElement(By.xpath("//textarea")).click();
			driver8.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(0).sendKeys("DO Advisors user");
		try {
			//driver8.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).click();
			//driver8.findElements(By.xpath("//td[1]/span[@class='lookupInput']/input")).get(1).sendKeys("DO Advisors user");
			driver8.findElement(By.xpath("//textarea")).click();
		} catch (Exception e) {
			driver8.findElement(By.xpath("//textarea")).click();
		}
		Utils.sleep(1);
		selectDropdownListValue("Concur with Edits / Comments", driver8.findElement(By.xpath("//div[@class='pbSubsection']/table/tbody/tr[2]/descendant::select")));
		Utils.sleep(1);
		driver8.findElement(By.xpath("//textarea")).sendKeys("Automated test comments");
		driver8.findElement(By.xpath("//a[contains(text(),'D2')]/parent::*/parent::*/following-sibling::td[3]/div/input[@type='checkbox' and not(contains(@checked,'checked'))]")).click();
		Utils.sleep(1);
		//driver8.findElements(By.xpath("//input[@type='checkbox' and not(contains(@checked,'checked'))]")).get(1).click();
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(2);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		driver8.navigate().refresh();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//span[text()='Service Item']/parent::div/following-sibling::div/descendant::a")).click();
		ele = By.xpath("//span[text()='Service Item Owner']/parent::div/following-sibling::div/descendant::a[text()='EXSO Service Item Manager']");
		fluentWaitForElementVisibility();
		
		element = driver8.findElement(By.xpath("//li[4]/div/span[text()='Status']/following-sibling::div[1]//descendant::span[text()='Clearance Review']"));
		highlightElement();
	}
	public void selectDropdownListValue(String listName, WebElement dropDownWebElement) {
		Select dropdown = new Select(dropDownWebElement);
		dropdown.selectByValue(listName);
	}
	//start from 55.11 time again. 2nd Round completed. Star from new function
	//#######################################################################################################################
	//*********************************************
	@Given("^Launching the EXSO app and logging in as EXSO Service Item Manager for scenario 13 validation$")
	public void init_2() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\EXSO_DO_User_Scenario13_"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("EXSO Regression Flow Scenario 13 End to End Validation For DO D1, Do D2 and Do Advisour Service Item Validation");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to Privacy portal");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to Privacy portal");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Logging in as EXSO Service Item Manager and Create a New service item$")
	public void function_2() throws IOException {
		try {
			internalUserLogin("EXSO Service Item Manager");
			select_service_item_list_option("All Open Service Items");
			createNewServiceItem();
			testReporter8.log(LogStatus.PASS, "Logged in as EXSO Service Item Manager user and Create a new Service Item. SI number: "+exsoSI);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Logged in as EXSO Service Item Manager user and Create a new Service Item.  SI number: "+exsoSI);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Attach New File to the service item$")
	public void function_3() throws IOException {
		try {
			attach_file_and_send(Constants.privacy_pia_attachment_url, false);
			testReporter8.log(LogStatus.PASS, "Upload an attachment to the service item."+exsoSI);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Upload an attachment to the service item."+exsoSI);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Create a New Assignment and Validate the Service Item status is changed to CLEARANCE$")
	public void function_4() throws IOException {
		try {
			checkBoxForApproval();
			createAssignment();
			logOut();
			testReporter8.log(LogStatus.PASS, "Validate new assignment "+aaoSINm+" and Service Item status changed to Clearance");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate new assignment "+aaoSINm+" and Service Item status changed to Clearance");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as Do Advisor User and accept the assignment$")
	public void function_5() throws IOException {
		try {
			logInAsPodUser_Do();
			openAssignment();
			acceptAssignmentForDo();
			testReporter8.log(LogStatus.PASS, "Validate Do Advisors User and able to accept the Assignment.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate Do Advisors User and able to accept the Assignment.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Do the execute approval with the logged in user$")
	public void function_6() throws IOException {
		try {
			executeApproval();
			logOutPodUser_DO_Advisor();
			testReporter8.log(LogStatus.PASS, "Validate Execute Approval is completed with Do advisors user.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate Execute Approval is completed with Do advisors user.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in DO D2 user and validate the execute approval and complete the service item$")
	public void function_7() throws IOException {
		try {
			logInAsPodUser_Do2_User();
			validateExecApprovalSection();
			completeExecApproval();
			testReporter8.log(LogStatus.PASS, "Log in DO D2 user and validate the execute approval and complete the service item.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in DO D2 user and validate the execute approval and complete the service item.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the status of D1 and D2 user$")
	public void function_8() throws IOException {
		try {
			validateAssignStat();
			testReporter8.log(LogStatus.PASS, "Validate the status of D1 and D2 user");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate the status of D1 and D2 user");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as DO Advisors User and validate the D1 and D2 user executive approval status$")
	public void function_9() throws IOException {
		try {
			validateTheSiwithDoAdvUsr();
			testReporter8.log(LogStatus.PASS, "Log in as DO Advisors User and validate the D1 and D2 user executive approval status and modify.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as DO Advisors User and validate the D1 and D2 user executive approval status and modify.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as DO D2 User and chnage the status from restart from the begining and validate the status is changed$")
	public void function_10() throws IOException {
		try {
			revalidatewithD2Usr();
			testReporter8.log(LogStatus.PASS, "Log in as DO D2 User and chnage the status from restart from the begining and validate the status is changed");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as DO D2 User and chnage the status from restart from the begining and validate the status is changed");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate D1 user status$")
	public void function_11() throws IOException {
		try {
			validateD1UserStatusAftReval();
			testReporter8.log(LogStatus.PASS, "Validate D1 user status");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate D1 user status");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Mark the D1 one item complete and validate the status$")
	public void function_12() throws IOException {
		try {
			markCompletedAgain();
			validateD1Usr();
			testReporter8.log(LogStatus.PASS, "Mark the D1 one item complete and avalidate the status");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Mark the D1 one item complete and avalidate the status");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log in as Do Advisors user and Verify the status of DO D1 and D2 line items status$")
	public void function_13() throws IOException {
		try {
			doAdvUserValidation();
			testReporter8.log(LogStatus.PASS, "Log in as Do Advisors user and Verify the status of DO D1 and D2 line items status");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as Do Advisors user and Verify the status of DO D1 and D2 line items status");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO Stop Report Generation for scenario EXSO Scenario13 End to End Scenario$")
	public void getResult_14() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^EXSO Scenario13 Close the browser Email auto approval process$")
	public void flushReporter_15() {
		driver8.close();
		driver8.quit();
	}
	//*********************************************
	//############################################################################
	@Given("^Launching the EXSO app and logging in as EXSO Service Item Manager for Edit Assiment validation$")
	public void init_4() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\EXSO_Edit_Assi_And_Make_Archive_"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("EXSO Validation for edit functionality of assigment and mark it Archive.");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to EXSO portal");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to EXSO portal");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Internal User log in as EXSO Service Item Manager for EXSO$")
	public void function_01() throws IOException {
		try {
			internalUserLogin("EXSO Service Item Manager");
			testReporter8.log(LogStatus.PASS, "Log in as Internal User");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Open any automation script created Service Item$")
	public void function_02() throws IOException {
		try {
			openRecentlySI();
			testReporter8.log(LogStatus.PASS, "Open any random Service item from recently open window");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Open any random Service item from recently open window");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Edit the service item and validate the edited comments are populated in the assignment details$")
	public void function_03() throws Exception {
		try {
			editAssi();
			testReporter8.log(LogStatus.PASS, "Edit Assignment validation and validate the edited comments are populated.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Edit Assignment validation and validate the edited comments are populated.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Make an assignment as Archive$")
	public void function_04() throws IOException {
		try {
			makeAssignmentArchieve();
			testReporter8.log(LogStatus.PASS, "Change the assignment as Archive.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Change the assignment as Archive.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^EXSO Stop Report Generation for scenario Edit and Archive validation$")
	public void getResult_18() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^EXSO Close the browser for Edit and Archive validation$")
	public void flushReporter_19() {
		driver8.close();
		driver8.quit();
	}
	public void openRecentlySI () throws Exception {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		try {
			driver8.findElements(By.xpath("//a[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[@title='Service Item Number']/parent::a")).click();
			Utils.sleep(3);
			driver8.findElements(By.xpath("//a[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		}
	}
	public void editAssi() throws Exception {
		ele = By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		//driver8.findElement(By.xpath("//a[@title='Edit Assignments']")).click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(By.xpath("//a[@title='Edit Assignments']")));
		ele = By.xpath("//span[text()='Assignment Due Date']/parent::*/following-sibling::div/descendant::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		//driver8.findElement(ele).click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(ele));
		ele = By.xpath("//span[contains(@class,'todayDate selectedDate')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//span[contains(@class,'todayDate selectedDate')]")).click();
		Utils.sleep(1);
		driver8.findElements(By.xpath("//label[text()='Instructions']/following-sibling::*/textarea")).get(0).sendKeys("Test ISO 12878 Instructions");
		driver8.findElement(By.xpath("//label[text()='Service Item Description']/following-sibling::*/textarea")).sendKeys("Test ISO 12878 Description");
		element = driver8.findElement(By.xpath("//label[text()='Service Item Description']/following-sibling::*/textarea"));
		scrollingFunction();
		//driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(0).click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
				driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(0));
		try {
			//driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(1).click();
			//driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(3).click();
			((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
					driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(1));
			((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
					driver8.findElements(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::input[@type='checkbox']")).get(3));
		} catch (Exception e) {
			
		}
		//element = driver8.findElement(By.xpath("//button[text()='Update Assignments']"));
		//scrollingFunction();
		attach_file_and_send_1(Constants.privacy_pia_attachment_url, false);
		element = driver8.findElement(By.xpath("//button[text()='Update Assignments']"));
//		scrollingFunction();
		Utils.sleep(1);
		//element.click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		ele = By.xpath("//span[contains(text(),'Assignments have been successfully')]/parent::*");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//a[@title='Edit Assignments']"));
		scrollingFunction();
		String Time = driver8.findElements(By.xpath("//span[text()='Assignment Due Date']/parent::*/following-sibling::div/descendant::input")).get(1).getText();
		System.out.println(Time);
		element = driver8.findElement(By.xpath("//*[text()='Include']/parent::*/parent::*/parent::*/following-sibling::tbody/descendant::span[contains(text(),'"+Time+"')]"));
		highlightElement();
		String fetchTxt = driver8.findElements(By.xpath("//label[text()='Instructions']/following-sibling::*/textarea")).get(1).getText();
		if (fetchTxt.contains("Test ISO 12878 Instructions")) {
			testReporter8.log(LogStatus.PASS, "Instructions are populated successfully");
		} else {
			System.out.println("Instructions did not get updated.");
			testReporter8.log(LogStatus.FAIL, "Instructions are not populated.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		}
		fetchTxt = driver8.findElements(By.xpath("//label[text()='Description']/following-sibling::*/textarea")).get(0).getText();
		if (fetchTxt.contains("Test ISO 12878 Description")) {
			testReporter8.log(LogStatus.PASS, "Description are populated successfully");
		} else {
			System.out.println("Description did not get updated.");
			testReporter8.log(LogStatus.FAIL, "Descriptions are not populated.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		}
	}
	//############################################################################
	public void makeAssignmentArchieve () throws IOException {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		try {
			driver8.findElements(By.xpath("//a[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		} catch (Exception e) {
			driver8.findElement(By.xpath("//span[@title='Service Item Number']/parent::a")).click();
			Utils.sleep(3);
			driver8.findElements(By.xpath("//a[contains(text(),'EXSO Automation Test SI')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		}
		ele = By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		
		element = driver8.findElement(By.xpath("//span[text()='Assignments']/following-sibling::span[contains(text(),'(')]"));
		scrollingFunction();
		//driver8.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]")).click();
		element = driver8.findElement(By.xpath("//span[text()='Assignments']/parent::span[contains(text(),'View All')]"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		ele = By.xpath("//span[contains(text(),'Assignment Name')]/parent::a");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		
		driver8.findElements(By.xpath("//a[contains(@title,'AS-')]")).get(0).click();
		ele = By.xpath("//button[contains(text(),'Submit Change Request')]");
		fluentWaitForElementVisibility();
		element = driver8.findElements(By.xpath("//span[contains(text(),'Created By')]/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(ele));
		Actions actObj1 = new Actions(driver8);
		actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/preceding-sibling::input")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver8.findElements(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/following-sibling::div/descendant::input")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		////Correct the path
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
				driver8.findElement(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/following-sibling::div/descendant::input")));
		//driver8.findElement(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/following-sibling::div/descendant::input")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(4);
		driver8.navigate().refresh();
		ele = By.xpath("//button[contains(text(),'Submit Change Request')]");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//*[text()='Archived']/parent::*/parent::p"));
		highlightElement();
	}
	//**************************************************************************************************************************************
	@Given("^Launching the app and logging in as Privacy Staff with Task for merge scenario$")
	public void init_5() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\Privacy_Staff_New_Task_"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("Privacy Staff 2 Create different task in different SI and merge it to 3rd SI.");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to portal and Logs in Privacy Staff 2");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to portal and Logs in Privacy Staff 2");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Internal User logs in as Privacy Staff 2 for Privacy$")
	public void function_05() throws IOException {
		try {
			internalUserLogin("Privacy Staff 2");
			testReporter8.log(LogStatus.PASS, "Log in as Internal User");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as Internal User");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Identify the 3 new Service Item for Privacy merge scenario$")
	public void function_06() throws IOException {
		try {
			mergeTaskScenarioForPrivacy();
			testReporter8.log(LogStatus.PASS, "Identify the 3 new Service Item for Privacy merge scenario.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Identify the 3 new Service Item for Privacy merge scenario.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Upload file and create task for first Service item$")
	public void function_07() throws IOException {
		try {
			createTask1st();
			testReporter8.log(LogStatus.PASS, "Upload file and create task for first Service item.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Upload file and create task for first Service item.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Upload file and create task for second Service item$")
	public void function_08() throws Exception {
		try {
			createTask2nd();
			testReporter8.log(LogStatus.PASS, "Upload file and create task for second Service item.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Upload file and create task for second Service item.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Merge the first two si to the third one and validate the SI change to Archive$")
	public void function_09() throws IOException {
		try {
			mergeWith3rdOne();
			testReporter8.log(LogStatus.PASS, "Merge the first two si to the third one and validate the SI change to Archive.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Merge the first two si to the third one and validate the SI change to Archive.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Privacy Stop Report Generation for Merge validation$")
	public void getResult_20() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^Privacy Close the browser for Merge Validation$")
	public void flushReporter_21() {
		driver8.close();
		driver8.quit();
	}
	static String si1,si2,si3;
	public void mergeTaskScenarioForPrivacy () throws Exception {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		ele = By.xpath("//span[text()='Service Items']/following-sibling::a");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		driver8.findElements(By.xpath("//span[text()='All Incident Service Items - CW']/parent::a")).get(0).click();
		for (int i=0;i<8;i++) {
			try {
				Utils.scrollWindow(driver8,400);
				Utils.sleep(1);
				element = driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/parent::*/preceding-sibling::tr[4]")).get(0);
				scrollingFunction();
			} catch (Exception e) {
				element = driver8.findElements(By.xpath("//span[text()='Archived']")).get(5);
				scrollingFunction();
			}
		}
		
		si1 = driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).getText();
		si2 = driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(1).getText();
		si3 = driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(2).getText();
		testReporter8.log(LogStatus.PASS, "First SI: "+si1+". Second SI: "+si2+" will get merge with third Si: "+si3);
	}
	
	public void createTask1st() throws Exception {
		driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a")).get(0).click();
		ele = By.xpath("//a[text()='Related']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(By.xpath("//a[text()='Related']")));
		Utils.sleep(2);
		attach_file_and_send(Constants.sorn_attachment_url,false);
		element = driver8.findElements(By.xpath("//span[text()='Emails']/following-sibling::span[contains(text(),'(')]/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//driver8.findElements(By.xpath("//a[@title='New Task']")).get(0).click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElements(By.xpath("//a[@title='New Task']")).get(0));
		Utils.sleep(4);
		driver8.findElement(By.xpath("//h2[text()='New Task']/parent::*/descendant::label[text()='Subject']/following-sibling::*/descendant::input")).sendKeys("ISO 13552 TEST SI ACTIVITY 1");
		driver8.findElement(By.xpath("//h2[text()='New Task']/parent::*/descendant::*[text()='Comments']/parent::*/parent::*/descendant::textarea")).sendKeys("ISO 13552 TEST SI ACTIVITY 1");
		driver8.findElements(By.xpath("//span[text()='Save & New']/parent::button/following-sibling::button[2]")).get(0).click();
		Utils.sleep(2);
		testReporter8.log(LogStatus.PASS, "Task Creation and File update is completed for "+si1);
		driver8.navigate().refresh();
		ele = By.xpath("//a[text()='Related']");
		fluentWaitForElementVisibility();
		Utils.sleep(3);
	}
	public void createTask2nd() throws Exception {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		ele = By.xpath("//span[text()='Service Items']/following-sibling::a");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).sendKeys(si2);
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+si2+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		driver8.navigate().refresh();
		ele = By.xpath("//a[text()='Related']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(By.xpath("//a[text()='Related']")));
		Utils.sleep(2);
		attach_file_and_send(Constants.privacy_pia_attachment_url,false);
		element = driver8.findElements(By.xpath("//span[text()='Emails']/following-sibling::span[contains(text(),'(')]/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//driver8.findElements(By.xpath("//a[@title='New Task']")).get(0).click();
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElements(By.xpath("//a[@title='New Task']")).get(0));
		Utils.sleep(4);
		driver8.findElement(By.xpath("//h2[text()='New Task']/parent::*/descendant::label[text()='Subject']/following-sibling::*/descendant::input")).sendKeys("ISO 13552 TEST SI ACTIVITY 2");
		driver8.findElement(By.xpath("//h2[text()='New Task']/parent::*/descendant::*[text()='Comments']/parent::*/parent::*/descendant::textarea")).sendKeys("ISO 13552 TEST SI ACTIVITY 2");
		driver8.findElements(By.xpath("//span[text()='Save & New']/parent::button/following-sibling::button[2]")).get(0).click();
		Utils.sleep(2);
		testReporter8.log(LogStatus.PASS, "Task Creation and File update is completed for "+si2);
	}
	public void mergeWith3rdOne () {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		ele = By.xpath("//span[text()='Service Items']/following-sibling::a");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		driver8.findElements(By.xpath("//span[text()='All Incident Service Items - CW']/parent::a")).get(0).click();
		for (int i=0;i<8;i++) {
			try {
				Utils.scrollWindow(driver8,300);
				Utils.sleep(1);
				element = driver8.findElements(By.xpath("//span[text()='New']/parent::*/parent::*/parent::*/preceding-sibling::tr[4]")).get(0);
				scrollingFunction();
			} catch (Exception e) {
				element = driver8.findElements(By.xpath("//span[text()='Archived']")).get(5);
				scrollingFunction();
			}
		}
		//String si1 = "00001081",si2 = "00001083", si3 = "00001084";
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
				driver8.findElement(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a[text()='"+si1+"']/parent::*/parent::*/preceding-sibling::td[1]/descendant::label/span[1]")));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
				driver8.findElement(By.xpath("//span[text()='New']/parent::*/parent::*/preceding-sibling::th/descendant::a[text()='"+si2+"']/parent::*/parent::*/preceding-sibling::td[1]/descendant::label/span[1]")));
		//driver8.findElement(By.xpath("//a[@title='Merge Duplicate Service Items']")).click();
		try {
			driver8.findElement(By.xpath("//a[text()='Clear All']")).click();
		} catch (Exception e) {
			
		}
		try {
			driver8.findElements(By.xpath("//span[text()='Dismiss notification']/parent::*")).get(0).click();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//span[text()='Dismiss notification']/parent::*")).get(0).click();
			Utils.sleep(2);
			driver8.findElements(By.xpath("//span[text()='Dismiss notification']/parent::*")).get(0).click();
			Utils.sleep(2);
		} catch (Exception e) {
			
		}
		Utils.sleep(2);
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(By.xpath("//a[@title='Merge Duplicate Service Items']")));
		Utils.sleep(2);
		driver8.findElement(By.xpath("//h2[text()='Merge Duplicate Service Items']/parent::*/following-sibling::div[1]/descendant::input")).click();
		driver8.findElement(By.xpath("//h2[text()='Merge Duplicate Service Items']/parent::*/following-sibling::div[1]/descendant::input")).sendKeys(si3);
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::div[@title='"+si3+"']/parent::*/parent::a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//h2[text()='Merge Duplicate Service Items']/parent::*/following-sibling::div[2]/descendant::span[text()='Save']/parent::button")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='OK']/parent::button")).click();
		Utils.sleep(8);
		
		element = driver8.findElements(By.xpath("//span[text()='Archived']/parent::*")).get(10);
		scrollingFunction();

		element = driver8.findElement(By.xpath("//span[text()='Archived']/parent::*/parent::*/preceding-sibling::th/descendant::a[text()='"+si1+"']"));
		scrollingFunction();
		highlightElement();
		element = driver8.findElement(By.xpath("//span[text()='Archived']/parent::*/parent::*/preceding-sibling::th/descendant::a[text()='"+si2+"']"));
		scrollingFunction();
		highlightElement();
	}
	
	//###########################################################################################################################
	@Given("^Launching the app and logging in as Paul Miller for assignment and status verification and AAO user Assignment verification$")
	public void init_6() throws IOException {
		extent8 = new ExtentReports(workingDir+"\\test-report\\EXSO_4_Scenario_"+randomDateTime1()+".html", true);
		testReporter8 = extent8.startTest("logging in as Paul Miller for assignment and status verification and AAO user Assignment verification");
		try {
			init();
			testReporter8.log(LogStatus.PASS, "User logs in successfully to portal and Logs in Paul Miller");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User logs in successfully to portal and Logs in Paul Miller");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Internal User logs in as PAUL MILLER$")
	public void function_14() throws IOException {
		try {
			internalUserLogin("Paul Miller");
			testReporter8.log(LogStatus.PASS, "Log in as Internal Paul Miller User");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log in as Internal Paul Miller User");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Create a Service Item with paul miller$")
	public void function_15() throws IOException {
		try {
			createNewServiceItem_Paul();
			testReporter8.log(LogStatus.PASS, "Service Item Creation SuccessFul with Paul Miller #"+exsoSI);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Service Item Creation SuccessFul with Paul Miller #"+exsoSI);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the assignment creation and user should get an error$")
	public void function_16() throws IOException {
		try {
			validateAssignment();
			testReporter8.log(LogStatus.PASS, "User should get an Error Message");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User should get an Error Message");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the assignment after override checkbox is marked$")
	public void function_17() throws Exception {
		try {
			checkBoxForApproval();
			testReporter8.log(LogStatus.PASS, "Validate the assignment after override checkbox is marked.");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate the assignment after override checkbox is marked.");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the assignment after override checkbox and user should be able to create assignment$")
	public void function_18() throws IOException {
		try {
			validateAssignmentafterApproval();
			checkBoxForApproval();
			submitForApproval_2();
			testReporter8.log(LogStatus.PASS, "User shpuld be able to create assignment and submit for approval");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User shpuld be able to create assignment and submit for approval");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log out and Log in as EXSO Manager and appriove the SI$")
	public void function_19() throws IOException {
		try {
			logOut();
			approveTheSiWithEXSO("EXSO Service Item Manager");
			testReporter8.log(LogStatus.PASS, "User shpuld be able to create assignment and submit for approval");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "User shpuld be able to create assignment and submit for approval");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Log out and Log in as PAUL MILLER and create an Assignment and Grab the assignment number$")
	public void function_20() throws IOException {
		try {
			logOut();
			createAssignmentWithMiller("Paul Miller");
			testReporter8.log(LogStatus.PASS, "Log out and Log in as PAUL MILLER and create an Assignment and Grab the assignment number "+clrAssNO1);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Log out and Log in as PAUL MILLER and create an Assignment and Grab the assignment number "+clrAssNO1);
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the Inactive, USPassbook is present in the SI status dropdown$")
	public void function_21() throws IOException {
		try {
			statusChanges3Chk();
			testReporter8.log(LogStatus.PASS, "All expected status are present in the dropdown");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "All expected status are present in the dropdown");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Validate the Assignment properties and able to mark it as completed$")
	public void function_22() throws IOException {
		try {
			ISO13864Scenario();
			testReporter8.log(LogStatus.PASS, "Validate the Assignment properties and able to mark it as completed");
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Validate the Assignment properties and able to mark it as completed");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			getResult();
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
	@Then("^Stop Report Generation for Paul Miller Scenario$")
	public void getResult_23() {
		extent8.endTest(testReporter8);
		extent8.flush();
		extent8.close();
	}
	@Then("^Close the browser for Paul Miller Scenario$")
	public void flushReporter_24() {
		driver8.close();
		driver8.quit();
	}
	public void createNewServiceItem_Paul () throws Exception {
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		driver8.findElement(By.xpath("//div[text()='New']/parent::*")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New Service Item']")));
		driver8.findElement(By.xpath("//span[text()='Correspondence']/parent::div/preceding-sibling::div/span")).click();
		driver8.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		Utils.sleep(2);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Type']/parent::span/following-sibling::div/descendant::a[@class='select']")));
//		driver8.findElement(By.xpath("//span[text()='Type']/parent::span/following-sibling::div/descendant::a[@class='select']")).click();
//		Utils.sleep(2);
//		driver8.findElement(By.xpath("//a[contains(text(),'Privacy Notice')]/parent::*")).click();
		driver8.findElement(By.xpath("//*[text()='EXSO Section']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[contains(text(),'Internal Section')]")).click();
		
		driver8.findElement(By.xpath("//span[text()='Service Item Origin']/parent::*/following-sibling::div/descendant::a[@class='select']")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//a[contains(text(),'Email')]")).click();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//span[text()='Policy Sub-Area 1']/parent::span"));
		scrollingFunction();
		driver8.findElement(By.xpath("//span[text()='Subject']/parent::*/following-sibling::input")).sendKeys("ISO - 13868 TEST EXSO Assignment Approval Process");
		driver8.findElement(By.xpath("//span[text()='Description']/parent::*/following-sibling::textarea")).sendKeys("ISO - 13868 TEST EXSO Assignment Approval Process");
		driver8.findElement(By.xpath("//span[text()='Summary']/parent::*/following-sibling::textarea")).sendKeys("ISO - 13868 TEST EXSO Assignment Approval Process");
		
		driver8.findElement(By.xpath("//span[text()='Save & New']/parent::button/following-sibling::button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title='Service Item Number']")));
		exsoSI = driver8.findElement(By.xpath("//*[text()='Service Item Number']/following-sibling::*/*/*")).getText();
		System.out.println("EXSO Service Item number : "+exsoSI);
		Utils.sleep(2);
		attach_file_and_send(Constants.privacy_pia_attachment_url, false);
	}
	public void validateAssignment () {
		WebDriverWait wait = new WebDriverWait(driver8, 10);
		element = driver8.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		ele = By.xpath("//*[text()='Service Item Not Approved']/parent::*");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		driver8.findElement(By.xpath("//input[@value='Ok']")).click();
		Utils.sleep(2);
		driver8.switchTo().defaultContent();
	}
	public void checkBoxForApproval () {
		element = driver8.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		//((JavascriptExecutor)driver8).executeScript("arguments[0].click();", driver8.findElement(ele));
		Actions actObj1 = new Actions(driver8);
		actObj1.moveToElement(driver8.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/preceding-sibling::input")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		element = driver8.findElements(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/following-sibling::div/descendant::input")).get(0);
		scrollingFunction();
		Utils.sleep(2);
		////Correct the path
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", 
				driver8.findElement(By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/following-sibling::div/descendant::input")));
		//driver8.findElement(By.xpath("//span[contains(text(),'Archive Assignment')]/parent::*/following-sibling::div/descendant::input")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//button[@title='Save']")).click();
		Utils.sleep(4);
		driver8.navigate().refresh();
		ele = By.xpath("//span[contains(text(),'Override Approvals')]/parent::*/preceding-sibling::input");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.navigate().refresh();
		ele = By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	public void validateAssignmentafterApproval() {
		element = driver8.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		WebDriverWait wait = new WebDriverWait (driver8, 14);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//input[contains(@id,'saveButton')]"));
		scrollingFunction();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//input[contains(@value,'Cancel')]")).click();
		driver8.navigate().refresh();
		ele = By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
	}
	// Call checkBoxForApproval function
	public void submitForApproval_2() {
		driver8.findElement(By.xpath("//div[@title='Submit for Approval']/parent::*/parent::*")).click();
		Utils.sleep(1);
		ele = By.xpath("//span[text()='Comments']/parent::*/following-sibling::textarea");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		driver8.findElement(ele).sendKeys("Submitting it for approval.");
		driver8.findElement(By.xpath("//span[text()='Submit']/parent::button")).click();
		Utils.sleep(4);
		//testReporter8.log(LogStatus.PASS, "Service Item is successfully submitted for approval.");
		driver8.navigate().refresh();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Status']/parent::*/following-sibling::div[1]/descendant::*[contains(text(),'QC Approval Request Submitted')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver8.findElement(ele);
		highlightElement();
	}
	//Call logOut () function
	//log in as EXSO service item manager
	public void approveTheSiWithEXSO (String internalUserNm) {
		WebDriverWait wait = new WebDriverWait(driver8, 10);
		ele = By.xpath("//input[@title='Search Setup']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Setup']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@title,'User')]"))));
		driver8.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
		Utils.sleep(2);
		driver8.switchTo().defaultContent();
		
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).sendKeys(exsoSI);
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+exsoSI+"']/parent::div/parent::a")).click();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//span[text()='Service Item History']/parent::*"));
		scrollingFunction();
		Utils.sleep(4);
		//element = driver8.findElement(By.xpath("//span[text()='Service Item History']/parent::span[contains(text(),'View All')]"));
		//scrollingFunction();
		//driver8.findElement(By.xpath("//span[text()='Approval History']/parent::*/parent::h2/parent::*/parent::*/following-sibling::div[1]/descendant::span[text()='Show actions for this object']/parent::*/parent::a")).click();
		element = driver8.findElement(By.xpath("//span[text()='Approval History']/parent::*/parent::h2/parent::*/parent::*/following-sibling::div[1]/descendant::span[text()='Show actions for this object']/parent::*/parent::a"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(2);
		driver8.findElements(By.xpath("//a[@title='Approve']")).get(0).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//textarea")).sendKeys("ISO 13868 Approval SI");
		driver8.findElement(By.xpath("//span[text()='Approve']/parent::button")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Status']/parent::*/following-sibling::div[1]/descendant::*[contains(text(),'QC EXSO Manager Approved')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver8.findElement(ele);
		highlightElement();
	}
	//Call logout () function
	//log in as John Miller
	public void createAssignmentWithMiller (String internalUserNm) throws IOException {
		WebDriverWait wait = new WebDriverWait(driver8, 10);
		ele = By.xpath("//input[@title='Search Setup']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[@title='Search Setup']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
		Utils.sleep(4);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@title,'User')]"))));
		driver8.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
		Utils.sleep(2);
		driver8.switchTo().defaultContent();
		
		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@placeholder,'Search Service Items')]")).sendKeys(exsoSI);
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+exsoSI+"']/parent::div/parent::a")).click();
		Utils.sleep(2);
		ele = By.xpath("//span[text()='Status']/parent::*/following-sibling::div[1]/descendant::*[contains(text(),'QC EXSO Manager Approved')]");
		fluentWaitForElementVisibility();
		Utils.sleep(1);
		element = driver8.findElement(ele);
		highlightElement();
		
		element = driver8.findElement(By.xpath("//a[@title='Create Assignments']"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[@title='Create Multiple Assignments']"))));
		driver8.findElement(By.xpath("//span[@class='dateInput']/span/a")).click();
		Utils.sleep(2);
		if(driver8.findElement(By.xpath("//label[text()='Instructions']/parent::*/following-sibling::td/textarea")).getText().contains("ISO - 13868 TEST EXSO Assignment Approval Process")) {
			//testReporter8.log(LogStatus.PASS, "Instructions are populated in the Create Assignment field.");
			System.out.println("Instructions are populated in the Create Assignment field.");
		} else {
			//testReporter8.log(LogStatus.FAIL, "Instructions are not populated in the Create Assignment field.");
//			screenShotPath = GetScreenShot.capture(driver8);
//			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			System.out.println("Instructions are not populated in the Create Assignment field.");
		}
		
		element = driver8.findElement(By.xpath("//label[text()='Back Date Assignment']"));
		scrollingFunction();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("PVY");
		driver8.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver8.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		ele = By.xpath("//td[@class='dataCell']/span[text()='PVY']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).clear();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//div[contains(@title,'Add Account')]/following-sibling::div[@class='pbSubsection']/descendant::label[contains(text(),'Search:')]/following-sibling::input[1]")).sendKeys("AAO");
		Utils.sleep(1);
		driver8.findElement(By.xpath("//input[contains(@id,'AssignmentTableSection:searchButton')]")).click();
		Utils.sleep(2);
		driver8.findElements(By.xpath("//a[text()='Add']")).get(0).click();
		Utils.sleep(1);
		ele = By.xpath("//td[@class='dataCell']/span[text()='AAO']");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//td[@class='dataCell']/span[text()='AAO']"));
		highlightElement();
		element = driver8.findElement(By.xpath("//*[text()='Select Attachment(s)']"));
		scrollingFunction();
		driver8.findElements(By.xpath("//*[text()='Select Attachment(s)']/parent::*/following-sibling::div/descendant::input")).get(0).click();
		Utils.sleep(1);
		Utils.sleep(2);
		driver8.findElement(By.xpath("//input[contains(@id,'saveButton')]")).click();
		Utils.sleep(6);
		driver8.switchTo().defaultContent();
		clrAssNO1 = driver8.findElements(By.xpath("//span[text()='AAO']/parent::*/parent::*/parent::*/following-sibling::li/descendant::*[contains(text(),'Clearance Request')]/parent::*/parent::*/parent::*/parent::*/preceding-sibling::h3/descendant::a")).get(0).getText();
		System.out.println("Assignment Clearance Request No. "+clrAssNO1);
	}
	//Time start 9 min
	public void statusChanges3Chk () throws IOException {
		Actions actObj = new Actions(driver8);
		try {
		//actObj.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Clearance')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
		actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
		Utils.sleep(2);
		//driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
		driver8.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
		Utils.sleep(2);
		element = driver8.findElement(By.xpath("//*[@title='USCIS FO Passback']"));
		scrollingFunction();
		driver8.findElement(By.xpath("//*[@title='USCIS FO Passback']")).click();
		Utils.sleep(1);
		driver8.findElement(By.xpath("//button[@title='Cancel']")).click();
		testReporter8.log(LogStatus.PASS, "Unable to select USCIS FO Passback as Status");
		Utils.sleep(4);
		} catch (Exception e) {
			testReporter8.log(LogStatus.FAIL, "Unable to select USCIS FO Passback as Status");
			screenShotPath = GetScreenShot.capture(driver8);
			testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		}
		driver8.navigate().refresh();
		ele =By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		try {
			//actObj.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Clearance')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			//driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			driver8.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//*[@title='DHS FO Passback']"));
			scrollingFunction();
			driver8.findElement(By.xpath("//*[@title='DHS FO Passback']")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Cancel']")).click();
			testReporter8.log(LogStatus.PASS, "Unable to select DHS FO Passback as Status");
			Utils.sleep(4);
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Unable to select DHS FO Passback as Status");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
		driver8.navigate().refresh();
		ele =By.xpath("//a[@title='Create Assignments']");
		fluentWaitForElementVisibility();
		try {
			//actObj.moveToElement(driver1.findElements(By.xpath("//span[contains(text(),'Clearance')]/parent::span/parent::div/button")).get(0)).doubleClick().build().perform();
			actObj.moveToElement(driver8.findElements(By.xpath("//*[contains(text(),'Clearance')]/parent::*/parent::*/parent::*/following-sibling::button")).get(0)).doubleClick().build().perform();
			Utils.sleep(2);
			//driver1.findElements(By.xpath("//span[text()='Status']/parent::span/following-sibling::div/descendant::a[@class='select']")).get(0).click();
			driver8.findElements(By.xpath("//*[text()='Status']/following-sibling::div")).get(0).click();
			Utils.sleep(2);
			element = driver8.findElement(By.xpath("//*[@title='Inactive']"));
			scrollingFunction();
			driver8.findElement(By.xpath("//*[@title='Inactive']")).click();
			Utils.sleep(1);
			driver8.findElement(By.xpath("//button[@title='Cancel']")).click();
			testReporter8.log(LogStatus.PASS, "Unable to select Inactive as Status");
			Utils.sleep(4);
			} catch (Exception e) {
				testReporter8.log(LogStatus.FAIL, "Unable to select Inactive as Status");
				screenShotPath = GetScreenShot.capture(driver8);
				testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
			}
	}
	public void ISO13864Scenario () throws IOException {
		logOut();
		logInAsPodUser();
		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
		ele = By.xpath("//a[@title='Select List View']");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		ele = By.xpath("//span[contains(text(),'Open Clearance Requests')]/parent::*");
		fluentWaitForElementVisibility();
		driver8.findElement(ele).click();
		Utils.sleep(4);
		try {
			ele = By.xpath("//a[@title='"+clrAssNO1+"']");
			driver8.findElement(ele).click();
			testReporter8.log(LogStatus.PASS, "AAO contact Clerance " +clrAssNO1);
		} catch (Exception e) {
			
		}
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		driver8.findElement(By.xpath("//a[@title='Complete Assignment']/parent::li")).click();
		WebDriverWait wait = new WebDriverWait (driver8, 8);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver8.findElement(By.xpath("//iframe[contains(@name,'vfFrameId')]"))));
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[1]")).sendKeys("PVY Contact 1");
		Utils.sleep(2);
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[2]")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::td/preceding-sibling::td[1]/a")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[1]")).clear();
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[1]")).sendKeys("AAO Contact 1");
		Utils.sleep(2);
		testReporter8.log(LogStatus.PASS, "Selected the User");
		screenShotPath = GetScreenShot.capture(driver8);
		testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		driver8.findElement(By.xpath("//label[contains(text(),'Search')]/following-sibling::input[2]")).click();
		Utils.sleep(2);
		driver8.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::td/preceding-sibling::td[1]/a")).click();
		Utils.sleep(2);
		testReporter8.log(LogStatus.PASS, "Selected the User");
		screenShotPath = GetScreenShot.capture(driver8);
		testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(1);
		ele = By.xpath("//*[contains(text(),'one must be marked as Primary')]/parent::*");
		fluentWaitForElementVisibility();
		element = driver8.findElement(ele);
		highlightElement();
		testReporter8.log(LogStatus.PASS, "Error msg appeared.");
		screenShotPath = GetScreenShot.capture(driver8);
		testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		element = driver8.findElement(By.xpath("//span[text()='AAO Contact 1']/parent::td/preceding-sibling::td[1]/input"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		testReporter8.log(LogStatus.PASS, "One Primary user selection");
		screenShotPath = GetScreenShot.capture(driver8);
		testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		element = driver8.findElement(By.xpath("//span[text()='PVY Contact 1']/parent::td/preceding-sibling::td[1]/input"));
		((JavascriptExecutor)driver8).executeScript("arguments[0].click();", element);
		Utils.sleep(1);
		testReporter8.log(LogStatus.PASS, "One Primary user selection");
		screenShotPath = GetScreenShot.capture(driver8);
		testReporter8.log(LogStatus.INFO, "Snapshot : " +testReporter8.addScreenCapture(screenShotPath));
		Select sel = new Select(driver8.findElement(By.xpath("//label[text()='Clearance Request Response']/parent::*/following-sibling::td[1]/select")));
		sel.selectByValue("Concur");
		driver8.findElement(By.xpath("//input[@value='Save']")).click();
		Utils.sleep(4);
		driver8.switchTo().defaultContent();
		ele = By.xpath("//span[text()='Service Item Information']/parent::button");
		fluentWaitForElementVisibility();
		element = driver8.findElement(By.xpath("//ul[@role='list']/li[@role='listitem'][2]/descendant::span[text()='Complete']/parent::div"));
		highlightElement();
		testReporter8.log(LogStatus.PASS, "Marked the Assihnment as Completed.");
	}
	
	//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Exso_Scenario2 ref = new Exso_Scenario2();
		ref.init();
		//ref.doD1UsrReportLinkValidation();
//		exsoSI = "00021642";
//		clrAssNO1 = "AS-005155";
		//ref.internalUserLogin("Privacy Staff 2");
		ref.internalUserLogin("Paul Miller");
		/*ref.createNewServiceItem_Paul();
		ref.validateAssignment();
		ref.checkBoxForApproval();
		ref.validateAssignmentafterApproval();
		ref.checkBoxForApproval();
		ref.submitForApproval_2();
		ref.logOut();
		ref.approveTheSiWithEXSO("EXSO Service Item Manager");*/
		ref.logOut();
		ref.createAssignmentWithMiller("Paul Miller");
		ref.statusChanges3Chk();
		ref.ISO13864Scenario();
		//ref.mergeTaskScenario();
//		ref.openRecentlySI();
//		ref.makeAssignmentArchieve();
//		ref.select_service_item_list_option("All Open Service Items");
		//ref.createNewServiceItem();
//		driver8.findElement(By.xpath("//a[@title='Service Items']/parent::*")).click();
//		Utils.sleep(2);
//		driver8.findElement(By.xpath("//a[@title='"+exsoSI+"']")).click();
//		Utils.sleep(2);
//		ref.attach_file_and_send(Constants.privacy_pia_attachment_url, false);
//		ref.createAssignment();
//		ref.logOut();
//		ref.logInAsPodUser_Do();
//		ref.openAssignment();
//		ref.acceptAssignmentForDo();
//		Utils.sleep(2);
//		driver8.findElement(By.xpath("//a[@title='Assignments']/parent::*")).click();
//		Utils.sleep(2);
//		driver8.findElement(By.xpath("//a[@title='Select List View']")).click();
//		Utils.sleep(2);
//		driver8.findElement(By.xpath("//span[contains(text(),'Open Assignments - DO')]/parent::*")).click();
//		Utils.sleep(4);
//		try {
//			driver8.findElement(By.xpath("//a[@title='"+clrAssNO1+"']")).click();
//			//testReporter8.log(LogStatus.PASS, "DO contact Clearance " +clrAssNO1);
//		} catch (Exception e) {
//			driver8.findElement(By.xpath("//span[text()='Assignment Name']/parent::a")).click();
//			Utils.sleep(2);
//			driver8.findElement(By.xpath("//a[@title='"+clrAssNO1+"']")).click();
//			//testReporter8.log(LogStatus.PASS, "DO contact Clearance " +clrAssNO1);
//		}
		//Utils.sleep(2);
//		ref.executeApproval();
//		ref.logOutPodUser_DO_Advisor();
//		ref.logInAsPodUser_Do2_User();
//		ref.validateExecApprovalSection();
//		ref.completeExecApproval();
//		ref.validateAssignStat();
//		ref.validateTheSiwithDoAdvUsr();
//		ref.revalidatewithD2Usr();
//		ref.validateD1UserStatusAftReval();
//		ref.markCompletedAgain();
//		ref.validateD1Usr();
//		ref.doAdvUserValidation();
		//aaoSINm = "00021649";
		//ref.createNewSIWithAAOusr();
		//ref.validateNewAAOSI();
		//ref.submitForApproval();
		//ref.logInEXSOAndValidate();
		ref.openSIfromMainSearch(aaoSINm);
		//ref.statusChangeCloseAndArchieve();
	}

}
