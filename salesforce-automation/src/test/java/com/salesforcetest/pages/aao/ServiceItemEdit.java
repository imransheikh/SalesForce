package com.salesforcetest.pages.aao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ServiceItemEdit {
	@FindBy(xpath="//div[3]//tr[1]/td[4]/div/span/select")
	private WebElement lstItemType;
	@FindBy(xpath="//div[3]//tr[2]/td[4]/div/span/span/select")
	private WebElement lstMotionType;
	@FindBy(xpath="//div[5]//tr[1]/td[2]/div/span/select")
	private WebElement lstStatus;
	@FindBy(xpath="//div[7]//tr[1]/td[2]/div/span/select")
	private WebElement lstParty;
	@FindBy(xpath="//div[9]//tr[1]/td[2]/span/span/select")
	private WebElement lstFinalDecision;
	@FindBy(xpath="//div[5]//tr[2]/td[4]/span/input")
	private WebElement txtIssuedDate;
	@FindBy(xpath="//td[@id='topButtonRow']/input[1]")
	private WebElement btnSave;
	@FindBy(id="errorDiv_ep")
	private WebElement lblError;
	@FindBy(xpath="//div[2]//tr/td[2]//tr[2]/td")
	private WebElement lblRecordError;
	@FindBy(linkText="here")
	private WebElement lnkHere;
	@FindBy(xpath="//div[2]//tr[2]/td[2]/div/span/select")
	private WebElement lstAfbAction;
	@FindBy(xpath="//div[2]//tr[9]/td[2]/div/span/select")
	private WebElement lstAfbStatus;
	@FindBy(xpath="//div[3]//tr[3]/td[2]/div/span/select")
	private WebElement lstFileRequestStatus;
	@FindBy(xpath="//input[@name='edit']")
	private WebElement btnEdit;
	@FindBy(xpath="//div[3]//tr[3]/td[2]/div")
	private WebElement lblFileStatus;
	@FindBy(xpath="//input[@name='cancel']")
	private WebElement btnCancel;
	private WebDriver drvr;
	
	
	
public ServiceItemEdit(WebDriver driver)
	
	{
		PageFactory.initElements(driver, this);
		this.drvr=driver;

		
	}

public boolean verifyAaoType(String text) {
	 
	 AaoHelper.FindAaoElement(drvr, lstItemType);
	 AaoHelper.AaohighlightElement(drvr, lstItemType);
	 Select sel=new Select(lstItemType);
	 WebElement ele=sel.getFirstSelectedOption();
	 System.out.print(ele.getText());
	 if(ele.getText().equalsIgnoreCase(text))
		 return true;
	 else
		 return false;
	 
}

public void selectAaoType(String s)
{
	AaoHelper.FindAaoElement(drvr, lstItemType);
	 AaoHelper.AaohighlightElement(drvr, lstItemType);
	 Select sel=new Select(lstItemType);
	 sel.selectByVisibleText(s);
	 try {
			AaoHelper.scrollIntoView(drvr,btnSave);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
}

public boolean verifyMotion(String text) {
	 
	 AaoHelper.FindAaoElement(drvr, lstMotionType);
	 AaoHelper.AaohighlightElement(drvr, lstMotionType);
	 Select sel=new Select(lstMotionType);
	 WebElement ele=sel.getFirstSelectedOption();
	String s= ele.getText();
	System.out.println(s);
	 if(ele.getText().equalsIgnoreCase(text))
		 return true;
	 else
		 return false;
	 
}

public void selectMotionType(String s)
{
	 AaoHelper.FindAaoElement(drvr, lstMotionType);
	 AaoHelper.AaohighlightElement(drvr, lstMotionType);
	 Select sel=new Select(lstMotionType);
	 AaoHelper.sleep(2);
	 sel.selectByVisibleText(s);
}
public boolean verifyLstStatus(String text) {
	 try {
		AaoHelper.scrollIntoView(drvr, lstStatus);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 AaoHelper.FindAaoElement(drvr, lstStatus);
	 AaoHelper.AaohighlightElement(drvr, lstStatus);
	 Select sel=new Select(lstStatus);
	 WebElement ele=sel.getFirstSelectedOption();
	 if(ele.getText().equalsIgnoreCase(text))
		 return true;
	 else
		 return false;
	 
}

public boolean verifyParty(String text) {
	try {
		AaoHelper.scrollIntoView(drvr,lstParty);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 AaoHelper.FindAaoElement(drvr, lstParty);
	 AaoHelper.AaohighlightElement(drvr, lstParty);
	 Select sel=new Select(lstParty);
	 WebElement ele=sel.getFirstSelectedOption();
	 String s=ele.getText();
	 System.out.println(s);
	 if(ele.getText().equalsIgnoreCase(text))
		 return true;
	 else
		 return false;
	 
}

public boolean verifyFinalDecision(String text) {
	try {
		AaoHelper.scrollIntoView(drvr,lstFinalDecision);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 AaoHelper.FindAaoElement(drvr, lstFinalDecision);
	 AaoHelper.AaohighlightElement(drvr, lstFinalDecision);
	 Select sel=new Select(lstFinalDecision);
	 WebElement ele=sel.getFirstSelectedOption();
	 if(ele.getText().equalsIgnoreCase(text))
		 return true;
	 else
		 return false;
	 
}

public void selectParty(String text) {
	 try {
			AaoHelper.scrollIntoView(drvr,lstParty);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	 AaoHelper.FindAaoElement(drvr, lstParty);
	 Select sel=new Select(lstParty);
	 AaoHelper.sleep(3);
	 sel.selectByVisibleText(text);
	 AaoHelper.AaohighlightElement(drvr, lstParty);
	 AaoHelper.sleep(3);
	 try {
			AaoHelper.scrollIntoView(drvr,btnSave);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
}

public void updateIssuedDate() {
	try {
		AaoHelper.scrollIntoView(drvr,txtIssuedDate);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	AaoHelper.FindAaoElement(drvr, txtIssuedDate).clear();
 
}

public void selectStatus(String text) {
	
	 try {
			AaoHelper.scrollIntoView(drvr,lstStatus);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 
	 AaoHelper.FindAaoElement(drvr, lstStatus);
	 Select sel=new Select(lstStatus);
	 AaoHelper.sleep(3);
	 sel.selectByVisibleText(text);
	 AaoHelper.AaohighlightElement(drvr, lstStatus);
	 AaoHelper.sleep(3);
}

public void editSI() {
	
	updateIssuedDate();
	selectStatus("Pending");
	selectParty("Triage - Queue");
}

public boolean verifyError() {
	
	AaoHelper.sleep(2);
	AaoHelper.AaohighlightElement(drvr, lblError);
	
	return true;
	
}

public boolean verifyRecordError() {
	AaoHelper.sleep(2);
	AaoHelper.AaohighlightElement(drvr, lblRecordError);
	
	return true;
	
}

public void clickHere() {
	AaoHelper.waitForAaoElement(drvr, lnkHere);
	AaoHelper.FindAaoElement(drvr, lnkHere).click();
}

public void clickSave() {
	 try {
			AaoHelper.scrollIntoView(drvr,btnSave);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	AaoHelper.FindAaoElement(drvr, btnSave).click();
}

public void inputAfbItemInfo() {
	
	AaoHelper.waitForAaoElement(drvr, lstAfbAction);
	AaoHelper.AaohighlightElement(drvr, lstAfbAction);
	Select select=new Select(lstAfbAction);
	 AaoHelper.sleep(4);
	 select.selectByVisibleText("Other");
	 clickSave();
}

public void editAfbStatus() {
	AaoHelper.waitForAaoElement(drvr, lstAfbStatus);
	AaoHelper.AaohighlightElement(drvr, lstAfbStatus);
	Select select=new Select(lstAfbStatus);
	 AaoHelper.sleep(4);
	 select.selectByVisibleText("Completed");
	 clickSave();
	
}

public void editFileRequestStatus(String s) {
	AaoHelper.waitForAaoElement(drvr, lstFileRequestStatus);
	AaoHelper.AaohighlightElement(drvr, lstFileRequestStatus);
	Select select=new Select(lstFileRequestStatus);
	 AaoHelper.sleep(4);
	 select.selectByVisibleText(s);
	 clickSave();
	 AaoHelper.sleep(5);
}

public boolean verifyFileRequestStatus(String s)

{
	AaoHelper.waitForAaoElement(drvr, lblFileStatus);
	AaoHelper.AaohighlightElement(drvr, lblFileStatus);
	if((lblFileStatus.getText().equalsIgnoreCase(s)))
		return true;
	else return false;
			
		
		
	
}

public void clickCancel()
{
	AaoHelper.waitForAaoElement(drvr, btnCancel);
	AaoHelper.FindAaoElement(drvr, btnCancel).click();
}

}


