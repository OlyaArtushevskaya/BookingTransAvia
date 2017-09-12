package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookFlightAdvancedSearchPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/book-a-flight/advanced-search/search/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(id = "countryStationSelection_Origin-input")
	private WebElement departureStationField;

	@FindBy(xpath = ".//*[@id='alternativesearch']/div[3]/div[1]/div[2]/h3")
	private WebElement budgetPerPersonButton;

	@FindBy(id = "budgetSelection_EurosBudget")
	private WebElement myBudgetField;

	@FindBy(xpath = ".//*[@id='alternativesearch']/div[6]/div[2]/button")
	private WebElement searchButton;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[2]/div/div[2]/div/div/section/div[2]/div/div/div/div/p")
	private WebElement noFlights;

	public BookFlightAdvancedSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookFlightAdvancedSearchPage fillingDepartureStationField(String departureStation)
			throws InterruptedException {
		departureStationField.clear();
		departureStationField.sendKeys(departureStation);
		Thread.sleep(2000);
		return this;
	}

	public BookFlightAdvancedSearchPage searchBudgetFlight(String myBudget) throws InterruptedException {
		budgetPerPersonButton.click();
		myBudgetField.sendKeys(myBudget);
		searchButton.click();
		Thread.sleep(3000);
		List<WebElement> getFlights = new LinkedList<WebElement>();
		getFlights = driver.findElements(By.xpath(".//*[@id='top']/div/div/div[2]/div/div[2]/div/div/section/ol/li"));
		for (WebElement flights : getFlights) {
			System.out.println(flights);
		}
		return this;
	}

}