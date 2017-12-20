package weatherApi;

import java.util.Arrays;

import org.junit.Test;

public class WeatherApiStaticTest {
	
	private static final double KELVIN = 273.15;
	private static final int MAX_TEMP = 100;
	private static final int MIN_TEMP = -100;
	private static final int MAX_WIND_SPEED = 300;
	private static final int MIN_WIND_SPEED = 0;

	@Test
	public void testGetMinTemp() throws Exception {
		String request = WeatherApiStatic.getDayMinTemp("London", 0);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMinTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMinTemp("London", 1);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMaxTemp() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 0);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetMaxTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 1);
		assert (Double.parseDouble(request) - KELVIN < MAX_TEMP && Double.parseDouble(request) - KELVIN > MIN_TEMP);
	}

	@Test
	public void testGetTown() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("Tallinn");
		assert (request.equals("Tallinn"));
	}

	@Test
	public void testGetWindSpeed() throws Exception {
		String request = WeatherApiStatic.getDayWindSpeed("London", 0);
		assert (Double.parseDouble(request) < MAX_WIND_SPEED && Double.parseDouble(request) >= MIN_WIND_SPEED);
	}

	@Test
	public void testGetWeather() throws Exception {
		String request = WeatherApiStatic.getDayWeather("London", 0);
		String[] weathers = { "Rain", "Clear", "Clouds" };
		assert (Arrays.asList(weathers).contains(request));
	}

	@Test
	public void testEmptyCityEntry() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("");
		assert (request.equals("Tallinn"));
	}

	@Test
	public void testWrongCityEntry() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("hnsfigfgbdfjgndlfnglsnlfghlisndfginsdfngsngfnsnrgiusngfn");
		assert (request.equals("Tallinn"));
	}
	
	@Test
	public void testGetCoordinates() throws Exception {
		String request = WeatherApiStatic.getCityCoordinates("London");
		assert (request.equals("51.5085:-0.1258"));
	}

}
