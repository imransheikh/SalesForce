package com.salesforcetest.pages.salesforce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.shared.Constants;

public class SalesforceLogin {

	private String baseUrl = Constants.salesforce_url;

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

	public void login(String userName, String pass) {
		driver.get(baseUrl);
		
		userNameTextBox.sendKeys(userName);
		
		passwordTextBox.sendKeys(pass);
		
		loginButton.click();
		
	}
}
