package testclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import datacollector.TestDataCollector;
import utils.Constants;
import utils.ExcelFactory;
import utils.Screenshots;

public class LoginFeatureTest {

	WebDriver driver;
	String envFile;
	Properties prop;
	ExtentReports report;
	ExtentTest test;
	String browser;
	String url;
	String browserDriverPath;
	SoftAssert sa = new SoftAssert();

	@BeforeClass
	public void Setup() throws Exception {
		envFile = System.getProperty("user.dir") + "/resources/env.properties";
		sa = new SoftAssert();
		FileInputStream fis = new FileInputStream(envFile);
		prop = new Properties();
		prop.load(fis);
		System.out.println("Report file name is :" + prop.getProperty("ReportFilePath"));

		report = new ExtentReports(prop.getProperty("ReportFilePath"), false);
		test = report.startTest("Verify login datadriven");
		System.out.println("Project name is :" + report.getProjectName());
		System.out.println("Project name is :" + test.getTest().getName());

		browser = prop.getProperty("Browser");
		url = prop.getProperty("HOST");
		browserDriverPath = prop.getProperty("DriverPath");
		System.setProperty("webdriver.chrome.driver", browserDriverPath);
		this.driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser Launched");

		this.driver.get(url);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String fp= Constants.TestDataFilePath + Constants.TestDataFileName;
		System.out.println("File path is : "+fp);
		ExcelFactory.setExcelFile(Constants.TestDataFilePath + Constants.TestDataFileName, "Sheet1");
		test.log(LogStatus.INFO, "Navigate to url: -" + url);
	}

	@AfterClass
	public void TearDown() {
		driver.quit();
	}

	@AfterMethod
	public void TearDownMethod(ITestResult testResult) throws IOException {

		if (testResult.getStatus() == ITestResult.FAILURE) {
			String errorScreenPath = Screenshots.takeScreenShot(driver, testResult.getName());
			errorScreenPath = test.addScreenCapture(errorScreenPath);
			test.log(LogStatus.FAIL, "Failed :- '" + testResult.getName() + "'", errorScreenPath);
		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Success :- '" + testResult.getName() + "'");
		}
		/* sa.assertAll(); */
		report.endTest(test);
		report.flush();
	}

	@Test(dataProvider = "excelCredential", dataProviderClass = TestDataCollector.class)
	public void LoginDataProvider(String userName, String Pass) throws InterruptedException {

		test.log(LogStatus.INFO, "Click Sign in link");
		driver.findElement(By.id("SignInLink")).click();
		test.log(LogStatus.INFO, "Validate if sign in page is opened");
		sa.assertEquals(this.driver.getTitle().concat("Sign In"), true);

		test.log(LogStatus.INFO, "Enter User Name after clearing pre-filled data");
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).sendKeys(userName);

		test.log(LogStatus.INFO, "Enter Password after clearing pre-filled data");
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).sendKeys(Pass);

		test.log(LogStatus.INFO, "Click Sign in button");
		driver.findElement(By.id("WC_AccountDisplay_links_2")).click();
		Thread.sleep(5000);

		test.log(LogStatus.INFO, "Verify if user is logged in or not");
		System.out.println("Opened page is " + driver.getTitle());

		Assert.assertEquals(driver.getTitle().toString(), "My Account");
		/*
		 * if(driver.getTitle().contains("My Account")) {
		 * driver.findElement(By.id("SignOutLink")).click(); }
		 */
	}

	// @Test
	public void LoginRegular() {
		test.log(LogStatus.INFO, "Click on Sign in");
		driver.findElement(By.id("SignInLink")).click();

		Validate("Sign In", this.driver.getTitle());
		test.log(LogStatus.INFO, "Click on Sign in");

		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonId_In_Logon_1"))
				.sendKeys("ksharma@horizonhobby.com");

		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).clear();
		driver.findElement(By.id("WC_AccountDisplay_FormInput_logonPassword_In_Logon_1")).sendKeys("Qanalyst1");

		driver.findElement(By.id("WC_AccountDisplay_links_2")).click();

		Validate("My Account1", driver.getTitle());
	}

	public void Validate(Object expected, Object actual) {
		try {
			Assert.assertEquals(actual, expected);

			test.log(LogStatus.PASS, "Validation pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Validation failed");
		}
	}

	public void Validate(String expected, String actual) {
		try {
			if (actual != expected) {
				String errorScreenPath = Screenshots.takeScreenShot(driver, test.getTest().getName());
				String errorScreenPathFinal = test.addScreenCapture(errorScreenPath);
				test.log(LogStatus.FAIL, "Validation failed", errorScreenPathFinal);
				Assert.assertEquals(actual, expected);
			} else {
				test.log(LogStatus.PASS, "Validation pass");
			}
		} catch (Exception e) {

		}
	}

	public void Validate(int expected, int actual) {
		try {
			Assert.assertEquals(actual, expected);
			test.log(LogStatus.PASS, "Validation pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Validation failed");
		}
	}

	public void Validate(boolean expected, boolean actual) {
		try {
			Assert.assertEquals(actual, expected);
			test.log(LogStatus.PASS, "Validation pass");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "Validation failed");
		}
	}

}
