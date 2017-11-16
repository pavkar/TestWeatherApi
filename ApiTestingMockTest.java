package weatherApi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import json.JSONReader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApiTestingMockTest {
	private static JSONReader weatherApiMock;

	@Before
	public static void setUpBeforeClass() throws Exception {
		weatherApiMock = mock(JSONReader.class);
	}

	@Test
	public void testWeatherApiMockVerify() throws Throwable {
		weatherApiMock.getWeatherJSONObject("Tallinn"); 
		verify(weatherApiMock).getWeatherJSONObject("Tallinn");
	}

}
