package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class CustomerPage {
	WebDriver driver;

	public CustomerPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "(//a[contains(@href,'a_customerslist.php')])[2]")
	WebElement customerTab;

	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement AddCustomerTab;

	@FindBy(name = "x_Customer_Number")
	WebElement CustomerNumber;

	@FindBy(name = "x_Customer_Name")
	WebElement CustomerName;

	@FindBy(name = "x_Address")
	WebElement CustomerAdress;

	@FindBy(name = "x_City")
	WebElement EnterCity;

	@FindBy(name = "x_Country")
	WebElement CustomerCountry;

	@FindBy(name = "x_Contact_Person")
	WebElement EnterContactPerson;

	@FindBy(name = "x_Phone_Number")
	WebElement EnterPhoneNumber;

	@FindBy(name = "x__Email")
	WebElement EnterEmail;

	@FindBy(name = "x_Mobile_Number")
	WebElement EnterMobileNumber;

	@FindBy(name = "x_Notes")
	WebElement EnterNotes;

	@FindBy(name = "btnAction")
	WebElement BtnSaveCustomer;

	@FindBy(xpath = "//button[contains(.,'OK!')]")
	WebElement ClickConfirmOK;

	@FindBy(xpath = "(//button[contains(text(),'OK')])[6]")
	WebElement ClickAlertOK;

	@FindBy(xpath = "//button[@class='btn btn-default ewSearchToggle']")
	WebElement ClickSearchPanel;

	@FindBy(name = "psearch")
	WebElement EnterSearch;

	@FindBy(name = "btnsubmit")
	WebElement ClickSearch;

	@FindBy(xpath = "/html/body/div[2]/div[3]/div/div/div[3]/form/div/div[2]/table/tbody/tr/td[5]/div/span/span")
	WebElement WebTable;

	boolean isValid = false;

	public boolean addCustomer(String cname, String adress, String city, String country, String cperson,
			String phoneNum, String email, String mobile, String note) throws InterruptedException {
		Actions ac = new Actions(driver);
		ac.moveToElement(customerTab).click().perform();
		ac.moveToElement(AddCustomerTab).click().perform();

		String cnumber = CustomerNumber.getAttribute("value");

		CustomerName.sendKeys(cname);
		CustomerAdress.sendKeys(adress);
		EnterCity.sendKeys(city);
		CustomerCountry.sendKeys(country);
		EnterContactPerson.sendKeys(cperson);
		EnterPhoneNumber.sendKeys(phoneNum);
		EnterEmail.sendKeys(email);
		EnterMobileNumber.sendKeys(mobile);
		EnterNotes.sendKeys(note);

		BtnSaveCustomer.click();
		Thread.sleep(2000);
		ClickConfirmOK.click();
		Thread.sleep(2000);
		ClickAlertOK.click();
		Thread.sleep(2000);

		if (!EnterSearch.isDisplayed())
			ClickSearchPanel.click();

		// ac.moveToElement(ClickSearchPanel).click().perform();

		EnterSearch.sendKeys(cnumber);
		ClickSearch.click();

		isValid = WebTable.getText().equalsIgnoreCase(cnumber);

		if (isValid) {
			isValid = true;
			Reporter.log(WebTable.getText() + "... " + cnumber + "customer added ...", isValid);
		} else {
			isValid = false;
			Reporter.log(WebTable.getText() + "... " + cnumber + "customer added ...", isValid);
		}

		return isValid;
	}

}
