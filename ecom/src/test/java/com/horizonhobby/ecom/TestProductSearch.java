package com.horizonhobby.ecom;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestProductSearch {
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
		test = report.startTest("Verify TestProductSearch");

		test.log(LogStatus.INFO, test.getTest().getName().toString());
		System.out.println("Project name is :" + test.getTest().getName());

		browser = prop.getProperty("Browser");
		url = prop.getProperty("HOST");
		browserDriverPath = prop.getProperty("DriverPath");
		ChromeOptions co = new ChromeOptions();
		
		System.setProperty("webdriver.chrome.driver", browserDriverPath);
		driver = new ChromeDriver(co);
		test.log(LogStatus.INFO, "Browser Launched"+browser);
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
	public void Search() throws InterruptedException {
		test.log(LogStatus.INFO, "Search term");
		driver.findElement(By.id("SimpleSearchForm_SearchTerm")).sendKeys("BLH1550");
		;
		driver.findElement(By.id("search_submit")).click();

		sa.assertEquals(driver.getTitle(),"Blade Night 230 S BNF Basic Flybarless Collective Pitch RC Helicopter with SAFE Technology | Horizon Hobby");
				System.out.println("Product page opened is :- "+driver.getTitle());
		if(driver.getTitle()!="Blade Night 230 S BNF Basic Flybarless Collective Pitch RC Helicopter with SAFE Technology | Horizon Hobby"){
			test.log(LogStatus.FAIL, "Wrong page opened");
		}else {
			test.log(LogStatus.PASS, "Correct product page opened");
		}
	}
}
