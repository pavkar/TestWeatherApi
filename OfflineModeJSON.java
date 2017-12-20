package json;

import org.json.JSONException;
import org.json.JSONObject;

import fileManager.FileOpener;

public class OfflineModeJSON {

	public static JSONObject getOfflineApiInfo() throws JSONException {
		String jsonText = FileOpener.openFile("forecast.txt");
		JSONObject jsonObj = new JSONObject(jsonText);
		return jsonObj;
	}
}
