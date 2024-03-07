package XYZBank;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.AddCustomerPage;
import pageObjects.BankManagerLandingPage;
import pageObjects.CustomersPage;
import pageObjects.HomePage;
import pageObjects.OpenAccountPage;

public class CustomersPageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static AddCustomerPage addCustomerPage;
	public static OpenAccountPage openAccountPage;
	public static CustomersPage customersPage;
	
	String firstname = prop.getProperty("firstName");
	String lastname = prop.getProperty("lastName");
	String postcode = prop.getProperty("postCode") ;
	String customername = prop.getProperty("customerName") ;
	String currency = prop.getProperty("currency");
	String accountAddedAlertMessage = prop.getProperty("accountAddedAlertMessage");
	String customerAddedAlertMessage = prop.getProperty("customerAddedAlertMessage");
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		
		initialization();
		
		homePage = new HomePage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		addCustomerPage = new AddCustomerPage(driver);
		openAccountPage = new OpenAccountPage(driver);
		customersPage = new CustomersPage(driver);
	}

		
	@Test(description = "Verify Customers Dashboard Of Bank Manager")
	public void customersPageValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		
		Thread.sleep(2000);
		
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
		bankManagerLandingPage.clickAddCustomerButton();
		
		Thread.sleep(1000);
		
		addCustomerPage.addCustomerDetails(firstname, lastname, postcode);
		
		String actualAlertMessage = driver.switchTo().alert().getText();
		
		Assert.assertTrue(actualAlertMessage.contains(customerAddedAlertMessage));
		
		driver.switchTo().alert().accept();
		
		bankManagerLandingPage.clickOpenAccountButton();
		
		Thread.sleep(2000);
		
		openAccountPage.selectCustomer(customername);
		openAccountPage.selectCurrency(currency);
		openAccountPage.clickProcess();
		
		String actualAlertMessage1 = driver.switchTo().alert().getText();
		
		Assert.assertTrue(actualAlertMessage1.contains(accountAddedAlertMessage));
		driver.switchTo().alert().accept();
		
		bankManagerLandingPage.clickCustomersButton();
		
		Thread.sleep(2000);
		
		customersPage.deleteCustomer(firstname);
	}

}
