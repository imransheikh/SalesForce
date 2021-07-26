package com.salesforcetest.shared;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

	public static String service_item_email_subject = null;

	public static void log(String msg) {
		System.out.println(msg);
	}

	public static void switch_to_another_tab(WebDriver driver) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('about:blank','_blank');");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public static void switch_to_tab(WebDriver driver, int tabIndex) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}

	/**
	 * Scrolling window down
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void scrollWindow(final WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
	}
	
	/**
	 * Scrolling window down
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void scrollElement(final WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
	}
	
	public static void sleep(int secs) {
		long expected = System.currentTimeMillis()/1000 + secs;
		while(true) {
			long loopTime = System.currentTimeMillis()/1000;
			if(expected < loopTime) {
				break;
			}
		}
	}
	
	/**
	    * Scrolling given panel up
	    * 
	    * @param driver
	    * @param locator
	    * @throws InterruptedException
	    */
	   public static void scrollUp(final WebDriver driver, By locator) throws InterruptedException {
	      JavascriptExecutor jse = (JavascriptExecutor) driver;
	      
	      WebElement el = driver.findElement(locator);
	      jse.executeScript("arguments[0].scrollBy(0, 300);",el);
	      Thread.sleep(3000);
	   }
	   
	   /**
	    * Scrolling given panel down
	    * 
	    * @param driver
	    * @param locator
	    * @throws InterruptedException
	    */
	   public static void scrollDown(final WebDriver driver, By locator) throws InterruptedException {
	      JavascriptExecutor jse = (JavascriptExecutor) driver;
	      
	      WebElement el = driver.findElement(locator);
	      jse.executeScript("arguments[0].scrollBy(0, 300);",el);
	      Thread.sleep(3000);
	   }
}
