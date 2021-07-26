package com.salesforcetest.pages.aao.manager;

import org.openqa.selenium.WebDriver;

import com.salesforcetest.pages.aao.Accounts;
import com.salesforcetest.pages.aao.ApplicationDetail;
import com.salesforcetest.pages.aao.Applications;
import com.salesforcetest.pages.aao.Hold;
import com.salesforcetest.pages.aao.Home;
import com.salesforcetest.pages.aao.LogIn;
import com.salesforcetest.pages.aao.MonthlyGoals;
import com.salesforcetest.pages.aao.NavigationBar;
import com.salesforcetest.pages.aao.PreCondition;
import com.salesforcetest.pages.aao.QualityControlPercentage;
import com.salesforcetest.pages.aao.ResponseBuilder;
import com.salesforcetest.pages.aao.ResponseDetail;
import com.salesforcetest.pages.aao.ServiceItem;
import com.salesforcetest.pages.aao.ServiceItemDetail;
import com.salesforcetest.pages.aao.ServiceItemEdit;
import com.salesforcetest.pages.aao.TrackingRecord;
import com.salesforcetest.pages.aao.UpdateMultipleServiceItems;
import com.salesforcetest.pages.aao.UserActions;
import com.salesforcetest.pages.aao.utils.AaoHelper;

public class PageManager {
	
	private  WebDriver driver1;
	private LogIn login;
	private Home home;
	private UserActions acts;
	private ApplicationDetail appdtl;
	private Applications apps;
	private ServiceItem itm;
	private ServiceItemDetail siitemdtl;
	private TrackingRecord record;
	private Hold hold;
	private Accounts accts;
	private ServiceItemEdit edit;
	private QualityControlPercentage qc;
	private ResponseBuilder bldr;
	private ResponseDetail rdetail;
	private MonthlyGoals goals;
	private UpdateMultipleServiceItems items;
	
	
	
	 public PageManager (WebDriver driver)
		{
			this.driver1=driver;
		}
	 
	 public PageManager init()
	 {
		 return new PageManager(driver1);
	 }
	 
	 public LogIn getLogInPage() {
		 if(login==null)
		return new LogIn(driver1);
		 else
			 return login;
	 }
	 
	public UserActions getUserActionsPage() {
		if(acts==null)
		return new UserActions(driver1);
		else
			return acts;
	}

	public Home getHomePage()
	{
		if(home==null)
		return new Home(driver1);
		else
			return home;
	}
	
	
	
	public ApplicationDetail getApplicationDetailPage() {
		if(appdtl==null)
		return new ApplicationDetail(driver1);
		else
			return appdtl;
	}
	
	public Applications getApplicationPage()
	{
		if(apps==null)
		return new Applications(driver1);
		else
			return apps;
	}
	
	public ServiceItem getServiceItemPage()
	{ 
		
		if(itm==null)
		return new ServiceItem(driver1);
		else
			return itm;
	}
	
	public ServiceItemDetail getServiceItemDetailPage()
	{
		 if(siitemdtl==null)
		return new ServiceItemDetail(driver1);
		 else
			 return siitemdtl;
		
	}
	
	public TrackingRecord getTrackingRecord() {
		if(record==null)
			return new TrackingRecord(driver1);
		else
			return record;
	}
	
	public Hold getHold() {
		if(hold==null)
			return new Hold(driver1);
		else
			return hold;
	}
	
	public Accounts getAccount() {
		if(accts==null)
			return new Accounts(driver1);
			else
				return accts;
	}
	

	public ServiceItemEdit getEdit() {
		if(accts==null)
			return  new ServiceItemEdit(driver1);
			else
				return edit;
	}
	
	public QualityControlPercentage getPercentage() {
		
		if(qc==null)
			return new QualityControlPercentage(driver1);
		else
			return qc;
	}
	
public ResponseBuilder getBuilder() {
		
		if(bldr==null)
			return new ResponseBuilder(driver1);
		else
			return bldr;
	}

public ResponseDetail getResponseDetail() {
	if(rdetail==null)
		return new ResponseDetail(driver1);
	else
		return rdetail;
	
}

public MonthlyGoals getMonthlyGoals()
{
	
	if(goals==null)
		return new MonthlyGoals(driver1);
	else
		return goals;
}

public UpdateMultipleServiceItems getServiceItemsPage()
{
	
	if(items==null)
		return new UpdateMultipleServiceItems(driver1);
	else
		return items;
}
}

