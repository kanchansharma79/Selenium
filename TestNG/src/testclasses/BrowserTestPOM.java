package testclasses;

import org.testng.annotations.Test;

import com.pageclasses.HomePage;
import com.pageclasses.LoginPage;
import com.pageclasses.MyAccount;
import com.pageclasses.ProductPage;
import com.pageclasses.SearchPage;

import base.BaseTest;

public class BrowserTestPOM extends BaseTest {

	

	@Test
	public void CartTest() throws InterruptedException {
		String title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "RC Airplanes, Multirotors, Cars, Trucks and Helicopters | HorizonHobby");

		page.GetInstance(HomePage.class).ClickSignin();
		title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "Sign In");

		page.GetInstance(LoginPage.class).SignIn();
		title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "My Account");

		page.GetInstance(HomePage.class).SearchTerm("heli");
		title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "Search Results");

		page.GetInstance(SearchPage.class).ClickProductImage();
		title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertTrue(title.contains("Blade"));

		page.GetInstance(ProductPage.class).AddToCart();
		Thread.sleep(10000);
		page.GetInstance(ProductPage.class).GoToCart();

		title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "Shopping Cart");

		page.GetInstance(MyAccount.class).ClickSignOut();
	}

	@Test(enabled=false)
	public void LoginTestExtended() {
		page.GetInstance(HomePage.class).ClickSignin();
		page.GetInstance(LoginPage.class).SignIn();
		String title = driver.getTitle();
		System.out.println("Page opened is :-" + title);
		sa.assertEquals(title, "My Account");
	}
	
}
