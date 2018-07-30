package com.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class MyAccount extends BasePage {

	@FindBy(id = "SignOutLink")
	WebElement lnkSignOut;

	@FindBy(id = "SimpleSearchForm_SearchTerm")
	WebElement txtSearch;

	@FindBy(id = "search_submit")
	WebElement btnSearch;

	public MyAccount(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
	}

	public void ClickSignOut() {
		lnkSignOut.click();
	}

	public void SearchTerm(String term) {
		txtSearch.clear();
		txtSearch.sendKeys(term);

		btnSearch.click();
	}

}
