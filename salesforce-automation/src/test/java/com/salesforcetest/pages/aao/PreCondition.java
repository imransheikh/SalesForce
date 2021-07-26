package com.salesforcetest.pages.aao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.manager.FileManager;
import com.salesforcetest.pages.aao.utils.AaoHelper;

public class PreCondition {
	
	private AaoHelper helper;
    private WebDriver drvr;
    private Home homepage;
    private UserActions actions;
    private ApplicationDetail appdetail;
    private Applications applications;
    private ServiceItem item;
    
    public PreCondition(WebDriver driver)
	
   	{
   		PageFactory.initElements(driver, this);
     	helper= new AaoHelper();
   		this.drvr=driver;
   		homepage=new Home(drvr);
   		
   		applications=new Applications(drvr);
   		actions=new UserActions(drvr);
   		appdetail=new ApplicationDetail(drvr);
   		item=new ServiceItem(driver);
   	}
   	
    public void createTestData() {
    	
    	 homepage.clickManageUsers();
    	 homepage.clickUsers();
    	 homepage.selectAllAaoUsers();
    	// homepage.searchAaoUser(FileManager.getInstance().getReader().getUser());
       actions.clickLogInForAaoUser();
    	 
 	   // applications.createApplication(FileManager.getInstance().getReader().getFileType());
 	   
    }

}
