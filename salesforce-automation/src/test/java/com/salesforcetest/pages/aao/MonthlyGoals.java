package com.salesforcetest.pages.aao;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class MonthlyGoals {
	
	
	private  WebDriver drvr;
	
	@FindBy(xpath="//article[@class='slds-card']/div/div[3]/button[2]")
	private WebElement btnNextPage;
	@FindBy(xpath="//article[@class='slds-card']/div/div[3]/button[3]")
	private WebElement btnSetGoal;
	@FindBy(xpath="//article[@class='slds-card']/div/div[1]/button[3]")
	private WebElement btnSetGoal1;
	@FindBy(xpath="//article[@class='slds-card']/div/div[3]/button[1]")
	private WebElement btnPreviousPage;
	@FindBy(xpath="//div[@class='cMonthlyGoals']//article/div/table")
	private WebElement tblGoals;
	@FindBy(xpath="//div[3]/div/div/div/div/article/div/table")
	private WebElement tblGoals1;
	@FindBy(xpath="//select[@class='slds-select'][@name='FiscalYear']")
	private WebElement lstFiscalYear;
	private int i,i1;
	String tableGoals="//div[@class='cMonthlyGoals']//article/div/table";
	
	private String textFound;
	public String d;
	
	 public MonthlyGoals(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
					
			
		}
	 
	 public int getOfficerRow(String s) {
		 
		 boolean isFound=false;
		 int storedi = 0;
		 String text1 = null;
		WebElement ele;
	   
	   
		
	   AaoHelper.waitForAaoElement(drvr, tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
		
	
		
		 while(btnNextPage.isEnabled()) {
		 List<WebElement> tableRows = tblGoals.findElements(By.tagName("tr"));
		
		 int rows_count= tableRows.size();
		//Loop will execute till the last row of table.
	    	for (int row = 1; row < rows_count; row++) {
	    		 
	    		try {
	    			AaoHelper.sleep(2);
	    			 ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+row+"]/td[2]"));
	    			
	    		 text1=ele.getText();
	    		
	    		}
	    		catch(org.openqa.selenium.StaleElementReferenceException ex)
	    		{
	    			ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+row+"]/td[2]"));
	    			text1=ele.getText();
		    		 System.out.println(text1);
	    		}
	    		
	    		 if(text1.equalsIgnoreCase(s)) {
	    		try {
					AaoHelper.scrollIntoView(drvr, ele);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	 
	    		AaoHelper.AaohighlightElement(drvr, ele);
	    	        	 isFound=true;
	    	        	 storedi=row;
	    	        	 break;
	
	    	}
	    		

	    	}
	    	if(isFound==true)
	    		break;
	    	else
	    		AaoHelper.FindAaoElement(drvr, btnNextPage).click();
	
		 }
		 return storedi;
}
	 
