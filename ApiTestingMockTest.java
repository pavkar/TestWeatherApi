package weatherApi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApiTestingMockTest {
	private static WeatherApi weatherApiMock;

	@Before
	public static void setUpBeforeClass() throws Exception {
		weatherApiMock = mock(WeatherApi.class);
	}

	@Test
	public void testWeatherApiMockVerify() {

	}

}
