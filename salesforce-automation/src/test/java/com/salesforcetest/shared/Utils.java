package com.salesforcetest.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utils {

	public static String service_item_email_subject = null;

	private static String logger_file = Constants.LOGGER_FILE;

	public static void open_another_tab(WebDriver driver) {
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
	
	public static String switch_to_popup(WebDriver driver) {
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window

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
	public static void scrollWindow(final WebDriver driver, int pixels) throws InterruptedException {
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
	public static void scrollElement(final WebDriver driver) throws InterruptedException {
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
	public static void scrollUp(final WebDriver driver, By locator) throws InterruptedException {
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
	public static void scrollDown(final WebDriver driver, By locator) throws InterruptedException {
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

	public static void log() {
	}

	public static void log(String status, String content) {
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
	public static WebElement fluentWait(final By locator, final WebDriver driver, final int timeout, final int polling)
			throws InterruptedException {
		int ii = polling;
		while (ii < timeout) {
			List<WebElement> allEle = driver.findElements(locator);
			if (allEle.size() > 0) {
				return allEle.get(0);
			}
			Thread.sleep(polling * 1000);
			ii += polling;
		}
		return null;
	}

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
	
	public static void buttonClick(WebDriver driver, WebElement button) throws Exception {
		try {
			button.click();
		}
		catch (ElementNotVisibleException e) {
			scrollWindow(driver);
			buttonClick(driver, button);
		}
	}
	
	public static String[] getCredential(String user) {
		String[] c = new String[2];
		if("Supervisor".equalsIgnoreCase(user)) {
			c[0] = Constants.supervisor_salesforce_username;
			c[1] = Constants.supervisor_salesforce_password;
		} else {
			c[0] = Constants.salesforce_username;
			c[1] = Constants.salesforce_password;
		}
		return c;
	}
}
