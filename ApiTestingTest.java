package weatherApi;

import static org.junit.Assert.*;

import org.junit.Test;

public class ApiTestingTest {

	@Test
	public void testWrongCityEntry() {
		WeatherRequest request = new WeatherRequest("NotExisting", EE, metric);
		//Must be error.
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testCoordinateStyle() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Coordinates must be xxx:yyy
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testCurrentTemperature() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Temperature check
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testHighestTemperature() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Highest temperature check
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testLowestTemperature() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Lowest temperature check
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testEveryThreeHourWeather() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Every 3 hour weather
		assertEquals(request.getWeather);
	}
	
	@Test
	public void testReturningFormat() {
		WeatherRequest request = new WeatherRequest("Tallinn", EE, metric);
		//Returned info is in right format
		assertEquals(request.getWeather);
	}

}
