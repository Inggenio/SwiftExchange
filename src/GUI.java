
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	double zielBetrag;
	double wechselsWert;
	double amount;

	JFrame meinFenster = new JFrame("W-hrung");
	JPanel panel = new JPanel();

	GridBagLayout gLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	JLabel message = new JLabel("Geben Sie die Ausgangswährung, die Zielwährung und den Betrag ein.");

	//Währung 1
	JLabel wahrung1Label = new JLabel("Ausgangswährung");
	JComboBox wahrung1Liste = new JComboBox(APIManager.waehrungen);

	//Wärung 2
	JLabel wahrung2T = new JLabel("Zielwährung");
	JComboBox wahrung2Liste = new JComboBox(APIManager.waehrungen);

	//Menge
	JLabel mengeLabel = new JLabel("Ausgangsbetrag");
	JTextField mengueField = new JTextField(30);

	int wah1 = wahrung1Liste.getSelectedIndex();
	int wah2 = wahrung2Liste.getSelectedIndex();

	JLabel wechsekurs = new JLabel("Wechselkurs");
	JLabel wechsel = new JLabel("");
	/*
	JLabel wechsel = new JLabel(String.valueOf(Logik.getWechselWert(wah1,wah2)));
	*/
	//Berechnen Button
	JButton knopf = new JButton("Berechnen");

	//Ziel Betrag

	JLabel zielBetragLabel = new JLabel("Betrag in Zielwährung");
	JLabel zielBetragWert = new JLabel();

	JButton info = new JButton("i");

	public void go() {

		//Layout
		//gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(3, 20, 3, 20);
		gbc.weightx = 0.4;
		gbc.weighty = 0.3;
		gbc.ipady = 2;
		gbc.ipadx = 1;
		gbc.gridheight = 1;

		Font myFont = zielBetragWert.getFont();

		//Panel Set
		panel.setLayout(gLayout);
		panel.setBackground(Color.darkGray);
		panel.setForeground(Color.darkGray);

		//Eingangs
		gbc.gridx = 0;
		gbc.gridy = 0;
		message.setForeground(Color.orange);
		panel.add(message);

		//Knöpfe und Felder

		//Währung 1
		gbc.gridx = 0;
		gbc.gridy = 1;
		wahrung1Label.setForeground(Color.LIGHT_GRAY);
		panel.add(wahrung1Label, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(wahrung1Liste, gbc);

		//MengueFeld
		gbc.gridx = 0;
		gbc.gridy = 2;
		mengeLabel.setForeground(Color.LIGHT_GRAY);
		panel.add(mengeLabel, gbc);

		gbc.weightx = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 2;
		mengueField.setForeground(Color.BLACK);
		panel.add(mengueField, gbc);

		//Wechselkurs
		wechsel.setFont(myFont.deriveFont(20f));
		gbc.gridx = 0;
		gbc.gridy = 3;
		wechsekurs.setForeground(Color.LIGHT_GRAY);
		panel.add(wechsekurs, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		wechsel.setForeground(Color.LIGHT_GRAY);
		panel.add(wechsel, gbc);

		//Währung 2
		gbc.gridx = 0;
		gbc.gridy = 4;
		wahrung2T.setForeground(Color.LIGHT_GRAY);
		panel.add(wahrung2T, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(wahrung2Liste, gbc);

		//Berechnen Knopf
		gbc.gridx = 1;
		gbc.gridy = 5;
		knopf.setForeground(Color.DARK_GRAY);
		panel.add(knopf, gbc);


		//ZielBetrag
		zielBetragWert.setFont(myFont.deriveFont(20f));
		gbc.gridx = 0;
		gbc.gridy = 6;
		zielBetragLabel.setForeground(Color.LIGHT_GRAY);
		panel.add(zielBetragLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		zielBetragWert.setForeground(Color.ORANGE);
		zielBetragWert.setFont(new Font("SansSerif", Font.PLAIN, 14));
		panel.add(zielBetragWert, gbc);

		/*
		//InfoButton
		info.setFont(new Font("SansSerif", Font.BOLD, 14));
		info.setForeground(Color.ORANGE);
		info.setIcon();
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(info, gbc);
		*/

		//Window
		meinFenster.add(panel);
		meinFenster.setSize(new Dimension(700, 600));
		meinFenster.setVisible(true);
		meinFenster.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ActionListener apiBerechnung = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String fromCurrency  = wahrung1Liste.getSelectedItem().toString();
				String toCurrency    = wahrung2Liste.getSelectedItem().toString();
				String from = fromCurrency.substring(0,3);
				String to   = toCurrency.substring(0,3);
				int count = 0;

				// Leer Feld validierung
				String amountText = mengueField.getText().trim();
				if (amountText.isEmpty()) {
					JOptionPane.showMessageDialog(
							null,
							"Bitte eine Menge eingeben.",
							"Menge Feld ist Leer",
							JOptionPane.WARNING_MESSAGE
					);
					return;
				}

				double amount;
				try {
					amountText = amountText.replace(",", ".");
					amount = Double.parseDouble(amountText);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(
							null,
							"Bitte einen gültigen Zahl eintragen.",
							"Format Fehler",
							JOptionPane.ERROR_MESSAGE
					);
					return;
				}

				System.out.println("From=" + from + " / To=" + to);
				System.out.println(amountText);
				System.out.println(amount);

				if(!fromCurrency.equalsIgnoreCase("Bitte wählen") && !toCurrency.equalsIgnoreCase("Bitte wählen")) {
					try {
						String myApiKey = APIKeyLoader.loadApiKey();
						APIManager request = new APIManager(myApiKey);

						String jsonResponse = request.convert(from, to, amount);
						//String jsonResponse = request.getWechselKurs(from, to);
						System.out.println(jsonResponse);
						count++;

						//Data Arrangement for the Logger Class
						String zielBetrag = request.getZielBetrag(jsonResponse);
						wechsel.setText(request.parseJsonQuote(jsonResponse));
						zielBetragWert.setText(request.parseJsonQuote(jsonResponse));

						//The Data we pass it onto the Logger Method in the Logger class
						LoggerClass.logger(count,from,to,wechsel.getText(),zielBetrag,amount);

					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(
								null,
								"Fehler bei der Verbindung zur API:\n" + ex.getMessage(),
								"API Fehler",
								JOptionPane.ERROR_MESSAGE
						);
					}
				} else {
					JOptionPane.showMessageDialog(
							null,
							"Bitte Aus- und Zielwährung wählen.",
							"Währungen auswählen",
							JOptionPane.WARNING_MESSAGE
					);
				}
			}
		};
		knopf.addActionListener(apiBerechnung);

	}

}
