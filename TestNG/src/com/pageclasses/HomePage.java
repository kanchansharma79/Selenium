package com.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;


public class HomePage extends BasePage {

	
	@FindBy(id="SignInLink")
	WebElement lnkSignIn;
	
	@FindBy(id="SimpleSearchForm_SearchTerm")
	WebElement txtSearch;
	
	@FindBy(id="search_submit")
	WebElement btnSearch;

	/***************** Constructor  *****************/
	public HomePage(WebDriver driver,WebDriverWait wait){
		super(driver,wait);
	}
	
	public void ClickSignin() {
		Click(lnkSignIn);
	}
	
	public void SearchTerm(String term) {
		txtSearch.clear();
		txtSearch.sendKeys(term);
		
		btnSearch.click();
	}
	
}
