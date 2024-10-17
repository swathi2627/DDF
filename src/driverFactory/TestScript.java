package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import commonFunctions.CustomerPage;
import config.AppUtil1;
import utilities.ExcelFileUtilities;

public class TestScript extends AppUtil1 {
	String FileInputPath = " ";
	String FileOutputPath = "./FileOutput/POMresults.xlsx";
	String sheetName = "CustomerData";

	@Test
	public void startTest() throws Throwable {

		ExcelFileUtilities excel = new ExcelFileUtilities(FileInputPath);
		int row = excel.rowCount(sheetName);

		for (int i = 1; i <= row; i++) {

			String cname = excel.getCellData(sheetName, i, 0);
			String adress =excel.getCellData(sheetName, i,1);
			String city =excel.getCellData(sheetName, i, 2);
			String country =excel.getCellData(sheetName, i, 3);
			String cperson =excel.getCellData(sheetName, i, 4);
			String phoneNum =excel.getCellData(sheetName, i, 5);
			String email =excel.getCellData(sheetName, i, 6);
			String mobile =excel.getCellData(sheetName, i, 7);
			String note =excel.getCellData(sheetName, i, 8);
			
			CustomerPage cpage = PageFactory.initElements(driver, CustomerPage.class);
			boolean isAdd=cpage.addCustomer(cname, adress, city, country, cperson, phoneNum, email, mobile, note);
			
			if(isAdd) {
				excel.setCellData(sheetName, i, 9, "pass", FileOutputPath);
				
			}
			else {
				excel.setCellData(sheetName, i, 9, "fail", FileOutputPath);
			}
		}
	}

}
