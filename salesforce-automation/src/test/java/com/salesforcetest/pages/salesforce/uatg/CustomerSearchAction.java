package com.salesforcetest.pages.salesforce.uatg;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.salesforcetest.shared.Utils;

public class CustomerSearchAction {

	private WebDriver driver;

	public CustomerSearchAction(WebDriver driver) {
		this.driver = driver;
	}

	public void login_internal_user(String user) {

		Utils.sleep(5);

		driver.findElement(By.id("phSearchInput")).sendKeys(user);

		Utils.sleep(5);

		WebElement resultContainer = driver.findElement(By.id("phSearchInput_autoCompleteBoxId"));

		resultContainer.findElements(By.className("autoCompleteRowLink")).get(0).click();

		Utils.sleep(3);

		// Switch to iframe
		WebElement iframe1 = driver.findElement(By.className("sd_secondary_container"))
				.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe1);

		driver.findElement(By.id("moderatorMutton")).click();

		Utils.sleep(2);

		driver.findElement(By.id("USER_DETAIL")).click();

		Utils.sleep(3);

		driver.findElement(By.xpath("//td[text()='Email']")).click();

		Utils.sleep(3);

		driver.findElement(By.name("login")).click();

		driver.switchTo().defaultContent();

		Utils.sleep(3);

	}

	public void search_customer(String customer) {
		// Back button
		driver.findElement(By.linkText("Back to USCIS CRM")).click();

		Utils.sleep(3);

		// Close all tabs
		driver.findElement(By.className("sd_primary_tabstrip")).findElement(By.xpath("div[1]")).click();

		driver.findElement(By.linkText("Close all primary tabs")).click();

		Utils.sleep(3);

		// After coming into customer search page
		WebElement navigator = driver.findElement(By.className("support-servicedesk-navigator"));

		WebElement button = navigator.findElement(By.xpath("table/tbody/tr[2]/td[2]/em/button"));
		
		//dropdown button click
		WebElement followingSibling = button.findElement(By.xpath("following-sibling::*"));
		followingSibling.click();
		
		//menu item
		Actions build = new Actions(driver);
		build.moveToElement(button).moveByOffset(124, 0).click().build().perform();

		Utils.sleep(3);

		// menu item
		driver.findElement(By.id("navigator-sbmenu")).findElement(By.linkText("Customer Search")).click();

		Utils.sleep(3);

	}

	public void update_customer() {
		// switching to iFrame
		WebElement iFrame = driver.findElement(By.className("sd_nav_tabpanel_body")).findElement(By.tagName("iframe"));

		driver.switchTo().frame(iFrame);

		WebElement detailTable = driver.findElement(By.className("detailList")).findElement(By.tagName("tbody"));

		detailTable.findElement(By.xpath("tr[1]/td[1]/input")).sendKeys("A200453283");

		Utils.sleep(2);

		driver.findElement(By.id("asyncSearchButton")).click();

		Utils.sleep(2);

		WebElement resultList = driver.findElement(By.id("asynchronousResults"))
				.findElement(By.id("asynchAccountResultTable")).findElement(By.tagName("tbody"));

		Utils.sleep(2);

		resultList.findElement(By.xpath("tr[1]/td[1]/input")).click();

		Utils.sleep(2);

		// Assert message
		WebElement messageEl;
		try {
			messageEl = Utils.fluentWait(By.className("messageCell"), driver, 150, 5);
			String expected = "Successfully updated Customer profile.";
			String actualMessage = messageEl.getAttribute("outerHTML");

			assertTrue(actualMessage.contains(expected));
			
			Utils.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.switchTo().defaultContent();
	}

}
