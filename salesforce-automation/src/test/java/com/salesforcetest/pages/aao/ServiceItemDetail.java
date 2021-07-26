package com.salesforcetest.pages.aao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ServiceItemDetail {
	private  WebDriver drvr;
	
	
	
	
	@FindBy(xpath="//div[2]/table/tbody/tr[6]/td[2]/div")
	private WebElement lblUnderlyingBenefit;
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[9]/td[4]/div")
	
	private WebElement lblAaoRecdDate;
	//@FindBy(xpath="//div[1]/div[2]/table/tbody/tr/td[2]/div[4]/div[2]/div[2]/table/tbody/tr[10]/td[4]/div")
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[10]/td[4]/div")
		private WebElement lblArcStartDate;
	//@FindBy(xpath="//div[1]/div[2]/table/tbody/tr/td[2]/div[4]/div[2]/div[2]/table/tbody/tr[9]/td[2]/div")
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[9]/td[2]/div")
	private WebElement lblAaoClock;
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[2]/td[2]/div")
	private WebElement lblReceiptNo;
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[3]/td[2]/div")
	private WebElement lblFileNo;
	
	@FindBy(xpath="//div[2]/table/tbody/tr[10]/td[2]/div")
	private WebElement lblAdjudicationClock;
	
	@FindBy(xpath="//div[4]//tr[8]/td[4]/div")
	private WebElement lblAaoClockAfb;
	@FindBy(id="datePickerCalendar")
	private WebElement tblCalendar;
	@FindBy(xpath="//div[2]/table/tbody/tr[9]/td[4]/div[2]/input")
	private WebElement txtAaoRecdDate;
	@FindBy(name="inlineEditSave")
	private WebElement btnSave;
	
	@FindBy(xpath="//input[@value='Refresh Application Data']")
	private WebElement btnRefresh;
	@FindBy(xpath="//div[2]/div[8]/table/tbody/tr[1]/td[2]/div")
	private WebElement lblResponsibleParty;
	@FindBy(xpath="//div[2]/div[8]/table/tbody/tr[1]/td[2]/div[2]/span/select")
	private WebElement lstRespParty;
	@FindBy(xpath="//div[6]//tr[3]/td[2]/div")
	private WebElement lblMileStone;
	@FindBy(xpath="//div[6]//tr[2]/td[2]/div[1]")
	private WebElement lblStatus;
	@FindBy(xpath="//h2[@class='pageDescription']")
	private WebElement lblServiceItemNumber;
	@FindBy(xpath="//div[10]/table/tbody/tr[1]/td[2]/div")
	private WebElement lblFinalDecision;
	@FindBy(xpath="//div[1]/div/table/tbody/tr[3]/td[2]/div/span/span/select")
	private WebElement lstDecision;
	@FindBy(xpath="//input[@class='zen-btn']")
	private WebElement btnOk;
	@FindBy(xpath="//div[8]/descendant::div[3]")
	private WebElement lblMsg;
	@FindBy(xpath="//div[10]/descendant::div[2]")
	private WebElement lblMsg1;
	
	@FindBy(xpath="//input[@value='Reopen Service Item']")
	private WebElement btnReopen;
	@FindBy(xpath="//div[4]/div[2]/div[2]/table/tbody/tr[7]/td[2]/div/a")
	private WebElement lblParentServiceItem;
	@FindBy(xpath="//div[@class='bRelatedList'][6]/descendant::table/tbody/tr[2]/th/a")
	private WebElement lnkResponseNumber;
	@FindBy(xpath="//div[3]/a[7]/span")
	private WebElement lnkResponses;
	@FindBy(xpath="//div[4]/div[2]/div[20]/table/tbody/tr[6]/td[4]/div[1]")
	private WebElement lblInternalContact;
	@FindBy(xpath="//tr[6]/td[4]/div[2]/span/a/img")
	private WebElement imgLookUpContact;
	@FindBy(name="go")
	private WebElement btnGo;
	@FindBy(xpath="//div[@id='Contact_body']/table[@class='list']/tbody")
	private WebElement tblSearchResult;
	@FindBy(id="lksrch")
	private WebElement txtLookUpSearch;
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[14]/td[2]/div")
	private WebElement lblCompletionWeight;
	@FindBy(xpath="//div[@class='pbSubsection'][1]/descendant::tr[14]/td[2]/div[2]/input")
	private WebElement txtlstWeight;
	@FindBy(xpath="//input[@name='edit']")
	private WebElement btnEdit;
	@FindBy(xpath="//div[4]/div[2]/div[6]/table/tbody/tr[2]/td[4]/div[1]")
	private WebElement lblIssuedDate;
	@FindBy(xpath="//div[4]/div[2]/div[6]/table/tbody/tr[2]/td[4]/div[2]/input")
	private WebElement lstIssuedDate;
	@FindBy(xpath="//div[20]//tr[4]/td[2]/div/a")
	private WebElement lnkChange;
	@FindBy(xpath="//h3[text()='Service Item History']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//div[2]/table")
	private WebElement tblServiceHistory;
	@FindBy(xpath="//div[4]/div[2]/div[1]")
	private WebElement lblHoldError;
	@FindBy(name="inlineEditCancel")
	private WebElement btnCancel;
	@FindBy(xpath="//div[2]//td[2]/div[3]/a[2]/span")
	private WebElement lnkRelatedServiceItems;
	@FindBy(xpath="//input[@value='Create AFB Service Item']")
	private WebElement btnCreateAFBItem;
	@FindBy(xpath="//a[@title='Edit - Record 1 - AFB Service Item']")
	private WebElement lnkCloseAfb;
	@FindBy(xpath="//div[4]//tr[6]/td[4]/div")
	private WebElement lblServiceItemGroup;
	@FindBy(xpath="//div[2]//div/textarea")
	private WebElement txtServiceItemGroup;
	@FindBy(xpath="//input[@class='zen-btn']")
	private WebElement btnServiceItemGrpOk;
	@FindBy(xpath="//div[10]//tr[5]/td[1]")
	private WebElement lblEgregiousPublicSafety;
	@FindBy(xpath="//div[3]//tr[3]/td[2]/div")
	private WebElement lblFileRequestStatus;
	
	@FindBy(xpath="//div[8]//tr[5]/td[2]//span/select")
	private WebElement lstTriage;
	@FindBy(xpath="//div[8]//tr[6]/td[2]//span/select")
	private WebElement lstBcuChecks;
	@FindBy(xpath="//div[8]//tr[6]/td[4]/div[1]")
	private WebElement lblTriageDate;
	@FindBy(xpath="//div[8]//tr[6]/td[4]/div[2]/input")
	private WebElement txtTriageDate;
	@FindBy(xpath="//div[8]//tr[7]/td[4]/div")
	private WebElement lblBcuCompletedDate;
	@FindBy(xpath="//div[8]//tr[7]/td[4]/div[2]/input")
	private WebElement txtBcuCompletedDate;
	@FindBy(xpath="//div[8]//tr[5]/td[2]/div")
	private WebElement lblTriageCompletedBy;
	@FindBy(xpath="//div[8]//tr[6]/td[2]/div[1]")
	private WebElement lblBcuChecksCompletedBy;
	
	
	
	String data;





	
	
	public ServiceItemDetail(WebDriver driver)
	
	{
		PageFactory.initElements(driver, this);
		this.drvr=driver;
		
		
	
		
	}
 
public String getServiceItemNumber()
{
	AaoHelper.waitForAaoElement(drvr, lblServiceItemNumber);
	return lblServiceItemNumber.getText();
}

 
 
 public boolean verifyUnderlyingBenefit(String benefit) {
	 AaoHelper.waitForAaoElement(drvr, btnRefresh);
	String ele= AaoHelper.FindAaoElement(drvr,lblUnderlyingBenefit).getText();
	if (ele.contains(benefit))
	return true;
	else
		return false;
	 
 }
 
 
 public void  updateAaoRecdDate(){
	 
	 drvr.navigate().back();
	 int count=0;
	 try {
		AaoHelper.scrollIntoView(drvr,lblAaoRecdDate);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	 
	 AaoHelper.FindAaoElement(drvr,lblAaoRecdDate);

	 Actions actions = new Actions(drvr);
	 actions.doubleClick(lblAaoRecdDate).perform();
	 
	data= AaoHelper.selectDate();
	    try{

	    	AaoHelper.sleep(4);
	    	txtAaoRecdDate.clear();
	    	txtAaoRecdDate.sendKeys(data);

	     
	    }  catch(Exception e){
	    	
	    	e.printStackTrace();
	    }
	   
			 AaoHelper.waitForAaoElement(drvr,btnSave);
			 try {
				AaoHelper.scrollIntoView(drvr, btnSave);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// JavascriptExecutor executor = (JavascriptExecutor)drvr;
			//AaoHelper.waitForAaoElement(drvr, btnRefresh);
	//	WebElement r= drvr.findElement(By.xpath("//input[@value='Refresh Application Data']"));
		// while(count<=2)
		// {
		
		//	if(btnRefresh.isDisplayed())
			// break;
			
		//	else {
	//drvr.navigate().refresh();
			 
			// System.out.println("catch");
		//	 actions.doubleClick(lblAaoRecdDate).perform();
			// AaoHelper.waitForAaoElement(drvr,txtAaoRecdDate);
			
			 
			// txtAaoRecdDate.clear();
		    //	txtAaoRecdDate.sendKeys(data);
			 
		// }
		// count=count+1;
	//	 }
		
		
			 clickSave();
		
	
}
 
 public void clickSave() {
 AaoHelper.waitForAaoElement(drvr,btnSave);
	 
	 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	 executor.executeScript("arguments[0].click();", btnSave);
	
AaoHelper.waitForAaoElement(drvr,btnRefresh);
	 
//try {
//executor.executeScript("arguments[0].click();", btnRefresh);
//}catch(UnhandledAlertException e) {
//}

//AaoHelper.sleep(5);
//executor.executeScript("window.alert = function() {};");

//try {
	//drvr.switchTo().alert().accept();
//}catch(Exception e) {
//}
}
	
	 
	
	

 
 public boolean verifyArcStartDate() {
	 AaoHelper.sleep(10);
	 AaoHelper.waitForAaoElement(drvr,lblArcStartDate);
	 String datecmp=AaoHelper.selectDate();
	 System.out.println(datecmp);
	
String d= AaoHelper.FindAaoElement(drvr,lblArcStartDate).getText();
	AaoHelper.AaohighlightElement(drvr, lblArcStartDate);
	
	System.out.println(d);
	return (d.trim().equalsIgnoreCase(datecmp.trim()));
		
				
 }
 
 public boolean verifyAaoClock(String clk) {
	 AaoHelper.waitForAaoElement(drvr,lblAaoClock);
	
	String text1= AaoHelper.FindAaoElement(drvr,lblAaoClock).getText();
	AaoHelper.AaohighlightElement(drvr, lblAaoClock);
	System.out.println(text1);
	return(clk.trim().equalsIgnoreCase(text1.trim()));
		
	 
 }
 
 public boolean verifyAdjudicationClock(String clk) throws InterruptedException {
	 
	 
	 AaoHelper.waitForAaoElement(drvr,lblAdjudicationClock);
	 AaoHelper.scrollIntoView(drvr, lblAdjudicationClock);
	String text= AaoHelper.FindAaoElement(drvr,lblAdjudicationClock).getText();
	AaoHelper.AaohighlightElement(drvr, lblAdjudicationClock);
	System.out.println(text);
	
	return(clk.equalsIgnoreCase(text));
		
 }
 
public boolean verifyAaoClockAfb(String clk) throws InterruptedException {
	 
	 
	 AaoHelper.waitForAaoElement(drvr,lblAaoClockAfb);
	 AaoHelper.scrollIntoView(drvr, lblAaoClockAfb);
	String text= AaoHelper.FindAaoElement(drvr,lblAaoClockAfb).getText();
	AaoHelper.AaohighlightElement(drvr, lblAaoClockAfb);
	System.out.println(text);
	
	return(clk.equalsIgnoreCase(text));
		
 }
 
 public void updateRespParty(String text) {
	 
	 drvr.navigate().back();
	 int count=0;
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
	 AaoHelper.waitForAaoElement(drvr, lstRespParty);
	 select.selectByVisibleText(text);
	 
	
		 AaoHelper.waitForAaoElement(drvr,btnSave);
		 try {
			AaoHelper.scrollIntoView(drvr, btnSave);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	
	//while(count<=2)
	// {
	//	if(btnRefresh.isDisplayed())
		// break;
		
	//	else {
//drvr.navigate().refresh();
		 
		// System.out.println("catch");
		// actions.doubleClick(lblResponsibleParty).perform();
		// AaoHelper.waitForAaoElement(drvr,lstRespParty);
		
		 
		// select.selectByVisibleText(text);
		 
	// }
	// count=count+1;
	// }
	
	
	 clickSave();
	
	 
	
 }
 
 
 public boolean verifyMileStone(String text) {
	 AaoHelper.waitForAaoElement(drvr,lblMileStone);
	
	String text1= AaoHelper.FindAaoElement(drvr,lblMileStone).getText();
	AaoHelper.AaohighlightElement(drvr, lblMileStone);
	System.out.println(text1);
	return(text.trim().equalsIgnoreCase(text1.trim()));
		
	 
 }
 
 public boolean verifyRespPartyList(String text) {
	 
	
boolean b= false;
int j=drvr.findElements(By.xpath("//div[2]/div[6]/table/tbody/tr[1]/td[2]/div[2]/span/select")).size();
if(j==0) {
try {
	AaoHelper.scrollIntoView(drvr,lblResponsibleParty);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
AaoHelper.FindAaoElement(drvr,lblResponsibleParty);
Actions actions = new Actions(drvr);
actions.doubleClick(lblResponsibleParty).perform();
}
	
	
 AaoHelper.waitForAaoElement(drvr,lstRespParty);
	 Select select=new Select(lstRespParty);
	 List<WebElement> allOptions = select.getOptions();
	 for(int i=0; i<allOptions.size(); i++) {
		 AaoHelper.sleep(2);
		 String t=allOptions.get(i).getText();
		 String t1=text;
		 System.out.println(t);
		 System.out.println(t1);
		    if(allOptions.get(i).getText()!=text) {
		        b=true;
		        break;
		    }
 }
	 
	 if(b) return true;
	 else return false;
 }
 
 public boolean verifyStatus(String text) {
	 try {
		AaoHelper.scrollIntoView(drvr, lblStatus);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	AaoHelper.waitForAaoElement(drvr, lblStatus);
	AaoHelper.AaohighlightElement(drvr, lblStatus);
	 String ele= AaoHelper.FindAaoElement(drvr,lblStatus).getText();
	 System.out.println(ele);
		if (ele.contains(text))
		return true;
		else
			return false;
 }
 
 
 public void setFinalDecision(String s) {
	 
	
	 
	 try {
			
			AaoHelper.scrollIntoView(drvr,lblFinalDecision);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
 }
	 
	 AaoHelper.FindAaoElement(drvr,lblFinalDecision);
	 AaoHelper.AaohighlightElement(drvr, lblFinalDecision);
	 Actions actions = new Actions(drvr);
	 AaoHelper.sleep(3);

     actions.doubleClick(lblFinalDecision).build().perform();
	 AaoHelper.sleep(3);
	 drvr.switchTo().activeElement();
	 Select sel=new Select(lstDecision);
	 AaoHelper.sleep(3);
	 AaoHelper.AaohighlightElement(drvr, lstDecision);
	 sel.selectByValue(s);
	 AaoHelper.FindAaoElement(drvr, btnOk).click();
	 AaoHelper.sleep(5);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
	 
 } 
 
 public boolean verifyError() {
	 
        AaoHelper.sleep(4);
	    if ((lblMsg.getText().trim().contains("You do not have the level of access required to make this change.")))
	    		{  
	    	AaoHelper.AaohighlightElement(drvr, lblMsg);
	        return true;
	    }
	    else
	    {
	        
	        return false;
	    }
	}
 
 
 public boolean verifyFinalDecisionError() {
	 
     AaoHelper.sleep(4);
	   
	    	AaoHelper.AaohighlightElement(drvr, lblMsg1);
	    	AaoHelper.sleep(4);
	    	AaoHelper.FindAaoElement(drvr, btnCancel).click();
	    	 return true;
	  
 }
	
 
 public void clickReopen()
 {
	 AaoHelper.waitForAaoElement(drvr, btnReopen);
	 AaoHelper.AaohighlightElement(drvr, btnReopen);
	 AaoHelper.FindAaoElement(drvr, btnReopen).click();
 }
 
 public boolean verifyNewSI(String text)
 {
	 
	 AaoHelper.waitForAaoElement(drvr, lblParentServiceItem);
	 AaoHelper.AaohighlightElement(drvr, lblParentServiceItem);
	String s=lblParentServiceItem.getText();
	if(s.equals(text))
		return true;
	else
		return false;
		
	 
 }
 
 public boolean newSIDisplayed(String text) {
	 AaoHelper.FindAaoElement(drvr, lblServiceItemNumber);
	 AaoHelper.AaohighlightElement(drvr, lblServiceItemNumber);
	 String s=lblServiceItemNumber.getText();
		if(s!=text)
			return true;
		else
			return false;
			
	 
 }
 
 public void goToResponse() {
	 AaoHelper.sleep(4);
	 JavascriptExecutor executor = (JavascriptExecutor)drvr;
	 executor.executeScript("arguments[0].click();", lnkResponses);
	 AaoHelper.FindAaoElement(drvr,lnkResponseNumber).click();
	 AaoHelper.sleep(3);
	 
	 
 }
 
 public void selectRespParty(String text) {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblResponsibleParty);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	 AaoHelper.FindAaoElement(drvr,lblResponsibleParty);
	 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblResponsibleParty).perform();
	 AaoHelper.waitForAaoElement(drvr,lstRespParty);
	 Select select=new Select(lstRespParty);
	 AaoHelper.waitForAaoElement(drvr, lstRespParty);
	 select.selectByVisibleText(text);
	 AaoHelper.sleep(4);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnReopen);
 }
 
 public void selectInternalContact() {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblInternalContact);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 Actions actions = new Actions(drvr);
	 
	 actions.moveToElement(lblInternalContact).doubleClick().build().perform();
	 
	 
 }
 
 public void lookUpContact(String text) {
	 
	 AaoHelper.FindAaoElement(drvr,imgLookUpContact).click();
	 AaoHelper.sleep(10);
	String parentHandler= AaoHelper.switch_to_popup(drvr);
	//new WebDriverWait(drvr,10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
	drvr.switchTo().frame(0);
	 JavascriptExecutor executor = (JavascriptExecutor)drvr;
executor.executeScript("arguments[0].value='"+text+"';",txtLookUpSearch);
//new WebDriverWait(drvr,10).until(ExpectedConditions.elementToBeClickable(btnGo)).click();
AaoHelper.FindAaoElement(drvr,btnGo).click();
drvr.switchTo().defaultContent();
	 drvr.switchTo().frame(1);
	 
	 AaoHelper.sleep(6);
	 selectSearchResult(text);
	 drvr.switchTo().window(parentHandler);
 }
 
 public void  selectSearchResult(String text)
 {
	 boolean b=false;

	 AaoHelper.FindAaoElement(drvr,tblSearchResult);
	 List<WebElement> rows = tblSearchResult.findElements(By.tagName("tr"));
		int rows_count=rows.size();
	
		for (int row = 2; row <= rows_count; row++) {
			
		
		 String usr=drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).getText();
			 if (usr.equalsIgnoreCase(text)){
			 drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).click();
			 b=true;
			 break;
}
 }
 
 }
 
 public String getWeight() {
	 
	 return lblCompletionWeight.getText();
 }
 
 
 public void updateIssuedDate() {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblIssuedDate);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	 AaoHelper.FindAaoElement(drvr,lblIssuedDate);
	 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblIssuedDate).perform();
	 AaoHelper.waitForAaoElement(drvr,lstIssuedDate);
	 String d=AaoHelper.getUpdatedDate();
	 AaoHelper.FindAaoElement(drvr,lstIssuedDate).sendKeys(d);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
	 
 }
 
 public void pressSave() {
	 
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
 }
 
 public void updateCompletionWeight(String s)
 {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblCompletionWeight);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	 AaoHelper.FindAaoElement(drvr,lblCompletionWeight);
	 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblCompletionWeight).perform();
	 AaoHelper.FindAaoElement(drvr,txtlstWeight).clear();
	 AaoHelper.FindAaoElement(drvr,txtlstWeight).sendKeys(s);
	 pressSave();
 }
 
 
 public void clickChangeRecord() {
	 try {
		 AaoHelper.scrollIntoView(drvr,lnkChange);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 AaoHelper.FindAaoElement(drvr, lnkChange).click();
	 
 }
 
 
 public boolean verifyArcDateAutoPopulated() {
	 AaoHelper.FindAaoElement(drvr, lblArcStartDate);
	
	 Date date = new Date();
	 SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
	 String text= sdf.format(date);
	String ele= AaoHelper.FindAaoElement(drvr,lblArcStartDate).getText();
	if (ele.contains(text))
	return true;
	else
		return false;
	
	 
 }
 
 public boolean verifyAaoRecdDateAutoPopulated() {
	 AaoHelper.FindAaoElement(drvr, lblAaoRecdDate);
	 
	
	 Date date = new Date();
	 SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
	 String text= sdf.format(date);
	String ele= AaoHelper.FindAaoElement(drvr,lblAaoRecdDate).getText();
	if (ele.contains(text))
	return true;
	else
		return false;
	
	 
 }
 
 public String returnReceiptAutoPopulated() {
	 
         AaoHelper.FindAaoElement(drvr, lblReceiptNo);
         AaoHelper.AaohighlightElement(drvr, lblReceiptNo);
		 String text=AaoHelper.FindAaoElement(drvr,lblReceiptNo).getText();
			System.out.println(text);
			
		
		return text;
		 
	 }


public String returnFileNoAutoPopulated() {
	 
    AaoHelper.FindAaoElement(drvr, lblFileNo);
    AaoHelper.AaohighlightElement(drvr, lblFileNo);
	 String text=AaoHelper.FindAaoElement(drvr,lblFileNo).getText();
	 System.out.println(text);
	return text;
		 
	 
}

public boolean verifyServiceHistory(String text,String text1)
{
	try {
		AaoHelper.scrollIntoView(drvr, tblServiceHistory);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	AaoHelper.waitForAaoElement(drvr,tblServiceHistory);
	AaoHelper.AaohighlightElement(drvr, tblServiceHistory);
	 
	
		
	AaoHelper.sleep(3);
		String s1=drvr.findElement(By.xpath("//h3[text()='Service Item History']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//tr[2]/td[1]/a")).getText();
		AaoHelper.AaohighlightElement(drvr,drvr.findElement(By.xpath("//h3[text()='Service Item History']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//tr[2]/td[1]/a")));
		String s=drvr.findElement(By.xpath("//h3[text()='Service Item History']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//tr[2]/td[2]/strong[2]")).getText();
		AaoHelper.AaohighlightElement(drvr,drvr.findElement(By.xpath("//h3[text()='Service Item History']/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*/parent::*//tr[2]/td[2]/strong[2]")));
		if((text.equalsIgnoreCase(s))&(text1.equalsIgnoreCase(s1)))
		return true;
		else
			return false;
	 }

public boolean updateFinalDecisionVerifyError(String s) {
	 try {
			
			AaoHelper.scrollIntoView(drvr,lblFinalDecision);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	 
	 AaoHelper.FindAaoElement(drvr,lblFinalDecision);
	 AaoHelper.AaohighlightElement(drvr, lblFinalDecision);
	 Actions actions = new Actions(drvr);
	 AaoHelper.sleep(3);

  actions.doubleClick(lblFinalDecision).build().perform();
	 AaoHelper.sleep(3);
	 drvr.switchTo().activeElement();
	 Select sel=new Select(lstDecision);
	 AaoHelper.sleep(3);
	 AaoHelper.AaohighlightElement(drvr, lstDecision);
	 sel.selectByValue(s);
	 AaoHelper.FindAaoElement(drvr, btnOk).click();
	 AaoHelper.sleep(5);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 
	 AaoHelper.sleep(4);
	    
	    	AaoHelper.AaohighlightElement(drvr, lblHoldError);
	    	AaoHelper.sleep(3);
	    	AaoHelper.FindAaoElement(drvr, btnCancel).click();
	        return true;
}

public void createAfbItem() {
	
	AaoHelper.waitForAaoElement(drvr,btnEdit);
	AaoHelper.FindAaoElement(drvr, lnkRelatedServiceItems).click();
	AaoHelper.waitForAaoElement(drvr, btnCreateAFBItem);
	AaoHelper.AaohighlightElement(drvr, btnCreateAFBItem);
	AaoHelper.FindAaoElement(drvr,btnCreateAFBItem).click();
}

public void closeServiceItem() {
	
	AaoHelper.waitForAaoElement(drvr, btnEdit);
	setFinalDecision("Admin Closed");
	selectRespParty("Issued Decisions - Queue");
}

public void editAfbItem()
{
	AaoHelper.waitForAaoElement(drvr,btnEdit);
	AaoHelper.FindAaoElement(drvr, lnkRelatedServiceItems).click();
	AaoHelper.waitForAaoElement(drvr, btnCreateAFBItem);
	AaoHelper.FindAaoElement(drvr, lnkCloseAfb).click();
}

public void addServiceItemGroup(String s) {
	AaoHelper.waitForAaoElement(drvr, btnEdit);
	AaoHelper.AaohighlightElement(drvr, lblServiceItemGroup);
	AaoHelper.FindAaoElement(drvr, lblServiceItemGroup);
 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblServiceItemGroup).perform();
	 
	 AaoHelper.FindAaoElement(drvr,txtServiceItemGroup).sendKeys(s);
	 AaoHelper.AaohighlightElement(drvr,txtServiceItemGroup);
	 AaoHelper.FindAaoElement(drvr, btnServiceItemGrpOk).click();
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
	 
}

public boolean verifyServiceItemGroup(String s)

{
	 AaoHelper.waitForAaoElement(drvr, lblServiceItemGroup);
	 AaoHelper.AaohighlightElement(drvr, lblServiceItemGroup);
	String text=lblServiceItemGroup.getText();
	if(s.equals(text))
		return true;
	else
		return false;
		
}


public boolean verifyEgregiousPublicSafety(String text) {
	 try {
		AaoHelper.scrollIntoView(drvr, lblEgregiousPublicSafety);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	AaoHelper.waitForAaoElement(drvr, lblEgregiousPublicSafety);
	AaoHelper.AaohighlightElement(drvr, lblEgregiousPublicSafety);
	 String ele= AaoHelper.FindAaoElement(drvr,lblEgregiousPublicSafety).getText();
	 System.out.println(ele);
		if (ele.contains(text))
		return true;
		else
			return false;
}


public boolean verifyFileRequestStatus(String s)

{
	 AaoHelper.waitForAaoElement(drvr, lblFileRequestStatus);
	 AaoHelper.AaohighlightElement(drvr, lblFileRequestStatus);
	String text=lblFileRequestStatus.getText();
	if(s.equals(text))
		return true;
	else
		return false;
		
}


public void selectTriage(String s) {
	 try {
			
			AaoHelper.scrollIntoView(drvr,lblTriageCompletedBy);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	 
	 AaoHelper.FindAaoElement(drvr,lblTriageCompletedBy);
	 AaoHelper.AaohighlightElement(drvr, lblTriageCompletedBy);
	 Actions actions = new Actions(drvr);
	 AaoHelper.sleep(3);

actions.doubleClick(lblTriageCompletedBy).build().perform();
	 AaoHelper.sleep(3);
	
	 Select sel=new Select(lstTriage);
	 AaoHelper.sleep(3);
	if(s.equalsIgnoreCase("None"))
		s="--None--";
	
	 AaoHelper.AaohighlightElement(drvr, lstTriage);
	 AaoHelper.sleep(3);
	 sel.selectByValue(s);
	 AaoHelper.sleep(3);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
}

public void selectBcu(String s) {
	 try {
			
			AaoHelper.scrollIntoView(drvr,lblBcuChecksCompletedBy);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
}
	 
	 AaoHelper.FindAaoElement(drvr,lblBcuChecksCompletedBy);
	 AaoHelper.AaohighlightElement(drvr, lblBcuChecksCompletedBy);
	 Actions actions = new Actions(drvr);
	 AaoHelper.sleep(3);

actions.doubleClick(lblBcuChecksCompletedBy).build().perform();
	 AaoHelper.sleep(3);
	 
	if(s.equalsIgnoreCase("None"))
		s="--None--";
	 Select sel=new Select(lstBcuChecks);
	 AaoHelper.AaohighlightElement(drvr, lstBcuChecks);
	 AaoHelper.sleep(3);
	 sel.selectByValue(s);
	 AaoHelper.sleep(3);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
}

public void updateTriageDate() {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblTriageDate);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	 AaoHelper.FindAaoElement(drvr,lblTriageDate);
	 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblTriageDate).perform();
	 AaoHelper.waitForAaoElement(drvr,txtTriageDate);
	 String d=AaoHelper.getUpdatedDate();
	 AaoHelper.FindAaoElement(drvr,txtTriageDate).sendKeys(d);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
	 
	
}

public void updateBcuDate() {
	 try {
		 AaoHelper.scrollIntoView(drvr,lblBcuCompletedDate);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	 AaoHelper.FindAaoElement(drvr,lblBcuCompletedDate);
	 Actions actions = new Actions(drvr);
	 
	 actions.doubleClick(lblBcuCompletedDate).perform();
	 AaoHelper.waitForAaoElement(drvr,txtBcuCompletedDate);
	 String d=AaoHelper.getUpdatedDate();
	 AaoHelper.FindAaoElement(drvr,txtBcuCompletedDate).sendKeys(d);
	 AaoHelper.FindAaoElement(drvr, btnSave).click();
	 AaoHelper.waitForAaoElement(drvr, btnEdit);
	 
	
}

}


	    
	        
	       



 
 
			 
			 
	 


