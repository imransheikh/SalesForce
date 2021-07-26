package com.salesforcetest.pages.aao;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class TrackingRecord {
	
    private WebDriver drvr;
	@FindBy(xpath="//div[1]//td[2]/div[3]/a[6]/span")
	private WebElement lnkTrackings;
	@FindBy(xpath="//div[10]//div[2]/table/tbody")
	
	private WebElement tblTrackingRecord;
	@FindBy(xpath="//div[11]/div[1]/div/div[2]/table/tbody")
	private WebElement tblTrackingRecord1;
	@FindBy(xpath="//div[4]/div[2]/div[2]/table")
	private WebElement tblTrackingDetail;
	@FindBy(linkText="Show 1 more �")
	private WebElement lnkMore;
	
	public TrackingRecord(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.drvr=driver;
	}
		
	public boolean verifyTrackingRecord(String startMileStone,String endMileStone,String startArc,String endArc,String days) {
		 
	     boolean b=false;
	     try {
	    	 WebElement ele= AaoHelper.FindAaoElement(drvr, lnkTrackings);
			 JavascriptExecutor executor = (JavascriptExecutor)drvr;
			 executor.executeScript("arguments[0].click();", ele);
	     }catch(StaleElementReferenceException Exception)
	     {
	    	 drvr.navigate().refresh();
	    	WebElement e= AaoHelper.FindAaoElement(drvr,lnkTrackings);
	    	e.click();
	     }
	AaoHelper.waitForAaoElement(drvr,tblTrackingRecord);
	AaoHelper.AaohighlightElement(drvr, tblTrackingRecord);
		 if(startMileStone.isEmpty())
			 startMileStone=" ";
		 if(endMileStone.isEmpty())
			 endMileStone=" ";
		 if(startArc.isEmpty())
			 startArc=" ";
		 if(endArc.isEmpty())
			 endArc=" ";
		 if(days.isEmpty())
			 days=" ";
		 
		
			
			 WebElement ele=drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[3]/th/a"));
			 AaoHelper.waitForAaoElement(drvr,ele);
			 try {
				AaoHelper.scrollIntoView(drvr, ele);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 JavascriptExecutor executor = (JavascriptExecutor)drvr;
			 executor.executeScript("arguments[0].click();", ele);
			 
			// String strtdate=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[4]/td[2]")).getText();
			 String sm=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[2]/td[4]")));
			
			 String em=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")));
			 String strtarc=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")));
			 String endarc=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[5]/td[4]")).getText();
			 AaoHelper.AaohighlightElement(drvr,drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[5]/td[4]")));
			 String d=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[7]/td[2]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[7]/td[2]")));
			 if( (sm.equalsIgnoreCase(startMileStone))&(em.equalsIgnoreCase(endMileStone))&(strtarc.equalsIgnoreCase(startArc))&(endarc.equalsIgnoreCase(endArc))&(d.equalsIgnoreCase(days)))
		     b=true;
			 if(b)
				 return true;
			 else return false;
			 
		
	 }
	
	public boolean verifyStartDate() {
		 String d=AaoHelper.selectDate();
		 
		 String strtdate=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[4]/td[2]")).getText();
		 if(strtdate.equalsIgnoreCase(d))
			 return true;
		 else return false;
		 
	 }
	
	public boolean verifyTrackingRecordRespParty(String sm,String em) {
		boolean  b=false;
			 AaoHelper.FindAaoElement(drvr,lnkTrackings).click();
		AaoHelper.waitForAaoElement(drvr,tblTrackingRecord);
		AaoHelper.AaohighlightElement(drvr, tblTrackingRecord);
		 if(sm.isEmpty())
			 sm=" ";
		 if(em.isEmpty())
			 em=" ";
		 int count=0;

		 List<WebElement> lnks= drvr.findElements(By.linkText("Show 1 more �"));
		 if (!lnks.isEmpty()) 
		     lnks.get(0).click();
		 List<WebElement> rows = tblTrackingRecord.findElements(By.tagName("tr"));
		
		 
		 int rows_count=rows.size();
		 for (int row = 2; row <=rows_count; row++) {
			AaoHelper.sleep(5);
		
			 String start=drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + rows_count + "]/td[5]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + rows_count + "]/td[5]")));
			 String end=drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + row + "]/td[6]")).getText();
			 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + row + "]/td[6]")));
		if((start.equalsIgnoreCase(sm))&(end.equalsIgnoreCase(em)))
		{
			b=true;
			break;
		}
}
		 if(b)
			 return true;
		 else return false;
}
	


	public boolean verifyTrackingRecordDecision(String sm) {
		boolean  b=false;
			WebElement ele= AaoHelper.FindAaoElement(drvr, lnkTrackings);
			 JavascriptExecutor executor = (JavascriptExecutor)drvr;
			 executor.executeScript("arguments[0].click();", ele);
		AaoHelper.waitForAaoElement(drvr,tblTrackingRecord);
		AaoHelper.AaohighlightElement(drvr, tblTrackingRecord);
		 if(sm.isEmpty())
			 sm=" ";
		
		 int c=0;
		
			 List<WebElement> lnks= drvr.findElements(By.linkText("Show 1 more �"));
			 if (lnks.size()>0) {
				 AaoHelper.sleep(3);
			     lnks.get(0).click();
			 }
		
		 List<WebElement> rows = tblTrackingRecord.findElements(By.tagName("tr"));
		 int rows_count=rows.size();
		 String start=drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + rows_count + "]/td[5]")).getText();
		 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + rows_count + "]/td[5]")));
		 System.out.println(start);
		// for (int row = 2; row <=rows_count; row++) {
		//	AaoHelper.sleep(3);
		
			 
			// String end=drvr.findElement(By.xpath("//div[10]/div[1]/div/div[2]/table/tbody/tr[" + row + "]/td[6]")).getText();
			// System.out.println(end);
		if((start.equalsIgnoreCase(sm)))
		{
			b=true;
			
		}
		 

		 if(b)
			 return true;
		 else return false;
}

			 
	
