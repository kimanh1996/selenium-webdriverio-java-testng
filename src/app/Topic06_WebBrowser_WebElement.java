package app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_WebBrowser_WebElement {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void T01_verifyUrl() {

		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		String url = "http://live.demoguru99.com/index.php/customer/account/login/";
		Assert.assertEquals(driver.getCurrentUrl(), url);
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		String urlCreateLogin = "http://live.demoguru99.com/index.php/customer/account/create/";
		Assert.assertEquals(driver.getCurrentUrl(), urlCreateLogin);
	}

	// @Test
	public void T02_verifyTitle() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		String loginPage = "Customer Login";
		Assert.assertEquals(driver.getTitle(), loginPage);
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		String registerPage = "Create New Customer Account";
		Assert.assertEquals(driver.getTitle(), registerPage);

	}

	// @Test
	public void T03_navigateFunction() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		String registerPage = "http://live.demoguru99.com/index.php/customer/account/create/";
		Assert.assertEquals(driver.getCurrentUrl(), registerPage);
		driver.navigate().back();
		String loginPage = "http://live.demoguru99.com/index.php/customer/account/login/";
		Assert.assertEquals(driver.getCurrentUrl(), loginPage);
		driver.navigate().forward();
		String title = "Create New Customer Account";
		Assert.assertEquals(driver.getTitle(), title);

	}

	// @Test
	public void T04_getPageSource() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

	}

	// @Test
	public void T01_isDisplayedElement() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("anh@gmail.com");
			System.out.println("Element is displayed");
		} else {
			System.out.println("Element email is displayed");
		}
		if (driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed()) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("MTA");
			System.out.println("Element edu is displayed");
		} else {
			System.out.println("Element edu not displayed");
		}
		if (driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed()) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
			System.out.println("Element under_18 is displayed");
		} else {
			System.out.println("Element under_18 not displayed");
		}

	}

	// @Test
	public void T02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String enabled = "Element is enabled";
		String disabled = "Element is disabled";
		if (driver.findElement(By.xpath("//input[@id=\"mail\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//input[@type=\"radio\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//textarea[@id=\"edu\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//select[@id=\"job1\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//select[@id=\\\"job2\\\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//input[@id=\"development\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//input[@id=\"slider-1\"]")).isEnabled()) {
			System.out.println(enabled);
		} else {
			System.out.println(disabled);
		}
		if (driver.findElement(By.xpath("//input[@id=\"password\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("//textarea[@id=\"edu\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("//textarea[@id=\"edu\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("// input[@id=\"radio-disabled\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("//textarea[@id=\"bio\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("//select[@id=\"job3\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("// input[@id=\\\"check-disbaled\\\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}
		if (driver.findElement(By.xpath("// input[@id=\\\"slider-2\\\"]")).isDisplayed()) {
			System.out.println(disabled);
		} else {
			System.out.println(enabled);
		}

	}

	// @Test
	public void T03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"java\"]")).click();
		if (driver.findElement(By.xpath("//input[@id='under_18']")).isSelected()) {
			System.out.println("under_18 is selected");
		}
		if (driver.findElement(By.xpath("//input[@type=\\\"checkbox\\\" and @value=\\\"java\\\"]")).isSelected()) {
			System.out.println("java is selected");
		}
		driver.findElement(By.xpath("//input[@type=\"checkbox\" and @value=\"java\"]")).click();
		if (!driver.findElement(By.xpath("//input[@type=\\\"checkbox\\\" and @value=\\\"java\\\"]")).isSelected()) {
			System.out.println("java not selected");
		}
	}

	@Test
	public void T04_RegisterFunctionAtMailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("anh@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"new_username\"]")).sendKeys("anh123");
		driver.findElement(By.xpath("//input[@id=\"new_password\"]")).sendKeys("a");
		if (driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isDisplayed()) {
			System.out.println("element lowercase-char displayed");
		}
		driver.findElement(By.xpath("//input[@id=\"new_password\"]")).sendKeys("A");
		if (driver.findElement(By.xpath("//li[@class=\"uppercase-char completed\"]")).isDisplayed()) {
			System.out.println("element uppercase-char displayed");
		}
		driver.findElement(By.xpath("//input[@id=\"new_password\"]")).sendKeys("1");
		if (driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isDisplayed()) {
			System.out.println("element number-char displayed");
		}
		driver.findElement(By.xpath("//input[@id=\"new_password\"]")).sendKeys(".");
		if (driver.findElement(By.xpath("//li[@class=\"special-char completed\"]")).isDisplayed()) {
			System.out.println("element special-char displayed");
		}
		driver.findElement(By.xpath("//input[@id=\"new_password\"]")).sendKeys("abcv");
		String passWord = driver.findElement(By.xpath("//input[@id=\"new_password\"]")).getText();
		if (driver.findElement(By.xpath("//li[@class=\"8-char completed\"]")).isDisplayed()) {
			System.out.println(passWord);
			System.out.println("element 8-char displayed");
		}

		if (driver.findElement(By.xpath("//li[@class=\"lowercase-char completed\"]")).isEnabled()
				|| driver.findElement(By.xpath("//li[@class=\"uppercase-char completed\"]")).isEnabled()
				|| driver.findElement(By.xpath("//li[@class=\"number-char completed\"]")).isEnabled()
				|| driver.findElement(By.xpath("//li[@class=\"8-char completed\"]")).isEnabled()) {
			driver.findElement(By.xpath("//button[@id=\"create-account\"]")).isDisplayed();

		}
	}

	@AfterClass
	public void afterClass() {
	}

}
