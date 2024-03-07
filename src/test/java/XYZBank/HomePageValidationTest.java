package XYZBank;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePage;

public class HomePageValidationTest extends BaseTest {
	
	public static HomePage homePage;
	
	String customerLoginPageUrl = prop.getProperty("customerLoginPageUrl");
	String bankManagerLoginPageUrl = prop.getProperty("bankManagerLoginPageUrl");
	
	@Parameters({"browser"})
	@BeforeMethod
	public void launcher(String browser) throws InterruptedException {
		
		initialization(browser);
		
		homePage = new HomePage(driver);
	}
 

	@Test(description = "Verify Visibility And Redirection Behaviour Of Home Page Elements")
		public void homePageValidation() throws InterruptedException {
		
		homePage.waitTillPageLoaded();
		homePage.verifyHomePageContent();
		homePage.clickCustomerLogin();
		
		Thread.sleep(1000);
		
		
		String currentUrl1 = driver.getCurrentUrl();
		
		Assert.assertEquals(currentUrl1, customerLoginPageUrl, "Verify Redirection To Customer Login Page");
		
		homePage.clickHomeButton();
		homePage.clickBankManagerLogin();
		
		Thread.sleep(1000);
		
		String currentUrl2 = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl2, bankManagerLoginPageUrl, "Verify Redirection To Bank Manger Landing Page");	
	
	}
	
 }