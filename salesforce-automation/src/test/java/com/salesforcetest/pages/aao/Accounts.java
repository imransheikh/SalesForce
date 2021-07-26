package com.salesforcetest.pages.aao;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class Accounts {
	private WebDriver drvr;
	private NavigationBar bar;
	
	@FindBy(id="p3")
	private WebElement lstRecordType;
	@FindBy(xpath="//input[@value='New']")
	private WebElement btnNewRecordType;
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement btnContinue;
	@FindBy(id="acc2")
	private WebElement txtAccountName;
	@FindBy(xpath="//div[1]/table/tbody/tr/td[2]/input[1]")
	private WebElement btnSave;
	@FindBy(xpath="//button[@class='slds-button slds-button_neutral margin_bottom']")
	private WebElement btnNewApplication;
	@FindBy(xpath="//div[1]/div/table/tbody/tr[1]/td[2]/div/input")
	private WebElement txtReceiptNo;
	@FindBy(xpath="//div/div[1]/div/table/tbody/tr[2]/td[2]/div/input")
	private WebElement txtFileNo;
	@FindBy(xpath="//input[@value='Submit']")
	private WebElement btnSubmit;
	@FindBy(xpath="//input[@name='new']")
	private WebElement btnNewAccount;
	@FindBy(xpath="//div[2]/table/tbody/tr/td[2]/input[@value='Finish']")
	private WebElement btnFinish;
	String parentWindow;
	
	
	public Accounts(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.drvr=driver;
		bar=new NavigationBar(drvr);
	}
	
	
	
	public void clickNewRecordType() {
		AaoHelper.waitForAaoElement(drvr,btnNewRecordType);
		AaoHelper.FindAaoElement(drvr,btnNewRecordType).click();
	}
	
	public void selectRecordType(String text) {
		AaoHelper.waitForAaoElement(drvr,lstRecordType);
		Select sel=new Select(lstRecordType);
		sel.selectByVisibleText(text);
	}
	
	public void clickNewApplication() {
		
		AaoHelper.sleep(3);
		drvr.switchTo().frame("066t0000000CgDB");
		parentWindow=drvr.getWindowHandle();
		
		btnNewApplication.click();
		AaoHelper.switch_to_tab(drvr, 1);
		
		
		
		
		 
	}
	
	public void clickContinue() {
		AaoHelper.FindAaoElement(drvr,btnContinue).click();
		AaoHelper.sleep(2);
	}
	
	public void completeApplication() {
		String receiptNumber=AaoHelper.getRandomReceiptNumber();
    	AaoHelper.FindAaoElement(drvr,txtReceiptNo).sendKeys(receiptNumber);
    	String fileNumber=AaoHelper.getRandomFileNumber();
    	AaoHelper.FindAaoElement(drvr,txtFileNo).sendKeys(receiptNumber);
    	AaoHelper.FindAaoElement(drvr,btnSubmit).click();
    	AaoHelper.sleep(3);
	}
	
	public void inputAcctAndSave(String name) {
		AaoHelper.FindAaoElement(drvr,txtAccountName).sendKeys(name);
		AaoHelper.sleep(2);
		AaoHelper.FindAaoElement(drvr, btnSave).click();
		AaoHelper.sleep(3);
	}
	
	public void clickFinish() {
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, btnFinish).click();
		drvr.switchTo().window(parentWindow);
		drvr.close();
		Set<String> handles = drvr.getWindowHandles();
		for (String handle : handles) {
		    drvr.switchTo().window(handle);
		}
	
	}
	
	public void createAccountAndCreateApplication(String text1,String name) {
		AaoHelper.sleep(4);
		bar.clickAccounts();
		AaoHelper.waitForAaoElement(drvr,btnNewAccount);
		AaoHelper.FindAaoElement(drvr,btnNewAccount).click();
		selectRecordType(text1);
		clickContinue();
		
		inputAcctAndSave(name);
		
		clickNewApplication();
		
		completeApplication();
		
		clickFinish();
		
		
		
		
}
}
