package extentreports;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {

	public static ExtentReports getInstance() {
		ExtentReports extent; 
		String path = "D:\\TestResults\\ExtentReports";
		extent = new ExtentReports(path, false);
		extent .addSystemInfo("Selenium Version", "2.52")
		.addSystemInfo("PlatForm", "Windows 7");
		return extent;
	}
	
}
