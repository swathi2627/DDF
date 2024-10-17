package driverFactory;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import config.AppUtil;
import utilities.ExcelFileUtilities;

public class DriverScript extends AppUtil {

	String FileInput = "./FileInput/LoginData.xlsx";
	String FileOutput = "./FileOutput/Results.xlsx";
	String Tcsheet = "Login";

	@Test
	public void startTest() throws Throwable {
		ExcelFileUtilities excel = new ExcelFileUtilities(FileInput);
		int row = excel.rowCount(Tcsheet);
		for (int i = 1; i <= row; i++) {
			String userName = excel.getCellData(Tcsheet, i, 0);
			String password = excel.getCellData(Tcsheet, i, 1);

		//	FunctionLibrary function = new FunctionLibrary();
			Boolean isValidLogin = FunctionLibrary.login(userName, password);

			if (isValidLogin) {

				excel.setCellData(Tcsheet, i, 2, "Login Success", FileOutput);
				excel.setCellData(Tcsheet, i, 3, "pass", FileOutput);

				excel.wb.write(new FileOutputStream(FileOutput));

				FunctionLibrary.logOut();
				org.testng.Reporter.log("Login sucess  ..", true);

			} else {
				TakesScreenshot screenShot = (TakesScreenshot)driver;
				File file = screenShot.getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(file, new File( "./FileOutput/iteration/" + i + "_ " + "error.png"));
				
				excel.setCellData(Tcsheet, i, 2, "Login Success", FileOutput);
				excel.setCellData(Tcsheet, i, 3, "pass", FileOutput);

				excel.wb.write(new FileOutputStream(FileOutput));
				
                  org.testng.Reporter.log("Login failed", true);
                  
                 // driver.findElement(By.xpath(property.getProperty("btnOk"))).click();
			}

		}

	}
}
