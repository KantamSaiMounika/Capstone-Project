package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerWelcomePage {
	
	@FindBy(id = "accountSelect")
	WebElement accounts;
	
	@FindBy(xpath = "//div[@ng-hide='noAccount']/button[1]")
	WebElement transactions;
	
	@FindBy(xpath = "//div[@ng-hide='noAccount']/button[2]")
	WebElement deposit;
	
	@FindBy(xpath = "//div[@ng-hide='noAccount']/button[3]")
	WebElement withdrawal;

	public void selectAccount(String accountNumber) {
		Select accountSelection = new Select(accounts);
		accountSelection.selectByVisibleText(accountNumber);
	}

	public void clickTransactions() {
		transactions.click();
	}

	public void clickDeposit() {
		deposit.click();
	}

	public void clickWithdrawal() {
		withdrawal.click();
	}

	public CustomerWelcomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
}
