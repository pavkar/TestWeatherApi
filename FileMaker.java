package weatherApi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileMaker {
	public static void writeFile(String weatherData) {
		// The name of the file to open.
		String[] toWrite = weatherData.split("/n");
		String fileName = "output.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
			for (String toAdd : toWrite) {
				bufferedWriter.write(toAdd);
				bufferedWriter.newLine();
			}

			// Always close files.
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

}
