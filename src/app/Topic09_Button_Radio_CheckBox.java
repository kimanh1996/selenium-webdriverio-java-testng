package app;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic09_Button_Radio_CheckBox {

	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		expliciWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void T01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//ul[@id=\"popup-login-tab_list\"]//a[text()=\"Đăng nhập\"]")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//form//button[@title=\"Đăng nhập\"]")).isEnabled());
		driver.findElement(By.xpath("//input[@id=\"login_username\"]")).sendKeys("0965042455");
		driver.findElement(By.xpath("//input[@id=\"login_password\"]")).sendKeys("Anh@09876");
		Assert.assertTrue(driver.findElement(By.xpath("//form//button[@title=\"Đăng nhập\"]")).isEnabled());
		driver.navigate().refresh();
		driver.findElement(By.xpath("//ul[@id=\"popup-login-tab_list\"]//a[text()=\"Đăng nhập\"]")).click();
		removeDisabledByJS(By.xpath("//form//button[@title=\"Đăng nhập\"]"));
		driver.findElement(By.xpath("//form//button[@title=\"Đăng nhập\"]")).click();
		String messError = driver
				.findElement(By.xpath("//input[@id=\"login_username\"]/parent:: div/following-sibling::div")).getText();
		Assert.assertEquals("Thông tin này không thể để trống", messError);
		String messErrorPass = driver
				.findElement(By.xpath("//input[@id=\"login_password\"]/parent:: div/following-sibling::div")).getText();
		Assert.assertEquals("Thông tin này không thể để trống", messErrorPass);
	}

	// @Test
	public void T02_checkBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By checkBox = By.xpath("//label[text()=\"Dual-zone air conditioning\"]/preceding-sibling::input");
		driver.findElement(checkBox).click();
		Assert.assertTrue(driver.findElement(checkBox).isSelected());
		driver.findElement(checkBox).click();
		Assert.assertFalse(driver.findElement(checkBox).isSelected());

		sleepElement(5);

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By radio = By.xpath("//input[@id=\"engine3\"]");
		driver.findElement(radio).click();
		if (!driver.findElement(radio).isSelected()) {
			driver.findElement(radio).click();
		}

	}

	@Test
	public void T03_CustomCheckboxRadio() {
		driver.get("https://material.angular.io/components/radio/examples");
		By selected = By.xpath("//input[@value=\"Summer\"]/parent::span");
		driver.findElement(selected).click();
		boolean check = driver.findElement(By.xpath("//input[@type=\"radio\" and @value=\"Summer\"]")).isSelected();
		Assert.assertTrue(check);
		if (!check) {
			driver.findElement(selected).click();
		}

	}

	@Test
	public void T03_CheckBox01() {

		driver.get("https://material.angular.io/components/checkbox/examples");
		By selected1 = By.xpath("//input[@id=\"mat-checkbox-1-input\"]/parent::span");
		By selected2 = By.xpath("//input[@id=\"mat-checkbox-2-input\"]/parent::span");
		driver.findElement(selected1).click();
		driver.findElement(selected2).click();
		boolean check1 = driver.findElement(By.xpath("//input[@id=\"mat-checkbox-1-input\"]")).isSelected();
		boolean check2 = driver.findElement(By.xpath("//input[@id=\"mat-checkbox-2-input\"]")).isSelected();
		Assert.assertTrue(check1);
		Assert.assertTrue(check2);
		driver.findElement(selected1).click();
		driver.findElement(selected2).click();
		Assert.assertFalse(check1);
		Assert.assertFalse(check2);

	}

	@Test
	public void T04_CustomerCheckBoxRadio() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By element = By.xpath("//div[@data-value=\"Cần Thơ\" and @aria-checked=\"false\" ]");
		Assert.assertTrue(driver.findElement(element).isDisplayed());
		driver.findElement(element).click();
		By elementClick = By.xpath("//div[@data-value=\"Cần Thơ\" and @aria-checked=\"true\" ]");
		Assert.assertTrue(driver.findElement(elementClick).isDisplayed());
	}

	@Test
	public void T05_Accept_alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You clicked: Ok");

		driver.findElement(By.xpath("//button[text()=\"Click for JS Confirm\"]")).click();
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id=\"result\"]")).getText(), "You clicked: Cancel");

	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		
		String alertText = "Automation Test 2021";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert = expliciWait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		sleepInSecond(2);
		
		alert.sendKeys(alertText);
		
		sleepInSecond(2);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + alertText);
		
	}
	
	@Test
	public void TC_04_Authentication_Alert() {

		// hrrp://username:password@
		
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		
		driver.get("http://the-internet.herokuapp.com/");

		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getCredentialToUrl(url, "admin", "admin"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCredentialToUrl(String url, String username, String password) {
		
		 String[] newUrl = url.split("//");
		 
		 url = newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
		 
		 return url;
	}
	public void sleepElement(long secord) {
		try {
			Thread.sleep(1000 * secord);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeDisabledByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);

	}

	@AfterClass
	public void afterClass() {
	}

}
