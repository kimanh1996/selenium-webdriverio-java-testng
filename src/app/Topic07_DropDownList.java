package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Util.Common;

public class Topic07_DropDownList {
	WebDriver driver;
	

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void T01_htmlDropDown() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Select selectJob = new Select(driver.findElement(By.cssSelector("select[id='job1']")));
		Assert.assertFalse(selectJob.isMultiple());
		selectJob.selectByVisibleText("Mobile Testing");
		Assert.assertEquals("Mobile Testing", selectJob.getFirstSelectedOption().getText());
		selectJob.selectByValue("manual");
		Assert.assertEquals("Manual Testing", selectJob.getFirstSelectedOption().getText());
		selectJob.selectByIndex(9);
		Assert.assertEquals("Functional UI Testing", selectJob.getFirstSelectedOption().getText());
		Assert.assertEquals(10, selectJob.getOptions().size());

		// JOB2
		Select selectJob2 = new Select(driver.findElement(By.cssSelector("select[id='job2']")));
		Assert.assertTrue(selectJob2.isMultiple());
		List<String> listJob = new ArrayList<>();
		selectJob2.selectByVisibleText("Automation");
		listJob.add("Automation");
		selectJob2.selectByVisibleText("Mobile");
		listJob.add("Mobile");
		selectJob2.selectByVisibleText("Desktop");
		listJob.add("Desktop");
		List<WebElement> listSelect = selectJob2.getAllSelectedOptions();
		List<String> listString = new ArrayList<>();
		for (WebElement we : listSelect) {
			listString.add(we.getText());
		}
		Assert.assertEquals(listString, listJob);
		selectJob2.deselectAll();
		Assert.assertEquals(selectJob2.getAllSelectedOptions().size(),0);

	}
	@Test
	public void T02_Register() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		senKeyData(By.id("FirstName"), "tran");
		senKeyData(By.id("LastName"), "anh");
		Select day = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		day.selectByVisibleText("1");
		Assert.assertEquals(day.getOptions().size(), 32);
		Select month = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		month.selectByVisibleText("May");
		Assert.assertEquals(month.getOptions().size(), 13);
		Select year = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		year.selectByVisibleText("1980");
		Assert.assertEquals(year.getOptions().size(), 112);
		driver.findElement(By.name("register-button")).click();
		senKeyData(By.id("Email"), "ab@gmail.com");
		senKeyData(By.id("Password"), "123456");
		senKeyData(By.id("ConfirmPassword"), "123456");
		driver.findElement(By.id("register-button")).click();
	    
	}
	public boolean checkDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			return true;
		}

		return false;

	}

	public boolean checkEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			return true;
		}
		return false;
	}

	public boolean checkSelected(By by) {

		if (checkDisplayed(by) && checkEnabled(by) && driver.findElement(by).isSelected()) {
			return true;
		}
		return false;
	}

	public void senKeyData(By by, String data) {

		if (checkDisplayed(by) && checkEnabled(by)) {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(data);
		}
	}
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
	}

}
