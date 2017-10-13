package weatherApi;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONObject;

public class WeatherApi {
	
	public static JSONObject getJSONObject() throws Exception {
	    // build a URL
	    String pageUrl = "http://api.openweathermap.org/data/2.5/forecast?id=524901&appid=4d72095dfc90617e337f65513da858c2";
	    URL url = new URL(pageUrl);
	    
	    // read from the URL
	    Scanner scan = new Scanner(url.openStream());
	    String str = "";
	    
	    while(scan.hasNext()) {
	    	str += scan.nextLine();
	    } 
	    scan.close();

	    return new JSONObject(str);
	}
	
	public static String getWeatherCity(String city) throws Exception {
		JSONObject jsonObject = getJSONObject();
		return jsonObject.getJSONObject(city).get("name").toString();
	}
	
	public static String getWeatherByDay(int day, String temp) throws Exception {
		JSONObject jsonObject = getJSONObject();
		String result = "";
		if (day >= 0 && day <= 5) {
			//TODO Fix get.
			if (temp.equals("min") || temp.equals("max")) {
				return jsonObject.getJSONObject("list").getJSONObject(Integer.toString(day)).get("temp_" + temp).toString();
			} else if (temp.equals("current")) {
				return jsonObject.getJSONObject("list").getJSONObject(Integer.toString(day)).getJSONObject("main").get("temp").toString();
			}
		}
	    return result;
	}
}
