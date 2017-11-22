package scanner;

import org.junit.Before;
import org.junit.Test;

import weatherApi.WeatherApi;

public class InfoFromConsoleTest {

	private WeatherApi weatherApi;

	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
	}

	@Test
	public void testSetCityNameFromConsole() throws Throwable {
		weatherApi.setCityNameFromConsole();
		assert (weatherApi.getWeatherApiCity().equals("Helsinki"));
	}

	@Test
	public void testSetCityNameFromConsoleWrongEntry() throws Throwable {
		weatherApi.setCityNameFromConsole();
		assert (weatherApi.getWeatherApiCity().equals("Tallinn"));
	}

}
