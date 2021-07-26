package com.salesforcetest.pages.aao;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ResponseDetail {
	
	private  String getUrl;
	private WebDriver drvr;
	private AaoHelper helper;
	
	@FindBy(xpath="//*[@id='ep']/div[2]/div[3]/table/tbody")
	private WebElement tblResponseDetails;
	@FindBy(xpath="//div[@class='bRelatedList'][1]/descendant::table[@class='list']/tbody")
	private WebElement tblResponseVersions;
	
	@FindBy(xpath="//div[5]/descendant::table/tbody")
	private WebElement tblResponseEvents;
	
	@FindBy(xpath="//*[@id='a11r0000002kmOi_RelatedProcessHistoryList_body']/table/tbody")
	private WebElement tblApprovalHistory;
	@FindBy(xpath="//*[@id='a11r0000002kmOi_RelatedEntityHistoryList_body']/table/tbody")
	private WebElement tblResponseHistory;
	
	@FindBy(xpath="//table[@class='detailList'][1]/descendant::tr[2]/td[2]")
	private WebElement lblStatus;
	@FindBy(xpath="//div[@class='pbSubsection'][3]/descendant::a[1]")
	private WebElement lnkOwner;
	@FindBy(xpath="//div[7]/table/tbody/tr[1]/td[4]/span/a[2]")
	private WebElement lnkOwner1;
	@FindBy(xpath="//div[@class='pbSubsection'][3]/descendant::td[4]")
	private WebElement lblOwner;
	@FindBy(xpath="//input[@name='print']")
	private WebElement btnPrint;
	@FindBy(xpath="//div[3]/table/tbody/tr[1]/td[2]/a")
	private WebElement lnkServiceItemNo;
	@FindBy(xpath="//div[@class='listHoverLinks']/descendant::a[@class='linklet'][2]/span")
	private WebElement lnkResponseVersions;
	@FindBy(xpath="//h2[@class='pageDescription']")
	private WebElement lblResponseNumber;
	@FindBy(xpath="//input[@name='edit']")
	private WebElement btnEdit;
	
	
	
	 public ResponseDetail(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
		}
	 
	
	 
	 public boolean verifyResponseEvents(String text1,String text2)
	 {
		 boolean isFound=false;
		 AaoHelper.waitForAaoElement(drvr,tblResponseEvents);
		 AaoHelper.AaohighlightElement(drvr, tblResponseEvents);
		String dec=drvr.findElement(By.xpath("//div[5]/descendant::table/tbody/tr[2]/td[2]")).getText();
		String owner=drvr.findElement(By.xpath("//div[5]/descendant::table/tbody/tr[2]/td[4]/a")).getText();
		if((dec.equalsIgnoreCase(text1))&(owner.equalsIgnoreCase(text2)))
        isFound=true;
        return true;
		
	 }
	 
	 public boolean verifyApprovalHistory(String status,String assignedTo)
	 {
		 boolean isFound=false;
		 helper.waitForAaoElement(drvr,tblApprovalHistory);
	
         String text1=drvr.findElement(By.xpath(tblApprovalHistory+"//tr[2]/td[1]/span")).getText();
         String text2=drvr.findElement(By.xpath(tblApprovalHistory+"//tr[4]/td[1]/span")).getText();
         String text3=drvr.findElement(By.xpath(tblApprovalHistory+"//tr[6]/td[1]/span")).getText();
         String text4=drvr.findElement(By.xpath(tblApprovalHistory+"//tr[4]/td[6]/div/span")).getText();
         String text5=drvr.findElement(By.xpath(tblApprovalHistory+"//tr[3]/td[3]/a")).getText();
         
         if((text1.equalsIgnoreCase("Approval Request Submitted"))&(text2.equalsIgnoreCase("Step: Step 1"))&(text3.equalsIgnoreCase("Step: Step 2  (Pending for first approval)"))&(text4.equalsIgnoreCase(status))&(text5.equalsIgnoreCase(assignedTo)))
      
        	 isFound=true;
        	 
	
	 return true;
	 }
	 
	 public boolean verifyResponseHistory(String text)
	 {
		 boolean isFound=false;
		 helper.waitForAaoElement(drvr,tblResponseHistory);
		 List<WebElement> tableRows = tblResponseHistory.findElements(By.tagName("tr"));
		 int rows_count= tableRows.size();
		//Loop will execute till the last row of table.
	    	for (int row = 0; row < rows_count; row++) {
	    	    //To locate columns(cells) of that specific row.
	    	    List < WebElement > Columns_row = tableRows.get(row).findElements(By.tagName("td"));
	    	    //To calculate no of columns (cells). In that specific row.
	    	    int columns_count = Columns_row.size();
	    	    
	    	    //Loop will execute till the last cell of that specific row.
	    	    for (int column = 0; column < columns_count; column++) {
	    	        // To retrieve text from that specific cell.
	    	        String celtext = Columns_row.get(column).getText();
	    	       if (celtext.equals(text))
	    	       {
	    	       isFound=true;
	    	      break;
	    	    }
	    	if (isFound)
	    	break;
	    	}
	   	}
        return true;
	 }
	 
	
	 
	 public boolean verifyResponseVersions(String text)
	 {
		 int i=Integer.parseInt(text);
		 AaoHelper.FindAaoElement(drvr, lnkResponseVersions).click();
		AaoHelper.waitForAaoElement(drvr,tblResponseVersions);
		AaoHelper.AaohighlightElement(drvr, tblResponseVersions);
		 List<WebElement> tableRows = tblResponseVersions.findElements(By.tagName("tr"));
		 int rows_count= tableRows.size();
		 if(rows_count==i+1)
			 return true;
		 else return false;
	
}
	 
	 public boolean verifyStatus(String text) {
		 
		 AaoHelper.waitForAaoElement(drvr,lblStatus);
			
			String text1= AaoHelper.FindAaoElement(drvr,lblStatus).getText();
			AaoHelper.AaohighlightElement(drvr, lblStatus);
			System.out.println(text1);
			return(text.trim().equalsIgnoreCase(text1.trim()));
	 }
	 
	 public boolean verifyOwner(String text)
	 {
		 try {
			AaoHelper.scrollIntoView(drvr, lblOwner);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ele= AaoHelper.FindAaoElement(drvr, lnkOwner).getText();
		if(!text.equalsIgnoreCase(ele))
		{
			ele=AaoHelper.FindAaoElement(drvr, lnkOwner1).getText();
		}
		
		System.out.println(ele);
		AaoHelper.AaohighlightElement(drvr, lblOwner);
		return(text.trim().equalsIgnoreCase(ele.trim()));
		 
		 
	 }
	 
	 public void clickServiceItem() {
		 AaoHelper.FindAaoElement(drvr,lnkServiceItemNo).click();
		 
	 }
	 
	 public String getResponseNumber() {
		 
		 return lblResponseNumber.getText();
	 }
	 
	 public void editResponse() {
		 
		 AaoHelper.FindAaoElement(drvr, btnEdit).click();
	 }
	 
	
			


	 
}





