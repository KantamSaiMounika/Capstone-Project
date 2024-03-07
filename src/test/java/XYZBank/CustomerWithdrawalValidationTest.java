package XYZBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.AddCustomerPage;
import pageObjects.BankManagerLandingPage;
import pageObjects.CustomerLoginPage;
import pageObjects.CustomerWelcomePage;
import pageObjects.CustomersPage;
import pageObjects.DepositPage;
import pageObjects.HomePage;
import pageObjects.OpenAccountPage;
import pageObjects.TransactionsPage;
import pageObjects.WithdrawalPage;

public class CustomerWithdrawalValidationTest {

	public static HomePage homePage;
	public static CustomerLoginPage customerLoginPage;
	public static CustomerWelcomePage customerWelcomePage;
	public static TransactionsPage transactionsPage;
	public static DepositPage depositPage;
	public static WithdrawalPage withdrawalPage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static AddCustomerPage addCustomerPage;
	public static OpenAccountPage openAccountPage;
	public static CustomersPage customersPage;
	WebDriver driver;

	@BeforeMethod
	public void launcher() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\expandable\\Downloads\\browserdrivers\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		homePage = new HomePage(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		customerWelcomePage = new CustomerWelcomePage(driver);
		transactionsPage = new TransactionsPage(driver);
		depositPage = new DepositPage(driver);
		withdrawalPage = new WithdrawalPage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		addCustomerPage = new AddCustomerPage(driver);
		openAccountPage = new OpenAccountPage(driver);
		customersPage = new CustomersPage(driver);
		driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
		// driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	@Test(description = "Verify Amount Deposit Functionality Of Customer", priority = 5)
	public void customerWithdrawalValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded(driver);
		homePage.clickCustomerLogin();
		Thread.sleep(1000);

		String username = "Harry Potter";

		customerLoginPage.selectUserName(username);
		customerLoginPage.clickLogin();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='fontBig ng-binding']")).getText(), username,
				"Verify Redirection To Welcome Page ");

		String accountNumber = "1006";

		customerWelcomePage.selectAccount(accountNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[1]")).getText(),
				accountNumber, "Verify Correct Account Number Selection");
		customerWelcomePage.clickDeposit();
		
		Thread.sleep(1000);

		String depositAmount = "15000";

		
		depositPage.enterDepositAmount(depositAmount);
		depositPage.clickDepositButton();

		
		/** Successful Withdrawal Amount Scenario **/

		customerWelcomePage.clickWithdrawal();
		int initialBalance = Integer
				.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());
		
		String WithdrawalAmount = "5000";
		withdrawalPage.addWithdrawalAmount(WithdrawalAmount);
		withdrawalPage.clickWithdrawalButton();

		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'Transaction successful')]")).isDisplayed(),
				"Verify Transaction Successful Message");
		
		int updatedBalance = Integer
				.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());
		
		Assert.assertEquals(updatedBalance, initialBalance - Integer.parseInt(WithdrawalAmount),
				"Verify Updation Of Balance Amount");
		
		
		/** Unsuccessful Withdrawal When Withdrawal Amount Exceeds Balance Amount **/
		
		String exceedWithdrawalAmount = "12000";
		withdrawalPage.addWithdrawalAmount(exceedWithdrawalAmount);
		withdrawalPage.clickWithdrawalButton();
		
		String actualErrorMessage = driver.findElement(By.xpath("//span[@class='error ng-binding']")).getText();
		String expectedErrorMessage = "Transaction Failed. You can not withdraw amount more than the balance.";
		
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Verify Updation Of Balance Amount");
	}

}
