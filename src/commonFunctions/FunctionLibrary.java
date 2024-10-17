package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil{
	
	public static boolean login(String userName ,String password) throws Throwable {
		
		driver.get(property.getProperty("Url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		
		driver.findElement(By.xpath(property.getProperty("Reset"))).click();;
		driver.findElement(By.xpath(property.getProperty("Username"))).sendKeys(userName);
		driver.findElement(By.xpath(property.getProperty("Password"))).sendKeys(password);
		driver.findElement(By.xpath(property.getProperty("btnLogin"))).click();
		
		Thread.sleep(3000);
		String Expected ="dashboard";
		String Actual = driver.getCurrentUrl();
		
		if(Actual.contains(Expected)) {
			Reporter.log("dashboard" + Expected + ".." +Actual,  true);
			return true;
		}
		else {
			
			String error_msg = driver.findElement(By.xpath(property.getProperty("errorMsg"))).getText();
			Thread.sleep(3000);
			driver.findElement(By.xpath(property.getProperty("btnOk"))).click();
			Thread.sleep(3000);
			Reporter.log("login failed"+ " .." + error_msg , true);
			return false;
		}
	}
	
	public static void logOut() {
		driver.findElement(By.xpath(property.getProperty("btnLogut"))).click();
	}

}
