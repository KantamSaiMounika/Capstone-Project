package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseTest;

public class HomePage extends BaseTest {
	

	@FindBy(xpath = "//strong[@class='mainHeading']")
	WebElement pageTitle;
	
	@FindBy(xpath = "//button[@class='btn home']")
	WebElement homeButton;
	
	@FindBy(xpath = "//button[contains(text(),'Customer Login')]")
	WebElement customerLogin;
	
	@FindBy(xpath = "//button[contains(text(),'Bank Manager Login')]")
	WebElement bankManagerLogin;

	public void waitTillPageLoaded(){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='mainHeading']")));
	}

	public void clickHomeButton() {
		homeButton.click();
	}

	public void clickCustomerLogin(){
		Assert.assertTrue(customerLogin.isDisplayed(),"Verify Customer Login Button Visibility");
		customerLogin.click();
	}

	public void clickBankManagerLogin() {
		bankManagerLogin.click();
	}

	public void verifyHomePageContent(){
		Assert.assertEquals(pageTitle.getText(),"XYZ Bank", "Verify Page Title Visibility");
		Assert.assertTrue(homeButton.isDisplayed(),"Verify Home Button Visibility");
		Assert.assertTrue(customerLogin.isDisplayed(),"Verify Customer Login Button Visibility");
		Assert.assertTrue(bankManagerLogin.isDisplayed(),"Verify Bank Manager Login Button Visibility");
	}

	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);;
	}
}
