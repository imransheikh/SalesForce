package com.salesforcetest.pages.aao;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ApplicationDetail {
	
	private WebDriver drvr;
	
	
	
	@FindBy(xpath=".//input[@value='Create Service Item']")
	private WebElement btnCreateServiceItem;
	@FindBy(xpath="//div[@id='Name_ileinner']")
	private WebElement lblReceiptNumber;
	@FindBy(xpath="//div[4]//tr[6]/td[2]/div")
	private WebElement lblFileNumber;
	@FindBy(xpath="//div[2]/table/tbody/tr[4]/td[2]/div[1]")
	private WebElement lblUnderlyingBenefit;
	@FindBy(xpath="//div[2]/table/tbody/tr[5]/td[4]/div[1]")
	private WebElement lblCisRecdDate;
	@FindBy(xpath="//div[1]/table/tbody/tr/td[2]/input[1]")
	private WebElement btnSave;
	@FindBy(xpath="//tr[4]/td[2]/div[2]/span/a/img")
	private WebElement imgLookUp;
	@FindBy(id="lksrch")
	private WebElement txtLookUpSearch;
	@FindBy(xpath="//input[@name='go']")
	private WebElement btnGo;
	@FindBy(linkText="I-102")
	public WebElement lnkBenefit;
	@FindBy(xpath="//div[2]/table/tbody/tr[5]/td[4]/div[2]/input")
	public WebElement txtCis;
	@FindBy(xpath="//h2[@class='mainTitle']")
	public WebElement lblApplicationDetail;
	
	
	 public ApplicationDetail(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
			
		}
	 
	
	 public String getReceiptNumber()
	 {
	return	 lblReceiptNumber.getText();
	
	 }
	 
	 public String getFileNumber() {
		 return lblFileNumber.getText();
	 }
	 
	 public String getCis() {
		 return lblCisRecdDate.getText();
	 }
	 public void clickCreateServiceItem()
	 {
		 AaoHelper.waitForAaoElement(drvr, lblApplicationDetail);
		 AaoHelper.waitForAaoElement(drvr, btnCreateServiceItem);
		WebElement ele= AaoHelper.FindAaoElement(drvr,btnCreateServiceItem);
		AaoHelper.AaohighlightElement(drvr, btnCreateServiceItem);
		AaoHelper.sleep(3);
		 JavascriptExecutor executor = (JavascriptExecutor)drvr;
		 executor.executeScript("arguments[0].click();", ele);
	 }
	 
	 public void fillUnderlyingBenefit() {
		 AaoHelper.FindAaoElement(drvr, lblUnderlyingBenefit);
		 AaoHelper.AaohighlightElement(drvr, lblUnderlyingBenefit);
		 Actions actions = new Actions(drvr);
		 actions.doubleClick(lblUnderlyingBenefit).perform();
		 lookUpBenefit();
		 
		 
	 }
	 
	 public void lookUpBenefit() {
		 AaoHelper.FindAaoElement(drvr,imgLookUp).click();
		 AaoHelper.sleep(10);
		String parentHandler= AaoHelper.switch_to_popup(drvr);
		//new WebDriverWait(drvr,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		drvr.switchTo().frame(0);
		 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	executor.executeScript("arguments[0].value='I-102';",txtLookUpSearch);
	//new WebDriverWait(drvr,10).until(ExpectedConditions.elementToBeClickable(btnGo)).click();
	AaoHelper.FindAaoElement(drvr,btnGo).click();
	drvr.switchTo().defaultContent();
		 drvr.switchTo().frame(1);
		 AaoHelper.waitForAaoElement(drvr,lnkBenefit);
		 AaoHelper.sleep(6);
		 lnkBenefit.click();
		 drvr.switchTo().window(parentHandler);
		 
	 }
	 public void fillCisDate() {
		 AaoHelper.FindAaoElement(drvr, lblCisRecdDate);
		 Actions actions = new Actions(drvr);
		 actions.doubleClick(lblCisRecdDate).perform();
		 String d=AaoHelper.getTodaysDate();
		 AaoHelper.waitForAaoElement(drvr, txtCis);
		 txtCis.clear();
		 txtCis.sendKeys(d);
	 }
	 
	 public void clickSave() {
		 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 }
	 
	 public void fillAppInfo() {
		 AaoHelper.waitForAaoElement(drvr, btnCreateServiceItem);
		 fillUnderlyingBenefit();
		 fillCisDate();
		 clickSave();
	 }

}
