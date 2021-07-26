package com.salesforcetest.pages.aao;

import java.sql.DriverManager;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.pages.aao.manager.PageManager;

import com.salesforcetest.pages.aao.utils.AaoHelper;

public class UserActions {
	
	
   
    private WebDriver drvr;
    
    
	
	
	@FindBy(id = "globalHeaderNameMink")
	private List<WebElement> lnkSetUpOptions;
	@FindBy(linkText="Setup")
	private List<WebElement> lnkSetUp;
	@FindBy(id="setupLink")
	private List<WebElement> lnkSetUpConsole;
	@FindBy(id="Users")	
	private WebElement lnkManageUsers;
	@FindBy(xpath="//div[@class='profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon']")
	private List<WebElement> imgViewProfileIcon;
	@FindBy(linkText="Switch to Salesforce Classic")
	private List<WebElement> lnkSwitchToClassic;
	@FindBy(name="login")
	private WebElement btnLogIn;
	@FindBy(id="phSearchInput")
	private WebElement txtServiceItemSearchBox;
	@FindBy(id="phSearchButton")
	private WebElement btnServiceItemSearch;
	@FindBy(id="ForceCom_font")
	private WebElement lnkHome;
	@FindBy(xpath="//div[2]/div/div[2]/table/tbody")
	private WebElement tblServiceItem;
	@FindBy(xpath="//a/img[@class='allTabsArrow'][@title='All Tabs']")
	private WebElement imgAllTabs;
	@FindBy(linkText="Monthly Goals")
	private WebElement lnkMonthlyGoals;
	@FindBy(linkText="Update Multiple Service Items")
	private WebElement lnkUpdateMultipleServiceItems;
	@FindBy(xpath="//span[text()='Contacts']")
	private WebElement lnkContacts;
	@FindBy(xpath="//span[text()='Forms']")
	private WebElement lnkForms;
	@FindBy(xpath="//div[@id='Contact_body']/table[@class='list']")
	private WebElement tblContacts;
	@FindBy(xpath="//div[@id='Form__c_body']/table[@class='list']")
	private WebElement tblForms;
	@FindBy(xpath="//input[@name='edit']")
	private WebElement btnEdit;
	@FindBy(xpath="//input[@name='con5']")
	private WebElement txtTitle;
	@FindBy(xpath="//div[3]//tr[6]/td[4]/input")
	private WebElement txtWeight;
	@FindBy(xpath="//input[@name='save']")
	private WebElement btnSave;
	@FindBy(xpath="//div[@id='con5_ileinner']")
	private WebElement lblTitle;
	private NavigationBar navbar;		
	
	

	
	
	
		 
	 public UserActions(WebDriver driver)
	
	{
		PageFactory.initElements(driver, this);

		
		this.drvr=driver;
	}
	
	 public NavigationBar getBar()
		{
			navbar=new NavigationBar(drvr);
			return navbar;
		}
		
	
	
	
	public void clickViewProfileIcon()
	{
		
	
		imgViewProfileIcon.get(0).click();
					
	}
	
	public void clickSwitchToClassic()
	{
		
		
		lnkSwitchToClassic.get(0).click();		
	}
		
	
	public void clickSetUp()
	{
		
		lnkSetUp.get(0).click();
	}
		
	public void navigateToHomePage()
	{
		//boolean isPresentIcon=AaoHelper.isListElementVisible(drvr,imgViewProfileIcon);
	if(imgViewProfileIcon.size()>0) {
		clickViewProfileIcon();
		if(lnkSwitchToClassic.size()>0)
		clickSwitchToClassic();
		
		}
	//	boolean isPresentSetUp=AaoHelper.isElementVisible(drvr,lnkSetUpOptions);
		 if(lnkSetUpOptions.size()>0) {
			lnkSetUpOptions.get(0).click();
		clickSetUp();
		 }
		
			
		if(lnkSetUpConsole.size()>0)
			lnkSetUpConsole.get(0).click();
			
		 
		
		
		
	}
	
	public void clickLogInForAaoUser()
	
	{
		
		AaoHelper.FindAaoElement(drvr,btnLogIn).click();
	    
	}
	
	
	public void goBack() {
		drvr.navigate().back();
	}
	
	public void findServiceItem(String text) {
		
		AaoHelper.FindAaoElement(drvr, txtServiceItemSearchBox).sendKeys(text);
		AaoHelper.FindAaoElement(drvr, btnServiceItemSearch).click();
	}
	
	public void clickServiceItem(String text) {
		
		 boolean b=false;
		
	AaoHelper.waitForAaoElement(drvr,tblServiceItem);
	 List<WebElement> rows = tblServiceItem.findElements(By.tagName("tr"));
	 int rows_count=rows.size();
	 for (int row = 2; row <=rows_count; row++) {
		
	
		 String item=drvr.findElement(By.xpath("//div[2]/div/div[2]/table/tbody/tr[" + rows_count + "]/th/a")).getText();
		 
	if((item.equalsIgnoreCase(text)))
	{
		drvr.findElement(By.xpath("//div[2]/div/div[2]/table/tbody/tr[" + rows_count + "]/th/a")).click();
		break;
	}
	}

}
	
