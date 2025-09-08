
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

public class GUI {
	double zielBetrag;
	double wechselsWert;

	JFrame meinFenster = new JFrame("W-hrung");
	JPanel panel = new JPanel();

	GridBagLayout gLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();

	JLabel message = new JLabel("Geben Sie die Ausgangswährung, die Zielwährung und den Betrag ein.");

	//Währung 1
	JLabel wahrung1Label = new JLabel("Ausgangswährung");
	JComboBox wahrung1Liste = new JComboBox(Logik.waehrungen);

	//Wärung 2
	JLabel wahrung2T = new JLabel("Zielwährung");
	JComboBox wahrung2Liste = new JComboBox(Logik.waehrungen);

	//Menge
	JLabel menge = new JLabel("Ausgangsbetrag");
	JTextField mengueField = new JTextField(30);

	int wah1 = wahrung1Liste.getSelectedIndex();
	int wah2 = wahrung2Liste.getSelectedIndex();

	JLabel wechsekurs = new JLabel("Wechselkurs");
	JLabel wechsel = new JLabel(String.valueOf(Logik.getWechselWert(wah1,wah2)));

	//Berechnen Button
	JButton knopf = new JButton("Berechnen");

	//Ziel Betrag

	JLabel zielBetragLabel = new JLabel("Betrag in Zielwährung");
	JLabel zielBetragWert = new JLabel(String.valueOf(Logik.zielBetragBerechnen(wah1,wah2,zielBetrag)));

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

		//Währung 2
		gbc.gridx = 0;
		gbc.gridy = 2;
		wahrung2T.setForeground(Color.LIGHT_GRAY);
		panel.add(wahrung2T, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel.add(wahrung2Liste, gbc);

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

		//MengueFeld
		gbc.gridx = 0;
		gbc.gridy = 4;
		menge.setForeground(Color.LIGHT_GRAY);
		panel.add(menge, gbc);


		gbc.weightx = 1.0;
		gbc.gridx = 1;
		gbc.gridy = 4;
		mengueField.setForeground(Color.BLACK);
		panel.add(mengueField, gbc);

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

		//Action Listener
		ActionListener wechselAktualisieren = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				wah1 = wahrung1Liste.getSelectedIndex();
				wah2 = wahrung2Liste.getSelectedIndex();
				wechselsWert = Logik.getWechselWert(wah1, wah2);
				wechsel.setText(String.valueOf(wechselsWert));
			}
		};
		wahrung1Liste.addActionListener(wechselAktualisieren);
		wahrung2Liste.addActionListener(wechselAktualisieren);


		ActionListener berechnung = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int wah1 = wahrung1Liste.getSelectedIndex();
					int wah2 = wahrung2Liste.getSelectedIndex();
					String eingabe = mengueField.getText().replace(",",".");
					double betrag = Double.parseDouble(eingabe);

					if (betrag < 0) {
						zielBetragWert.setText("Betrag darf nicht negativ sein");
						return;
					}
					DecimalFormat df = new DecimalFormat("#.00");
					double ergebnis = Logik.zielBetragBerechnen(wah1, wah2, betrag);

					//Ergebnis
					zielBetragWert.setText(df.format(ergebnis) + " " + Logik.getWaerungenSymbol(wah2));

				} catch (NumberFormatException ex) {
					zielBetragWert.setText("Betrag Ungültig");
				}
				LoggerClass.logger(
						Logik.getWaerungenSymbol(wah1),
						Logik.getWaerungenSymbol(wah2),
						Logik.getWechselWert(wah1,wah2),
						mengueField.getText(),
						zielBetragWert.getText());
				mengueField.setText("");
			}
		};
		mengueField.addActionListener(berechnung);
		knopf.addActionListener(berechnung);

		meinFenster.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LoggerClass.closer();
				super.windowClosing(e);
			}
		});
	}

}
