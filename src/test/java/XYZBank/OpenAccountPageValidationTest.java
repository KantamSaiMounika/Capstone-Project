package XYZBank;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.BankManagerLandingPage;
import pageObjects.HomePage;
import pageObjects.OpenAccountPage;

public class OpenAccountPageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static OpenAccountPage openAccountPage;
	
	String customername = prop.getProperty("customerName") ;
	String currency = prop.getProperty("currency");
	String accountAddedAlertMessage = prop.getProperty("accountAddedAlertMessage");
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		
		initialization();
		homePage = new HomePage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		openAccountPage = new OpenAccountPage(driver);
		
	}
	
	@Test(description = "Verify Open Account Functionality Of Bank Manager")
	public void openAccountPageValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		
		Thread.sleep(1000);
		
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
		bankManagerLandingPage.clickOpenAccountButton();
		
		Thread.sleep(1000);
		
		openAccountPage.selectCustomer(customername);
		openAccountPage.selectCurrency(currency);
		openAccountPage.clickProcess();
		
		String actualAlertMessage = driver.switchTo().alert().getText();
		
		Assert.assertTrue(actualAlertMessage.contains(accountAddedAlertMessage));
		driver.switchTo().alert().accept();
	}

}
