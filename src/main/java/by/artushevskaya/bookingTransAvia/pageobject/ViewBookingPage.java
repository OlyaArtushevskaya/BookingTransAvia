package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ViewBookingPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/my-transavia/booking/booking-overview/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div[1]/div/p")
	private WebElement flightNumber;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[1]")
	private WebElement departureStation;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[1]/h3/span[3]")
	private WebElement arrivalStation;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[1]/div/div/div[2]/div[1]/div[4]/div/p/em/time")
	private WebElement arrivalTime;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div/div[1]/div[2]/div[2]/div/div/div/div[3]/a")
	private WebElement bookingDetailsButton;

	public ViewBookingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public ViewBookingPage checkingFlightAndArrivalTime() {
		wait.until(ExpectedConditions.visibilityOf(flightNumber));
		// Thread.sleep(2000);
		Assert.assertTrue(flightNumber.isDisplayed());
		System.out.println("flight number is displayed: " + flightNumber.getText());
		System.out.println("departure station of booking flight is " + departureStation.getText());
		System.out.println("arrival station of booking flight is " + arrivalStation.getText());
		System.out.println("arrival time of booking flight is " + arrivalTime.getText());
		return this;
	}

	public BookingDetailsPage bookingDetailsPage() {
		bookingDetailsButton.click();
		return new BookingDetailsPage(driver);
	}

}