import java.io.InputStream;
import java.util.Scanner;

public class APIKeyLoader {
	public static String loadApiKey() {
		InputStream is = APIKeyLoader.class.getResourceAsStream("/apikey.properties");

		if (is == null) {
			throw new RuntimeException("apikey.txt in resources nicht gefunden.");
		}

		try (Scanner scanner = new Scanner(is)) {
			if (scanner.hasNextLine()) {
				String line = scanner.nextLine().trim();
				return line.split("=")[1].trim();
			} else {
				throw new RuntimeException("Datei apikey.txt ist leer.");
			}
		} catch (Exception e) {
			throw new RuntimeException("Beim API key lesen es gab ein Fehler.", e);
		}
	}
}
