package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	public static String takeScreenShot(WebDriver driver, String fileName) throws IOException {
		fileName = fileName + ".png";
		String dir = "D:\\TestResults\\ExtentReports\\";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationDir = dir + fileName;
		FileUtils.copyFile(sourceFile, new File(destinationDir));
		return destinationDir;
	}
}
