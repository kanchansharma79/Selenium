package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends Pages{

	public WebDriver driver;
	public WebDriverWait wait;
	
	public BasePage(WebDriver driver,WebDriverWait wait) {
		super(driver, wait);
	}

	// click Method
	public void Click(By elementLocation) {
		driver.findElement(elementLocation).click();
	}

	// click Method
	public void Click(WebElement element) {
		element.click();
	}

	// write Method
	public void writeText(By elementLocation, String text) {
		driver.findElement(elementLocation).sendKeys(text);
	}

	// Write Text
	public void writeText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	// read text
	public String readText(By elementLocation) {
		return driver.findElement(elementLocation).getText();
	}

	// read text
	public void readText(WebElement element) {
		element.getText();
	}

}
