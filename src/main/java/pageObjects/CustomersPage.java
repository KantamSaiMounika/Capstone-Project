package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage {
	
	@FindBy(xpath = "//input[@placeholder='Search Customer']")
	WebElement searchCustomer;
	
	@FindBy(xpath = "//tr[@class='ng-scope'][1]/td[5]/button")
	WebElement deleteButton;

	public void searchCustomer(String customerName) {
		searchCustomer.sendKeys(customerName);
	}

	public void deleteCustomer(String customerName) {
		searchCustomer(customerName);
		deleteButton.click();
	}

	public CustomersPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}