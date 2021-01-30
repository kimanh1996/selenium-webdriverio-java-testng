package app;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic02_Xpath_CSS_Part_IV {
	WebDriver driver;
	String firstName = null;
	String middleName = null;
	String lastName = null;
	String email = null;
	String password = null;
	String fullName = null;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void tc01_loginEmptyWithEmailAndPassWord() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"email\"]/following-sibling::div")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"pass\"]/following-sibling::div")).getText(), "This is a required field.");

	}
	@Test
	public void tc02_invalidEmail() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123.1234@123.21");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void t03_checkLengthPassWord() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@mail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void t04_checkPassWord() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@mail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText(),"Invalid login or password.");
		
	}
	
	@Test
	public void t05_createNewAccount() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		WebElement elementFirstName = driver.findElement(By.id("firstname"));
		elementFirstName.sendKeys("tran");
		firstName = elementFirstName.getAttribute("value");
		
		WebElement elementMiddleName = driver.findElement(By.id("middlename"));
		elementMiddleName.sendKeys("Kim");
		middleName = elementMiddleName.getAttribute("value");
		
		WebElement elementLastName = driver.findElement(By.id("lastname"));
		elementLastName.sendKeys("Anh");
		lastName = elementLastName.getAttribute("value");
		
		WebElement elementEmail = driver.findElement(By.id("email_address"));
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		elementEmail.sendKeys("kimanh" + randomInt + "@gmail.com");
		email = elementEmail.getAttribute("value");
		
		WebElement elementPassWord = driver.findElement(By.id("password"));
		elementPassWord.sendKeys("123456");
		password = elementPassWord.getAttribute("value");
		
		WebElement elementconfirmation = driver.findElement(By.id("confirmation"));
		elementconfirmation.sendKeys("123456");
		
		driver.findElement(By.xpath("//div[@class='buttons-set']//button[@class='button']")).click();
		
		WebElement elementInfo = driver.findElement(By.xpath("//a[text()=\"Change Password\"]/parent::p"));
		String infoFullName = elementInfo.getText();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText(),"Thank you for registering with Main Website Store.");

		fullName = firstName +" " + middleName +" " + lastName;
		Assert.assertTrue(infoFullName.contains(fullName));
		Assert.assertTrue(infoFullName.contains(email));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.cssSelector("#header-account li:last-child")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='main-container col2-right-layout']//h2[contains(text(), \"This is demo site for\")]")).getText(), "THIS IS DEMO SITE FOR   ");
		
	}
	
	@Test
	public void t06_check_Login_Email_PassWord() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()=\"My Account\"]")).click();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"page-title\"]/h1")).getText(),"MY DASHBOARD" );
		Assert.assertTrue(driver.findElement(By.cssSelector(".hello >strong")).getText().contains("Hello,"+fullName +"!"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = \"Change Password\"]/parent::p")).getText().contains(fullName));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text() = \"Change Password\"]/parent::p")).getText().contains(email));
		
		
	}
	
	

	@AfterClass
	public void afterClass() {
	}

}
