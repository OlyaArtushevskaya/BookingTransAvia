package by.artushevskaya.bookingTransAvia.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HandLuggageVideoPage extends BasePage {

	public static final String URL = "//www.youtube.com/embed/fQMuhniqWAg";

	@FindBy(xpath = ".//div[@id='player']/div/div[3]/div[1]/div[2]/a")
	private WebElement videoName;

	@FindBy(xpath = ".//*[@id='owner-name']/a")
	private WebElement videoAuthor;

	public HandLuggageVideoPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void navigate() {
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public HandLuggageVideoPage checkingVideoNameAndAuthor() {
		System.out.println("video name is \"" + videoName.getText() + "\"");
		// System.out.println("video author is "+videoAuthor.getText());
		return this;
	}

}