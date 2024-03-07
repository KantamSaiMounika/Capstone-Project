package base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class BaseTest {
	public static WebDriver driver;
	public static Properties prop;

	public BaseTest() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./src/main/resources/config.properties");
			prop.load(ip);
			prop.getProperty("url");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	public static void initialization(String browser) {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\expandable\\Downloads\\browserdrivers\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		} 
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\expandable\\Documents\\driver//geckodriver.exe");
			driver = new FirefoxDriver();
		} 
		else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\expandable\\Downloads\\browserdrivers\\chromedriver-win64\\chromedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Browser Does Not Supports");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
}