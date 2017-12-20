package fileManager;

import org.junit.Before;
import org.junit.Test;

import weatherApi.WeatherApi;

public class FileOpenerTest {
	private WeatherApi weatherApi;

	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
	}

	@Test
	public void testWrongFileName() {
		assert (FileOpener.openFile("notSuchFile.txt").isEmpty());
	}

	@Test
	public void testFileReading() throws Exception {
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		String request = weatherApi.getWeatherApiCity();
		assert (request.equals("London"));
	}
	
}
