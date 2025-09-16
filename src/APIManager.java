import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIManager {

	private static final String BASE_URL = "https://api.exchangerate.host/";
	private String apiKey;

	public APIManager(String apiKey) {
		this.apiKey = apiKey;
	}

	// Convierte de una moneda a otra y devuelve el resultado numérico
	public String convert(String from, String to, double amount) {
		StringBuilder result = new StringBuilder();
		try {
			// URL Build (con API key)
			String urlString = BASE_URL + "convert" + "?from=" + from + "&to=" + to + "&amount=" + amount + "&access_key=" + apiKey;
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
	public String getWechselKurs(String from, String to) {
		StringBuilder result = new StringBuilder();
		try {
			// URL Build (con API key)
			String urlString = BASE_URL + "latest?from=" + from + "&to=" + to + "&access_key=" + apiKey;
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
	public static String getWechselKurs(String json){
		try {
			String marker = "\"quote\":";
			int index = json.indexOf(marker);

			if (index != -1) {
				int start = index + marker.length();
				int end = json.indexOf("}", start); // cierra el objeto
				if (end == -1) end = json.length();

				return json.substring(start, end).trim();
			} else {
				System.err.println("⚠️ 'quote' not found in response: " + json);
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	public String getZielBetrag(String json) {
		try {
			String marker = "\"result\":";
			int index = json.indexOf(marker);

			if (index != -1) {
				int start = index + marker.length();
				int end = json.indexOf("}", start); // cierra el objeto
				if (end == -1) end = json.length();

				return json.substring(start, end).trim();
			} else {
				System.err.println("⚠️ 'result' not found in response: " + json);
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	public static String[] waehrungen = {
			"Bitte wählen",
			"ARS 	Argentine Peso",
			"AUD 	Australian Dollar",
			"EUR 	Euro",
			"GBP 	British Pound Sterling",
			"JPY 	Japanese Yen",
			"USD 	United States Dollar",
			"AED 	United Arab Emirates Dirham",
			"AFN 	Afghan Afghani",
			"ALL 	Albanian Lek",
			"AMD 	Armenian Dram",
			"ANG 	Netherlands Antillean Guilder",
			"AOA 	Angolan Kwanza",
			"AWG 	Aruban Florin",
			"AZN 	Azerbaijani Manat",
			"BAM 	Bosnia-Herzegovina Convertible Mark",
			"BBD 	Barbadian Dollar",
			"BDT 	Bangladeshi Taka",
			"BGN 	Bulgarian Lev",
			"BHD 	Bahraini Dinar",
			"BIF 	Burundian Franc",
			"BMD 	Bermudan Dollar",
			"BND 	Brunei Dollar",
			"BOB 	Bolivian Boliviano",
			"BRL 	Brazilian Real",
			"BSD 	Bahamian Dollar",
			"BTC 	Bitcoin",
			"BTN 	Bhutanese Ngultrum",
			"BWP 	Botswanan Pula",
			"BYR 	Belarusian Ruble",
			"BZD 	Belize Dollar",
			"CAD 	Canadian Dollar",
			"CDF 	Congolese Franc",
			"CHF 	Swiss Franc",
			"CLF 	Chilean Unit of Account (UF)",
			"CLP 	Chilean Peso",
			"CNY 	Chinese Yuan",
			"COP 	Colombian Peso",
			"CRC 	Costa Rican Colón",
			"CUC 	Cuban Convertible Peso",
			"CUP 	Cuban Peso",
			"CVE 	Cape Verdean Escudo",
			"CZK 	Czech Republic Koruna",
			"DJF 	Djiboutian Franc",
			"DKK 	Danish Krone",
			"DOP 	Dominican Peso",
			"DZD 	Algerian Dinar",
			"EGP 	Egyptian Pound",
			"ERN 	Eritrean Nakfa",
			"ETB 	Ethiopian Birr",
			"FJD 	Fijian Dollar",
			"FKP 	Falkland Islands Pound",
			"GEL 	Georgian Lari",
			"GGP 	Guernsey Pound",
			"GHS 	Ghanaian Cedi",
			"GIP 	Gibraltar Pound",
			"GMD 	Gambian Dalasi",
			"GNF 	Guinean Franc",
			"GTQ 	Guatemalan Quetzal",
			"GYD 	Guyanaese Dollar",
			"HKD 	Hong Kong Dollar",
			"HNL 	Honduran Lempira",
			"HRK 	Croatian Kuna",
			"HTG 	Haitian Gourde",
			"HUF 	Hungarian Forint",
			"IDR 	Indonesian Rupiah",
			"ILS 	Israeli New Sheqel",
			"IMP 	Manx pound",
			"INR 	Indian Rupee",
			"IQD 	Iraqi Dinar",
			"IRR 	Iranian Rial",
			"ISK 	Icelandic Króna",
			"JEP 	Jersey Pound",
			"JMD 	Jamaican Dollar",
			"JOD 	Jordanian Dinar",
			"KES 	Kenyan Shilling",
			"KGS 	Kyrgystani Som",
			"KHR 	Cambodian Riel",
			"KMF 	Comorian Franc",
			"KPW 	North Korean Won",
			"KRW 	South Korean Won",
			"KWD 	Kuwaiti Dinar",
			"KYD 	Cayman Islands Dollar",
			"KZT 	Kazakhstani Tenge",
			"LAK 	Laotian Kip",
			"LBP 	Lebanese Pound",
			"LKR 	Sri Lankan Rupee",
			"LRD 	Liberian Dollar",
			"LSL 	Lesotho Loti",
			"LTL 	Lithuanian Litas",
			"LVL 	Latvian Lats",
			"LYD 	Libyan Dinar",
			"MAD 	Moroccan Dirham",
			"MDL 	Moldovan Leu",
			"MGA 	Malagasy Ariary",
			"MKD 	Macedonian Denar",
			"MMK 	Myanma Kyat",
			"MNT 	Mongolian Tugrik",
			"MOP 	Macanese Pataca",
			"MRO 	Mauritanian Ouguiya",
			"MUR 	Mauritian Rupee",
			"MVR 	Maldivian Rufiyaa",
			"MWK 	Malawian Kwacha",
			"MXN 	Mexican Peso",
			"MYR 	Malaysian Ringgit",
			"MZN 	Mozambican Metical",
			"NAD 	Namibian Dollar",
			"NGN 	Nigerian Naira",
			"NIO 	Nicaraguan Córdoba",
			"NOK 	Norwegian Krone",
			"NPR 	Nepalese Rupee",
			"NZD 	New Zealand Dollar",
			"OMR 	Omani Rial",
			"PAB 	Panamanian Balboa",
			"PEN 	Peruvian Nuevo Sol",
			"PGK 	Papua New Guinean Kina",
			"PHP 	Philippine Peso",
			"PKR 	Pakistani Rupee",
			"PLN 	Polish Zloty",
			"PYG 	Paraguayan Guarani",
			"QAR 	Qatari Rial",
			"RON 	Romanian Leu",
			"RSD 	Serbian Dinar",
			"RUB 	Russian Ruble",
			"RWF 	Rwandan Franc",
			"SAR 	Saudi Riyal",
			"SBD 	Solomon Islands Dollar",
			"SCR 	Seychellois Rupee",
			"SDG 	Sudanese Pound",
			"SEK 	Swedish Krona",
			"SGD 	Singapore Dollar",
			"SHP 	Saint Helena Pound",
			"SLL 	Sierra Leonean Leone",
			"SOS 	Somali Shilling",
			"SRD 	Surinamese Dollar",
			"STD 	São Tomé and Príncipe Dobra",
			"SVC 	Salvadoran Colón",
			"SYP 	Syrian Pound",
			"SZL 	Swazi Lilangeni",
			"THB 	Thai Baht",
			"TJS 	Tajikistani Somoni",
			"TMT 	Turkmenistani Manat",
			"TND 	Tunisian Dinar",
			"TOP 	Tongan Paʻanga",
			"TRY 	Turkish Lira",
			"TTD 	Trinidad and Tobago Dollar",
			"TWD 	New Taiwan Dollar",
			"TZS 	Tanzanian Shilling",
			"UAH 	Ukrainian Hryvnia",
			"UGX 	Ugandan Shilling",
			"UYU 	Uruguayan Peso",
			"UZS 	Uzbekistan Som",
			"VEF 	Venezuelan Bolívar Fuerte",
			"VND 	Vietnamese Dong",
			"VUV 	Vanuatu Vatu",
			"WST 	Samoan Tala",
			"XAF 	CFA Franc BEAC",
			"XAG 	Silver (troy ounce)",
			"XAU 	Gold (troy ounce)",
			"XCD 	East Caribbean Dollar",
			"XDR 	Special Drawing Rights",
			"XOF 	CFA Franc BCEAO",
			"XPF 	CFP Franc",
			"YER 	Yemeni Rial",
			"ZAR 	South African Rand",
			"ZMK 	Zambian Kwacha (pre-2013)",
			"ZMW 	Zambian Kwacha",
			"ZWL 	Zimbabwean Dollar",
	};
}
