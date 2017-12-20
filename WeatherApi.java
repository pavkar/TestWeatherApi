package weatherApi;

import java.io.IOException;
import java.net.UnknownHostException;

import org.json.JSONObject;

import json.JSONReader;
import json.OfflineModeJSON;
import json.WeatherApiURLReader;
import scanner.InfoFromConsole;

public class WeatherApi {

	private static final int MAX_DAYTIME = 29;
	private JSONObject jsonObject;

	private String cityName;

	public WeatherApi() throws Exception {
		setCityName("");
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
		try {
			this.jsonObject = WeatherApiURLReader.getJSONObjectFromApi(cityName);
		} catch (UnknownHostException ex) {
			this.jsonObject = OfflineModeJSON.getOfflineApiInfo();
		} catch (IOException ex) {
			this.jsonObject = WeatherApiURLReader.getJSONObjectFromApi("Tallinn");
		}
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
		return jsonObject.getJSONObject("city").getJSONObject("coord").get("lat").toString() + ":"
				+ jsonObject.getJSONObject("city").getJSONObject("coord").get("lon").toString();
	}

	public String getDayWeather(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONArray("weather")
				.getJSONObject(0).get("main").toString();
	}

	public String getDayWindSpeed(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("wind").get("speed")
				.toString();
	}

	public String getDayMinTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("main")
				.get("temp_min").toString();
	}

	public String getDayMaxTemp(int dayTime) throws Exception {
		return jsonObject.getJSONArray("list").getJSONObject(checkDayTime(dayTime)).getJSONObject("main")
				.get("temp_max").toString();
	}

	// TODO getWeatherFOrAday
	public JSONObject getAllData(int dayTime) throws Exception {
		String toReturn = "";
		toReturn += "City_Name " + cityName + "/n";
		toReturn += "Coordinates " + getCityCoordinates() + "/n";
		for (int day = 0; day <= MAX_DAYTIME; day++) {
			toReturn += "Min_Temp_" + (day + 1) + " " + getDayMinTemp(dayTime) + " K /n";
			toReturn += "Max_Temp_" + (day + 1) + " " + getDayMaxTemp(dayTime) + " K /n";
			toReturn += "Weather_" + (day + 1) + " " + getDayWeather(dayTime) + "/n";
			toReturn += "Wind_Speed_" + (day + 1) + " " + getDayWindSpeed(dayTime) + " m/s";
		}

		return JSONReader.getJSONObjectFromText(toReturn);
	}
}
