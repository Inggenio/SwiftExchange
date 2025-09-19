
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerClass {

	static String pfad = "Logs/";
	//static String dateiname = "SessionLog-" + dateiName() + ".txt";
	static String dateiname = "SessionLogs.txt";
	static String komplettPfad = pfad + dateiname;

	static String dateiName(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH:mm:ss");
		LocalDateTime jetzt = LocalDateTime.now();
		return jetzt.format(formatter);
	}

	static String getZeit(){
		DateTimeFormatter formatterZeit = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime jetztUhr = LocalDateTime.now();
		return jetztUhr.format(formatterZeit);
	}

	static String getZeitUndDate(){
		DateTimeFormatter formatterZeit = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss");
		LocalDateTime jetztDateUhr = LocalDateTime.now();
		return jetztDateUhr.format(formatterZeit);
	}
	//Metodo que escribe los Logs
	public static void dataLog(){

		File log = new File(pfad);
		if(!log.exists()){
			log.mkdir();
		}
		File logDatei = new File(log, dateiname);

		try{
			if(logDatei.createNewFile()){
				System.out.println("Log Datei erstellt" + logDatei.getAbsolutePath());
			} else {
				System.out.println("LogDatei existiert bereits");
			}
		} catch (IOException e){
			System.out.println("Fehler beim Erstellen der Logdatei");
			e.printStackTrace();
		}
		FileWriter logKopf = null;
		try {
			logKopf = new FileWriter(komplettPfad,true);
			logKopf.write("Session Log um " + getZeitUndDate() + " geöffnet\n Operation Log:\n");
			logKopf.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void logsOpenClose(String operation){
		try {
			FileWriter log = new FileWriter(komplettPfad,true);
			log.write("\n-------------------------------------------------------------" +
					"\n Sesion " + operation + " at " + getZeitUndDate());
			log.close();
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim schreiben in der Logdatei",e);
		}
	}

	public static void operationLogger(int count, String w1, String w2, String wechselkuers, String mengue, double zielbretrag){

		try {
			FileWriter log = new FileWriter(komplettPfad,true);
			log.write("\n=> Operation "+ count +" am " + getZeit() +
					"\n-> AusgangW: " + w1 + "//  ZielW: " + w2 +
					"\nWechselkurs: " + wechselkuers +
					"\nMengue: " + mengue +
					"\nBetrag: " + zielbretrag);
			log.close();
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim schreiben in der Logdatei",e);
		}
	}
	public static void JSONlogger(String JSON){

		try {
			FileWriter log = new FileWriter(komplettPfad,true);
			log.write("\n-" + JSON);
			log.close();
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim schreiben in der Logdatei",e);
		}
	}

	public static void closer(){
		try (FileWriter closer = new FileWriter(komplettPfad, true)) {

			closer.write("\n\nSession um " + getZeitUndDate() + " geschlossen");
		} catch (IOException e) {
			throw new RuntimeException("Fehler beim schreiben in der Logdatei",e);
		}
	}

}
