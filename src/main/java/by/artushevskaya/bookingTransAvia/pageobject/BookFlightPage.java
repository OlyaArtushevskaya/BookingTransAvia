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

public class BookFlightPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/book-a-flight/flights/search/";
	WebDriverWait wait = new WebDriverWait(driver, 5);
	private int outFlightPrice;
	private int inFlightPrice;

	@FindBy(xpath = ".//div[@class='button button--cta-selection button--cta-selection-arrow-bottom   ']")
	private WebElement outboundFlight;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[1]/div/div[1]/h2")
	private WebElement outboundFlightTitle;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement outboundFlightSelectButton;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[1]/div/div[1]/h2")
	private WebElement inboundFlightTitle;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button")
	private WebElement inboundFlightSelectButton;

	@FindBy(xpath = ".//*[@id='top']/form/div[1]/div/footer/div/div/section/div/button")
	private WebElement nextButton;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[3]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/div/button/div[3]/div[1]")
	private WebElement outboundFlightPrice;

	@FindBy(xpath = ".//*[@id='top']/div/div/div[4]/section/section/div/div[1]/div[2]/div/div[3]/div/form/div/button/div[3]/div[1]")
	private WebElement inboundFlightPrice;

	@FindBy(xpath = ".//*[@id='flights']/div/section/div[2]/div[1]/div/div/div[1]/div/div/div[2]/p")
	private WebElement errorMessage;

	public BookFlightPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public BookFlightPage checkingOutboundFlight() {
		Assert.assertTrue(outboundFlight.isEnabled());
		System.out.println("outbound flight enabled!!");
		return this;
	}

	public BookFlightPage errorFlight(String message) {
		Assert.assertEquals(errorMessage.getText(), message);
		System.out.println("got error message!!");
		return this;
	}

	public BookFlightPage selectFlight() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(outboundFlightTitle));
		Assert.assertEquals(outboundFlightTitle.getText(), "Outbound flight");
		System.out.println("title 'Outbound flight' was found!!");
		Thread.sleep(3000);
		outboundFlightSelectButton.click();
		Assert.assertEquals(inboundFlightTitle.getText(), "Inbound flight");
		System.out.println("title 'Inbound flight' was found!!");
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", inboundFlightSelectButton);
		Thread.sleep(3000);
		inboundFlightSelectButton.click();
		return this;
	}

	public void flightPrice() {
		String outFlightPriceStr;
		String inFlightPriceStr;
		outFlightPriceStr = outboundFlightPrice.getText().replaceAll("\\D+", " ");
		String[] eqw_outFlightPriceStr = outFlightPriceStr.split(" ");
		for (int i = 0; i < eqw_outFlightPriceStr.length; i++) {
			if (eqw_outFlightPriceStr[i].isEmpty() == false) {
				outFlightPrice += Integer.parseInt(eqw_outFlightPriceStr[i]);
			}
		}

		inFlightPriceStr = inboundFlightPrice.getText().replaceAll("\\D+", " ");
		String[] eqw_inFlightPriceStr = inFlightPriceStr.split(" ");
		for (int i = 0; i < eqw_inFlightPriceStr.length; i++) {
			if (eqw_inFlightPriceStr[i].isEmpty() == false) {
				inFlightPrice += Integer.parseInt(eqw_inFlightPriceStr[i]);
			}

		}

	}

	public BookFlightSelectFarePage bookFlightChooseFarePage() throws InterruptedException {
		Thread.sleep(2000);
		nextButton.click();
		return new BookFlightSelectFarePage(driver);
	}

	public int getOutFlightPrice() {
		return outFlightPrice;
	}

	public void setOutFlightPrice(int outFlightPrice) {
		this.outFlightPrice = outFlightPrice;
	}

	public int getInFlightPrice() {
		return inFlightPrice;
	}

	public void setInFlightPrice(int inFlightPrice) {
		this.inFlightPrice = inFlightPrice;
	}

}