package fileManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.json.JSONObject;

import weatherApi.WeatherApi;

public class FIleWriterMockTest {
	private WeatherApi weatherApi;
	@Mock
	FileMaker filemakerMock;
	
	@Before
	public void setupBeforeEachTest() throws Exception {
		weatherApi = new WeatherApi();
	}
	
	@Test
	public void test() throws Exception {
		filemakerMock = mock(FileMaker.class);
		weatherApi.setCityName(FileOpener.openFile("input.txt"));
		JSONObject request = weatherApi.getAllData(0);
		verify(filemakerMock);
		FileMaker.writeFile(request);
	}

}
