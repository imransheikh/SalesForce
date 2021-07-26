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

	@FindBy(id = "sign_in_username")
	WebElement userNameTextBox;

	@FindBy(id = "identifierNext")
	WebElement nextButtonEl;

	@FindBy(id = "sign_in_password")
	WebElement passwordTextBox;

	@FindBy(name = "commit")
	WebElement loginButton;

	@FindBy(xpath = "//div[contains(text(),'COMPOSE')]")
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

		fill_custom_username_password(userNameCustom, pwd);

	}

	public void compose_and_send_email_with_attachment(String to, String subject, String msg, String attachmentPath)
			throws Exception {
		composeEmailButton.click();

		Thread.sleep(2000);

		windowObj.findElement(emailToTextBox).sendKeys(to);

		windowObj.findElement(emailSubjectTextBox).sendKeys(subject);

		WebElement element = windowObj.findElement(emailBodyObj);
		element.clear();
		element.sendKeys(msg);

		attach_file_and_send(attachmentPath);

	}

	private void attach_file_and_send(String attachmentPath) throws Exception {
		// click attachment
		driver.findElement(attachmentBtn).click();

		Thread.sleep(5000);

		StringSelection s = new StringSelection(attachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();

		if (Constants.isWindows()) {
			// For windows
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);

			Thread.sleep(3000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} else {
			// Cmd + Tab is needed since it launches a Java app and the browser looses focus

			robot.keyPress(KeyEvent.VK_META);

			robot.keyPress(KeyEvent.VK_TAB);

			robot.keyRelease(KeyEvent.VK_META);

			robot.keyRelease(KeyEvent.VK_TAB);

			robot.keyPress(KeyEvent.VK_META);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_SHIFT);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_G);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_META);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_G);
			Thread.sleep(1000);

			// Paste the clipboard value
			robot.keyPress(KeyEvent.VK_META);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_META);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);

			// Press Enter key to close the Goto window and Upload window
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		}

		// switch back
		driver.switchTo().activeElement();

		Thread.sleep(5000);

		press_send_button(robot);
	}

	private void press_send_button(Robot robot) throws Exception {
		// Press Enter key to close the Goto window and Upload window
		robot.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(500);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
	}

	private void fillUserNameAndNext(String userName) {
		try {
			emailTextBox.sendKeys(userName);
			Thread.sleep(1000);
			nextButtonEl.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void fill_custom_username_password(String userNameCustom, String password) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(userNameTextBox));
				userNameTextBox.sendKeys(userNameCustom);
				Thread.sleep(1000);

				passwordTextBox.sendKeys(password);
			} catch (Exception e) {

			}
			loginButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reply_with_attachment(String msg, String attachment) throws Exception {
		driver.findElement(By.cssSelector("div[data-tooltip='More']")).click();

		driver.findElement(By.xpath("//div[contains(text(), 'Reply')]")).click();

		WebElement element = driver.findElement(emailBodyObj);
		element.clear();
		element.sendKeys(msg);

		attach_file_and_send(attachment);

	}

	public void open_received_email() throws Exception {
		// Assuming gmail opned
		// loop
		boolean loop = true;
		WebElement email = null;
		int counter = 0;
		while (loop) {
			Utils.sleep(5);
			// check first message subject
			List<WebElement> messages = driver.findElements(By.className("bog"));
			// latest message
			email = messages.get(0);
			String subject = email.getText();
			if (email.getAttribute("outerHTML").contains("<b>") && subject.contains(Constants.email_subject)) {
				loop = false;
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

		// Open email
		email.findElement(By.xpath("..")).findElement(By.xpath("..")).click();
	}

}
