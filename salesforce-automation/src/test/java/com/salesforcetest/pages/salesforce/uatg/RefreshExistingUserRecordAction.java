package com.salesforcetest.pages.salesforce.uatg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Calendar;
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

import com.salesforcetest.main.uatg.HD_ISO_VSC_Service_Request_Rejection_E2E;
import com.salesforcetest.mapper.srire2e.HD_ISO_VSC_New_Service_Response_Rejection_Flow;
import com.salesforcetest.pages.salesforce.SalesforceLogin;
import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class RefreshExistingUserRecordAction {
	public static WebDriver driver1;
	private WebElement element1;
	public static String newSINo1, emailLink1, subjectLine1;
	private By ele1;
	
	public RefreshExistingUserRecordAction(WebDriver driver1) {
		this.driver1 = driver1;
	}
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		driver1 = new ChromeDriver();
		driver1.manage().window().maximize(); // maximizes
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver1).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
	}
	public void highlightElement() {
		JavascriptExecutor js = (JavascriptExecutor) driver1;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element1);
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
		 
	    .withTimeout(20, TimeUnit.SECONDS)
	 
	    .pollingEvery(1, TimeUnit.SECONDS)
	 
	    .ignoring(NoSuchElementException.class);
		return fwait;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void fluentWaitForElementVisibility()
	{	
		Wait fwait = fluentWaitFunctionality(driver1);
		try
		{
		fwait.until(ExpectedConditions.visibilityOfElementLocated(ele1));
		}
		catch(Exception e) {
		}
	}
	public void verifyProfile() {
		driver1.findElement(By.id("userNavLabel")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//a[text()='My Profile']")).click();
		Utils.sleep(2);
		ele1 = By.xpath(".//*[@id='BackToServiceDesk_Tab']/a");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(By.id("tailBreadcrumbNode"));
		highlightElement();
		driver1.findElement(ele1).click();
		Utils.sleep(2);
	}
	public void searchHDISOVSCitems(String user) throws AWTException {
		try {
			//clear all tab code
			driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
			Utils.sleep(2);
			((JavascriptExecutor)driver1).executeScript("arguments[0].click();", 
					driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")));
			Utils.sleep(4);
			} catch (Exception e) {
				
			}
		try {
		//clear all tab code
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		} catch (Exception e) {
			
		}
		driver1.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		driver1.findElement(By.id("phSearchInput")).sendKeys(user);
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::ul[@class='autoCompleteGroup']/li[1]/a")).click();
		Utils.sleep(4);
	}
	public void scrollingFunction() {
		JavascriptExecutor js = (JavascriptExecutor)driver1;
		js.executeScript("arguments[0].scrollIntoView();", element1);
	}
	public void logInAsInternalUser(String user) {
		WebDriverWait wait = new WebDriverWait (driver1, 10);
		ele1 = By.id("moderatorMutton");
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
			fluentWaitForElementVisibility();
			element1 = driver1.findElement(By.id("moderatorMutton"));
			element1.click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(ele1);
			fluentWaitForElementVisibility();
			element1 = driver1.findElement(By.id("moderatorMutton"));
			element1.click();
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//a[@id='USER_DETAIL']/span")).click();
		Utils.sleep(1);
		driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[2]"))));
		driver1.findElements(By.xpath("//*[text()='Active']")).get(0).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='topButtonRow']/input[contains(@value,'Login')]")).click();
		driver1.switchTo().defaultContent();
		ele1 = By.xpath("//a[text()='"+user+"']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//a[text()='Back to USCIS CRM']")).click();
		Utils.sleep(2);
	}
	public void customerSearch(String customer) throws AWTException {
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		//clear all tab code
		try {
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		driver1.switchTo().alert().accept();
		} catch (Exception e) {
			
		}
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Customer Search']")).click();
		Utils.sleep(4);
		driver1.findElement(By.id("phSearchInput")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		ele1 = By.xpath("//*[text()='Customer Search']");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath(".//table[@class='detailList']/tbody/tr[1]/td[1]/input")).sendKeys(customer);
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//*[@id='asyncSearchButton']")).click();
		Utils.sleep(4);
		ele1 = By.xpath(".//*[@id='asynchronousResults']/descendant::*[contains(text(),'Customers Matching Primary/Secondary')]");
		fluentWaitForElementVisibility();
		driver1.switchTo().defaultContent();
	}
	public void customerRefresh() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 40);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver1.findElement(By.xpath("//table[@id='asynchAccountResultTable']/tbody/tr[1]/td[1]/input[@value='Refresh']")).click();
		Utils.sleep(2);
		ele1 = By.xpath(".//img[@alt='CONFIRM']");
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(ele1));
		} catch (Exception e) {
			
		}
		driver1.switchTo().defaultContent();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//*[@class='x-btn-split']")).click();
		Utils.sleep(1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		element1 = driver1.findElement(ele1);
		highlightElement();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void clickOnRecordItemFromOwnerPage (String applicationRecord2){
		driver1.findElement(By.xpath("//span[contains(@class,'x-tab-strip-text')]/span[@class='tabText' and text()='EDVARD EDOUARD']")).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//h3[contains(text(),'Applications')]"));
		element1 = driver1.findElement(By.xpath("//h3[contains(text(),'Applications')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver1.switchTo().frame(driver1.findElement(By.xpath("//iframe[@title='ApplicationPartiesApplicationDetailTable']")));
		element1 = driver1.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		driver1.findElement(By.xpath("//div[2]/div/div/a[1]/span")).click();
		Utils.sleep(2);
		element1.click();
		driver1.switchTo().defaultContent();
		driver1.switchTo().defaultContent();
	}
	public void searchForApplicationRecordWithAppNo(String applicationRecord) throws AWTException {
		driver1.findElement(By.id("phSearchInput")).sendKeys(applicationRecord);
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/div[1]/span")).click();
		Utils.sleep(4);
	}
	public void openApplicationRecordFromGlobalSearchResultPanel(String applicationRecord2) {
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver1, 15);
		fetchCorrectIframe(By.xpath("//*[text()='Show More']"));
		driver1.findElement(By.xpath(".//*[@id='Application__c']/div[2]/div/div[1]/table/tbody")).click();
		driver1.findElement(By.xpath("//*[text()='Show More']")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::iframe[contains(@src,'UnifiedSearchResults')]"))));
		driver1.findElement(By.xpath("//div[2]/div/div/a[1]/span")).click();
		element1 = driver1.findElement(By.xpath("//a[text()='"+applicationRecord2+"']"));
		scrollingFunction();
		highlightElement();
		element1.click();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element1 = driver1.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
	}
	public void createNewServiceItem() {
		driver1.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void createNewServiceItemParam(String applicationRecord2) {
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		element1 = driver1.findElement(By.xpath("//*[@class='pageDescription' and contains(text(),'"+applicationRecord2+"')]"));
		highlightElement();
		driver1.findElement(By.xpath(".//input[@value='Create Service Item']")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void setOrgName(String orgName) {
		WebDriverWait wait = new WebDriverWait (driver1, 15);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[1]"))));
		driver1.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input"));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]/input")).sendKeys(orgName);
	}
	public void setEmail(String email) { //String email
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[4]/input")).sendKeys(email);
		
	}
	public void selectSenderType(String senderType) {
		selectDropdownListValue(senderType, driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[2]/td[2]/span/select")));
		Utils.sleep(2);
	}
	public void setRandomSubjectAndDesAndFormType() {
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		Random randNumber = new Random();
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys("Test Subject_"+randNumber.nextInt(10000));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys("Test Description_"+randNumber.nextInt(10000));
		selectRandomDropdownListValue(driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		Utils.sleep(2);
	}
	public void setParamSubjectAndDesAndFormType(String subject, String description, String formType) {
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[2]/div/input")).sendKeys(subject);
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]/textarea")).sendKeys(description);
		selectDropdownListValue(formType, driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		Utils.sleep(2);
	}
	public void setformNumberAndFormType(String formNumber, String formType) {
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'How Can We Help You')]"));
		scrollingFunction();
		selectDropdownListValue(formNumber, driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]/div/span/select")));
		selectDropdownListValue(formType, driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[4]/div/span/span/select")));
		//driver1.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setRandomCategoryAndKind() {
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		Random randNumber = new Random();
		selectRandomDropdownListValue(driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectRandomDropdownListValue(driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys("Auto Generated Comments_"+randNumber.nextInt(10000));
		//driver1.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void setParamCategoryAndKind(String category, String kind, String comments) {
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'Category / Kind')]"));
		scrollingFunction();
		selectDropdownListValue(category,driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/span/select")));
		selectDropdownListValue(kind,driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/span/span/select")));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
		//driver1.switchTo().defaultContent();
		Utils.sleep(2);
	}
	public void selectSIOrigin(String serviceItem) { //String serviceItem
		//String serviceItem = "Email";
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		try {
		selectDropdownListValue(serviceItem, driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(serviceItem, driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[3]/td[2]/div/span/select")));
		}
		//driver1.switchTo().defaultContent();
	}
	public void selectInitialQueue(String initialQueue) { //String initialQueue
		//String initialQueue = "VAWA_UPFD";
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		try {
		selectDropdownListValue(initialQueue, driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[2]/span/select")));
		} catch (Exception e) {
			selectDropdownListValue(initialQueue, driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/span/select")));
		}
		//driver1.switchTo().defaultContent();
	}
	public void setReceivedDate(String receivedDate) throws AWTException { //String receivedDate
		//String receivedDate = "10/4/2018 9:43 AM";
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[4]/td[2]/div/span/input")).sendKeys(receivedDate);
		Robot robot = new Robot();
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
	}
	public void setResponseComments(String text) throws AWTException { //String receivedDate
		//String receivedDate = "10/4/2018 9:43 AM";
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[5]/td[4]/textarea")).sendKeys(text);
		//driver1.switchTo().defaultContent();
	}
	public void clickOnSaveSI() {
		//WebDriverWait wait = new WebDriverWait (driver1, 15);
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::div[contains(@class,'sd_primary_container x-border-layout-ct')][3]/descendant::iframe[1]"))));
		element1 = driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']"));
		scrollingFunction();
		driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[contains(@value,'Save')][1]")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(8);
	}
	public String fetchServiceItemNo() {
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		element1 = driver1.findElement(By.xpath(".//div[@class='feedUpdateContainer']/div[1]/descendant::div[@class='feedcommenttext']/following-sibling::div"));
		highlightElement();
		newSINo1 = driver1.findElement(By.xpath("//div[5]/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div[1]/a")).getText();
		System.out.println("Newly Generated Service Item Number is :"+newSINo1);
		driver1.switchTo().defaultContent();
		return newSINo1;
	}
	public void createNewResponse() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver1.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element1 = driver1.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Greetings')]"));
		scrollingFunction();
		element1.click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'HD Good Day')]")).click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"));
		//scrollingFunction();
		/*element1.click();
		element1.click();*/
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath(".//option[contains(text(),'HD Document Requests')]"))).doubleClick().build().perform();
		/*element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element1);*/
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'I-929 Sent to NVC')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]/preceding::option[1]"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'Humanitarian Division Closings')]"));
		Utils.sleep(2);
		actObj.moveToElement(element1).doubleClick().build().perform();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[text()='Available Templates']"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'HD Closing')]"));
		Utils.sleep(1);
		element1.click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element1.click();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		/*Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);*/
		wait.until(ExpectedConditions.alertIsPresent());
		driver1.switchTo().alert().accept();
		ele1 = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		driver1.switchTo().defaultContent();
	}
	public void verifyNewResponseStatus() {
		driver1.navigate().refresh();
		Utils.sleep(8);
		driver1.navigate().refresh();
		Utils.sleep(2);
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		try {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		
		//element1 = driver1.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Under Review')]"));
		try {
			element1 = driver1.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Under Review')]"));
			} catch (Exception e) {
			element1 = driver1.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[contains(text(),'Submitted')]"));
			}
		highlightElement();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
	}
	public void verifyTheChangeOwnerFunctionalityAsHDISOVSC() {
		WebDriverWait wait2 = new WebDriverWait (driver1, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element1 = driver1.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element1.click();
		Utils.sleep(1);
		ele1 = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver1.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver1.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_I918inquiriesNSC");
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele1 = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_I918inquiriesNSC')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_I918inquiriesNSC')]"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element1.click();
		Utils.sleep(1);
		ele1 = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		//dropdown.selectByValue("User");
		driver1.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("One HD Supervisor");
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele1 = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/span/a[contains(text(),'One HD Supervisor')]");
		fluentWaitForElementVisibility();
		driver1.switchTo().defaultContent();
	}
	public void verifyTheChangeOwnerFunctionalityAsHDSupervisor() {
		WebDriverWait wait2 = new WebDriverWait (driver1, 30);
		try {
			wait2.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[starts-with(@src,'https://uscis--uatg.my.salesforce.com')]"))));
			wait2.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
			driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='New Response']"));
			driver1.findElement(By.xpath("//a[@title='Details']/span[1]")).click();
		}
		element1 = driver1.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element1.click();
		Utils.sleep(1);
		ele1 = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		Select dropdown = new Select(driver1.findElement(By.xpath("//select[@title='Search scope']")));
		dropdown.selectByValue("case_queue");
		driver1.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("VAWA_ISOA");
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele1 = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_ISOA')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[contains(text(),'VAWA_ISOA')]"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/a[text()='[Change]']"));
		highlightElement();
		element1.click();
		Utils.sleep(1);
		ele1 = By.xpath("//select[@title='Search scope']");
		fluentWaitForElementVisibility();
		//dropdown.selectByValue("User");
		driver1.findElement(By.xpath(".//input[@id='newOwn']")).sendKeys("One HD Supervisor");
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='bottomButtonRow']/input[1]")).click();
		Utils.sleep(2);
		ele1 = By.xpath("//span[contains(text(),'Service Item Owner')]/parent::td/following-sibling::td[1]/div/span/a[contains(text(),'One HD Supervisor')]");
		fluentWaitForElementVisibility();
		driver1.switchTo().defaultContent();
	}
	public void currentUserLogOut() {
		driver1.findElement(By.xpath(".//*[@id='userNavLabel']")).click();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//a[text()='Logout']")).click();
		JavascriptExecutor executor = (JavascriptExecutor)driver1;
		executor.executeScript("arguments[0].click();", driver1.findElement(By.xpath("//a[text()='Logout']")));
		Utils.sleep(4);
	}
	//Call supervisor function to log in with supervisor
	public void selectRequiredDropdownlist() {
		//newSINo1 = "03679989";
		//clear all tab code
				driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
				Utils.sleep(4);
				//************
		//newSINo1 = "02734526";
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")).click();
		((JavascriptExecutor)driver1).executeScript("arguments[0].click();", 
				driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")));
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		Utils.sleep(4);
		ele1 = By.xpath("//a[text()='"+newSINo1+"']");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		scrollingFunction();
		driver1.findElement(By.xpath("//a[text()='"+newSINo1+"']")).click(); //// need to change
		System.out.println("Clicked on new service item :"+newSINo1);
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
	}
	public void approveServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		//driver1.switchTo().defaultContent();
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[2]"))));
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver1.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element1 = driver1.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver1.findElement(By.xpath("//input[@name='Decision__c']")).click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element1 = driver1.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver1.findElement(By.xpath("//input[@name='Decision__c']")).click();
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//*[@data-value='Approve']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//textarea[@name='Decision_Reason_Other__c']")).sendKeys("Auto Approved from System.");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele1 = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		highlightElement();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateApprovedResponse() {
		//newSINo1 = "03077164";
		driver1.navigate().refresh();
		Utils.sleep(8);
		ele1 = By.xpath(".//span[@class='tabText' and text()='"+newSINo1+"']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		driver1.navigate().refresh();
		Utils.sleep(4);
		ele1 = By.xpath(".//span[@class='tabText' and text()='"+newSINo1+"']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();
		driver1.switchTo().defaultContent();
	}
	public void rejectServiceRqst() {
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		//driver1.switchTo().defaultContent();
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[2]"))));
		driver1.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']"));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[4]/input[@type='checkbox']")).click();
		clickOnSaveSI();
		driver1.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		ele1 = By.xpath("//a[@title='Details']/span/span[1]");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//img[@title='Checked']"));
		highlightElement();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']"));
		highlightElement();
		driver1.findElement(By.xpath("//tr[2]/td[1]/a[@class='actionLink'][text()='Edit']")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(2);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[3]"))));
			element1 = driver1.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver1.findElement(By.xpath("//input[@id='input-10']")).click();
		} catch (Exception e) {
			driver1.switchTo().defaultContent();
			fetchCorrectIframe(By.xpath("//span[text()='Approve/Reject Response']"));
			element1 = driver1.findElement(By.xpath("//span[text()='Approve/Reject Response']"));
			scrollingFunction();
			highlightElement();
			Utils.sleep(2);
			//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
			driver1.findElement(By.xpath("//input[@id='input-10']")).click();
		}
		Utils.sleep(1);
		driver1.findElement(By.xpath("//*[@data-value='Reject']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//input[@id='input-11']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//*[@data-value='Plain Language']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//textarea[@id='input-3']")).sendKeys("Auto Reject from System.");
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@type='submit']")).click();
		Utils.sleep(1);
		ele1 = By.xpath("//*[contains(text(),'Response decision successfully saved.')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		highlightElement();
		driver1.switchTo().defaultContent();
		Utils.sleep(4);
	}
	public void validateRejectedResponse() {
		//newSINo1 = "02734526";
		driver1.navigate().refresh();
		Utils.sleep(4);
		ele1 = By.xpath(".//span[@class='tabText' and text()='"+newSINo1+"']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//tr[2]/td[text()='Edits Required']"));
		highlightElement();
		/*element1 = driver1.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();*/
		driver1.switchTo().defaultContent();
	}
	//Log out
	//Log in with HD ISC VSO
	public void searchWithStoredItem() throws AWTException { //String applicationRecord
		//String newSINo1 = "A200453283";
		//clear all tab code
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		//newSINo1 = "02734526";
		driver1.findElement(By.id("phSearchInput")).sendKeys(newSINo1);
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='phSearchInput_autoCompleteBoxId']/descendant::ul[@class='autoCompleteGroup']/li[1]/a")).click();
		Utils.sleep(4);
		/*Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_DOWN);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_DOWN);
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(4);*/
	}
	public void editRejectedSI() throws AWTException {
		//newSINo1 = "02734526";
		try {
			WebDriverWait wait = new WebDriverWait (driver1, 8);
			fetchCorrectIframe(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo1+"']"));
			driver1.findElement(By.xpath("//table[@class='list']/descendant::a[text()='"+newSINo1+"']")).click();
			driver1.switchTo().defaultContent();
			Utils.sleep(1);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']"));
		highlightElement();
		driver1.findElement(By.xpath("//td[text()='Edits Required']/parent::tr/td[1]/a[text()='Edit']")).click();
		driver1.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@aria-label='Compose text']/p[1]"));
		//driver1.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")).click();
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]"))).click().build().perform();
		Utils.sleep(2);
		//driver1.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")).sendKeys("Good day, /br /br Changes for edit /br");
		((JavascriptExecutor)driver1).executeScript("arguments[0].textContent = arguments[1];", driver1.findElement(By.xpath("//div[@aria-label='Compose text']/p[1]")), "Good day, /br /br Changes for edit /br");
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		ele1 = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
	}
	//Second Scenario Start
	public void setPercentage(String percentageValue) throws AWTException { //String applicationRecord
		//clear all tab code
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		//newSINo1 = "02734526";
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Quality Control Percentage']")).click();
		//actObj.moveToElement(driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Quality Control Percentage']"))).doubleClick().build().perform();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		ele1 = By.xpath(".//input[@title='QC Percentage for HD ISO VSC']");
		fluentWaitForElementVisibility();
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//table[@title='ISO Users']/tbody/tr[1]/td[1]/input[@type='checkbox']")).click();
		Utils.sleep(1);
		driver1.findElement(ele1).clear();
		driver1.findElement(ele1).sendKeys(percentageValue);
		Utils.sleep(1);
		//driver1.findElement(By.xpath(".//div[contains(@class,'pbBottomButtons')]/descendant::input[@value='Set QC Percentage']")).click();
		driver1.findElements(By.xpath(".//input[@value='Set QC Percentage']")).get(0).click();
		Utils.sleep(2);
		ele1 = By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(By.xpath(".//*[contains(text(),'Successfully updated QC Percentage')]"));
		highlightElement();
		driver1.switchTo().defaultContent();
	}
	public void deleteExistingSI () {
		//clear all tab code
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//************
		//newSINo1 = "02734526";
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		//selectDropdownListValue("My Open Service Items", driver1.findElement(By.xpath("//img[@title='Service Item']/following::select[@title='View:']")));
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
		scrollingFunction();
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
		//driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		try {
			driver1.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver1.findElement(By.xpath("//a[text()='Delete']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver1.switchTo().alert().accept();
			Utils.sleep(2);
			/*Robot robot = new Robot();
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(2);*/
		} catch (Exception e) {
			
		}
		driver1.switchTo().defaultContent();
	}
	public void fetchEmailLink () {
		driver1.findElement(By.id("setupLink")).click();
		ele1 = By.xpath(".//input[@id='setupSearch']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).sendKeys("email services");
		Utils.sleep(2);
		driver1.findElement(By.xpath(".//a[text()='Email Services']")).click();
		element1 = driver1.findElement(By.xpath("//a[contains(text(),'Email Service Name')]"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//table[@class='list']/tbody/tr[2]/td[2]/a[text()='EmailRelayRoutingHandler']"));
		highlightElement();
		element1.click();
		ele1 = By.xpath("//*[text()='Email Service: EmailRelayRoutingHandler']");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(By.xpath(".//*[text()='Email Addresses']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//table[@class='list']/tbody/tr[1]/td[3]/a"));
		highlightElement();
		emailLink1 = element1.getText();
		System.out.println("Fetch email link :"+emailLink1);
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
	      //System.out.printf("Now random "+randomDateTime);
	      subjectLine1 = "UATG Automation Test SI - " + randomDateTime;
	      return randomDateTime;
	}
	public void logIntoGmail (String url, String username, String passowrd) throws AWTException {
		Robot robot = new Robot();
		driver1.get(url);
		driver1.findElement(By.id("sign_in_username")).sendKeys(username);
		driver1.findElement(By.id("sign_in_password")).sendKeys(passowrd);
		driver1.findElement(By.xpath("//input[@value='Log In']")).click();
		try {
			WebDriverWait wait = new WebDriverWait (driver1, 5);
			ele1 = By.xpath("//div[text()='zabid@acumensolutions.com']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele1));
			driver1.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			
		}
		ele1 = By.xpath("//div[text()='Compose']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		ele1 = By.xpath("//textarea[@name='to']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		driver1.findElement(ele1).sendKeys(emailLink1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		randomDateTime();
		//driver1.findElement(By.xpath("//input[@name='subjectbox']")).click();
		driver1.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(subjectLine1);
		robot.keyPress(KeyEvent.VK_TAB);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_TAB);
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).click();
		driver1.findElement(By.xpath("//div[contains(@class,'Am Al editable')]")).sendKeys(subjectLine1);
		driver1.findElement(By.xpath("//div[text()='Send']")).click();
		Utils.sleep(8);
		driver1.close();
		driver1.quit();
	}
	public void hotLineDropDown () {
		//clear all tab code
				driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
				Utils.sleep(2);
				driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
				Utils.sleep(4);
				//************
				//newSINo1 = "02734526";
				WebDriverWait wait = new WebDriverWait (driver1, 14);
				ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
				fluentWaitForElementVisibility();
				driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
				Utils.sleep(2);
				Actions actObj = new Actions(driver1);
				actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
				Utils.sleep(2);
				driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")).click();
				Utils.sleep(4);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
				driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
				Utils.sleep(2);
				element1 = driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']/preceding::option[1]"));
				scrollingFunction();
				driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD Hotline Follow-up I-360']")).click();
				//driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
				Utils.sleep(2);
				driver1.findElement(By.xpath("//div[@title='Subject']")).click();
				Utils.sleep(1);
				element1 = driver1.findElement(By.xpath(".//span[text()='"+subjectLine1+"']"));
				highlightElement();
				element1 = driver1.findElement(By.xpath(".//span[text()='"+subjectLine1+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a"));
				highlightElement();
				newSINo1 = driver1.findElement(By.xpath(".//span[text()='"+subjectLine1+"']/parent::a/parent::div/parent::td/preceding-sibling::td[2]/div/a")).getText(); //// need to chang
				Utils.sleep(4);
				driver1.switchTo().defaultContent();
	}
	public void duplicateSIandOpenSI () throws AWTException {
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[@id='navigatortab']/div[2]/div[@class='x-tab-tabmenu-right']")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//a/span[text()='Close all primary tabs']")).click();
		Utils.sleep(4);
		//String dontSave = "Don't Save";
		//driver1.findElement(By.xpath("//button[text()='Don't Save']")).click();
		WebDriverWait wait = new WebDriverWait (driver1, 14);
		ele1 = By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*");
		fluentWaitForElementVisibility();
		driver1.findElement(By.xpath("//div[@id='navigatortab']/descendant::table/tbody/tr[2]/td[2]/*[@class='x-btn-split']/button")).click();
		Utils.sleep(2);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath("//*[@class='x-btn-split']"))).moveByOffset(124, 0).click().build().perform();
		Utils.sleep(2);
		driver1.findElement(By.xpath("//div[@id='navigator-sbmenu']/descendant::span[text()='Service Items']")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[not(starts-with(@src,'https://uscis--uatg.my.salesforce.com'))]"))));
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]")).click();
		Utils.sleep(2);
		try {
		element1 = driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']/preceding::option[1]"));
		scrollingFunction();
		} catch (Exception e) {
			
		}
		driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='My Open Service Items']")).click();
		//driver1.findElement(By.xpath("//select[contains(@id,'listSelect')]/option[text()='HD All Open Service Items']")).click();
		Utils.sleep(2);
		/*driver1.findElement(By.xpath("//div[@title='Subject']")).click();
		Utils.sleep(1);
		Robot robot = new Robot();
		try {
			driver1.findElement(By.xpath(".//input[@id='allBox']")).click();
			driver1.findElement(By.xpath(".//input[@value='Mark as Duplicate']")).click();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver1.switchTo().alert().accept();
			Utils.sleep(2);
			wait.until(ExpectedConditions.alertIsPresent());
			Utils.sleep(2);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(2);
		} catch (Exception e) {
			
		}
		driver1.findElement(By.xpath(".//input[@title='Refresh']")).click();
		Utils.sleep(3);
		driver1.findElement(By.xpath("//input[@value='Assign a Service Item']")).click();
		wait.until(ExpectedConditions.alertIsPresent());
		Utils.sleep(1);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);*/
		driver1.findElement(By.xpath("//table[@class='x-grid3-row-table']/tbody/tr[1]/td[4]/div/a")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
	}
	public void editSIandSave () {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//*[@id='topButtonRow']/input[3]")).click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
	}
	public void setReceiptNumberAndContactName (String receiptNo, String contactNm) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNo);
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/div[@class='requiredInput']/span/a/img")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver1.getWindowHandles().size());
		String winHandleBefore = driver1.getWindowHandle();
		for(String winHandle : driver1.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
		    driver1.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//frame[@id='resultsFrame']"))));
		driver1.findElement(By.xpath(".//*[@id='Contact_body']/table/tbody/tr[2]/th/a[text()='"+contactNm+"']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		driver1.switchTo().window(winHandleBefore);
		//driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/div[@class='requiredInput']/span/input")).sendKeys(contactNm);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
	}
	public void setReceiptNumberAndContactNameForIpo (String receiptNo, String contactNm) throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 8);
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[2]/span/input")).sendKeys(receiptNo);
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[1]/td[4]/descendant::img[@class='lookupIcon']")).click();
		Utils.sleep(2);
		System.out.println("Window Count :: "+driver1.getWindowHandles().size());
		String winHandleBefore = driver1.getWindowHandle();
		for(String winHandle : driver1.getWindowHandles()){
			System.out.println(winHandle);
			if (!winHandleBefore.equals(winHandle)) {
		    driver1.switchTo().window(winHandle);
			}
		}
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//frame[@id='searchFrame']"))));
		driver1.findElement(By.xpath("//input[@id='lksrch']")).sendKeys(contactNm);
		driver1.findElement(By.xpath("//input[@name='go']")).click();
		driver1.switchTo().defaultContent();
		Utils.sleep(2);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//frame[@id='resultsFrame']"))));
		driver1.findElement(By.xpath(".//*[@id='Contact_body']/table/tbody/tr[2]/th/a[text()='"+contactNm+"']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		driver1.switchTo().window(winHandleBefore);
		driver1.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//div[@class='pbSubsection'][1]/table[@class='detailList']/tbody/tr[3]/td[2]"));
	}
	public void issueAndAction(String issue, String action, String comments) {
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'Issue / Action')]"));
		scrollingFunction();
		selectDropdownListValue(issue, driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[2]/descendant::select")));
		selectDropdownListValue(action, driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[2]/td[2]/descendant::select")));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][3]/table[@class='detailList']/tbody/tr[1]/td[4]/textarea")).sendKeys(comments);
	}
	public void validateEditedItemDetailsSaved() {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[2]/td[2]"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//div[@class='pbSubsection'][2]/table[@class='detailList']/tbody/tr[1]/td[4]"));
		highlightElement();
		driver1.switchTo().defaultContent();
	}
	//Below function is in progress
	public void createEditedResponse() throws AWTException {
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		Utils.sleep(1);
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath("//div[@id='navigatortab']/div[3]/descendant::iframe[contains(@src,'RecordType')]"))));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver1.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element1 = driver1.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'01. Humanitarian Division Greetings')]"));
		scrollingFunction();
		element1.click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'HD Good Day')]")).click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]"));
		//scrollingFunction();
		/*element1.click();
		element1.click();*/
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]"))).doubleClick().build().perform();
		/*element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element1);*/
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'I-929 Sent to NVC')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. SAVE Program')]"));
		scrollingFunction();
		Utils.sleep(2);
		/*element1.click();
		element1.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element1);
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'03. Humanitarian Division Closings')]"));
		scrollingFunction();
		actObj.moveToElement(element1).doubleClick().build().perform();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'HD Closing')]"));
		scrollingFunction();
		Utils.sleep(1);
		element1.click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element1.click();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		ele1 = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		highlightElement();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		driver1.switchTo().defaultContent();
	}
	public void validateEmailRelatedNewResponse() {
		driver1.navigate().refresh();
		Utils.sleep(4);
		ele1 = By.xpath(".//span[@class='tabText' and text()='New Response']");
		fluentWaitForElementVisibility();
		driver1.findElement(ele1).click();
		Utils.sleep(2);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//img[@title='Response']/following::*[text()='Responses']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//tr[2]/td[text()='Sent']"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//*[text()='Emails']/parent::td[@class='pbTitle']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//th[text()='Sent']"));
		highlightElement();
		driver1.switchTo().defaultContent();
	}
	public void createNewEmailResponse() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver1.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element1 = driver1.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'01. Humanitarian Division Greetings')]"));
		scrollingFunction();
		element1.click();
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'HD Greeting')]")).click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Change of Address')]"));
		//scrollingFunction();
		/*element1.click();
		element1.click();*/
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Change of Address')]"))).doubleClick().build().perform();
		/*element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element1);*/
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'3156:Request to Change Address to Attorney')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'03. Humanitarian Division Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		/*element1.click();
		element1.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element1);
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'03. Humanitarian Division Closings')]"));
		scrollingFunction();
		actObj.moveToElement(element1).doubleClick().build().perform();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'HD ISOA Closing')]"));
		scrollingFunction();
		Utils.sleep(1);
		element1.click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element1.click();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);
		ele1 = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		driver1.switchTo().defaultContent();
	}
	public void createNewEmailResponseForIpo() throws AWTException {
		WebDriverWait wait = new WebDriverWait (driver1, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Feed']/span/span[1]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='New Response']")));
		driver1.findElement(By.xpath("//span[text()='New Response']")).click();
		Utils.sleep(2);
		//driver1.switchTo().defaultContent();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver1.findElement(By.xpath(".//iframe[@title='ResponseBuilder']"))));
		element1 = driver1.findElement(By.xpath(".//span[text()='Select Folder']"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'IPO Greetings')]"));
		scrollingFunction();
		element1.click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Greetings')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 2nd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"));
		//scrollingFunction();
		/*element1.click();
		element1.click();*/
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(driver1.findElement(By.xpath(".//option[contains(text(),'IPO I-829')]"))).doubleClick().build().perform();
		/*element1 = driver1.findElement(By.xpath(".//option[contains(text(),'02. HD Document Requests')]/parent::select"));
		selectDropdownListValue("02. HD Document Requests", element1);*/
		Utils.sleep(1);
		driver1.findElement(By.xpath(".//option[contains(text(),'ASC Appointment Language (due to C3 Conversion)')]")).click();
		Utils.sleep(1);
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		//Select 3rd one
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]/preceding::option[1]"));
		scrollingFunction();
		Utils.sleep(2);
		/*element1.click();
		element1.click();*/
		//selectDropdownListValue("03. Humanitarian Division Closings", element1);
		//((JavascriptExecutor)driver1).executeScript("arguments[0].click();", element1);
		element1 = driver1.findElement(By.xpath(".//option[contains(text(),'IPO Closings')]"));
		//scrollingFunction();
		actObj.moveToElement(element1).doubleClick().build().perform();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Available Templates')]"));
		scrollingFunction();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Available Templates')]/parent::div/following-sibling::div[1]/select/option[contains(text(),'IPO Closing')]"));
		Utils.sleep(1);
		element1.click();
		driver1.findElement(By.xpath("//button[@title='Select to move the template to the selected box. Information about the template will appear below.']")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element1.click();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		/*Robot robot = new Robot();
		Utils.sleep(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		Utils.sleep(1);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Utils.sleep(2);*/
		wait.until(ExpectedConditions.alertIsPresent());
		driver1.switchTo().alert().accept();
		ele1 = By.xpath("//div[contains(text(),'select one or more recipients before submitting')]");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		highlightElement();
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Submit')]"));
		scrollingFunction();
		driver1.findElement(By.xpath("//button[contains(text(),'Go Back')]")).click();
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Response Recipients')]"));
		scrollingFunction();
		element1 = driver1.findElement(By.xpath("//span[contains(text(),'Is selected?')]/parent::label"));
		element1.click();
		highlightElement();
		element1 = driver1.findElement(By.xpath("//button[contains(text(),'Generate Preview')]"));
		scrollingFunction();
		Utils.sleep(3);
		element1.click();
		Utils.sleep(4);
		driver1.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		Utils.sleep(4);
		//Robot robot = new Robot();
		//Utils.sleep(2);
		//robot.keyPress(KeyEvent.VK_ENTER);
		//Utils.sleep(1);
		//robot.keyRelease(KeyEvent.VK_ENTER);
		//Utils.sleep(2);
		wait.until(ExpectedConditions.alertIsPresent());
		driver1.switchTo().alert().accept();
		ele1 = By.xpath("//div[contains(text(),'Your response has been successfully submitted and is now locked.')]");
		fluentWaitForElementVisibility();
		Utils.sleep(4);
		driver1.switchTo().defaultContent();
		driver1.switchTo().defaultContent();
	}
	public void closureFunction() {
		driver1.navigate().refresh();
		Utils.sleep(4);
		//WebDriverWait wait = new WebDriverWait (driver1, 30);
		fetchCorrectIframe(By.xpath("//a[@title='Details']/span/span[1]"));
		driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]")).click();
		element1 = driver1.findElement(By.xpath("//img[@name='Service Item Details']/following::*[text()='Service Item Details']"));
		scrollingFunction();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]"));
		Actions actObj = new Actions(driver1);
		actObj.moveToElement(element1).doubleClick().build().perform();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]/div[2]/span/select"));
		selectDropdownListValue("Closed", element1);
		Utils.sleep(1);
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[1]")).click();
		Utils.sleep(1);
		element1 = driver1.findElement(By.xpath("//div[@class='pbSubsection'][5]/table/tbody/tr[1]/td[4]"));
		highlightElement();
		element1 = driver1.findElement(By.xpath("//a[@title='Details']/span/span[1]"));
		scrollingFunction();
		driver1.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Save')]")).click();
		Utils.sleep(4);
		ele1 = By.xpath(".//*[@id='errorDiv_ep']");
		fluentWaitForElementVisibility();
		element1 = driver1.findElement(ele1);
		highlightElement();
		driver1.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Cancel')]")).click();
		Utils.sleep(2);
		element1 = driver1.findElement(By.xpath(".//td[@id='topButtonRow']/input[contains(@value,'Edit')]"));
		scrollingFunction();
		element1.click();
		Utils.sleep(2);
		driver1.switchTo().defaultContent();
		fetchCorrectIframe(By.xpath("//*[contains(text(),'Service Item Details')]"));
		element1 = driver1.findElement(By.xpath("//*[contains(text(),'Service Item Details')]"));
		scrollingFunction();
		selectDropdownListValue("Closed", driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[1]/td[4]/descendant::select")));
		driver1.findElement(By.xpath("//div[@class='pbSubsection'][4]/table[@class='detailList']/tbody/tr[3]/td[4]/textarea")).sendKeys("Closing the Service Item.");
	}
	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--user-data-dir=C:\\Users\\Zaim3\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Default");
		//options.addArguments("--start-maximized");
		WebDriver driver1 = new ChromeDriver();
		driver1.manage().window().maximize(); // maximizes
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		new SalesforceLogin(driver1).login(Constants.salesforce_url_uatg, Constants.salesforce_username_uatg, Constants.salesforce_password_uatg);
		RefreshExistingUserRecordAction ref = new RefreshExistingUserRecordAction(driver1);
		/*ref.verifyProfile();
		//ref.deleteExistingSI();
		ref.fetchEmailLink();
		ref.logIntoGmail("https://acumensolutions-com.clearlogin.com/login", "zabid", "Mohammad1988");
		ref.launch();
		ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.setPercentage("5");
		ref.hotLineDropDown();
		ref.currentUserLogOut();*/
		/*ref.searchHDISOVSCitems("IPO CSR");
		ref.logInAsInternalUser("IPO CSR");
		ref.duplicateSIandOpenSI();
		ref.editSIandSave();*/
		//ref.setReceiptNumberAndContactName("WAC1690258857", "EDVARD EDOUARD");
		//ref.setReceiptNumberAndContactNameForIpo("WAC1690258857", "IPO Test 1");
		/*ref.searchHDISOVSCitems("HD ISO VSC");
		ref.logInAsInternalUser("HD ISO VSC");
		ref.customerSearch("A200453283");
		ref.customerRefresh();
		ref.clickOnRecordItemFromOwnerPage("WAC1690258857");
		//ref.searchForApplicationRecordWithAppNo("A200453283");
		//ref.openApplicationRecordFromGlobalSearchResultPanel("WAC1690258857");
		ref.createNewServiceItemParam("WAC1690258857");*/
		//ref.setOrgName("Test Organi");
		//ref.setEmail("zabid@acumensolutions.com");
		//ref.selectSenderType("ASC");
		//ref.setformNumberAndFormType("I924", "I-924 Application for Regional Center Under the Immigrant Investor Pilot Program");
		//ref.setRandomSubjectAndDesAndFormType();
		//ref.setParamCategoryAndKind("AWA Case", "AWA Concerns Identified", "New Comments");
		//ref.issueAndAction("Change of Address", "Status Provided", "Auto Generated Comments");
		//ref.selectSIOrigin("Email");
		//ref.selectInitialQueue("VAWA_HotlineFollowupI360");
		//ref.setReceivedDate("10/4/2018 9:43 AM");
		//ref.setResponseComments("New Responses");
		//ref.clickOnSaveSI();
		//ref.createNewEmailResponseForIpo();
		//ref.validateEmailRelatedNewResponse();
		//
		/*ref.fetchServiceItemNo();
		ref.createNewResponse();
		ref.verifyNewResponseStatus();
		ref.currentUserLogOut();
		
		ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.selectRequiredDropdownlist();
		ref.rejectServiceRqst();
		ref.validateRejectedResponse();
		ref.currentUserLogOut();*/
		ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		/*ref.searchWithStoredItem();
		ref.editRejectedSI();
		ref.currentUserLogOut();*/
		/*ref.searchHDISOVSCitems("One HD Supervisor");
		ref.logInAsInternalUser("One HD Supervisor");
		ref.setPercentage("5");
		ref.currentUserLogOut();*/
		//ref.setPercentage("100");
		newSINo1 = "06764572";
		ref.searchWithStoredItem();
		//ref.selectRequiredDropdownlist();
		//ref.closureFunction();
		//ref.clickOnSaveSI();
		ref.verifyTheChangeOwnerFunctionalityAsHDSupervisor();
		/*ref.approveServiceRqst();
		ref.validateApprovedResponse();*/
		//driver1.close();
		//driver1.quit();
	}
}
