package com.salesforcetest.pages.aao;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ResponseBuilder {

	private WebDriver drvr;
	
	private Select select;
	
	@FindBy(xpath="//div[3]/a[7]/span")
	private WebElement lnkResponses;
	@FindBy(xpath="//input[@name='new_response']")
	private WebElement btnNew;
	
	@FindBy(xpath="//div[5]/div/div[3]/button[1]")
	private WebElement btnForward;
	
	@FindBy(xpath="//div[8]/form/div[3]/button")
	private WebElement btnGeneratePreview;
	@FindBy(xpath="//div[6]/div/form/div[2]/div/button")
	private WebElement btnSubmit;
	@FindBy(linkText="View Response")
	private WebElement lnkViewResponse;
	@FindBy(xpath="//div[1]/div[2]/select")
	private WebElement lstFolderOption;
	@FindBy(xpath="//div[2]/div[2]/select")
	private WebElement lstTemplateOption;
	@FindBy(xpath="//select[@class='slds-select']")
	private WebElement lstResponseType;
	@FindBy(xpath="//input[@name='Decision__c']")
	private WebElement lstDecision;
	@FindBy(xpath="//input[@name='Decision_Reason__c']")
	private WebElement lstDecisionReason;
	@FindBy(xpath="//textarea[@name='Decision_Reason_Other__c']")
	private WebElement txtSupervisorComments;
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	private WebElement btnSubmitEditResponse;
	@FindBy(xpath="//article[@class='slds-card']/div/div")
	private WebElement lblMessage;
	@FindBy(xpath="//button[@title='Save']")
	private WebElement btnSave;
	
	
	 public ResponseBuilder(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
		}
	 
	
	 
	 public void selectFolder()
	 {
		 AaoHelper.waitForAaoElement(drvr, lstFolderOption);
		try {
			AaoHelper.scrollIntoView(drvr, lstFolderOption);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Select select= new Select(lstFolderOption);
		select.selectByVisibleText("AAO Bonds");
	 }
	 
	 public void selectTemplate()
	 {
     AaoHelper.waitForAaoElement(drvr, lstTemplateOption);
		 try {
				AaoHelper.scrollIntoView(drvr, lstTemplateOption);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 Select select= new Select(lstTemplateOption);
			select.selectByVisibleText("02 Bond Decision - 1 Law - Notice");
	 }
	 
	 public void clickForwardButton()
	 {
       
		WebElement ele= AaoHelper.FindAaoElement(drvr, btnForward);
		ele.click();
	
	 }
	 
	 public void clickGeneratePreviewButton()
	 {
		 AaoHelper.waitForAaoElement(drvr, btnGeneratePreview);
		 try {
			AaoHelper.scrollIntoView(drvr, btnGeneratePreview);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 AaoHelper.waitForAaoElement(drvr, btnGeneratePreview);
		 AaoHelper.FindAaoElement(drvr,btnGeneratePreview).click();
	 }
	 
	 public void clickSubmitButton()
	 {
		 AaoHelper.waitForAaoElement(drvr, btnSubmit);
		 AaoHelper.FindAaoElement(drvr,btnSubmit).click();
	 }
	 
	 public void acceptAlert()
	 {
		AaoHelper.waitForAlert(drvr);
		drvr.switchTo().alert().accept();
	 }
	 
	 public void clickViewResponse()
	 {
		 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
		 AaoHelper.FindAaoElement(drvr,lnkViewResponse).click();
	 }
	 
	 public void createResponse() {
		 AaoHelper.waitForAaoElement(drvr, lnkResponses);
		 AaoHelper.FindAaoElement(drvr, lnkResponses).click();
		 AaoHelper.sleep(3);
		 AaoHelper.FindAaoElement(drvr, btnNew).click();
		 AaoHelper.sleep(3);
		 selectFolder();
		 AaoHelper.sleep(2);
		 selectTemplate();
		 AaoHelper.sleep(2);
		 clickForwardButton();
		 AaoHelper.sleep(3);
		 clickGeneratePreviewButton();
		 clickSubmitButton();
		 acceptAlert();
		 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
		 AaoHelper.sleep(5);
		 String windowHandle = drvr.getWindowHandle();
		 clickViewResponse();
		 ArrayList<String> tabs = new ArrayList<String> (drvr.getWindowHandles());
		 drvr.close();
		 drvr.switchTo().window((tabs.get(1)));
		 
	 }
	 
	 public void createResponse(String text) {
		 AaoHelper.waitForAaoElement(drvr, lnkResponses);
		 AaoHelper.FindAaoElement(drvr, lnkResponses).click();
		 AaoHelper.sleep(3);
		 AaoHelper.FindAaoElement(drvr, btnNew).click();
		 AaoHelper.sleep(3);
		 Select sel=new Select(lstResponseType);
		 sel.selectByVisibleText("Internal");
		 AaoHelper.sleep(3);
		 selectFolder();
		 AaoHelper.sleep(3);
		 selectTemplate();
		 AaoHelper.sleep(2);
		 clickForwardButton();
		 AaoHelper.sleep(3);
		 clickGeneratePreviewButton();
		 clickSubmitButton();
		 acceptAlert();
		 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
		 String windowHandle = drvr.getWindowHandle();
		 AaoHelper.sleep(5);
		 clickViewResponse();
		 ArrayList<String> tabs = new ArrayList<String> (drvr.getWindowHandles());
		 drvr.switchTo().window((tabs.get(1)));
		 drvr.switchTo().window(windowHandle);
		 drvr.close();
		 ArrayList<String> tabs1 = new ArrayList<String> (drvr.getWindowHandles());
		 drvr.switchTo().window((tabs1.get(0)));
		 
	}
	 
	 public void rejectResponse()
	 {
		 AaoHelper.waitForAaoElement(drvr, lstDecision);
		 try {
			AaoHelper.scrollIntoView(drvr, lstDecision);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 AaoHelper.FindAaoElement(drvr, lstDecision).click();
		 AaoHelper.sleep(3);
		 drvr.findElement(By.xpath("//*[@data-value='Reject']")).click();
		 AaoHelper.FindAaoElement(drvr, lstDecisionReason).click();
		 AaoHelper.sleep(3);
		 drvr.findElement(By.xpath("//*[@data-value='Plain Language']")).click();
		 AaoHelper.sleep(3);
		 AaoHelper.FindAaoElement(drvr, txtSupervisorComments).sendKeys("Auto Reject from System.");
		 AaoHelper.sleep(2);
		 AaoHelper.FindAaoElement(drvr, btnSave).click();
		 AaoHelper.waitForAaoElement(drvr, lblMessage);
		 AaoHelper.AaohighlightElement(drvr, lblMessage);
		 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
		 try {
			AaoHelper.scrollIntoView(drvr, lnkViewResponse);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String windowHandle = drvr.getWindowHandle();
		 AaoHelper.sleep(6);
		 clickViewResponse();
		 ArrayList<String> tabs = new ArrayList<String> (drvr.getWindowHandles());
		 //drvr.close();
		 drvr.switchTo().window((tabs.get(1)));
		 drvr.switchTo().window(windowHandle);
		 drvr.close();
		 ArrayList<String> tabs1 = new ArrayList<String> (drvr.getWindowHandles());
		 drvr.switchTo().window((tabs1.get(0)));
		 
	 
	 }
	 
	 public void resubmitResponse()
	 {
		 
		
	 AaoHelper.waitForAaoElement(drvr, btnSubmitEditResponse);
	 AaoHelper.FindAaoElement(drvr, btnSubmitEditResponse).click();
	 acceptAlert();
	 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
	AaoHelper.sleep(5);
	 String windowHandle = drvr.getWindowHandle();
	 AaoHelper.sleep(3);
	 clickViewResponse();
	 ArrayList<String> tabs = new ArrayList<String> (drvr.getWindowHandles());
	// drvr.close();
	 drvr.switchTo().window((tabs.get(1)));
	 drvr.switchTo().window(windowHandle);
	 drvr.close();
	 ArrayList<String> tabs1 = new ArrayList<String> (drvr.getWindowHandles());
	 drvr.switchTo().window((tabs1.get(0)));
	 
}
	 
	 public void approveResponse() {
		 AaoHelper.waitForAaoElement(drvr, lstDecision);
		 try {
			AaoHelper.scrollIntoView(drvr, lstDecision);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 AaoHelper.FindAaoElement(drvr, lstDecision).click();
		 AaoHelper.sleep(3);
		 drvr.findElement(By.xpath("//*[@data-value='Approve']")).click();
		 AaoHelper.sleep(3);
		 
		 AaoHelper.FindAaoElement(drvr, btnSave).click();
		 AaoHelper.waitForAaoElement(drvr, lblMessage);
		 AaoHelper.AaohighlightElement(drvr, lblMessage);
		AaoHelper.sleep(5);
		 AaoHelper.waitForAaoElement(drvr, lnkViewResponse);
		 try {
			AaoHelper.scrollIntoView(drvr, lnkViewResponse);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String windowHandle = drvr.getWindowHandle();
		 AaoHelper.sleep(3);
		 clickViewResponse();
		 ArrayList<String> tabs = new ArrayList<String> (drvr.getWindowHandles());
		
		 drvr.switchTo().window((tabs.get(1)));
		 
		 drvr.switchTo().window(windowHandle);
		 drvr.close();
		 ArrayList<String> tabs1 = new ArrayList<String> (drvr.getWindowHandles());
		 drvr.switchTo().window((tabs1.get(0)));
	 }
}
