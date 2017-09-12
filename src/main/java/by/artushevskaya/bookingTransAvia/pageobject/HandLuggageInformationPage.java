package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HandLuggageInformationPage extends BasePage {

	public static final String URL = "https://www.transavia.com/en-EU/service/hand-luggage/";
	private String link;

	@FindBy(xpath = ".//*[@id='top']/div/div[9]/section/div/div/div")
	private WebElement handLuggageTransaviaVideoSection;

	@FindBy(xpath = ".//*[@id='top']/div/div[9]/section/div/div/div/div[2]/div/p/iframe")
	private WebElement handLuggageTransaviaVideo;

	public HandLuggageInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public HandLuggageInformationPage getVideoLink() throws InterruptedException {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].scrollIntoView(true);", handLuggageTransaviaVideoSection);
		Thread.sleep(2000);
		// driver.switchTo().frame(handLuggageTransaviaVideo);
		link = handLuggageTransaviaVideo.getAttribute("src");
		System.out.println("hand luggage and Transavia video's link: " + link);
		// driver.switchTo().defaultContent();
		return this;
	}

	public HandLuggageVideoPage handLuggageVideoPage() {
		driver.get(link);
		return new HandLuggageVideoPage(driver);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}