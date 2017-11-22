package fileManager;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import weatherApi.WeatherApi;

public class FileMakerTest {
	private WeatherApi weatherApi;

	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
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

}
