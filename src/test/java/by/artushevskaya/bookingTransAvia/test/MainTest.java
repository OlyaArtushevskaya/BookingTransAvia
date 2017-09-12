package by.artushevskaya.bookingTransAvia.test;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import by.artushevskaya.bookingTransAvia.pageobject.BookFlightAdvancedSearchPage;
import by.artushevskaya.bookingTransAvia.pageobject.BookFlightMultipleDestinationsPage;
import by.artushevskaya.bookingTransAvia.pageobject.BookFlightPage;
import by.artushevskaya.bookingTransAvia.pageobject.BookFlightSelectFarePage;
import by.artushevskaya.bookingTransAvia.pageobject.BookingDetailsPage;
import by.artushevskaya.bookingTransAvia.pageobject.DestinationsPage;
import by.artushevskaya.bookingTransAvia.pageobject.HandLuggageInformationPage;
import by.artushevskaya.bookingTransAvia.pageobject.HandLuggageVideoPage;
import by.artushevskaya.bookingTransAvia.pageobject.HomePage;
import by.artushevskaya.bookingTransAvia.pageobject.LoginBookingPage;
import by.artushevskaya.bookingTransAvia.pageobject.MainPage;
import by.artushevskaya.bookingTransAvia.pageobject.ViewBookingPage;

public class MainTest extends BaseTest {

	private static final Logger LOG = Logger.getLogger(MainTest.class);

	// test case ID1 Заполнение поля "Where do you want to go?" для выбора
	// одиночного перелета на одну персону в одну сторону

	@Test(priority = 1)
	public void checkingRouteSearchPanel() {
		LOG.info("test case ID1");

		LOG.warn("start 'navigate'");
		MainPage mainPage = navigate(MainPage.URL);
		LOG.info("finish 'navigate'");

		LOG.warn("start 'selectLanguage'");
		mainPage.selectLanguage();
		LOG.info("finish 'selectLanguage'");

		LOG.warn("start 'checkingRoutePanel'");
		HomePage homePage = new HomePage(driver);
		homePage.checkingRoutePanel();
		LOG.info("routeSearchPanel was enabled!!");
		LOG.info("finish 'checkingRoutePanel'");

		LOG.warn("start 'checkingDepartureStationField'");
		homePage.checkingDepartureStationField();
		LOG.info("departure station was selected!!");
		LOG.info("finish 'checkingDepartureStationField'");

		LOG.warn("start 'checkingArrivalStationField'");
		homePage.checkingArrivalStationField();
		LOG.info("arrival station was selected!!");
		LOG.info("finish 'checkingArrivalStationField'");

		LOG.warn("start 'checkingIsReturnFlightCheckbox'");
		homePage.checkingIsReturnFlightCheckbox();
		LOG.info("isReturnFlightCheckbox was unchecked and isReturnFlightField is empty!!");
		LOG.info("finish 'checkingIsReturnFlightCheckbox'");

		LOG.warn("start 'checkingPassengersInputField'");
		homePage.checkingPassengersInputField();
		LOG.info("passengersTogglepanel was enabled and passenger was selected!!");
		LOG.info("finish 'checkingPassengersInputField'");

		LOG.warn("start 'bookFlightPage'");
		homePage.bookFlightPage();
		LOG.info("finish 'bookFlightPage'");

		LOG.warn("start 'checkingOutboundFlight'");
		BookFlightPage bookFlightPage = new BookFlightPage(driver);
		bookFlightPage.checkingOutboundFlight();
		LOG.info("outbound flight enabled!!");
		LOG.info("finish 'checkingOutboundFlight'");
	}

	// test case ID2 Проверка total суммы выбранных билетов. Двое взрослых и
	// один ребенок(2-11 age) из Барселоны в Париж в обе стороны.Выбрать билет с
	// Hold Luggage 20kg.

