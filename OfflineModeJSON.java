package json;

import org.json.JSONException;
import org.json.JSONObject;

import fileManager.FileOpener;

public class OfflineModeJSON {

	protected static JSONObject getOfflineApiInfo(String cityName) throws JSONException {
		String jsonText = FileOpener.openFile("forecast.txt");
		JSONObject jsonObj = new JSONObject(jsonText);
		System.out.println(jsonObj);
		jsonObj.getJSONObject("city").put("name", cityName);
		return jsonObj;
	}
}
