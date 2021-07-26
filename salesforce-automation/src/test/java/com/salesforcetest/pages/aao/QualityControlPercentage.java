package com.salesforcetest.pages.aao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class QualityControlPercentage {
	
	private WebDriver drvr;
	private NavigationBar navbar;
	
	@FindBy(xpath="//table/tbody/tr[1]/td[1]/input[@type='checkbox']")
	private WebElement chkbxOfficer;
	@FindBy(xpath="//table/tbody/tr[1]/td[3]/input")
	private WebElement txtQcPercentageOfficer;
	@FindBy(xpath="//table/tbody/tr/td[2]/input")
	private WebElement btnSetPercentage;
	@FindBy(xpath=".//*[contains(text(),'Successfully updated QC Percentage for ISOs')]")
	private WebElement lblMessage;

	
	
	 public QualityControlPercentage(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
			
		}
	 
		public NavigationBar getBar()
		{
			navbar=new NavigationBar(drvr);
			return navbar;
		}
	 
	 public void setPercentageOfficer(String text) {
	
		 getBar().clickQC();
		 AaoHelper.FindAaoElement(drvr, chkbxOfficer).click();
		 AaoHelper.AaohighlightElement(drvr, chkbxOfficer);
		 AaoHelper.sleep(2);
		 AaoHelper.FindAaoElement(drvr, txtQcPercentageOfficer).clear();
		 AaoHelper.FindAaoElement(drvr, txtQcPercentageOfficer).sendKeys(text);
		 AaoHelper.AaohighlightElement(drvr, txtQcPercentageOfficer);
		 AaoHelper.FindAaoElement(drvr, btnSetPercentage).click();
		// AaoHelper.AaohighlightElement(drvr, lblMessage);
		 
	 }

}
