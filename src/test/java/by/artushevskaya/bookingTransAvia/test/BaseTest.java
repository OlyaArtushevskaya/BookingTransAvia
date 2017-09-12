package by.artushevskaya.bookingTransAvia.test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import by.artushevskaya.bookingTransAvia.pageobject.MainPage;

@Listeners({ HTMLReporter.class })

public class BaseTest {
	private static final Logger LOG = Logger.getLogger(BaseTest.class);
	protected WebDriver driver;

	@BeforeTest
	public WebDriver startBrowser() {
		LOG.warn("start 'startBrowser'");
		System.setProperty("webdriver.gecko.driver", "C://Program Files//geckodriver-v0.17.0-win64//geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		LOG.warn("finish 'startBrowser'");
		return driver;
	}

	@Test
	protected MainPage navigate(String url) {
		LOG.info("start 'navigate'");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		LOG.info("finish 'navigate'");
		return new MainPage(driver);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}

	}

}
