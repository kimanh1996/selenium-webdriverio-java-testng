package app;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_TextBoxTextAreaDropdown {
	WebDriver driver;
	String urlLogin = "http://demo.guru99.com/v4/";
	By userIDLogin = By.name("uid");
	By passWord = By.name("password");
	String userID ;

	// locator form add/edit
	By customerName = By.name("name");
	By genderMale = By.xpath("//input[@type=\"radio\" and @value=\"m\"]");
	By date = By.name("dob");
	By address = By.name("addr");
	By city = By.name("city");
	By state = By.name("state");
	By pin = By.name("pinno");
	By mobileNumber = By.name("telephoneno");
	By email = By.name("emailid");
	By password = By.name("password");
	By btSubmit = By.name("sub");
	
	//add data customer
	String datacustomerName = "anhttk";
	String datagenderMale = "male";
	String datadate = "2021-02-01";
	String dataaddress = "Giao Thuy Nam Dinh";
	String datacity = "Nam Dinh";
	String datastate = "Ngo Dong";
	String datapin = "123456";
	String datamobileNumber = "0965058982";
	Random random = new Random();
	int ran = random.nextInt(9999);
	String dataemail = "anhttk" + ran + "@gmail.com";
	String datapassword = "123456";
	
	//eidt data customer 
	String editaddress = "GT Nam Dinh";
	String editcity = "NamDinh";
	String editstate = "NgoDong";
	String editpin = "123409";
	String editmobileNumber = "0965002034";
	Random randomMail = new Random();
	int ranMail = randomMail.nextInt(9999);
	String editemail = "anhttk" + ranMail + "@gmail.com";
	
	//get form add success customer
	By lCustomerID = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By lCustomerName = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By lgender = By.xpath("//td[text()='Gender']/following-sibling::td");
	By lBirthdate = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By lAddress = By.xpath("//td[text()='Address']/following-sibling::td");
	By lCity = By.xpath("//td[text()='City']/following-sibling::td");
	By lState = By.xpath("//td[text()='State']/following-sibling::td");
	By lPin = By.xpath("//td[text()='Pin']/following-sibling::td");
	By lphone = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By lemail = By.xpath("//td[text()='Email']/following-sibling::td");
	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();

		driver.get(urlLogin);
		clickElement(By.xpath("//a[text()=\"here\"]"));
		Random random = new Random();
		int ran = random.nextInt(99999);
		String email = "anhttk" + ran + "@gmail.com";
		senKeyElement(By.name("emailid"), email);
		clickElement(By.name("btnLogin"));
		String userID = driver.findElement(By.xpath("//td[text()=\"User ID :\"]/following-sibling::td")).getText();
		String password = driver.findElement(By.xpath("//td[text()=\"Password :\"]/following-sibling::td")).getText();
		driver.get(urlLogin);
		senKeyElement(userIDLogin, userID);
		senKeyElement(passWord, password);
		clickElement(By.name("btnLogin"));
		String titleHome = driver
				.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).getText();
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", titleHome);
	}

	@Test
	public void T01_TextBoxTextArea() {

		// click new customer
		clickElement(By.xpath("//a[text()=\"New Customer\"]"));
		String titleAddNewCustomer = driver.findElement(By.xpath("//p[text()=\"Add New Customer\"]")).getText();
		Assert.assertEquals(titleAddNewCustomer, "Add New Customer");
		
		//add customerName
		senKeyElement(customerName, datacustomerName);
		clickElement(genderMale);
		senKeyElement(date, datadate);
		senKeyElement(address, dataaddress);
		senKeyElement(city, datacity);
		senKeyElement(state, datastate);
		senKeyElement(pin, datapin);
		senKeyElement(mobileNumber, datamobileNumber);
		senKeyElement(this.email, dataemail);
		senKeyElement(password, datapassword);
		clickElement(btSubmit);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		this.userID = driver.findElement(lCustomerID).getText();
		//verify data
		Assert.assertEquals(datacustomerName, driver.findElement(lCustomerName).getText());
		Assert.assertEquals(datagenderMale, driver.findElement(lgender).getText());
		Assert.assertEquals(datadate, driver.findElement(lBirthdate).getText());
		Assert.assertEquals(dataaddress, driver.findElement(lAddress).getText());
		Assert.assertEquals(datacity, driver.findElement(lCity).getText());
		Assert.assertEquals(datastate, driver.findElement(lState).getText());
		Assert.assertEquals(datapin, driver.findElement(lPin).getText());
		Assert.assertEquals(datamobileNumber, driver.findElement(lphone).getText());
		Assert.assertEquals(dataemail, driver.findElement(lemail).getText());
		
		//click edit 
		clickElement(By.xpath("//a[text()=\"Edit Customer\"]"));
		String titleEditCustomer = driver.findElement(By.xpath("//p[text()=\"Edit Customer Form\"]")).getText();
		Assert.assertEquals(titleEditCustomer, "Edit Customer Form");
		senKeyElement(By.name("cusid"), userID);
		clickElement(By.name("AccSubmit"));
		
		
		//verify data
		Assert.assertEquals(datacustomerName, driver.findElement(customerName).getAttribute("value"));
		Assert.assertEquals(datagenderMale, driver.findElement(By.name("gender")).getAttribute("value"));
		Assert.assertEquals(datadate, driver.findElement(date).getAttribute("value"));
		Assert.assertEquals(dataaddress, driver.findElement(address).getText());
		Assert.assertEquals(datacity, driver.findElement(city).getAttribute("value"));
		Assert.assertEquals(datastate, driver.findElement(state).getAttribute("value"));
		Assert.assertEquals(datapin, driver.findElement(pin).getAttribute("value"));
		Assert.assertEquals(datamobileNumber, driver.findElement(mobileNumber).getAttribute("value"));
		Assert.assertEquals(dataemail, driver.findElement(this.email).getAttribute("value"));
		
		//edit data
		senKeyElement(address, editaddress);
		senKeyElement(city, editcity);
		senKeyElement(state, editstate);
		senKeyElement(pin, editpin);
		senKeyElement(mobileNumber, editmobileNumber);
		senKeyElement(this.email, editemail);
		
		clickElement(btSubmit);
		
		//verify data
		
		Assert.assertEquals(editaddress, driver.findElement(lAddress).getText());
		Assert.assertEquals(editcity, driver.findElement(lCity).getText());
		Assert.assertEquals(editstate, driver.findElement(lState).getText());
		Assert.assertEquals(editpin, driver.findElement(lPin).getText());
		Assert.assertEquals(editmobileNumber, driver.findElement(lphone).getText());
		Assert.assertEquals(editemail, driver.findElement(lemail).getText());
		
		
		
		
		
		

	}

	public boolean verifyDisplayed(By by) {
		if (!driver.findElement(by).isDisplayed()) {
			return false;
		}
		return true;
	}

	public boolean verifyEnabled(By by) {
		if (verifyDisplayed(by) && driver.findElement(by).isEnabled()) {
			return true;
		}
		return false;
	}

	public boolean verifySelected(By by) {
		if (verifyDisplayed(by) && verifyEnabled(by) && driver.findElement(by).isSelected()) {
			return true;

		}
		return false;
	}

	public void senKeyElement(By by, String data) {
		if (verifyEnabled(by)) {
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(data);
		}
	}

	public void clickElement(By by) {
		if (verifyEnabled(by)) {
			driver.findElement(by).click();
		}
	}

	@AfterClass
	public void afterClass() {

		//driver.quit();
	}

}
