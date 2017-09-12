package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BookingDetailsPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/my-transavia/booking/booking-overview/";
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@FindBy(id = "price-breakdown")
	private WebElement priceBreakdownPageSection;

	@FindBy(xpath = ".//*[@id='top']/div/div[5]/div/div/div/section/div[5]/div/div[2]/div/div/div")
	private WebElement totalSumFlight;

	@FindBy(xpath = ".//*[@id='top']/div/div[6]/section/div/div[2]/div/div/div[2]/div")
	private WebElement paymentAmountFlight;

	public BookingDetailsPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookingDetailsPage compareTotalSumAndPaymentAmountFlight() {
		wait.until(ExpectedConditions.visibilityOf(priceBreakdownPageSection));
		// Thread.sleep(2000);
		Assert.assertTrue(priceBreakdownPageSection.isDisplayed());
		System.out.println("price breakdown page section was displayed!!");
		System.out.println("total sum of flight is " + totalSumFlight.getText());
		System.out.println("payment amount of flight is " + paymentAmountFlight.getText());
		Assert.assertEquals(paymentAmountFlight.getText(), totalSumFlight.getText());
		System.out.println("total sum and payment amount of flight compared!!");
		return this;
	}

}