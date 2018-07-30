package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseTest {

	public SoftAssert sa;
	public WebDriver driver;
	public WebDriverWait wait;

	String url;
	String envFile;
	String browser;
	String browserDriverPath;
	Properties prop;
	public Pages page;

	@BeforeSuite(alwaysRun = true)
	public void SetUpBeforeSuite() {
		System.out.println("This method invoked before a suite is executing");
		envFile = System.getProperty("user.dir") + "/resources/env.properties";
		System.out.println("Dir path is: " + envFile);
	}

	@BeforeClass
	public void SetUp() throws IOException {
		System.out.println("This method invoked before a Test tag defined in testng xml");
		sa = new SoftAssert();
		FileInputStream fis = new FileInputStream(envFile);
		prop = new Properties();
		prop.load(fis);
		browser = prop.getProperty("Browser");
		url = prop.getProperty("HOST");
		browserDriverPath = prop.getProperty("DriverPath");
		System.out.println("Browser used is :- " + browser);
		System.out.println("Host url is :- " + url);

		System.setProperty("webdriver.chrome.driver", browserDriverPath);
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		page = new Pages(driver, wait);
	}

	@AfterTest
	public void TearDown() {
		System.out.println("This method invoked after a Test tag defined in testng xml");
		driver.quit();
		sa.assertAll();
	}
}
