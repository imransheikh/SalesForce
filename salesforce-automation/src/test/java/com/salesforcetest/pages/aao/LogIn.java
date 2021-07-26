package com.salesforcetest.pages.aao;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.salesforcetest.pages.aao.manager.FileManager;
import com.salesforcetest.pages.aao.utils.*;
import com.salesforcetest.shared.Utils;

public class LogIn {
		
	
   private  WebDriver driver;
	private AaoHelper helper;
	
	@FindBy(id = "username")
	private WebElement userNameTxtBox;

	@FindBy(id = "password")
	private WebElement passwordTxtBox;
	
	@FindBy(id = "Login")
	private WebElement loginBtn;
	
	@FindBy(id="userNavLabel")
	private WebElement mnuUser;
	
	@FindBy(linkText="Logout")
	private WebElement lnkLogOut;
	
	
	public LogIn(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		helper=new AaoHelper();
	}
	
 
	
	public void AaoLogin() {
		//try {
		String userName=FileManager.getInstance().getReader().getUserId();
		String pass=FileManager.getInstance().getReader().getPassWord();
		helper.sleep(2);
		userNameTxtBox.sendKeys(userName);
		helper.sleep(1);
		passwordTxtBox.sendKeys(pass);
		helper.sleep(1);
		loginBtn.click();
		
		helper.sleep(3);
		/*} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void Aaologout() {
		helper.FindAaoElement(driver,mnuUser).click();
		helper.FindAaoElement(driver,lnkLogOut).click();
	}
}

