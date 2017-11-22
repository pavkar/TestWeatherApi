package fileManager;

import org.json.JSONObject;
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
	public void testSeveralCityNames() throws Exception {
		for (String city : FileOpener.openFile("inputMany.txt").split(" ")) {
			weatherApi.setCityName(city);
			JSONObject request = weatherApi.getAllData(0);
			FileMaker.writeFile(request);
			assert (!FileOpener.openFile(city + ".txt").isEmpty());
		}
	}

	@Test
	public void testFileReading() throws Exception {
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		String request = weatherApi.getWeatherApiCity();
		assert (request.equals("London"));
	}
}
