package com.horizonhobby.ecom;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestLoginWorkflow {
	WebDriver driver;
	SoftAssert sa;
	Properties prop;

	ExtentReports report;
	ExtentTest test;

	String envFile;
	String browser;
	String url;
	String browserDriverPath;

	@BeforeClass
	public void SetUp() throws Exception {

		envFile = System.getProperty("user.dir") + "/env.properties";
		sa = new SoftAssert();
		prop = new Properties();

		FileInputStream fis = new FileInputStream(envFile);
		prop.load(fis);

		String filePath = prop.getProperty("ReportFilePath");
		System.out.println("Report file name is :" + filePath);
		report = new ExtentReports(filePath, false);
		test = report.startTest("Verify HHWorkflow");

		test.log(LogStatus.FAIL, test.getTest().getName().toString());
		System.out.println("Project name is :" + test.getTest().getName());

		browser = prop.getProperty("Browser");
		url = prop.getProperty("HOST");
		browserDriverPath = prop.getProperty("DriverPath");

		System.setProperty("webdriver.chrome.driver", browserDriverPath);
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser Launched");
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
		sa.assertAll();
		report.endTest(test);
		report.flush();
	}

	@Test
	public void HHLogin() throws InterruptedException {
		test.log(LogStatus.INFO, "Click Sign in link");
		driver.findElement(By.id("SignInLink")).click();
		
		test.log(LogStatus.INFO, "Validate if sign in page is opened");
		sa.assertEquals(this.driver.getTitle().concat("Sign In"), true);

		test.log(LogStatus.INFO, "Enter User Name after clearing pre-filled data");
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1"))
				.sendKeys("ksharma@horizonhobby.com");

		test.log(LogStatus.INFO, "Enter Password after clearing pre-filled data");
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).sendKeys("Qanalyst1");

		test.log(LogStatus.INFO, "Click Sign in button");
		driver.findElement(By.id("WC_AccountDisplay_links_2")).click();
		Thread.sleep(5000);

		test.log(LogStatus.INFO, "Verify if user is logged in or not");
		System.out.println("Opened page is " + driver.getTitle());

		Assert.assertEquals(driver.getTitle().toString(), "My Account");
	}
	
}
