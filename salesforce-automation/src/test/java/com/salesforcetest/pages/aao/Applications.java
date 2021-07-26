package com.salesforcetest.pages.aao;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.pages.aao.manager.FileManager;

import com.salesforcetest.pages.aao.utils.AaoHelper;
//import com.sun.tools.javac.util.List;

public class Applications {
	
	private WebDriver drvr;
	//private AaoAaoHelper AaoHelper;
	private NavigationBar navbar;

	
	@FindBy(name="new")
	private WebElement btnNew;
	@FindBy(xpath="//img[@class='lookupIcon']")
	private WebElement imgLookUp;
	@FindBy(name="save")
	private WebElement btnSave;
	@FindBy(id="Name")
	private WebElement txtReceiptNumber;
	@FindBy(id="CF00Nt0000000ZnfK")
	private WebElement txtUnderlyingBenefit;
	@FindBy(id="00Nt0000000ZnfE")
	private WebElement txtFileNumber;
	@FindBy(id="00Nt0000000ZnfD")
	private WebElement lstFileType;
	@FindBy(id="00Nt0000000WAh3")
	private WebElement txtCisRecdDate;
	@FindBy(xpath="//span[@class='dateFormat']/a")
	private WebElement lnkDateLink;
	@FindBy(id="lksrch")
	private WebElement txtLookUpSearch;
	@FindBy(name="go")
	private WebElement btnGo;
	@FindBy(linkText="I-130")
	private WebElement lnkBenefit;
	
	 public Applications(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			//AaoHelper=new AaoAaoHelper();
			//navbar=new NavigationBar(driver);
			
		}
	 
	public NavigationBar getBar()
	{
		navbar=new NavigationBar(drvr);
		return navbar;
	}
	
	public WebElement getLookUp()
	{
		return imgLookUp;
	}
	
	public WebElement getLookUpSearch() {
		return txtLookUpSearch;
		
	}
	
	public WebElement getGo() {
		return btnGo;
	}
	
	public WebElement getBenefitLink() {
		return lnkBenefit;
	}
	 
	 public void createApplication(String fileType) {
		 
		 getBar().clickApplications();
		 clickNew();
		 fillApplication(fileType);
		 saveApplication();
	 }
	 
	 public void clickNew()
	 {
		 AaoHelper.AaohighlightElement(drvr, btnNew);
		 AaoHelper.FindAaoElement(drvr,btnNew).click();
	 }
	 
	 public void lookUpBenefit() {
		 AaoHelper.FindAaoElement(drvr,imgLookUp).click();
		 AaoHelper.sleep(10);
		String parentHandler= AaoHelper.switch_to_popup(drvr);
		//new WebDriverWait(drvr,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		drvr.switchTo().frame(0);
		 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	executor.executeScript("arguments[0].value='I-130';",txtLookUpSearch);
	//new WebDriverWait(drvr,10).until(ExpectedConditions.elementToBeClickable(btnGo)).click();
	AaoHelper.FindAaoElement(drvr,btnGo).click();
	drvr.switchTo().defaultContent();
		 drvr.switchTo().frame(1);
		 AaoHelper.waitForAaoElement(drvr,lnkBenefit);
		 AaoHelper.sleep(6);
		 lnkBenefit.click();
		 drvr.switchTo().window(parentHandler);
		 
	 }
	 
    public void fillApplication(String fileType)
    {
    	String receiptNumber=AaoHelper.getRandomReceiptNumber();
    	
    	AaoHelper.FindAaoElement(drvr,txtReceiptNumber).sendKeys(receiptNumber);
    	lookUpBenefit();
    	String fileNumber=AaoHelper.getRandomFileNumber();
    	
    	AaoHelper.FindAaoElement(drvr,txtFileNumber).sendKeys(fileNumber);
    	AaoHelper.waitForAaoElement(drvr, lstFileType);
		Select select=new Select(lstFileType);
		select.selectByValue(fileType);
		String recdDate=lnkDateLink.getText();
		AaoHelper.FindAaoElement(drvr,txtCisRecdDate).clear();
		txtCisRecdDate.sendKeys(recdDate);
    }
    
    public void saveApplication()
    {
    	AaoHelper.FindAaoElement(drvr,btnSave).click();
    }
}

