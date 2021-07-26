package com.salesforcetest.pages.aao;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class Hold {
	
	private WebDriver drvr;
	@FindBy(xpath="//div[2]//td[2]/div[3]/a[1]/span")
	private WebElement lnkHolds;
	@FindBy(xpath="//input[@value='New Hold']")
	private WebElement btnNewHold;
	@FindBy(xpath="//div[2]//tr[1]/td[2]/div/span/select")
	private WebElement lstHold;
	@FindBy(xpath="//div[1]/table/tbody/tr/td[2]/input[1]")
	private WebElement btnSave;
	@FindBy(xpath="//div[1]//td[2]/input[3]")
	private WebElement btnCancel;
	@FindBy(xpath="//div[4]//tr[2]/td[2]/div/a")
	private WebElement lnkServiceItem;
	@FindBy(xpath="//div[3]/table/tbody/tr[4]/td[2]/span/input")
	private WebElement txtCallupDate;
	@FindBy(xpath="//div[2]//tr[4]/td[2]/span/span/a")
	private WebElement lnkCallDate;
	@FindBy(xpath="//div[5]//div[2]/table/tbody")
	private WebElement tblHold;
	@FindBy(xpath="//div[2]/table/tbody/tr[2]/td[1]/input")
	private WebElement chkbxHold;
	@FindBy(xpath="//div[2]/table/tbody/tr[3]/td[1]/input")
	private WebElement chkbxHold1;
	@FindBy(xpath="//input[@value='Remove Hold']")
	private WebElement btnRemoveHold;
	@FindBy(xpath="//div[6]//tr[2]/td[2]/div[1]")
	private WebElement lblStatus;
	@FindBy(xpath="//div[8]//tr[1]/td[2]/div")
	private WebElement lblResponsibleParty;
	@FindBy(xpath="//div[8]//tr[1]/td[2]/div[2]/span/select")
	private WebElement lstRespParty;
	@FindBy(xpath="//input[@name='edit']")
	private WebElement btnEdit;
	@FindBy(xpath="//td[2]//div[2]/div[1]")
	private WebElement lblHoldError;
	@FindBy(xpath="//div[4]//tr[4]/td[2]/div")
	private WebElement lblCallUpDate;
	@FindBy(xpath="//div[4]//tr[4]/td[2]/div[2]/input")
	private WebElement txtCallUpDate;
	@FindBy(xpath="//div[@id='errorDiv_ep']")
	private WebElement lblCallUpError;
	
	
	
	public Hold(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.drvr=driver;
	}
	
	public void GoToHolds() {
		try {
			 AaoHelper.scrollIntoView(drvr,lnkHolds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AaoHelper.waitForAaoElement(drvr, lnkHolds);
		AaoHelper.FindAaoElement(drvr, lnkHolds).click();
		AaoHelper.AaohighlightElement(drvr, btnNewHold);
		AaoHelper.FindAaoElement(drvr,btnNewHold).click();
		
	}
	
	public void createHold(String hld) {
		GoToHolds();
		AaoHelper.waitForAaoElement(drvr,lstHold);
		AaoHelper.AaohighlightElement(drvr, lstHold);
		Select sel=new Select(lstHold);
		sel.selectByVisibleText(hld);
	//	inputCallUpDate();
		AaoHelper.FindAaoElement(drvr,btnSave).click();
	}
	
	public boolean verifyCallUpDate() {
		 AaoHelper.FindAaoElement(drvr,lblCallUpDate);
		AaoHelper.AaohighlightElement(drvr, lblCallUpDate);
		 String Date=lblCallUpDate.getText();
		 String s=AaoHelper.selectDate();
		
		 Actions actions = new Actions(drvr);
		 
		 actions.doubleClick(lblCallUpDate).perform();
		 AaoHelper.FindAaoElement(drvr,txtCallUpDate).clear();
		 AaoHelper.FindAaoElement(drvr,txtCallUpDate).sendKeys(s);
		 AaoHelper.FindAaoElement(drvr,btnSave).click();
		 AaoHelper.AaohighlightElement(drvr, lblCallUpError);
		 if((Date!="")&(lblCallUpError.getText().contains("Please do not edit the Hold.")))
			 return true;
		 else return false;
			 
		 
		
	}
	
	public void clickServiceItem() {
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr,lnkServiceItem).click();
	}
	
	public boolean verifyHold() {
		
		
		boolean  b=false;
		 AaoHelper.FindAaoElement(drvr,lnkHolds).click();
	AaoHelper.waitForAaoElement(drvr,tblHold);
	AaoHelper.AaohighlightElement(drvr, tblHold);
	 
	 List<WebElement> rows = tblHold.findElements(By.tagName("tr"));
	 int rows_count=rows.size();
	 for (int row = 2; row <=rows_count; row++) {
		
	AaoHelper.sleep(3);
		 String callupdate=drvr.findElement(By.xpath("//div[2]/table/tbody/tr[" + row + "]/td[6]")).getText();
		 String holddays=drvr.findElement(By.xpath("//div[2]/table/tbody/tr[" + row + "]/td[7]")).getText();
		 String datecmp=AaoHelper.getCallUpResumeDate();
	if((callupdate.equalsIgnoreCase(datecmp))&(holddays.equalsIgnoreCase("0")))
	{
		b=true;
		break;
	}
}
		if(b)
			return true;
		else
			return false;
	}
	
	public void removeHold(String s) {
		AaoHelper.waitForAaoElement(drvr, lnkHolds);
		AaoHelper.FindAaoElement(drvr,lnkHolds).click();
		AaoHelper.sleep(5);
		boolean  b=false;
		
	AaoHelper.waitForAaoElement(drvr,tblHold);
	AaoHelper.AaohighlightElement(drvr, tblHold);
	 
	 List<WebElement> rows = tblHold.findElements(By.tagName("tr"));
	 int rows_count=rows.size();
	 for (int row = 2; row <=rows_count; row++) {
		
	AaoHelper.sleep(3);
		 String text=drvr.findElement(By.xpath("//div[5]//table/tbody/tr[" + row + "]/td[2]")).getText();
		 if(text.equalsIgnoreCase(s))
		 {
			 
			 AaoHelper.waitForAaoElement(drvr, drvr.findElement(By.xpath("//div[5]//table/tbody/tr[" + row + "]/td[1]/input")));
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[5]//table/tbody/tr[" + row + "]/td[1]/input")));
			 drvr.findElement(By.xpath("//div[5]//table/tbody/tr[" + row + "]/td[1]/input")).click();
			 AaoHelper.AaohighlightElement(drvr, btnRemoveHold);
			 AaoHelper.FindAaoElement(drvr, btnRemoveHold).click();
		
			 b=true;
			 break;
		 }
	 }
		
	}
	
	public boolean verifyResumeDate() {
		boolean  b=false;
		 AaoHelper.FindAaoElement(drvr,lnkHolds).click();
	AaoHelper.waitForAaoElement(drvr,tblHold);
	 
	 List<WebElement> rows = tblHold.findElements(By.tagName("tr"));
	 int rows_count=rows.size();
	 for (int row = 2; row <=rows_count; row++) {
		String resumedate=drvr.findElement(By.xpath("//div[5]/div[1]/div/form/div[2]/table/tbody/tr[" + row + "]/td[4]")).getText();
		
		 String datecmp=AaoHelper.getCallUpResumeDate();
		 System.out.println(datecmp);
			if((resumedate.equalsIgnoreCase(datecmp)))
			{
				b=true;
				break;
			}
		}
				if(b)
					return true;
				else
					return false;
			}
	
	
	public boolean verifyStatus(String text) {
		AaoHelper.waitForAaoElement(drvr, btnEdit);
		AaoHelper.waitForAaoElement(drvr, lblStatus);
		 try {
			AaoHelper.scrollIntoView(drvr, lblStatus);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    AaoHelper.AaohighlightElement(drvr, lblStatus);
		 String ele= AaoHelper.FindAaoElement(drvr,lblStatus).getText();
		 System.out.println(ele);
			if (ele.contains(text))
			return true;
			else
				return false;
	 }
	
	public void clickSave() {
		
		AaoHelper.FindAaoElement(drvr, btnSave).click();
	}
	
	public void clickCancel() {
		AaoHelper.FindAaoElement(drvr, btnCancel).click();
	}
	
	public void updateParty(String text) {
		
		AaoHelper.sleep(4);
		
		try {
			 AaoHelper.scrollIntoView(drvr,lblResponsibleParty);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement ele= AaoHelper.FindAaoElement(drvr,lblResponsibleParty);
		
		 AaoHelper.FindAaoElement(drvr,lblResponsibleParty);
		 Actions actions = new Actions(drvr);
		 actions.doubleClick(lblResponsibleParty).perform();
		 AaoHelper.waitForAaoElement(drvr,lstRespParty);
		 Select select=new Select(lstRespParty);
		 AaoHelper.sleep(4);
		 select.selectByVisibleText(text);
		 clickSave();
	}
	
	public boolean verifyErrorMessage() {
		AaoHelper.sleep(2);
		AaoHelper.AaohighlightElement(drvr, lblHoldError);
		
		return true;
		
	}
	
	 
	}


