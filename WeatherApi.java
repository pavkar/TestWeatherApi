package weatherApi;

import org.json.JSONObject;

public class WeatherApi {
	private JSONObject jsonObject;

	private String cityName = "Tallinn";

	public void setCityName(String cityName) throws Exception {
		this.cityName = cityName;
		jsonObject = JSONReader.getJSONObjectObject(this.cityName);
	}

	public WeatherApi() throws Exception {
		jsonObject = JSONReader.getJSONObjectObject(cityName);
	}

	public String getWeatherCity() throws Exception {
		return jsonObject.getJSONObject("city").get("name").toString();
	}

	public String getDayWeather(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONArray("weather").getJSONObject(0)
				.get("main").toString();
	}

	public String getDayWindSpeed(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("wind").get("speed").toString();
	}

	public String getDayMinTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_min").toString();
	}

	public String getDayMaxTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(dayTime).getJSONObject("main").get("temp_max").toString();
	}

	public String getAllData(int dayTime) throws Exception {
		String toReturn = "";
		toReturn += cityName + ":/n";
		toReturn += getDayMinTemp(dayTime) + " K /n";
		toReturn += getDayMaxTemp(dayTime) + " K /n";
		toReturn += getDayWeather(dayTime) + "/n";
		toReturn += getDayWindSpeed(dayTime) + " m/s";
		return toReturn;
	}
}
