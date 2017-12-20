package fileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
		List<String> records = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(weatherApi.getCityName() + ".txt"));
		String line;
		while ((line = reader.readLine()) != null)
		{
		  records.add(line);
		}
		reader.close();
		  
		assert (records.size() > 5);
	}

	@Test
	public void testFileWtritingTallinn() throws Exception {
		weatherApi.setCityName("Tallinn");
		JSONObject request = weatherApi.getAllData(0);
		FileMaker.writeFile(request);
		List<String> records = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(weatherApi.getCityName() + ".txt"));
		String line;
		while ((line = reader.readLine()) != null)
		{
		  records.add(line);
		}
		reader.close();
		  
		assert (records.size() > 5);
	}
	
	@Test
	public void testSeveralCityNames() throws Exception {
		for (String city : FileOpener.openFile("inputMany.txt").split(" ")) {
			weatherApi.setCityName(city);
			JSONObject request = weatherApi.getAllData(0);
			FileMaker.writeFile(request);
			List<String> records = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new FileReader(weatherApi.getCityName() + ".txt"));
			String line;
			while ((line = reader.readLine()) != null)
			{
			  records.add(line);
			}
			reader.close();
			assert (records.size() > 5);
		}
	}

}
