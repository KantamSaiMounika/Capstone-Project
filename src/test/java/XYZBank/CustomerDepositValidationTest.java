package XYZBank;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.CustomerLoginPage;
import pageObjects.CustomerWelcomePage;
import pageObjects.DepositPage;
import pageObjects.HomePage;
import org.testng.Assert;

public class CustomerDepositValidationTest extends BaseTest {

	public static HomePage homePage;
	public static CustomerLoginPage customerLoginPage;
	public static CustomerWelcomePage customerWelcomePage;
	public static DepositPage depositPage;
	
	String username = prop.getProperty("userName");
	String accountnumber = prop.getProperty("accountNumber");
	String depositamount = prop.getProperty("depositAmount");
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		
		initialization();
		
		homePage = new HomePage(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		customerWelcomePage = new CustomerWelcomePage(driver);
		depositPage = new DepositPage(driver);
		
	}

	@Test(description = "Verify Amount Deposit Functionality Of Customer", priority = 4)
	public void customerDepositValidation() throws InterruptedException {
		
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

		int initialBalance = Integer.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());
		
		depositPage.enterDepositAmount(depositamount);
		depositPage.clickDepositButton();

		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Deposit Successful')]")).isDisplayed(),
				"Verify Successful Deposit Message");
		
		int updatedBalance = Integer
				.parseInt(driver.findElement(By.xpath("//div[@ng-hide='noAccount']/strong[2]")).getText());

		Assert.assertEquals(updatedBalance, initialBalance + Integer.parseInt(depositamount),
				"Verify Updation Of Balance Amount");
	}

}