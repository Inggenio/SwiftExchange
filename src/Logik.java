
public class Logik {

	public static String[] waehrungen = {
			"(EUR) Euro ",
			"(GBP) Britisches Pfund ",
			"(CHF) Schweizer Franken ",
			"(DKK) Dänische Krone ",
			"(NOK) Norwegische Krone ",
			"(SEK) Schwedische Krone ",
			"(PLN) Polnischer Złoty ",
			"(ARS) Argentinischer Peso",
			"(USD) US-Dollar"
	};
	public static String getWaerungenSymbol(int s) {
		String[] symbols = {
				"€",
				"£",
				"CHF",
				"kr",
				"kr",
				"kr",
				"zł",
				"$",
				"US$",
		};
		return symbols[s];
	}

	public static String getWaehrung(int a){
		return waehrungen[a].toString();
	}

	static Double[][] werte = {
			{ 1.00	 ,0.86  ,0.97	,7.46	,11.35	,11.65	,4.31	,930.00 ,1.07	}, //EUR
			{ 1.17	 ,1.00  ,1.13	,8.66	,13.15	,13.55  ,5.01	,1080.00,1.25	}, //GBP
			{ 1.03	,0.88	,1.00	,7.65	,11.60	,11.95	,4.45	,960.00	,1.10	}, //CHF
			{ 0.13	,0.12	,0.13	,1.00	,1.52	,1.56	,0.58	,125.00	,0.14	}, //DKK
			{ 0.09	,0.08	,0.09	,0.66	,1.00	,1.03	,0.38	,82.00	,0.095	}, //NOK
			{ 0.09	,0.07	,0.08	,0.64	,0.97	,1.00	,0.37	,80.00	,0.093	},//SEK
			{ 0.23	,0.20	,0.22	,1.72	,2.63	,2.70	,1.00	,215.00	,0.25	}, //PLN
			{ 0.0011,0.0009	,0.0010	,0.0080	,0.012	,0.0125	,0.0046	,1.00	,0.0012	},//ARS
			{0.93	,0.80	,0.91	,6.96	,10.50	,10.80	,4.00	,1280.00,1.00	} //USD
			// EUR    | GBP   | CHF   | DKK   | NOK   | SEK   | PLN	| ARS	|USD  |
	};

	public static double getWechselWert(int a, int b){
		return werte[a][b];
	}
	public static double zielBetragBerechnen(int a, int b, double mengue){
		return getWechselWert(a,b) * mengue;
	}



}
