package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginBookingPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/my-transavia/account/logon/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(id = "retrieveBookingByLastname_RecordLocator")
	private WebElement bookingNumberField;

	@FindBy(id = "retrieveBookingByLastname_LastName")
	private WebElement lastNameField;

	@FindBy(id = "retrieveBookingByLastname_FlightDate-datepicker")
	private WebElement flightdateField;

	@FindBy(xpath = ".//*[@id='access-booking']/div/div/div[4]/button")
	private WebElement viewBookingButton;

	public LoginBookingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public ViewBookingPage loginBooking(String bookingNumber, String lastName, String flightdate) {
		bookingNumberField.sendKeys(bookingNumber);
		lastNameField.sendKeys(lastName);
		flightdateField.sendKeys(flightdate);
		wait.until(ExpectedConditions.visibilityOf(viewBookingButton));
		viewBookingButton.click();
		return new ViewBookingPage(driver);
	}

}