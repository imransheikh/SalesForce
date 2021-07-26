package com.salesforcetest.pages.aao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;
import com.salesforcetest.pages.aao.manager.FileManager;

import com.salesforcetest.pages.aao.utils.AaoHelper;
import com.salesforcetest.pages.salesforce.uatg.GetScreenShot;



public class ServiceItem {
	
	private  WebDriver drvr;
//	private AaoAaoHelper AaoHelper;
	private ApplicationDetail appdetail;
	private Select select;
	
	@FindBy(name="p3")
	private WebElement lstServiceRecordType;
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement btnContinue;
	@FindBy(xpath="//div[3]/table/tbody/tr[1]/td[2]/div/span/input")
	private WebElement txtReceiptNumber;
	@FindBy(xpath="//div[3]/table/tbody/tr[1]/td[4]/div/span/select")
	private WebElement lstAaoServiceItemType;
	@FindBy(xpath="//div[3]/table/tbody/tr[4]/td[4]/div/span/input")
	private WebElement txtOriginatingOffice;
	@FindBy(xpath="//span[@class='dateFormat']/a")
	private WebElement lnkDateLink;
	@FindBy(xpath="//div[3]/table/tbody/tr[7]/td[4]/div/span/input")
	private WebElement txtAaoRecdDate;
	@FindBy(id="cas7")
	private WebElement lstStatus;
	@FindBy(xpath="//div[7]/table/tbody/tr[1]/td[2]/div/span/select")
	private WebElement lstResponsibleParty;
	
	@FindBy(xpath="//td[@id='bottomButtonRow']/input[1]")
	private WebElement btnSave;
	@FindBy(xpath="//img[contains(@title,'Originating Office Lookup')]")
	private WebElement imgOfficeLookUp;
	@FindBy(xpath="//img[contains(@title,'Supervisor Lookup')]")
	private WebElement imgSupervisorLookUp;
	@FindBy(xpath="//img[contains(@title,'Editor Lookup')]")
	private WebElement imgEditorLookUp;
	@FindBy(xpath="//img[contains(@title,'Contact Name Lookup')]")
	private WebElement imgContactLookUp;
	@FindBy(xpath="//img[contains(@title,'Account Name Lookup')]")
	private WebElement imgAccountLookUp;
	@FindBy(xpath="//img[contains(@title,'AAO Internal Contact Lookup')]")
	private WebElement imgInternalContactLookUp;
	@FindBy(id="lksrch")
	private WebElement txtLookUpSearch;
	@FindBy(name="go")
	private WebElement btnGo;
	@FindBy(linkText="AAO")
	private WebElement lnkOffice;
	@FindBy(xpath="//div[2]/div[3]/table/tbody/tr[1]/td[4]/div/div[2]")
	private WebElement lblItemTypeErrorMessage;
	@FindBy(xpath="//div[2]/div[3]/table/tbody/tr[4]/td[4]/div/div[2]")
	private WebElement lblOfficeErrorMessage;
	@FindBy(xpath="//div[3]//tr[5]/td[2]/span/select")
	private WebElement lstDivision;
	@FindBy(xpath="//div[3]//tr[6]/td[2]/span/select")
	private WebElement lstBranch;
	@FindBy(xpath="//div[5]//tr[4]/td[2]/input")
	private WebElement txtOfficerComments;
	
	@FindBy(xpath="//div[5]//tr[5]/td[2]/input")
	private WebElement txtSupervisorComments;
	
	@FindBy(xpath="//div[11]//tr[6]/td[2]/input")
	private WebElement txtAttorney;
	@FindBy(xpath="//div[15]//tr[3]/td[4]/div/input")
	private WebElement txtOffset;
	@FindBy(xpath="//h3[contains(text(),'Status Information')]")
	private WebElement lblStatusInfo;
	@FindBy(xpath="//h3[contains(text(),'Customer Information')]")
	private WebElement lblCustomerInfo;
	
	@FindBy(xpath="//div[3]//div[2]")
	private WebElement tblSearch;
	@FindBy(xpath="//div[11]//tr[5]/td[2]/input")
	private WebElement txtBusiness;
	
	
	 public ServiceItem(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			//appdetail=new ApplicationDetail(driver);
			
			
		}
	 
	
	 
	 public void selectRecordType(String text)
	 {
		
		 AaoHelper.waitForAaoElement(drvr,lstServiceRecordType);
		 AaoHelper.AaohighlightElement(drvr, lstServiceRecordType);
		Select select=new Select(lstServiceRecordType);
		select.selectByVisibleText(text);
	 }
	 
