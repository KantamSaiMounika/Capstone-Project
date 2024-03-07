package XYZBank;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.AddCustomerPage;
import pageObjects.BankManagerLandingPage;
import pageObjects.CustomerLoginPage;
import pageObjects.HomePage;

public class NoAccountsValidationTest extends BaseTest {

	public static HomePage homePage;
	public static CustomerLoginPage customerLoginPage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static AddCustomerPage addCustomerPage;
	
	String firstname = prop.getProperty("firstName");
	String lastname = prop.getProperty("lastName");
	String postcode = prop.getProperty("postCode");
	String customerAddedAlertMessage = prop.getProperty("customerAddedAlertMessage");
	String noAccountsFoundMessage = prop.getProperty("noAccountsFoundMessage");

	@Parameters({"browser"})
	@BeforeMethod
	public void launcher(String browser) throws InterruptedException {
		
		initialization(browser);
		
		homePage = new HomePage(driver);
		customerLoginPage = new CustomerLoginPage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		addCustomerPage = new AddCustomerPage(driver);
	}
	
	@Test(description = "Verify Customer Journey With Having No Accounts")
	public void noAccountsValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		
		Thread.sleep(2000);
		
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
		bankManagerLandingPage.clickAddCustomerButton();
		
		Thread.sleep(1000);
		
		addCustomerPage.addCustomerDetails(firstname, lastname, postcode);
		
		Thread.sleep(2000);
		
		String actualAlertMessage = driver.switchTo().alert().getText();
	
		Assert.assertTrue(actualAlertMessage.contains(customerAddedAlertMessage));
		driver.switchTo().alert().accept();
		
		homePage.clickHomeButton();
		homePage.clickCustomerLogin();
		
		Thread.sleep(1000);
		
		customerLoginPage.selectUserName(firstname + ' ' + lastname);
		customerLoginPage.clickLogin();
		
		String actualMessage = driver.findElement(By.xpath("//span[@ng-show='noAccount']")).getText();
		
		Assert.assertEquals(actualMessage, noAccountsFoundMessage, "Verify Message For Customer With No Accounts Found");
	}
	
}
