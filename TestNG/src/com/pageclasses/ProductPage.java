package com.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class ProductPage extends BasePage {
	
	
	
	@FindBy(id="add2CartBtn")
	WebElement btnAddToCart;
	
	@FindBy(id="GotoCartButton2")
	WebElement btnGoToCart;
	
	public ProductPage(WebDriver driver,WebDriverWait wait) {
		super(driver, wait);
	}
	
	public void AddToCart() {
		btnAddToCart.click();
	}
	
	public void GoToCart() {
		btnGoToCart.click();
	}
}
