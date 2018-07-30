package com.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class LoginPage extends BasePage{
	
	@FindBy(css="#WC_AccountDisplay_FormInput_logonId_In_Logon_1")
	WebElement txtEmail;
	
	@FindBy(css="#WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")
	WebElement txtPassword;
	
	@FindBy(xpath="//*[@id=\"WC_AccountDisplay_links_2\"]")
	WebElement btnSignIn;
	
	public LoginPage(WebDriver driver,WebDriverWait wait){
		super(driver, wait);
	}
	
	private void FillSignIn() {
		writeText(txtEmail, "ksharma@horizonhobby.com");
		writeText(txtPassword, "Qanalyst1");
	}
	
	private void ClcikSignIn() {
		Click(btnSignIn);
	}

	public void SignIn() {
		FillSignIn();
		ClcikSignIn();
	}
	
	
	
}
