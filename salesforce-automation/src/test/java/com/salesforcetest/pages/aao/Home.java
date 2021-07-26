package com.salesforcetest.pages.aao;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.manager.FileManager;
import com.salesforcetest.pages.aao.utils.AaoHelper;


public class Home {
	
	private WebDriver drvr;
	//private AaoHelper AaoAaoHelper;

	@FindBy(id="Users_font")
	private WebElement lnkManageUsers;
	@FindBy(id="ManageUsers_font")
	private WebElement lnkUsers;
	@FindBy(id="fcf")
	private WebElement lstUsers;
	 @FindBy(xpath="//table[@class='list']/tbody")
    private  WebElement tblAaoUsers;
	 @FindBy(linkText="Next Page>")
	 private WebElement lnkNextPage;
	 @FindBy(tagName="a")
	 private List<WebElement> lnkAaoUsers;
	 @FindBy(xpath="//div[@id='cruc_notify_buttons']/input[@name='ok']")
	 private List<WebElement> btnOk;
	
	 
	 
	 
	 public Home(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
		//	AaoAaoHelper=new AaoHelper();
			
		}
	 
	
		
	public void clickManageUsers()
	{
		AaoHelper.sleep(4);
		
		try {
			AaoHelper.scrollIntoView(drvr, lnkManageUsers);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AaoHelper.sleep(5);
		AaoHelper.FindAaoElement(drvr,lnkManageUsers).click();
		try {
			drvr.switchTo().alert().accept();
		}catch(Exception e) {
		}
	}
	
	public void clickUsers()
	{
		AaoHelper.sleep(3);
		//if (btnOk.size()>0)
		//	btnOk.get(0).click();
		//try {
		//	AaoHelper.scrollIntoView(drvr, lnkUsers);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		//AaoHelper.FindAaoElement(drvr,lnkManageUsers).click();
		//AaoHelper.waitForAaoElement(drvr, lnkUsers);
		 JavascriptExecutor executor = (JavascriptExecutor)drvr;
		 executor.executeScript("arguments[0].click();", lnkUsers);
		//AaoHelper.FindAaoElement(drvr,lnkUsers).click();
	}
	
	public void selectAllAaoUsers()
	{
		AaoHelper.waitForAaoElement(drvr, lstUsers);
		Select select=new Select(lstUsers);
		select.selectByVisibleText("AAO Users (Active)");
	}
	
	public void searchAaoUser(String user)
	{
		//int lnkCount=lnkAaoUsers.size();
		
	//for(int i=0;i<lnkCount;i++)
	//	{
		//	if(lnkAaoUsers.get(i).getText().contains(user) )
	//	{
			//	lnkAaoUsers.get(i).click();
		//	break;
	//	}
		//	else
		//		 AaoHelper.FindAaoElement(drvr, lnkNextPage).click();
	//}
//	}
//}
		//user=FileManager.getInstance().getReader().getUser();
		String u;
		boolean b= false;
		
		 u=user.replaceAll("\\s+","");
		String usr1 = null,reversedstring = null,actualstring=null;
		 AaoHelper.waitForAaoElement(drvr,tblAaoUsers);
		 
		
		 
		 
		for(int j=2;j<=25;j++)
		{
			List<WebElement> rows = tblAaoUsers.findElements(By.tagName("tr"));
		int rows_count=rows.size();
	
		for (int row = 2; row < rows_count; row++) {
			
		
		 String usr=drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).getText();
			 if (usr.contains(","))
					 {
				String usr1array[] = usr.split(",");
				 reversedstring=usr1array[1]+usr1array[0];
				 actualstring=usr1array[0]+usr1array[1];
				 actualstring=actualstring.replaceAll("\\s+","");
				 reversedstring=reversedstring.replaceAll("\\s+","");
			       
					 }
			 else
				 usr1=usr.replaceAll("\\s+","");
			 if((u.equals(usr1))|(u.equals(reversedstring)|(u.equals(actualstring)))) {
			 drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).click();
			 b=true;
			 break;
}
		
				 
	}
		if(b)
			break;
		else
			drvr.findElement(By.xpath("//td[2]/div[4]/div/div/a["+j+"]/span")).click();
	}
	}
		
}