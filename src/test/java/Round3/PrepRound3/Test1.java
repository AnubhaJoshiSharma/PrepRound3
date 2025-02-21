package Round3.PrepRound3;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import configs.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	private static final Logger log = LogManager.getLogger(Test1.class);
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	protected static ConfigReader configreader;
	public enum SupportedBrowsers {
		CHROME,
		FIREFOX;
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	@BeforeSuite
	public void setUp() {
		configreader = new ConfigReader();
	}
	@Parameters({"browserName","myName"})
	@Test
	public void myTestMethod(String browserName,String myName) {
		SupportedBrowsers browser = SupportedBrowsers.valueOf(browserName.toUpperCase());
		switch(browser) {
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
		
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		}
				getDriver().get(configreader.readUrl());
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		System.out.println("CURRENT URL IS "+getDriver().getCurrentUrl());
		System.out.println("My name is coming from Maven as "+ myName);
		getDriver().quit();
	}
}
