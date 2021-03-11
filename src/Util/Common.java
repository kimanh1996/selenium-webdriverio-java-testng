package Util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Common {
	WebDriver driver = new FirefoxDriver();

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

}
