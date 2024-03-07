package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {

	@FindBy(id = "userSelect")
	private WebElement userNameSelection;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement loginButton;

	@FindBy(xpath = "//button[contains(text(),'Logout')]")
	private WebElement logoutButton;

	@FindBy(xpath = "//span[@class='fontBig ng-binding']")
	private WebElement welcomeMessage;

	public void selectUserName(String username) {
		Select selectUser = new Select(userNameSelection);
		selectUser.selectByVisibleText(username);
	}

	public void clickLogin() throws InterruptedException {
		loginButton.click();
		Thread.sleep(2000);
	}

	public void clickLogout() {
		logoutButton.click();
	}

	public CustomerLoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}