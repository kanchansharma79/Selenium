package com.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class SearchPage extends BasePage{
	@FindBy(id="")
	WebElement txtSearch;
	
	@FindBy(css="#CatalogEntryProdImg_1007501 > a > img")
	WebElement imgProduct;
	
	public SearchPage(WebDriver driver,WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void ClickProductImage() {
		imgProduct.click();
	}
	
	public String GetProductPage() {
		return driver.getTitle();
	}
	
}
