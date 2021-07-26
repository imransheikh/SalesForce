package com.salesforcetest.pages.aao.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class AaoHelper {
	public static String service_item_email_subject = null;

	private static String logger_file = Constants.LOGGER_FILE;
	public static String subjectLine;
	private static String getUrl;
	private static URL pdfurl;
	private static BufferedInputStream bis;
	private static PDFParser pdfparser;
	
	

	public  static void open_another_tab(WebDriver driver) {
		// Creating the JavascriptExecutor interface object by Type casting
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open('about:blank','_blank');");

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public  static void switch_to_tab(WebDriver driver, int tabIndex) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(tabIndex));
	}
	
	public  static String switch_to_popup(WebDriver driver) {
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler) ;// switch to popup window

		// Now you are in the popup window, perform necessary actions here

//		driver.switchTo().window(parentWindowHandler);
		return parentWindowHandler;
	}

	/**
	 * Scrolling window down
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void scrollWindow(final WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,400)", "");
		Thread.sleep(1000);
	}

	/**
	 * Scrolling window down
	 * 
	 * @param driver
	 * @param pixels
	 * @throws InterruptedException
	 */
	public  static void scrollWindow(final WebDriver driver, int pixels) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + pixels + ")", "");
		Thread.sleep(1000);
	}

	/**
	 * Scrolling window down
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static  void scrollElement(final WebDriver driver) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(1000);
	}

	public static void sleep(int secs) {
		long expected = System.currentTimeMillis() / 1000 + secs;
		while (true) {
			long loopTime = System.currentTimeMillis() / 1000;
			if (expected < loopTime) {
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
	public static  void scrollUp(final WebDriver driver, By locator) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebElement el = driver.findElement(locator);
		jse.executeScript("arguments[0].scrollBy(0, -300);", el);
		Thread.sleep(3000);
	}

	/**
	 * Scrolling given panel down
	 * 
	 * @param driver
	 * @param locator
	 * @throws InterruptedException
	 */
	public  static void scrollDown(final WebDriver driver, By locator) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;

		WebElement el = driver.findElement(locator);
		jse.executeScript("arguments[0].scrollBy(0, 300);", el);
		Thread.sleep(3000);
	}

	public static void scrollIntoView(final WebDriver driver, WebElement el) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", el);
		Thread.sleep(3000);
	}

	public  void log() {
	}

	public  static void log(String status, String content) {
		OutputStreamWriter output = null;
		try {
			File f = new File(logger_file);

			output = new OutputStreamWriter(new FileOutputStream(f, true));
			output.write("<br><br>");
			if ("passed".equalsIgnoreCase(status)) {
				output.write("<font color='green'>" + content + "</font>");
			} else {
				output.write("<font color='red'>" + content + "</font>");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Check whether there is an alert on page. If so, press ok.
	 * 
	 * @param driver
	 * @return
	 */
	public static boolean handleAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}
	public boolean isAlertPresent(WebDriver driver) {
	    try {
	        driver.switchTo().alert();
	        return true;
	    } // try
	    catch (Exception e) {
	        return false;
	    } // catch
	}
	/**
	 * Custom fluent wait
	 * 
	 * @param locator
	 * @param driver
	 * @param timeout
	 * @param polling
	 * @return
	 * @throws InterruptedException
	 */
	
	

	public static WebElement scrollToFindElement(final WebDriver driver, final By locator) throws Exception {
		int ii = 0;
		while (ii < 10) {
			List<WebElement> allEle = driver.findElements(locator);
			if (allEle.size() > 0) {
				return allEle.get(0);
			}
			scrollWindow(driver);
			ii++;
		}
		return null;
	}
	
	public static  WebElement FindAaoElement(WebDriver driver,WebElement element ) {
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		        .withTimeout(Duration.ofSeconds(100))
		        .pollingEvery(Duration.ofMillis(100))
		       
		  .ignoring(NoSuchElementException.class,TimeoutException.class).ignoring(StaleElementReferenceException.class);
			try {
      	   // wait.until(ExpectedConditions.visibilityOf(element));
      	  //   wait.until(ExpectedConditions.elementToBeClickable(element));
				wait.until( ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
			
     
      }catch (Exception e) {
          e.printStackTrace();
         
      
  }
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return element;
			
	}
	
public  static void  waitForAaoElement(WebDriver driver,WebElement element ) {
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
	
		WebDriverWait wait = new WebDriverWait(driver, 100);
		try {
			 wait.ignoring(NoSuchElementException.class,StaleElementReferenceException.class).until(
	                ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
      }catch (Exception e) {
          e.printStackTrace();
         
      
  }
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
	}

public static boolean isListElementVisible(WebDriver driver,List<WebElement> ele) {
	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); 
    boolean result = false;
    try {
        WebDriverWait wait = new WebDriverWait(driver, 100);
       // wait.until(ExpectedConditions.visibilityOfAllElements(ele));
        if (ele.size()>0)
        result = true;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        result = false;
    }
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   return result;
}

public static  boolean isElementVisible(WebDriver driver,WebElement ele) {
	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean result = false;
    try {
        WebDriverWait wait = new WebDriverWait(driver, 80);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        result = true;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        result = false;
    }
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   return result;
}

public static String getRandomReceiptNumber() {
	
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           
	 String AlphaNumericString1 =   "0123456789";
           
             

// create StringBuffer size of AlphaNumericString 
StringBuilder sb = new StringBuilder(100); 

for (int i =0; i < 3; i++) { 

// generate a random number between 
// 0 to AlphaNumericString variable length 
int index 
= (int)(AlphaNumericString.length() 
 * Math.random()); 

sb.append(AlphaNumericString 
        .charAt(index)); 
} 

for (int j =0; j < 10; j++) { 

	// generate a random number between 
	// 0 to AlphaNumericString variable length 
	int index 
	= (int)(AlphaNumericString1.length() 
	 * Math.random()); 

	sb.append(AlphaNumericString1
	        .charAt(index)); 
	} 

return sb.toString(); 
        	
			
}

public static String getRandomFileNumber() {
	
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
           
	 String AlphaNumericString1 =   "0123456789";
           
             

// create StringBuffer size of AlphaNumericString 
StringBuilder sb = new StringBuilder(100); 

for (int i =0; i < 1; i++) { 

// generate a random number between 
// 0 to AlphaNumericString variable length 
int index 
= (int)(AlphaNumericString.length() 
 * Math.random()); 

sb.append(AlphaNumericString 
        .charAt(index)); 
} 

for (int j =0; j < 9; j++) { 

	// generate a random number between 
	// 0 to AlphaNumericString variable length 
	int index 
	= (int)(AlphaNumericString1.length() 
	 * Math.random()); 

	sb.append(AlphaNumericString1
	        .charAt(index)); 
	} 

return sb.toString(); 
        	
			
}
public static String randomDateTime() {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);
    String randomDateTime = String.valueOf(year) + String.valueOf(month) + String.valueOf(day)
    + String.valueOf(hour) + String.valueOf(minute) + String.valueOf(second);
    subjectLine = "Test Automation OIDP Regression Flow - " + randomDateTime;
    return randomDateTime;
}

public static String selectDate() {
    DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
   
     
    // Create a calendar object with today date. Calendar is in java.util pakage.
    Calendar calendar = Calendar.getInstance();
    
    calendar.add(Calendar.DATE, -2);
   
    // Get current date of calendar which point to the yesterday now
    Date d = calendar.getTime();

    return dateFormat.format(d).toString();
}

public static String getTodaysDate() {
	DateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");

	//get current date time with Date()
	Date date = new Date();

	// Now format the date
	String dateFormatted= dateFormat.format(date);
	return dateFormatted;
}

public static void AaohighlightElement(WebDriver driver,WebElement element) {
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    Utils.sleep(4);
}

public static void waitForAlert(WebDriver driver) {
	WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.alertIsPresent());
	
}


public static String getCurrentMonth() {
	
	  Date date = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("MMMMMMMMM");
	   return formatter.format(date);          
	 }

public static String getNextMonth() {
	
	 DateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM");
	   
     
	    // Create a calendar object with today date. Calendar is in java.util pakage.
	    Calendar calendar = Calendar.getInstance();
	   
	    
	    calendar.add(Calendar.MONTH,+5);
	   
	    // Get current date of calendar which point to the yesterday now
	    Date d = calendar.getTime();

	    return dateFormat.format(d).toString();
}

public static String getUpdatedDate() {
	DateFormat dateFormat = new SimpleDateFormat("MM/d/yyyy");
	   
    
    // Create a calendar object with today date. Calendar is in java.util pakage.
    Calendar calendar = Calendar.getInstance();
   
    
    calendar.add(Calendar.MONTH,+1);
   
    // Get current date of calendar which point to the yesterday now
    Date d = calendar.getTime();

    return dateFormat.format(d).toString();
	
}

public static String getUpdatedMonth() {
	DateFormat dateFormat = new SimpleDateFormat("MMMMMMMMM");
	   
    
    // Create a calendar object with today date. Calendar is in java.util pakage.
    Calendar calendar = Calendar.getInstance();
   
    
    calendar.add(Calendar.MONTH,+1);
   
    // Get current date of calendar which point to the yesterday now
    Date d = calendar.getTime();

    return dateFormat.format(d).toString();
	
}


public enum Month {
    October,November,December,January,February,March,April,May,June,July,August,September;
}

public static int getMonthNumber(String s) {
	
	int val = 0;
	switch(Month.valueOf(s)) {
	case October:
		val=3;
		break;
	case November:
		val=4;
		break;
	case December:
		val=5;
		break;
	case January:
		val=6;
		break;
	case February:
	   val=7;
	   break;
	case March:
	val=8;
	break;
	case April:
		val=9;
		break;
	case May:
	   val=10;
	   break;
	case June:
	   val=11;
	   break;
	case July:
	val=12;
	break;
	case August:
	val=13;
	break;
	case September:
	val=14;
	break;
	
		
	
	
	}
	
	return val;
}

public static String getCallUpResumeDate() {
	DateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

	//get current date time with Date()
	Date date = new Date();

	// Now format the date
	String dateFormatted= dateFormat.format(date);
	return dateFormatted;
}

public static String getNextYear() {
	
	 DateFormat dateFormat = new SimpleDateFormat("YYYY");
	   
    
	    // Create a calendar object with today date. Calendar is in java.util pakage.
	    Calendar calendar = Calendar.getInstance();
	   
	    
	    calendar.add(Calendar.YEAR,+1);
	   
	    // Get current date of calendar which point to the yesterday now
	    Date d = calendar.getTime();

	    return dateFormat.format(d).toString();
}

public static String getCurrentYear() {
	
	  Date date = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("YYYY");
	   return formatter.format(date);          
	 }

	
}




