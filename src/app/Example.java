package app;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Example {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void tc01_loginEmptyWithEmailAndPassWord() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"email\"]/following-sibling::div")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id=\"pass\"]/following-sibling::div")).getText(), "This is a required field.");

	}
	//@Test
	public void tc02_invalidEmail() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123.1234@123.21");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	//@Test
	public void t03_checkLengthPassWord() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@mail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='pass']/following-sibling::div")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	//@Test
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
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		driver.findElement(By.id("firstname")).sendKeys("tran");
		driver.findElement(By.id("middlename")).sendKeys("kim");
		driver.findElement(By.id("lastname")).sendKeys("anh");
		driver.findElement(By.id("email_address")).sendKeys("anh024@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id=\"form-validate\"]//button[@title=\"Register\"]")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"success-msg\"]//span")).getText(),"Thank you for registering with Main Website Store.");
	    System.out.println(driver.findElement(By.xpath("//a[text()=\"Change Password\"]/parent::p")).getText());
//		driver.findElement(By.xpath("//a[text()=\"click here\"]")).click();
//		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("anh123@gmail.com");
//		driver.findElement(By.xpath("//button[@title=\"Submit\"]")).click();
//		
//		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
//		driver.findElement(By.id("firstname")).sendKeys("tran");
//		driver.findElement(By.id("middlename")).sendKeys("kim");
//		driver.findElement(By.id("lastname")).sendKeys("anh");
//		driver.findElement(By.id("email_address")).sendKeys("anh123@gmail.com");
//		driver.findElement(By.id("password")).sendKeys("123456");
//		driver.findElement(By.id("confirmation")).sendKeys("123456");
//		driver.findElement(By.xpath("//form[@id=\"form-validate\"]//button[@title=\"Register\"]")).click();
		
		
	}

	@AfterClass
	public void afterClass() {
	}

}
