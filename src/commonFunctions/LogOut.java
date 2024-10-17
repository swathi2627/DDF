package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogOut {
	
	@FindBy(xpath = "(//a[starts-with(text(),' Logout')])[2]")
	WebElement BtnLogOut;

	public void adminLogout() {
		BtnLogOut.click();
	}
}
