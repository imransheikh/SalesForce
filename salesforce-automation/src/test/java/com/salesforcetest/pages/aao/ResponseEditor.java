package com.salesforcetest.pages.aao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class ResponseEditor {
	private WebDriver drvr;
	private AaoHelper helper;
	
	@FindBy(id="input-62")
	private WebElement cbxDecision;
	@FindBy(xpath="//button[@title='Save']")
	private WebElement btnSave;
	@FindBy(xpath="//a[contains(text(),'View Response'] ")
	private WebElement lnkViewResponse;
	
	 public ResponseEditor(WebDriver driver)
		
		{
			PageFactory.initElements(driver, this);
			this.drvr=driver;
			
		}
	 
	 public ResponseEditor init(WebDriver driver)
		{
			return new ResponseEditor(driver);
		}
	 
	 public void selectDecision() {
		 helper.FindAaoElement(drvr,cbxDecision);
		 cbxDecision.findElement(By.xpath("//input[@data-position-id='lgcp-1000043']")).click();
	 }
	 
	 public void clickSave()
	 {
		 btnSave.click();
	 }
	public void clickViewResponse() {
		 helper.FindAaoElement(drvr,lnkViewResponse).click();
	}
}
