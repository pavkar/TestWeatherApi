package fileManager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.json.JSONObject;

import weatherApi.WeatherApi;

public class FIleWriterMockTest {
	private WeatherApi weatherApi;
	private FileMaker filemakerMock;
	
	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
		filemakerMock = mock(FileMaker.class);
	}
	
	@Test
	public void test() throws Exception {
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		JSONObject request = weatherApi.getAllData(0);
		filemakerMock.writeFile(request);
		verify(filemakerMock).writeFile(request);
	}

}
