package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TransactionsPage {
	@FindBy(xpath = "//button[contains(text(),'Back')]")
	WebElement backButton;
	
	@FindBy(xpath = "//button[contains(text(),'Reset')]")
	WebElement resetButton;
	
	@FindBy(xpath = "//a[contains(text(),'Date-Time')]")
	WebElement dateTime;

	@FindBy(xpath = "//tr[@id='anchor0']/td[2]")
	WebElement amount;
	
	@FindBy(xpath = "//tr[@id='anchor0']/td[3]")
	WebElement transactionType;
	
	@FindBy(xpath = "//div[@ng-hide='noAccount']/button[1]")
	WebElement transactions;

	public void validateTransaction(String Amount, String TransactionType) throws InterruptedException {
		
		try {
			
			Thread.sleep(2000);
			dateTime.click();
			Assert.assertEquals(amount.getText(), Amount, "Verify Transaction Amount Of Record");
			Assert.assertEquals(transactionType.getText(), TransactionType, "Verify Transaction Type Of Record");
		}
		
		catch(Exception e) {
			clickBackButton();
			transactions.click();
			dateTime.click();
			Assert.assertEquals(amount.getText(), Amount, "Verify Transaction Amount Of Record");
			Assert.assertEquals(transactionType.getText(), TransactionType, "Verify Transaction Type Of Record");	
		}
		
	}

	public void clickBackButton() throws InterruptedException {
		Thread.sleep(1000);
		backButton.click();
	}

	public TransactionsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
