package com.salesforcetest.pages.salesforce;

import java.util.List;

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

public class AccountScenario {

	private WebDriver driver;

	//@FindBy(xpath = ".//*[@id='oneHeader']/descendant::*[@data-id='Account']")
	@FindBy(xpath = "//a[@title='Accounts']/parent::*")
	WebElement accountTab;
	
	@FindBy(linkText = "New")
	WebElement newButton;
	
	public AccountScenario(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public void create_internal_account() {
		open_account_tab_and_click_new();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//span[text()='USCIS Internal Account']")));
		Utils.sleep(1);
		//Clicking internal contact
		select_radio_and_next(1);
		
		fill_account_form_and_save("Test Internal Account "+Constants.getRamdomId());
	}
	
	public void create_internal_contact() throws InterruptedException {
		Utils.scrollWindow(driver,280);
		//driver.findElements(By.className("forceRelatedListSingleContainer")).get(0).findElement(By.cssSelector("a[title='New']")).click();
		//driver.findElement(By.xpath("//span[@title='Contacts']/parent::*/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[@title='Contacts']/parent::*/parent::*/parent::*/parent::*/following-sibling::div/descendant::a[@title='New']")));
		Utils.sleep(3);
		
		select_radio_and_next(1);
		
		Utils.sleep(3);
		
		fill_contact_form_and_save();
		
	}
	
	public void create_external_account() {
		open_account_tab_and_click_new();
		
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", 
				driver.findElement(By.xpath("//span[text()='USCIS External Account']")));
		Utils.sleep(1);
		//select nothing . just press next
		select_radio_and_next(0);
		fill_account_form_and_save("Test External Account "+Constants.getRamdomId());
	}
	
	public void create_external_contact() throws InterruptedException {
		driver.navigate().refresh();
		Utils.sleep(5);
		Utils.scrollWindow(driver,180);
		//driver.findElements(By.className("forceRelatedListSingleContainer")).get(0).findElement(By.cssSelector("a[title='New']")).click();
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElements(By.className("forceRelatedListSingleContainer")).get(0).findElement(By.cssSelector("a[title='New']")));
		Utils.sleep(3);
		
		select_radio_and_next(0);
		
		Utils.sleep(3);
		
		fill_contact_form_and_save2();
	}
	
	/**
	 * common method 
	 */
	private void open_account_tab_and_click_new() {
		Utils.sleep(3);
		
		//click Account tab button
		accountTab.click();
		
		Utils.sleep(3);
		
		//click 'New' buton under Account
		newButton.click();
		
		Utils.sleep(3);
	}
	
	/**
	 * Account form is similar to both account types. So, making it common
	 */
	private void fill_account_form_and_save(String actionName) {
		
		//Fill Account Name
		WebElement accNameLabel = driver.findElement(By.xpath("//label[.//span[text()='Account Name']]"));
		
		driver.findElement(By.id(accNameLabel.getAttribute("for"))).sendKeys(actionName);
		
		//Press save
		//List<WebElement> footerButtons = driver.findElement(By.className("modal-footer")).findElements(By.tagName("button"));
		List<WebElement> footerButtons = driver.findElements(By.xpath("//span[text()='Save']/parent::button"));
		footerButtons.get(1).click();
		
		Utils.sleep(3);
	}
	
	/**
	 * Contact form is similar to both account types. So, making it common
	 */
	private void fill_contact_form_and_save() {
		
		//Fill first name
//		WebElement firstNameLabel = driver.findElement(By.xpath("//label[.//span[text()='First Name']]"));
//		
//		driver.findElement(By.id(firstNameLabel.getAttribute("for"))).sendKeys("Test Automation");
		
		//Fill Last Name
		WebElement lastNameLabel = driver.findElement(By.xpath("//label[.//span[text()='Last Name']]"));
		
		driver.findElement(By.id(lastNameLabel.getAttribute("for"))).sendKeys("Test Automation Internal Contact");
		
		//Fill Email
		WebElement emailLabel = driver.findElement(By.xpath("//label[.//span[text()='Email']]"));
		
		driver.findElement(By.id(emailLabel.getAttribute("for"))).sendKeys("zabid"+Constants.getRamdomId()+"@acumensolutions.com");
		
		Utils.sleep(2);
		
		//Press save
		//List<WebElement> footerButtons = driver.findElement(By.className("modal-footer")).findElements(By.tagName("button"));
		List<WebElement> footerButtons = driver.findElements(By.xpath("//span[text()='Save']/parent::button"));
		try {
			footerButtons.get(2).click();
			} catch (Exception e) {
				footerButtons.get(1).click();	
			}
		
		Utils.sleep(4);
	}

	private void fill_contact_form_and_save2() {
		
		//Fill first name
//		WebElement firstNameLabel = driver.findElement(By.xpath("//label[.//span[text()='First Name']]"));
//		
//		driver.findElement(By.id(firstNameLabel.getAttribute("for"))).sendKeys("Test Automation");
		
		//Fill Last Name
		WebElement lastNameLabel = driver.findElement(By.xpath("//label[.//span[text()='Last Name']]"));
		
		driver.findElement(By.id(lastNameLabel.getAttribute("for"))).sendKeys("Test Automation External Contact");
		
		//Fill Email
		WebElement emailLabel = driver.findElement(By.xpath("//label[.//span[text()='Email']]"));
		
		driver.findElement(By.id(emailLabel.getAttribute("for"))).sendKeys("zabid"+Constants.getRamdomId()+"@acumensolutions.com");
		
		Utils.sleep(2);
		
		//Press save
		//List<WebElement> footerButtons = driver.findElement(By.className("modal-footer")).findElements(By.tagName("button"));
		List<WebElement> footerButtons = driver.findElements(By.xpath("//span[text()='Save']/parent::button"));
		try {
		footerButtons.get(1).click();
		} catch (Exception e) {
			driver.findElement(By.xpath("//button[@title='Save']")).click();
		}
		
		Utils.sleep(4);
	}
	/**
	 * common method
	 * @param num
	 */
	private void select_radio_and_next(int num) {
		//Clicking internal contact
		List<WebElement> radioList = driver.findElements(By.cssSelector("input[type='radio']"));
		
		radioList.get(num).findElement(By.xpath("following-sibling::span")).click();
		
		Utils.sleep(3);
		
		WebElement footer = driver.findElement(By.className("forceChangeRecordTypeFooter"));
		
		//footer.findElements(By.tagName("button")).get(1).click();
		driver.findElement(By.xpath("//span[text()='Next']/parent::button")).click();
		
		Utils.sleep(2);
	}
	
}
