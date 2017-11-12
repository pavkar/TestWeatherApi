package weatherApi;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ApiTestingTest {
	
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
		assert(Double.parseDouble(request) - 273.15 < 100 && Double.parseDouble(request) - 273.15 > -100);
	}
	
	@Test
	public void testGetMinTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMinTemp("London", 1);
		assert(Double.parseDouble(request) - 273.15 < 100 && Double.parseDouble(request) - 273.15 > -100);
	}
	
	@Test
	public void testGetMaxTemp() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 0);
		assert(Double.parseDouble(request) - 273.15 < 100 && Double.parseDouble(request) - 273.15 > -100);
	}
	
	@Test
	public void testGetMaxTempThreeHoursLater() throws Exception {
		String request = WeatherApiStatic.getDayMaxTemp("London", 1);
		assert(Double.parseDouble(request) - 273.15 < 100 && Double.parseDouble(request) - 273.15 > -100);
	}
	
	@Test
	public void testGetTown() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("Tallinn");
		assert(request.equals("Tallinn"));
	}
	
	@Test
	public void testGetWindSpeed() throws Exception {
		String request = WeatherApiStatic.getDayWindSpeed("London", 0);
		assert(Double.parseDouble(request) < 300 && Double.parseDouble(request) >= 0);
	}
	
	@Test
	public void testGetWeather() throws Exception {
		String request = WeatherApiStatic.getDayWeather("London", 0);
		String[] weathers = {"Rain", "Clear", "Clouds"};
		assert(Arrays.asList(weathers).contains(request));
	}
	
	@Test(expected = IOException.class)
	public void testWrongCityEntry() throws Exception {
		String request = WeatherApiStatic.getWeatherCity("");
		assert(request.equals(""));
	}
	
	public void testFileReading() throws Exception {
		weatherApi.setCityName(FileOpener.openFile());
		String request = weatherApi.getWeatherCity();
		assert(request.equals("London"));
	}
	
	public void testFileWtriting() throws Exception {
		weatherApi.setCityName(FileOpener.openFile());
		String request = weatherApi.getAllData(0);
		FileMaker.writeFile(request);
	}

}
