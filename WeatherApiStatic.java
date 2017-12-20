package weatherApi;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.JSONObject;

import json.OfflineModeJSON;
import json.WeatherApiURLReader;

public class WeatherApiStatic {
	
	public static JSONObject getWeatherJSONObject(String cityName) throws Exception {
		try {
			return WeatherApiURLReader.getJSONObjectFromApi(cityName);
		} catch (UnknownHostException ex) {
			return OfflineModeJSON.getOfflineApiInfo();
		} catch (IOException ex) {
			return WeatherApiURLReader.getJSONObjectFromApi("Tallinn");
		}
	}

	public static String getWeatherCity(String cityName) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONObject("city").get("name").toString();
	}

	public static String getCityCoordinates(String cityName) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONObject("city").getJSONObject("coord").get("lat").toString() + ":"
				+ jsonObject.getJSONObject("city").getJSONObject("coord").get("lon").toString();
	}

	public static String getDayWeather(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONArray("weather").getJSONObject(0)
				.get("main").toString();
	}

	public static String getDayWindSpeed(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("wind").get("speed").toString();
	}

	public static String getDayMinTemp(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_min").toString();
	}

	public static String getDayMaxTemp(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = getWeatherJSONObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_max").toString();
	}

}
