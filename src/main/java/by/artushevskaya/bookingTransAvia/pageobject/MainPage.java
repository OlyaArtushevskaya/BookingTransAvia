package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

	public static final String URL = "https://www.transavia.com/";

	@FindBy(xpath = ".//div[@class='component_language-switch']/div/div/div[2]/ul/li[5]/a")
	private WebElement languageSwitch;

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public HomePage selectLanguage() {
		languageSwitch.click();
		return new HomePage(driver);
	}

}