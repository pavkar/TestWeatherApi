package weatherApi;

import org.json.JSONObject;

import fileManager.JSONReader;

public class WeatherApiStatic {

	public static String getWeatherCity(String cityName) throws Exception {
		JSONObject jsonObject = JSONReader.getJSONObjectObject(cityName);
		return jsonObject.getJSONObject("city").get("name").toString();
	}

	public static String getDayWeather(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = JSONReader.getJSONObjectObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONArray("weather").getJSONObject(0)
				.get("main").toString();
	}

	public static String getDayWindSpeed(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = JSONReader.getJSONObjectObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("wind").get("speed").toString();
	}

	public static String getDayMinTemp(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = JSONReader.getJSONObjectObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_min").toString();
	}

	public static String getDayMaxTemp(String cityName, int dayTime) throws Exception {
		JSONObject jsonObject = JSONReader.getJSONObjectObject(cityName);
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_max").toString();
	}

}