	@Test(priority = 2)
	public void checkingTotalCostTickets() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID2");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'fillingDepartureStationField'");
		homePage.fillingDepartureStationField("Barcelona");
		LOG.info("finish 'fillingDepartureStationField'");

		LOG.warn("start 'fillingArrivalStationField'");
		homePage.fillingArrivalStationField("Paris");
		LOG.info("finish 'fillingArrivalStationField'");

		LOG.warn("start 'inputPassengers'");
		homePage.inputPassengers();
		LOG.info("finish 'inputPassengers'");

		LOG.warn("start 'bookFlightPage'");
		homePage.bookFlightPage();
		LOG.info("finish 'bookFlightPage'");

		LOG.warn("start 'selectFlight'");
		BookFlightPage bookFlightPage = new BookFlightPage(driver);
		bookFlightPage.selectFlight();
		LOG.info("titles 'Outbound flight' and 'Inbound flight' were found!!");
		LOG.info("finish 'selectFlight'");

		LOG.warn("start 'flightPrice'");
		bookFlightPage.flightPrice();
		LOG.info("finish 'flightPrice'");

		LOG.warn("start 'bookFlightChooseFarePage'");
		bookFlightPage.bookFlightChooseFarePage();
		LOG.info("finish 'bookFlightChooseFarePage'");

		LOG.warn("start 'chooseFare'");
		BookFlightSelectFarePage bookFlightChooseFarePage = new BookFlightSelectFarePage(driver);
		bookFlightChooseFarePage.chooseFare();
		LOG.info("title 'Plus' was found!!");
		LOG.info("finish 'chooseFare'");

		LOG.warn("start 'plusFareFlightPrice'");
		bookFlightChooseFarePage.plusFareFlightPrice();
		LOG.info("finish 'plusFareFlightPrice'");

		LOG.warn("start 'checkingTotalCostFlight'");
		bookFlightChooseFarePage.checkingTotalCostFlight(bookFlightPage.getOutFlightPrice(),
				bookFlightPage.getInFlightPrice(), bookFlightChooseFarePage.getPlusFareFlightPrice());
		LOG.info("total amounts of flight equals!!");
		LOG.info("finish 'checkingTotalCostFlight'");
	}

	// test case ID3 Зайти в кабинет и проверить ожидаемое и фактическое время
	// приземления самолета (данные для входа, напр.: MF8C9R, kukharau,
	// 09.06.2016)

	@Test(priority = 3)
	public void checkingFlightAndArrival() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID3");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'loginBookingPage'");
		homePage.loginBookingPage();
		LOG.info("finish 'loginBookingPage'");

		LOG.warn("start 'loginBooking'");
		LoginBookingPage loginBookingPage = new LoginBookingPage(driver);
		loginBookingPage.loginBooking("MF8C9R", "kukharau", "09.06.2016");
		LOG.info("finish 'loginBooking'");

		LOG.warn("start 'checkingFlightAndArrivalTime'");
		ViewBookingPage viewBookingPage = new ViewBookingPage(driver);
		viewBookingPage.checkingFlightAndArrivalTime();
		LOG.info("flight was found!!");
		LOG.info("finish 'checkingFlightAndArrivalTime'");
	}

	// test case ID4 Проверить равенство стоимости билета и стоимости оплаты по
	// брони

	@Test(priority = 4)
	public void compareTotalSumAndPaymentAmount() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID4");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'loginBookingPage'");
		homePage.loginBookingPage();
		LOG.info("finish 'loginBookingPage'");

		LOG.warn("start 'loginBooking'");
		LoginBookingPage loginBookingPage = new LoginBookingPage(driver);
		loginBookingPage.loginBooking("MF8C9R", "kukharau", "09.06.2016");
		LOG.info("finish 'loginBooking'");

		LOG.warn("start 'bookingDetailsPage'");
		ViewBookingPage viewBookingPage = new ViewBookingPage(driver);
		viewBookingPage.bookingDetailsPage();
		LOG.info("finish 'bookingDetailsPage'");

		LOG.warn("start 'compareTotalSumAndPaymentAmountFlight'");
		BookingDetailsPage bookingDetailsPage = new BookingDetailsPage(driver);
		bookingDetailsPage.compareTotalSumAndPaymentAmountFlight();
		LOG.info("total sum and payment amount of flight compared!!");
		LOG.info("finish 'compareTotalSumAndPaymentAmountFlight'");
	}

	// test case ID8 Найти рейсы из Dubai в Agadir,Morocco

	@Test(priority = 5)
	public void flightsFromDubaiToAgadir() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID8");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'fillingDepartureStationField'");
		homePage.fillingDepartureStationField("Dubai");
		LOG.info("finish 'fillingDepartureStationField'");

		LOG.warn("start 'fillingArrivalStationField'");
		homePage.fillingArrivalStationField("Agadir");
		LOG.info("finish 'fillingArrivalStationField'");

		LOG.warn("start 'bookFlightPage'");
		homePage.bookFlightPage();
		LOG.info("finish 'bookFlightPage'");

		LOG.warn("start 'errorFlight'");
		BookFlightPage bookFlightPage = new BookFlightPage(driver);
		bookFlightPage.errorFlight(
				"Unfortunately we do not fly from Dubai, United Arab Emirates to Agadir, Morocco. However, we do fly from Dubai, United Arab Emirates to other destinations. Please change your destination and try again.");
		LOG.info("got error message!!");
		LOG.info("finish 'errorFlight'");
	}

	// test case ID9 Проверить стоимость сложного маршрута Bologna-Eindhoven
	// (1st date) и Amsterdam-Casablanca (2nd date)

	@Test(priority = 6)
	public void checkingCostMultipleDestinationsFlight() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID9");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'bookFlightMultipleDestinationsPage'");
		homePage.bookFlightMultipleDestinationsPage();
		LOG.info("finish 'bookFlightMultipleDestinationsPage'");

		LOG.warn("start 'fillingOutboundFlight'");
		BookFlightMultipleDestinationsPage bookFlightMultipleDestinationsPage = new BookFlightMultipleDestinationsPage(
				driver);
		bookFlightMultipleDestinationsPage.fillingOutboundFlight("Bologna, Italy", "Eindhoven, Netherlands",
				"13.09.2017");
		LOG.info("finish 'fillingOutboundFlight'");

		LOG.warn("start 'fillingInboundFlight'");
		bookFlightMultipleDestinationsPage.fillingInboundFlight("Amsterdam (Schiphol), Netherlands",
				"Casablanca, Morocco", "20.09.2017");
		LOG.info("finish 'fillingInboundFlight'");

		LOG.warn("start 'search flight multiple destinations'");
		bookFlightMultipleDestinationsPage.searchFlightMultipleDestinations();
		LOG.info("finish 'search flight multiple destinations'");

		LOG.warn("start 'timingOutboundDepartureFlight'");
		bookFlightMultipleDestinationsPage.timingOutboundDepartureFlight("15:40");
		LOG.info("finish 'timingOutboundDepartureFlight'");

		LOG.warn("start 'timingInboundDepartureFlight'");
		bookFlightMultipleDestinationsPage.timingInboundDepartureFlight("10:20");
		LOG.info("finish 'timingInboundDepartureFlight'");

		LOG.warn("start 'getTotalAmount'");
		bookFlightMultipleDestinationsPage.getTotalAmount();
		LOG.info("total amount of flight - " + bookFlightMultipleDestinationsPage.getTotal());
		LOG.info("finish 'getTotalAmount'");
	}

	// test case ID5 Проверить валидность ссылки на видео с инструкцией Transavia по
	// ручному багажу

	@Test(priority = 7)
	public void checkingValidityLink() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID5");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'handLuggageInformationPage'");
		homePage.handLuggageInformationPage();
		LOG.info("finish 'handLuggageInformationPage'");

		LOG.warn("start 'getVideoLink'");
		HandLuggageInformationPage handLuggageInformationPage = new HandLuggageInformationPage(driver);
		handLuggageInformationPage.getVideoLink();
		LOG.info("hand luggage and Transavia video's link: " + handLuggageInformationPage.getLink());
		LOG.info("finish 'getVideoLink'");

		LOG.warn("start 'handLuggageVideoPage'");
		handLuggageInformationPage.handLuggageVideoPage();
		LOG.info("finish 'handLuggageVideoPage'");

		LOG.warn("start 'checkingVideoNameAndAuthor'");
		HandLuggageVideoPage handLuggageVideoPage = new HandLuggageVideoPage(driver);
		handLuggageVideoPage.checkingVideoNameAndAuthor();
		LOG.info("finish 'checkingVideoNameAndAuthor'");

	}

	// test case ID6 Поиск билета в одну сторону с бюджетом < 200 euro (аэропорт
	// вылета: Innsbruck, Austria)

	@Test(priority = 8)
	public void searchTickets() throws InterruptedException {
		System.out.println("");
		LOG.info("test case ID6");

		LOG.warn("start 'navigate'");
		HomePage homePage = new HomePage(driver);
		homePage.navigate();
		LOG.info("finish 'navigate'");

		LOG.warn("start 'destinationsPage'");
		homePage.destinationsPage();
		LOG.info("finish 'destinationsPage'");

		LOG.warn("start 'bookFlightAdvancedSearchPage'");
		DestinationsPage destinationsPage = new DestinationsPage(driver);
		destinationsPage.bookFlightAdvancedSearchPage();
		LOG.info("finish 'bookFlightAdvancedSearchPage'");

		LOG.warn("start 'fillingDepartureStationField'");
		BookFlightAdvancedSearchPage bookFlightAdvancedSearchPage = new BookFlightAdvancedSearchPage(driver);
		bookFlightAdvancedSearchPage.fillingDepartureStationField("Amsterdam (Schiphol), Netherlands");
		LOG.info("finish 'fillingDepartureStationField'");

		LOG.warn("start 'searchBudgetFlight'");
		bookFlightAdvancedSearchPage.searchBudgetFlight("200");
		LOG.info("finish 'searchBudgetFlight'");

		closeBrowser();
	}

}