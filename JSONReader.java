package json;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {

	public static JSONObject getJSONObjectFromText(String text) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		String[] textSplitted = text.split("/n");
		for (String parts : textSplitted) {
			String[] partsSplitted = parts.split(" ");
			jsonObj.put(partsSplitted[0], partsSplitted[1]);
		}
		return jsonObj;
	}

	public static JSONObject getWeatherJSONObject(String cityName) throws Exception {
		try {
			JSONObject jsonObject = WeatherApiURLReader.getJSONObjectFromApi(cityName);
			return jsonObject;
		} catch (IOException ex) {
			return WeatherApiURLReader.getJSONObjectFromApi("Tallinn");
		}

	}
}
