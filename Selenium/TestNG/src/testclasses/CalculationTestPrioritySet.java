package testclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.app.Calculation;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class CalculationTestPrioritySet {
	SoftAssert sa = new SoftAssert();
	int result = 0;

	ExtentReports report;
	ExtentTest test;
	String envFile;
	String logFilePath;
	Properties prop;

	@BeforeClass
	public void SetupReport() throws IOException {
		envFile = System.getProperty("user.dir") + "/resources/env.properties";
		FileInputStream fis = new FileInputStream(envFile);
		prop = new Properties();
		prop.load(fis);
		logFilePath = prop.getProperty("ReportFilePath").toString();
		System.out.println("Path for log file is :- " + logFilePath);

		report = new ExtentReports(prop.getProperty("ReportFilePath"), false);
		test = report.startTest("Calculation test started");
	}

	@AfterClass
	public void TearDownClass() {
		sa.assertAll();
		report.endTest(test);
		report.flush();

	}

	@Test(priority = 1)
	public void TestSum() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Sum(4, 2);
		sa.assertEquals(result, 6);
		System.out.println("Sum is " + result);
	}

	@Test(priority = 2)
	public void TestSubstract() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Substract(result, 1);
		sa.assertEquals(result, 5);
		System.out.println("Substraction is " + result);
	}

	@Test(priority = 3)
	public void TestMultiply() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Multiply(result, 3);
		sa.assertEquals(result, 15);
		System.out.println("Multiplication  is " + result);

	}

	@Test(priority = 4)
	public void TestDevision() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Devide(result, 5);
		sa.assertEquals(result, 3);
		System.out.println("Devision is " + result);
	}

}
