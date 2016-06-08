package whs.gdi2.tippspiel.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import whs.gdi2.tippspiel.Config;
import whs.gdi2.tippspiel.Main;
import whs.gdi2.tippspiel.database.DatabaseManagement;
import whs.gdi2.tippspiel.database.MySQLConnection;
import whs.gdi2.tippspiel.log.Log;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

/**
 * Database management tool for the instance of testdatabase.
 * 
 * This tool can recreate the the database and fill it with testdata. It can
 * also show some informations over the testdatabase.
 * 
 * It is only usable with the testdatabase because testdatabase instances has
 * always more rights than instances which holds livedatabases.
 * 
 * @since 1.0
 * @version 1.2
 * @author Mario Kellner <mario.kellner@studmail.w-hs.de>
 *
 */
public class DBToolDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblHost;
	private JLabel lblUser;
	private JLabel lblDatabase;
	private JLabel lblDBExistiert;
	private JPanel panel;
	private JButton btnCreateTables;
	private JButton btnDatenbankNeuAnlegen;
	private JButton button;
	private JPanel buttonPane;
	private JButton cancelButton;
	private DBToolDialog classContext;
	private JLabel lblHostPlaceholder;
	private JLabel lblUserPlaceholder;
	private JLabel lblDbPlaceholder;
	private JLabel lblTableplaceholder;
	private JLabel lblTabellenExistieren;
	private JLabel lblDatenbankExistiert;
	private JFileChooser fc;

	/**
	 * Create the dialog.
	 */
	public DBToolDialog(JFrame parent) {
		super(parent);

		classContext = this;

		setTitle("Tippspiel Admin - Tool | Verwaltung der Testdatenbank");
		setBackground(Config.getGuiColor());
		setModal(true);
		setSize(450, 300);

		Dimension windowSize = this.getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - windowSize.width / 2, screenSize.height / 2 - windowSize.height / 2);

		InitializeGui();
		InitializeEvents();

		reload();
	}

	public void InitializeGui() {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Config.getGuiColor());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		fc = new JFileChooser();

		panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 168, 215);
		panel_1.setBorder(new LineBorder(UIManager.getColor("ComboBox.disabledForeground"), 2));
		panel_1.setBackground(UIManager.getColor("Button.light"));
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("Datenbankstatus");
		lblNewLabel.setBounds(7, 7, 101, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.add(lblNewLabel);

		lblHost = new JLabel("Host:");
		lblHost.setBounds(7, 32, 36, 14);
		panel_1.add(lblHost);

		lblHostPlaceholder = new JLabel("host placeholder");
		lblHostPlaceholder.setBounds(7, 50, 151, 14);
		panel_1.add(lblHostPlaceholder);

		lblUser = new JLabel("User:");
		lblUser.setBounds(7, 72, 36, 14);
		panel_1.add(lblUser);

		lblUserPlaceholder = new JLabel("user placeholder");
		lblUserPlaceholder.setBounds(7, 90, 123, 14);
		panel_1.add(lblUserPlaceholder);

		lblDatabase = new JLabel("Datenbank:");
		lblDatabase.setBounds(7, 115, 86, 14);
		panel_1.add(lblDatabase);

		lblDbPlaceholder = new JLabel("db placeholder");
		lblDbPlaceholder.setBounds(7, 133, 76, 14);
		panel_1.add(lblDbPlaceholder);

		lblDBExistiert = new JLabel("Datenbank existiert: ");
		lblDBExistiert.setBounds(7, 165, 123, 14);
		panel_1.add(lblDBExistiert);

		lblDatenbankExistiert = new JLabel("ja");
		lblDatenbankExistiert.setBounds(131, 165, 48, 14);
		panel_1.add(lblDatenbankExistiert);

		lblTabellenExistieren = new JLabel("Tabellen existieren:");
		lblTabellenExistieren.setBounds(7, 190, 123, 14);
		panel_1.add(lblTabellenExistieren);

		lblTableplaceholder = new JLabel("tableplaceholder");
		lblTableplaceholder.setBounds(131, 190, 101, 14);
		panel_1.add(lblTableplaceholder);

		panel = new JPanel();
		panel.setBounds(173, 0, 256, 220);
		panel.setBackground(Config.getGuiColor());
		contentPanel.add(panel);
		panel.setLayout(null);

		btnCreateTables = new JButton("L\u00F6sche und Erstelle Tabellen");
		btnCreateTables.setBounds(10, 101, 236, 27);
		panel.add(btnCreateTables);
		btnCreateTables.setFont(new Font(Config.getFont(), Font.PLAIN, 15));
		btnCreateTables.setBackground(Config.getGuiColor());

		button = new JButton("Testdaten Einf�gen");
		button.setBounds(10, 139, 236, 27);
		panel.add(button);

		button.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		button.setBackground(Color.WHITE);

		btnDatenbankNeuAnlegen = new JButton("Datenbank neu anlegen");
		btnDatenbankNeuAnlegen.setBounds(10, 63, 236, 27);

		btnDatenbankNeuAnlegen.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		btnDatenbankNeuAnlegen.setBackground(Color.WHITE);
		panel.add(btnDatenbankNeuAnlegen);

		buttonPane = new JPanel();
		buttonPane.setBackground(Config.getGuiColor());
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		cancelButton = new JButton("Schlie\u00DFen");

		cancelButton.setBackground(Config.getGuiColor());
		cancelButton.setFont(new Font(Config.getFont(), Font.PLAIN, 13));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	public void InitializeEvents() {
		
		String[] options = {"Interne Testdaten benutzen", "Externe Testdaten benutzen"};
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Log.info("Menue item 'Testdaten Einf�gen' clicked.");

				if (DatabaseManagement.addTestData(Main.mainConnection)) {
					int answer = JOptionPane.showOptionDialog(classContext, "Wie m�chten Sie die Tastdaten einlesen?",
							"Testdaten Einlesen", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					        null, options, options[0]);

					if (answer == JOptionPane.YES_OPTION) {
						DatabaseManagement.importGameData(Main.mainConnection, new InputStreamReader(
								DBToolDialog.class.getResourceAsStream("/whs/gdi2/tippspiel/data/spiele_test.txt")));
					} else {

						int returnVal = fc.showOpenDialog(classContext);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();

							try {
								DatabaseManagement.importGameData(Main.mainConnection,
										new InputStreamReader(new FileInputStream(file)));
							} catch (FileNotFoundException e1) {
								Log.error("Cannot open " + file.getAbsolutePath());
								JOptionPane.showMessageDialog(classContext, "Spiele wurden nicht importiert");

							}

						} else {
							JOptionPane.showMessageDialog(classContext, "Spiele wurden nicht importiert");
							Log.info("Spiele wurden nicht importiert");
						}
					}
					JOptionPane.showMessageDialog(classContext, "Testdaten wurden in Datenbank geschrieben.",
							"Testdaten importieren", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(classContext, "Testdaten konnten nicht importiert werden.",
							"Testdaten importieren", JOptionPane.WARNING_MESSAGE);
				}

				reload();
			}
		});

		btnDatenbankNeuAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				DatabaseManagement.createDB(Main.mainConnection);
				reload();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBToolDialog.this.dispose();
			}
		});

		btnCreateTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Log.info("Menue item 'Erstelle Tabellen' clicked.");
				DatabaseManagement.dropTables(Main.mainConnection);
				
				if (!DatabaseManagement.createTables(Main.mainConnection)) {
					JOptionPane.showMessageDialog(classContext, "Tabellen konnten nicht erstellt werden.",
							"Tabellen erstellen", JOptionPane.WARNING_MESSAGE);
				} else {

					JOptionPane.showMessageDialog(classContext, "Tabellen wurden erfolgreich angelegt.",
							"Tabellen erstellen", JOptionPane.PLAIN_MESSAGE);
				}
				reload();
			}
		});
	}

	public void reload() {
		lblHostPlaceholder.setText(Config.getDBIp_offline());
		lblUserPlaceholder.setText(Config.getDBUser_offline());
		lblDbPlaceholder.setText(Config.getDB_offline());

		lblTableplaceholder.setText("nein");
		lblDatenbankExistiert.setText("nein");

		if (MySQLConnection.testConnection(Config.getDBIp_offline(), Config.getDBUser_offline(),
				Config.getDBPass_offline(), "")) {
			if (MySQLConnection.testConnection(Config.getDBIp_offline(), Config.getDBUser_offline(),
					Config.getDBPass_offline(), Config.getDB_offline())) {
				lblDatenbankExistiert.setText("ja");
				try {
					Statement stmt = Main.mainConnection.getConnection().createStatement();
					stmt.executeQuery("SELECT * FROM spiele LIMIT 1");
					lblDatenbankExistiert.setText("ja");
					lblTableplaceholder.setText("ja");
				} catch (Exception e) {
					// tables doesnt exists;
				}
			}
		}
	}
}
