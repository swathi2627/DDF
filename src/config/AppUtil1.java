package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.LogOut;
import commonFunctions.LoginPage;

public class AppUtil1 {
	public static WebDriver driver;
	public static Properties property;

	@BeforeTest
	public static void setTup() throws Throwable {

		property = new Properties();
		property.load(new FileInputStream("./PropertyFiles/Environment.properties"));

		if (property.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.get(property.getProperty("Url"));
			driver.manage().window().maximize();
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			login.adminLogin("admin", "master");

		} else if (property.getProperty("Browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			LoginPage login = PageFactory.initElements(driver, LoginPage.class);
			login.adminLogin("admin", "master");

		}else {
			Reporter.log("Driver not found ...", true);
		}
	}
	@AfterTest
	public static void tearDown() {
		LogOut logOut = PageFactory.initElements(driver, LogOut.class);
		logOut.adminLogout();
	}

}