public boolean verifyRightBookendTracking(String a,String b)
{
	boolean found= false;
	WebElement ele= AaoHelper.FindAaoElement(drvr,lnkTrackings);
	JavascriptExecutor executor = (JavascriptExecutor)drvr;
	 executor.executeScript("arguments[0].click();", ele);
		AaoHelper.waitForAaoElement(drvr,tblTrackingRecord1);
		AaoHelper.AaohighlightElement(drvr, tblTrackingRecord1);
		 List<WebElement> lnks= drvr.findElements(By.linkText("Show 1 more �"));
		 if (!lnks.isEmpty()) 
		     lnks.get(0).click();
		 List<WebElement> rows = tblTrackingRecord1.findElements(By.tagName("tr"));
		
		 
		 int rows_count=rows.size();
		 WebElement e=drvr.findElement(By.xpath("//div[11]/div[1]/div/div[2]/table/tbody/tr["+ rows_count +"]/th/a"));
		 AaoHelper.waitForAaoElement(drvr,e);
		 AaoHelper.AaohighlightElement(drvr, e);
		 
		 
		 executor.executeScript("arguments[0].click();", e);
		 AaoHelper.sleep(3);
		 String em=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")).getText();
		 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[3]/td[4]")));
		 String bookend=drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[8]/td[4]")).getText();
		 AaoHelper.AaohighlightElement(drvr, drvr.findElement(By.xpath("//div[4]/div[2]/div[2]/table/tbody/tr[8]/td[4]")));
		 if( em.equalsIgnoreCase(a)&bookend.equalsIgnoreCase(b))
		    found=true;
			 if(found)
				 return true;
			 else return false;
			 
		
	 }
}
		 

		