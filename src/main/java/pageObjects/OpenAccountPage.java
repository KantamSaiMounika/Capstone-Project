package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenAccountPage {
	@FindBy(id = "userSelect")
	WebElement customerNames;
	
	@FindBy(id = "currency")
	WebElement currencySelection;
	
	@FindBy(xpath = "//button[contains(text(),'Process')]")
	WebElement processButton;

	public void selectCustomer(String customername) {
		Select selectUser = new Select(customerNames);
		selectUser.selectByVisibleText(customername);
	}

	public void selectCurrency(String currency) {
		Select selectCurrency = new Select(currencySelection);
		selectCurrency.selectByVisibleText(currency);
	}

	public void clickProcess() {
		processButton.click();
	}

	public OpenAccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
