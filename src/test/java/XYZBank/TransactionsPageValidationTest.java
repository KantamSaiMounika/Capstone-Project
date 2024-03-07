package XYZBank;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.CustomerLoginPage;
import pageObjects.CustomerWelcomePage;
import pageObjects.DepositPage;
import pageObjects.HomePage;
import pageObjects.TransactionsPage;
import pageObjects.WithdrawalPage;

public class TransactionsPageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	public static CustomerLoginPage customerLoginPage;
	public static CustomerWelcomePage customerWelcomePage;
	public static TransactionsPage transactionsPage;
	public static DepositPage depositPage;
	public static WithdrawalPage withdrawalPage;

	String username = prop.getProperty("userName");
	String accountnumber = prop.getProperty("accountNumber");
	String depositamount = prop.getProperty("depositAmount");
	String withdrawalAmount = prop.getProperty("WithdrawalAmount");

	@Parameters({"browser"})
	@BeforeMethod
	public void launcher(String browser) throws InterruptedException {
		
		initialization(browser);
		
		homePage = new HomePage(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		customerWelcomePage = new CustomerWelcomePage(driver);
		transactionsPage = new TransactionsPage(driver);
		depositPage = new DepositPage(driver);
		withdrawalPage = new WithdrawalPage(driver);

	}
	
	@Test(description = "Verify Transactions Functionality Of Customer")
	public void transcationsPageValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickCustomerLogin();
		
		Thread.sleep(1000);
		
		customerLoginPage.selectUserName(username);
		customerLoginPage.clickLogin();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='fontBig ng-binding']")).getText(), username, 
				"Verify Redirection To Welcome Page ");


		customerWelcomePage.selectAccount(accountnumber);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[1]")).getText(), 
				 accountnumber,"Verify Correct Account Number Selection");
		
		customerWelcomePage.clickDeposit();
		
		Thread.sleep(1000);
		
		depositPage.enterDepositAmount(depositamount);
		depositPage.clickDepositButton();

		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Deposit Successful')]")).isDisplayed(), 
				"Verify Successful Deposit Message");
		
		customerWelcomePage.clickTransactions();
		
		transactionsPage.validateTransaction(depositamount,"Credit");
		transactionsPage.clickBackButton();
		
		customerWelcomePage.clickWithdrawal();
		
		withdrawalPage.addWithdrawalAmount(withdrawalAmount);
		withdrawalPage.clickWithdrawalButton();

		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Transaction successful')]")).isDisplayed(), 
				"Verify Transaction Successful Message");
		
		Thread.sleep(2000);
		
		customerWelcomePage.clickTransactions();
		transactionsPage.validateTransaction(withdrawalAmount,"Debit");
		customerLoginPage.clickLogout();
	}


}
