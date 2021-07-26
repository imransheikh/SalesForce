package com.salesforcetest.shared;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver() {
		if(driver == null) {
			System.out.println("System Name:" + System.getProperty("os.name"));
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize(); // maximizes
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);// implicit wait
		}
		return driver;
	}
	
	public static void closeWindows(WebDriver driver) {
		Utils.open_another_tab(driver);
		
		String homeWindow = driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		
		//Use Iterator to iterate over windows
		Iterator<String> windowIterator =  allWindows.iterator();

		//Verify next window is available
		while(windowIterator.hasNext())
		{
		    String childWindow = windowIterator.next();
		    driver.switchTo().window(childWindow);
		    driver.close();
		}
		
		driver.switchTo().window(homeWindow);
	}
}
