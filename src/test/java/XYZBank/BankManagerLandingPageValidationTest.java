package XYZBank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.BankManagerLandingPage;
import pageObjects.HomePage;

public class BankManagerLandingPageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	public static BankManagerLandingPage bankManagerLandingPage;
	
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		
		initialization();
		
		homePage = new HomePage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
	}

	@Test(description = "Verify Login Functionality Of Bank Manager")
	public void bankManagerLandingPageValidation() throws InterruptedException {
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		Thread.sleep(1000);
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
	}

}
