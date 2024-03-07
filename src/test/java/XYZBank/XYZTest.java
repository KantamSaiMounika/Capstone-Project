package XYZBank;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.AddCustomerPage;
import pageObjects.BankManagerLandingPage;
import pageObjects.HomePage;

public class XYZTest extends BaseTest{
	
	public static HomePage homePage;
	public static BankManagerLandingPage bankManagerLandingPage;
	public static AddCustomerPage addCustomerPage;
	
	@BeforeMethod
	public void launcher() throws InterruptedException {
		initialization();
		homePage = new HomePage(driver);
		bankManagerLandingPage = new BankManagerLandingPage(driver);
		addCustomerPage = new AddCustomerPage(driver);
	}
 

	@Test(description = "Verify Visibility And Redirection Behaviour Of Home Page Elements",priority = 1)
	public void homePageValidation() throws InterruptedException {
		homePage.waitTillPageLoaded();
		homePage.clickBankManagerLogin();
		Thread.sleep(1000);
		bankManagerLandingPage.verifyBankManagerLandingPageContent();
		bankManagerLandingPage.clickAddCustomerButton();
		Thread.sleep(1000);
		addCustomerPage.addCustomerDetails("Kantam", "Sai Mounika", "533102");
		String actualAlertMessage = driver.switchTo().alert().getText();
		String expectedAlertMessage = "Customer added successfully with customer id :";

		Assert.assertTrue(actualAlertMessage.contains(expectedAlertMessage));
		driver.switchTo().alert().accept();
		
	}
 }