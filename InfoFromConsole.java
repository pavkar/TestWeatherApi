package scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InfoFromConsole {
	public static String getInfoFromConsole() {
		System.out.println("Enter city name here : ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		try {
			return bufferRead.readLine();
		} catch (Exception e) {
			return "Tallinn";
		}
	}
}