public String getInitialActual(String s)	 
{
	AaoHelper.switch_to_tab(drvr, 1);
	boolean isMatch=false;
	AaoHelper.waitForAaoElement(drvr,tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
 d=AaoHelper.getCurrentMonth();
	i=getOfficerRow(s);
	 String initialVal = null;
	
	 

	//Loop will execute till the last row of table.
   	for (int row = 3; row <= 14; row++) {
   		
   		 String text1=drvr.findElement(By.xpath(tableGoals+"/"+"thead/tr/th["+row+"]/span")).getText();
   		 if(text1.equalsIgnoreCase(d)) {
   		 initialVal=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[3]/div/input")).getAttribute("value");
   		 System.out.println(initialVal);
   		isMatch=true;
   		break;
	
}
   	}
   		 return initialVal;
   	}
	 
public boolean verifyActual(String s,String weight) {
	AaoHelper.switch_to_tab(drvr, 1);
	boolean isMatch=false;
	 String initialVal,finalVal;
	AaoHelper.waitForAaoElement(drvr,tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
	 d=AaoHelper.getCurrentMonth();
	
	// initialVal=getInitialActual(s);
	// if(i==0)
	 i=getOfficerRow(s);
	
	 int actual;
	 String[] temp=weight.split("\\.");
	 
	
	 System.out.println(temp[0]);

	//Loop will execute till the last row of table.
   	for (int row = 3; row <= 14; row++) {
   		
   		 String text1=drvr.findElement(By.xpath(tableGoals+"/"+"thead/tr/th["+row+"]/span")).getText();
   		 if(text1.equalsIgnoreCase(d)) {
   		// initialVal=drvr.findElement(By.xpath("//div[2]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td["+row+"]/lightning-input[3]/div/input")).getAttribute("value");
   		// AaoHelper.sleep(2);
   		// drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).clear();
   	// drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).sendKeys("3");
   	//	AaoHelper.FindAaoElement(drvr, btnSetGoal).click();
   			 WebElement ele1=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]"));
   			try {
				AaoHelper.scrollIntoView(drvr, ele1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
   		 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
   		 AaoHelper.sleep(2);
   		 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
   		try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
   		
   		 AaoHelper.sleep(2);
   		 WebElement ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[3]/div/input"));
   		 
   		
   		finalVal=ele.getAttribute("value");
   		AaoHelper.AaohighlightElement(drvr, ele);
   		// if((initialVal.isBlank()| initialVal.equals("0"))&(finalVal.equalsIgnoreCase(temp[0])) ) {
   		if((finalVal.equalsIgnoreCase(temp[0])) ) {
   	        	 isMatch=true;
   	        	 break;

   	}
   		// else
   		// {
   		//	 int i1= Integer.parseInt(initialVal);
   		//	int f=Integer.parseInt(finalVal);
   			 
   		//	 actual=f-i1;
   		//	 System.out.println(actual);
   			 
   		//	if(Integer.toString(actual).equalsIgnoreCase(temp[0]))	 
   			//	isMatch=true;
   		//	break;
   		// }

   	}
  
	
}
   	if(isMatch)
		return true;
	else
		return false;
}


public boolean verifyActualForUpdatedDate(String s,String weight) {
	AaoHelper.switch_to_tab(drvr, 1);
	boolean isMatch=false;
	 String initialVal,finalVal,text1,currentmonthvalue;
	AaoHelper.waitForAaoElement(drvr, tblGoals);
	
	 String updated=AaoHelper.getUpdatedMonth();
	 System.out.println(updated);
	 String current=AaoHelper.getCurrentMonth();
	 i=getOfficerRow(s);
	// initialVal=getInitialActual(s);
	 String[] temp=weight.split("\\.");
	 int actual;
	
	 WebElement ele;
	 WebElement ele2;
	 WebElement elecurrent;
	 WebElement ele3;
	 WebElement eleupdated;
	 int updatedmth=AaoHelper.getMonthNumber(updated);
	
	 int currentmth=AaoHelper.getMonthNumber(current);
	
	 System.out.println(temp[0]);

	if((updatedmth==3)|(updatedmth==4)|(updatedmth==5))
	{
        selectFiscalYear();
	        try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
		  			
			 
		 AaoHelper.sleep(3);
		WebElement  elenextyear=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+updatedmth+"]/lightning-input[3]/div/input"));
		 try {
				AaoHelper.scrollIntoView(drvr, elenextyear);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 String nextyearvalue=elenextyear.getAttribute("value");
		 AaoHelper.sleep(5);
		 selectCurrentYear();
		 AaoHelper.sleep(3);
		 WebElement elecurrentyear=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+currentmth+"]/lightning-input[3]/div/input"));
   		
   	 try {
			AaoHelper.scrollIntoView(drvr, elecurrentyear);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
	 AaoHelper.sleep(2);
	 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
		 AaoHelper.sleep(3);
		 WebElement elecurrentyear1=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+currentmth+"]/lightning-input[3]/div/input"));
		 String currentyearvalue=elecurrentyear1.getAttribute("value");
		 if((nextyearvalue.equalsIgnoreCase(temp[0]))&currentyearvalue.equals("0") ) {
	        	 isMatch=true;
	        	
		 
	}
	}
		 else {
   	        try {
				AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			
   			 
   		  			
   			 
   		 AaoHelper.sleep(3);
   		 
   		  			
   		
   		
   	 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
		 AaoHelper.sleep(2);
		 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
   		 AaoHelper.sleep(3);
   		 elecurrent=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+currentmth+"]/lightning-input[3]/div/input"));
			eleupdated=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+updatedmth+"]/lightning-input[3]/div/input"));
   			
   			 try {
 				AaoHelper.scrollIntoView(drvr, eleupdated);
 			} catch (InterruptedException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
   			 
   		  try {
				AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   			currentmonthvalue=elecurrent.getAttribute("value");
   		 finalVal=eleupdated.getAttribute("value");
   		 System.out.println(finalVal);
   		 System.out.println(currentmonthvalue);
   		 AaoHelper.AaohighlightElement(drvr, elecurrent);
   		 AaoHelper.AaohighlightElement(drvr, eleupdated);
   		// if((initialVal.isBlank()|initialVal.equals("0"))&(finalVal.equalsIgnoreCase(temp[0]))&previousmonthvalue.equals("0") ) {
   		if((finalVal.equalsIgnoreCase(temp[0]))&currentmonthvalue.equals("0") ) {
   	        	 isMatch=true;
   	        	

   	}
   		
		 }	  	
  
	

   	if(isMatch)
		return true;
	else
		return false;
}


