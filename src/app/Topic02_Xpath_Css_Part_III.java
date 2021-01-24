package app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic02_Xpath_Css_Part_III {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/login");

	}

	@Test
	public void checkValidate() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).isDisplayed();
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys("abc");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).clear();
		driver.findElement(By.cssSelector("input[id='small-searchterms']")).sendKeys("cvb");
		
		Thread.sleep(3000);
		//click button
		driver.findElement(By.xpath("//input[@id='small-searchterms']//following-sibling::input")).click();
		//driver.findElement(By.xpath("//input[@class='button-1 search-box-button']")).click();
		
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
