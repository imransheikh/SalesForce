package com.salesforcetest.pages.aao.manager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.salesforcetest.pages.aao.Applications;

public class DriverManager {
	
	private static  ThreadLocal<WebDriver> Threaddriver=new ThreadLocal<WebDriver>();
    private WebDriver driver;
	private  DriverType driverType;

	
	 public  DriverManager init()
		{
			return new DriverManager();
		}
	 
	 public static void disableSeleniumLogs() {    
		    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
		    Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
		}
	 
	
	public   WebDriver getDrvr() {
		
		//driver=DriverManager.Threaddriver.get();
		if (driver==null)
		{
			driverType=FileManager.getInstance().getReader().getBrowser();
			switch (driverType) {     
	        case FIREFOX : driver = new FirefoxDriver();
	      break;
	        case CHROME : 
	        //	System.setProperty("webdriver.chrome.driver", "C:\\Users\\udanturthy\\Downloads\\chromedriver_80\\chromedriver.exe");
	        	System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
	        	//System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver_win32\\chromedriver.exe");
	        
	        	disableSeleniumLogs();
	         driver = new ChromeDriver();
	     break;
	        case INTERNETEXPLORER : driver= new InternetExplorerDriver();
	     break;
	        }
			//Threaddriver.set(driver);
			driver.manage().window().maximize(); 
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}
		return driver;
		
	}
	

	
	public  void Navigate()
	{
		getDrvr().get(FileManager.getInstance().getReader().getApplicationUrl());
	}
	
	public  void closeDriver() {
		driver.close();
		getDrvr().quit();
		//DriverManager.Threaddriver.set(null);
		 }
}