	 public void clickContinue()
	    {
		 
	    	AaoHelper.waitForAaoElement(drvr,btnContinue);
	    	btnContinue.click();
	    	
	    }

	 public void createServiceItem(String type,String itemtype) {
		 selectRecordType(type);
		 clickContinue();
		// fillServiceItem(itemtype);
		// clickSave();
	 }
	 
	 public void fillServiceItemInfo(String s,String s1,String s2)
			 {
		
	 
	
		 setItemType(s);
		
	 
	 
	  
	  lookUp(s1,s2);
	  String recdDate=lnkDateLink.getText();
	  AaoHelper.FindAaoElement(drvr,txtAaoRecdDate).clear();
	  txtAaoRecdDate.sendKeys(recdDate);
	 
	 // AaoHelper.waitForAaoElement(drvr, lstStatus);
	//  select=new Select(lstStatus);
   // select.selectByValue(status);
	// AaoHelper.waitForAaoElement(drvr, lstResponsibleParty);
	// select=new Select(lstResponsibleParty);
	//	select.selectByVisibleText(responsibleparty);
		// AaoHelper.FindAaoElement(drvr,txtOffset).sendKeys(offset);
		
}
	 
	 public void fillStatusInfo(String s1,String s2)
	 {
		 fillOfficerComments(s1);
		 fillSupervisorComments(s2);
	 }
	 
	 public void fillRespPartyInfo(String s,String s1,String s2,String s3)
	 {
		 
		 lookUp(s,s1);
		 lookUp(s2,s3);
		
		 
			 
	 }
	 
