package weatherApi;

import java.util.Arrays;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fileManager.FileMaker;
import fileManager.FileOpener;

public class ApiTestingTest {

	private static final double KELVIN = 273.15;
	private WeatherApi weatherApi;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
	}

	@Test
	public void testGetMinTemp() throws Exception {
		String request = WeatherApiStatic.getDayMinTemp("London", 0);
		assert (Double.parseDouble(request) - KELVIN < 100 && Double.parseDouble(request) - KELVIN > -100);
	}

	@Test
	public void testGetMinTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMinTemp("London", 1);
		assert (Double.parseDouble(request) - KELVIN < 100 && Double.parseDouble(request) - KELVIN > -100);
	}

	@Test
	public void testGetMaxTemp() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 0);
		assert (Double.parseDouble(request) - KELVIN < 100 && Double.parseDouble(request) - KELVIN > -100);
	}

	@Test
	public void testGetMaxTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 1);
		assert (Double.parseDouble(request) - KELVIN < 100 && Double.parseDouble(request) - KELVIN > -100);
	}

	@Test
	public void testGetTown() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("Tallinn");
		assert (request.equals("Tallinn"));
	}

	@Test
	public void testGetWindSpeed() throws Exception {
		String request = WeatherApiStatic.getDayWindSpeed("London", 0);
		assert (Double.parseDouble(request) < 300 && Double.parseDouble(request) >= 0);
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
	public void testCityCoordiantes() throws Exception {
		weatherApi.setCityName("London");
		assert (weatherApi.getCityCoordinates().equals("51.5085:-0.1258"));
	}

	@Test
	public void testWrongDayTimeNegative() throws Exception {
		assert (Double.parseDouble(weatherApi.getDayWindSpeed(-1)) < 300
				&& Double.parseDouble(weatherApi.getDayWindSpeed(-1)) >= 0);
	}
	
	@Test
	public void testWrongDayTimeOverThreeDays() throws Exception {
		assert (Double.parseDouble(weatherApi.getDayWindSpeed(50)) < 300
				&& Double.parseDouble(weatherApi.getDayWindSpeed(50)) >= 0);
	}

	@Test
	public void testFileReading() throws Exception {
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		String request = weatherApi.getWeatherApiCity();
		assert (request.equals("London"));
	}

	@Test
	public void testFileWtritingInputText() throws Exception {
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		JSONObject request = weatherApi.getAllData(0);
		FileMaker.writeFile(request);
		assert (FileOpener.openFile(weatherApi.getCityName() + ".txt").length() > 0);
	}

	@Test
	public void testFileWtritingTallinn() throws Exception {
		weatherApi.setCityName("Tallinn");
		JSONObject request = weatherApi.getAllData(0);
		FileMaker.writeFile(request);
		assert (FileOpener.openFile(weatherApi.getCityName() + ".txt").length() > 0);
	}

	@Test
	public void testWrongFileName() {
		assert (FileOpener.openFile("notSuchFile.txt").isEmpty());
	}

	@Test
	public void testSeveralCityNames() throws Exception {
		for (String city : FileOpener.openFile("inputMany.txt").split(" ")) {
			weatherApi.setCityName(city);
			JSONObject request = weatherApi.getAllData(0);
			FileMaker.writeFile(request);
			assert (!FileOpener.openFile(city + ".txt").isEmpty());
		}
	}

}
