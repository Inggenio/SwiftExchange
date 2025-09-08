public class Main {
	public static void main(String[] args) {
		/*
		GUI programm = new GUI();
		programm.go();
		LoggerClass.dataLog();
		 */
		String myApiKey = APIKeyLoader.loadApiKey();
		APIManager request = new APIManager(myApiKey);
		System.out.println(request.convert("USD","EUR", 20.00));
	}
}