	public void goToMonthlyGoals() {
		AaoHelper.waitForAaoElement(drvr, imgAllTabs);
		AaoHelper.FindAaoElement(drvr, imgAllTabs).click();
		AaoHelper.waitForAaoElement(drvr, lnkMonthlyGoals);
		//((JavascriptExecutor) drvr).executeScript("window.open('https://uscis--uatwin20--c.cs33.visual.force.com/apex/MonthlyGoals?sfdc.tabName=01rt00000008Yj0')");
		new Actions(drvr).keyDown(Keys.CONTROL).click(lnkMonthlyGoals).keyUp(Keys.CONTROL).build().perform();
		//AaoHelper.switch_to_tab(drvr, 0);
		
		
	}
	
	public void goToUpdateMultipleServiceItems() {
		AaoHelper.waitForAaoElement(drvr, imgAllTabs);
		AaoHelper.FindAaoElement(drvr, imgAllTabs).click();
		AaoHelper.waitForAaoElement(drvr, lnkUpdateMultipleServiceItems);
		//((JavascriptExecutor) drvr).executeScript("window.open('https://uscis--uatwin20--c.cs33.visual.force.com/apex/MonthlyGoals?sfdc.tabName=01rt00000008Yj0')");
		AaoHelper.FindAaoElement(drvr,lnkUpdateMultipleServiceItems).click();
		//AaoHelper.switch_to_tab(drvr, 0);
		
	}
	
	public void closeCurrentTab() {
		
		drvr.close();
		drvr.switchTo().defaultContent();
	}
	
	
	public void goToContactAndSetTitle(String s,String s1)
	{
		
		AaoHelper.FindAaoElement(drvr, txtServiceItemSearchBox).sendKeys(s);
		AaoHelper.FindAaoElement(drvr, btnServiceItemSearch).click();
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, lnkContacts).click();
		AaoHelper.sleep(3);
		clickContact(s);
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, btnEdit).click();
		AaoHelper.waitForAaoElement(drvr, txtTitle);
		AaoHelper.FindAaoElement(drvr, txtTitle).clear();
		
		AaoHelper.FindAaoElement(drvr, txtTitle).sendKeys(s1);
		AaoHelper.FindAaoElement(drvr, btnSave).click();
		
		
	}
	
	public void goToFormSetWeight(String s,String s1)
	{
		AaoHelper.FindAaoElement(drvr, txtServiceItemSearchBox).sendKeys(s);
		AaoHelper.FindAaoElement(drvr, btnServiceItemSearch).click();
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, lnkForms).click();
		AaoHelper.sleep(3);
		clickForm(s);
		AaoHelper.sleep(3);
		AaoHelper.FindAaoElement(drvr, btnEdit).click();
		AaoHelper.waitForAaoElement(drvr, txtWeight);
		AaoHelper.FindAaoElement(drvr, txtWeight).clear();
		
		AaoHelper.FindAaoElement(drvr, txtWeight).sendKeys(s1);
		AaoHelper.FindAaoElement(drvr, btnSave).click();
	}
	
	public void clickContact(String s)
	{
		
		boolean b=false;
		List<WebElement> rows = tblContacts.findElements(By.tagName("tr"));
		int rows_count=rows.size();
		for (int row = 2; row < rows_count; row++) {
			 String contct=drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).getText();
			 if(contct.equalsIgnoreCase(s))
			 {
				 drvr.findElement(By.xpath("//div[2]/table[@class='list']/tbody/tr[" + row + "]/th/a")).click();
				 break;
			 }
				 
	}
	
}
	
	public void clickForm(String s)
	{
		
		boolean b=false;
		WebElement ele;
		List<WebElement> rows = tblForms.findElements(By.tagName("tr"));
		int rows_count=rows.size();
		
		 System.out.println(rows_count);
		for (int row = 1; row <= rows_count; row++) {
			 String form=drvr.findElement(By.xpath("//div[@id='Form__c_body']/table[@class='list']/tbody/tr[" + row + "]/th/a")).getText();
			 System.out.println(form);
			 if(form.equalsIgnoreCase(s))
			 {
				ele= drvr.findElement(By.xpath("//div[@id='Form__c_body']/table[@class='list']/tbody/tr[" + row + "]/th/a"));
				 JavascriptExecutor executor = (JavascriptExecutor)drvr;
				 executor.executeScript("arguments[0].click();", ele);
				 break;
			 }
				 
	}
	
}
	
	 public String getTitle()
	 {
	return	 lblTitle.getText();
	
	 }
	 
	 public void goToServiceItems() {
		 AaoHelper.switch_to_tab(drvr, 0);
		 getBar().clickServiceItems();
	 }
	 
}
