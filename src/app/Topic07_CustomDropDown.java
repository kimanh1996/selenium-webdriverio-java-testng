package app;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_CustomDropDown {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	String[] expectedText = { "April", "July", "September" };

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MICROSECONDS);
		driver.manage().window().maximize();
	}

	 @Test
	public void T01_customDropDownJquery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectDropDownCustom("//span[@id=\"number-button\"]", "//li[@class=\"ui-menu-item\"]", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id=\"number-button\"]/span[@class=\"ui-selectmenu-text\"]")).getText(),
				"19");

	}

	 @Test
	public void T02_customDropAngular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectDropDownCustom("//ejs-dropdownlist[@id='games']", "//ul[@id=\"games_options\"]/li", "Football");
		Assert.assertEquals(getDataSelectedDropDown(), "Football");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id=\"number-button\"]/span[@class=\"ui-selectmenu-text\"]")).getText(),
				"Football");

	}

	@Test
	public void T03_customReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectDropDownCustom("//div[@role=\"listbox\"]", "//div[@role=\"option\"]", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role=\"alert\"]")).getText(), "Matt");

	}

	@Test
	public void T04_customVue() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectDropDownCustom("//div[@class=\"btn-group\"]", "//ul[@class=\"dropdown-menu\"]/li", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"dropdown-toggle\"]")).getText(), "First Option");
	}

	@Test
	public void T05_editTable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		// cach 1: keo chuot click
		// selectDropDownCustom("//div[@id='default-place']","//div[@id=\"default-place\"]/ul/li",
		// "Audi");

		// cach 2: senkey tim den cai de chon
		selectDropDownEditTable("Au", "//div[@id='default-place']/input", "//div[@id=\"default-place\"]/ul/li", "Audi");

		// cach 3 :Tab
		// selectDropDownEditTable2("//div[@id='default-place']/input","Audi");
		Assert.assertEquals(getDataEditTable(), "Audi");

	}

	@Test
	public void T07_MuiltDropDown() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectMuiltDropDownCustom("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span",
				expectedText);
		Assert.assertTrue(checkSelected(expectedText));
	}

	public void selectDropDownCustom(String parentSelect, String allItemSlect, String expectedText) {
		// click vao element dropdown cha
		driver.findElement(By.xpath(parentSelect)).click();
		// cho load cac phan tu
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allItemSlect)));
		List<WebElement> listElement = driver.findElements(By.xpath(allItemSlect));
		for (WebElement element : listElement) {
			if (element.getText().equals(expectedText)) {
				element.click();
			}
		}

	}

	public void selectDropDownEditTable(String input, String parentSelect, String allItemSlect, String expectedText) {
		driver.findElement(By.xpath(parentSelect)).clear();
		driver.findElement(By.xpath(parentSelect)).sendKeys(input);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemSlect)));
		List<WebElement> listElement = driver.findElements(By.xpath(allItemSlect));
		for (WebElement element : listElement) {
			if (element.getText().equals(expectedText)) {
				element.click();
			}
		}
	}

	public void selectDropDownEditTable2(String parentSelect, String expectedText) {
		driver.findElement(By.xpath(parentSelect)).clear();
		driver.findElement(By.xpath(parentSelect)).sendKeys(expectedText);
		driver.findElement(By.xpath(parentSelect)).sendKeys(Keys.TAB);
	}

	public void selectMuiltDropDownCustom(String parentSelect, String allItemSelect, String[] expectedText) {

		driver.findElement(By.xpath(parentSelect)).click();

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(allItemSelect)));
		List<WebElement> listElement = driver.findElements(By.xpath(allItemSelect));
		for (WebElement element : listElement) {
			for (String text : expectedText) {
				if (element.getText().trim().equals(text)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
					element.click();
					break;
				}
			}
		}
	

	}

	public boolean checkSelected(String[] allItem) {
		List<WebElement> element = driver.findElements(By.xpath("//li[@class=\"selected\"]//input"));
		String dataSelected = driver.findElement(By.xpath("(//button[@class=\"ms-choice\"])[1]/span")).getText().trim();
		int count = element.size();
		if (count > 0 && count <= 3) {
			for (String item : allItem) {
				if (!dataSelected.contains(item)) {
					return false;
				}
			}
			return true;
		} else {
			return driver.findElement(By
					.xpath("(//button[@class=\"ms-choice\"])[1]/span[text()='" + count + " of 12 selected" + "']"))
					.isDisplayed();
		}

	}

	public String getDataEditTable() {
		return (String) jsExecutor.executeScript(
				"return $(\"div[id='default-place'] > ul[class='es-list']\").children(\".es-visible\")[0].innerText");
	}

	public String getDataSelectedDropDown() {
		// return (String) jsExecutor.executeScript("return
		// $(\"select[id='games_hidden'] > option\").text");// trang không support
		// jquery.
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option\").text ");
	}

	@AfterClass
	public void afterClass() {
	}

}
