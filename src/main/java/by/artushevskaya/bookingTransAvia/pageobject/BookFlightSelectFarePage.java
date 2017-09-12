package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BookFlightSelectFarePage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/book-a-flight/choose-a-fare/select/";
	WebDriverWait wait = new WebDriverWait(driver, 5);
	private int plusFareFlightPrice;
	private int numberPassenger;
	private String totalCostFlight;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/thead/tr/th[3]/span[1]")
	private WebElement plusTitle;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/thead/tr/th[3]/span[2]")
	private WebElement plusFarePrice;

	@FindBy(xpath = ".//*[@id='top']/div[1]/div[1]/div/div/div[2]/div/div[2]/table/tfoot/tr/td[3]/div/div/button[1]")
	private WebElement plusSelectButton;

	@FindBy(xpath = ".//*[@id='top']/div[2]/form/div[1]/div/footer/div/div/section/div/div/div[2]/div/div/div[2]")
	private WebElement totalAmount;

	@FindBy(xpath = ".//*[@id='top']/div[2]/form/div[1]/div/footer/div/div/a/section/div/div[3]/span[2]")
	private WebElement numberPassengers;

	public BookFlightSelectFarePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookFlightSelectFarePage chooseFare() throws InterruptedException {
		Assert.assertEquals(plusTitle.getText(), "Plus", "title 'Plus' was found!!");
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", plusSelectButton);
		wait.until(ExpectedConditions.visibilityOf(plusSelectButton));
		// Thread.sleep(4000);
		plusSelectButton.click();
		return this;
	}

	public void plusFareFlightPrice() {
		String plusFareFlightPriceStr;
		plusFareFlightPriceStr = plusFarePrice.getText().replaceAll("\\D+", " ");
		String[] eqw_plusFareFlightPriceStr = plusFareFlightPriceStr.split(" ");
		for (int i = 0; i < eqw_plusFareFlightPriceStr.length; i++) {
			if (eqw_plusFareFlightPriceStr[i].isEmpty() == false) {
				plusFareFlightPrice += Integer.parseInt(eqw_plusFareFlightPriceStr[i]);
			}

		}

	}

	public BookFlightSelectFarePage checkingTotalCostFlight(int outFlightPrice, int inFlightPrice,
			int plusFareFlightPrice) throws InterruptedException {
		System.out.println("price of outbound flight per one persone is € " + outFlightPrice);
		System.out.println("price of inbound flight per one persone is € " + inFlightPrice);
		System.out.println("price of Plus fare (hold luggage - 20 kg) per one persone is € " + plusFareFlightPrice);
		numberPassenger = Integer.parseInt(numberPassengers.getText());
		System.out.println("number of passengers is " + numberPassenger);
		totalCostFlight = String.valueOf((outFlightPrice + inFlightPrice + plusFareFlightPrice) * numberPassenger);
		System.out.println("got total cost of flight - € " + totalCostFlight);
		// wait.until(ExpectedConditions.visibilityOf(totalAmount));
		Thread.sleep(4000);
		System.out.println("total amount of flight from site - " + totalAmount.getText());
		Assert.assertTrue(totalAmount.getText().contains(totalCostFlight));
		System.out.println("total amounts of flight equals!!");
		return this;
	}

	public int getPlusFareFlightPrice() {
		return plusFareFlightPrice;
	}

	public void setPlusFareFlightPrice(int plusFareFlightPrice) {
		this.plusFareFlightPrice = plusFareFlightPrice;
	}

	public int getNumberPassenger() {
		return numberPassenger;
	}

	public void setNumberPassenger(int numberPassenger) {
		this.numberPassenger = numberPassenger;
	}

	public String getTotalCostFlight() {
		return totalCostFlight;
	}

	public void setTotalCostFlight(String totalCostFlight) {
		this.totalCostFlight = totalCostFlight;
	}

}