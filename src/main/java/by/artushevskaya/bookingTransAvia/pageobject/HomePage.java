package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/home/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(id = "desktop")
	private WebElement routeSearchPanel;

	@FindBy(id = "routeSelection_DepartureStation-input")
	private WebElement departureStationField;

	@FindBy(xpath = ".//li[text()='Pisa, Italy']")
	private WebElement departureStationName;

	@FindBy(xpath = ".//h6[text()='Origins to Rotterdam/The Hague, Netherlands']")
	private WebElement checkingArrivalStation;

	@FindBy(id = "routeSelection_ArrivalStation-input")
	private WebElement arrivalStationField;

	@FindBy(xpath = ".//li[text()='Rotterdam/The Hague, Netherlands']")
	private WebElement arrivalStationName;

	@FindBy(xpath = ".//h6[text()='Destinations from Pisa, Italy']")
	private WebElement checkingDepartureStation;

	@FindBy(id = "dateSelection_IsReturnFlight")
	private WebElement isReturnFlightCheckbox;

	@FindBy(xpath = ".//input[@id='dateSelection_IsReturnFlight-datepicker']")
	private WebElement isReturnFlightField;

	@FindBy(id = "booking-passengers-input")
	private WebElement passengersInputField;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]")
	private WebElement passengersTogglepanel;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[1]/div/div/div[2]/div/div/button[2]")
	private WebElement passengersTogglepanelAddAdultsButton;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[1]/div[2]/div/div/div[2]/div/div/button[2]")
	private WebElement passengersTogglepanelAddChildrenButton;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[2]/div[2]/div[2]/button")
	private WebElement passengersTogglepanelSaveButton;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[2]/div[3]/div/div[1]/div/div")
	private WebElement checkingPassengersInputField;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[3]/div/button")
	private WebElement searchButton;

	@FindBy(xpath = ".//nav[@class='navigation navigation--bbl navigation--horizontal']/div[1]/div[1]/ul/li[3]/a")
	private WebElement manageYourBooking;

	@FindBy(xpath = ".//nav[@class='navigation navigation--bbl navigation--horizontal']/div[1]/div[1]/ul/li[3]/div/div")
	private WebElement viewYourBooking;

	@FindBy(xpath = ".//*[@id='desktop']/section/div[3]/ul/li[2]/a")
	private WebElement addMultipleDestinationsButton;

	@FindBy(xpath = ".//nav[@class='navigation navigation--bbl navigation--horizontal']/div[1]/div[1]/ul/li[4]")
	private WebElement service;

	@FindBy(xpath = ".//*[@id='horizontal-sub-navigation-service']/div/div[2]/div/div[2]/div[2]/ul/li[1]/a")
	private WebElement handLuggage;

	@FindBy(xpath = "html/body/header/nav/div[1]/div[2]/ul/li[3]")
	private WebElement destinations;

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public HomePage checkingRoutePanel() {
		// wait.until(ExpectedConditions.visibilityOf(routeSearchPanel));
		Assert.assertTrue(routeSearchPanel.isEnabled());
		System.out.println("routeSearchPanel was enabled!!");
		return this;
	}

	public HomePage checkingDepartureStationField() {
		// wait.until(ExpectedConditions.visibilityOf(departureStationField));
		departureStationField.click();
		departureStationName.click();
		// wait.until(ExpectedConditions.visibilityOf(arrivalStationField));
		arrivalStationField.click();
		Assert.assertTrue(checkingDepartureStation.getText().contains(departureStationName.getText()));
		System.out.println("departure station was selected!!");
		return this;
	}

	public HomePage checkingArrivalStationField() {
		arrivalStationField.click();
		arrivalStationName.click();
		departureStationField.click();
		Assert.assertTrue(checkingArrivalStation.getText().contains(arrivalStationName.getText()));
		System.out.println("arrival station was selected!!");
		departureStationName.click();
		return this;
	}

	public HomePage checkingIsReturnFlightCheckbox() {
		// wait.until(ExpectedConditions.visibilityOf(isReturnFlightCheckbox));
		isReturnFlightCheckbox.click();
		Assert.assertFalse(isReturnFlightCheckbox.isSelected());
		System.out.println("isReturnFlightCheckbox was unchecked!!");
		isReturnFlightField.click();
		Assert.assertTrue(isReturnFlightField.getAttribute("placeholder").contains("Single flight"));
		System.out.println("isReturnFlightField is empty!!");
		return this;
	}

	public HomePage checkingPassengersInputField() {
		passengersInputField.click();
		// wait.until(ExpectedConditions.visibilityOf(passengersTogglepanel));
		Assert.assertTrue(passengersTogglepanel.isEnabled());
		System.out.println("passengersTogglepanel was enabled!!");
		passengersTogglepanelSaveButton.click();
		return this;
	}

	public HomePage fillingDepartureStationField(String departureStation) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(departureStationField));
		// Thread.sleep(2000);
		departureStationField.sendKeys(departureStation);
		return this;
	}

	public HomePage fillingArrivalStationField(String arrivalStation) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(arrivalStationField));
		// Thread.sleep(2000);
		arrivalStationField.sendKeys(arrivalStation);
		return this;
	}

	public HomePage inputPassengers() throws InterruptedException {
		passengersInputField.click();
		Thread.sleep(1000);
		// wait.until(ExpectedConditions.visibilityOf(passengersTogglepanel));
		passengersTogglepanel.click();
		passengersTogglepanelAddAdultsButton.click();
		passengersTogglepanelAddChildrenButton.click();
		passengersTogglepanelSaveButton.click();
		return this;
	}

	public BookFlightPage bookFlightPage() {
		searchButton.click();
		return new BookFlightPage(driver);
	}

	public LoginBookingPage loginBookingPage() {
		manageYourBooking.click();
		// viewYourBooking.click();
		return new LoginBookingPage(driver);
	}

	public BookFlightMultipleDestinationsPage bookFlightMultipleDestinationsPage() {
		// wait.until(ExpectedConditions.visibilityOf(addMultipleDestinationsButton));
		addMultipleDestinationsButton.click();
		return new BookFlightMultipleDestinationsPage(driver);
	}

	public HandLuggageInformationPage handLuggageInformationPage() throws InterruptedException {
		Thread.sleep(2000);
		service.click();
		handLuggage.click();
		return new HandLuggageInformationPage(driver);
	}

	public DestinationsPage destinationsPage() throws InterruptedException {
		Thread.sleep(3000);
		destinations.click();
		return new DestinationsPage(driver);
	}

}