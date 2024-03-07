package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DepositPage {
	
	@FindBy(xpath = "//input[@ng-model='amount']")
	WebElement depositAmount;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	WebElement depositButton;

	public void enterDepositAmount(String amount) throws InterruptedException {
		Thread.sleep(1000);
		depositAmount.sendKeys(amount);
	}

	public void clickDepositButton() {
		depositButton.click();
	}

	public DepositPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

}
 
