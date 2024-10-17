package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	@FindBy(xpath="//button[@id='btnreset']")
	WebElement reset ;
	
	@FindBy(xpath = "//input[@id='username']")
	WebElement userName;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	@FindBy(xpath = "//button[@id='btnsubmit']") WebElement logOut;
	
	public void adminLogin(String uname, String password) {
		reset.click();
		userName.sendKeys(uname);
		this.password.sendKeys(password);
		logOut.click();
		
	}
	

}
