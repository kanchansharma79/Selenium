package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pages {

	WebDriver driver;
WebDriverWait wait;
	public Pages(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}

	//JAVA Generics to Create and return a New Page
	public <TPage extends BasePage> TPage GetInstance(Class<TPage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class, WebDriverWait.class).newInstance(this.driver, this.wait);
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