public boolean verifyActualForUpdatedContact(String weight,String s)
{
	AaoHelper.switch_to_tab(drvr, 1);
	boolean isMatch=false;
	 String updated=AaoHelper.getUpdatedMonth();
	 int updatedmth=AaoHelper.getMonthNumber(updated);
	 i=getOfficerRow(s);
	 if (weight.equalsIgnoreCase("0"))
		 weight="0.0";
	
	 String[] temp=weight.split("\\.");
	 System.out.println(temp[0]);
	 if((updatedmth==3)|(updatedmth==4)|(updatedmth==5)) {
		 AaoHelper.waitForAaoElement(drvr, tblGoals);
		 selectFiscalYear();
		 AaoHelper.sleep(2);
			
			
		 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
			 AaoHelper.sleep(2);
			 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
			 AaoHelper.sleep(2);
			  try {
					AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		WebElement ele1=	drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+updatedmth+"]/lightning-input[3]/div/input"));
		String val=ele1.getAttribute("value");
		AaoHelper.AaohighlightElement(drvr, ele1);

		if( val.equals(temp[0]))
		isMatch=true;
	 }
	 else{
		 AaoHelper.waitForAaoElement(drvr, tblGoals);
	
	 
	// try {
		//	AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
	//	} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		  			
		 
	 AaoHelper.sleep(2);
	 	  			
		
 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
	 AaoHelper.sleep(2);
	 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
	 AaoHelper.sleep(2);
	  try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
WebElement ele=	drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+updatedmth+"]/lightning-input[3]/div/input"));
String val=ele.getAttribute("value");
AaoHelper.AaohighlightElement(drvr, ele);

if( val.equals(temp[0]))
isMatch=true;
	 }
if(isMatch)
	return true;
else return false;
	

}

public boolean verifySumOfExpectations(String s,String val)
{
	String s1;
    
	
	for (int row = 3; row <= 14; row++) {
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).clear();
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).sendKeys(val);
		
	}
	
	AaoHelper.FindAaoElement(drvr, btnSetGoal).click();
	AaoHelper.sleep(3);
	WebElement ele=drvr.findElement(By.xpath("//div[2]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td[15]/lightning-input[1]/div/input"));
	s1=drvr.findElement(By.xpath("//div[2]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td[15]/lightning-input[1]/div/input")).getAttribute("value");
	  try {
			AaoHelper.scrollIntoView(drvr,ele);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	  try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
			
	int foo = Integer.parseInt(val);
	int sum=foo*12;
	String finalSum=Integer.toString(sum);
	if(s1.equals(finalSum))
		return true;
	else return false;
	
}

