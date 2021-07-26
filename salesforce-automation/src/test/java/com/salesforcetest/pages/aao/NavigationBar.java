package com.salesforcetest.pages.aao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class NavigationBar {
	
	private  WebDriver drvr;
	//private AaoHelper helper;
	@FindBy(linkText="Applications")
	private WebElement tabApplications;
	@FindBy(linkText="Service Items")
	private WebElement tabServiceItems;
	@FindBy(linkText="Quality Control Percentage")
	private WebElement tabQualityPercentage;
	@FindBy(linkText="Accounts")
	private WebElement tabAccounts;
	
	
	 public NavigationBar(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
			
			
		}
	 
	
	 
	
	 
	 public   void clickApplications()
	 {
		 AaoHelper.FindAaoElement(drvr,tabApplications).click();
	 }
	 
	 public  void clickServiceItems()
	 {
		AaoHelper.FindAaoElement(drvr,tabServiceItems).click();
	 }
	 public void clickAccounts() {
		 AaoHelper.FindAaoElement(drvr,tabAccounts).click();
	 }
	 
	 public void clickQC() {
		 AaoHelper.FindAaoElement(drvr, tabQualityPercentage).click();
	 }

}
