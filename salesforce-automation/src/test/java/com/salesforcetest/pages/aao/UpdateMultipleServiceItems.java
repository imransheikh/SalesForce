package com.salesforcetest.pages.aao;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class UpdateMultipleServiceItems {
	
	@FindBy(name="massUpdateAction")
	private WebElement lstUpdateAction;
	@FindBy(xpath="//div[2]//lightning-base-combobox/div/div[1]/input")
	private WebElement lstResponsibleParty;
	@FindBy(xpath="//div[3]//lightning-input/div/input")
	private WebElement txtFileNumber;
	@FindBy(xpath="//button[@title='Search']")
	private WebElement btnSearch;
	@FindBy(xpath="//div[1]/lightning-datatable/div[2]//table")
	private WebElement tblResults;
	 private WebDriver drvr;

	public UpdateMultipleServiceItems(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);

			
			this.drvr=driver;
		}
	
	public void selectAction() {
		
		 AaoHelper.waitForAaoElement(drvr,lstUpdateAction);
		 Select select=new Select(lstUpdateAction);
		 AaoHelper.sleep(4);
		 select.selectByVisibleText("Responsible Party");
		
	}
	
	public void selectResponsibleParty() {
		
		 AaoHelper.waitForAaoElement(drvr,lstResponsibleParty);
		AaoHelper.FindAaoElement(drvr,lstResponsibleParty).click();
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, drvr.findElement((By.xpath("//div[2]/lightning-base-combobox-item[2]/span[2]/span")))).click();
				
		
	}
	
	public void enterFileNumber(String text)
	{
		
		AaoHelper.waitForAaoElement(drvr, txtFileNumber);
		AaoHelper.AaohighlightElement(drvr, txtFileNumber);
		AaoHelper.FindAaoElement(drvr, txtFileNumber).sendKeys(text);
		clickSearch();
	}
	
	public void clickSearch() {
		
		AaoHelper.FindAaoElement(drvr, btnSearch).click();
		
		
	}
	
	public boolean verifySearchResults(String s,String s1,String s2,String s3)
	{
		boolean  b=false;
		AaoHelper.sleep(5);
		AaoHelper.waitForAaoElement(drvr,tblResults);
		AaoHelper.AaohighlightElement(drvr, tblResults);
		 
		 List<WebElement> rows = tblResults.findElements(By.tagName("tr"));
		 
			 AaoHelper.sleep(3);
			 String text=drvr.findElement(By.xpath("//div[1]//tr[1]//lightning-primitive-cell-factory//lightning-formatted-url/a")).getText();
			 
			 String text1=drvr.findElement(By.xpath("//div[1]//tr[1]/td[7]//lightning-primitive-cell-factory//lightning-formatted-text")).getText();
			 String text2=drvr.findElement(By.xpath("//div[1]//tr[2]//lightning-primitive-cell-factory//lightning-formatted-url/a")).getText();
			 String text3=drvr.findElement(By.xpath("//div[1]//tr[2]/td[7]//lightning-primitive-cell-factory//lightning-formatted-text")).getText();
			 
			 if((text.equalsIgnoreCase(s))&(text1.equalsIgnoreCase(s1))&(text2.equalsIgnoreCase(s2))&(text3.equalsIgnoreCase(s3)))
					return true;
					else
						return false;
	}
	
	public void clickServiceItem(String text)
	{
		
		boolean  b=false;
		 List<WebElement> rows = tblResults.findElements(By.tagName("tr"));
		 int rows_count=rows.size();
		 for (int row = 1; row <=rows_count; row++) {
			
		AaoHelper.sleep(3);
			 String si=drvr.findElement(By.xpath("//div[2]//tr["+row+"]//lightning-primitive-cell-factory//lightning-formatted-url/a")).getText();
			 if(si.equalsIgnoreCase(text)) {
				 JavascriptExecutor executor = (JavascriptExecutor)drvr;
				 executor.executeScript("arguments[0].click();", drvr.findElement(By.xpath("//div[2]//tr["+row+"]//lightning-primitive-cell-factory//lightning-formatted-url/a")));
				 b=true;
				break;
			
		}
	}
	}
	
	public boolean verifyAaoServiceItemReturned(String text)
	{
		String si=drvr.findElement(By.xpath("//div[1]//tr//lightning-primitive-cell-factory//lightning-formatted-url/a")).getText();
		if(text.equalsIgnoreCase(text))
			return true;
		else
			return false;
}
}

