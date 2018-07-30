package testclasses;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.app.Calculation;

public class CalculationTest {
	SoftAssert sa = new SoftAssert();
	int result = 0;
	int a,b;
	@BeforeTest(alwaysRun=true)
	public void Setup() {
		a=4;
		b=2;
	}
	
	@Test(groups={"sum","calc"}, enabled=false)
	public void TestSum() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Sum(a, b);
		sa.assertEquals(result, 7);
		System.out.println("Sum is " + result);
	}
	
	@Test(groups={"sum"}, enabled=true)
	public void TestSumBig() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc TestSumBig methods");
		result = calc.Sum(3, 3);
		sa.assertEquals(result, 8);
		System.out.println("Sum is " + result);
	}

	@Test(dependsOnMethods= {"TestSum"}, groups={"sub","calc"})
	public void TestSubstract() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Substract(result, 1);
		sa.assertEquals(result, 5);
		System.out.println("Substraction is " + result);
	}

	@Test(dependsOnMethods= {"TestSubstract"}, groups={"multiple","calc"})
	public void TestMultiply() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Multiply(result, 3);
		sa.assertEquals(result, 15);
		System.out.println("Multiplication  is " + result);

	}

	@Test(dependsOnMethods= {"TestMultiply"}, groups={"devision","calc"})
	public void TestDevision() {
		Calculation calc = new Calculation();
		System.out.println("Running test calc methods");
		result = calc.Devide(result, 5);
		sa.assertEquals(result, 3);
		System.out.println("Devision is " + result);
	}

	@AfterTest(alwaysRun=true)
	public void TearDown() {
		sa.assertAll();
	}

}
