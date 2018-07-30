package datacollector;

import org.testng.annotations.DataProvider;

import utils.ExcelFactory;

public class TestDataCollector {

	@DataProvider(name = "credential")
	public Object[][] GetLoginData() {

		return new Object[][] { { "kanchan.sharma@horizonhobby.com", "Qanalyst1" }, { "ksharma1@horizonhobby.com", "Qanalyst1" }, { "ksharma@horizonhobby.com", "Qanalyst1" }};
	}
	
	@DataProvider(name = "excelCredential")
	public Object[][] GetLoginDataExcel() {

		Object[][] testData= ExcelFactory.getTestData("Cred");
		return testData;
	}

}