	 public void fillCustomerInfo(String s,String s1,String s2,String s3,String s4,String s5)
	 {
		 try {
				AaoHelper.scrollIntoView(drvr,lblCustomerInfo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 lookUp(s,s1);
		 lookUp(s2,s3);
		 fillBusiness(s4);
		 fillAttorney(s5);
		 
	 }
	 
	 public void fillSystemInfo(String s1,String s2)
	 {
		 try {
			AaoHelper.scrollIntoView(drvr,btnSave);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 lookUp(s1,s2);
	 }
	 public void setItemType(String s)
	 {
		 AaoHelper.waitForAaoElement(drvr, lstAaoServiceItemType);
		  AaoHelper.AaohighlightElement(drvr, lstAaoServiceItemType);
		 
		   select=new Select(lstAaoServiceItemType);
		 select.selectByIndex(1);
	 }
	// public void lookUpOffice(String s) {
		// AaoHelper.FindAaoElement(drvr,imgOfficeLookUp).click();
		// AaoHelper.sleep(6);
	//	String parentHandler= AaoHelper.switch_to_popup(drvr);
	//	drvr.switchTo().frame(0);
	//	 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	//executor.executeScript("arguments[0].value='"+s+"';",txtLookUpSearch);
	//	 AaoHelper.FindAaoElement(drvr, btnGo).click();
	//	 drvr.switchTo().defaultContent();
	//	 drvr.switchTo().frame(1);
		// AaoHelper.sleep(6);
		// AaoHelper.FindAaoElement(drvr,lnkOffice).click();
		// clickSearchResults(s);
		// drvr.switchTo().window(parentHandler);
		 
	// }
	 
	 public boolean verifyReceiptNumber(String no) {
		 
		 String text=AaoHelper.FindAaoElement(drvr,txtReceiptNumber).getAttribute("value");
				
		
		 if(text.equalsIgnoreCase(no))
			 return true;
		 else
			 return false;
			 
		 
	 }
	 public boolean verifyAaoRecdDate()
	 {
		 String d=AaoHelper.FindAaoElement(drvr, txtAaoRecdDate).getAttribute("value");
		
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		 String date1= sdf.format(date);
		 
		 if(d.equals(date1))
			 return true;
		 else 
			 return false;
	 
	 }
	 
	 public boolean verifyResponsibleParty() 
	 {
		 try {
			AaoHelper.scrollIntoView(drvr, lstStatus);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Select s= new Select(lstResponsibleParty);
		 String text=s.getFirstSelectedOption().getText();
		 if(text.equals("Mail Room - Queue"))
			 return true;
		 else return false;
	 }
	 public boolean verifyErrorMessages() {
		 
		 AaoHelper.sleep(2);
		 AaoHelper.AaohighlightElement(drvr, lblItemTypeErrorMessage);
		 AaoHelper.AaohighlightElement(drvr, lblOfficeErrorMessage);
		
		    if ((lblItemTypeErrorMessage.getText().trim().contains("You must enter a value")) & (lblOfficeErrorMessage.getText().trim().contains("You must enter a value")))
		    {
		        
		        return true;
		    }
		    else
		    {
		        
		        return false;
		    }
		}
	
	 
	 public void clickSave()
	    {
		 AaoHelper.waitForAaoElement(drvr, btnSave);
		
		
	    	WebElement ele=AaoHelper.FindAaoElement(drvr,btnSave);
	    	ele.click();
	    	AaoHelper.sleep(3);
	    }
	 
	 public void fillOfficerComments(String s) 
	 {
		 try {
			AaoHelper.scrollIntoView(drvr,lblStatusInfo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 AaoHelper.AaohighlightElement(drvr, txtOfficerComments);
		 AaoHelper.FindAaoElement(drvr,txtOfficerComments).sendKeys(s);
		 
	 }
	 
	 public void fillSupervisorComments(String s)
	 {
		
		 AaoHelper.AaohighlightElement(drvr, txtSupervisorComments);
		 AaoHelper.FindAaoElement(drvr,txtSupervisorComments).sendKeys(s);
		 
	 }
	 
	 public void lookUp(String s1,String s2) {
		 WebElement e1 = null;
		 AaoHelper.sleep(2);
		 if(s1.equalsIgnoreCase("Office")) 
			 e1=imgOfficeLookUp;
		 if(s1.equalsIgnoreCase("Supervisor")) 
			 e1=imgSupervisorLookUp;
		 if(s1.equalsIgnoreCase("Editor")) 
			 e1=imgEditorLookUp;
		 if(s1.equalsIgnoreCase("Contact")) 
			 e1=imgContactLookUp;
		 if(s1.equalsIgnoreCase("Account")) 
			 e1=imgAccountLookUp;
		 if(s1.equalsIgnoreCase("Internal Contact")) 
			 e1=imgInternalContactLookUp;
		
		 AaoHelper.FindAaoElement(drvr,e1).click();
		 AaoHelper.sleep(6);
		String parentHandler= AaoHelper.switch_to_popup(drvr);
		drvr.switchTo().frame(0);
		 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	executor.executeScript("arguments[0].value='"+s2+"';",txtLookUpSearch);
		 AaoHelper.FindAaoElement(drvr, btnGo).click();
		 drvr.switchTo().defaultContent();
		 drvr.switchTo().frame(1);
		 AaoHelper.sleep(6);
		clickSearchResults(s2);
		 drvr.switchTo().window(parentHandler);
		 
	 }
	 
	 public void clickSearchResults(String s)
	 {
		
			AaoHelper.waitForAaoElement(drvr,tblSearch);
			
			 
			 List<WebElement> rows = tblSearch.findElements(By.tagName("tr"));
			 int rows_count=rows.size();
			 for (int row = 2; row <=rows_count; row++) {
				
			AaoHelper.sleep(3);
			 String sm=drvr.findElement(By.xpath("//div[2]//tr[ "+row+"]/th/a")).getText();
			 if(sm.equalsIgnoreCase(s)) {
				 drvr.findElement(By.xpath("//div[2]//tr[ "+row+"]/th/a")).click();
				 break;
			 }
	 }
	 }
	 
	
	 public void selectDivision(String s) {
		 Select sel=new Select(lstDivision);
		 AaoHelper.AaohighlightElement(drvr, lstDivision);
		 AaoHelper.sleep(3);
		 sel.selectByValue(s);
	 }
	 
	 
	 public void selectBranch(String s) {
		 Select sel=new Select(lstBranch);
		 AaoHelper.AaohighlightElement(drvr, lstBranch);
		 AaoHelper.sleep(3);
		 sel.selectByValue(s);
	 }
	 public void fillAttorney(String s)
	 {
		 AaoHelper.AaohighlightElement(drvr, txtAttorney);
		 AaoHelper.FindAaoElement(drvr, txtAttorney).sendKeys(s);
		 
	 }
	 
	 public void fillBusiness(String s)
	 {
		 AaoHelper.AaohighlightElement(drvr, txtBusiness);
		 AaoHelper.FindAaoElement(drvr, txtBusiness).sendKeys(s);
	 }
}
	 
	 
	 
	 
