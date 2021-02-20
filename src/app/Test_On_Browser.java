package app;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Test_On_Browser {

	WebDriver driver ;
	
	String projectLocation = System.getProperty("user.dir");
	@Test
	public void T01_Chrome() {
		
		System.setProperty("webdriver.chrome.driver",projectLocation +"\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}
	@Test
	public void T01_FireFox() {
		// đối  với firefox 48 trở đi + selenium 3.xx   thì dùng gecko
		//System.setProperty("webdriver.chrome.driver",projectLocation+ "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("https://google.com");
		driver.quit();
	}
	
	

}
