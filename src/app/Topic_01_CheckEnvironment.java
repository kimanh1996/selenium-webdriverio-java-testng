package app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

	}

	@Test
	public void checkValidate() throws InterruptedException {
		driver.get("https://login.ubuntu.com/+login");
		//click button
		driver.findElement(By.xpath("//form[@id=\"login-form\"]//button[@name=\"continue\"]")).click();
		Thread.sleep(3000);
		//
		driver.findElement(By.xpath(".//*[@id='txtFirstname']")).sendKeys("kim anh");
		Thread.sleep(3000);
		
	}
	@Test
	public void checkValidateLoginUbuntu() throws InterruptedException {
		//click button
		driver.findElement(By.xpath("//form[@id=\"frmLogin\"]//button[text()=\"ĐĂNG KÝ\"]")).click();
		Thread.sleep(3000);
		//
		driver.findElement(By.xpath(".//*[@id='txtFirstname']")).sendKeys("kim anh");
		Thread.sleep(3000);
		
		driver.findElement(By.id("txtEmail")).sendKeys("@gmail.com");
		Thread.sleep(3000);
		
		driver.findElement(By.cssSelector("input[id=txtPassword]")).sendKeys("12345678");
		Thread.sleep(3000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
