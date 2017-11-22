package weatherApi;

import org.json.JSONObject;

import json.JSONReader;
import scanner.InfoFromConsole;

public class WeatherApi {
	private static final int MAX_DAYTIME = 29;
	private JSONObject jsonObject;

	private String cityName;

	public WeatherApi() throws Exception {
		jsonObject = JSONReader.getWeatherJSONObject("");
		this.cityName = jsonObject.getJSONObject("city").get("name").toString();
		
	}
	
	private int checkDayTime(int dayTime) {
		if (dayTime > MAX_DAYTIME) {
			return MAX_DAYTIME;
		} else if (dayTime < 0) {
			return 0;
		}
		return dayTime;
	}

	public void setCityName(String cityName) throws Exception {
		jsonObject = JSONReader.getWeatherJSONObject(cityName);
		this.cityName = jsonObject.getJSONObject("city").get("name").toString();
		
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

	public String getCityCoordinates() throws Exception {
		return jsonObject.getJSONObject("city").getJSONObject("coord")
				.get("lat").toString() + ":" + jsonObject.getJSONObject("city").getJSONObject("coord")
				.get("lon").toString();
	}

	public String getDayWeather(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONArray("weather").getJSONObject(0)
				.get("main").toString();
	}

	public String getDayWindSpeed(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("wind").get("speed").toString();
	}

	public String getDayMinTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("main").get("temp_min").toString();
	}

	public String getDayMaxTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("main").get("temp_max").toString();
	}

	public JSONObject getAllData(int dayTime) throws Exception {
		String toReturn = "";
		toReturn += "City_Name " + cityName + "/n";
		toReturn += "Coordinates " + getCityCoordinates() + "/n";
		toReturn += "Min_Temp " + getDayMinTemp(dayTime) + " K /n";
		toReturn += "Max_Temp " + getDayMaxTemp(dayTime) + " K /n";
		toReturn += "Weather " + getDayWeather(dayTime) + "/n";
		toReturn += "Wind_Speed " + getDayWindSpeed(dayTime) + " m/s";
		return JSONReader.getJSONObjectFromText(toReturn);
	}
}
