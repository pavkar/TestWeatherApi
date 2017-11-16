package weatherApi;

import org.json.JSONObject;

import fileManager.JSONReader;
import scanner.InfoFromConsole;

public class WeatherApi {
	private JSONObject jsonObject;

	private String cityName;

	public WeatherApi() throws Exception {
		jsonObject = JSONReader.getJSONObjectObject(cityName);
	}

	public void setCityName(String cityName) throws Exception {
		this.cityName = cityName;
		jsonObject = JSONReader.getJSONObjectObject(this.cityName);
	}

	public void setCityNameFromConsole() throws Throwable {
		setCityName(InfoFromConsole.getInfoFromConsole());
	}

	public String getCityName() {
		return cityName;
	}

	public String getWeatherApiCity() throws Exception {
		return jsonObject.getJSONObject("city").get("name").toString();
	}

	public String getWeatherApiCoordinates() throws Exception {
		return jsonObject.getJSONObject("city").getJSONObject("coord")
				.get("lat").toString() + ":" + jsonObject.getJSONObject("city").getJSONObject("coord")
				.get("lon").toString();
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

	public JSONObject getAllData(int dayTime) throws Exception {
		String toReturn = "";
		toReturn += "City_Name " + cityName + "/n";
		toReturn += "Coordinates " + getWeatherApiCoordinates() + "/n";
		toReturn += "Min_Temp " + getDayMinTemp(dayTime) + " K /n";
		toReturn += "Max_Temp " + getDayMaxTemp(dayTime) + " K /n";
		toReturn += "Weather " + getDayWeather(dayTime) + "/n";
		toReturn += "Wind_Speed " + getDayWindSpeed(dayTime) + " m/s";
		return JSONReader.getJSONObjectFromText(toReturn);
	}
}
