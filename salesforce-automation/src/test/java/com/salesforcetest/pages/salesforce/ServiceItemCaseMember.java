package com.salesforcetest.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.salesforcetest.shared.Utils;

public class ServiceItemCaseMember {

	private WebDriver driver;
	
	//@FindBy(css = "a[title='Add Member']")
	@FindBy(xpath = "//a[@title='Add Member']")
	WebElement addMemberButton;
	
	public ServiceItemCaseMember(WebDriver driver, ExtentTest testReporter) {
		super();
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		this.driver = driver;
	}

	
	public void add_member_in_service_item(String teamMember) {
		Utils.sleep(4);

		
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Related']/parent::*")));
		try {
			driver.findElement(By.xpath("//ul/li/a[text()='Related']")).click();
		} catch (Exception e) {
			//driver.findElement(By.linkText("Related")).click();	
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//ul/li/a[text()='Related']")));
		}
		Utils.sleep(2);
		
		//addMemberButton.click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();",addMemberButton);
		
		Utils.sleep(2);
		
		//WebElement parent = driver.findElement(By.className("modal-container"));
		
		//WebElement combo = parent.findElement(By.className("autocompleteWrapper"));

		// Fill owner name
		//combo.findElement(By.tagName("input")).sendKeys(teamMember);
		driver.findElement(By.xpath("//*[text()='Search for and add member']/parent::*/parent::*/descendant::input")).sendKeys(teamMember);
		Utils.sleep(2);
		
		//parent.findElement(By.cssSelector("div[title='" + teamMember + "']")).click();
		driver.findElement(By.xpath("//div[@title='" + teamMember + "']/parent::div/parent::a")).click();
		Utils.sleep(2);

		//parent.findElement(By.name("save")).click();
		driver.findElement(By.xpath("//*[text()='Search for and add member']/parent::*/parent::*/descendant::button[@name='save']")).click();

		Utils.sleep(2);
	}
}