public boolean verifySumOfGoals(String s,String val)
{
	String s1;
	
	//i=getOfficerRow(s);
	for (int row = 3; row <= 14; row++) {
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[2]/div/input")).clear();
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[2]/div/input")).sendKeys(val);
		
	}
	
	AaoHelper.FindAaoElement(drvr, btnSetGoal).click();
	AaoHelper.sleep(3);
	WebElement ele=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td[15]/lightning-input[2]/div/input"));
	s1=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td[15]/lightning-input[2]/div/input")).getAttribute("value");
	 try {
			AaoHelper.scrollIntoView(drvr,ele);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	 try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	int foo = Integer.parseInt(val);
	int sum=foo*12;
	String finalSum=Integer.toString(sum);
	if(s1.equals(finalSum))
		return true;
	else return false;
	
}

public boolean verifySingleRecord(String s)
{
	int expectedcount=1;
	AaoHelper.waitForAaoElement(drvr, tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
	 List<WebElement> tableRows = tblGoals.findElements(By.tagName("tr"));
		
	 int rows_count= tableRows.size();
	 if((rows_count-1)==expectedcount)
		 return true;
		 else
			 return false;
	
}

public boolean verifyNotEditable() {
	
	for (int row = 3; row <= 17; row++) {
		WebElement ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr/td["+row+"]/lightning-input[1]/div/input"));
	
		
		if(ele.isEnabled())
			return false;
		
	}
	
	return true;
}


public boolean verifyEditable() {
	boolean a=false,b=false;
	for (int row = 3; row <= 14; row++) {
		WebElement ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr/td["+row+"]/lightning-input[2]/div/input"));
	
		
		if(ele.isEnabled())
			a=true;
		
	}
	
	WebElement ele1=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr/td[16]/lightning-input[2]/div/input"));
	if(ele1.isEnabled())
		b=true;
	if((a) & (b))
		return true;
	else
	
	return false;
}

public boolean verifyMultipleRecords() {
	AaoHelper.waitForAaoElement(drvr, tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
	 
	 List<WebElement> tableRows = tblGoals.findElements(By.tagName("tr"));
		
	 int rows_count= tableRows.size();
	 if(rows_count>2)
		 return true;
		 else
			 return false;
	
	
}
public boolean updateExpectationsVerifySum() {
	String s1;
	boolean match=false;
	
    AaoHelper.waitForAaoElement(drvr,tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
   

    
	//i=getOfficerRow(s);
	for (int row = 3; row <= 14; row++) {
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr[1]/td["+row+"]/lightning-input[1]/div/input")).clear();
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr[1]/td["+row+"]/lightning-input[1]/div/input")).sendKeys("3");
		
	}
	
	AaoHelper.FindAaoElement(drvr, btnSetGoal1).click();
	AaoHelper.sleep(3);
	WebElement ele=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr[1]/td[15]/lightning-input[1]/div/input"));
	s1=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr[1]/td[15]/lightning-input[1]/div/input")).getAttribute("value");
	 try {
			AaoHelper.scrollIntoView(drvr,ele);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	int foo = Integer.parseInt("3");
	int sum=foo*12;
	String finalSum=Integer.toString(sum);
	if((s1.equals(finalSum)))
	return true;

	else   return false;
}


public boolean updateGoalsVerifySum() {
	String s1;
	boolean match=false;
	
    AaoHelper.waitForAaoElement(drvr,tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
   

    
	//i=getOfficerRow(s);
	for (int row = 3; row <= 14; row++) {
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr[1]/td["+row+"]/lightning-input[2]/div/input")).clear();
		drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr[1]/td["+row+"]/lightning-input[2]/div/input")).sendKeys("6");
		
	}
	
	AaoHelper.FindAaoElement(drvr, btnSetGoal1).click();
	AaoHelper.sleep(3);
	WebElement ele=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr[1]/td[15]/lightning-input[2]/div/input"));
	s1=drvr.findElement(By.xpath("//div[3]/div/div/div/div/article/div/table/tbody/tr[1]/td[15]/lightning-input[2]/div/input")).getAttribute("value");
	 try {
			AaoHelper.scrollIntoView(drvr,ele);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	int foo = Integer.parseInt("6");
	int sum=foo*12;
	String finalSum=Integer.toString(sum);
	if((s1.equals(finalSum)))
	return true;

	else   return false;
}

public boolean verifyActualUpdated(String s,String weight) {
	AaoHelper.switch_to_tab(drvr, 1);
	boolean isMatch=false;
	 String initialVal,finalVal;
	AaoHelper.waitForAaoElement(drvr,tblGoals);
	 AaoHelper.AaohighlightElement(drvr, tblGoals);
	 d=AaoHelper.getUpdatedMonth();
	
	// initialVal=getInitialActual(s);
	 if(i==0)
	 i=getOfficerRow(s);
	
	 int actual;
	 String[] temp=weight.split("\\.");
	 
	
	 System.out.println(temp[0]);

	//Loop will execute till the last row of table.
   	for (int row = 3; row <= 14; row++) {
   		
   		 String text1=drvr.findElement(By.xpath(tableGoals+"/"+"thead/tr/th["+row+"]/span")).getText();
   		 if(text1.equalsIgnoreCase(d)) {
   		// initialVal=drvr.findElement(By.xpath("//div[2]/div/div/div/div/article/div/table/tbody/tr["+i+"]/td["+row+"]/lightning-input[3]/div/input")).getAttribute("value");
   		// AaoHelper.sleep(2);
   		// drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).clear();
   	// drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[1]/div/input")).sendKeys("3");
   	//	AaoHelper.FindAaoElement(drvr, btnSetGoal).click();
   			 WebElement ele1=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]"));
   			try {
				AaoHelper.scrollIntoView(drvr, ele1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
   		 AaoHelper.FindAaoElement(drvr, btnNextPage).click();
   		 AaoHelper.sleep(2);
   		 AaoHelper.FindAaoElement(drvr, btnPreviousPage).click();
   		try {
			AaoHelper.scrollIntoView(drvr, drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td[2]")));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
   		
   		 AaoHelper.sleep(2);
   		 WebElement ele=drvr.findElement(By.xpath(tableGoals+"/"+"tbody/tr["+i+"]/td["+row+"]/lightning-input[3]/div/input"));
   		 
   		
   		finalVal=ele.getAttribute("value");
   		AaoHelper.AaohighlightElement(drvr, ele);
   		// if((initialVal.isBlank()| initialVal.equals("0"))&(finalVal.equalsIgnoreCase(temp[0])) ) {
   		if((finalVal.equalsIgnoreCase(temp[0])) ) {
   	        	 isMatch=true;
   	        	 break;

   	}
   		// else
   		// {
   		//	 int i1= Integer.parseInt(initialVal);
   		//	int f=Integer.parseInt(finalVal);
   			 
   		//	 actual=f-i1;
   		//	 System.out.println(actual);
   			 
   		//	if(Integer.toString(actual).equalsIgnoreCase(temp[0]))	 
   			//	isMatch=true;
   		//	break;
   		// }

   	}
  
	
}
   	if(isMatch)
		return true;
	else
		return false;
}

public void selectFiscalYear() {
	AaoHelper.waitForAaoElement(drvr, lstFiscalYear);
	try {
		AaoHelper.scrollIntoView(drvr, lstFiscalYear);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		String year=AaoHelper.getNextYear();
		Select sel=new Select(lstFiscalYear);
		AaoHelper.sleep(3);
		sel.selectByValue(year);
	
			
}


public void selectCurrentYear() {
	AaoHelper.waitForAaoElement(drvr, lstFiscalYear);
	try {
		AaoHelper.scrollIntoView(drvr, lstFiscalYear);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
		String year=AaoHelper.getCurrentYear();
		Select sel=new Select(lstFiscalYear);
		AaoHelper.sleep(3);
		sel.selectByValue(year);
	
			
}
	
}







