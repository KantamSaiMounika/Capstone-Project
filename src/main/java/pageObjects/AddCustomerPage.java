package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	
	@FindBy(xpath = "//input[@placeholder='First Name']")
	WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement lastName;

	@FindBy(xpath = "//input[@placeholder='Post Code']")
	WebElement postCode;

	@FindBy(xpath = "//button[@class='btn btn-default']")
	WebElement addCustomer;

	public void addCustomerDetails(String firstname, String lastname, String postcode) throws InterruptedException {
		Thread.sleep(1000);
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		postCode.sendKeys(postcode);
		addCustomer.click();
	}

	public AddCustomerPage(WebDriver driver) {
		 PageFactory.initElements(driver,this);
	}

}
