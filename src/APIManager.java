import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIManager {

	private static final String BASE_URL = "https://api.exchangerate.host/convert";
	private String apiKey;

	public APIManager(String apiKey) {
		this.apiKey = apiKey;
	}

	// Convierte de una moneda a otra y devuelve el resultado numérico
	public String convert(String from, String to, double amount) {
		StringBuilder result = new StringBuilder();
		try {
			// URL Build (con API key)
			String urlString = BASE_URL + "?from=" + from + "&to=" + to + "&amount=" + amount + "&access_key=" + apiKey;
			URL url = new URL(urlString);

			// Connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// Response Read
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			reader.close();
			System.out.println(urlString);
			// Parsear el JSON
			//return parseResult(result.toString());
			return result.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error!";
	}

	private double parseResult(String json) {
		String marker = "\"result\":";
		int index = json.indexOf(marker);
		if (index != -1) {
			int start = index + marker.length();
			int end = json.indexOf(",", start);
			if (end == -1) {
				end = json.indexOf("}", start);
			}
			String value = json.substring(start, end).trim();
			return Double.parseDouble(value);
		}
		throw new RuntimeException("'result' not found");
	}
}
