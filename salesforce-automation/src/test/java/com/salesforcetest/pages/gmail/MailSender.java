package com.salesforcetest.pages.gmail;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforcetest.shared.Constants;
import com.salesforcetest.shared.Utils;

public class MailSender {
	WebDriver driver;

	@FindBy(id = "identifierId")
	WebElement emailTextBox;

	@FindBy(id = "password")
	WebElement userNameTextBox;

	@FindBy(id = "identifierNext")
	WebElement nextButtonEl;

	@FindBy(id = "password")
	WebElement passwordTextBox;

	@FindBy(name = "commit")
	WebElement loginButton;

	//@FindBy(xpath = "//div[contains(text(),'COMPOSE')]")
	@FindBy(className = "T-I-KE")
	WebElement composeEmailButton;

	@FindBy(className = "AD")
	WebElement windowObj;

	private By emailToTextBox = By.xpath("//textarea[@name='to']");

	private By emailSubjectTextBox = By.xpath("//input[@name='subjectbox']");

	private By emailBodyObj = By.className("Am");

	private By attachmentBtn = By.cssSelector("[command='Files']");

	public MailSender(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void login(String url, String userName, String userNameCustom, String pwd) {

		driver.get(url);

		fillUserNameAndNext(userName);

		//fill_custom_username_password(userNameCustom, pwd);

		click_verification_button();
		WebDriverWait wait = new WebDriverWait (driver, 14);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys(userNameCustom+"@acumensolutions.com");
		Utils.sleep(2);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		//ele = By.id("password");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Utils.sleep(1);
		driver.findElement(By.id("password")).sendKeys(pwd);
		Utils.sleep(1);
		driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		try {
			//ele = By.xpath("//div[text()='zabid@acumensolutions.com']");
			//ele = By.xpath("//button[text()='skip']");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='zabid@acumensolutions.com']")));
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
			//driver.findElement(By.xpath("//button[text()='skip']")).click();
		} catch (Exception e) {
			
		}
		Utils.sleep(2);
	}

	private void click_verification_button() {
		try {
			Utils.sleep(2);
			driver.findElement(By.xpath("//span[text()='Continue']")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void compose_and_send_email_with_attachment(String to, String subject, String msg, String attachmentPath)
			throws Exception {
		Utils.sleep(2);
		composeEmailButton.click();

		Utils.sleep(2);

		windowObj.findElement(emailToTextBox).sendKeys(to);

		windowObj.findElement(emailSubjectTextBox).sendKeys(subject);

		WebElement element = windowObj.findElement(emailBodyObj);
		element.clear();
		element.sendKeys(msg);
		
		//Robot robot = new Robot();

		attach_file_and_send(attachmentPath, true);
		//press_send_button(robot);

		Utils.sleep(5);
	}

	private void attach_file_and_send(String attachmentPath, boolean pressTab) throws Exception {
		// click attachment
		driver.findElement(attachmentBtn).click();

		StringSelection s = new StringSelection(attachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();

		Utils.sleep(2);
		if (Constants.isWindows()) {
			// For windows
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			Utils.sleep(3);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} else {
			// Cmd + Tab is needed since it launches a Java app and the browser looses focus
			Utils.sleep(5);
			if (pressTab) {

				robot.keyPress(KeyEvent.VK_META);

				robot.keyPress(KeyEvent.VK_TAB);

				robot.keyRelease(KeyEvent.VK_META);

				robot.keyRelease(KeyEvent.VK_TAB);

			}

			robot.keyPress(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_SHIFT);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_G);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_G);
			Utils.sleep(1);

			// Paste the clipboard value
			robot.keyPress(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_V);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_V);
			Utils.sleep(1);

			// Press Enter key to close the Goto window and Upload window
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(1);
		}

		// switch back
		driver.switchTo().activeElement();

		Utils.sleep(1);

		press_send_button(robot);
	}

	private void press_send_button(Robot robot) throws Exception {
		// Press Enter key to close the Goto window and Upload window
		if (Constants.isWindows()) {

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(500);

		} else {
			robot.keyPress(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyRelease(KeyEvent.VK_META);
			Utils.sleep(1);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Utils.sleep(1);
		}
		Utils.sleep(1);
	}

	private void fillUserNameAndNext(String userName) {
		try {
			emailTextBox.sendKeys(userName);
			Utils.sleep(1);
			nextButtonEl.click();
			Utils.sleep(2);
			//driver.findElement(By.id("username")).sendKeys(userName);
			//driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fill_custom_username_password(String userNameCustom, String password) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			try {
				
				wait.until(ExpectedConditions.elementToBeClickable(userNameTextBox));
				//userNameTextBox.sendKeys(userNameCustom);
				Utils.sleep(1);

				passwordTextBox.sendKeys(password);
			} catch (Exception e) {

			}
			//loginButton.click();
			driver.findElement(By.xpath("//span[text()='Continue']/parent::button")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reply_with_attachment(String msg, String attachment) throws Exception {
		driver.findElement(By.cssSelector("div[data-tooltip='More']")).click();

		driver.findElement(By.xpath("//div[contains(text(), 'Reply')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//div[contains(@class,'aoD hl')]")).click();
		Utils.sleep(2);
		driver.findElement(By.xpath("//input[@tabindex='-1']/following-sibling::textarea")).sendKeys(Constants.incident_report_email);
		Utils.sleep(1);
		//driver.findElement(By.className("Am")).sendKeys("Automated reply mail to create child service item.");
		Utils.sleep(1);
		WebElement element = driver.findElement(emailBodyObj);
		element.clear();
		element.sendKeys(msg);

		attach_file_and_send(attachment, false);
	}

	public void open_received_email(String emailSubject, boolean isNew) throws Exception {
		// Assuming gmail opened
		// loop
		WebElement email = null;
		int count = 0, count1 = 0;
		boolean loop = true, found = false;
		int counter = 0;
		Utils.sleep(2);
		driver.navigate().refresh();
		Utils.sleep(48);
		
		// Before Looping, click inbox button
		driver.findElement(By.cssSelector(".nU.n1")).findElement(By.tagName("a")).click();
		//try {
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[text()='Primary']")).click();
			Utils.sleep(2);
			//List<WebElement> messages = driver.findElements(By.className("bog"));
	
			List<WebElement> messages = driver.findElements(By.cssSelector("div.xT>div.y6>span"));
	
			for (WebElement message : messages) {
				// latest message
				String subject = message.getText();
				if (isNew) {
					//if (message.getAttribute("outerHTML").contains("\"bqe\"") && subject.contains(emailSubject)) {
					if (subject.contains(emailSubject)) {
						email = message;
						break;
					} else if (count == 8) {
						//System.out.println("I am here!!!!");
						break;
					}
					//System.out.println("Number : "+count);
					count = count+1;
				} else if (subject.contains(emailSubject)) {
					email = message;
					break;
				}
				
			}
		//} catch (Exception e) {
			//System.out.println("Number2 : "+count);
			//List<WebElement> messages = null;
			//if (count == 8) {
			driver.navigate().refresh();
			Utils.sleep(6);
			Utils.sleep(2);
			driver.findElement(By.xpath("//div[text()='Updates' and contains(@data-tooltip, 'Personal')]")).click();
			Utils.sleep(2);
			//List<WebElement> messages = driver.findElements(By.className("bog"));
			Utils.sleep(2);
			//messages = driver.findElements(By.cssSelector("div.xT>div.y6"));
			messages = driver.findElements(By.cssSelector("div.xT>div.y6>span.bog"));
			while (loop) {
				Utils.sleep(2);
				// check first message subject
				//List<WebElement> messages = driver.findElements(By.className(""));//bog
				// latest message
				email = messages.get(counter);
				String subject = email.getText();
				System.out.println(subject);
				//if (email.getAttribute("outerHTML").contains("\"bqe\"") && subject.contains(closingEmailSubject)) {
				if (subject.contains(emailSubject)) {
					loop = false;
					found = true;
					break;
				} else {
					loop = true;
				}

				if (counter == 10) {
					loop = false;
					break;
				}
				counter++;
			  }
			//}
		//}
		// Open email
		email.findElement(By.xpath("..")).findElement(By.xpath("..")).click();
	}

	public void verify_closing_email(String closingEmailSubject) throws Exception {
		// Assuming gmail opened
		// loop
		boolean loop = true, found = false;
		int counter = 0;
		driver.get(Constants.reporter_account_url);
		Utils.sleep(4);
		// Before Looping, click inbox button
		try {
		driver.findElement(By.cssSelector(".nU.n1")).findElement(By.tagName("a")).click();

		Utils.sleep(2);
		//driver.findElement(By.xpath("//div[text()='Updates']")).click();
		//driver.findElement(By.xpath("//div[text()='Updates' and contains(@data-tooltip, 'Personal')]")).click();
		driver.findElement(By.xpath("//div[text()='Primary']")).click();
		Utils.sleep(2);
		//List<WebElement> messages = driver.findElements(By.cssSelector("div.xT>div.y6"));
		List<WebElement> messages = driver.findElements(By.cssSelector("div.xT>div.y6>span"));
		while (loop) {
			Utils.sleep(2);
			// check first message subject
			//List<WebElement> messages = driver.findElements(By.className(""));//bog
			// latest message
			WebElement email = messages.get(counter);
			String subject = email.getText();
			System.out.println(subject);
			//if (email.getAttribute("outerHTML").contains("\"bqe\"") && subject.contains(closingEmailSubject)) {
			if (subject.contains(closingEmailSubject)) {
				loop = false;
				found = true;
				break;
			} else {
				loop = true;
			}

			if (counter == 10) {
				loop = false;
				break;
			}
			counter++;
		}
		if (!found) {
			throw new Exception("Closing service item email not received in reporter account.");
		}
		} catch (Exception e) {
			System.out.println(found);
		}
	}

}
