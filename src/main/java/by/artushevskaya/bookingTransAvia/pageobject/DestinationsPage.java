package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DestinationsPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/destinations/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(xpath = ".//*[@id='top']/div/div[2]/div/div/div/a")
	private WebElement findPerfectDestinationButton;

	public DestinationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookFlightAdvancedSearchPage bookFlightAdvancedSearchPage() {
		findPerfectDestinationButton.click();
		return new BookFlightAdvancedSearchPage(driver);
	}

}