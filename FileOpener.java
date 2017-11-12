package weatherApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {
	public static String openFile() {

		// The name of the file to open.
		String fileName = "input.txt";

		// This will reference one line at a time
		String line = "";
		String toReturn = "";

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				toReturn += line;
			}

			// Always close files.
			bufferedReader.close();
			return toReturn;
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
			return "";
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			return "";
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

}
