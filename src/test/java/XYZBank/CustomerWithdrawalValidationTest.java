package XYZBank;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.CustomerLoginPage;
import pageObjects.CustomerWelcomePage;
import pageObjects.DepositPage;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import pageObjects.WithdrawalPage;

public class CustomerWithdrawalValidationTest extends BaseTest {

	public static HomePage homePage;
	public static CustomerLoginPage customerLoginPage;
	public static CustomerWelcomePage customerWelcomePage;
	public static TransactionsPage transactionsPage;
	public static DepositPage depositPage;
	public static WithdrawalPage withdrawalPage;
	
	String username = prop.getProperty("userName");
	String accountnumber = prop.getProperty("accountNumber");
	String depositamount = prop.getProperty("depositAmount");
	String exceedWithdrawalAmount = prop.getProperty("exceedWithdrawalAmount");
	String withdrawalAmount = prop.getProperty("WithdrawalAmount");
	String inSufficientBalanceErrorMessage = prop.getProperty("inSufficientBalanceErrorMessage");
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		
		initialization();
		
		homePage = new HomePage(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		customerWelcomePage = new CustomerWelcomePage(driver);
		transactionsPage = new TransactionsPage(driver);
		depositPage = new DepositPage(driver);
		withdrawalPage = new WithdrawalPage(driver);
	}

	@Test(description = "Verify Amount Deposit Functionality Of Customer")
	public void customerWithdrawalValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickCustomerLogin();
		
		Thread.sleep(1000);

		customerLoginPage.selectUserName(username);
		customerLoginPage.clickLogin();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='fontBig ng-binding']")).getText(), username,
				"Verify Redirection To Welcome Page ");

		customerWelcomePage.selectAccount(accountnumber);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[1]")).getText(),
				accountnumber, "Verify Correct Account Number Selection");
		
		customerWelcomePage.clickDeposit();
		
		Thread.sleep(1000);

		
		depositPage.enterDepositAmount(depositamount);
		depositPage.clickDepositButton();

		
		/** Successful Withdrawal Amount Scenario **/

		customerWelcomePage.clickWithdrawal();
		
		int initialBalance = Integer
				.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());
		
		withdrawalPage.addWithdrawalAmount(withdrawalAmount);
		withdrawalPage.clickWithdrawalButton();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'Transaction successful')]")).isDisplayed(),
				"Verify Transaction Successful Message");
		
		int updatedBalance = Integer
				.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());
		
		Assert.assertEquals(updatedBalance, initialBalance - Integer.parseInt(withdrawalAmount),
				"Verify Updation Of Balance Amount");
		
		
		/** Unsuccessful Withdrawal When Withdrawal Amount Exceeds Balance Amount **/
		
		
		withdrawalPage.addWithdrawalAmount(exceedWithdrawalAmount);
		withdrawalPage.clickWithdrawalButton();
		
		String actualErrorMessage = driver.findElement(By.xpath("//span[@class='error ng-binding']")).getText();
		
		Assert.assertEquals(actualErrorMessage, inSufficientBalanceErrorMessage, "Verify Updation Of Balance Amount");
	}

}
