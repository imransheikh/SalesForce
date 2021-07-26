package com.salesforcetest.shared;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver
{
   public static WebDriver driver = null;
   
   static {
		if(Constants.isWindows()) {
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
		}
		else {
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver");
		}
      
   }
   
   public static WebDriver getDriver() {
      if(driver == null) {
         driver = new ChromeDriver();
         driver.manage().window().maximize(); // maximizes
         driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);// implicit wait
      }
      return driver;
   }
}
