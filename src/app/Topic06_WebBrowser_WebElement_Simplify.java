package app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_WebElement_Simplify {

	WebDriver driver;
	By email = By.id("mail");
	By age = By.id("under_18");
	By education = By.id("edu");
	By job1 = By.id("job1");
	By job2 = By.id("job2");
	By development = By.id("development");
	By slider = By.id("slider-1");
	By password = By.id("password");
	By userAge = By.id("radio-disabled");
	By biography = By.id("bio");
	By job3 = By.id("job3");
	By checkDisbaled = By.id("check-disbaled");
	By slider2 = By.id("slider-2");
	By java = By.id("java");
	By email_new = By.id("email");
	By userName = By.name("username");
	By passwordRegister = By.name("password");

	String url = "https://automationfc.github.io/basic-form/index.html";
	String urlRegister = "https://login.mailchimp.com/signup/";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void T01_Checkdisplayed() {
		System.out.println("===T01_Checkdisplayed===");
		driver.get(url);

		if (verifyDisplayed(email)) {
			sendKeyElement(email, "anhttk3@gmail.com");
			System.out.println("email displayed");

		}
		if (verifyDisplayed(education)) {
			sendKeyElement(education, "MTA");
			System.out.println("education displayed");
		}
		if (verifyDisplayed(age)) {
			clickElement(age);
		}
	}

	@Test
	public void T02_checkEnabled() {
		System.out.println("===T02_checkEnabled===");
		driver.get(url);
		if (verifyEnabled(email)) {
			System.out.println("Email enabled");
		}
		if (verifyEnabled(age)) {
			System.out.println("Age enbled");
		}
		if (verifyEnabled(education)) {
			System.out.println("education enbled");
		}
		if (verifyEnabled(job1)) {
			System.out.println("job1 enabled");
		}
		if (verifyEnabled(job2)) {
			System.out.println("job2 enabled");
		}
		if (verifyEnabled(slider)) {
			System.out.println("slider enabled");
		}
		if (!verifyEnabled(password)) {
			System.out.println("password disabled");
		}
		if (!verifyEnabled(userAge)) {
			System.out.println("Age Radio button is disabled");
		}
		if (!verifyEnabled(biography)) {
			System.out.println("Biopraphy is disabled");
		}
		if (!verifyEnabled(job3)) {
			System.out.println("Job is disabled");
		}
		if (!verifyEnabled(checkDisbaled)) {
			System.out.println("checkbox is disabled");
		}
		if (!verifyEnabled(slider2)) {
			System.out.println("slider is disabled");
		}
	}

	@Test
	public void T03_verifySelected() {
		System.out.println("===T03_verifySelected===");
		driver.get(url);
		clickElement(age);
		clickElement(java);
		if (verifySelected(age)) {
			System.out.println("Under 18 selected");
		}
		if (verifySelected(java)) {
			System.out.println("Java is selected");
		}
		clickElement(java);
		if (!verifySelected(java)) {
			System.out.println("Java de-selected");
		}

	}

	@Test
	public void T04_RegisterFuntionAtMailChimp() {
		driver.get(urlRegister);

		sendKeyElement(email_new, "anh123@gmail,com");

		sendKeyElement(userName, "tran anh");
		sendKeyElement(passwordRegister, "1234");
	    Assert.assertTrue(verifyEnabled(By.xpath("//li[@class=\"number-char completed\"]")));
	    Assert.assertFalse(verifyEnabled(By.xpath("//button[@id=\"create-account\"]")));
	    sendKeyElement(passwordRegister, "abc");
	    Assert.assertTrue(verifyEnabled(By.xpath("//li[@class=\"lowercase-char completed\"]")));
	    Assert.assertFalse(verifyEnabled(By.xpath("//button[@id=\"create-account\"]")));
	    
	    sendKeyElement(passwordRegister, "ABC");
	    Assert.assertTrue(verifyEnabled(By.xpath("//li[@class=\"uppercase-char completed\"]")));
	    Assert.assertFalse(verifyEnabled(By.xpath("//button[@id=\"create-account\"]")));
	    
	    sendKeyElement(passwordRegister, ".@");
	    Assert.assertTrue(verifyEnabled(By.xpath("//li[@class=\"special-char completed\"]")));
	    Assert.assertFalse(verifyEnabled(By.xpath("//button[@id=\"create-account\"]")));
	    
	    sendKeyElement(passwordRegister, "abc");
	    Assert.assertTrue(verifyEnabled(By.xpath("//li[@class=\"lowercase-char completed\"]")));
	    Assert.assertFalse(verifyEnabled(By.xpath("//button[@id=\"create-account\"]")));
	    
	    
		
	}

	public boolean verifyDisplayed(By by) {
		if (!driver.findElement(by).isDisplayed()) {
			return false;
		}
		return true;
	}

	public boolean verifyEnabled(By by) {
		if (verifyDisplayed(by)) {
			if (!driver.findElement(by).isEnabled()) {
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean verifySelected(By by) {
		if (verifyDisplayed(by) && verifyEnabled(by)) {
			if (!driver.findElement(by).isSelected()) {
				return false;
			}
			return true;
		}
		return false;
	}

	public void sendKeyElement(By by, String data) {
		if (verifyDisplayed(by) && verifyEnabled(by)) {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(data);
		}

	}

	public void clickElement(By by) {
		driver.findElement(by).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
