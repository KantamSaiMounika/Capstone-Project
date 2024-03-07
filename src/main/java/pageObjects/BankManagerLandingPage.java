package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BankManagerLandingPage {

	@FindBy(xpath = "//button[@ng-class='btnClass1']")
	WebElement addCustomerButton;
	
	@FindBy(xpath = "//button[@ng-class='btnClass2']")
	WebElement openAccountButton;
	
	@FindBy(xpath = "//button[@ng-class='btnClass3']")
	WebElement customersButton;

	public void clickAddCustomerButton() {
		addCustomerButton.click();
	}

	public void clickOpenAccountButton() {
		openAccountButton.click();
	}

	public void clickCustomersButton() {
		customersButton.click();
	}

	public void verifyBankManagerLandingPageContent(){
		
		Assert.assertTrue(addCustomerButton.isDisplayed(),"Verify Add Customer Button Visibility");
		Assert.assertTrue(openAccountButton.isDisplayed(),"Verify Open Account Button Visibility");
		Assert.assertTrue(customersButton.isDisplayed(),"Verify Customers Button Visibility");
	}

	public BankManagerLandingPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
