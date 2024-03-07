package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WithdrawalPage {
	
	@FindBy(xpath = "//input[@ng-model='amount']")
	WebElement withdrawalAmount;
	
	@FindBy(xpath = "//button[@class='btn btn-default']")
	WebElement withdrawalButton;

	public void addWithdrawalAmount(String amount) throws InterruptedException {
		Thread.sleep(1000);
		withdrawalAmount.sendKeys(amount);
	}

	public void clickWithdrawalButton() {
		withdrawalButton.click();
	}

	public WithdrawalPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}