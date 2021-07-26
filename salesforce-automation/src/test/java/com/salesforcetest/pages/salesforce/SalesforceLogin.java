package com.salesforcetest.pages.salesforce;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class SalesforceLogin {

	private String baseUrl = Constants.salesforce_url;
	public static String emailLink;
	private WebDriver driver;

	@FindBy(id = "username")
	WebElement userNameTextBox;

	@FindBy(id = "password")
	WebElement passwordTextBox;
	
	@FindBy(id = "Login")
	WebElement loginButton;
	
	public SalesforceLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void login(String baseUrl, String userName, String pass) {
		this.baseUrl = baseUrl;
		login(userName, pass);
	}
	
	public void login(String userName, String pass) {
		try {
		driver.get(baseUrl);
		Utils.sleep(2);
		userNameTextBox.sendKeys(userName);
		Utils.sleep(1);
		passwordTextBox.sendKeys(pass);
		Utils.sleep(1);
		loginButton.click();
		
		Utils.sleep(3);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void fetchMailUrl (String addName) {
		Utils.sleep(4);
		try {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click();
		} catch (Exception e) {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[7]/div")).click();
		}
		Utils.sleep(4);
		driver.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.xpath("//div[@class='viewport']/div[2]/div[1]/descendant::input[@placeholder='Quick Find']")).sendKeys("Email Services");
		Utils.sleep(4);
		driver.findElement(By.xpath(".//*[@id='split-left']/div/div/div/ul/li[2]/ul/li/div/a")).click();
		Utils.sleep(2);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Email Services ~ Salesforce - Unlimited Edition']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
		driver.findElement(By.xpath("//table[@class='list']/descendant::a[text()='Email Receiver']")));
		Utils.sleep(2);
		driver.findElement(By.xpath("//table[@class='list']/descendant::a[text()='Email Receiver']")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Email Service ~ Salesforce - Unlimited Edition']")));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", 
				driver.findElement(By.xpath("//th/div[contains(text(),'Email Address Name')]")));
		emailLink = driver.findElement(By.xpath("//table[@summary='Email Addresses']/tbody/tr/descendant::span[text()='"+addName+"']/parent::td/following-sibling::td[1]/a")).getText();
		driver.switchTo().defaultContent();
		System.out.println("Fetched email link::::"+emailLink);
		Constants.setEmailUrl(emailLink);
		driver.close();
		driver.switchTo().window(tabs.get(0));
		Utils.sleep(2);
		//driver.close();
		//driver.quit();
		logout();
	}
	public void internalUserLogin(String internalUserNm) {
		//String internalUserNm = "Privacy Staff 2";
		WebDriverWait wait = new WebDriverWait (driver, 8);
		Utils.sleep(4);
		try {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/ul/li[6]/div")).click(); // old env
		} catch (Exception e) {
		driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div/ul/li[7]/div")).click(); //QA env
		}
		Utils.sleep(4);
		driver.findElement(By.xpath(".//*[@id='related_setup_app_home']/a/div[1]/div[1]/span/span[2]")).click();
		Utils.sleep(2);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		try {
		driver.switchTo().window(tabs.get(2));
		driver.switchTo().window(tabs.get(1));
		//System.out.println("I am here3.");
		driver.close();
		Utils.sleep(2);
		driver.switchTo().window(tabs.get(2));
		} catch (Exception e) {
			driver.switchTo().window(tabs.get(0));
			driver.close();
			Utils.sleep(2);
			driver.switchTo().window(tabs.get(1));
		}
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='viewport']/div[2]/div[2]/descendant::input[@placeholder='Quick Find']")));
		} catch (Exception e) {
			
		}
		driver.findElement(By.xpath("//input[@title='Search Setup']")).sendKeys(internalUserNm);
		Utils.sleep(4);
		driver.findElement(By.xpath("//div[@class='listContent']/ul/li/descendant::span[@title='"+internalUserNm+"']/parent::div/parent::a")).click();
		Utils.sleep(4);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//iframe"))));
		driver.findElement(By.xpath("//table[@class='detailList']/tbody/tr[8]/td[3]")).click();
		Utils.sleep(1);
		driver.findElement(By.xpath(".//*[@id='topButtonRow']/input[@name='login' and contains(@value,'Login')]")).click();
		Utils.sleep(2);
		driver.switchTo().defaultContent();
		for(int icount=0; icount<4; icount++) {
			try {
				//driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@data-id='Case']/a")).getText();
				//driver.findElement(By.xpath(".//*[@id='oneHeader']/descendant::*[@role='listitem'][4]")).getText();
				driver.findElement(By.xpath("//a[@title='Service Items']/parent::*")).getText();
				break;
			} catch (Exception e) {
				driver.get(driver.getCurrentUrl());
				driver.navigate().refresh();
				Utils.sleep(4);
			}
		}
	}
	public void logout() {
		Utils.sleep(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[9]")));
		Utils.sleep(2);
		driver.findElement(By.xpath(".//*[@id='oneHeader']/div[2]/span/div[2]/ul/li[9]")).click();
		
		Utils.sleep(2);
		
		driver.findElement(By.linkText("Log Out")).click();
		
		Utils.sleep(3);
	}
}
