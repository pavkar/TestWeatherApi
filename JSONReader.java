package weatherApi;

import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class JSONReader {

	private static JSONObject getJSONObjectWithCityName(String cityName) throws Exception {
		// build a URL
		String pageUrl = "http://api.openweathermap.org/data/2.5/forecast?q=" + cityName
				+ "&appid=4d72095dfc90617e337f65513da858c2";
		URL url = new URL(pageUrl);

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = "";

		while (scan.hasNext()) {
			str += scan.nextLine();
		}
		scan.close();

		return new JSONObject(str);
	}

	private static JSONObject getJSONObjectTallinn() throws Exception {
		// build a URL
		String pageUrl = "http://api.openweathermap.org/data/2.5/forecast?q=Tallinn&appid=4d72095dfc90617e337f65513da858c2";
		URL url = new URL(pageUrl);

		// read from the URL
		Scanner scan = new Scanner(url.openStream());
		String str = "";

		while (scan.hasNext()) {
			str += scan.nextLine();
		}
		scan.close();

		return new JSONObject(str);
	}

	public static JSONObject getJSONObjectObject(String cityName) throws Exception {
		JSONObject jsonObject = getJSONObjectWithCityName(cityName);
		if (jsonObject.get("cod") == "404" || jsonObject.get("cod") == "400") {
			return getJSONObjectTallinn();
		}
		return jsonObject;
	}
}
