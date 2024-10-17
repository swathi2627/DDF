package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {

	public static WebDriver driver;
	public static Properties property;

	@BeforeTest
	public static void setUp() throws Throwable {

		property = new Properties();
		property.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		

		System.out.println(property.getProperty("Browser"));
		
		if (property.getProperty("Browser").trim().equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (property.getProperty("Browser").equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		} else {
			org.testng.Reporter.log("invalid Browser value ", true);
		}
	}

	@AfterTest
	public static void tearDown() {
		driver.quit();
	}

}
