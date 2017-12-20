package weatherApi;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class WeatherApiMockTest {
	@Mock
	WeatherApi weatherApiMock;
	
	@Test
	public void testWeatherApiMockVerify() throws Exception {
		weatherApiMock = mock(WeatherApi.class);
		weatherApiMock.getAllData(0);
		verify(weatherApiMock).getDayWeather(0);
	}
	
	@Test
	public void testWeatherApiMockReturnValue() {
		when(weatherApiMock.getCityName()).thenReturn("MockCity");
		assert(weatherApiMock.getCityName().equals("MockCity"));
	}
	
	@Test
	public void testWeatherApiMockDoThrow() throws Throwable {
		doThrow(new IllegalArgumentException()).when(weatherApiMock).getCityName();
		weatherApiMock.getCityName();
	}

}
