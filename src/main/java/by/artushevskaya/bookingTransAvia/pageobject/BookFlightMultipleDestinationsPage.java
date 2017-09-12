package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class BookFlightMultipleDestinationsPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/book-a-flight/flights/search/";
	// WebDriverWait wait = new WebDriverWait(driver, 5);
	private String total;

	@FindBy(id = "openJawRouteSelection_DepartureStationOutbound-input")
	private WebElement outboundDepartureStationField;

	@FindBy(id = "openJawRouteSelection_ArrivalStationOutbound-input")
	private WebElement outboundArrivalStationField;

	@FindBy(id = "dateSelection_OutboundDate-datepicker")
	private WebElement outboundDateField;

	@FindBy(id = "openJawRouteSelection_DepartureStationInbound-input")
	private WebElement inboundDepartureStationField;

	@FindBy(id = "openJawRouteSelection_ArrivalStationInbound-input")
	private WebElement inboundArrivalStationField;

	@FindBy(id = "dateSelection_InboundDate-datepicker")
	private WebElement inboundDateField;

	@FindBy(xpath = ".//*[@id='flights']/div/section/div[1]")
	private WebElement emptyClick;

	@FindBy(xpath = ".//*[@id='flights']/div/section/div[3]/div/button[2]")
	private WebElement searchButton;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[1]/time[1]")
	private WebElement outboundDepartureTime;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement outboundFlightSelectButton;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[1]/time[1]")
	private WebElement inboundDepartureTime;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement inboundFlightSelectButton;

	@FindBy(xpath = ".//*[@id='top']/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]")
	private WebElement totalAmount;

	public BookFlightMultipleDestinationsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookFlightMultipleDestinationsPage fillingOutboundFlight(String departureStationOutbound,
			String arrivalStationOutbound, String dateOutbound) throws InterruptedException {
		Thread.sleep(3000);
		outboundDepartureStationField.sendKeys(departureStationOutbound);
		outboundArrivalStationField.sendKeys(arrivalStationOutbound);
		outboundDateField.clear();
		outboundDateField.sendKeys(dateOutbound);
		return this;
	}

	public BookFlightMultipleDestinationsPage fillingInboundFlight(String departureStationInbound,
			String arrivalStationInbound, String dateInbound) throws InterruptedException {
		Thread.sleep(2000);
		inboundDepartureStationField.sendKeys(departureStationInbound);
		inboundArrivalStationField.sendKeys(arrivalStationInbound);
		inboundDateField.clear();
		inboundDateField.sendKeys(dateInbound);
		return this;
	}

	public BookFlightMultipleDestinationsPage searchFlightMultipleDestinations() throws InterruptedException {
		// wait.until(ExpectedConditions.visibilityOf(emptyClick));
		Thread.sleep(3000);
		emptyClick.click();
		searchButton.click();
		Thread.sleep(3000);
		return this;
	}

	public BookFlightMultipleDestinationsPage timingOutboundDepartureFlight(String time) throws InterruptedException {
		List<WebElement> weekFlight = new ArrayList<WebElement>();
		weekFlight = driver.findElements(By.xpath(
				".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li"));
		for (WebElement dayFlight : weekFlight) {
			dayFlight.click();
			// wait.until(ExpectedConditions.visibilityOf(outboundDepartureTime));
			Thread.sleep(1000);
			if (outboundDepartureTime.getText().contains(time)) {
				Thread.sleep(2000);
				outboundFlightSelectButton.click();
				break;
			}
		}
		return this;
	}

	public BookFlightMultipleDestinationsPage timingInboundDepartureFlight(String time) throws InterruptedException {
		Thread.sleep(3000);
		// JavascriptExecutor je = (JavascriptExecutor) driver;
		// je.executeScript("arguments[0].scrollIntoView(true);",inboundDepartureTime);
		Thread.sleep(3000);
		List<WebElement> weekFlight = new ArrayList<WebElement>();
		weekFlight = driver.findElements(By.xpath(
				".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/section/div[1]/div/div[2]/div/form/ol/li"));
		for (WebElement dayFlight : weekFlight) {
			dayFlight.click();
			// wait.until(ExpectedConditions.visibilityOf(inboundDepartureTime));
			Thread.sleep(1000);
			if (inboundDepartureTime.getText().contains(time)) {
				Thread.sleep(2000);
				inboundFlightSelectButton.click();
				break;
			}
		}

		return this;
	}

	public BookFlightMultipleDestinationsPage getTotalAmount() throws InterruptedException {
		Thread.sleep(3000);
		total = totalAmount.getText();
		System.out.println("total amount of flight - " + total);
		return this;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}