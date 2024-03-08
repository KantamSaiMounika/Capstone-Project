package XYZBank;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.AddCustomerPage;
import pageObjects.BankManagerLandingPage;
import pageObjects.HomePage;

public class AddCustomerPageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static AddCustomerPage addCustomerPage;
	
	String firstname = prop.getProperty("firstName");
	String lastname = prop.getProperty("lastName");
	String postcode = prop.getProperty("postCode");
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launcher(@Optional("chrome") String browser) throws InterruptedException {
		
		initialization(browser);
		
		homePage = new HomePage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		addCustomerPage = new AddCustomerPage(driver);
		
	}
 
	
	@Test(description = "Verify Visibility And Redirection Behaviour Of Home Page Elements")
	public void homePageValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		
		Thread.sleep(1000);
		
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
		bankManagerLandingPage.clickAddCustomerButton();
		
		Thread.sleep(1000);
		
		addCustomerPage.addCustomerDetails(firstname, lastname, postcode);
		
		Thread.sleep(2000);
		
		String actualAlertMessage = driver.switchTo().alert().getText().;
		String expectedAlertMessage = "Customer added successfully with customer id :";

		Assert.assertTrue(actualAlertMessage.contains("gfhthrtj"));
		driver.switchTo().alert().accept();
			
	}
 }