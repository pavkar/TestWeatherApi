package fileManager;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class FileMaker {
	public static void writeFile(JSONObject weatherData) {
		// The name of the file to open.

		String cityName;
		try {
			cityName = weatherData.get("City_Name").toString();
		} catch (JSONException e) {
			weatherData = new JSONObject();
			cityName = "Error";
		}
		String fileName = cityName + ".txt";

		try {
			FileWriter fileWriter = new FileWriter(fileName);
			
			String weatherDataString = weatherData.toString().replaceAll(",", "\n");
			
			fileWriter.write(weatherDataString);
			fileWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

}
