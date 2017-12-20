package weatherApi;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class WeatherApiTest {

	private static final double KELVIN = 273.15;
	private static final int MAX_TEMP = 100;
	private static final int MIN_TEMP = -100;
	private static final int MAX_WIND_SPEED = 300;
	private static final int MIN_WIND_SPEED = 0;
	private WeatherApi weatherApi;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
	}

	@Test
	public void testGetMinTemp() throws Exception {
		String request = weatherApi.getDayMinTemp(0);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMinTempThreeHoursLater() throws Exception {
		String request = weatherApi.getDayMinTemp(1);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMaxTemp() throws Exception {
		String request = weatherApi.getDayMaxTemp(0);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMaxTempThreeHoursLater() throws Exception {
		String request = weatherApi.getDayMaxTemp(1);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetTown() throws Exception {
		assert (weatherApi.getCityName().equals("Tallinn"));
	}

	@Test
	public void testGetWindSpeed() throws Exception {
		String request = weatherApi.getDayWindSpeed(0);
		assert (Double.parseDouble(request) < MAX_WIND_SPEED && Double.parseDouble(request) >= MIN_WIND_SPEED);
	}

	@Test
	public void testGetWeather() throws Exception {
		String[] weathers = { "Rain", "Clear", "Clouds" };
		assert (Arrays.asList(weathers).contains(weatherApi.getDayWeather(0)));
	}

	@Test
	public void testEmptyCityEntry() throws Exception {
		weatherApi.setCityName("");
		assertEquals(weatherApi.getCityName(), "Tallinn");
	}

	@Test
	public void testWrongCityEntry() throws Exception {
		weatherApi.setCityName("hnsfigfgbdfjgndlfnglsnlfghlisndfginsdfngsngfnsnrgiusngfn");
		assertEquals(weatherApi.getCityName(), "Tallinn");
	}

	@Test
	public void testCityCoordiantes() throws Exception {
		weatherApi.setCityName("London");
		assertEquals(weatherApi.getCityCoordinates(), "51.5073:-0.1277");
	}

	@Test
	public void testWrongDayTimeNegative() throws Exception {
		assert (Double.parseDouble(weatherApi.getDayWindSpeed(-1)) < MAX_WIND_SPEED
				&& Double.parseDouble(weatherApi.getDayWindSpeed(-1)) >= MIN_WIND_SPEED);
	}

	@Test
	public void testWrongDayTimeOverThreeDays() throws Exception {
		assert (Double.parseDouble(weatherApi.getDayWindSpeed(50)) < MAX_WIND_SPEED
				&& Double.parseDouble(weatherApi.getDayWindSpeed(50)) >= MIN_WIND_SPEED);
	}

}
