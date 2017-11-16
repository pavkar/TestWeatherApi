package fileManager;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {

	private static JSONObject getJSONObjectFromApi(String cityName) throws Exception {
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
	
	public static JSONObject getJSONObjectFromText(String text) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		String[] textSplitted = text.split("/n");
		for (String parts : textSplitted) {
			String[] partsSplitted = parts.split(" ");
			jsonObj.put(partsSplitted[0], partsSplitted[1]);
		}
		return jsonObj;
	}

	public static JSONObject getJSONObjectObject(String cityName) throws Exception {
		try {
			JSONObject jsonObject = getJSONObjectFromApi(cityName);
			return jsonObject;
		} catch (IOException ex) {
			return getJSONObjectFromApi("Tallinn");
		}

	}
}
